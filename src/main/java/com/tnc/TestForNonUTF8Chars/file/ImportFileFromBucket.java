package com.tnc.TestForNonUTF8Chars.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportFileFromBucket {

    private static int lineNumber = 0;
    private static int countChars = 0;


    public static void readFile(String fileName) throws IOException {
        var bufferedReader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> countArrayChars = new ArrayList<>();
        String stringLine = "";
        while ((stringLine = bufferedReader.readLine()) != null) {
            lineNumber++;
            validUTF8(stringLine.getBytes());
            if (countChars == 0) {
                continue;
            }
            countArrayChars.add("\n You have " + countChars + " non UTF 8 chars " + " on line " + lineNumber + ": " + "\n" + lineNumber + " " + stringLine);
            countChars = 0;
        }
        bufferedReader.close();
        System.out.println(countArrayChars);
    }

    public static boolean validUTF8(byte[] fileNameBytes) {
        int i = 0;
        // Check for BOM
        if (fileNameBytes.length >= 3 && (fileNameBytes[0] & 0xFF) == 0xEF
                && (fileNameBytes[1] & 0xFF) == 0xBB & (fileNameBytes[2] & 0xFF) == 0xBF) {
            i = 3;
        }

        int end;
        int countNonUTFChar = 0;
        for (int j = fileNameBytes.length; i < j; ++i) {
            int octet = fileNameBytes[i];
            if ((octet & 0x80) == 0) {
                continue; // ASCII
            }

            // Check for UTF-8 leading byte
            if ((octet & 0xE0) == 0xC0) {
                end = i + 1;
            } else if ((octet & 0xF0) == 0xE0) {
                end = i + 2;
            } else if ((octet & 0xF8) == 0xF0) {
                end = i + 3;
            } else {
                // Java only supports BMP so 3 is max
                return false;
            }

            while (i < end) {
                i++;
                octet = fileNameBytes[i];
                if ((octet & 0xC0) != 0x80) {
                    return false;
                }
            }
            countNonUTFChar++;
            countChars = countNonUTFChar;
        }
        return true;
    }
}
