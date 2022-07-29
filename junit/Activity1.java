package junit_TestActivities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {
    static ArrayList<String> list;

    @BeforeEach
    public void setUp(){
        // Initialize a new ArrayList
        list = new ArrayList<String>();
        // Add values to the list
        list.add("alpha"); // at index 0
        list.add("beta"); // at index 1
    }

    @Test
    public void insertTest() {
        // Assert size of list
        assertEquals(2, list.size(), "Wrong size");
        // Add new element
        list.add(2, "Scooby");
        // Assert size of new list
        assertEquals(3, list.size(), "Wrong size");

        // Assert each element in array
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("beta", list.get(1), "Wrong element");
        assertEquals("Scooby", list.get(2), "Wrong element");
    }

    @Test
    public void replaceTest() {
        assertEquals(2, list.size(), "Wrong size");
        list.add(2, "Scooby");
        assertEquals(3, list.size(), "Wrong size");
        // Replace value of element in index 1
        list.set(1, "Tommy");
        // Assert each elements
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("Tommy", list.get(1), "Wrong element");
        assertEquals("Scooby", list.get(2), "Wrong element");
    }
}
