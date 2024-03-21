package sortAlgorithms;
import java.io.*;
import java.util.Random;

public abstract class SortClass {
    protected int size = 0;
    protected int[] array = null; 
    protected String inputDataFailName;
    protected FileOutputStream resultFile;

    /**
     * Constructor which as a argument take fileName from user, calls function to set variable of the object and read data from the file 
     * @param fileName - name of the file which from data should be read 
     * @throws Exception whenever the fileName is empty or file  
     * @throws FileNotFound whenever file which name was specified do not exist
     */
    protected SortClass(String fileName) throws Exception {
        setFileName(fileName);
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
     *  Method is reading data from file and inserting it into array as variable of next memory blocks 
     * @throws IOException
     */
    private void readDataFromFile()  {
        BufferedReader reader = generatBufferedReader();
        int i = -1;
        try {
            for (; reader.ready() && i < size; i++) { 
                if (array == null) {
                    setSize(Integer.parseInt(reader.readLine()));
                    array = createArray();
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

    private static void closeStream(Closeable stream) {
        try { 
           stream.close(); 
        } catch(IOException e) {
            System.out.println("Problem with closing stream");
            System.out.println("Stream type: " + stream.getClass());
        } 
    }

    private BufferedReader generatBufferedReader() {
        try {
            return new BufferedReader(new FileReader(inputDataFailName));
        } catch(FileNotFoundException e) {
            return null;
        }

    }
    protected abstract void sortAlgorithm(); 

    /**
     * Method allocates data for array     
     */
    protected int[] createArray() {
        return new int[size];
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
        return inputDataFailName;
    }

    protected void setFileName(String fileName) throws Exception {
        if (!fileName.equals(""))
            inputDataFailName = fileName;
        else
            throw new Exception("Empty file name");
    }
}
