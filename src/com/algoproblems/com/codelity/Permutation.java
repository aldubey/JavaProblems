package com.codelity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Permutation implements Comparable<Permutation>{

    public static void main(String[] args) {
        System.out.println(solution(987645321));
    }

    public static int solution(int N) {
        String num = String.valueOf(N);
        int length = num.length();
        char[] chars = num.toCharArray();
        int zeroCount = 0;
        Map<Character, Integer> numCountMap = new HashMap<>();
        for (Character c : chars) {
            if (c.equals('0')) {
                zeroCount++;
            }
            if (numCountMap.containsKey(c)) {
                numCountMap.put(c, numCountMap.get(c) + 1);
            } else {
                numCountMap.put(c, 1);
            }
        }
        List<Integer> dupCountSet = numCountMap
                .values()
                .parallelStream()
                .filter(val -> val > 1)
                .collect(Collectors.toList());
        int dupDivisor = 1;
        for (int n : dupCountSet) {
            dupDivisor *= factorial(n);
        }
        int fact = 1;
        if (zeroCount > 0) {
            return (factorial(length - 1) * (length - zeroCount)) / dupDivisor;
        }
        return factorial(length) / dupDivisor;
    }

    static int factorial(int N) {
        int fact = 1;
        for (int i = 2; i <= N; i++) {
            fact = fact * i;
        }
        return fact;
    }

    @Override
    public int compareTo(Permutation o) {
        return 0;
    }
}
