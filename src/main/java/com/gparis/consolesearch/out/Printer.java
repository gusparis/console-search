package com.gparis.consolesearch.out;

import com.gparis.consolesearch.search.FileSearch;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Printer {

    private static final String SEARCH = "search> ";
    private static final String NO_MATCHES = "no matches found";
    private static final String DELIMITER = " ";
    private static final String PERCENTAGE = " %";
    private static final String COLON = " : ";

    public static void filesRead(int filesRead, String directory) {
        System.out.format("%d files read in directory %s\n", filesRead, directory);
    }

    public static void search() {
        System.out.print(SEARCH);
    }

    public static void print(Supplier<Stream<FileSearch>> files) {
        if (files.get().noneMatch(file -> file.getMatchCount() != 0)) {
            System.out.println(NO_MATCHES);
            return;
        }
        files.get().forEach(Printer::print);
    }

    /**
     * Prints every file with its corresponding matching percentage.
     *
     * @param fileSearch
     */
    static void print(FileSearch fileSearch) {
        System.out.print(fileSearch.getFile().getFileName() + COLON);
        System.out.print(fileSearch.getMatchCount() * 100 / fileSearch.getLine().split(DELIMITER).length);
        System.out.println(PERCENTAGE);
    }

}
