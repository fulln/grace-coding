package me.fulln.study.alg;

public class Leetcode20221226 {
    public int countHomogenous(String s) {

        char[] chars = s.toCharArray();
        int ret = 0;
        int curr = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                ret += count(curr);
                curr = 1;
            }else{
                curr++;
            }
        }
        ret += count(curr);
        return ret;
    }

    private int count(int i) {
        return (i + 1) * i / 2;
    }

    public static void main(String[] args) {
        Leetcode20221226 leetcode20221226 = new Leetcode20221226();
        System.out.println(leetcode20221226.countHomogenous("abbcccaa"));
    }


}
