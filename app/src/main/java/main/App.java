package main;
import java.io.IOException;

import menu.Menu;
import sortAlgorithms.*;

public class App {
    QuickSort quikcSort;
    ShellSort shellSort;
    InsertSort insetSort;
    HeapSort heapSort;
    Menu menu = new Menu();

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.invokeProgram(); 
    }

    private void chooseAlgorithm() {
        switch (menu.getAlgorithm()) {
            case 1 -> invokeAlgorithm(heapSort);
            case 2 -> invokeAlgorithm(insetSort); 
            case 3 -> invokeAlgorithm(quikcSort);
            case 4 -> invokeAlgorithm(shellSort);
        }
    }

    private void invokeProgram() throws IOException {
        while (true) {
        menu.printOperations();
        if (menu.getAlgorithm() != -1) 
            chooseAlgorithm();
        menu.clearTermianl();
        }

    }

    public static void exitApp() { 
        Menu.closeReader(); 
        System.exit(0);
    }

    private void invokeAlgorithm(SortClass sortingClass) {
        System.out.println("Printing something to check if works");

    }
}