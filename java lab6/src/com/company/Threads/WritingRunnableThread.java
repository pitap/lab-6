package com.company.Threads;

import com.company.Testing;

public class WritingRunnableThread implements Runnable {
    private SeriesableSynchronizer ssyncher;

    public WritingRunnableThread(SeriesableSynchronizer ssyncher) {
        this.ssyncher = ssyncher;
    }

    @Override
    public void run() {
        try {
            int val;
            for (int index = 0; index < ssyncher.getSerNumOfEls(); index++) {
                val = Testing.getRandNumOfPages();
                ssyncher.write(val);
            }
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
}
