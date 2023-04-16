package me.fulln.study.alg;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LeetCode20230102 {

    public int getNumberOfBacklogOrders(int[][] orders) {

        PriorityQueue<int[]> buy = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        for (int[] order : orders) {
            int temp = order[1];
            if (order[2] == 0) {
                while (!sell.isEmpty()&& temp > 0 && order[0] >= sell.peek()[0]) {
                    temp -= sell.peek()[1];
                    if (temp < 0) {
                        sell.peek()[1] = -temp;
                    } else {
                        sell.poll();
                    }
                }
                if (temp > 0) {
                    buy.offer(new int[]{order[0], temp});
                }
            } else {
                while (!buy.isEmpty() && temp > 0 && order[0] <= buy.peek()[0]) {
                    temp -= buy.peek()[1];
                    if (temp < 0) {
                        buy.peek()[1] = -temp;
                    } else {
                        buy.poll();
                    }
                }
                if (temp > 0) {
                    sell.offer(new int[]{order[0], temp});
                }
            }
        }
        int ret = 0;
        for (PriorityQueue<int[]> ints : Arrays.asList(buy, sell)) {
            while (!ints.isEmpty()) {
                ret = (ret +ints.poll()[1]) % 1000000007;
            }
        }

        return ret ;
    }

    public static void main(String[] args) {
        LeetCode20230102 leetCode20230102 = new LeetCode20230102();
        int res = leetCode20230102.getNumberOfBacklogOrders(new int[][]{{10, 5, 0}, {15, 2, 1}, {25, 1, 1}, {30, 4, 0}});
        int res2 = leetCode20230102.getNumberOfBacklogOrders(new int[][]{{7, 1000000000, 1}, {15, 3, 0}, {5, 999999995, 0}, {5, 1, 1}});
        System.out.println(res2);
    }


}
