package com.company.Series;

import com.company.Exceptions.NullSarrException;
import com.company.Exceptions.NullSeriesableException;
import com.company.Factories.ArticlesSeriesFactory;
import com.company.Factories.SeriesableFactory;
import com.company.Seriesable.Seriesable;
import com.company.Seriesable.SynchronizedSeriesable;
import com.company.Seriesable.UnmodifiableSeriesable;

import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Series {
    // region для фабрик
    private static SeriesableFactory factory = new ArticlesSeriesFactory();

    public static void setSeriesableFactory(SeriesableFactory sf) {
        factory = sf;
    }

    public static Seriesable createInstance() {
        return factory.createInstance();
    }

    public static Seriesable createInstance(String title, int numOfStartPages, int numOfEls) {
        return factory.createInstance(title, numOfStartPages, numOfEls);
    }

    public static Seriesable getSynchronizedSeriesable(Seriesable s) {
        return new SynchronizedSeriesable(s);
    }

    public static Seriesable getUnmodifiableSeriesable(Seriesable s) {
        return new UnmodifiableSeriesable(s);
    }
    // endregion

    // region помощники
    public static ArticlesSeries[] getArticlesArrFromSarr(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: массив не задан");
        } else {
            LinkedList<Integer> indexesOfArticles = getIndexesOfArticles(sarr);
            ArticlesSeries[] as = new ArticlesSeries[indexesOfArticles.size()];

            for (int i = 0; i < as.length; i++) {
                as[i] = (ArticlesSeries) sarr[indexesOfArticles.get(i)];
            }

            return as;
        }
    }

    private static LinkedList<Integer> getIndexesOfArticles(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: сборник статей не задан");
        } else {
            LinkedList<Integer> indexesOfArticles = new LinkedList<>();

            for (int i = 0; i < sarr.length; i++) {
                if (sarr[i] instanceof ArticlesSeries) {
                    indexesOfArticles.add(i);
                }
            }

            return indexesOfArticles;
        }
    }

    public static BooksSeries[] getBooksArrFromSarr(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = getIndexesOfBooks(sarr);
            BooksSeries[] bs = new BooksSeries[indexesOfBooks.size()];

            for (int i = 0; i < bs.length; i++) {
                bs[i] = (BooksSeries) sarr[indexesOfBooks.get(i)];
            }

            return bs;
        }
    }

    private static LinkedList<Integer> getIndexesOfBooks(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: сборник книг не задан");
        } else {
            LinkedList<Integer> indexesOfBooks = new LinkedList<>();

            for (int i = 0; i < sarr.length; i++) {
                if (sarr[i] instanceof BooksSeries) {
                    indexesOfBooks.add(i);
                }
            }

            return indexesOfBooks;
        }
    }

    public static Seriesable[] getSarrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: массив не задан");
        } else {
            int[] sumsOfPagesWithoutStart = getSumsOfPagesWithoutStart(sarr);

            int currIndexOfSum;
            int indexToCompareWith;
            int len = sumsOfPagesWithoutStart.length;

            for (currIndexOfSum = 0; currIndexOfSum < len; currIndexOfSum++) {
                for (indexToCompareWith = currIndexOfSum + 1; indexToCompareWith < len; indexToCompareWith++) {
                    if (sumsOfPagesWithoutStart[currIndexOfSum] == sumsOfPagesWithoutStart[indexToCompareWith]) {
                        Seriesable[] twoSer = new Seriesable[2];
                        twoSer[0] = sarr[currIndexOfSum];
                        twoSer[1] = sarr[indexToCompareWith];

                        return twoSer;
                    }
                }
            }

            throw new NoSuchElementException("нет таких элементов");
        }
    }

    private static int[] getSumsOfPagesWithoutStart(Seriesable[] sarr) throws NullSarrException {
        if (sarr == null) {
            throw new NullSarrException("операция невозможна: массив не задан");
        } else {
            int[] sumsOfPagesWithoutStart = new int[sarr.length];

            for (int i = 0; i < sumsOfPagesWithoutStart.length; i++) {
                sumsOfPagesWithoutStart[i] = sarr[i].getSumOfPagesWithoutStart();
            }

            return sumsOfPagesWithoutStart;
        }
    }
    // endregion

    // region запись объекта
    public static void outputSeriesable(Seriesable s, OutputStream out) {
        s.output(out);
    }

    public static void writeSeriesable(Seriesable s, Writer out) {
        s.write(out);
    }

    public static void serializeSeriesable(Seriesable s, OutputStream out) {
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(s);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }
    // endregion

    // region считывание объекта
    public static Seriesable inputSeriesable(InputStream in) throws NullSeriesableException, ClassNotFoundException {
        Seriesable s;

        DataInputStream dataInputter;
        try {
            dataInputter = new DataInputStream(in);

            String className = dataInputter.readUTF();
            String title = dataInputter.readUTF();
            int numOfStartPages = dataInputter.readInt();
            int numOfEls = dataInputter.readInt();

            s = getNewSerByClassName(className, title, numOfStartPages, numOfEls);

            final int len = s.getNumOfEls();
            int numOfPages;
            for (int index = 0; index < len; index++) {
                numOfPages = dataInputter.readInt();
                s.setNumOfPagesOfEl(index, numOfPages);
            }
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            s = null;
        } catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }

    public static Seriesable readSeriesable(Reader in) throws NullSeriesableException, ClassNotFoundException {
        Seriesable s;

        StreamTokenizer st;
        try {
            st = new StreamTokenizer(in);

            st.nextToken();
            String className = st.sval;

            StringBuilder sbTitle = new StringBuilder();
            String currWord;
            st.nextToken();
            while (st.ttype == StreamTokenizer.TT_WORD) {
                currWord = st.sval;
                sbTitle.append(currWord);
                sbTitle.append(' ');

                st.nextToken();
            }
            String title = sbTitle.toString();

            int numOfStartPages = (int) st.nval;

            st.nextToken();
            int numOfEls = (int) st.nval;

            s = getNewSerByClassName(className, title, numOfStartPages, numOfEls);

            final int len = s.getNumOfEls();
            int numOfPages;
            for (int index = 0; index < len; index++) {
                st.nextToken();
                numOfPages = (int) st.nval;
                s.setNumOfPagesOfEl(index, numOfPages);
            }
        } catch (IOException | NumberFormatException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            s = null;
        } catch (ClassNotFoundException exc) {
            throw new ClassNotFoundException(exc.getMessage());
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }

    private static Seriesable getNewSerByClassName(String className, String title, int numOfStartPages, int numOfEls) throws ClassNotFoundException {
        if (className.equals(ArticlesSeries.class.getName())) {
            return new ArticlesSeries(title, numOfStartPages, numOfEls);
        } else if (className.equals(BooksSeries.class.getName())) {
            return new BooksSeries(title, numOfStartPages, numOfEls);
        } else {
            throw new ClassNotFoundException("ошибка: такого класса не существует");
        }
    }

    public static Seriesable deserializeSeriesable(InputStream in) throws NullSeriesableException {
        Seriesable s;

        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            s = (Seriesable) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            s = null;
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }
    // endregion
}
