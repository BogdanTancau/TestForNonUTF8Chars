package com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8;

public class VerifyFilesIfContainNonUtf8Chars {

    private int countChars;

    public boolean checkFileIfContainsOnlyUTF8Chars(byte[] fileNameBytes) {
        countChars = 0;
        int i = 0;
        // Check for BOM - byte order mark
        if (fileNameBytes.length >= 3 && (fileNameBytes[0] & 0xFF) == 0xEF
                && (fileNameBytes[1] & 0xFF) == 0xBB & (fileNameBytes[2] & 0xFF) == 0xBF) {
            i = 3;
        }
        int end;
        for (int j = fileNameBytes.length; i < j; ++i) {
            int octet = fileNameBytes[i];
            if ((octet & 0x80) == 0) {
                continue;
            }

            // Check for UTF-8 leading byte
            if ((octet & 0xE0) == 0xC0) {
                end = i + 1;
            } else if ((octet & 0xF0) == 0xE0) {
                end = i + 2;
            } else if ((octet & 0xF8) == 0xF0) {
                end = i + 3;
            } else {
                return false;
            }
            while (i < end) {
                i++;
                octet = fileNameBytes[i];
                if ((octet & 0xC0) != 0x80) {
                    return false;
                }
            }
            countChars++;
        }
        return true;
    }

    public int getCountChars() {
        return countChars;
    }
}
