package com.codecool;

public class CustomLinkedList<T> {

    private CustomLinkedListNode head;
    private int length;
    private final int ZERO = 0;
    private final int ONE_INDEX = 1;


    public CustomLinkedList() {
        this.head = null;
        this.length = 0;
    }

    public void add(T data) {

        if (head == null) {
            this.head = new CustomLinkedListNode(data);
        } else {
            CustomLinkedListNode current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new CustomLinkedListNode(data));
        }
        this.length++;
    }

    public T get(int index) {

        this.checkIndex(index, true);
        T dataToReturn = (T) this.head.getData();

        if (this.length > this.ONE_INDEX) {
            CustomLinkedListNode current = this.head;
            for(int currentIndex = this.ZERO; currentIndex < index; currentIndex++) {
                current = current.getNext();
            }
            dataToReturn = (T) current.getData();
        }
        return dataToReturn;
    }

    public void remove(int index) {
        this.checkIndex(index, true);

        if (index == this.ZERO) {
            if (this.length == this.ONE_INDEX) {
                this.head = null;
            } else {
                this.head = head.getNext();
            }
        } else {
            CustomLinkedListNode current = head;
            for(int currentIndex = this.ZERO; currentIndex < index - this.ONE_INDEX; currentIndex++) {
                current = current.getNext();
            }

            CustomLinkedListNode nodeToRemove = current.getNext();
            if (current.getNext().getNext() != null) {
                current.setNext(current.getNext().getNext());
            }
            nodeToRemove = null;
        }
        this.length--;
    }

    public int size() {
        return this.length;
    }

    private void checkIndex(int index, boolean checkIfEndIsExceeded) {
        if (index < this.ZERO) {
            throw new ArrayIndexOutOfBoundsException("Index is negative!");
        }
        if (checkIfEndIsExceeded) {
            if (index > this.length - this.ONE_INDEX) {
                throw new ArrayIndexOutOfBoundsException("Index to high!");
            }
        }
    }
}
