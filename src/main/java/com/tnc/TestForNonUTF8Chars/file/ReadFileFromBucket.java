package com.tnc.TestForNonUTF8Chars.file;

import com.tnc.TestForNonUTF8Chars.validation.LogFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileFromBucket {

    LogFiles logFiles = new LogFiles();

    public void readFile(String fileName) throws IOException {
        var bufferedReader = new BufferedReader(new FileReader(fileName));
        logFiles.countErrorsIfExist(bufferedReader);
    }
}
