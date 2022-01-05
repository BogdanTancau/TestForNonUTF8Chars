package com.tnc.TestForNonUTF8Chars.validation;

import com.tnc.TestForNonUTF8Chars.validation.verifyForAscii.CheckFilesIfContainAscii;
import com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8.CheckFilesIfContainNonUtf8Chars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

import static com.tnc.TestForNonUTF8Chars.validation.verifyForAscii.CheckFilesIfContainAscii.*;

public class LogFiles {

    private final CheckFilesIfContainNonUtf8Chars checkFilesIfContainNonUtf8Chars = new CheckFilesIfContainNonUtf8Chars();
    private final CheckFilesIfContainAscii checkFilesIfContainAscii = new CheckFilesIfContainAscii();
    private final Logger logger = LoggerFactory.getLogger(LogFiles.class);
    private static Integer errorCounter;

    public void countErrorsIfExist(BufferedReader bufferedReader) throws IOException {
        String stringLine;
        while ((stringLine = bufferedReader.readLine()) != null) {
            if (isAsciiEncoded(stringLine)) {
                continue;
            } else if (checkFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes())) {
                continue;
            }
        }
        errorCounter = getBadCharsCounterAscii();

        if (checkFilesIfContainNonUtf8Chars.getBadCharsCounter() == 0 && getBadCharsCounterAscii() != 0) {
            logger.warn("""
                    UTF-8 and ASCII characters in the record. %s ASCII chars : %s
                    ---------------------------------------------------
                    """.formatted(errorCounter, getAsciiCollector()));
            //create a log file
            //-	UTF-8 and ASCII characters in the record -> send the file for processing
            // (the record goes to the “UTF-8” file and the comment goes to the LOG file with the
            // list of ASCII format characters as a note)
        } else if (checkFilesIfContainNonUtf8Chars.getBadCharsCounter() == 0) {
            logger.info("""
                     This record can be moved for processing.
                    ---------------------------------------------------
                    """);
            //move to the UTF8 directory
        } else {
            logger.error("""
                    You have  %s  foreign characters in the record: %s
                    ---------------------------------------------------"""
                    .formatted(errorCounter, getBadCharacterCollector()));
            //-	Non-UTF-8/ASCII characters in the record -> the file does not go for processing
            // (the record goes to the LOG file along with the list of non-UTF-8 format characters in it)
        }

        getBadCharacterCollector().clear();
        errorCounter = 0;
    }


//    public void countErrorsIfExist(BufferedReader bufferedReader) throws IOException {
//        String stringLine;
//        while ((stringLine = bufferedReader.readLine()) != null) {
//            if (isAsciiEncoded(stringLine)) {
//                continue;
//            }
//            if (checkFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes())) {
//                continue;
//            }
//        }
////            errorCounter = checkFilesIfContainNonUtf8Chars.getCharsCounter();
//        errorCounter = getBadCharsCounter();
//        moveRecordForProcessing();
//        getBadCharacterCollector().clear();
//        errorCounter = 0;
//    }

//    public void moveRecordForProcessing() {
//        if (checkFilesIfContainNonUtf8Chars.getCharsCounter() == 0) {
//            logger.info("""
//                     This record can be moved for processing.
//                    ---------------------------------------------------
//                    """);
//            //move to the UTF8 directory
//        }
//    }
//
//    public void errorMessage() {
//        if (errorCounter != 0) {
//            logger.error("""
//                    You have  %s  foreign characters in the record: %s
//                    ---------------------------------------------------"""
//                    .formatted(errorCounter, getBadCharacterCollector()));
//            //-	Non-UTF-8/ASCII characters in the record -> the file does not go for processing
//            // (the record goes to the LOG file along with the list of non-UTF-8 format characters in it)
//        }
//    }
//
//    public void warnMessage() {
//        if (getAsciiCharsCounter() != 0) {
//            logger.warn("""
//                    UTF-8 and ASCII characters in the record. %s ASCII chars : %s
//                    ---------------------------------------------------
//                    """.formatted(errorCounter, getAsciiCollector()));
//            //create a log file
//            //-	UTF-8 and ASCII characters in the record -> send the file for processing
//            // (the record goes to the “UTF-8” file and the comment goes to the LOG file with the
//            // list of ASCII format characters as a note)
//        }
//    }
}

