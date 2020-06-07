package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {


    /*
        f(n) = n/2 if n is even
        f(n) = 3 * n + 1 if ni odd;

        e,g 5->
        1. 16
        2. 8
        3. 4
        4. 2
        5. 1

     */
    private static int collatz(int num, int i) {
        if (num == 1) {
            return i;
        } else if (num % 2 == 0) {
            return collatz(num / 2, ++i);
        } else {
            return collatz(num * 3 + 1, ++i);
        }
    }

    static int sum(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sum(n - 1);
    }

    static boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        } else if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        } else {
            return isPalindrome(s.substring(1, s.length() - 1));
        }
    }

    static String reverseString(String s) {
        return reverseString(s, "");
    }

    /*
      # Reverse a string
    1. abc, "" -> len < 2  false
       tmp = "" + c -> c
       return  reverse(ab, c)

    2. ab, c -> len < 2 false
       tmp = c + b -> cb
       return reverse(a, cb)

    3. a, cb -> len < 2 true
       return cb + a > cba  (base case hit)

       unwind :     cba

    */
    static String reverseString(String s, String tmp) {
        if (s.length() < 2) {
            return tmp + s;
        } else {
            tmp = tmp + s.charAt(s.length() - 1);
        }
        return reverseString(s.substring(0, s.length() - 1), tmp);
    }

    /*
       # Binary search
    Algo:
           if(midIndex < 1 || midIndex==length -1 || k==num[midIndex]){
                return midIndex
           }
           else if( k < num[midIndex]){
               return search(num, midIndex/2)
          }
          else if(k > num[midIndex]){
                return search(num, (length - midIndex) +1)
          }
    FLow: num : {1,2,3,4,5} , length : 5, k:4, mid: 2
        1.   k < num[mid] -> false
             k > num[mid]  ->  true
                return search(num, 4) (mid = (4)

        2. k:4, mid:4


    */
    public static int search(int[] num, int l, int r, int k) {
        if (l > r) {
            return -1;
        }
        int mid = (l + r) / 2;
        if (k == num[mid]) {
            return mid;
        }
        if (k < num[mid]) {
            return search(num, l, mid - 1, k);
        } else if (k > num[mid]) {
            return search(num, mid + 1, r, k);
        }
        return -1;
    }

    public static int lizardWall(int wallHeight, int climb, int shift, int days) {
        if (wallHeight < climb) {
            return days;
        } else {
            return lizardWall(wallHeight - climb + shift, climb, shift, ++days);
        }
    }

    public static void charCount(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);

            }
        }
        map.forEach((key, val) -> System.out.println(key + ": " + val));
    }

    public static void charCount2(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            Integer val;
            if (null != (val = map.put(c, 1))) {
                map.put(c, ++val);
            }
        }
        map.forEach((key, val) -> System.out.println(key + " : " + val));
    }

    public static void charCount3(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            map.computeIfPresent(c, (key, value) -> ++value);
            map.putIfAbsent(c, 1);
        }
        map.forEach((key, val) -> System.out.println(key + " : " + val));
    }

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(s.substring(1, s.length()));
        System.out.println(s.substring(0, s.length()-1));

        System.out.println(shiftString("abcd", 1, 4));

    }

    private static String shiftString(String s, int leftS, int rightS) {
        String tmp = s;
        //left
        for (int i = 1; i <= leftS; i++) {
            tmp = tmp.substring(1, s.length());
            tmp = tmp + s.charAt(0);
            s = tmp;
        }
        System.out.println("After left shift "+tmp);
        //right
        for (int i = 1; i <= rightS; i++) {
            tmp = tmp.substring(0, s.length()-1);
            tmp = s.charAt(s.length()-1) + tmp ;
            s = tmp;
        }
        return s;
    }


}

