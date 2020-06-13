package com.algoproblems.coursera;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public static long fibonacci1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        long f0 = 0;
        long f1 = 1;
        long temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = f1;
            f1 = f0 + f1;
            f0 = temp;

        }
        return f1;
    }

    // 0 1 1 2 3 5 8
    static Map<Integer, Long> map = new HashMap<>();

    public static void main(String[] args) {
        // System.out.println(fibonacci1(100));
         System.out.println(fibonacci2(100));
        //System.out.println(fibonacci3(100));
    }

    private static long fibonacci3(int n) {
        if (!map.containsKey(n)) {
            if (n <= 1) {
                map.put(n, Long.valueOf(n));
            } else {
                map.put(n, fibonacci3(n - 1) + fibonacci3(n - 2));
            }
        }
        return map.get(n);
    }

    private static int fibonacci2(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci2(n - 1) + fibonacci2(n - 2);
    }
}