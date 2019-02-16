package com.codecool;


import java.util.LinkedList;

public class CustomHashMap<K, V> {

    private int hashMapSize = 16;
    private LinkedList<KeyValue>[] elements;

    public CustomHashMap() {
        this.elements = new LinkedList[this.hashMapSize];
    }

    public V add(K key, V value) throws CustomHashMapKeyException {

        int position = getHash(key);
        LinkedList<KeyValue> currentList = elements[position];

        for (KeyValue keyValue : currentList) {
            if (keyValue.getKey() == key) {
                V oldValue = this.getValue(key);
                keyValue.setValue(value);
                return oldValue;
            }
        }
        currentList.add(new KeyValue(key, value));
        resizeIfNeeded();
        return null;
    }

    public V getValue (K key) throws CustomHashMapKeyException {

        int position = getHash(key);
        LinkedList<KeyValue> currentList = elements[position];
        V value = null;

        for (KeyValue keyValue : currentList) {
            if (keyValue.getKey() == key) {
                value = (V) keyValue.getValue();
            }
        }
        if (value == null) {
            throw new CustomHashMapKeyException("key not found error");
        }
        return value;
    }

    public V remove(K key) {
        return (V) "Value";
    }

    public boolean isEmpty() {
        return false;
    }

    public void clearAll() {

    }

    public int size() {
        return this.hashMapSize;
    }

    private int getHash (K key){
        return key.hashCode() % this.hashMapSize;
    }

    private void resizeIfNeeded () {
        // If it holds more elements than bucketSize * 2, destroy and recreate it
        // with the double size of the elements array.
        // if it holds less elements than bucketSize / 2, destroy and recreate it
        // with half size of the elements array.
    }
}
