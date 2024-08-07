package sortAlgorithms;

import java.io.*;
import java.util.Random;

public abstract class SortClass {
    protected int size = 0, numberOfRepetitions = 1;
    protected int[] array = null, unsortedArray = null, sortedArray = null;
    protected String inputDataFileName;
    protected long timeResult = 0; 
    protected boolean fatalError = false;

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
        array = generateDataForArray(this.size);
    }

    /**
    * Constructor whicha as a agrument takes size of array, then sets the size and decided if data should be generated for array.
    * This constructor is for compability parent class with desented QuicSort class. 
    * @param size - size of array
    * @param ifReadData - logical variable determing if data should be read genereted by desented QuickSort class.
    */
    protected SortClass(int size, boolean ifReadData) {
        setSize(size);
        if (ifReadData) 
            array = generateDataForArray(this.size);
    }
    
    protected abstract void sortAlgorithm();

    /**
     * Method is reading one line at the time and inserting that line into array if an array object exists. 
     * In case of errors during the file reading function displays the message and line which resulted in an error.
     * After finish of reading data function closes the stream to the file. 
     */
    private void readDataFromFile()  {
        BufferedReader reader = generatBufferedReader();
        if (reader == null) 
            return;
        try {
            setSize(Integer.parseInt(reader.readLine()));
            array = new int[this.size];
            for (int i = 0; reader.ready() && i < size; i++) { 
                array[i] = Integer.parseInt(reader.readLine());
            }
        } 
        catch (NumberFormatException | IOException | NullPointerException e) {
            System.out.println("Problem with reading data from file");
        } 
        finally{
            closeStream(reader);
        }
    }

    /**
     * Method responsible for generating reader for a file. In case of execption returns null.
     * @return object of reader
     */
    protected BufferedReader generatBufferedReader() {
        try {
            return new BufferedReader(new FileReader(inputDataFileName));
        } catch(FileNotFoundException e) {
            return null;
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
        } catch(NullPointerException e) {
            System.out.println("Invalid input");
        } 
    }

    /**
     * Method creates and generates random ints for an array. 
     * @param size - size of created array
     * @return - created and genearted array
     */
    protected int[] generateDataForArray(int size){
        int[] array = new int[size];
        Random rand = new Random();
        if (array != null)
            for (int i = 0; i<size; i++) 
                array[i] = rand.nextInt();
        return array;
    }


    /**
     * Mathod is controling the process of array sorting.  It's handles all case senarios for possible sorting. A few  desendence class of SortClass needs to implement their own controller due to boraden class structure. 
     */
    public void sortingControler() {
        for (int i = 0; i < numberOfRepetitions; i++) {
            sortingArrayState();
            timeResult += timeMesurment(); 
            sortingArrayState();
            if (fatalError = sortErrorChecker(i))
                break;
            sortedArray = copyArray(array);
            dataPreparation();
        }

        if (!fatalError) {
            timeResult = Math.floorDiv(timeResult, numberOfRepetitions);
            System.out.println("Average time of sroting: " + timeResult + " ms for " + numberOfRepetitions + " number of repetitions");
        }
    }

    /**
     * Method prints small arrays before and after sorting. Small array is an array which meets the conditions belowe.
     */
    protected void sortingArrayState() {
        if (size < 2000 && numberOfRepetitions < 10) 
            System.out.println("Sorted array: " + printArray(array));
    }

    /**
     * Method reads current time in miliseconds between begining and ending of sorting algorithm. After substruction functions returns the time of sorting
     * @return - sorting time of algorithm
     */
    protected long timeMesurment() {
        long startTime = System.currentTimeMillis(); 
        sortAlgorithm();
        long endTime = System.currentTimeMillis();  
        return endTime-startTime;
    } 

    /**
     *  Method checks the result of sroting 
     * @param iteration - repetition of sorting 
     * @return - boolean value which defines if the algorithm was sorted corectly. If method returns true there was an error in sorting. In other case false is returned 
     */
    protected boolean sortErrorChecker(int iteration) {
        if (!errorAlgorithm())
            System.out.println("Incorect sorting repetition number: " + iteration);
        return !errorAlgorithm();
    }

    /**
     * Method implementing algorithm which iterates over array and checks if eleemnt of index i is smaller than the element of index i+1.
     * @return - return true if array was sorted correctly and false if there is a mistake.
     */
    protected boolean errorAlgorithm() {
        for (int i = 0; i < array.length - 1; i++)
            if (array[i] > array[i+1]) 
                return false;
        return true;   
    }

    /**
     * Method prepares object array variable and unsrotedArray varialbe to containe new random elements in case of generated numbers or copies the unsorted array to array that in next repetition array will contained unsorted values.
     */
    protected void dataPreparation() {
        if (this.inputDataFileName != null) 
            array = copyArray(unsortedArray);    
        else if (this.inputDataFileName == null) {
            array = generateDataForArray(size);
            unsortedArray = copyArray(array);
        }
    }

    /**
     *  Method which returns a string with array content which is easy to read. 
     * @return - string with the content of the array
     */
    public String printArray(int[] source) {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int element : source) {
            stringBuilder.append(element);
            stringBuilder.append(" ");
        } 
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String printSortedArray() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int element : sortedArray) {
            stringBuilder.append(element);
            stringBuilder.append(" ");
        } 
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Method copies the content of generated or loaded array to second array and returns the array
     * @return - returns newly created array with copie of content of the first array
     */
    protected int[] copyArray(int[] source) {
        if (!checkArrayState())
            return null;
        int[] array = new int[source.length];
        int i = 0;
        for (int element : source) {
            array[i] = element;
            i++; 
        }
        return array;
    }

    /**
     * Checks if array exists
     * @return - the result of boolean equation which check is array is null
     */
    public boolean checkArrayState() {
        return !(array == null);
    }

    /**
     * Method which enables swaping iteams between two indexes of an array
     * @param tab - array where swap should be done
     * @param left - lower index to swap
     * @param right - higher index to swap
     */
    protected void swap( int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    /**
     * Saves the results of sortig to the file using writer which is frislty generated. Once data has been entered, wrtier is closed.
     */
    public void saveResults(){
        BufferedWriter writer = generaBufferedWriter() ; 
        try {
            StringBuilder msg = new StringBuilder("Avrage time of sroting: ").append(timeResult).append(" ms for ").append(this.getClass()).append("Array size: ").append(this.size).append(", number of repetitions: ").append(this.numberOfRepetitions).append(" , source: ").append(this.inputDataFileName != null? inputDataFileName : "generated");
            writer.write(msg.toString());
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Line cound't be saved to file");
        } finally {
            closeStream(writer);
        }
    }

    /**
     * Genereates the writer for the file with specified location. Writer is in append mode so the content of the file won't be lost. In case of errors with writer, message will be displaied to the user and
     * method will return null.
     * @return - object of writer if there was no erros. In other case returns null.
     */
    protected BufferedWriter generaBufferedWriter() {
        try  {
            return new BufferedWriter(new FileWriter("E:\\workspace\\JavaPrograming\\SorthingApplication\\app\\src\\main\\resources\\result.txt", true));
        } catch (Exception e) {
            System.out.println("Writrter generattor problem");
            return null;
        }
    }

    /**
     * Method creates unsorted array based on the array. 
     */
    public void createUnsortedArray() {
        unsortedArray = copyArray(array); 
    }



/* GETTERS AND SETTERS */

    public int[] getArray() {
        return array;
    }

    public int[] getUnsortedArray() {
        return array;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    /**
     * Setter of size variable of the object which additional checking of argument. If size is not correct RuntimeExecption occurese.
     * @param size - size provieded by the user
     */
    protected void setSize(int size) {
        if (size >= 1)
            this.size = size;
        else
            new RuntimeException("Invalid size");
    }

    /**
     * Setter of fileName variable of the object which additional is checking for emtty string. If file name is not correct RuntimeException occurses. 
     * @param fileName - fileName provided by the user
     */
    protected void setFileName(String fileName) {
        if (!fileName.equals(""))
            inputDataFileName = fileName; 
        else
            new RuntimeException("Invalid fileName");
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        if (numberOfRepetitions > 1)
            this.numberOfRepetitions = numberOfRepetitions;
        else
            this.numberOfRepetitions = 1;
    }
}
