package com.algoproblems.coursera;

public class GCD {
    private static int gcd(int a, int b) {
        int gcd = 1;
        for (int i = 1; i < a + b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public static int gcd_maths_method(int a, int b) {
        int r = -1;
        int result = 1;
        do {
            if (a > b) {
                r = a % b;
                if (r > 0) {
                    a = b;
                    b = r;
                } else {
                    result = b;
                }
            } else {
                r = b % a;
                if (r > 0) {
                    b = a;
                    a = r;
                } else {
                    result = a;
                }
            }
        }
        while (r > 0);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(gcd_maths_method(2589063, 4));
    }


    // GCD of 4,6
   /*
            4 |6|1
               4||
    --------------------
               2 | 4|2
                    4
               -------
                     0
   */
}
