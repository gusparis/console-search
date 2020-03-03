package com.gparis.consolesearch.search;

import com.gparis.consolesearch.file.FileContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(PowerMockRunner.class)
public class FileSearchTest {

    @Test
    public void testNotMatching() {
        FileContent fc = new FileContent("Test.txt", "no match");
        FileSearch fs = new FileSearch(fc, "not");

        assertThat(fs.getMatchCount(), is(0L));
    }

    @Test
    public void testMatchingWords() {
        FileContent fc = new FileContent("Test.txt", "match two words");
        FileSearch fs = new FileSearch(fc, "match words");

        assertThat(fs.getMatchCount(), is(2L));
    }

    @Test
    public void testCompareMatching() {
        FileContent fc1 = new FileContent("Test.txt", "no match");
        FileContent fc2 = new FileContent("Test.txt", "match two words");

        FileSearch fs1 = new FileSearch(fc1, "not");
        FileSearch fs2 = new FileSearch(fc2, "match words");

        assertThat(fs1.compareTo(fs2), is(2));
    }

    @Test
    public void testCompareMatchingWithCommaAndDot() {
        FileContent fc = new FileContent("Test.txt", "match, two words.");
        FileSearch fs = new FileSearch(fc, "match words");

        assertThat(fs.getMatchCount(), is(2L));
    }

    @Test
    public void testCompareMatchingLotsOfBlanks() {
        FileContent fc = new FileContent("Test.txt", "            match                  two");
        FileSearch fs = new FileSearch(fc, "match two");

        assertThat(fs.getMatchCount(), is(2L));
    }

}
