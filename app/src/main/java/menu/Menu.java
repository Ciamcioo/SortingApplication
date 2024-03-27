package menu;
import java.io.*;


import main.App;
public class Menu {
    private static BufferedReader reader;
    private int algorithm = -1, typeOfSource = -1, operation = -1, size = -1, optionVariable = -1, qsDataType = -1, pivotPlacmentQS = -1  ;
    private String fileName = null;
    private boolean error = false;

    /**
     *  Prints main menu for sorting application and as an input takes number which will relate to next operations. Input will be read till the input will be correct. After that error variable 
     *  is clear and operationControllers is invoked.
     * @throws IOException
     */
    // TODO handles the exceptions
    public void printOperations() throws IOException {
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
       System.out.println("6. Exit");
       System.out.println("Enter a number related to operation");
       do {
            System.out.print("> ");
            operation =  Integer.parseInt(readData());
            checkConditions(operation, 1, 6, "Undefined operation");
       } while(error);
       clearsError();
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
            case 6 -> App.exitApp(); 
        }

    }

    /** 
     * Prints available algorithms to use for sorting an array and take an input from the user. Method will read data until all the condition will be met. Clears error
     * flags and invokes printSourceOfData().
     */
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
            checkConditions(algorithm, 1, 5, "Undefinded sorting algorithm");
        }while(error);
        clearsError();
        printSourcesOfData();
    }

    /** 
     * Prints availabe options for array input and take an input from the user. Metod will read data until all the condition will be met. Clears error flags and invoke sourceController.
     */
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

    /**
     * Pritns additional availabe options for input of array. Method will read data until all the condition will be met. Clear error flags and optionalVariableSource.
     */
    private void printStateOfData() {
        System.out.println("Choose variable used for source");
        System.out.println("1. Enter new value");
        System.out.println("2. Use saved value");
        System.out.println("Enter number relevatn to variable for source");
        do {
            System.out.print("> ");
            optionVariable = Integer.parseInt(readData());
            checkConditions(optionVariable, 1, 2, "Undefine option");
        }while(error);
        clearsError();
        optionVariableSource();
    }

    /**
     *  Controler for the additiona information
     */
    private void optionVariableSource() {
        if (optionVariable == 1) {
           switch (optionVariable) {
                case 1 -> enterFileName(); 
                case 2 -> enterArraySize();
           } 
        } 
        

    }

    /**
     * Method informs user that additional informations must be provided and invokes method which will recieve those infomration. Method is used only with quick sort, because
     * this alogirhtm provides sorting for two type of data
     */
    public void printAdditionalRequest() {
        System.out.println("To use quick sort algorithm you need to provide additional infomation");
        printTypeOfVariableQS();
        printPivotPlacmentQS();

    }


    /**
     *  Method displays types of data which are enabled for quick sort alogirthm. Method will read data until the correct input is provided, checked by checkConditions. After words error variable is cleared.
    */
    private void printTypeOfVariableQS() {
        System.out.println("Choose data type: ");
        System.out.println("1. Float");
        System.out.println("2. Int");
        System.out.println("Enter number relevatn to data type");
        do {
            System.out.print("> ");
            qsDataType = Integer.parseInt(readData()); 
            checkConditions(qsDataType, 1, 2, "Undefined data type");
        } while(error);
        clearsError();
    }

    /**
     * Method dipslay option of pivot placemnt in quick sort algorithm. Metod will read data until the correct input is provided, check by checkConditions. After words error variable is clear. 
     */
    private void printPivotPlacmentQS() {
        System.out.println("Choose pivot placment type: ");
        System.out.println("1. Left");
        System.out.println("2. Center");
        System.out.println("3. Right");
        System.out.println("Enter number relevatn to position of pivot");
        do {
            System.out.print("> ");
            pivotPlacmentQS = Integer.parseInt(readData()); 
            checkConditions(qsDataType, 1, 3, "Undefinded pivot placment");
        } while(error);
        clearsError();

    }
    /**
     * Method servies the array based on the type of source which was provided by the user. If the appropieate data is provided function will invoke printStateOfData if no aproprite data must be providded
     */
    private void sourceController() {
        switch(typeOfSource) {
            case 1 -> {
                if (fileName == null) enterFileName();
                else printStateOfData(); 
            }
            case 2 -> {
                if (size < 0)  enterArraySize();
                else printStateOfData();
            }
        }
    }

    /**
     * Method enables file name 
     */
    private void enterFileName() {
        System.out.println("Enter a file name");
        System.out.print("> ");
        fileName = readData();
    }

    /**
     * Method enables size of array 
     */
    private void enterArraySize() {
        System.out.println("Enter a size of array");
        System.out.print("> ");
        size = Integer.parseInt(readData());
    }
    
    /**
     * Method reads data from standard input in case of problem with reading data. Method displays according message and returns deafult value
     * @return - returns data from standard input stream or deafult value which is -1
     */
    private String readData() {
        try {
            return reader.readLine();
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
    /**
     * Method reset all varaible of the object
     */
    public void resetValues() {
        algorithm = -1;
        error = false;
    }

    /**
     *  Method clears terminal after pressing any key by inputing data to the stream and flushing it 
     */
    public void clearTermianl() {
        System.out.println("Press any key to clear...");
        readData();
        System.out.print("\033[H\033[2J");  
        System.out.flush();     
    }

    public String getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }
}

