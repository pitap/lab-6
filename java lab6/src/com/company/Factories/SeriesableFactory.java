package com.company.Factories;

import com.company.Seriesable.Seriesable;

public interface SeriesableFactory {
    Seriesable createInstance();

    Seriesable createInstance(String title, int numOfStartPages, int numOfEls);
}
