package me.fulln.algriame;

import java.util.Random;

public class SkipList {

    Node head = new Node(0, null, null);

    Node[] nodes = new Node[64];

    Random random = new Random();


    public void add(int code) {
        int level = -1;
        for (Node curr = head; curr.down != null; curr = curr.down) {
            while (curr.right != null && curr.right.val < code) {
                curr = curr.right;
            }
            nodes[++level] = curr;
        }

        boolean insertUp = true;
        Node downNode = null;

        while (insertUp && level >= 0) {
            Node headNode = nodes[level--];
            headNode.right = new Node(code, headNode.right, downNode);
            downNode = headNode.right;
            insertUp = (random.nextInt() & 1) == 0;
        }

        if (insertUp) {
            head = new Node(0, new Node(code, null, downNode), head);
        }

    }

    public boolean search(int code) {
        for (Node curr = head; curr != null; curr = curr.down) {
            while (curr.right != null && curr.right.val < code) {
                curr = curr.right;
            }
            if (curr.right != null && curr.right.val == code) {
                return true;
            }
        }
        return false;
    }

    public boolean erase(int code) {
        boolean del = false;
        for (Node curr = head; curr != null; curr = curr.down) {
            while (curr.right != null && curr.right.val < code) {
                curr = curr.right;
            }
            if (curr.right != null && curr.right.val == code) {
                del = true;
                curr.right = curr.right.right;
            }
        }
        return del;
    }


    private static class Node {
        int val;
        Node right, down;

        Node(Integer val, Node right, Node down) {
            this.val = val;
            this.right = right;
            this.down = down;
        }

    }
}
