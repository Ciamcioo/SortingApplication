package sortAlgorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class sortClass {
    protected int size = 0;
    protected int[] array;
    protected String fileName;
    protected FileInputStream dataFile;

    /**
     * Constructor which as a argument take fileName from user, based on the fileName establishes conection with the fileName,
     * reads data from the file and closes the conections with the file. 
     * @param fileName - provided by the user
     * @throws Exception 
     */
    protected sortClass(String fileName) throws Exception {
        setFileName(fileName);
        setDataFile();
        readDataFromFile();
    }


    /**
    *  Constructor whicha as a agrument takes size of wanted array, creates the Array and generates data for it.
    * @param size
    */
    protected sortClass(int size) {
        setSize(size);
        createArray();
        generateDataForArray();
    }

    private void readDataFromFile() {
    }

    protected void createArray() {
        array = new int[size];
    }

    /**
     * Method which is responsible for generating data for table of set size
     */
    protected void generateDataForArray(){
    }

    protected int getSize() {
        return size;
    }

    protected void setSize(int size) {
        if (size > 1)
            this.size = size;
        else
            System.out.println("Niepoprawna wartosc");
    }

    protected String getFileName() {
        return fileName;
    }

    protected void setFileName(String fileName) throws Exception {
        if (!fileName.equals(""))
            this.fileName = fileName;
        else
            throw new Exception("Empty file name");
    }

    public FileInputStream getDataFile() {
        return dataFile;
    }

    public void setDataFile() throws FileNotFoundException {
        this.dataFile = new FileInputStream(fileName);
    }

    
}
