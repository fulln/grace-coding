package me.fulln.study.alg;

public class Leetcode230505 {

    public static void main(String[] args) {
        Leetcode230505 codes = new Leetcode230505();
        System.out.println(codes.maxVowels("tryhard",4));
    }

    public int maxVowels(String  s,int k){
        int from= 0 ,end = k,ret = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i <k; i++) {
            if (check(charArray[i])){
                ret ++;
            }
        }

        int curr = ret;
        while (end < s.length()) {
            if (check(charArray[from++])){
                curr --;
            }
            if (check(charArray[end++])){
                curr ++;
            }
            ret = Math.max(ret,curr);
        }
        return ret;

    }

    public boolean check(char a){
        return a == 'a' || a == 'e' || a == 'i'|| a == 'o'|| a == 'u';
    }
}
