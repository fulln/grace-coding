package me.fulln.study.alg;

import java.util.PriorityQueue;

public class LeetCode230416 {


    public static void main(String[] args) {
        //["MajorityChecker","query"]
        //[[[1,1,1,1,1,2,2,2,2,2,2,1,1,1,1,1]],[3,12,6]]

        MajorityChecker majorityChecker = new MajorityChecker(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1});
        System.out.println(majorityChecker.query(3, 12, 6));
    }

}


class MajorityChecker {

    private int[][] doubleArr;

    public MajorityChecker(int[] arr) {
        doubleArr = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            doubleArr[i][i] = arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (doubleArr[i][j - 1] == arr[j]) {
                    doubleArr[i][j] = arr[j];
                } else {
                    doubleArr[i][j] = -1;
                }
            }
        }
    }

    public int query(int left, int right, int threshold) {
        int[] arr = doubleArr[left];
        int result = arr[right];
        if (result == -1) {
            return -1;
        }
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (arr[i] == result) {
                count++;
            }
        }
        if (count >= threshold) {
            return result;
        }
        return -1;
    }


    
}