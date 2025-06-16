package telran.regex.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static telran.regex.model.PhotoSelector.selectPictures;

public class PhotoSelectorTest {
    private static final String FILE_DATA = """
            "Paris\\20140101_090000.jpg",
            "Paris\\20140201_121005.jpg",				
            "Paris\\20150301_211035.jpg",				
            "Paris\\20150401_110023.gif",
            "Paris\\20150401_181705.jpg",				
            "Paris\\20150501_231035.gif",				
            "London\\20140205_090000.jpg",
            "London\\20140205_121005.jpg",				
            "London\\20140601_211035.gif",				
            "London\\20151001_110023.jpg",
            "London\\20151001_121705.jpg",				
            "London\\20151001_231035.jpg",
            "Chicago\\20150301_120001.png",
            "Chicago\\20151111_232000.png
            """;


    // TODO StringBuilder (method: append and toString().split)
    // TODO * -> Pattern and Matcher (reset find)


    @Test
    void testAllEuropePictures(){
        String regex = "(Paris|London)\\\\[^\\s,\"]+";
        String actual = selectPictures(FILE_DATA, regex);
        String expected = """
                "Paris\\20140101_090000.jpg",
                "Paris\\20140201_121005.jpg",
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_110023.gif",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20140205_090000.jpg",
                "London\\20140205_121005.jpg",
                "London\\20140601_211035.gif",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg\"""";
        assertEquals(expected, actual);
    }

    @Test
    void testAutumnPictures() {
        String regex = "([A-Za-z]+\\\\\\d{4}(09|10|11)\\d{2}_\\d{6}\\.(jpg|png|gif))";
        String actual = selectPictures(FILE_DATA, regex);
        String expected = """
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png\"""";
        assertEquals(expected, actual);
    }

    @Test
    void testPhotosByYear() {
        String regex = "([A-Za-z]+\\\\2015\\d{4}_\\d{6}\\.(jpg|png|gif))";
        String actual = selectPictures(FILE_DATA, regex);
        String expected = """
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_110023.gif",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20150301_120001.png",
                "Chicago\\20151111_232000.png\"""";
        assertEquals(expected, actual);
    }

    @Test
    void testNightPictures() {
        String regex = "([A-Za-z]+\\\\\\d{8}_(1[89]|2[0-3])\\d{4}\\.(jpg|png|gif))";
        String actual = selectPictures(FILE_DATA, regex);
        String expected = """
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20140601_211035.gif",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png\"""";
        assertEquals(expected, actual);
    }

    @Test
    void testNightChicagoPictures(){

        
    }
}