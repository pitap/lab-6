package com.company.Series;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NumsOfPagesIterator implements Iterator<Integer> {
    private int[] numsOfPages;
    private int currPos;

    public NumsOfPagesIterator(int[] numsOfPages) {
        this.numsOfPages = numsOfPages;
        currPos = 0;
    }

    @Override
    public boolean hasNext() {
        return currPos < numsOfPages.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int next = numsOfPages[currPos];
        currPos++;

        return next;
    }
}
