package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CheckFilesIfContainAscii {
//    boolean isAscii = false;

    private static final Logger logger = LoggerFactory.getLogger(CheckFilesIfContainAscii.class);
    static int asciiCharsCounter = 0;
    static int badCharsCounter = 0;
    static final ArrayList<String> asciiCollector = new ArrayList<>();
    static final ArrayList<String> badCharacterCollector = new ArrayList<>();

    public static boolean isAsciiEncoded(String fileNameBytes) {
        char[] charsArray = fileNameBytes.toCharArray();
        for (char chars : charsArray) {
            int numberOfBytes = CountNumberOfBytes.countNbBytesPerChar(chars);
            if (chars < 0x7F && numberOfBytes == 1) {
                asciiCollector.add(String.valueOf(chars));
                asciiCharsCounter++;
            } else {
                badCharacterCollector.add(String.valueOf(chars));
                badCharsCounter++;
            }
        }
//        loggerInfoForAscii();
//        asciiCollector.clear();
//        badCharacterCollector.clear();
//        badCharsCounter = 0;
//        asciiCharsCounter = 0;
        return false;
    }

    public static void loggerInfoForAscii() {
        if (badCharsCounter != 0) {
            logger.info("""
                    ---------------------------------------------------
                    You have  %s  foreign characters: %s 
                    ---------------------------------------------------"""
                    .formatted(badCharsCounter, badCharacterCollector));

        } else if (asciiCharsCounter != 0) {
            logger.info("""
                    ---------------------------------------------------
                    UTF-8 and ASCII characters in the record %s ASCII chars : %s
                    ---------------------------------------------------
                    """.formatted(asciiCharsCounter, CheckFilesIfContainAscii.asciiCollector));
            //create a log file
        }
    }
}

