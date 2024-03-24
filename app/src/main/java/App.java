import sortAlgorithms.*;

public class App {
    QuickSort quikcSort;
    ShellSort shellSort;
    InsertSort insetSort;
    HeapSort heapSort;
    Menu menu = new Menu();

    public static void main(String[] args) {
        App app = new App();
    }

    public static void exitApp() { 
        Menu.closeReader(); 
        System.exit(0);
    }
}