package com.company.Factories;

import com.company.Series.ArticlesSeries;
import com.company.Seriesable.Seriesable;

public class ArticlesSeriesFactory implements SeriesableFactory {
    @Override
    public Seriesable createInstance() {
        return new ArticlesSeries();
    }

    @Override
    public Seriesable createInstance(String title, int numOfStartPages, int numOfEls) {
        return new ArticlesSeries(title, numOfStartPages, numOfEls);
    }
}
