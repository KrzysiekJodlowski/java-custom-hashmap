package com.codecool;

import java.util.LinkedList;


public class CustomHashMap<K, V> {

    private int hashMapSize = 16;
    private LinkedList<KeyValue>[] elements;

    public CustomHashMap() {
        this.elements = (LinkedList<KeyValue>[]) new LinkedList<?>[this.hashMapSize];
    }

    public V add(K key, V value) {

        int position = getHash(key);

        if (elements[position] != null) {
            LinkedList<KeyValue> currentList = elements[position];

            for (KeyValue keyValue : currentList) {
                if (keyValue.getKey().equals(key)) {
                    V oldValue = (V) keyValue.getValue();
                    keyValue.setValue(value);
                    return oldValue;
                }
            }
        }
        elements[position] = new LinkedList<>();
        elements[position].add(new KeyValue(key, value));
        resizeIfNeeded();
        return null;
    }

    public V getValue (K key) throws CustomHashMapKeyException {

        int position = getHash(key);
        LinkedList<KeyValue> currentList = elements[position];
        V value = null;

        if (currentList != null) {
            for (KeyValue keyValue : currentList) {
                if (keyValue.getKey().equals(key)) {
                    value = (V) keyValue.getValue();
                }
            }
        }
        if (value == null) {
            throw new CustomHashMapKeyException("key not found error");
        }
        return value;
    }

    public V remove(K key) throws CustomHashMapKeyException {

        int position = getHash(key);
        LinkedList<KeyValue> currentList = elements[position];
        V value = null;

        if (currentList != null) {
            for (KeyValue keyValue : currentList) {
                if (keyValue.getKey().equals(key)) {
                    currentList.remove(keyValue);
                    value = (V) keyValue.getValue();
                }
            }
        }
        if (value == null) {
            throw new CustomHashMapKeyException("key not found error");
        }
        return value;

    }

    public boolean isEmpty() {

        for (LinkedList<KeyValue> currentList : this.elements) {
            if (currentList != null) {
                return false;
            }
        }
        return true;
    }

    public void clearAll() {

        for (int index = 0; index < this.elements.length; index++) {
            if (this.elements[index] != null) {
                this.elements[index] = null;
            }
        }
    }

    public int size() {
        int currentSize = 0;

        for (LinkedList<KeyValue> currentList : this.elements) {
            if (currentList != null) {
                for (KeyValue keyValue : currentList) {
                    currentSize++;
                }
            }
        }
        return currentSize;
    }

    private int getHash (K key){
        return key.hashCode() % this.hashMapSize;
    }

    private void resizeIfNeeded () {
        int currentSize = this.size();
        int doubleHashMapSize = this.hashMapSize * 2;
        int halfHashMapSize = this.hashMapSize / 2;

        if (currentSize > doubleHashMapSize) {
            recreateHashMapWithNewSize(doubleHashMapSize);
        } else if (currentSize < halfHashMapSize) {
            recreateHashMapWithNewSize(halfHashMapSize);
        }
    }

    private void recreateHashMapWithNewSize(int newHashMapSize){
        LinkedList<KeyValue> keyValuePairs = new LinkedList<>();

        for (int index = 0; index < this.elements.length; index++) {
            if (this.elements[index] != null) {
                keyValuePairs.addAll(this.elements[index]);
            }
        }

        this.elements = (LinkedList<KeyValue>[]) new LinkedList<?>[newHashMapSize];
        this.hashMapSize = newHashMapSize;

        for (KeyValue keyValue : keyValuePairs) {
            this.add((K) keyValue.getKey(), (V) keyValue.getValue());
        }
    }
}
