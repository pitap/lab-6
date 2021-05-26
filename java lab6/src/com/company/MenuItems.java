package com.company;

import com.company.Exceptions.IllegalIndexException;
import com.company.Exceptions.NullSeriesableException;
import com.company.Factories.ArticlesSeriesFactory;
import com.company.Factories.BooksSeriesFactory;
import com.company.Factories.SeriesableFactory;
import com.company.Series.ArticlesSeries;
import com.company.Series.BooksSeries;
import com.company.Series.Series;
import com.company.Seriesable.Seriesable;

import java.io.*;
import java.util.Scanner;

import static com.company.Series.Series.*;

class MenuItems {
    static final String LINE = "-------------------------------------------------------------------------------\n";

    private static final String BYTES_FILE = "bytes.bin";
    private static final String TEXT_FILE = "text.txt";
    private static final String OBJECT_FILE = "object.bin";

    // region принты
    static void printRed(String str) {
        System.out.print("\u001B[31m" + str + "\u001B[0m");
    }

    static void printRedLn(String str) {
        System.out.println("\u001B[31m" + str + "\u001B[0m");
    }

    static void printGreen(String str) {
        System.out.print("\u001B[32m" + str + "\u001B[0m");
    }

    static void printGreenLn(String str) {
        System.out.println("\u001B[32m" + str + "\u001B[0m");
    }

    static void printTask(String task) {
        System.out.print('\n' + task + '\n' + LINE);
    }

    static void printExit() {
        System.out.println('\n' + "нажмите Enter, чтобы выйти в меню ...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
    // endregion

    // region вывести БД
    static void printSarrAsTitlesOfEls(Seriesable[] sarr) {
        System.out.print("база данных: ");
        if (sarr == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + LINE);

            for (int i = 0; i < sarr.length; i++) {
                System.out.print("[" + i + "] ");
                if (sarr[i] == null) {
                    System.out.println("элемент не задан");
                } else {
                    System.out.println('«' + sarr[i].getTitle() + '»');
                }
            }
        }
    }

    static void printSarr(Seriesable[] sarr) {
        System.out.print("база данных: ");
        if (sarr == null) {
            System.out.println("не задана");
        } else {
            System.out.print('\n' + LINE);

            for (int i = 0; i < sarr.length; i++) { // по элементам БД
                System.out.print("[" + i + "] ");
                printSer(sarr[i]);
                System.out.print(LINE + LINE);
            }
        }
    }

    private static void printSer(Seriesable s) {
        if (s == null) {
            printRedLn("серия не задана");
        } else {
            System.out.println('«' + s.getTitle() + '»');
            System.out.print(LINE);
            System.out.println(s);
        }
    }
    // endregion

    // region геты
    static Seriesable[] printGetSerArr() {
        int len;

        do {
            len = printGetInt();

            if (len < Seriesable.MIN_LEN_OF_ARR) {
                printRedLn("массив должен вмещать хотя бы" + Seriesable.MIN_LEN_OF_ARR + " элемент/-ов");
            } else if (len > Seriesable.MAX_LEN_OF_ARR) {
                printRedLn("слишком большая база");
            } else {
                Seriesable[] sarr = new Seriesable[len];
                printGreenLn("массив размером в " + len + " элементов успешно создан");
                return sarr;
            }
        } while (true);
    }

    private static int printGetInt() {
        int num;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите число ... ");
            str = scan.nextLine();

            try {
                num = Integer.parseInt(str);
                break;
            } catch (NumberFormatException exc) {
                printRedLn("ошибка: введённая строка не является числом");
            }
        } while (true);

        return num;
    }

    private static int printGetIndex(int maxIndex) {
        int index;

        Scanner scan = new Scanner(System.in);
        String str;

        do {
            System.out.print("введите индекс ... ");
            str = scan.nextLine();
            System.out.println();

            try {
                index = Integer.parseInt(str);
                if (index < 0 || index > maxIndex) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalIndexException exc) {
                printRedLn("ошибка: неверный индекс");
            } catch (Exception exc) {
                printRedLn("ошибка: введённая строка не является числом");
            }
        } while (true);

        return index;
    }
    // endregion

    // region геты и сеты базы + объекта
    static void printSetElOfSarr(Seriesable[] db) {
        if (db == null) {
            printRedLn("операция невозможна: база данных не задана");
        } else {
            System.out.println("задайте индекс элемента,\n" +
                    "который хотите изменить\n" +
                    "(нумерация начинается с нуля):");
            int index = printGetIndex(db.length - 1);

            System.out.print("задание элемента под индексом " + index + '\n' + LINE);

            db[index] = printCreateSer();
            printSetElsOfSer(db[index]);
        }
    }

    static Seriesable printCreateSer() {
        Scanner scan = new Scanner(System.in);
        System.out.print("введите название сборника ................................. ");
        String title = scan.nextLine();

        int numOfStartPages = printGetNumOfStartPages();

        int numOfEls = printGetNumOfElsInSer();

        return createInstance(title, numOfStartPages, numOfEls);
    }


    private static int printGetNumOfElsInSer() {
        int num;

        do {
            System.out.print("задание количества элементов в серии: ... ");
            num = printGetInt();

            if (num < Seriesable.MIN_NUM_OF_ELS) {
                printRedLn("серия должна содержать хотя бы " + Seriesable.MIN_NUM_OF_ELS + " элемент/-та/-ов");
            } else if (num > Seriesable.MAX_NUM_OF_ELS) {
                printRedLn("слишком большая серия");
            } else {
                return num;
            }
        } while (true);
    }

    private static int printGetNumOfStartPages() {
        int num;

        do {
            System.out.print("задание количества страниц в предисловии/аннотации каждого элемента серии: ");
            num = printGetInt();

            if (num < Seriesable.MIN_NUM_OF_START_PAGES) {
                printRedLn("предисловие/аннотация должно/-на быть хотя бы в " + Seriesable.MIN_NUM_OF_START_PAGES + " страницу/-ц");
            } else if (num > Seriesable.MAX_NUM_OF_START_PAGES) {
                printRedLn("слишком много страниц в предисловии/аннотации");
            } else {
                return num;
            }
        } while (true);
    }

    static void printSetElsOfSer(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: серия не задана");
        } else {
            for (int i = 0; i < s.getNumOfEls(); i++) {
                System.out.print("элемент под индексом  " + "[" + i + "]" + '\n' + LINE);
                try {
                    if (!printSetElOfSer(s, i)) {
                        i--;
                    }
                } catch (Exception exc) {
                    printRedLn(exc.getMessage());
                } finally {
                    System.out.println();
                }
            }
        }
    }

    private static boolean printSetElOfSer(Seriesable s, int index) throws Exception {
        if (s == null) {
            throw new UnsupportedOperationException("операция невозможна: серия не задана");
        } else {
            try {
                System.out.print("количество страниц ... ");
                int numOfPages = printGetNumOfPages();
                s.setNumOfPagesOfEl(index, numOfPages);

                return true;
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exc) {
                printRed(exc.getMessage());
                exc.printStackTrace();
                return false;
            } catch (Exception exc) {
                throw new Exception(exc.getMessage());
            }
        }
    }

    private static int printGetNumOfPages() {
        int num;

        do {
            num = printGetInt();
            if (num < Seriesable.MIN_NUM_OF_PAGES_OF_EL) {
                printRedLn("ошибка: должна/-но быть хотя бы " + Seriesable.MIN_NUM_OF_PAGES_OF_EL + " страница/-цы/-ц");
            } else if (num > Seriesable.MAX_NUM_OF_PAGES_OF_EL) {
                printRedLn("слишком много страниц");
            } else {
                return num;
            }
        } while (true);
    }
    // endregion

    // region деление базы
    static void printGetSarrWithTwoElsWithSameSumOfPagesWithoutStart(Seriesable[] sarr) {
        Seriesable[] newSarr;

        try {
            newSarr = getSarrWithTwoElsWithSameSumOfPagesWithoutStart(sarr);
            printGreenLn("база данных успешно разделена");
            System.out.println();

            printSarr(newSarr);
        } catch (Exception exc) {
            printRedLn(exc.getMessage());
            exc.printStackTrace();
        }
    }

    static void printSplitSarrIntoTwoArticlesAndBooksArrs(Seriesable[] sarr) {
        if (sarr == null) {
            printRedLn("операция невозможна: база данных не задана");
        } else {
            try {
                ArticlesSeries[] as = getArticlesArrFromSarr(sarr);
                BooksSeries[] bs = getBooksArrFromSarr(sarr);
                printGreenLn("база данных разбита на два массива, в которых хранятся однотипные элементы");
                System.out.println();

                printSarr(as);
                printSarr(bs);
            } catch (Exception exc) {
                printRedLn(exc.getMessage());
                exc.printStackTrace();
            }
        }
    }
    // endregion

    // region запись объекта
    static void printOutputSerAsBytes(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(BYTES_FILE);
                Series.outputSeriesable(s, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                printGreenLn("объект успешно записан в байтовый поток");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
                exc.printStackTrace();
            }
        }
    }

    static void printWriteSerAsText(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: объект не задан");
        } else {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(TEXT_FILE);
                Series.writeSeriesable(s, fileWriter);
                fileWriter.flush();
                fileWriter.close();

                printGreenLn("объект успешно записан в текстовый поток");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
                exc.printStackTrace();
            }
        }
    }

    static void printSerializeSer(Seriesable s) {
        if (s == null) {
            printRedLn("операция невозможна: объект не задан");
        } else {
            FileOutputStream fileOutputter;
            try {
                fileOutputter = new FileOutputStream(OBJECT_FILE);
                Series.serializeSeriesable(s, fileOutputter);
                fileOutputter.flush();
                fileOutputter.close();

                printGreenLn("объект успешно сериализован");
            } catch (IOException exc) {
                printRedLn(exc.getMessage());
                exc.printStackTrace();
            }
        }
    }
    // endregion

    // region считывание объекта
    static Seriesable printInputBytesAsSer() throws NullSeriesableException {
        Seriesable s = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(BYTES_FILE);
            s = inputSeriesable(fileInputter);
            fileInputter.close();

            printGreenLn("объект успешно считан из байтового потока (файла)");
        } catch (IOException | NullSeriesableException | ClassNotFoundException exc) {
            printRedLn(exc.getMessage());
            exc.printStackTrace();
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }

    static Seriesable printReadTextAsSer() throws NullSeriesableException {
        Seriesable s = null;

        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader(TEXT_FILE);
            bufferedReader = new BufferedReader(fileReader);

            s = readSeriesable(bufferedReader);

            bufferedReader.close();
            fileReader.close();

            printGreenLn("объект успешно считан из тектового потока (файла)");
        } catch (IOException | NullSeriesableException | ClassNotFoundException exc) {
            printRedLn(exc.getMessage());
            exc.printStackTrace();
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }

    static Seriesable printDeserializeSer() throws NullSeriesableException {
        Seriesable s = null;

        FileInputStream fileInputter;
        try {
            fileInputter = new FileInputStream(OBJECT_FILE);
            s = deserializeSeriesable(fileInputter);
            fileInputter.close();

            printGreenLn("объект успешно десериализован (из файла)");
        } catch (IOException | NullSeriesableException exc) {
            printRedLn(exc.getMessage());
            exc.printStackTrace();
        }

        if (s == null) {
            throw new NullSeriesableException("не удалось считать Seriesable");
        }

        return s;
    }
    // endregion

    static void printSetSeriesableFactory() {
        String menuItem;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("создавать по-умолчанию объекты следующего типа:\n" +
                    LINE +
                    " 1 -- Articles Series\n" +
                    " 2 --    Books Series\n" +
                    LINE +
                    "выбор ... ");
            menuItem = scan.nextLine();

            SeriesableFactory sf;
            switch (menuItem) {
                case "1":
                    sf = new ArticlesSeriesFactory();
                    break;
                case "2":
                    sf = new BooksSeriesFactory();
                    break;
                default:
                    printRedLn("ошибка: неверный пункт меню");
                    continue;
            }
            printGreenLn("фабрика успешно установлена");
            Series.setSeriesableFactory(sf);
            break;
        } while (true);
    }
}
