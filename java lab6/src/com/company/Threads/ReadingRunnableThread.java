package com.company.Threads;

public class ReadingRunnableThread implements Runnable {
    private SeriesableSynchronizer ssyncher;

    public ReadingRunnableThread(SeriesableSynchronizer ssyncher) {
        this.ssyncher = ssyncher;
    }

    @Override
    public void run() {
        try {
            for (int index = 0; index < ssyncher.getSerNumOfEls(); index++) {
                ssyncher.read();
            }
        } catch (InterruptedException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
}
