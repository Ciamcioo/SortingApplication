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
     * Keeps the program working until the exit (6) operation is not provided. It prints the main menu with basic operations and checks for algorithm choice, whenever algorithm is choosen
     * invokes the sorting algorithm. When the sorting algorithm finished clears the flag and termianl. 
     */
    private void invokeProgram() {
        while (true) {
        try {
            menu.printOperations();
        } catch(Exception e) {
            System.out.println("Input error! Try again");
        }
        if (menu.getAlgorithm() != -1) {  
            invokeSpecifiedSorting();
            menu.clearFlags();
        }
        menu.clearTermianl();
        }

    }

    /**
     * Creates alogirhtm, checks if array exists if no returns. If array exists for specified algorithm, sorting and savingResults methods are invoked. 
     */
    private void invokeSpecifiedSorting() {
        createAlgorithm();
        switch (menu.getAlgorithm()) {
            case 1 -> { 
                if(!heapSort.checkArrayState())
                    return; 
                heapSort.sorting();
                heapSort.saveResults();
                menu.setCurrentAlgorithm(heapSort);
            }
            case 2 -> {
                if(!insertSort.checkArrayState())
                    return; 
                insertSort.sorting();
                insertSort.saveResults();
                menu.setCurrentAlgorithm(insertSort);
            }
            case 3 -> { 
                if(!quikcSort.checkArrayState())
                    return; 
                quikcSort.sorting();
                quikcSort.saveResults();
                menu.setCurrentAlgorithm(quikcSort);
            }
            case 4 -> {
                if(!shellSort.checkArrayState())
                    return; 
                shellSort.sorting();
                shellSort.saveResults();
                menu.setCurrentAlgorithm(shellSort);
            }
        }
        
    }

    /**
     * Invokes method to create algorithm based on source type specified in menu object
     */
    private void createAlgorithm() {
        if (menu.getSourceTyep() == 1) 
            createAlgorithmWithFileInput();
        else 
            createAlgorithmWithGeneratedInput();
    } 

    /**
     *  Creates object of algorithm based on the number from menu obeject when input data will be from file
     */
    private void createAlgorithmWithFileInput() {
        switch (menu.getAlgorithm()) {
            case 1 -> heapSort = new HeapSort(menu.getFileName());
            case 2 -> insertSort = new InsertSort(menu.getFileName());
            case 3 -> createQuickSortWithFileInput(); 
            case 4 -> shellSort = new ShellSort(menu.getFileName());
            }
    } 

    /**
     * Creates quicksort object of specified type when input data will be from file 
     */
    private void createQuickSortWithFileInput() {
        menu.printAdditionalRequest();
        if (menu.getQSDataType() == 1) 
            quikcSort = new QuickSort(menu.getFileName(), "float", menu.getPivot());
        else
            quikcSort = new QuickSort(menu.getFileName(), menu.getPivot());
    }

    /**
     *  Creates object of algorithm based on the number from menu obeject when input data will be generated  
     */
    private void createAlgorithmWithGeneratedInput() {
        switch (menu.getAlgorithm()) {
            case 1 -> heapSort = new HeapSort(menu.getSize());
            case 2 -> insertSort = new InsertSort(menu.getSize());
            case 3 -> createQuickSortWithGeneratedInput(); 
            case 4 -> shellSort = new ShellSort(menu.getSize());
        }
    }

    /**
     * Creates quicksort object of specified type when input data will be generated
     */
    private void createQuickSortWithGeneratedInput() {
        menu.printAdditionalRequest();
        if (menu.getQSDataType() == 1) 
            quikcSort = new QuickSort(menu.getSize(), "float", menu.getPivot());
        else
            quikcSort = new QuickSort(menu.getSize(), menu.getPivot());
    }

    /**
     * Closes the program
     */
    public static void exitApp() { 
        System.exit(0);
    }
}