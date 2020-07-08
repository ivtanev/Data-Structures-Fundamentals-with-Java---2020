package implementations;

import interfaces.Heap;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MaxHeapTest {
    private Heap<Integer> maxHeap;

    @Before
    public void setUp() {
        this.maxHeap = new MaxHeap<>();
        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));
        for (Integer element : elements) {
            this.maxHeap.add(element);
        }
    }

    @Test
    public void testHeapifyUpAddOne() {
        Heap<Integer> integerHeap = new MaxHeap<>();
        integerHeap.add(13);
        assertEquals(Integer.valueOf(13), integerHeap.peek());
    }

    @Test
    public void testHeapifyUpAddMany() {
        assertEquals(Integer.valueOf(25), maxHeap.peek());
    }

    @Test
    public void testHeapifyUpAdd100() {
        Heap<Integer> integerHeap = new MaxHeap<>();
        integerHeap.add(100);
        assertEquals(Integer.valueOf(100), integerHeap.peek());
    }

    @Test
    public void testSizeShouldBeCorrect() {
        assertEquals(8, this.maxHeap.size());
    }

    @Test
    public void testSizeShouldBeCorrectWhenAddElement() {
        this.maxHeap.add(100);
        assertEquals(9, this.maxHeap.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testPeekShouldThrowException() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.peek();
    }

}