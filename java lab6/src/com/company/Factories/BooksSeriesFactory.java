package com.company.Factories;

import com.company.Series.BooksSeries;
import com.company.Seriesable.Seriesable;

public class BooksSeriesFactory implements SeriesableFactory {
    @Override
    public Seriesable createInstance() {
        return new BooksSeries();
    }

    @Override
    public Seriesable createInstance(String title, int numOfStartPages, int numOfEls) {
        return new BooksSeries(title, numOfStartPages, numOfEls);
    }
}
