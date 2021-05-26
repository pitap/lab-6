package com.company.Threads;

import com.company.Seriesable.Seriesable;

public class ReadingThread extends Thread {
    private Seriesable s;

    public ReadingThread(Seriesable s) {
        this.s = s;
    }

    @Override
    public void run() {
        if (s == null) {
            System.out.println("операция невозможна: объект не задан");
            return;
        }

        for (int index = 0; index < s.getNumOfEls(); index++) {
            System.out.println("READ  " + s.getNumOfPagesOfEl(index) + " from position " + index);
        }
    }
}
