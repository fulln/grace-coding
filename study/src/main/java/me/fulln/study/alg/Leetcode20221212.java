package me.fulln.study.alg;

public class Leetcode20221212 {


    public static int checkMax(String s) {
        int sum = 0;
        char[] chars = s.toCharArray();
        int maxIndex, minIndex;
        for (int i = 0; i < chars.length; i++) {
            maxIndex = chars[i] - 'a';
            minIndex = chars[i] - 'a';
            int[] ret = new int[26];
            ret[maxIndex]++;
            for (int j = i+1; j < chars.length; j++) {
                ret[chars[j] - 'a']++;
                if (ret[chars[j] - 'a'] > ret[maxIndex]) {
                    maxIndex = chars[j] - 'a';
                }
                if (ret[chars[j] - 'a'] < ret[minIndex]) {
                    minIndex = chars[j] - 'a';
                }
                sum += ret[maxIndex] - ret[minIndex];
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        System.out.println(checkMax("aabcbaa"));
    }

}
