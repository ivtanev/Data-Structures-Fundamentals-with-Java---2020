package implementations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ReversedListTest {
    private ReversedList<String> list;

    @Before
    public void setUp() {
        this.list = new ReversedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
    }

    @Test
    public void checkLast() {
        assertEquals("0", list.get(99));
    }

    @Test
    public void checkFirst() {
        assertEquals("99", list.get(0));
    }

    @Test
    public void testSize() {
        assertEquals(100, list.size());
    }

    @Test
    public void testCapacity() {
        assertEquals(128, list.capacity());
    }

    @Test
    public void testInitialSize() {
        ReversedList<Integer> tst = new ReversedList<>();
        assertEquals(0, tst.size());
    }

    @Test
    public void testInitialCapacity() {
        ReversedList<Integer> tst = new ReversedList<>();
        assertEquals(2, tst.capacity());
    }

    @Test
    public void testIterator() {
        int last = 99;
        for (String s : list) {
            assertEquals(String.valueOf(last--), s);
        }
    }

    @Test
    public void testRemoveAt(){
        assertEquals("89", list.removeAt(10));
        assertEquals(99, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBound(){
        assertNull(list.get(list.size()+1));
        assertNull(list.removeAt(list.size()+1));
        assertNull(list.get(-1));
        assertNull(list.removeAt(-1));
    }
}