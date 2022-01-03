package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

import java.util.ArrayList;

public class VerifyFilesIfContainAscii {

    boolean isAscii = false;
    static int count = 0;
    //    static String charsCollector;
    static ArrayList<String> charsCollector = new ArrayList<>();

    public static ArrayList<String> isAsciiEncoded(String fileNameBytes) {

        boolean isAscii;
        char[] charsArray = fileNameBytes.toCharArray();
        for (char chars : charsArray) {
//            int c = fileNameBytes.charAt(chars);
            if (chars < 0x7F) {
                ////
                int numberOfBytes = CountNumberOfBytes.countNbBytesPerChar(chars);
                System.out.println(numberOfBytes);
                if (numberOfBytes <= 7) {
//                    System.out.println("ASCII HERE");
                    isAscii = false;
                    charsCollector.add(String.valueOf(chars));
                    count++;
//                continue;
//                }
                }
            }
        }
        if (count != 0) {
            System.out.println(count);
            System.out.println(charsCollector);
        }
//        count = 0;
//        System.out.println("ASCII dsfsdgfsdgdfsgsdf");
        return charsCollector;

//        return false;
    }
}

