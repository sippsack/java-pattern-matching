package de.sippsack.list.fp;

import org.junit.jupiter.api.Test;

import static de.sippsack.list.fp.LinkedList.*;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTests {

    private LinkedList<Integer> list = LinkedList.of(1, 2, 3);

    @Test
    void head_of_linked_list() {
        assertEquals(1, head(list));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            head(list);
        });
    }

    @Test
    void tail_of_linked_list() {
        assertEquals(EMPTY, tail(EMPTY));
        assertEquals(LinkedList.of(2, 3), tail(list));
    }

    @Test
    void linked_list_contains() {
        assertFalse(contains(42, EMPTY));
        assertTrue(contains(1, list));
        assertFalse(contains(5, list));
    }

}
