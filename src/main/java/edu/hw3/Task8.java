package edu.hw3;

import java.util.Iterator;
import java.util.List;

public class Task8<T> implements Iterator<T> {
    private final List<T> list;
    private int index;

    public Task8(List<T> list) {
        this.list = list;
        this.index = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            return list.get(index--);
        }
        throw new IndexOutOfBoundsException();
    }
}
