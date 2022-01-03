package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class VerifyFilesIfContainAscii {
//    boolean isAscii = false;

    private static final Logger logger = LoggerFactory.getLogger(VerifyFilesIfContainAscii.class);
    static int count = 0;
    static int badCharsCounter = 0;
    static final ArrayList<String> asciiCollector = new ArrayList<>();
    static final ArrayList<String> badCharacterCollector = new ArrayList<>();

    public static void isAsciiEncoded(String fileNameBytes) {

//        boolean isAscii;
        char[] charsArray = fileNameBytes.toCharArray();
        for (char chars : charsArray) {
//            int c = fileNameBytes.charAt(chars);
            int numberOfBytes = CountNumberOfBytes.countNbBytesPerChar(chars);
            if (chars < 0x7F && numberOfBytes == 1) {
                asciiCollector.add(String.valueOf(chars));
//                System.out.println("ASCII HERE");
//                System.out.println(numberOfBytes);
                count++;
                ////
//                if (numberOfBytes <= 7) {
//                isAscii = false;
            } else {
                badCharacterCollector.add(String.valueOf(chars));
                badCharsCounter++;
            }
//            }
        }
        if (count != 0) {
            logger.info("""
                    ---------------------------------------------------
                    You have %s ASCII chars : %s
                    ---------------------------------------------------
                    """.formatted(count, asciiCollector));
        } if (badCharsCounter != 0) {
            logger.info("""
                    ---------------------------------------------------
                    You have  %s  foreign characters: %s 
                    ---------------------------------------------------"""
                    .formatted(badCharsCounter, badCharacterCollector));
        }


//        return false;
        asciiCollector.clear();
        badCharacterCollector.clear();
        badCharsCounter = 0;
        count = 0;
    }
}

