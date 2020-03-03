package com.gparis.consolesearch.file;

import java.util.List;

public class TextFiles {

    private List<FileContent> files;

    public List<FileContent> getTextFiles() {
        return files;
    }

    public void setFiles(List<FileContent> files) {
        this.files = files;
    }

    public int filesRead() {
        return files.size();
    }

}
