package com.tnc.TestForNonUTF8Chars.validation;

import com.tnc.TestForNonUTF8Chars.validation.verifyForAscii.CheckFilesIfContainAscii;
import com.tnc.TestForNonUTF8Chars.validation.verifyForUtf8.CheckFilesIfContainNonUtf8Chars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

import static com.tnc.TestForNonUTF8Chars.validation.verifyForAscii.CheckFilesIfContainAscii.*;

public class LogFiles {

    private final Logger logger = LoggerFactory.getLogger(LogFiles.class);
    private final CheckFilesIfContainNonUtf8Chars checkFilesIfContainNonUtf8Chars = new CheckFilesIfContainNonUtf8Chars();
    private final CheckFilesIfContainAscii checkFilesIfContainAscii = new CheckFilesIfContainAscii();
    public static Long badCharsCounter = 0L;

    public void countErrorsIfExist(BufferedReader bufferedReader) throws IOException {
        String stringLine;
        while ((stringLine = bufferedReader.readLine()) != null) {

            if (isAsciiEncoded(stringLine) && checkFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes())) {
//            if (StandardCharsets.US_ASCII.newEncoder().canEncode(stringLine) && StandardCharsets.UTF_8.newEncoder().canEncode(stringLine)) {
                logger.warn("""
                        UTF-8 and ASCII characters in the record. %s ASCII chars : %s
                        ---------------------------------------------------
                        """.formatted(badCharsCounter, getAsciiCollector()));
//          All language encodings use the same values as ASCII for their first 128 characters.
//            // create a log file
//            // (the record goes to the “UTF-8” file and for processing
//            //  the list of ASCII format goes to the LOG file
//            //v UTF-8 and ASCII characters in the record ->
//            //v list of ASCII format characters as a note)
            }
//            isAsciiEncoded(stringLine);
//            checkFilesIfContainNonUtf8Chars.checkFileIfContainsOnlyUTF8Chars(stringLine.getBytes());
        }

        if (badCharsCounter == 0) {
            logger.info("""
                     This record can be moved for processing.
                    ---------------------------------------------------
                    """);
            //move to the UTF8 directory
        } else {
            logger.error("""
                    You have  %s  foreign characters in the record: %s
                    ---------------------------------------------------"""
                    .formatted(badCharsCounter, getBadCharacterCollector()));
            //-	Non-UTF-8/ASCII characters in the record -> the file does not go for processing
            // (the record goes to the LOG file along with the list of non-UTF-8 format characters in it)
        }
        getBadCharacterCollector().clear();
        getAsciiCollector().clear();
        badCharsCounter = 0L;
    }
}

