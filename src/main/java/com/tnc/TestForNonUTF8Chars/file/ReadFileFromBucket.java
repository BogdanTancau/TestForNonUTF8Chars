package com.tnc.TestForNonUTF8Chars.file;

import com.tnc.TestForNonUTF8Chars.validation.VerifyFilesAndCountErrors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileFromBucket {

    public static void readFile(String fileName) throws IOException {
        var bufferedReader = new BufferedReader(new FileReader(fileName));
        VerifyFilesAndCountErrors.countErrorsIfExist(bufferedReader);
    }
}
