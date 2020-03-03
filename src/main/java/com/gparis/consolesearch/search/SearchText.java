package com.gparis.consolesearch.search;

import com.gparis.consolesearch.file.TextFiles;
import com.gparis.consolesearch.fs.FileLoader;
import com.gparis.consolesearch.out.Printer;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SearchText {

    public static void searchTextInDirectory(String directory) {
        Scanner keyboard = new Scanner(System.in);
        try {
            TextFiles files = FileLoader.getDirectoryTextFiles(directory);
            Printer.filesRead(files.filesRead(), directory);
            if (files.filesRead() == 0) {
                throw new FileNotFoundException();
            }
            while (true) {
                Printer.search();
                final String line = keyboard.nextLine();
                if (":quit".equals(line) || ":q".equals(line)) throw new InterruptedException();

                if (!line.isEmpty()) {
                    Supplier<Stream<FileSearch>> streamSupplier = () -> files.getTextFiles().stream()
                            .map(file -> new FileSearch(file, line))
                            .sorted()
                            .limit(10);
                    Printer.print(streamSupplier);
                }
                keyboard = new Scanner(System.in);
            }
        } catch (Exception e) {
            System.exit(0);
        } finally {
            keyboard.close();
        }
    }

}
