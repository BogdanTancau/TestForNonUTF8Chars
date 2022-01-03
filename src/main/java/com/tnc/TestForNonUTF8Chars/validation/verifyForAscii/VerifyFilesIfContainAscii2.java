package com.tnc.TestForNonUTF8Chars.validation.verifyForAscii;

public class VerifyFilesIfContainAscii2 {

    static int count = 0;

    public static boolean isAsciiEncoded2(String filenameByte) {
        byte[] charsArray = filenameByte.getBytes();
        for (byte chars : charsArray) {
            int charInt = chars;
            System.out.println(charInt);
            if (charInt <= 127) {
                System.out.println("ASCII HERE");
                count++;
            }
        }
        if (count != 0) {
            System.out.println(count);
        }
        count = 0;
        return false;
    }
}
