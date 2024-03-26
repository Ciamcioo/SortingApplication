package main;

import menu.Menu;
import sortAlgorithms.*;

public class App {
    QuickSort quikcSort;
    ShellSort shellSort;
    InsertSort insertSort;
    HeapSort heapSort;
    Menu menu = new Menu();

    public static void main(String[] args) throws Exception{
        App app = new App();
        app.invokeProgram(); 
    }

    private void invokeSpecifiedSorting() throws Exception{
        createAlgorithm();
        switch (menu.getAlgorithm()) {
            case 1 -> heapSort.sorting();
            case 2 -> insertSort.sorting();
            case 3 -> quikcSort.sorting();
            case 4 -> quikcSort.sorting();
        }
    }

    private void invokeProgram() throws Exception{
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

    public static void exitApp() { 
        Menu.closeReader(); 
        System.exit(0);
    }

    private void createAlgorithm() throws Exception {
        if (menu.getSourceTyep() == 1) {
            switch (menu.getAlgorithm()) {
                case 1 -> heapSort = new HeapSort(menu.getFileName());
                case 2 -> insertSort = new InsertSort(menu.getFileName());
                case 3 -> {

                }
                case 4 -> shellSort = new ShellSort(menu.getFileName());
            }
        } 
        else {
            switch (menu.getAlgorithm()) {
                case 1 -> heapSort = new HeapSort(menu.getSize());
                case 2 -> insertSort = new InsertSort(menu.getSize());
                case 3 -> {

                }
                case 4 -> shellSort = new ShellSort(menu.getSize());
            }

        }

        

    } 
}