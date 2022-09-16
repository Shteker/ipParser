package ru.sedash.lightspeed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IpParser {

        public static void main(String[] args) throws IOException {
            String filePath = "./src/main/resources/doc2.txt";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                long distinct = bufferedReader.lines().distinct().count();
                System.out.println(distinct);
            } finally {
                bufferedReader.close();
                fileReader.close();
            }
        }
}
