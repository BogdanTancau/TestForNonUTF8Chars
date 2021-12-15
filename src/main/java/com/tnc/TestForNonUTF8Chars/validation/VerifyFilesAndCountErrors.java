package com.tnc.TestForNonUTF8Chars.validation;

import com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8.VerifyFilesIfContainNonUtf8Chars;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class VerifyFilesAndCountErrors {

    private final VerifyFilesIfContainNonUtf8Chars verifyFilesIfContainNonUtf8Chars = new VerifyFilesIfContainNonUtf8Chars();

    private int lineNumber = 0;

    public void countErrorsIfExist(BufferedReader bufferedReader) throws IOException {
        ArrayList<String> countNonUTF8Chars = new ArrayList<>();
        String stringLine = "";
        while ((stringLine = bufferedReader.readLine()) != null) {
            lineNumber++;
            verifyFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes());
            int countVerifier = verifyFilesIfContainNonUtf8Chars.getCountChars();
            if (countVerifier == 0) {
                continue;
            } else {
                countNonUTF8Chars.add("\n You have " + countVerifier + " non UTF 8 chars " + " on line " + lineNumber + ": " + "\n" + lineNumber + " " + stringLine);
            }
        }
//        bufferedReader.close();
        System.out.println(countNonUTF8Chars);
        System.out.println("\n---------------------------------------------------\n");
        if (countNonUTF8Chars.isEmpty()) {
            //move to the UTF8 directory
        } else {
            //move to the next test
        }
    }

    public VerifyFilesIfContainNonUtf8Chars getVerifyFilesIfContainNonUtf8Chars() {
        return verifyFilesIfContainNonUtf8Chars;
    }
}
