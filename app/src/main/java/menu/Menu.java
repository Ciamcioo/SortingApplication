package menu;

import java.io.*;
import main.App;
import sortAlgorithms.SortClass;

public class Menu {
    private static BufferedReader reader;
    private int algorithm = -1, typeOfSource = -1, operation = -1, size = 0, dataSource = -1, qsDataType = -1, pivotPlacmentQS = -1, numOfRepetition = 1  ;
    private String fileName = null;
    private boolean error = false;
    private SortClass currentAlgorithm = null;

    /**
     *  Prints main menu of application, as an input takes number relating to next operations. Input will be read unless it is not correct. After that error variable is cleared and operationControllers is invoked.
     */
    public void printOperations() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("---------------------------------------");
        System.out.println("Sorting Algortihms Application");
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println("Choose operation");
        System.out.println("1. Sort an array");
        System.out.println("2. Entere fileName");
        System.out.println("3. Print current fileName");
        System.out.println("4. Enter size of array");
        System.out.println("5. Print current array size");
        System.out.println("6. Print unsorted array");
        System.out.println("7. Print sorted array");
        System.out.println("8. Print number of repetions of sorting");
        System.out.println("9. Set number of repetitons of sorting");
        System.out.println("10. Exit");
        System.out.println("Enter a number related to operation");
       do {
            clearsError();
            System.out.print("> ");
            operation = handleInput(readData(), -1, "Provided input must be a number! Try again!");
            checkConditions(operation, 1, 10, "Undefined operation");
       } while(error);
       operationController();
    }

    /**
     * Controller for the main menu which invokes next operation according to input from the user 
     */
    private void operationController() {
        switch (operation) {
            case 1 -> printAviableAlgorithms();
            case 2 -> enterFileName(); 
            case 3 -> System.out.println("Current file name: " + fileName);
            case 4 -> enterArraySize(); 
            case 5 -> System.out.println("Current size of array: " + size);
            case 6 -> System.out.println(currentAlgorithm.printArray(currentAlgorithm.getUnsortedArray())); 
            case 7 -> System.out.println(currentAlgorithm.printArray(currentAlgorithm.getUnsortedArray())); 
            case 8 -> System.out.println("Current number of repetitions: " + numOfRepetition); 
            case 9 -> enterNumberOfRepetitions(); 
            case 10 -> App.exitApp(); 
        }
    }

    /** 
     * Prints available algorithms to use for sorting an array and take an input from the user. Method will read data unless all the condition will not be met. Clears error flags and invokes printSourceOfData().
     */
    private void printAviableAlgorithms() {
        System.out.println("Choose type of sort algorithm");
        System.out.println("1. Heap sorting algorithm");
        System.out.println("2. Insert sorting algorithm");
        System.out.println("3. Quick sorting algorithm");
        System.out.println("4. Shell sorting algorithm");
        System.out.println("Enter number related to sorting algorithm");
        do {
            clearsError();
            System.out.print("> " );
            algorithm = handleInput(readData(), -1, "Algorithm must relate to a number! Try again!");
            checkConditions(algorithm, 1, 4, "Undefinded sorting algorithm");
        }while(error);
        printSourcesOfData();
    }

    /** 
     * Prints availabe options for array input and take an input from the user. Metod will read data unless all the condition won't be met. Clears error flags and invoke sourceController.
     */
    private void printSourcesOfData() {
        System.out.println("Choose source of data for array");
        System.out.println("1. File");
        System.out.println("2. Generated");
        System.out.println("Enter number relevante to source of data");
        clearsError();
        System.out.print("> ");
        typeOfSource = handleInput(readData(), 2, "Provided input is not a number! Array data will be generated.");
        checkConditions(typeOfSource, 1, 3, "Undefine source of data");
        sourceController();
    }

    /**
     * Pritns additional options for input of array. If the input is correct, it will be proccessed. Clear error flags and invokes optionalVariableSource() if optionalVarialbe equals one (1).
     */
    private void printStateOfData() {
        System.out.println("Choose variable used for source");
        System.out.println("1. Enter new value");
        System.out.println("2. Use saved value");
        System.out.println("Enter number relevatn to variable for source");
        clearsError();
        System.out.print("> ");
        dataSource = handleInput(readData(), 2,"Provided input is not a number. Saved value was used!");
        checkConditions(dataSource, 1, 2, "Undefine source of data");
        if (dataSource == 1)
            optionVariableSource();
    }

    /*
     *  Controler for the additiona information which invokes method based on the source of data.
     */
    private void optionVariableSource() {
        switch (typeOfSource) {
            case 1 -> enterFileName(); 
            case 2 -> enterArraySize();
        } 
    }

    /**
     * Method informs user that additional informations must be provided and invokes method which will recieve those infomration. Method is used only with quick sort, because
     * this alogirhtm provides sorting for two type of data.
     */
    public void printAdditionalRequest() {
        System.out.println("To use quick sort algorithm you need to provide additional infomation");
        printTypeOfVariableQS();
        printPivotPlacmentQS();

    }


    /**
     *  Method displays types of data which are enabled for quick sort alogirthm. Method will read data unless the correct input isn't provided, checked by checkConditions. 
     *  After the input was provided error flags are clread. 
    */
    private void printTypeOfVariableQS() {
        System.out.println("Choose data type: ");
        System.out.println("1. Float");
        System.out.println("2. Int");
        System.out.println("Enter number relevatn to data type");
        clearsError();
        System.out.print("> ");
        qsDataType = handleInput(readData(), 2, "Provided input is not a number. Array will be generate for Integer");
        checkConditions(qsDataType, 1, 2, "Undefined data type of quick sort Alogorithm");
    }

    /**
     * Method dipslay option of pivot placemnt in quick sort algorithm. Metod will read data until the correct input is provided, check by checkConditions. 
     * If correct input was provided the error flag is cleraed.
     */
    private void printPivotPlacmentQS() {
        System.out.println("Choose pivot placment type: ");
        System.out.println("1. Left");
        System.out.println("2. Center");
        System.out.println("3. Right");
        System.out.println("Enter number relevatn to position of pivot");
        clearsError();
        System.out.print("> ");
        pivotPlacmentQS = handleInput(readData(), 1, "Provided input is not a number. Pivot will be set to the left side");
        checkConditions(pivotPlacmentQS, 1, 3, "Undefinded pivot placment");
    }

    /**
     * Method servies the array based on the type of source which was provided by the user. If the appropieate data is provided function will invoke printStateOfData, 
     * if not aproprite data must be providded by the user.
     */
    private void sourceController() {
        switch(typeOfSource) {
            case 1 -> {
                if (fileName == null) enterFileName();
                else printStateOfData(); 
            }
            case 2 -> {
                if (size <= 0)  enterArraySize();
                else printStateOfData();
            }
        }
    }

    /**
     * Method takes file name from user 
     */
    private void enterFileName() {
        do {
        System.out.println("Enter a file name");
        System.out.print("> ");
        fileName = readData();
        } while(fileName.equals(""));
    }

    /**
     * Method takes size of array from user. 
     */
    private void enterArraySize() {
        System.out.println("Enter a size of array");
        System.out.print("> ");
        size = handleInput(readData(), size, "Provided input must be a number! Size will remain the same as it was before");
    }

    /**
     * Method takes user input which will be interpreted as number of repetitions
     */
    private void enterNumberOfRepetitions(){
        System.out.println("Enter a number of repetitions");
        System.out.print("> ");
        setNumOfRepetions(handleInput(readData(), numOfRepetition, "Provided input must be a number! Number of repetition will remane the same"));
    }
    
    /**
     * Method reads data from standard input. In case of errors displays according message and returns deafult value
     * @return - returns data from standard input stream or deafult value which is -1
     */
    private String readData() {
        String input = null;
        try {
            while(input == null) {
                input = reader.readLine();
            }
            return input;
        } catch(Exception e) {
            System.out.println("Error! While reading data");
            error = true;
            return "-1";
        }
    }

    /**
     * Method checks if condition of input is met
     * @param valueToCheck - variable to check
     * @param minValue - minimum value of the variable
     * @param maxValue - maximum value of the variable
     * @param msg - message which is displayed in case of error
     */
    private void checkConditions(int valueToCheck, int minValue, int maxValue, String msg) {
        if ( valueToCheck < minValue || valueToCheck > maxValue) {
            System.out.println(msg);
            error = true;
        }
    }

    /**
     * Meathod clears error variable which is a flag
     */
    private void clearsError() {
        error = false;
    }

    /**
     *  Method closes the reader which read data from System.in 
     */
    public static void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error! Reader was not closed");
            e.printStackTrace();
        }
    }

    /**
     * Restores the default values of the object.
     */
    public void clearFlags() {
        algorithm = -1; 
        typeOfSource = -1;
        operation = -1;
    }

    /**
     *  Method clears terminal after pressing any key and flushed the buffor. 
     */
    public void clearTermianl() {
        System.out.println("Press any key to clear...");
        readData();
        System.out.print("\033[H\033[2J");  
        System.out.flush();     
    }

    public int getAlgorithm() {
        return algorithm;
    }

    public int getSourceTyep() {
        return typeOfSource;
    }

    public int getQSDataType() {
        return qsDataType;
    }

    public int getPivot() {
        return pivotPlacmentQS;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }

    public void setCurrentAlgorithm(SortClass alg) {
        if (alg != null)
            currentAlgorithm = alg;
    }
    
    public int getNumOfRepetitions() {
        return numOfRepetition;
    }
    public void setNumOfRepetions(int numberOfRepetitions) { 
        if (numberOfRepetitions > 1)
            numOfRepetition = numberOfRepetitions;
        else
            numOfRepetition = 1;
    }


    private int handleInput(String source, int defaultValue, String errorMsg) {
        try {
            return Integer.parseInt(source);
        } catch(NumberFormatException e) {
            System.out.println(errorMsg);
            error = true;
            return defaultValue;
        }
    }
}
