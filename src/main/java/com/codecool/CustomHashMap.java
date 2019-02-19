package com.codecool;


public class CustomHashMap<K, V> {

    private int hashMapSize = 16;
    private CustomLinkedList<KeyValue>[] elements;

    public CustomHashMap() {
        this.elements = (CustomLinkedList<KeyValue>[]) new CustomLinkedList<?>[this.hashMapSize];
    }

    public V add(K key, V value) {

        int position = getHash(key);

        if (elements[position] != null) {
            CustomLinkedList<KeyValue> currentList = elements[position];

            for (int index = 0; index < currentList.size(); index++) {
                if (currentList.get(index).getKey().equals(key)) {
                    V oldValue = (V) currentList.get(index).getValue();
                    currentList.get(index).setValue(value);
                    return oldValue;
                }
            }
        }
        elements[position] = new CustomLinkedList<>();
        elements[position].add(new KeyValue(key, value));
        resizeIfNeeded();
        return null;
    }

    public V getValue (K key) throws CustomHashMapKeyException {

        int position = getHash(key);
        CustomLinkedList<KeyValue> currentList = elements[position];
        V value = null;

        if (currentList != null) {
            for (int index = 0; index < currentList.size(); index++) {
                if (currentList.get(index).getKey().equals(key)) {
                    value = (V) currentList.get(index).getValue();
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
        CustomLinkedList<KeyValue> currentList = elements[position];
        V value = null;

        if (currentList != null) {
            for (int index = 0; index < currentList.size(); index++) {
                if (currentList.get(index).getKey().equals(key)) {
                    value = (V) currentList.get(index).getValue();
                    currentList.remove(index);
                }
            }
        }
        if (value == null) {
            throw new CustomHashMapKeyException("key not found error");
        }
        return value;

    }

    public boolean isEmpty() {

        for (CustomLinkedList<KeyValue> currentList : this.elements) {
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

        for (CustomLinkedList<KeyValue> currentList : this.elements) {
            if (currentList != null) {
                for (int index = 0; index < currentList.size(); index++) {
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
        CustomLinkedList<KeyValue> keyValuePairs = new CustomLinkedList<>();

        for (int index = 0; index < this.elements.length; index++) {
            if (this.elements[index] != null) {
                for (int innerIndex = 0; innerIndex < this.elements[index].size(); innerIndex++) {
                    keyValuePairs.add(this.elements[index].get(innerIndex));
                }
            }
        }

        this.elements = (CustomLinkedList<KeyValue>[]) new CustomLinkedList<?>[newHashMapSize];
        this.hashMapSize = newHashMapSize;

        for (int index = 0; index < keyValuePairs.size(); index++) {
            this.add((K) keyValuePairs.get(index).getKey(), (V) keyValuePairs.get(index).getValue());
        }
    }
}
