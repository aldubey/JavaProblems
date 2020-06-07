package src.leetcode;

import java.util.stream.IntStream;

public class FizzBuzz {

    public static void main(String[] args) {
        printFizzBuzz();
    }

    private static void printFizzBuzz() {
        // IntStream.range(1, 101)
        //       .map()

        IntStream.rangeClosed(1, 100).forEach(i -> {
            if (i % 15 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            }
        });
    }
}
