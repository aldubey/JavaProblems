package src.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] input = {300, 98, 97, 90, 100, 123, 67, 40, 85, 102, 2, 7, 1, 5};
        int target = 6; // 9, 3, 7, 8, 12, 6
       /* int[] indexes = findTwoSum(input, target);
        System.out.println(Arrays.toString(indexes));
        System.out.println("_________________");
        System.out.println(Arrays.toString(findTwoSum1(input, 6)));*/
        System.out.println(reverseString("find", ""));
    }

    private static int[] findTwoSum(int[] input, int target) {
        long startTime = System.nanoTime();
        int[] result = new int[2];
        for (int i = 0; i < input.length; ++i) {
            result[0] = i;
            for (int j = i + 1; j < input.length; ++j) {
                if (target - input[i] == input[j]) {
                    result[1] = j;
                    System.out.println("Time: " + (System.nanoTime() - startTime));
                    return result;
                }
            }
        }
        return result;
    }

    // {2,7,12,13}-> 9
    private static int[] findTwoSum1(int[] input, int target) {
        long startTime = System.nanoTime();
        int[] result = new int[2];
        int diff;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length; ++i) {
            diff = target - input[i];
            if (map.containsKey(diff)) {
                result[0] = map.get(diff);
                result[1] = i;
                System.out.println("Time1: " + (System.nanoTime() - startTime));
                return new int[]{map.get(diff), i};
            }
            map.put(input[i], i);
        }

        return result;
    }

    private static String reverseString(String input, String empty) {
        System.out.println("Input: " + input);
        System.out.println("Empty: " + empty);
        if (input.length() == 1) {
            return empty + input;
        }
        return empty + reverseString(input.substring(0, input.length() - 1), String.valueOf(input.charAt(input.length() - 1)));
    }
}
