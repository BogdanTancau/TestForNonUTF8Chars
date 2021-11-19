package com.tnc.TestForNonUTF8Chars.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ImportFileFromBucket {

    private static int lineNumber = 0;
    private boolean appPassed;
    private static int countNonUTFChar = 0;

    public static void readFile(String fileName) throws IOException {
        String strLine = "";
        var bufferedReader = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));

        while ((strLine = bufferedReader.readLine()) != null) {
            lineNumber++;
            validUTF8(strLine.getBytes(StandardCharsets.UTF_8));
            System.out.println(lineNumber + " " + strLine);
        }
        bufferedReader.close();
        testForUtf();
    }

    public static boolean validUTF8(byte[] fileNameBytes) {
        int i = 0;
        // Check for BOM
        if (fileNameBytes.length >= 3 && (fileNameBytes[0] & 0xFF) == 0xEF
                && (fileNameBytes[1] & 0xFF) == 0xBB & (fileNameBytes[2] & 0xFF) == 0xBF) {
            i = 3;
        }

        int end;
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
                    // Not a valid trailing byte
                    return false;
                }
            }
            countNonUTFChar++;
//            System.out.println("\nEXCEPTION///////////////////////////////////////" + " " + " on line " + lineNumber + "\n");
        }
        System.out.println("\n You have " + countNonUTFChar + " " + " on line " + lineNumber + "\n");
        return true;
    }

    public static boolean testForUtf() {
        if (countNonUTFChar != 0) {
            System.out.println("Wrong file format");
            return false;
        }
        System.out.println("This file is UTF 8.");
        return true;
    }
}
