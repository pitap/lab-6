package com.company;

import com.company.Series.Series;
import com.company.Seriesable.Seriesable;

import java.util.Random;

import static com.company.MenuItems.printGreenLn;

public class Testing {
    // region titles
    private static final String TITLE_1 = "Каталог лучших услуг в географическом регионе";
    private static final String TITLE_2 = "Список лучших рассказов 1913 года";
    private static final String TITLE_3 = "Книга больших новостных фотографий";
    private static final String TITLE_4 = "Академический журнал, содержащий статьи по определенной теме";
    private static final String TITLE_5 = "Каталог состоит из текстов и фотографий";
    // endregion

    static Seriesable[] getSarrThenSetWithFiveElsAutomatically() {
        Seriesable[] sarr = getSarrWithFiveEls();
        setElsInSarrWithFiveEls(sarr);

        printGreenLn("массив успешно создан и заполнен");

        return sarr;
    }

    private static Seriesable[] getSarrWithFiveEls() {
        final int five = 5;

        Seriesable[] s = new Seriesable[five];

        s[0] = getSerWithRandGeneratedType(TITLE_1, getRandNumOfStartPages(), five);
        s[1] = getSerWithRandGeneratedType(TITLE_2, getRandNumOfStartPages(), five);
        s[2] = getSerWithRandGeneratedType(TITLE_3, getRandNumOfStartPages(), five);
        s[3] = getSerWithRandGeneratedType(TITLE_4, getRandNumOfStartPages(), five);
        s[4] = getSerWithRandGeneratedType(TITLE_5, getRandNumOfStartPages(), five);

        return s;
    }

    private static Seriesable getSerWithRandGeneratedType(String title, int numOfStartPages, int numOfEls) {
        Seriesable s;
        s = Series.createInstance(title, numOfStartPages, numOfEls);
        return s;
    }

    static int getRandNumOfStartPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_START_PAGES, Seriesable.MAX_NUM_OF_START_PAGES);
    }

    private static int getRandInt(int min, int max) {
        int num;

        Random rand = new Random();
        num = min + rand.nextInt(max - min + 1);

        return num;
    }

    private static void setElsInSarrWithFiveEls(Seriesable[] sarr) {
        final int index0 = 0;
        final int index1 = 1;
        final int index2 = 2;
        final int index3 = 3;
        final int index4 = 4;

        sarr[index0].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index0].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index1].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index1].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index2].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index2].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index3].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index3].setNumOfPagesOfEl(index4, getRandNumOfPages());

        sarr[index4].setNumOfPagesOfEl(index0, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index1, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index2, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index3, getRandNumOfPages());
        sarr[index4].setNumOfPagesOfEl(index4, getRandNumOfPages());
    }

    public static int getRandNumOfPages() {
        return getRandInt(Seriesable.MIN_NUM_OF_PAGES_OF_EL, Seriesable.MAX_NUM_OF_PAGES_OF_EL);
    }

    static Seriesable getSerThenSetAutomatically() {
        Seriesable s;

        s = getSerWithRandGeneratedType(TITLE_1, getRandNumOfStartPages(), 5);

        s.setNumOfPagesOfEl(0, getRandNumOfPages());
        s.setNumOfPagesOfEl(1, getRandNumOfPages());
        s.setNumOfPagesOfEl(2, getRandNumOfPages());
        s.setNumOfPagesOfEl(3, getRandNumOfPages());
        s.setNumOfPagesOfEl(4, getRandNumOfPages());

        printGreenLn("объект успешно создан и заполнен");

        return s;
    }

    static void setTwoSerWithSameSumOfPagesWithoutStart(Seriesable[] sarr) {
        int lastIndex = sarr.length - 1;

        int firstIndex = getRandInt(0, lastIndex);
        int secondIndex = getRandInt(0, lastIndex);

        Seriesable s1 = sarr[firstIndex];
        Seriesable s2 = sarr[secondIndex];

        int sameNumOfPages;

        for (int i = 0; i < s1.getNumOfEls(); i++) {
            sameNumOfPages = s1.getNumOfPagesOfEl(i);
            s2.setNumOfPagesOfEl(i, sameNumOfPages);
        }

        printGreenLn("массив успешно создан и заполнен");
    }
}
