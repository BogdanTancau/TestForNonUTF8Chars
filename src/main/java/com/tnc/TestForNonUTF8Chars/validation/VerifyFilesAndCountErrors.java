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
        System.out.println(countNonUTF8Chars);
        System.out.println("\n---------------------------------------------------\n");
        moveFileToTheNextTest(countNonUTF8Chars);
    }

    private void moveFileToTheNextTest(ArrayList<String> countNonUTF8Chars) {
        if (countNonUTF8Chars.isEmpty()) {
            //move to the UTF8 directory
            System.out.println("This record is clean from the bad format");
        } else if (countNonUTF8Chars.isEmpty()){
            System.out.println();
            //-	UTF-8 and ASCII characters in the record -> send the file for processing
            // (the record goes to the “UTF-8” file and the comment goes to the LOG file with the
            // list of ASCII format characters as a note)
        }else {
            System.out.println();
            //-	Non-UTF-8/ASCII characters in the record -> the file does not go for processing
            // (the record goes to the LOG file along with the list of non-UTF-8 format characters in it)
        }
    }
}
