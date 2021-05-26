package com.company;

import com.company.Exceptions.NullSeriesableException;
import com.company.Series.ArticlesSeries;
import com.company.Series.Series;
import com.company.Seriesable.Seriesable;
import com.company.Threads.*;

import java.util.Scanner;

import static com.company.MenuItems.*;

class Program {
    public static void main(String[] args) {
        Seriesable[] sarr = null; // сборник серий (сборник сборников)
        Seriesable s = null;

        Scanner scan = new Scanner(System.in);
        String menuItem;

        int testingNumOfStartPages;
        final int testingNumOfEls = 500;
        Seriesable testingSer;

        do {
            System.out.print(LINE +
                    "ДЛЯ ТЕСТИРОВАНИЯ:\n" +
                    LINE +
                    "-1 -- создать и заполнить базу автоматически\n" +
                    "-2 -- создать и заполнить базу автоматически так,\n" +
                    "      чтобы были элементы,\n" +
                    "      у которых функциональные методы возвращают одинаковый результат\n" +
                    LINE +
                    "-3 -- создать и заполнить объект автоматически\n" +
                    "-4 -- создать заполненный автоматически синхронизированный объект\n" +
                    "-5 -- создать заполненный автоматически неизменяемый объект\n" +
                    LINE +
                    "-6 -- изменить поле-заголовок объекта\n" +
                    LINE +
                    "-7 -- установить фабрику\n" +
                    LINE +
                    LINE +
                    "РАБОТА С БАЗОЙ:\n" +
                    LINE +
                    " 1 -- вывести полную информацию базы\n" +
                    LINE +
                    " 2 -- создать базу\n" +
                    " 3 -- задание элемента базы\n" +
                    LINE +
                    " 4 -- найти в базе объекты,\n" +
                    "      функциональный метод которых возвращают одинаковый результат,\n" +
                    "      поместить такие объекты в массив\n" +
                    " 5 -- разбить базу на два массива,\n" +
                    "      в которых будут храниться однотипные элементы\n" +
                    LINE +
                    LINE +
                    "РАБОТА С ОБЪЕКТОМ:\n" +
                    LINE +
                    "12 -- показать содержимое объекта\n" +
                    LINE +
                    "13 -- создать и заполнить объект\n" +
                    "14 -- считать из байтового потока\n" +
                    "15 -- считать из текстового потока\n" +
                    "16 -- десериализовать объект\n" +
                    LINE +
                    "17 -- записать объект в байтовый поток\n" +
                    "18 -- записать объект в символьный поток\n" +
                    "19 -- сериализовать объект\n" +
                    LINE +
                    LINE +
                    "РАБОТА С НИТЯМИ:\n" +
                    LINE +
                    "20 -- заполнить нитью +\n" +
                    "      считать   нитью\n" +
                    "21 -- write-read-write-read...\n" +
                    LINE +
                    LINE +
                    "ПАТТЕРНЫ:\n" +
                    "22 -- Iterable/Iterator\n" +
                    LINE +
                    "0 -- выйти\n" +
                    LINE +
                    "выбор ... ");
            menuItem = scan.nextLine();

            switch (menuItem) {
                // region ДЛЯ ТЕСТИРОВАНИЯ
                case "-1":
                    printTask("-1 -- создать и заполнить базу автоматически");
                    sarr = Testing.getSarrThenSetWithFiveElsAutomatically();
                    break;

                case "-2":
                    printTask("-2 -- создать и заполнить базу автоматически так,\n" +
                            "      чтобы были элементы,\n" +
                            "      у которых функциональные методы возвращают одинаковый результат");
                    sarr = Testing.getSarrThenSetWithFiveElsAutomatically();
                    Testing.setTwoSerWithSameSumOfPagesWithoutStart(sarr);
                    break;

                case "-3":
                    printTask("-3 -- создать и заполнить объект Seriesable автоматически");
                    s = Testing.getSerThenSetAutomatically();
                    break;

                case "-4":
                    printTask("-4 -- создать заполненный автоматически синхронизированный объект");
                    s = Series.getSynchronizedSeriesable(Testing.getSerThenSetAutomatically());
                    break;

                case "-5":
                    printTask("-5 -- создать заполненный автоматически неизменяемый объект");
                    s = Series.getUnmodifiableSeriesable(Testing.getSerThenSetAutomatically());
                    break;

                case "-6":
                    printTask("-6 -- изменить поле-заголовок объекта");
                    try {
                        s.setTitle("Привет, Мир!");
                    } catch (Exception exc) {
                        printRedLn(exc.getMessage());
                        exc.printStackTrace();
                    }
                    break;

                case "-7":
                    printTask("-7 -- установить фабрику");
                    printSetSeriesableFactory();
                    break;
                // endregion

                // region РАБОТА С БАЗОЙ
                case "1":
                    printTask(" 1 -- вывести полную информацию базы");
                    printSarr(sarr);
                    break;

                case "2":
                    printTask(" 2 -- создать базу");
                    System.out.print("задание размера базы: ");
                    sarr = printGetSerArr();
                    break;

                case "3":
                    printTask(" 3 -- задание элемента базы");
                    printSarrAsTitlesOfEls(sarr);
                    System.out.println();
                    printSetElOfSarr(sarr);
                    break;

                case "4":
                    printTask(" 4 -- найти в базе объекты,\n" +
                            "      функциональный метод которых возвращают одинаковый результат,\n" +
                            "      поместить такие объекты в массив");
                    printGetSarrWithTwoElsWithSameSumOfPagesWithoutStart(sarr);
                    break;

                case "5":
                    printTask(" 5 -- разбить базу на два массива,\n" +
                            "      в которых будут храниться однотипные элементы");
                    printSplitSarrIntoTwoArticlesAndBooksArrs(sarr);
                    break;
                // endregion

                // region РАБОТА С ОБЪЕКТОМ
                case "12":
                    printTask("12 -- показать содержимое объекта");
                    System.out.println(s);
                    break;

                case "13":
                    printTask("13 -- создать и заполнить объект");
                    s = printCreateSer();
                    printSetElsOfSer(s);
                    break;

                case "14":
                    printTask("14 -- считать из байтового потока");
                    try {
                        s = printInputBytesAsSer();
                    } catch (NullSeriesableException exc) {
                        printRedLn(exc.getMessage());
                        exc.printStackTrace();
                    }
                    break;

                case "15":
                    printTask("15 -- считать из текстового потока");
                    try {
                        s = printReadTextAsSer();
                    } catch (NullSeriesableException exc) {
                        printRedLn(exc.getMessage());
                        exc.printStackTrace();
                    }
                    break;

                case "16":
                    printTask("16 -- десериализовать объект");
                    try {
                        s = printDeserializeSer();
                    } catch (NullSeriesableException exc) {
                        printRedLn(exc.getMessage());
                        exc.printStackTrace();
                    }
                    break;

                case "17":
                    printTask("17 -- записать объект в байтовый поток");
                    printOutputSerAsBytes(s);
                    break;

                case "18":
                    printTask("18 -- записать объект в текстовый поток");
                    printWriteSerAsText(s);
                    break;

                case "19":
                    printTask("19 -- сериализовать объект");
                    printSerializeSer(s);
                    break;
                // endregion

                // region РАБОТА С НИТЯМИ
                case "20":
                    printTask("20 -- заполнить нитью +\n" +
                            "      считать   нитью");

                    testingNumOfStartPages = Testing.getRandNumOfStartPages();

                    testingSer = new ArticlesSeries("Моё название", testingNumOfStartPages, testingNumOfEls);
                    WritingThread wt = new WritingThread(testingSer);
                    ReadingThread rt = new ReadingThread(testingSer);

                    wt.start();
                    rt.start();
                    break;

                case "21":
                    printTask("21 -- write-read-write-read...");

                    testingNumOfStartPages = Testing.getRandNumOfStartPages();

                    testingSer = new ArticlesSeries("Моё название", testingNumOfStartPages, testingNumOfEls);

                    SeriesableSynchronizer ssyncher = new SeriesableSynchronizer(testingSer);
                    WritingRunnableThread wrt = new WritingRunnableThread(ssyncher);
                    ReadingRunnableThread rrt = new ReadingRunnableThread(ssyncher);

                    new Thread(wrt).start();
                    new Thread(rrt).start();
                    // endregion

                case "22":
                    if (s == null) {
                        printRedLn("операция невозможна: серия не задана");
                    } else {
                        for (Integer numOfPages : s) {
                            System.out.print(numOfPages);
                            System.out.print(' ');
                        }
                        System.out.print('\n');
                    }
                    break;

                default:
                    break;
            }
            printExit();
        } while (!menuItem.equals("0"));
    }
}
