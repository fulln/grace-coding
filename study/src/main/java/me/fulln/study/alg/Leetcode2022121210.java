package me.fulln.study.alg;

public class Leetcode2022121210 {

    public static boolean gcd(int n) {


        int sum = 1;
        int curr = 0;
        for (int i = 1; i < n ; i++) {
            sum *= 3  ;
            if (sum > n) {
                sum /=3;
                curr = i - 1;
                break;
            }
        }
        if (sum == n) {
            return true;
        }
        int temp = 0;
        for (int i = curr; i >= 0;  i--) {
            if (temp + sum > n ){
                sum /=3;
            }else if (temp + sum == n){
                return true;
            }else {
                temp += sum;
                sum /=3;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(gcd(10));
    }
}
