package me.fulln.study;

public class bitSet {
    public static void main(String[] args) {
        java.util.BitSet bitSet = new java.util.BitSet(10);
        bitSet.set(0);
        bitSet.set(1);
        bitSet.set(2);
        bitSet.set(3);
        bitSet.set(4);
        bitSet.set(5);
        bitSet.set(6);
        bitSet.set(7);
        bitSet.set(8);
        bitSet.set(9);
        System.out.println(bitSet.nextClearBit(0));
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(2));
        System.out.println(bitSet.get(3));
        System.out.println(bitSet.get(4));
        System.out.println(bitSet.get(5));
        System.out.println(bitSet.get(6));
        System.out.println(bitSet.get(7));
        System.out.println(bitSet.get(8));
        System.out.println(bitSet.get(9));
    }
}
