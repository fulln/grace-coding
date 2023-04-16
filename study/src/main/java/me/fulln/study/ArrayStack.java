package me.fulln.study;

public class ArrayStack {

    private String[] element;
    private int c_size;
    private int m_size;


    public ArrayStack( int m_size) {
        this.element = new String[m_size];
        this.m_size = m_size;
    }

    public boolean put(String node){
        if (c_size >= m_size){
            return false;
        }
        this.element[c_size]= node;
        c_size ++;
        return true;
    }

    public String pop(){
        if (c_size <= 0){
            return null;
        }
        String result = this.element[c_size-1];
        c_size--;
        return result;
    }
}
