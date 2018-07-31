package com.codelity;

import java.util.Arrays;
import java.util.stream.Stream;

public class LongestSubsequence {
    public static int findLongestSubsequenceLength(String s1, String s2) {
        int[][] matrix = new int[s1.length() + 1][s2.length() + 1];
        System.out.println(s1.length() + ", " + s2.length());
        for (int i = 0; i <= s1.length() - 1; i++) {
            System.out.println("i: " + i);
            for (int j = 0; j <= s2.length() - 1; j++) {
                System.out.println("j: " + j);
                System.out.println("s1.charAt(i): " + s1.charAt(i) + " s2.charAt(j): " + s2.charAt(j));
                if (s1.charAt(i) == s2.charAt(j)) {
                    matrix[i + 1][j + 1] = 1 + matrix[i][j];
                } else {
                    matrix[i + 1][j + 1] = Math.max(matrix[i + 1][j], matrix[i][j + 1]);
                }
            }
        }
        Stream.of(matrix).map(Arrays::toString).forEach(System.out::println);
        return 0;
    }


    public static void main(String[] args) {
        findLongestSubsequenceLength("longestcommonsubsequence", "dynamicprogramming");
    }
}
