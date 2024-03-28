package sortAlgorithms;
import java.io.*;
import java.util.Random;

public abstract class SortClass {
    protected int size = 0;
    protected int[] array = null; 
    protected String inputDataFailName = "";
    protected long timeResult = 0;

    /**
     * Constructor which as a argument takes fileName from user, calls function to set variable of the object and read data from the file 
     * @param fileName - name of the file which from data should be read 
     */
    protected SortClass(String fileName) {
        setFileName(fileName);
        readDataFromFile();
    }

    /**
     * Constructor which as a argument takes fileName from user, calls function to set variable of the object and decided if data should be read from file.
     * This constructor is for compability parent class with desented QuicSort class. 
     * @param fileName - name of the file which from data should be read 
     * @param ifReadData - logical variable determing if data should be read genereted by desented QuickSort class.
     */
    protected SortClass(String filename, boolean ifReadData) {
        setFileName(filename);
        if (ifReadData) 
            readDataFromFile();
    }

    /**
    *  Constructor whicha as a agrument takes size of array, then sets the size, creates an array and generates data for it.
    * @param size
    */
    protected SortClass(int size) {
        setSize(size);
        array = createArray();
        generateDataForArray();
    }

    /**
    * Constructor whicha as a agrument takes size of array, then sets the size and decided if data should be generated for array.
    * This constructor is for compability parent class with desented QuicSort class. 
    * @param size - size of array
    * @param ifReadData - logical variable determing if data should be read genereted by desented QuickSort class.
    */
    protected SortClass(int size, boolean ifReadData) {
        setSize(size);
        if (ifReadData) {
            array = createArray();
            generateDataForArray();
        }
    }

    /**
     * Method is reading one line at the time and inserting that line into array if an array object exists. 
     * In case of errors during the file reading function displays the message and line which resulted in an error.
     * After finish of reading data function closes the stream to the file. 
     */
    private void readDataFromFile()  {
        BufferedReader reader = generatBufferedReader();
        int i = -1;
        try {
            for (; reader.ready() && i < size; i++) { 
                if (array == null) {
                    setSize(Integer.parseInt(reader.readLine()));
                    array = createArray();
                    i++;
                }
                array[i] = Integer.parseInt(reader.readLine());
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Problem with reading data from file");
            System.out.println("Reader has stoped on line number: " + i);
        } finally{
            closeStream(reader);
        }
    }

    /**
    *  Method responsible for closing the stream which as arguments takes an closeable object
    *  When problems occurse method is displaying statment about it and stream of class which was not closed
    * @param stream - opened stream in program
    */
    protected static void closeStream(Closeable stream) {
        try { 
           stream.close(); 
        } catch(IOException e) {
            System.out.println("Problem with closing stream");
            System.out.println("Stream type: " + stream.getClass());
        } 
    }

    /**
     * Method responsible for generating reader for a file
     * @return object of reader
     */
    protected BufferedReader generatBufferedReader() {
        try {
            return new BufferedReader(new FileReader(inputDataFailName));
        } catch(FileNotFoundException e) {
            return null;
        }
    }


    /**
     *  Method which initializes array variable of a object 
     * @return created array object
     */
    protected int[] createArray() {
        return new int[size];
    }

    /**
     * Method which fills the array with data if the array is not null
     */
    protected void generateDataForArray(){
        Random rand = new Random();
        if (array != null)
            for (int i = 0; i<size; i++) 
                array[i] = rand.nextInt();
    }

    /**
     * Method which enables swaping iteams between two indexes of an array
     * @param tab - array where swap should be done
     * @param left - lower index to swap
     * @param right - higher index to swap
     */
    protected void swap(int[] tab, int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    protected abstract void sortAlgorithm();

    /**
     * Base invoke of sorting. Every signle desentend class have diffrent implementation of sortAlogrithm().
     */
    public void sorting() {
        System.out.println("Array before sorting: " + array.toString()); 
        long startTime = System.currentTimeMillis(); 
        sortAlgorithm();
        long endTime = System.currentTimeMillis(); 
        System.out.println("Array after sorting: " +  array.toString());
        System.out.println("Time of sroting: " + (endTime-startTime));
    }

    protected int getSize() {
        return size;
    }

    protected void saveResults(){
        BufferedWriter writer = generaBufferedWriter() ; 
        try {
            writer.write("Time of sorting: " + timeResult + " ms");
        } catch (Exception e) {
            System.out.println("Line cound't be saved to file");
        } finally {
            closeStream(writer);
        }

    }

    private BufferedWriter generaBufferedWriter() {
        try  {
            return new BufferedWriter(new FileWriter("app\\src\\main\\resources"));
        } catch (Exception e) {
            System.out.println("Writrter generattor problem");
            return null;
        }
    }

    /**
     * Setter of size variable of the object which additional checking of argument
     * @param size - size provieded by the user
     */
    protected void setSize(int size) {
        if (size >= 1)
            this.size = size;
        else
            System.out.println("Niepoprawna wartosc");
    }

    protected String getFileName() {
        return inputDataFailName;
    }

    /**
     * Setter of fileName variable of the object which additional is checking for emtty string 
     * @param fileName - fileName provided by the user
     */
    protected void setFileName(String fileName) {
        if (!fileName.equals(""))
            inputDataFailName = fileName;
        else
           System.out.println("Empty file name"); 
    }
}
