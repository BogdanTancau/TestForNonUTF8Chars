package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

import java.util.ArrayList;

import static com.tnc.TestForNonUTF8Chars.validation.LogFiles.badCharsCounter;

public class CheckFilesIfContainAscii {
    static ArrayList<String> asciiCollector = new ArrayList<>();
    static ArrayList<String> badCharacterCollector = new ArrayList<>();

    public static boolean isAsciiEncoded(String fileNameBytes) {
        char[] charsArray = fileNameBytes.toCharArray();
        for (char chars : charsArray) {
            if (chars < 0x7F && chars > 0) {
                asciiCollector.add(String.valueOf(chars));
            } else {
                badCharacterCollector.add(String.valueOf(chars));
                badCharsCounter++;
            }
        }
        return false;
    }

    public static ArrayList<String> getAsciiCollector() {
        return asciiCollector;
    }

    public static ArrayList<String> getBadCharacterCollector() {
        return badCharacterCollector;
    }
}

