package com.codelity;

import java.util.Arrays;

public class CodelityProblem {

    /*1. Missing smallest integer*/

    public static int findSmallestMissingInteger(int[] A) {
        int length = A.length;
        Arrays.parallelSort(A);
        int count = 0;
        boolean onePresent = false;
        for (int i = 0; i < length; i++) {
            if (A[i] == 1) {
                onePresent = true;
            }
            // single element check
            if (length == 1 && A[i] != 1) {
                return 1;
            }

            //all negative check
            if (A[i] <= 0) {
                count++;
                continue;
            }

            if (i + 1 != length && A[i + 1] - A[i] > 1 && onePresent|| (i == length - 1 && onePresent)) {
                return A[i] + 1;
            }
        }
        if (count == length) {
            return 1;
        }
        return 1;
    }

    public static void main(String[] args) {
        int[] inputArray = new int[]{10000,2};
        System.out.println(findSmallestMissingInteger(inputArray));
    }
}