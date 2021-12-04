package Day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartA {
    private int nOfLines = 376;
    private BufferedReader reader;
    private String[] array = new String[nOfLines];

    public static void main(String[] args) {
        PartA part = new PartA();
        part.setup();
        part.solution();
    }

    public void setup() {
        try {
            reader = new BufferedReader(new FileReader("src/Day18/input.txt"));
            for (int i = 0; i < nOfLines; i++) {
                array[i] = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void solution() {
        long sum = 0;
        for (int i = 0; i < nOfLines; i++) {
            String line = array[i];
            boolean run = true;
            while (run) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.exit(1);
                }

                System.out.println(line);
                if (line.contains("(")) {
                    String[] split =line.split("[(]");
                    for (int j = 0; j < split.length; j++) {
                        if (split[j].contains(")")) {
                            String newLine = "";
                            if (line.charAt(0) == '(') {
                                for (int k = 1; k < line.length(); k++) {
                                    if (line.charAt(k) == '(') {
                                        newLine = "(";
                                        break;
                                    } else if (line.charAt(k) == ')') {
                                        break;
                                    }
                                }
                            }
                            line = newLine;
                            for (int k = 0; k < j; k++) {
                                line = line + split[k];
                                System.out.println("1: " + split[k]);
                                System.out.println("2: " + split[k + 1]);
                                if (k!= 0 && (!split[k + 1].contains(")"))) {
                                    line = line + "(";
                                }
                                System.out.println("3: " + line);
                            }
                            String subline = split[j].split("[)]")[0];
                            while (subline.contains("*") || subline.contains("+")) {
                                subline = calculate(subline);
                            }
                            line = line + subline;
                            for (int k = j; k < split.length; k++) {
                                if (k == j) {
                                    line = line + split[k].substring(split[k].indexOf(")") + 1);
                                } else {
                                    line = line + "(" + split[k];
                                }
                            }
                            break;
                        }
                    }
                } else if (line.contains("*") || line.contains("+")) {
                    line = calculate(line);
                } else {
                    sum += Long.parseLong(line);
                    run = false;
                }
            }

        }

        System.out.println(sum);
    }

    public String calculate(String line) {
        boolean twoNumbers = false;
        String[] split = line.split(" ");
        long number = -1;
        if (split.length == 3) {
            twoNumbers = true;
        }
        if (split[1].equals("+")) {
            number = Long.parseLong(split[0]) + Long.parseLong(split[2]);
        } else {
            number = Long.parseLong(split[0]) * Long.parseLong(split[2]);
        }
        int spaceCounter = 0;
        if (number != -1) {
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == ' ') {
                    spaceCounter++;
                    if (spaceCounter == 3) {
                        return String.valueOf(number) + line.substring(j);
                    } else if (twoNumbers) {
                        return String.valueOf(number);
                    }
                }
            }
        } else {
            System.out.println("HELP2");
            System.exit(1);
        }
        return null;
    }
}
