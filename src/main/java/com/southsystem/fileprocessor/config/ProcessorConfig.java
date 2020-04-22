package com.southsystem.fileprocessor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {
    @Value("${inDirPath}")
    private String inDirPath;

    @Value("${outDirPath}")
    private String outDirPath;

    @Value("${firstSplitSeparator}")
    private String firstSplitSeparator;

    @Value("${secondSplitSeparator}")
    private String secondSplitSeparator;

    @Value("${thirdSplitSeparator}")
    private String thirdSplitSeparator;

    @Value("${itemReplace1}")
    private String itemReplace1;

    @Value("${itemReplace2}")
    private String itemReplace2;

    @Value("${sellerMarkingCode}")
    private String sellerMarkingCode;

    @Value("${clientMarkingCode}")
    private String clientMarkingCode;

    @Value("${saleMarkingCode}")
    private String saleMarkingCode;

    public String getInDirPath() {
        return inDirPath;
    }

    public void setInDirPath(String inDirPath) {
        this.inDirPath = inDirPath;
    }

    public String getOutDirPath() {
        return outDirPath;
    }

    public void setOutDirPath(String outDirPath) {
        this.outDirPath = outDirPath;
    }

    public String getFirstSplitSeparator() {
        return firstSplitSeparator;
    }

    public void setFirstSplitSeparator(String firstSplitSeparator) {
        this.firstSplitSeparator = firstSplitSeparator;
    }

    public String getSecondSplitSeparator() {
        return secondSplitSeparator;
    }

    public void setSecondSplitSeparator(String secondSplitSeparator) {
        this.secondSplitSeparator = secondSplitSeparator;
    }

    public String getThirdSplitSeparator() {
        return thirdSplitSeparator;
    }

    public void setThirdSplitSeparator(String thirdSplitSeparator) {
        this.thirdSplitSeparator = thirdSplitSeparator;
    }

    public String getItemReplace1() {
        return itemReplace1;
    }

    public void setItemReplace1(String itemReplace1) {
        this.itemReplace1 = itemReplace1;
    }

    public String getItemReplace2() {
        return itemReplace2;
    }

    public void setItemReplace2(String itemReplace2) {
        this.itemReplace2 = itemReplace2;
    }

    public String getSellerMarkingCode() {
        return sellerMarkingCode;
    }

    public void setSellerMarkingCode(String sellerMarkingCode) {
        this.sellerMarkingCode = sellerMarkingCode;
    }

    public String getClientMarkingCode() {
        return clientMarkingCode;
    }

    public void setClientMarkingCode(String clientMarkingCode) {
        this.clientMarkingCode = clientMarkingCode;
    }

    public String getSaleMarkingCode() {
        return saleMarkingCode;
    }

    public void setSaleMarkingCode(String saleMarkingCode) {
        this.saleMarkingCode = saleMarkingCode;
    }
}
