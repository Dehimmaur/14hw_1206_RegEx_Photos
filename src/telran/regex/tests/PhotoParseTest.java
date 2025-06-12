package telran.regex.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.regex.model.PhotoParse;

class PhotoParseTest {
    PhotoParse p;
    String FILE_DATA = """
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
            "Chicago\\20151111_232000.png"
            """;

    @BeforeEach
    void setUp() {
        p = new PhotoParse();
    }

    @Test
    void testAllEuropePictures() {
        String[] res = {"Paris\\20140101_090000.jpg",
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
                "London\\20151001_231035.jpg"
        };
        assertArrayEquals(res, p.getEuropPlaces(FILE_DATA));
    }

    @Test
    void testAutumnPictures() {
        String[] res = {"London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"
        };
        assertArrayEquals(res, p.getPicturesBySeason(FILE_DATA, "Autumn"));
    }

    @Test
    void testPhotosByYear() {
        String[] res = {"Paris\\20150301_211035.jpg",
                "Paris\\20150401_110023.gif",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20150301_120001.png",
                "Chicago\\20151111_232000.png"
        };
        assertArrayEquals(res, p.getPhotosByYear(FILE_DATA, 2015));
        String[] res2 = {"Paris\\20140101_090000.jpg",
                "Paris\\20140201_121005.jpg",
                "London\\20140205_090000.jpg",
                "London\\20140205_121005.jpg",
                "London\\20140601_211035.gif"
        };
        assertArrayEquals(res2, p.getPhotosByYear(FILE_DATA, 2014));
    }

    @Test
    void testNightPictures(){
        String[] res = {"Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "Paris\\20150501_231035.gif",
                "London\\20140601_211035.gif",
                "London\\20151001_231035.jpg",
                "Chicago\\20151111_232000.png"
        };
        assertArrayEquals(res, p.getNightPictures(FILE_DATA));
    }

    @Test
    void testNightChicagoPictures(){
        String[] res = {"Chicago\\20151111_232000.png"};
        assertArrayEquals(res, p.getNightPictures(FILE_DATA,"Chicago"));
        assertArrayEquals(res, p.getNightPictures(FILE_DATA,"chicago"));
    }

    @Test
    void testByType(){
        String[] res = {"Chicago\\20150301_120001.png", "Chicago\\20151111_232000.png"};
        assertArrayEquals(res, p.getPhotosByType(FILE_DATA, "png"));
        String[] res2 = {"Paris\\20140101_090000.jpg",
                "Paris\\20140201_121005.jpg",
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "London\\20140205_090000.jpg",
                "London\\20140205_121005.jpg",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg"
        };
        assertArrayEquals(res2, p.getPhotosByType(FILE_DATA, "jpg"));
        String[] res3 = {"Paris\\20140101_090000.jpg",
                "Paris\\20140201_121005.jpg",
                "Paris\\20150301_211035.jpg",
                "Paris\\20150401_181705.jpg",
                "London\\20140205_090000.jpg",
                "London\\20140205_121005.jpg",
                "London\\20151001_110023.jpg",
                "London\\20151001_121705.jpg",
                "London\\20151001_231035.jpg",
                "Chicago\\20150301_120001.png",
                "Chicago\\20151111_232000.png"
        };
        assertArrayEquals(res3,p.getPhotosByType(FILE_DATA, "jpg", "png"));
    }
}

