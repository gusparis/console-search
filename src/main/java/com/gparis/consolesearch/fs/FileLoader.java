package com.gparis.consolesearch.fs;

import com.gparis.consolesearch.file.FileContent;
import com.gparis.consolesearch.file.TextFiles;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileLoader {

    /**
     * File filter for txt extension files only.
     */
    private static FilenameFilter textFilter;

    static {
        textFilter = (dir, name) -> name.toLowerCase().endsWith(".txt");
    }

    /**
     * Loads all text files in memory from given directory. Throws NotDirectoryException
     * if given path is not a directory.
     *
     * @param path
     * @return
     * @throws NotDirectoryException
     */
    public static TextFiles getDirectoryTextFiles(String path) throws NotDirectoryException {
        File directory = new File(path);
        if (!directory.isDirectory()) {
            throw new NotDirectoryException("Not a directory");
        }
        List<File> files = Arrays.asList(Optional.ofNullable(directory.listFiles(textFilter)).orElse(new File[0]));
        TextFiles textFiles = new TextFiles();
        textFiles.setFiles(files
                .parallelStream()
                .map(FileLoader::getContent)
                .collect(Collectors.toList())
        );
        return textFiles;
    }

    /**
     * Gets the content of every file in the directory.
     *
     * @param file
     * @return
     */
    private static FileContent getContent(File file) {
        try {
            return new FileContent(file.getName(), new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8));
        } catch (IOException e) {
            return new FileContent();
        }
    }

}
