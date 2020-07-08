package implementations;

import java.sql.Array;
import java.util.Iterator;

public class ReversedList<E> implements Iterable<E> {
    private final Integer DEFAULT_CAPACITY = 2;
    private Object[] elements;
    private int capacity;
    private int size;

    public ReversedList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public void add(E element) {
        if (this.size == this.capacity) {
            this.elements = grow();
        }

        this.elements[size++] = element;

    }

    private Object[] grow() {
        int newCapacity = this.capacity * 2;
        Object[] newElements = new Object[newCapacity];

        for (int i = 0; i < this.capacity; i++) {
            newElements[i] = this.elements[i];
        }

        this.elements = newElements;
        this.capacity *= 2;

        return elements;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        ensureIndex(index);

        int realIndex = this.size - index - 1;

        return (E) this.elements[realIndex];

    }

    private void ensureIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index out of bound for index: " + index);
        }
    }

    public E removeAt(int index) {
        ensureIndex(index);
        E elementForRemove = this.get(index);
        shiftElements(index);
        return elementForRemove;
    }

    private void shiftElements(int index) {

        for (int i = index; i < this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }

        this.elements[this.size - 1] = null;
        this.size--;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) elements[index--];
            }
        };
    }


}
