package com.southsystem.fileprocessor.dto;

import java.io.Serializable;
import java.util.List;

public class ProcessFileRequestDTO implements Serializable {

    List<String> lines;

    String path;

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
