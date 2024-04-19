package sortAlgorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class QuickSort extends SortClass {

    private String typeOfData; 
    private int pivot;
    private float[] floatArray,  unsortedFloatArray, sortedFloatArray;
    private boolean stackOverFlow = false, fatalError = false;

    public QuickSort(int size, int pivot) {
        super(size);
        setPivot(pivot);
        setTypeOfData("int");
    }

    public QuickSort(String fileName, int pivot) {
        super(fileName);
        setPivot(pivot);
        setTypeOfData("int");
    }

    /**
     * Constructor which additonaly enable user to set data type of array
     * @param size - size of array specified by user
     * @param typeOfData - array type specified by user
     * @param pivot  - point on pivot which should be use while sorting specified by user
    */
    public QuickSort(int size, String typeOfData, int pivot) {
        super(size, !checkIfDataTypeFloat(typeOfData));
        setPivot(pivot);
        setTypeOfData(typeOfData);
        if (checkIfDataTypeFloat(typeOfData)) 
            floatArray = generateFloatsArray(size);
        else 
            printErrorMessage();
    }

    /**
     * Consturcot which additionaly take infomration from the user about the type of data contained in file.
     * @param fileName - name of file from which data should be read
     * @param typeOfData - type of data stored in file
     * @param pivot - point on pivot which should be use while sorting specified by user
     */
    public QuickSort(String fileName, String typeOfData, int pivot) {
        super(fileName, !checkIfDataTypeFloat(typeOfData));
        setTypeOfData(typeOfData); 
        setPivot(pivot);
        if (checkIfDataTypeFloat(typeOfData))  
            readFloatsFromFile();
        else 
            printErrorMessage();
    }

    /**
     * Method which reads the data from the file in case user specifies data type as float. Returns if the reader is null. Reader is null when the incorrect path was provided by the user. 
     * After the data was inserted the reader is closed.
     */
    private void readFloatsFromFile() {
        BufferedReader reader = generatBufferedReader();
        if (reader == null) 
            return;
        try {
            setSize(Integer.parseInt(reader.readLine()));
            floatArray =  new float[this.size];
            for (int i = 0; reader.ready() && i < this.size; i++)   
                floatArray[i] = Float.parseFloat(reader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("Problem with reading data from file");
        } finally{
            closeStream(reader);
        }
    }

    /**
     * Method prints the messages when unspecified data type occured in the program
     */
    private void printErrorMessage() {
        System.out.println("Type doesn't supported");
        System.out.println("Generation for default type was invoked");
    }

    /**
     * Method generates random float data and insert it into the float array
     */
    private float[] generateFloatsArray(int size) {
        float[] array =  new float[size];
        Random rand_float = new Random();
        Random rand_int = new Random();
        for (int i = 0; i < array.length; i++) 
            array[i] = rand_int.nextInt(100) +  rand_float.nextFloat(); 
        return array;
    }

    /**
     * Method which calculates the pivot based on the pivot location specified by the user. Overides the value specified by the user.
     * @param left - first index of array/subarray
     * @param right - last index of array/subarray
     */
    private void calculatePivot(int left, int right) {
       switch (pivot) {
        case 1 -> pivot = left;
        case 2 -> pivot = (left+right)/2;
        default -> pivot = right;
       }
    }

    /**
     * Method parttions the array on two subarrays where the left subarray contains numbers smaller than pivot. Whereas the right array contains numbers biger than the pivot.
     * @param left - first index of subbaray
     * @param right - last index of subbaray
     * @return - Index where subbarays contact. Inclusivly for the smaller than pivot subarray and exclusivly for biger than pivot subarray.
     */
    private int partitionOfIntArray(int left, int right) {
        calculatePivot(left, right);
        int pivot = array[this.pivot], l = left, r = right;
        while (true) {
            while (pivot > this.array[l]) l++;
            while (pivot < this.array[r]) r--;
            if (l < r) {
                swap(l,r); 
                r--;
                l++;
            }
            else {
                if (r == right) r--;
                return r;
            }
        }
    }

    /**
     * Method parttions the float array on two float subarrays where the left subarray contains numbers smaller than pivot. Whereas the right array contains numbers biger than the pivot.
     * @param left - first index of subbaray
     * @param right - last index of subbaray
     * @return - Index where subbarays contact. Inclusivly for the smaller than pivot subarray and exclusivly for biger than pivot subarray.
     */
    private int partitionOfFloatArray(int left, int right){
        calculatePivot(left, right);
        float pivot = floatArray[this.pivot];
        int l = left, r = right;
        while (true) {
            while (pivot > this.floatArray[l]) l++;
            while (pivot < this.floatArray[r]) r--;
            if (l < r) {
                swap(l,r); 
                r--;
                l++;
            }
            else {
                if (r == right) r--;
                return r;
            }
        }
    }

    /**
     * Contains recursive quickSort alogorihtm for both type of data of object. 
     * @param left - first index of array 
     * @param right - last index of array
     */
    private void quikcSorting(int left, int right) {
        try {
            int pod;
            if (left >= right)  return;
            if (checkIfDataTypeFloat(this.typeOfData))
                pod = partitionOfFloatArray(left, right);
            else  
                pod = partitionOfIntArray(left, right);
            quikcSorting(left, pod);
            quikcSorting(pod+1, right);
            return;
        } catch (StackOverflowError e) {
            if (!stackOverFlow) 
                stackOverFlow = !stackOverFlow;
            return;
        }
    }

    /**
     * Implements handling swap method for both types of data of object.
     */
    @Override
    protected void swap(int left, int right) {
        if (checkIfDataTypeFloat(this.typeOfData)){
            float tmp = floatArray[right];
            floatArray[right] = floatArray[left];
            floatArray[left] = tmp;
        }
        else {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }
    }

    /**
     * Method implemeneting srotingControler for QuikcSort class. Enabling to mesure time of sorting and chack all types of error conected with this type of sorting
     */
    @Override
    public void sortingControler() {
        for (int i = 0; i < numberOfRepetitions; i++) {
            sortingArrayState();
            timeResult += timeMesurment(); 
            sortingArrayState();
            if (fatalError = checkErrors(i)) 
                break;
            sortedArrayCopy();
            dataPreparation();
        }

        if (!fatalError) {
            timeResult = Math.ceilDiv(timeResult, numberOfRepetitions);
            System.out.println("Average time of sroting: " + timeResult + " ms for " + numberOfRepetitions + " number of repetitions");
        }
    }

    /**
     * Invokes the quickSorting algorithm.
     */
    @Override
    protected void sortAlgorithm() {
        quikcSorting(0, size-1);
    }

    /**
     * Method checks if stacOverflow excpetion occured during sorting and if data was sorted correctly
     * @param repetition - current repetition of sorting
     * @return - returns true if error occured, false if not
     */
    private boolean checkErrors(int repetition) {
        if (stackOverFlow) {
            System.out.println("Exception stack overflow occured");
            return true;
        }
        else if (sortErrorChecker(repetition)) { 
            System.out.println("Array was not sorted correctly");
            return true;
        }
        else 
            return false;
       
    }

    @Override
    protected boolean sortErrorChecker(int iteration) {
        boolean result;
        if (checkIfDataTypeFloat(this.typeOfData)) {
            result = !errorFloatAlgorithm();
            if (result)
                System.out.println("Incorect sorting repetition number: " + iteration);
            return result;
        }
        else {
            result = !errorAlgorithm();
            if (result)
                System.out.println("Incorect sorting repetition number: " + iteration);
            return result;
        }
    }

    private boolean errorFloatAlgorithm() {
        for (int i = 0; i < floatArray.length-1; i++) 
            if (floatArray[i] > floatArray[i+1])
                return false;
        return true; 
    }

    protected boolean errorAlgorithm() {
        if (checkIfDataTypeFloat(this.typeOfData)) {
            for (int i = 0; i < floatArray.length-1; i++)
                if (floatArray[i] > floatArray[i+1])
                    return false;
            return true;
        }
        else {
            for (int i = 0; i < array.length-1; i++)
                if (array[i] > array[i+1])
                    return false;
            return true;
        }
    }
    
    private void sortedArrayCopy() {
        if (checkIfDataTypeFloat(this.typeOfData)) 
            sortedFloatArray = copyFloatArray(floatArray);           
        else 
            sortedArray = copyArray(array);
    }

    /**
     * Method prepares data to be sorted, based on the type of array and the source of numbers. 
     */
    @Override
    protected void dataPreparation() {
       if (checkIfDataTypeFloat(this.typeOfData)) {
            if (this.inputDataFileName != null )
                floatArray = copyFloatArray(unsortedFloatArray);
            else
                floatArray = generateFloatsArray(this.size);
       } 
       else {
            if (this.inputDataFileName != null) 
                array = copyArray(unsortedArray);
            else
                array = generateDataForArray(this.size);
       }
    }

    /**
     * Checks if array is not null for type specified for object. Overriden from the SortClass class.
     */
    @Override
    public boolean checkArrayState() {
        if (checkIfDataTypeFloat(this.typeOfData))
            return !(floatArray == null);
        else 
            return !(array == null);
    } 

    /**
     * Method prints array based on type specified for object.
     */
    @Override
    public String printArray(int[] sourceArray) {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (checkIfDataTypeFloat(this.typeOfData)) 
            for (float element : floatArray)
                stringBuilder.append(element).append(" ");
        else 
            for (int element : sourceArray) 
                stringBuilder.append(element).append(" ");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     *  Method copies generated/loaded/saved source float array to new float array 
     * @param source - float array from which data will be read
     * @return - returns new float array which is copie of source array
     */
    private float[] copyFloatArray(float[] source) {
        if (!checkArrayState())
            return null; 
        float[] floatArray = new float[this.size];
        for (int i = 0; i < source.length; i++) 
            floatArray[i] = source[i];
        return floatArray; 
    }

    /**
     * Copies array to unsorted array based on its type
     */
    @Override
    public void createUnsortedArray() {
        if (checkIfDataTypeFloat(this.typeOfData))
            unsortedFloatArray = copyFloatArray(floatArray);
        else
            unsortedArray = copyArray(array); 
    }

    /**
     * Method check if the string passed as an argumetn is "float", which indicates that data type of object should be float.
     * @param typeOfData - string which determins type of data of object
     * @return - return true if string is flaot so data type of object should be float, returns false if string isn't float 
     */
    private static boolean checkIfDataTypeFloat(String typeOfData) {
        return typeOfData.equals("float");
    }

    /**
     * Method saves time of sorting into the result.txt file.
     */
    public void saveResults() {
        BufferedWriter writer = generaBufferedWriter() ; 
        try {
            StringBuilder msg = new StringBuilder("Avrage time of sroting: ").append(timeResult).append(" ms for ").append(this.getClass()).append("Array size: ").append(this.size).append(", number of repetitions: ").append(this.numberOfRepetitions).append(" , source: ").append(this.inputDataFileName != null ? inputDataFileName : "generated").append(" type of data: ").append(this.typeOfData);
            writer.write(msg.toString());
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Line cound't be saved to file");
        } finally {
            closeStream(writer);
        }
    }

    @Override
    public String printSortedArray() {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (checkIfDataTypeFloat(this.typeOfData))
            for (float element : sortedFloatArray) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            } 
        else
            for (int element : sortedArray) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

/* SETTERS */

    public void setPivot(int  pivot) {
        this.pivot = pivot;
    }

    public void setTypeOfData(String typeOfData) {
        if (checkIfDataTypeFloat(typeOfData))
            this.typeOfData = "float";
        else
            this.typeOfData = "int";
    }
}

