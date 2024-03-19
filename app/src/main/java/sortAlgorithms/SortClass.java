package sortAlgorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public abstract class SortClass {
    protected int size = 0;
    protected int[] array;
    protected String fileName;
    protected FileReader dataFile;
    protected FileOutputStream resultFile;

    /**
     * Constructor which as a argument take fileName from user, based on the fileName establishes conection with the fileName,
     * reads data from the file and closes the conections with the file. 
     * @param fileName - provided by the user
     * @throws Exception 
     */
    protected SortClass(String fileName) throws Exception {
        setFileName(fileName);
        setDataFile();
        readDataFromFile();
    }


    /**
    *  Constructor whicha as a agrument takes size of wanted array, creates the Array and generates data for it.
    * @param size
    */
    protected SortClass(int size) {
        setSize(size);
        createArray();
        generateDataForArray();
    }

    /**
     *  Method is reading the data from file and inserting it into array variable
     * @throws IOException
     */
    private void readDataFromFile() throws IOException {
        BufferedReader reader  = new BufferedReader(dataFile);
        setSize(Integer.parseInt(reader.readLine()));
        createArray();
        for (int i=0; reader.ready() && i < size; i++) 
            array[i] = Integer.parseInt(reader.readLine());
        reader.close();
    }

    protected abstract void sortAlgorithm(); 

    /**
     * Method allocates data for array     
     */
    protected void createArray() {
        array = new int[size];
    }

    /**
     * Method which is responsible for generating data for table of set size
     */
    protected void generateDataForArray(){
        Random rand = new Random();
        for (int i = 0; i<size; i++) 
            array[i] = rand.nextInt();
        

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

    public FileReader getDataFile() {
        return dataFile;
    }

    public void setDataFile() throws FileNotFoundException {
        this.dataFile = new FileReader(fileName);
    }

    
}
