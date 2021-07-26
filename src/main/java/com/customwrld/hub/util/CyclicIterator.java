package com.customwrld.hub.util;

import java.util.Iterator;

public class CyclicIterator<T> implements Iterator<T> {

    private final T[] values;
    private int current;

    public CyclicIterator(T[] values) {
        this.values = values;
        this.current = 0;
    }

    public CyclicIterator(T[] values, int current) {
        this.values = values;
        this.current = current;
    }

    @Override
    public boolean hasNext() {
            return true;
    }

    @Override
    public T next() {
        current = (current + 1) % values.length;
        return values[current];
    }

}