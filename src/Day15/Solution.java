package Day15;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution part = new Solution();
        part.solution();
    }

    public void solution() {
        int secondLastNumber;
        int lastNumber = 9;
        List<List<Integer>> listOfLists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0, -1);
        list.add(1,8);
        list.add(2,13);
        list.add(3,1);
        list.add(4,0);
        list.add(5,18);
        listOfLists.add(0, list);
        for (int i = 7; i <= 30000000; i++) {
            secondLastNumber = lastNumber;
            for (int j = listOfLists.size() - 1; j >= 0; j--) {
                if (listOfLists.get(j).contains(lastNumber)) {
                    lastNumber = (i - 1) - (j * 1000000 + listOfLists.get(j).lastIndexOf(lastNumber));
                    break;
                } else if (j == 0) {
                    lastNumber = 0;
                }
            }

            List<Integer> myList = listOfLists.get(listOfLists.size() - 1);
            if (myList.size() == 1000000) {
                List<Integer> newList = new ArrayList<>();
                newList.add(0, secondLastNumber);
                listOfLists.add(listOfLists.size(), newList);
            } else {
                myList.add((i - 1) % 1000000, secondLastNumber);
                listOfLists.set(listOfLists.size() - 1, myList);
            }

            if (i % 100000 == 0) {
                System.out.println(((double) i / (double) 1000000) + " million");
            }
        }

        System.out.println(lastNumber);
    }
}

