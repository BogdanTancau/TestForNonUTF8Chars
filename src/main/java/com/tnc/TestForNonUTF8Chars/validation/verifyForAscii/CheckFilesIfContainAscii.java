package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

//@Log4j2
public class CheckFilesIfContainAscii {
    private static final Logger logger = LogManager.getLogger(CheckFilesIfContainAscii.class);
    //    private static final Logger logger = LoggerFactory.getLogger(CheckFilesIfContainAscii.class);
    static int asciiCharsCounter;
    static int badCharsCounterAscii;
    static ArrayList<String> asciiCollector = new ArrayList<>();
    static ArrayList<String> badCharacterCollector = new ArrayList<>();

    public static boolean isAsciiEncoded(String fileNameBytes) {
//        badCharsCounterAscii = 0;
//        asciiCharsCounter = 0;
        char[] charsArray = fileNameBytes.toCharArray();
        for (char chars : charsArray) {
            int numberOfBytes = CountNumberOfBytes.countNbBytesPerChar(chars);
            if (chars < 0x7F || numberOfBytes == 1) {
                asciiCollector.add(String.valueOf(chars));
                asciiCharsCounter++;
//                return false;
            }
            else {
                badCharacterCollector.add(String.valueOf(chars));
                badCharsCounterAscii++;
//                return false;
            }
        }
        return false;
    }

//    public static void loggerInfoForAscii() {
//        if (badCharsCounter != 0) {
//            logger.error("""
//                    ---------------------------------------------------
//                    You have  %s  foreign characters in the record: %s
//                    ---------------------------------------------------"""
//                    .formatted(badCharsCounter, badCharacterCollector));
//            //-	Non-UTF-8/ASCII characters in the record -> the file does not go for processing
//            // (the record goes to the LOG file along with the list of non-UTF-8 format characters in it)
//
//        } else if (asciiCharsCounter != 0) {
//            logger.warn("""
//                    ---------------------------------------------------
//                    UTF-8 and ASCII characters in the record. %s ASCII chars : %s
//                    ---------------------------------------------------
//                    """.formatted(asciiCharsCounter, CheckFilesIfContainAscii.asciiCollector));
//            //create a log file
//            //-	UTF-8 and ASCII characters in the record -> send the file for processing
//            // (the record goes to the “UTF-8” file and the comment goes to the LOG file with the
//            // list of ASCII format characters as a note)
//        }
//    }

    public static int getAsciiCharsCounter() {
        return asciiCharsCounter;
    }

    public static int getBadCharsCounterAscii() {
        return badCharsCounterAscii;
    }

    public static ArrayList<String> getAsciiCollector() {
        return asciiCollector;
    }

    public static ArrayList<String> getBadCharacterCollector() {
        return badCharacterCollector;
    }
}

