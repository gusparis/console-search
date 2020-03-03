package com.gparis.consolesearch.search;

import com.gparis.consolesearch.file.FileContent;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearch implements Comparable<FileSearch> {

    private static final String BLANK = "\\b";
    private static final String BLANK_COMMA_DOT = "\\b|\\,|\\.";
    private static final String DELIMITER = " ";
    private FileContent file;
    private String line;
    private long matchCount;

    public FileSearch(FileContent file, String line) {
        this.file = file;
        this.line = line;
        this.matchCount = matchCount();
    }

    public FileContent getFile() {
        return file;
    }

    public String getLine() {
        return line;
    }

    public long getMatchCount() {
        return matchCount;
    }

    /**
     * Matches in the file content every word entered by the user, splitting it by a blank.
     *
     * @return
     */
    private long matchCount() {
        List<String> items = Arrays.asList(line.split(DELIMITER));
        return items.parallelStream().filter(item -> {
            String pattern = BLANK + item + BLANK_COMMA_DOT;
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(file.getContent());
            return m.find();
        }).count();
    }

    /**
     * Comparable override for Collections sorted method. Its sorted in descending
     * rank order.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(FileSearch o) {
        return (int) (o.matchCount - this.matchCount);
    }

}
