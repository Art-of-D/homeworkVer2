package WordsCalc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import WordsCalc.WordCalculator;

public class WordTester {
    public static void main(String[] args) {
        File file = new File("/Users/art_of_d/Java/homeworkVer2/src/main/java/mod10/WordsCalc/words.txt");
        ArrayList<Character> chars = new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            int i = -1;
            while ((i = reader.read()) != -1) {
                chars.add((char) i);
            }
        } catch (
                IOException e) {
            System.out.println(e.getMessage());
        }
        WordCalculator wordCalculator = new WordCalculator(chars);

    }
}

