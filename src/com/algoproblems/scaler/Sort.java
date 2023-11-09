package scaler;

import java.util.Arrays;

public class Sort {
    static void mergeSort(int[] A, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            mergeSort(A, l, mid);
            mergeSort(A, mid + 1, r);
            merge(A, l, mid, r);

        }
    }

    static void merge(int[] A, int l, int mid, int r) {
        int mergedLength = r - l + 1;
        int[] merged = new int[mergedLength];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        int count = 0;

        while (p1 <= mid && p2 <= r) {
            if (A[p1] <= A[p2]) {
                merged[i++] = A[p1++];
            } else {
                merged[i++] = A[p2++];
                count = (count + (mid - p1 + 1));
            }
        }

        while (p1 <= mid) {
            merged[i++] = A[p1++];
        }

        while (p2 <= r) {
            merged[i++] = A[p2++];
        }

        // Copy merged array back to the original array
        for (int j = 0; j < mergedLength; ++j) {
            A[l + j] = merged[j];
        }

    }

    public static String largestNumber(int[] A) {
        String str = "";
        for(int i:A){
            str+=i;
        }
        Arrays.sort(str.toCharArray());
        return null;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 3, 4, 5, 3};
        mergeSort(arr, 0, 5);
        System.out.println(Arrays.toString(arr));
    }
}
