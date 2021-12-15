package com.tnc.TestForNonUTF8Chars.file;

import com.tnc.TestForNonUTF8Chars.validation.VerifyFilesAndCountErrors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileFromBucket {

    VerifyFilesAndCountErrors verifyFilesAndCountErrors = new VerifyFilesAndCountErrors();

    public void readFile(String fileName) throws IOException {
        var bufferedReader = new BufferedReader(new FileReader(fileName));
        verifyFilesAndCountErrors.countErrorsIfExist(bufferedReader);
    }
}
