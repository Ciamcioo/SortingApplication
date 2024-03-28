package main;

import menu.Menu;
import sortAlgorithms.*;

public class App {
    QuickSort quikcSort;
    ShellSort shellSort;
    InsertSort insertSort;
    HeapSort heapSort;
    Menu menu = new Menu();

    public static void main(String[] args) {
        App app = new App();
        app.invokeProgram(); 
    }


    /**
     * Method invokes the start of the application and servies of the input until the algorithm to use wasn't specified by the user 
     */
    private void invokeProgram() {
        while (true) {
        try {
            menu.printOperations();
        } catch(Exception e) {
            System.out.println("Input error! Try again");
        }
        if (menu.getAlgorithm() != -1) 
            invokeSpecifiedSorting();
        menu.clearTermianl();
        }

    }

    /**
     * Method creates the alogirhtm and invokes sorting for specified algorithm
     */
    private void invokeSpecifiedSorting() {
        createAlgorithm();
        switch (menu.getAlgorithm()) {
            case 1 -> heapSort.sorting();
            case 2 -> insertSort.sorting();
            case 3 -> quikcSort.sorting();
            case 4 -> quikcSort.sorting();
        }
    }

    /**
     * Method closes reader and program
     */
    public static void exitApp() { 
        System.exit(0);
    }

    /**
     * Method creates approprite alogirthm based on the algorithm number and source type. For quick sort additional infomrmations are taken from the user due to the fact it supports two tyeps of data
     */
    private void createAlgorithm() {
        if (menu.getSourceTyep() == 1) {
            switch (menu.getAlgorithm()) {
                case 1 -> heapSort = new HeapSort(menu.getFileName());
                case 2 -> insertSort = new InsertSort(menu.getFileName());
                case 3 -> {
                    menu.printAdditionalRequest();
                    if (menu.getQSDataType() == 1) 
                        quikcSort = new QuickSort(menu.getFileName(), "float", menu.getPivot());
                    else
                        quikcSort = new QuickSort(menu.getFileName(), menu.getPivot());

                }
                case 4 -> shellSort = new ShellSort(menu.getFileName());
            }
        } 
        else {
            switch (menu.getAlgorithm()) {
                case 1 -> heapSort = new HeapSort(menu.getSize());
                case 2 -> insertSort = new InsertSort(menu.getSize());
                case 3 -> {
                    menu.printAdditionalRequest();
                    if (menu.getQSDataType() == 1) 
                        quikcSort = new QuickSort(menu.getSize(), "float", menu.getPivot());
                    else
                        quikcSort = new QuickSort(menu.getSize(), menu.getPivot());
                }
                case 4 -> shellSort = new ShellSort(menu.getSize());
            }

        }

        

    } 
}