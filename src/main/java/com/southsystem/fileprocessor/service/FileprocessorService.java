package com.southsystem.fileprocessor.service;

import com.southsystem.fileprocessor.config.BrokerInput;
import com.southsystem.fileprocessor.config.ProcessorConfig;
import com.southsystem.fileprocessor.dto.ProcessFileRequestDTO;
import com.southsystem.fileprocessor.model.Client;
import com.southsystem.fileprocessor.model.Sale;
import com.southsystem.fileprocessor.model.SaleItem;
import com.southsystem.fileprocessor.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileprocessorService {

    @Autowired
    ProcessorConfig processorConfig;

    @StreamListener(BrokerInput.FILE_PROCESSOR)
    public void processFile(ProcessFileRequestDTO processFileRequestDTO) {

        List<String> lines = processFileRequestDTO.getLines();

        List<Seller> sellers = lines.parallelStream().filter(a -> a.startsWith(processorConfig.getSellerMarkingCode()))
                .map(line -> line.split(processorConfig.getFirstSplitSeparator()))
                .map(a2 -> new Seller(a2[1], extractName(a2, processorConfig.getFirstSplitSeparator(), 2, 1), new BigDecimal(a2[a2.length - 1]))).collect(Collectors.toList());

        List<Client> clients = lines.parallelStream().filter(a -> a.startsWith(processorConfig.getClientMarkingCode())).map(line -> line.split(processorConfig.getFirstSplitSeparator())).map(a2 ->
                new Client(a2[1], extractName(a2, processorConfig.getFirstSplitSeparator(), 2, 1), a2[a2.length - 1])).collect(Collectors.toList());

        List<Sale> sales = lines.parallelStream().filter(a -> a.startsWith("003")).map(line -> line.split(processorConfig.getFirstSplitSeparator())).map(a2 -> {
            Stream<String> subStream = Pattern.compile(processorConfig.getSecondSplitSeparator()).splitAsStream( a2[2].replace(processorConfig.getItemReplace1(), "")
                    .replace(processorConfig.getItemReplace2(), "") );
            List<SaleItem> salesItems = subStream.map(sub -> sub.split(processorConfig.getThirdSplitSeparator())).map(sub2 ->
                    new SaleItem(Long.parseLong(sub2[0]), Long.parseLong(sub2[1]), new BigDecimal(sub2[2]))
            ).collect(Collectors.toList());
            return new Sale(Long.parseLong(a2[1]), salesItems, extractName(a2, processorConfig.getFirstSplitSeparator(), 3, 0));
        }).collect(Collectors.toList());

        File outputFile = null;
        try {
            outputFile = createOutputFile(processorConfig.getOutDirPath(), processFileRequestDTO.getPath());
            printFile(outputFile, sellers, clients, sales);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File createOutputFile(String dirOut, String currentFileName) throws Exception{
        File pathDir = new File(System.getProperty("user.home") + processorConfig.getOutDirPath());

        if (!pathDir.exists() && !pathDir.mkdirs()) {
            throw new Exception("Caminho não existe ou não pode ser criado: " + dirOut);
        }
        int lastIndex = currentFileName.lastIndexOf(".dat");
        String outputFileName = pathDir.getAbsolutePath() + "//" + currentFileName.substring(0, lastIndex) + ".done.dat";
        return new File(outputFileName);
    }

    public static void printFile(File outputFile, List<Seller> sellers, List<Client> clients, List<Sale> sales){
        try {

            PrintWriter output = new PrintWriter(outputFile);
            output.println("Quantidade de clientes no arquivo de entrada: " + clients.size());
            output.println("Quantidade de vendedores no arquivo de entrada: " + sellers.size());
            sales.stream().sorted(Comparator.comparing(Sale::getTotal));
            output.println("ID da venda mais cara: " + sales.get(sales.size()-1).getSaleId());
            output.println("O pior vendedor: " + sales.get(0).getSellerName());
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String extractName(String[] splitLine, String split, int initialPosition, int finalPosition) {
        if (splitLine.length > 4) {
            for (int i = initialPosition + 1; i < splitLine.length - finalPosition; i++) {
                splitLine[initialPosition] = splitLine[initialPosition] + split + splitLine[i];
            }
        }
        return splitLine[initialPosition];
    }

}
