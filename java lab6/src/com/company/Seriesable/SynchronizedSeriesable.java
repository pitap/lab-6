package com.company.Seriesable;

import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public class SynchronizedSeriesable implements Seriesable {
    private final Seriesable s;

    public SynchronizedSeriesable(Seriesable s) {
        this.s = s;
    }

    @Override
    public synchronized int getNumOfEls() {
        return s.getNumOfEls();
    }

    @Override
    public synchronized String getTitle() {
        return s.getTitle();
    }

    @Override
    public synchronized void setTitle(String title) {
        s.setTitle(title);
    }

    @Override
    public synchronized int getNumOfStartPages() {
        return s.getNumOfStartPages();
    }

    @Override
    public synchronized void setNumOfStartPages(int num) {
        s.setNumOfStartPages(num);
    }

    @Override
    public synchronized int getNumOfPagesOfEl(int index) {
        return s.getNumOfPagesOfEl(index);
    }

    @Override
    public synchronized int getSumOfPagesWithoutStart() {
        return s.getSumOfPagesWithoutStart();
    }

    @Override
    public synchronized void setNumOfPagesOfEl(int index, int num) {
        s.setNumOfPagesOfEl(index, num);
    }

    @Override
    public synchronized void output(OutputStream out) {
        s.output(out);
    }

    @Override
    public synchronized void write(Writer out) {
        s.write(out);
    }

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return s.iterator();
    }

    @Override
    public String toString() {
        return s.toString();
    }
}
