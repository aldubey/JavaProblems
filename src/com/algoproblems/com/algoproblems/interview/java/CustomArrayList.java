package com.algoproblems.interview.java;

import java.util.*;

public class CustomArrayList<T> {
    private static final int INIT_CAPACITY = 16;
    private int length;
    private int capacity;
    private T[] arr = null;

    public CustomArrayList() {
        this(INIT_CAPACITY);
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
    }

    public void add(T element) {
        if (length + 1 > capacity) {
            capacity *= 2;
            T[] newArr = (T[]) new Object[capacity];
            for (int i = 0; i < length; i++) {
                newArr[i] = arr[i];
            }

            arr = newArr;
        }
        arr[length++] = element;
    }

    @Override
    public String toString() {
        return "CustomArrayList{" + "length=" + length + ", capacity=" + capacity + ", arr=" + Arrays.toString(arr) + '}';
    }

    public int getSize() {
        return length;
    }


    public static ArrayList<Integer> solve(int A, ArrayList<Integer> B) {
        int temp = 0;
        for (int i = 0; i <= (A / 2 - 1); ++i) {
            temp = B.get(i);
            B.set(i, B.get(A - (i + 1)));
            B.set(A - (i + 1), temp);
        }

        return B;
    }

    public static int[] reverse(int N, int[] a) {
        int temp = 0;
        for (int i = 0; i <= (N / 2 - 1); ++i) {
            temp = a[i];
            a[i] = a[N - (i + 1)];
            a[N - (i + 1)] = temp;
        }
        return a;
    }

    public static int maxElementDelta(int N, int[] a) {
        int max = a[0];
        int maxCount = 1;
        for (int i = 1; i < N; ++i) {
            if (a[i] > max) {
                max = a[i];
                maxCount = 1;
            } else if (a[i] == max) {
                ++maxCount;
            }
        }
        return N - maxCount;
    }

    public static int twoPair(ArrayList<Integer> A, int B) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i : A) {
            if (set.contains(B - i)) {
                return 1;
            }
            set.add(i);
        }
        return 0;
    }

    public static ArrayList<Integer> reverse(final List<Integer> A) {
        int size = A.size();
        ArrayList<Integer> result = new ArrayList<>(size);
        for (int i = size - 1; i >= 0; --i) {
            result.add(A.get(i));
        }
        return result;

    }

    public static int[] solve(int[] A, int[][] B) {
        int[] evenArr = new int[A.length];
        int count = 0;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] % 2 == 0) {
                evenArr[i] = ++count;
            } else {
                evenArr[i] = count;
            }
        }
        int[] result = new int[B.length];
        int r = -1;
        int l = -1;
        for (int i = 0; i < B.length; ++i) {
            r = B[i][1];
            l = B[i][0];

            result[i] = l == 0 ? evenArr[r] : evenArr[r] - evenArr[l - 1];

        }
        return result;
    }


    public int equilibrium(int[] A) {
        int[] sumArr = new int[A.length];
        sumArr[0] = A[0];
        for (int i = 1; i < A.length; ++i) {
            sumArr[i] = sumArr[i - 1] + A[i];

        }
        for (int i = 1; i < A.length; ++i) {
            if (sumArr[i - 1] == sumArr[A.length - 1] - sumArr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int[] productArray(int[] A) {
        int[] prefixProd = new int[A.length];
        int[] sufProd = new int[A.length];
        int[] result = new int[A.length];

        prefixProd[0] = 1;
        sufProd[A.length - 1] = 1;

        for (int i = 1; i < A.length; i++) {
            prefixProd[i] = prefixProd[i - 1] * A[i - 1];
        }

        for (int i = A.length - 2; i >= 0; i--) {
            sufProd[i] = sufProd[i + 1] * A[i + 1];
        }

        System.out.println("sufProd " + Arrays.toString(sufProd));

        for (int i = 0; i < A.length; i++) {
            result[i] = prefixProd[i] * sufProd[i];
        }

        return result;
    }

    public static int minMaxSubarray(int[] A) {
        int length = A.length;
        int subLength = length;
        int min = A[0];
        int max = A[0];
        int mini = -1;
        int maxi = -1;
        //find max & min
        for (int i = 1; i < length; ++i) {
            if (A[i] >= max) {
                max = A[i];
            } else if (A[i] < min) {
                min = A[i];
            }
        }
        System.out.println("max: " + max + " min: " + min);
        //find min max i
        for (int i = length - 1; i >= 0; --i) {
            if (A[i] == min) {
                mini = i;
                if (maxi != -1) {
                    subLength = Math.min(subLength, maxi - mini + 1);
                }
            } else if (A[i] == max) {
                maxi = i;
                if (mini != -1) {
                    subLength = Math.min(subLength, mini - maxi + 1);
                }

            }
        }
        return subLength;

    }

    public static int findString(String A) {
        long[] charACountIndex = new long[A.length()];
        long count = 0;
        for (int i = 0; i < A.length(); ++i) {
            if (A.charAt(i) == 'A') {
                charACountIndex[i] = ++count;
            } else {
                charACountIndex[i] = count;
            }
        }
        count = 0;
        for (int i = 0; i < A.length(); ++i) {
            if (A.charAt(i) == 'G') {
                count += charACountIndex[i];
            }
        }
        return (int) (count % (Math.pow(10, 9) + 7));
    }

    public static int maxProfit(final int[] A) {
        int length = A.length;
        if (length == 0) {
            return length;
        }
        int maxProfit = A[1] - A[0];
        int minPrice = A[0];
        //find max
        for (int i = 0; i < length; ++i) {
            if (A[i] < minPrice) {
                minPrice = A[i];
            }
            if (A[i] - minPrice > maxProfit) {
                maxProfit = A[i] - minPrice;
            }
            /*for(int j = i+1 ; j<length; ++j){
                if(maxProfit <= A[j]-A[i]){
                    maxProfit=A[j]-A[i];
                }
            }*/
        }
        return maxProfit;
    }

    public static int pickBothSides(int[] A, int B) {
        int n = A.length;
        int sum = A[0];
        int[] prefixSum = new int[n];
        prefixSum[0] = A[0];
        int[] suffSum = new int[n];
        suffSum[n - 1] = A[n - 1];
        for (int i = 1; i < n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }
        System.out.println("prefixSum: " + Arrays.toString(prefixSum));
        for (int i = n - 2; i >= 0; --i) {
            suffSum[i] = suffSum[i + 1] + A[i];
        }
        System.out.println("suffSum: " + Arrays.toString(suffSum));

        int l = B;
        int r = 0;
        int lsum = 0;
        int rsum = 0;
        while (l >= 0) {
            lsum = l - 1 >= 0 ? prefixSum[l - 1] : 0;
            //  rsum= n-r >=0 && r !=0  ? suffSum[n-r] : 0; // 1 2 3
            rsum = n - r >= 0 && r != 0 ? prefixSum[n - r] - prefixSum[n - r - 1] : 0; // 1 2 3
            if (sum < (lsum + rsum)) {
                sum = lsum + rsum;
            }
            --l;
            ++r;
        }
        return sum;
    }

    static int maxSubarray(int A, int B, int[] C) {
        int sum = 0;
        int temp = 0;
        int s = 0;
        if (A == 1) {
            return C[0];
        }
        for (int i = s; i < A; ++i) {
            sum += C[i];
            if (sum == B) {
                return sum;
            }
            if (sum > B) {
                if (i == A - 1) {
                    sum = temp;
                } else {
                    s++;
                    i = s;
                    temp = Math.max(temp, sum);
                    sum = 0;
                }
            } else {
                temp = Math.max(temp, sum);
            }


        }
        return sum > B ? 0 : sum;
    }

    public static int minSwap(int[] A, int B) {
        int n = A.length;
        int lessCount = 0;
        for (int j : A) {
            if (j <= B) {
                ++lessCount;
            }
        }
        int s = 0, e = lessCount - 1;
        int swap = 0;
        int result = Integer.MAX_VALUE;
        if (lessCount == 0) {
            return 0;
        }
        while (e < n) {
            swap = 0;
            System.out.println(" s: " + s + " e: " + e);
            for (int i = s; i <= e; ++i) {
                if (A[i] > B) {
                    ++swap;
                }
            }
            System.out.println(" swap " + swap);
            result = Math.min(swap, result);
            System.out.println(" result " + result);
            ++s;
            ++e;
        }
        System.out.println(" final result " + result);
        return result;
    }

    public static int[][] generateMatrix(int A) {
        int[][] matrix = new int[A][A];
        int r = 0, c = 0, value = 1;
        while (A > 1) {
            for (int i = 1; i < A; ++i) {
                matrix[r][c] = value;
                ++value;
                ++c;
            }

            for (int i = 1; i < A; ++i) {
                matrix[r][c] = value;
                ++value;
                ++r;
            }
            for (int i = 1; i < A; ++i) {
                matrix[r][c] = value;
                ++value;
                --c;
            }
            for (int i = 1; i < A; ++i) {
                matrix[r][c] = value;
                ++value;
                --r;
            }
            ++r;
            ++c;
            A -= 2;
        }
        if (A == 1) {
            matrix[r][c] = value;
        }
        return matrix;
    }

    public static int minAvg(int[] A, int B) {
        int n = A.length;
        double avg = (double) A[0] / B;
        int fi = 0;

        for (int i = 1; i < B; ++i) {
            avg = Math.round(avg + (double) A[i] / B);
        }
        double minAvg = avg;
        if (n == B) {
            return fi;
        }

        int s = 1, e = B;
        while (e < n) {
            avg = Math.round(avg - (double) A[s - 1] / B + (double) A[e] / B);
            if (avg < minAvg) {
                minAvg = avg;
                if (fi < s) {
                    fi = s;
                }
            }
            ++s;
            ++e;
        }
        return fi;
    }

    public static int[] evenIndexSum(int[] A, int[][] B) {
        int[] result = new int[B.length];
        int sum = 0;
        for (int i = 0; i < B.length; ++i) {
            sum = 0;
            int l = B[i][0];
            int r = B[i][1];
            for (int j = l; j <= r; j += 2) {
                sum += A[j];
            }
            result[i] = sum;
        }
        return result;
    }

    public static long bitwiseOrSubArr(int A, int[] B) {
        long totalSubArrays = (long) A * (A + 1) / 2;
        long count = 0;
        long zeroes = 0;
        boolean hasZeroes = false;

        for (int i = 0; i < A; ++i) {
            if (B[i] == 0) {
                zeroes++;
                hasZeroes = true;
            } else {
                count += zeroes * (zeroes + 1) / 2;
                zeroes = 0;
            }
        }

        count += zeroes * (zeroes + 1) / 2;

        if (!hasZeroes) {
            return totalSubArrays; // Return the total number of subarrays if there are no zeroes
        }

        return totalSubArrays - count;
    }

    public static int[] solve(int[] A, int[] B) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            if (map.containsKey((i))) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int[] frequencies = new int[B.length];
        for (int i = 0; i < B.length; ++i) {
            frequencies[i] = null == map.get(i) ? 0 : map.get(i);
        }
        return frequencies;
    }

    public static int numSetBits(int A) {
        List<Integer> list = new ArrayList<>();
        while (A > 0) {
            int r = A % 2;
            list.add(r);
            A = A / 2;
        }

        return (int) list.stream().filter(n -> n.equals(1)).count();
    }

    public static int digitSum(int A) {
        if (A % 10 == A) {
            return A;
        }
        return digitSum(A % 10) + digitSum(A % 10);
    }

    public static ListNode insertNode(ListNode A, int B, int C) {
        int i = 0;
        ListNode tempNode = A;
        ListNode replaceNode = null;
        while (tempNode != null && (i < C)) {
            replaceNode = tempNode;
            tempNode = tempNode.next;
            ++i;
        }
        ListNode node = new ListNode(B);
        if (tempNode == null && replaceNode != null) {
            replaceNode.next = node;
        } else {
            replaceNode.next = node;
            node.next = tempNode;
        }
        return A;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(11);
        a.next = new ListNode(12);
        a.next.next = new ListNode(13);
        System.out.println(" insertNode(a, 2, 14) " + insertNode(a, 14, 5));
    }

}

class   ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

class Matrix {
    // Define properties here
    private final int rows;
    private final int cols;
    private final int[][] arr;

    // Define constructor here
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.arr = new int[this.rows][this.cols];
    }

    void input(Scanner sc) {
        // Use the Scanner object passed as argument for taking input and not a new Scanner object
        // Complete the function
       
    }


}
