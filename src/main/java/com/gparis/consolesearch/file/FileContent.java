package com.gparis.consolesearch.file;

public class FileContent {

    private String fileName;
    private String content;

    public FileContent() {
    }

    public FileContent(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

}
