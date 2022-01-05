package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

import java.util.ArrayList;

public class CheckFilesIfContainAscii {
    static int asciiCharsCounter;
    static int badCharsCounterAscii;
    static ArrayList<String> asciiCollector = new ArrayList<>();
    static ArrayList<String> badCharacterCollector = new ArrayList<>();

    public static boolean isAsciiEncoded(String fileNameBytes) {
        char[] charsArray = fileNameBytes.toCharArray();
        for (char chars : charsArray) {
            int numberOfBytes = CountNumberOfBytes.countNbBytesPerChar(chars);
            if (chars < 0x7F || numberOfBytes == 1) {
                asciiCollector.add(String.valueOf(chars));
                asciiCharsCounter++;
            } else {
                badCharacterCollector.add(String.valueOf(chars));
                badCharsCounterAscii++;
            }
        }
        return false;
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

