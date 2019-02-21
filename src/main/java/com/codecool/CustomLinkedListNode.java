package com.codecool;

public class CustomLinkedListNode<T extends Object> {

    private T data;
    private CustomLinkedListNode pointer;

    public CustomLinkedListNode(T data) {
        this.data = data;
    }

    public CustomLinkedListNode<T> getNext() {
        return this.pointer;
    }

    public void setNext(CustomLinkedListNode<T> pointer) {
        this.pointer = pointer;
    }

    public T getData() {
        return this.data;
    }
}
