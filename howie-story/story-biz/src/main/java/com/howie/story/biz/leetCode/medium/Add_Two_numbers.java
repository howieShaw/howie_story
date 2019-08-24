package com.howie.story.biz.leetCode.medium;

/**
 * 题目：
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class Add_Two_numbers {

    public static void main(String[] args) {
        int[] arr1 = {5};
        int[] arr2 = {5};
        ListNode l1 = mockNode(arr1);
        ListNode l2 = mockNode(arr2);

        ListNode result = Solution.addTwoNumbers2(l1,l2);
        System.out.println(result);

    }

    public static ListNode mockNode (int[] arr) {
        ListNode top = new ListNode(arr[0]);
        ListNode cur = top;

        for (int i =1,len = arr.length; i < len;i++) {
            ListNode node = new ListNode(arr[i]);
            cur.next = node;
            cur = cur.next;

        }

        return top;

    }
}

class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int val1 = 0;
        int val2 = 0;
        while (l1 != null) {
            val1 = val1 * 10 + l1.val;
            l1 = l1.next;
        }

        while (l2 != null) {
            val2 = val2 * 10 + l2.val;
            l2 = l2.next;
        }

        int sum = val1 + val2;

        ListNode node = null;
        ListNode cur = null;

        while (sum > 0) {

            int tail = sum % 10;
            ListNode tailNode = new ListNode(tail);
            if (node == null) {
                node = tailNode;
                cur = node;
            } else {
                cur.next = tailNode;
                cur = cur.next;
            }

            sum = sum / 10;

        }

        return node;

    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        if (l1 == null || (l1.next == null && l1.val==0)) {
            return l2;
        }
        if (l2 == null || (l2.next == null && l2.val==0)) {
            return l1;
        }
        ListNode node1 = l1;
        ListNode node2 = l2;

        ListNode node = null;
        ListNode cur = null;
        int temp = 0;
        while (node1 != null || node2 != null) {
            int v1 = node1 != null ? node1.val :0;
            int v2 = node2 != null ? node2.val :0;
            int sum = v1 + v2 + temp;
            temp = sum > 9 ? 1 : 0;

            ListNode tempNode = new ListNode(sum%10);
            if (node == null) {
                node = tempNode;
                cur = node;
            } else {
                cur.next = tempNode;
                cur = cur.next;
            }


            if (node1 != null) {
                node1 = node1.next;
            }

            if (node2 != null) {
                node2 = node2.next;
            }


        }

        if (temp ==1) {
            cur.next = new ListNode(temp);
        }

        return node;

    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
