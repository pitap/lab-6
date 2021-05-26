package com.company.Series;

import com.company.Exceptions.IllegalIndexException;
import com.company.Seriesable.Seriesable;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Iterator;

public class BooksSeries implements Seriesable, Serializable {
    private String title;
    private int numOfPrefacePages;
    private int[] numsOfPages;

    public BooksSeries() {
        this.title = "Моё название";
        this.numOfPrefacePages = Seriesable.DEFAULT_NUM_OF_START_PAGES;
        this.numsOfPages = new int[Seriesable.DEFAULT_NUM_OF_ELS];
    }

    public BooksSeries(String title, int numOfPrefacePages, int numOfBooks) {
        this.title = title;
        this.numOfPrefacePages = numOfPrefacePages;
        numsOfPages = new int[numOfBooks];
    }

    // region get и set
    public int getNumOfEls() {
        return numsOfPages.length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfStartPages() {
        return numOfPrefacePages;
    }

    public void setNumOfStartPages(int num) {
        if (num < Seriesable.MIN_NUM_OF_START_PAGES) {
            throw new IllegalArgumentException("неверное число страниц");
        }
        if (num > Seriesable.MAX_NUM_OF_START_PAGES) {
            throw new IllegalArgumentException("слишком большое число страниц");
        }

        numOfPrefacePages = num;
    }

    public int getNumOfPagesOfEl(int index) {
        if (index < 0 || index >= numsOfPages.length) {
            throw new IllegalIndexException("неверный индекс");
        }

        return numsOfPages[index];
    }

    public void setNumOfPagesOfEl(int index, int num) {
        if (index < 0 || index >= numsOfPages.length) {
            throw new IllegalIndexException("неверный индекс");
        }
        if (num < Seriesable.MIN_NUM_OF_PAGES_OF_EL) {
            throw new IllegalIndexException("неверное число страниц");
        }
        if (num > Seriesable.MAX_NUM_OF_PAGES_OF_EL) {
            throw new IllegalIndexException("слишком большое число страниц");
        }

        numsOfPages[index] = num;
    }
    // endregion

    // функциональный метод
    public int getSumOfPagesWithoutStart() {
        int sum = 0;

        for (int num : numsOfPages) {
            sum += num;
        }

        return sum;
    }

    // region переопределения
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("название сборника книг............................. ").append(title).append('\n');
        sb.append("кол-во страниц в предисловии ...................... ").append(numOfPrefacePages).append('\n');
        sb.append("общей кол-во страниц в сборнике без предисловий ... ").append(getSumOfPagesWithoutStart()).append('\n');
        sb.append("кол-во элементов .................................. ").append(numsOfPages.length).append('\n');
        sb.append("тип объекта........................................ ").append(getClass().getName()).append('\n');
        sb.append("---------------------------------------------------\n");

        appendBooksInfo(sb);

        return sb.toString();
    }

    private void appendBooksInfo(StringBuilder sb) {
        int lastIndex = numsOfPages.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            sb.append(i).append(") ").
                    append(" (кол-во стр. -- ").append(numsOfPages[i]).append(")").append("\n");
        }
        sb.append(lastIndex).append(") ").
                append(" (кол-во стр. -- ").append(numsOfPages[lastIndex]).append(")");
    }

    @Override
    public boolean equals(Object obj) {
        boolean isSer = obj instanceof Seriesable;

        if (isSer) {
            Seriesable anotherSer = (Seriesable) obj;

            if (this.title.equals(anotherSer.getTitle()))
                return areNumsOfPagesEqual(anotherSer);
        }

        return false;
    }

    private boolean areNumsOfPagesEqual(Seriesable anotherSer) {
        return this.getNumOfStartPages() == anotherSer.getNumOfStartPages() &&
                this.numsOfPages.length == anotherSer.getNumOfEls();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    // endregion

    public void output(OutputStream out) {
        DataOutputStream dataOutputter;
        try {
            dataOutputter = new DataOutputStream(out);

            dataOutputter.writeUTF(getClass().getName());
            dataOutputter.writeUTF(title);
            dataOutputter.writeInt(numOfPrefacePages);
            dataOutputter.writeInt(numsOfPages.length);

            for (int numsOfPage : numsOfPages) {
                dataOutputter.writeInt(numsOfPage);
            }

            dataOutputter.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    public void write(Writer out) {
        PrintWriter printer = new PrintWriter(out);

        printer.print(getClass().getName());
        printer.print(' ');

        printer.print(title);
        printer.print(' ');

        printer.print(numOfPrefacePages);
        printer.print(' ');

        printer.print(numsOfPages.length);
        printer.print(' ');

        for (int numsOfPage : numsOfPages) {
            printer.print(numsOfPage);
            printer.print(' ');
        }

        printer.flush();
    }

    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return new NumsOfPagesIterator(this.numsOfPages);
    }
}
