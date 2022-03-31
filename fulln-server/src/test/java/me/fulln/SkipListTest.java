package me.fulln;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkipListTest {

    SkipList list = new SkipList();


    @Test
    void add() {
        list.add(0);
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(3);
    }

    @Test
    void search() {
    }

    @Test
    void erase() {
    }
}