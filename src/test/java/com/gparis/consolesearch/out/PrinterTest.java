package com.gparis.consolesearch.out;

import com.gparis.consolesearch.file.FileContent;
import com.gparis.consolesearch.search.FileSearch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(PowerMockRunner.class)
public class PrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testNoMatching() {
        FileContent fc = new FileContent("Test.txt", "no match");
        FileSearch fs = new FileSearch(fc, "not");

        Supplier<Stream<FileSearch>> fileSearchSupplier = () -> Stream.of(fs);

        Printer.print(fileSearchSupplier);

        assertThat(outContent.toString(), is("no matches found\n"));
    }

    @Test
    public void testMatching() {
        FileContent fc = new FileContent("Test.txt", "not match");
        FileSearch fs = new FileSearch(fc, "not");

        Supplier<Stream<FileSearch>> fileSearchSupplier = () -> Stream.of(fs);

        Printer.print(fileSearchSupplier);

        assertThat(outContent.toString(), is("Test.txt : 100 %\n"));
    }

    @Test
    public void testOutput() {
        FileContent fc = new FileContent("Test.txt", "match two words");
        FileSearch fs = new FileSearch(fc, "match words");

        Printer.print(fs);

        assertThat(outContent.toString(), is("Test.txt : 100 %\n"));
    }

}
