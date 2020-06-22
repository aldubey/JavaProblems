package com.algoproblems.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

public class PriceDiscount {
    // in ->  8,4,6,2,3
// out -> 4,2,2,3
    public static int[] discountedPrice(int[] prices) {
        int[] discountedPrice = new int[prices.length];
        int disc = 0;
        for (int i = 0; i < prices.length; ++i) {
            disc = getDiscount(prices, i);
            discountedPrice[i] = prices[i] == disc ? prices[i] : prices[i] - disc;
        }
        return discountedPrice;
    }

    private static int getDiscount(int[] prices, int i) {
        int minIndex = -1;
        for (int j = i + 1; j < prices.length; ++j) {
            if (prices[j] < prices[i]) {
                minIndex = j;
                break;
            }
        }
        return minIndex > 0 ? prices[minIndex] : prices[i];
    }

    public static void main(String[] args) {
        // System.out.println(Arrays.toString(discountedPrice(new int[]{1, 1, 2, 2})));
        System.out.println(Arrays.toString(getDiscountStack(new int[]{8, 4, 6, 2, 3})));
    }

    private static int[] getDiscountStack(int[] prices) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(prices.length);
        int min = 0;
        int[] discountedPrice = new int[prices.length];
        discountedPrice[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0 && i - 1 >= 0; --i) {
            if (prices[i - 1] > prices[i]) {
                discountedPrice[i - 1] = prices[i - 1] - prices[i];
            } else if (prices[i-1] < prices[i] && prices[i] > min) {
                discountedPrice[i-1] = prices[i-1] - min;
            } else {
                discountedPrice[i] = prices[i];
            }
        }
        return discountedPrice;
    }

    private static int[] getDiscountRecursive(int[] prices, int[] discountPrice, int i, int j) {
        if (j + 1 < prices.length && prices[j] < prices[i]) {
            discountPrice[i] = prices[i + 1];
            i += 1;
        } else if (j == prices.length) {
            return discountPrice;
        }
        return getDiscountRecursive(prices, discountPrice, i, ++j);
    }
}
