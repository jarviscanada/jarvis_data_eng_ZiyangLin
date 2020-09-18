package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;

public class ArrayJList<E> implements JList<E> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
    public ArrayJList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayJList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean add(E e) {
        // check if the adding element is null.
        if (e == null) {
            throw new NullPointerException("error: cannot add null object to ArrayJList.");
        }
        // check if current elementData is full, and extend length if yes.
        if (size == elementData.length) {
            int newSize = elementData.length + elementData.length >> 1;
            elementData = Arrays.copyOf(elementData, newSize);
        }
        // add the element to the next available location.
        elementData[size++] = e;
        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return elementData.length == 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        // check if the adding element is null.
        if (o == null) {
            throw new NullPointerException("error: cannot check presence of a null object in an ArrayJList.");
        }
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        // check if the index provided is out of bound.
        if (index >= size) {
            throw new IndexOutOfBoundsException("error: index out of bound.");
        }

        return (E) elementData[index];
    }

    @Override
    public E remove(int index) {
        // check if the index provided is out of bound.
        if (index >= size) {
            throw new IndexOutOfBoundsException("error: index out of bound.");
        }

        // copy the remaining data and store them all 1 index ahead.
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        // set the original last element to be null.
        elementData[--size] = null;

        return get(index);
    }

    @Override
    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }
}
