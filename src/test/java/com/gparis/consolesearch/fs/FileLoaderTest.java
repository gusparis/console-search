package com.gparis.consolesearch.fs;

import com.gparis.consolesearch.file.TextFiles;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileLoader.class, Files.class, Paths.class})
public class FileLoaderTest {

    @Mock
    private File directory;

    @Before
    public void setUp() {
        mockStatic(Files.class);
        mockStatic(Paths.class);
    }

    @Test(expected = NotDirectoryException.class)
    public void testNoValidDirectory() throws NotDirectoryException {
        FileLoader.getDirectoryTextFiles("/NoValid");
    }

    @Test
    public void testZeroTextFiles() throws Exception {
        whenNew(File.class).withArguments(anyString()).thenReturn(directory);
        when(directory.isDirectory()).thenReturn(true);

        TextFiles txt = FileLoader.getDirectoryTextFiles("/MockPath");

        assertThat(txt.filesRead(), is(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWrongPathFile() throws Exception {
        File f = mock(File.class);
        File[] files = new File[1];
        files[0] = f;

        whenNew(File.class).withArguments(anyString()).thenReturn(directory);
        when(directory.isDirectory()).thenReturn(true);
        when(directory.listFiles(any(FilenameFilter.class))).thenReturn(files);

        when(f.getName()).thenReturn("TestFile.txt");
        when(Files.readAllBytes(any())).thenThrow(IOException.class);

        FileLoader.getDirectoryTextFiles("/WrongPath");
    }

    @Test
    public void testTextFiles() throws Exception {
        File f = mock(File.class);
        File[] files = new File[1];
        files[0] = f;

        whenNew(File.class).withArguments(anyString()).thenReturn(directory);
        when(directory.isDirectory()).thenReturn(true);
        when(directory.listFiles(any(FilenameFilter.class))).thenReturn(files);

        when(f.getName()).thenReturn("TestFile.txt");
        when(Files.readAllBytes(any())).thenReturn("hello test".getBytes());

        TextFiles txt = FileLoader.getDirectoryTextFiles("/MockPath");

        assertThat(txt.getTextFiles(), hasItem(allOf(
                hasProperty("fileName", is("TestFile.txt")),
                hasProperty("content", is("hello test"))
        )));
    }

}
