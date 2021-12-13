package com.tnc.TestForNonUTF8Chars;

import com.tnc.TestForNonUTF8Chars.file.ReadFileFromBucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TestForNonUtf8CharsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TestForNonUtf8CharsApplication.class, args);
		ReadFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/personal_test.txt");
		ReadFileFromBucket.readFile("C:/Users/a830083/Desktop/DDO/new_file_test.txt");
	}
}
