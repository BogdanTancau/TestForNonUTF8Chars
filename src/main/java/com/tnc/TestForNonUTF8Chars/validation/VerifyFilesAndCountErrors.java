package com.tnc.TestForNonUTF8Chars.validation;

import com.tnc.TestForNonUTF8Chars.validation.verifyForAscii.VerifyFilesIfContainAscii;
import com.tnc.TestForNonUTF8Chars.validation.verifyForAscii.VerifyFilesIfContainAscii2;
import com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8.VerifyFilesIfContainNonUtf8Chars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class VerifyFilesAndCountErrors {

    private final VerifyFilesIfContainNonUtf8Chars verifyFilesIfContainNonUtf8Chars = new VerifyFilesIfContainNonUtf8Chars();
    private final VerifyFilesIfContainAscii verifyFilesIfContainAscii = new VerifyFilesIfContainAscii();
    private final Logger logger = LoggerFactory.getLogger(VerifyFilesAndCountErrors.class);

    //    private int lineNumber = 0;
    private static Integer errorCounter = 0;
    private static Integer totalErrorCounter = 0;

    public void countErrorsIfExist(BufferedReader bufferedReader) throws IOException {
//        ArrayList<String> countNonUTF8Chars = new ArrayList<>();
        String stringLine;
        while ((stringLine = bufferedReader.readLine()) != null) {
//            lineNumber++;
            verifyFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes());
            VerifyFilesIfContainAscii.isAsciiEncoded(stringLine);
//            VerifyFilesIfContainAscii2.isAsciiEncoded2(stringLine);
            errorCounter = verifyFilesIfContainNonUtf8Chars.getCharsCounter();
            if (errorCounter == 0) {
                continue;
            } else {
//                errorCounter += countVerifier;
                totalErrorCounter += errorCounter;
//                countNonUTF8Chars.add("\n You have " + errorCounter + " non UTF 8 chars " + " on line " + lineNumber + ": " + "\n" + lineNumber + " " + stringLine);
            }
        }
//        System.out.println(countNonUTF8Chars);
        moveFileToTheNextTest();
        errorCounter = 0;
        totalErrorCounter = 0;
    }

    public void moveFileToTheNextTest() {
        if (errorCounter == 0) {
            logger.info("""
                    ---------------------------------------------------
                     This record is clean from the bad format
                    ---------------------------------------------------
                    """);
            //move to the UTF8 directory
        } else {
            logger.info("""
                    ---------------------------------------------------
                    You have %s non UTF 8 chars
                    ---------------------------------------------------
                    """.formatted(totalErrorCounter));
            VerifyFilesIfContainAscii2.isAsciiEncoded2(stringLine);
//            totalErrorCounter = 0;
            //-	UTF-8 and ASCII characters in the record -> send the file for processing
            // (the record goes to the “UTF-8” file and the comment goes to the LOG file with the
            // list of ASCII format characters as a note)
        }

//        else if (errorCounter != 0) {
//            logger.info("\n---------------------------------------------------\n" +
//                    "\n You have " + totalErrorCounter + " non UTF 8 chars " +
//                    "\n---------------------------------------------------\n");
//            errorCounter = 0;
//            //-	Non-UTF-8/ASCII characters in the record -> the file does not go for processing
//            // (the record goes to the LOG file along with the list of non-UTF-8 format characters in it)
//        }
    }
}
