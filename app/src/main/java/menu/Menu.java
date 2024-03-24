package menu;
import java.io.*;
import main.App;
public class Menu {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private int algorithm = -1, typeOfSource = -1, operation = -1, size = -1 ;
    private String fileName = null;
    private boolean error = false;

    public void printOperations() {
       System.out.println("---------------------------------------");
       System.out.println("Sorting Algortihms Application");
       System.out.println("---------------------------------------");
       System.out.println();
       System.out.println("Choose operation");
       System.out.println("1. Sort an array");
       System.out.println("2. Entere fileName");
       System.out.println("3. Enter size of array");
       System.out.println("4. Exit");
       System.out.println("Enter a number related to operation");
       do {
            operation = Integer.parseInt(readData());
            checkConditions(operation, 1, 4, "Undefined operation");
       } while(error);
       clearsError();
       operationController();
    }

    private void operationController() {
        switch (operation) {
            case 1 -> printAviableAlgorithms();
            case 2 -> enterFileName(); 
            case 3 -> enterArraySize(); 
            case 4 -> App.exitApp();  
        }

    }

    private void printAviableAlgorithms() {
        System.out.println("Choose type of sort algorithm");
        System.out.println("1. Heap sorting algorithm");
        System.out.println("2. Insert sorting algorithm");
        System.out.println("3. Quick sorting algorithm");
        System.out.println("4. Shell sorting algorithm");
        System.out.println("Enter number related to sorting algorithm");
        do {
            System.out.print("> " );
            algorithm = Integer.parseInt(readData());
            checkConditions(algorithm, 1, 4, "Undefinded sorting algorithm");
        }while(error);
        clearsError();
        printSourcesOfData();
    }

    private void printSourcesOfData() {
            System.out.println("Choose source of data for array");
            System.out.println("1. File");
            System.out.println("2. Generated");
            System.out.println("Enter number relevante to source of data");
        do {
            System.out.print("> ");
            typeOfSource = Integer.parseInt(readData());
            checkConditions(typeOfSource, 1, 2, "Undefine source");
        }while(error);
        clearsError();
        sourceController();
    }

    private void sourceController() {
        switch(typeOfSource) {
            case 1 -> {
                if (fileName == null) enterFileName();
            }
            case 2 -> {
                if (size < 0)  enterArraySize();
            }
        }
    }

    private void enterFileName() {
        System.out.println("Enter a file name");
        System.out.print("> ");
        fileName = readData();
    }

    private void enterArraySize() {
        System.out.println("Enter a size of array");
        System.out.print("> ");
        size = Integer.parseInt(readData());
    }
    
    private String readData() {
        try {
            return reader.readLine();
        } catch(Exception e) {
            System.out.println("Error! While reading data");
            error = true;
            return "-1";
        }
    }

    private void checkConditions(int valueToCheck, int minValue, int maxValue, String msg) {
        if ( valueToCheck < minValue || valueToCheck > maxValue) {
            System.out.println(msg);
            error = true;
        }
    }

    private void clearsError() {
        error = false;
    }

    public static void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error! Reader was not closed");
            e.printStackTrace();
        }
    }
}

