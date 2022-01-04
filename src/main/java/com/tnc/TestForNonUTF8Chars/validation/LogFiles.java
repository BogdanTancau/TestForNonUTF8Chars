package com.tnc.TestForNonUTF8Chars.validation;

import com.tnc.TestForNonUTF8Chars.validation.verifyForAscii.CheckFilesIfContainAscii;
import com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8.CheckFilesIfContainNonUtf8Chars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class LogFiles {

    private final CheckFilesIfContainNonUtf8Chars checkFilesIfContainNonUtf8Chars = new CheckFilesIfContainNonUtf8Chars();
    private final CheckFilesIfContainAscii checkFilesIfContainAscii = new CheckFilesIfContainAscii();
    private final Logger logger = LoggerFactory.getLogger(LogFiles.class);
    private static Integer errorCounter = 0;
//    private static Integer totalErrorCounter = 0;

    public void countErrorsIfExist(BufferedReader bufferedReader) throws IOException {
        String stringLine;
        while ((stringLine = bufferedReader.readLine()) != null) {
            if (checkFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes())) {
                continue;
            } if (CheckFilesIfContainAscii.isAsciiEncoded(stringLine) && checkFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes())) {
                continue;
            }
            errorCounter = checkFilesIfContainNonUtf8Chars.getCharsCounter();
//            if (errorCounter == 0) {
//                continue;
//            } else {
////                totalErrorCounter += errorCounter;
//            }
        }
                moveRecordForProcessing();
//        errorCounter = 0;
//        totalErrorCounter = 0;
    }

//    public void moveRecordForProcessingWith() {
    public void moveRecordForProcessing() {
        if (errorCounter == 0) {
            logger.info("""
                    ---------------------------------------------------
                     This record can be moved for processing.
                    ---------------------------------------------------
                    """);
            //move to the UTF8 directory
        }
        else {
            CheckFilesIfContainAscii.loggerInfoForAscii();
        }

//        else if (errorCounter == 0 && totalErrorCounter != 0) {
//            logger.info("""
//                    ---------------------------------------------------
//                    You have %s non UTF 8 chars
//                    ---------------------------------------------------
//                    """.formatted(totalErrorCounter));
//            totalErrorCounter = 0;
        //-	UTF-8 and ASCII characters in the record -> send the file for processing
        // (the record goes to the “UTF-8” file and the comment goes to the LOG file with the
        // list of ASCII format characters as a note)
//        }

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
