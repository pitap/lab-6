package com.company.Seriesable;

import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

public class UnmodifiableSeriesable implements Seriesable {
    private final Seriesable s;

    public UnmodifiableSeriesable(Seriesable s) {
        this.s = s;
    }

    @Override
    public int getNumOfEls() {
        return s.getNumOfEls();
    }

    @Override
    public String getTitle() {
        return s.getTitle();
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("неподдерживаемая операция: невозможно изменить заголовок");
    }

    @Override
    public int getNumOfStartPages() {
        return s.getNumOfStartPages();
    }

    @Override
    public void setNumOfStartPages(int num) {
        throw new UnsupportedOperationException("неподдерживаемая операция: невозможно изменить количество вступительных страниц");
    }

    @Override
    public int getNumOfPagesOfEl(int index) {
        return s.getNumOfPagesOfEl(index);
    }

    @Override
    public int getSumOfPagesWithoutStart() {
        return s.getSumOfPagesWithoutStart();
    }

    @Override
    public void setNumOfPagesOfEl(int index, int num) {
        throw new UnsupportedOperationException("неподдерживаемая операция: невозможно изменить количество страниц части серии");
    }

    @Override
    public void output(OutputStream out) {
        s.output(out);
    }

    @Override
    public void write(Writer out) {
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
