package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PartB {
    private int nOfLines = 565;
    private BufferedReader reader;
    private String[] array = new String[nOfLines];

    public static void main(String[] args) {
        PartB part = new PartB();
        part.setup();
        part.solution();
    }

    public void setup() {
        try {
            reader = new BufferedReader(new FileReader("src/Day14/input.txt"));
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

        int highest = 0;
        for (int i = 0; i < nOfLines; i++) {
            try {
                int number = Integer.parseInt(array[i].split("]")[0].substring(4));
                if (number > highest) {
                    highest = number;
                }
                if (number < 0) {
                    System.out.println("OVERFLOW");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }

        if (highest == 0) {
            System.exit(1);
        }

        long[] memory = new long[highest + 1];
        String mask = null;

        for (int i = 0; i < nOfLines; i++) {
            try {
                String[] split = array[i].split(" = ");
                if (split[0].equals("mask")) {
                    mask = split[1];
                } else {
                    long value = Long.parseLong(split[1]);
                    int memorySlot = Integer.parseInt(array[i].split("]")[0].substring(4));
                    for (int j = 0; j < 36; j++) {
                        char maskChar = mask.charAt(35 - j);
                        if (maskChar != 'X') {
                            int shifted = memorySlot >> j;
                            if (shifted % 2 != Integer.parseInt(String.valueOf(maskChar))) { //bits are different
                                if (shifted % 2 == 0) { //the 0 needs to become a 1
                                    memorySlot += Math.pow(2, j);
                                } else { //the 1 needs to become a 0
                                    memorySlot -= Math.pow(2 , j);
                                }
                            }
                        }
                    }

                    List<Long> oldList = new ArrayList<>();
                    List<Long> newList = new ArrayList<>();
                    for (int j = 0; j < 36; j++) {
                        char maskChar = mask.charAt(35 - j);
                        if (maskChar == 'X') {
                            if (oldList.size() > 0) {
                                for (Long number : oldList) {
                                    newList.add((long) Math.pow(2, j) + number);
                                }
                            } else {
                                newList.add((long) Math.pow(2, j));
                                newList.add((long) 0);
                            }
                            oldList = newList;
                        }
                    }


                    memory[memorySlot] = value;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        long sum = 0;
        for (int i = 0; i < memory.length; i++) {
            sum += memory[i];
        }



        System.out.println(sum);
    }
}
