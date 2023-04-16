package me.fulln.study.alg;

public class Leetcode20230101 {

    public static void main(String[] args) {
     Leetcode20230101 leetcode20230101 = new Leetcode20230101();
        System.out.println(leetcode20230101.chars("abcdd"));
    }

    public char chars(String s) {
        char[] chars = s.toCharArray();
        int m = 1 << ( chars[0] -'a');
        for (int i = 1; i < chars.length; i++) {
            if ((m & 1 << (chars[i] -'a')) == 0) {
                m |=1 << (chars[i]-'a');
            }else{
                return chars[i];
            }
        }
        return  'a';
    }

}
