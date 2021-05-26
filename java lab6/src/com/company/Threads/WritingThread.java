package com.company.Threads;

import com.company.Seriesable.Seriesable;
import com.company.Testing;

public class WritingThread extends Thread {
    private Seriesable s;

    public WritingThread(Seriesable s) {
        this.s = s;
    }

    @Override
    public void run() {
        if (s == null) {
            System.out.println("операция невозможна: объект не задан");
            return;
        }

        int numOfPages;
        for (int index = 0; index < s.getNumOfEls(); index++) {
            numOfPages = Testing.getRandNumOfPages();
            s.setNumOfPagesOfEl(index, numOfPages);
            System.out.println("WRITE " + numOfPages + " to   position " + index);
        }
    }
}
