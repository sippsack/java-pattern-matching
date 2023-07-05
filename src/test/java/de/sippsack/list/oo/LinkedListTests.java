package de.sippsack.list.oo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTests {

    private List<Integer> empty = LinkedList.of();
    private List<Integer> list = LinkedList.of(1, 2, 3);


    @Test
    void head_of_linked_list() {
        assertEquals(1, list.head());
        assertThrows(IndexOutOfBoundsException.class, () -> {
            empty.head();
        });
    }

    @Test
    void tail_of_linked_list() {
        assertEquals(empty, empty.tail());
        assertEquals(LinkedList.of(2, 3), list.tail());
    }

    @Test
    void linked_list_contains() {
        assertFalse(empty.contains(42));
        assertTrue(list.contains(1));
        assertFalse(list.contains(5));
    }

}
