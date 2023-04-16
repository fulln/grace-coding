package me.fulln.study.alg;


import java.util.*;

public class Leetcode20221130 {

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        int[] c = new int[]{5, 7, 5, 7, 4, 5};


        for (int i = 0; i < c.length; i++) {
            freqStack.push(c[i]);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(freqStack.pop());
        }

    }

    static class FreqStack {

        static class Ele {
            int element;
            int index;
            int curr;
        }

        PriorityQueue<Ele> scales;

        Map<Integer, Integer> map;


        public FreqStack() {
            scales = new PriorityQueue<>((a,b) -> b.index != a.index ? b.index - a.index : b.curr - a.curr);
            map = new HashMap<>();
        }

        public void push(int val) {
            int n = scales.size();
            map.compute(val, (k, v) -> (v == null ? 0 : v) + 1);
            Ele e = new Ele();
            e.element = val;
            e.curr = n;
            e.index = map.get(val);
            scales.add(e);
        }

        public int pop() {
            return scales.poll().element;
        }

    }
}


