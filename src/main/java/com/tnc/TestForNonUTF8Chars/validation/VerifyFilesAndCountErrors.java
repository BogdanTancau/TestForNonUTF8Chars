package com.tnc.TestForNonUTF8Chars.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8.VerifyFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars;
import static com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8.VerifyFilesIfContainNonUtf8Chars.countChars;

public class VerifyFilesAndCountErrors {

    private static int lineNumber = 0;

    public static void countErrorsIfExist(BufferedReader bufferedReader) throws IOException {
        ArrayList<String> countNonUTF8Chars = new ArrayList<>();
        String stringLine = "";
        while ((stringLine = bufferedReader.readLine()) != null) {
            lineNumber++;
            checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes());
            if (countChars == 0) {
                continue;
            } else {
                countNonUTF8Chars.add("\n You have " + countChars + " non UTF 8 chars " + " on line " + lineNumber + ": " + "\n" + lineNumber + " " + stringLine);
                countChars = 0;
            }
        }
//        bufferedReader.close();
        System.out.println(countNonUTF8Chars);
        if (countNonUTF8Chars.isEmpty()) {
            //move to the UTF8 directory
        } else {
            //move to the next test
        }
    }
}
