package ru.sedash.lightspeed;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IpParser {

        public static void main(String[] args) throws IOException {
            String filePath = "./src/main/resources/doc2.txt";

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            long distinct = reader.lines().distinct().count();
            System.out.println(distinct);
        }
}
