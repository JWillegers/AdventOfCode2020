package A_normal_day;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part {
    public static void main(String[] args) {
        int nOfLines = 875;
        BufferedReader reader;
        String[] array = new String[nOfLines];

        try {
            reader = new BufferedReader(new FileReader("src/Day5/input.txt"));
            for (int i = 0; i < nOfLines; i++) {
                array[i] = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (int i = 0; i < nOfLines; i++) {

        }

        System.out.println();
    }
}
