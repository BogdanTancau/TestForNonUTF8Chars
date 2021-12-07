package com.tnc.TestForNonUTF8Chars;

import com.tnc.TestForNonUTF8Chars.file.ImportFileFromBucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TestForNonUtf8CharsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TestForNonUtf8CharsApplication.class, args);
		ImportFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/personal_test.txt");
		ImportFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/Test2.txt");
//		ImportFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/DocsFromMila/TestApp/SAL_Location_fuer_SIS_ATOS_v11_200.txt");


		//read from directory
//		ImportFileFromBucket.readDirectory("C:/Users/a830083/Desktop/DDO/ForTesting");
	}
}
