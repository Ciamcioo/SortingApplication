package main;

import menu.Menu;
import sortAlgorithms.*;

public class App {
    SortClass alogirhtmClass;
    Menu menu = new Menu();

    public static void main(String[] args) {
        App app = new App();
        app.invokeProgram(); 
    }


    /**
     * Keeps the program working until the exit (10) operation is not provided. It prints the main menu with basic operations and checks for algorithm choice, whenever algorithm is choosen
     * invokes the sorting algorithm. When the sorting algorithm finished clears the flag and termianl. 
     */
    private void invokeProgram() {
        while (true) {
            try {
                menu.printOperations();
            } catch(Exception e) {
                System.out.println("Something went wrong during the proces");
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
        if (alogirhtmClass == null)
            return; 
        if(!alogirhtmClass.checkArrayState())
            return;
        alogirhtmClass.setNumberOfRepetitions(menu.getNumOfRepetitions());
        alogirhtmClass.createUnsortedArray();
        alogirhtmClass.sortingControler();
        alogirhtmClass.saveResults();
        menu.setCurrentAlgorithm(alogirhtmClass); 
    }

    /**
     * Invokes method to create algorithm based on source type specified in menu object
     */
    private void createAlgorithm() {
        switch (menu.getSourceTyep()) {
            case 1 -> createAlgorithmWithFileInput();
            case 2 -> createAlgorithmWithGeneratedInput();
            default -> System.out.println("Source type wasn't specified"); 
        }
    } 

    /**
     *  Creates object of algorithm based on the number from menu obeject when input data will be from file
     */
    private void createAlgorithmWithFileInput() {
        switch (menu.getAlgorithm()) {
            case 1 -> alogirhtmClass = new HeapSort(menu.getFileName());
            case 2 -> alogirhtmClass = new InsertSort(menu.getFileName());
            case 3 -> createQuickSortWithFileInput(); 
            case 4 -> alogirhtmClass = new ShellSort(menu.getFileName());
            }
    } 

    /**
     * Creates quicksort object of specified type when input data will be from file 
     */
    private void createQuickSortWithFileInput() {
        menu.printAdditionalRequest();
        if (menu.getQSDataType() == 1) 
            alogirhtmClass = new QuickSort(menu.getFileName(), "float", menu.getPivot());
        else
            alogirhtmClass = new QuickSort(menu.getFileName(), menu.getPivot());
    }

    /**
     *  Creates object of algorithm based on the number from menu obeject when input data will be generated  
     */
    private void createAlgorithmWithGeneratedInput() {
        switch (menu.getAlgorithm()) {
            case 1 -> alogirhtmClass = new HeapSort(menu.getSize());
            case 2 -> alogirhtmClass = new InsertSort(menu.getSize());
            case 3 -> createQuickSortWithGeneratedInput(); 
            case 4 -> alogirhtmClass = new ShellSort(menu.getSize());
        }
    }

    /**
     * Creates quicksort object of specified type when input data will be generated
     */
    private void createQuickSortWithGeneratedInput() {
        menu.printAdditionalRequest();
        if (menu.getQSDataType() == 1) 
            alogirhtmClass = new QuickSort(menu.getSize(), "float", menu.getPivot());
        else
            alogirhtmClass = new QuickSort(menu.getSize(), menu.getPivot());
    }

    /**
     * Closes the program
     */
    public static void exitApp() { 
        System.exit(0);
    }
}