package com.tnc.TestForNonUTF8Chars;

import com.tnc.TestForNonUTF8Chars.file.ReadFileFromBucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TestForNonUtf8CharsApplication {

    public static void main(String[] args) throws IOException {
        ReadFileFromBucket readFileFromBucket = new ReadFileFromBucket();

        SpringApplication.run(TestForNonUtf8CharsApplication.class, args);
//		readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/personal_test.txt");
//		readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/non_ascii.txt");
//        readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/new_file_test.txt");
//		readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/ascii_test.txt");
//		readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/DocsFromMila/Mail_1_Sal_Location/SAL_Location_fuer_SIS_ATOS.txt");
//		readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/ascii.txt");
		readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/unicod.txt");
//		readFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/TestForNonUTF8Chars/src/main/resources/testutf.txt");
    }
}
