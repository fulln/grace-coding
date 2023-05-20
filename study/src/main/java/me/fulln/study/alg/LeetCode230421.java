package me.fulln.study.alg;

import java.util.HashMap;
import java.util.Map;

public class LeetCode230421 {


    public static void main(String[] args) {
        int i = profitableSchemes(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8});
        System.out.println(i);
    }

    /*
    集团里有 n 名员工，他们可以完成各种各样的工作创造利润。

    第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。

    工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。

    有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。

    来源：力扣（LeetCode）
    链接：https://leetcode.cn/problems/profitable-schemes
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static final int mod = 1000000007;

    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;
        for (int i = 0; i < group.length; i++) {
            int members = group[i], earn = profit[i];
            for (int j = n; j >= members; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % mod;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + dp[i][minProfit]) % mod;
        }
        return sum;
    }



}
