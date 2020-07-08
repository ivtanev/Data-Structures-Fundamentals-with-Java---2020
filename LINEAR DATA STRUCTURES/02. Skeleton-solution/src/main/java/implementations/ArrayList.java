package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_SIZE = 4;
    private int size;
    private Object[] elements;
    private int capacity;

    public ArrayList() {
        this.elements = new Object[INITIAL_SIZE];
        int size = 0;
        int capacity = INITIAL_SIZE;
    }

    @Override
    public boolean add(E element) {
        if (this.size == this.elements.length) {
            this.elements = grow();
        }
        this.elements[this.size++] = element;
        return true;
    }


    @Override
    public boolean add(int index, E element) {
        this.checkIndex(index);
        insert(index, element);
        return true;
    }

    @Override
    public E get(int index) {
        this.checkIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        this.checkIndex(index);
        E oldElement = this.get(index);
        this.elements[index] = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        this.checkIndex(index);
        E element = this.get(index);
        this.elements[index] = null;
        this.size--;
        shift(index);
        ensureCapacity();
        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < this.size; i++) {
            if(this.elements[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index;
            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private Object[] grow() {
//        this.capacity *= 2;
//        Object[] temp = new Object[capacity];
//
//        for (int i = 0; i < this.elements.length; i++) {
//            temp[i] = elements[i];
//        }
//        elements = temp;
        return Arrays.copyOf(this.elements,this.elements.length*2);
        //return elements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d for size: %d", index, this.size));
        }
    }

    private void insert(int index, E element) {
        if (this.size == elements.length) {
            this.elements = grow();
        }
        E lastElement = this.get(this.size - 1);
        for (int i = this.size - 1; i > index; i--) {
            this.elements[i] = this.elements[i - 1];
        }
        this.elements[this.size] = lastElement;
        this.elements[index] = element;
        this.size++;
    }

    private void shift(int index) {
        for (int i = index; i <= this.size - 1; i++) {
            this.elements[i] = this.elements[i + 1];
        }
    }


    private void ensureCapacity() {
        if (this.size < this.elements.length / 3) {
            this.elements = shrink();
        }
    }

    private Object[] shrink() {
        return Arrays.copyOf(this.elements, this.elements.length / 2);
    }


}
