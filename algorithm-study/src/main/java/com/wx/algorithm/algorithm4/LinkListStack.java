package com.wx.algorithm.algorithm4;

import java.util.Iterator;

public class LinkListStack<Item> implements Iterable<Item> {

    public static void main(String[] args) {
        LinkListStack<String> stack = new LinkListStack<>();
        stack.push("to");
        stack.push("be");
        stack.push("or");
        stack.push("or");
        System.out.println("stack.size:" + stack.size());
        String pop = stack.pop();
        System.out.println("pop:" + pop);
        stack.push("not");
        stack.push("to");
        stack.push("be");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private Node<Item> first;
    private int n;
    
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
}
