package scaler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problems {

    static void mergeSort(int[] A, int l, int r) {
        if (l < r) {
            // int mid = (l + r) / 2;
            int mid = l + (r - l) / 2;
            mergeSort(A, l, mid);
            mergeSort(A, mid + 1, r);
            merge(A, l, mid, r);
        }
    }

    static ListNode mergeSort(ListNode list1) {
        if (list1 == null || list1.next == null) {
            return list1;
        }
        ListNode mid = middleNode(list1);
        ListNode list2 = mid.next;
        mid.next = null;
        list1 = mergeSort(list1);
        list2 = mergeSort(list2);
        return mergeSortedLists(list1, list2);
    }


    static void merge(int[] A, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int p1 = l, p2 = mid + 1, p3 = 0;
        while (p1 <= mid && p2 <= r) {
            if (A[p1] <= A[p2]) {
                temp[p3] = A[p1];
                p1++;
                p3++;
            } else {
                temp[p3] = A[p2];
                p2++;
                p3++;
            }
        }

        while (p1 <= mid) {
            temp[p3] = A[p1];
            p1++;
            p3++;
        }

        while (p2 <= r) {
            temp[p3] = A[p2];
            p2++;
            p3++;
        }
        for (int j = 0; j < temp.length; ++j) {
            A[l + j] = temp[j];
        }
    }

    public static String largestNumber(int[] A) {
        List<String> list = new ArrayList<>();
        long zeroCount = 0;
        for (int i : A) {
            if (i == 0) {
                zeroCount++;
            }
            list.add(String.valueOf(i));
        }
        if (zeroCount == list.size()) {
            return "0";
        }
        Comparator<String> stringComparator = (a, b) -> (a + b).compareTo(b + a);
        list.sort(stringComparator.reversed());
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s);
        }
        return result.toString();
    }

    public static int[] factorsSort(int[] A) {
        Integer[] boxedArray = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            boxedArray[i] = A[i];
        }

        Comparator<Integer> factorsComparator = (num1, num2) -> {
            int f1 = factorsCount(num1);
            int f2 = factorsCount(num2);
            return f1 == f2 ? Integer.compare(num1, num2) : Integer.compare(f1, f2);
        };

        Arrays.sort(boxedArray, factorsComparator);

        for (int i = 0; i < A.length; i++) {
            A[i] = boxedArray[i];
        }
        return A;
    }

    public static int factorsCount(int num) {
        int count = 0;
        for (int i = 1; i * i <= num; ++i) {
            if (num % i == 0) {
                count += i * i == num ? 1 : 2;
            }
        }
        return count;
    }

    public static ListNode reverseList(ListNode A) {
        ListNode temp;
        ListNode prev = null;
        ListNode curr = A;
        while (curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        A = prev;
        return A;
    }

    public static int lPalin(ListNode A) {
        int length = 1;
        ListNode curr = A;
        while (curr.next != null) {
            curr = curr.next;
            ++length;
        }
        if (length == 1) {
            return 1;
        }
        int mid = (length + 1) / 2;
        curr = A;
        for (int i = 1; i < mid; ++i) {
            curr = curr.next;
        }
        ListNode list2 = curr.next;
        curr.next = null;

        list2 = reverseList(list2);

        ListNode curr1 = A;
        ListNode curr2 = list2;

        while (curr2 != null) {
            if (curr1.getVal() != curr2.getVal()) {
                return 0;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return 1;
    }

    public static void insert_node(ListNode head, int position, int value) {
        ListNode curr = head;
        ListNode temp = null;
        for (int i = 1; i < position; i++) {
            temp = curr.next;
        }
        ListNode newNode = new ListNode(value);
        curr.next = newNode;
        newNode.next = temp;
        head = curr;
    }

    public static ListNode removeNthFromEnd(ListNode A, int B) {
        int length = 1;
        ListNode curr = A;
        while (curr.next != null) {
            curr = curr.next;
            ++length;
        }
        B = B > length ? 1 : B;
        ListNode head = A;
        curr = A;
        ListNode temp = null;
        if (B == 1) {
            head = head.next;
        }


        for (int i = 1; i <= length - B; i++) {
            temp = curr;
            curr = curr.next;
        }
        if (temp != null && temp.next != null) {
            temp.next = curr.next;
        }
        return head;
    }

    public static ListNode deleteDuplicates(ListNode A) {
        ListNode curr = A;
        while (curr != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return A;
    }

    public static ListNode reverseList(ListNode A, int B) {

        ListNode temp;
        ListNode prev = null;
        ListNode curr = A;
        int k = 1;

        while (curr != null && k <= 3) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            ++k;
        }
        if (curr != null) {
            A.next = reverseList(curr, B);
        }
        return prev;

    }

    public static ListNode reverseBetween(ListNode A, int B, int C) {
        ListNode temp;
        ListNode prev = null;
        int k = B;

        ListNode start = A;
        ListNode part1 = null;
        for (int i = 1; i < B; i++) {
            part1 = start;
            start = start.next;
        }
        while (start != null && k <= C) {
            temp = start.next;
            start.next = prev;
            prev = start;
            start = temp;
            ++k;
        }
        // Connect part1 to the reversed section
        if (part1 != null) {
            part1.next = prev;
        } else {
            A = prev; // Update the head of the list if B == 1
        }
        if (start != null) {
            while (prev.next != null) {
                prev = prev.next;
            }
            prev.next = start;
        }
        return A;
    }

    public static ListNode middleNode(ListNode A) {
        ListNode slowNode = A;
        ListNode fastNode = A;
        while (fastNode.next != null && fastNode.next.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        return slowNode;
    }

    /* public static ListNode loopBreak(ListNode A) {
         ListNode slowNode = A;
         ListNode fastNode = A;
         while (fastNode.next != null && fastNode.next.next != null) {
             slowNode = slowNode.next;
             fastNode = fastNode.next.next;
             if (slowNode == fastNode) {
                 break;
             }
         }
         slowNode = A;
         while (slowNode != fastNode ) {
             slowNode = slowNode.next;
             fastNode = fastNode.next.next;
             if (slowNode == fastNode) {
                 break;
             }
         }
         return slowNode;
     }*/
    public static ListNode loopBreak(ListNode A) {
        ListNode slowNode = A;
        ListNode fastNode = A;
        boolean loopDetected = false;

        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;

            if (slowNode == fastNode) {
                loopDetected = true;
                break;
            }
        }

        if (!loopDetected) {
            return null;
        }
        slowNode = A;
        while (slowNode != fastNode) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            System.out.println("last loop");
        }
        ListNode startNode = slowNode;
        ListNode currNode = slowNode;
        while (currNode.next != startNode) {
            currNode = currNode.next;
        }
        currNode.next = null;
        return A;
    }


    //merge sorted linkedlist
    public static ListNode mergeSortedLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        if (list1 != null) {
            curr.next = list1;
        } else {
            curr.next = list2;
        }
        return dummyHead.next;
    }


    public static ListNode swapPairs(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = A;
        ListNode prev = dummy;
        ListNode curr = A;
        while (curr != null && curr.next != null) {
            ListNode next = curr.next.next;//next = 3->4->..., curr= 1->2->3->4->
            prev.next = curr.next;//prev.next = d->2
            curr.next.next = curr;//  2-> curr => 2->1  || next->3->4->5
            curr.next = next;// 1->next => 1->3->4 => 2->1->3->4
            prev = curr;// prev -> 1->3->4
            curr = next;// curr -> next => 3->4->
        }
        return dummy.next;
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;

        for (int k = 1; k < prices.length; k++) {
            for (int i = 0; i + k < prices.length; i++) {
                profit = Math.max(prices[i + k] - prices[i], profit);
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        //   listNode6.next = listNode2;


      /*  ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode10 = new ListNode(10);
        listNode3.next = listNode4;
        listNode4.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode10;*/
       /* ListNode listNode1 = new ListNode(8);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode5;
        listNode5.next = listNode2;
        listNode2.next = listNode6;*/

        //  System.out.println(loopBreak(listNode1));
        System.out.println(maxProfit(new int[]{8, 7, 5, 3, 6, 4}));
        StringBuilder ans = new StringBuilder();
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public int getVal() {
            return val;
        }

        @Override
        public String toString() {
            //  return "ListNode{" + "val=" + val + ", next=" + next + '}';
            return "List";
        }
    }
}

