package com.gparis.consolesearch;

import com.gparis.consolesearch.search.SearchText;

public class ConsoleSearchApplication {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("You must provide ONE directory.");
        }
        SearchText.searchTextInDirectory(args[0]);
    }

}
