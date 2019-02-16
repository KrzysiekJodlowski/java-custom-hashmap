package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CustomHashMapTest {
    private CustomHashMap customHashMap;

    @BeforeEach
    public void setCustomHashMap() {
        this.customHashMap = new CustomHashMap();
    }

    @Test
    public void testAddingToHashMap() throws CustomHashMapKeyException {

        assertNull(this.customHashMap.add("Key", 27));
        assertNull(this.customHashMap.add(28, "Value"));
    }

    @Test
    public void testGettingAValue() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);

        assertEquals(27, this.customHashMap.getValue("Key"));
    }

    @Test
    public void testRemovingData() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);

        assertEquals(27, this.customHashMap.remove("Key"));
    }

    @Test
    public void testIsEmpty() {

        assertTrue(this.customHashMap.isEmpty());
    }

    @Test
    public void testClearingHashMap() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);
        this.customHashMap.add("Another key", 28);

        this.customHashMap.clearAll();

        assertTrue(this.customHashMap.isEmpty());
    }

    @Test
    public void testGettingSizeOfAHashMap() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);
        this.customHashMap.add("Another key", 28);

        assertEquals(2, this.customHashMap.size());
    }

}