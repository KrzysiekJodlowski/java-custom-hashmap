package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("unchecked")
class CustomHashMapTest {
    private CustomHashMap customHashMap;

    @BeforeEach
    public void setCustomHashMap() {
        this.customHashMap = new CustomHashMap();
    }

    @Test
    public void testAddingToHashMap() {

        assertNull(this.customHashMap.add("Key", 27));
    }

    @Test
    public void testAddingToHashMapReturningOldValue() {

        assertNull(this.customHashMap.add("Key", 27));
        assertEquals(27, this.customHashMap.add("Key", 28));
    }

    @Test
    public void testGettingAValue() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);

        assertEquals(27, this.customHashMap.getValue("Key"));
    }

    @Test
    public void testIfThrowsCustomHashMapKeyExceptionWhenGettingWithWrongKey() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);

        assertThrows(CustomHashMapKeyException.class, ()-> this.customHashMap.getValue("AnotherKey"));
    }

    @Test
    public void testRemovingData() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);

        assertEquals(27, this.customHashMap.remove("Key"));
    }

    @Test
    public void testIfThrowsCustomHashMapKeyExceptionWhenRemovingNoExistingData() throws CustomHashMapKeyException {

        this.customHashMap.add("Key", 27);

        assertThrows(CustomHashMapKeyException.class, ()-> this.customHashMap.remove("AnotherKey"));
    }

    @Test
    public void testIsEmptyWhenEmptyHashMap() {

        assertTrue(this.customHashMap.isEmpty());
    }

    @Test
    public void testIsNotEmptyWhenNotEmptyHashMap() {

        this.customHashMap.add("Key", 27);

        assertFalse(this.customHashMap.isEmpty());
    }

    @Test
    public void testClearingHashMap() {

        this.customHashMap.add("Key", 27);
        this.customHashMap.add("Another key", 28);

        this.customHashMap.clearAll();

        assertTrue(this.customHashMap.isEmpty());
    }

    @Test
    public void testGettingSizeOfEmptyHashMap() {

        assertEquals(0, this.customHashMap.size());
    }

    @Test
    public void testGettingSizeOfNotEmptyHashMap() {

        this.customHashMap.add("Key", 27);
        this.customHashMap.add("Another key", 28);

        assertEquals(2, this.customHashMap.size());
    }

}