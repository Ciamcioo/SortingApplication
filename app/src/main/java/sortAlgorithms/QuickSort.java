package sortAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class QuickSort extends SortClass {

    private String typeOfData;
    private int pivot;
    private float[] floatArray;
    private float[] unsortedFloatArray;

    public QuickSort(int size, int pivot) {
        super(size);
        setPivot(pivot);
        typeOfData = "int";
    }

    public QuickSort(String fileName, int pivot) {
        super(fileName);
        setPivot(pivot);
        typeOfData = "int";
    }

    /**
     * Constructor which additonaly enable user to set data type of array
     * @param size - size of array specified by user
     * @param typeOfData - array type specified by user
     * @param pivot  - point on pivot which should be use while sorting specified by user
    */
    public QuickSort(int size, String typeOfData, int pivot) {
        super(size, !typeOfData.equals("float"));
        if (typeOfData.equals("float")) {
            floatArray = new float[size];
            generateFloatsForArray();
            unsortedFloatArray = copyFloatArray(floatArray);
        } 
        else 
            printErrorMessage();
        setPivot(pivot);
        this.typeOfData = typeOfData;
    }

    /**
     * Consturcot which additionaly take infomration from the user about the type of data contained in file.
     * @param fileName - name of file from which data should be read
     * @param typeOfData - type of data stored in file
     * @param pivot - point on pivot which should be use while sorting specified by user
     */
    public QuickSort(String fileName, String typeOfData, int pivot) {
        super(fileName, !typeOfData.equals("float"));
        if (typeOfData.equals("float")) { 
            readFloatsFromFile();
            unsortedFloatArray = copyFloatArray(floatArray);
        }
        else 
            printErrorMessage();
        setPivot(pivot);
        this.typeOfData = typeOfData;
    }

    /**
     * Method which reads the data from the file in case user specifies data type as float. Returns if the reader is null. Reader is null when the incorrect path was provided by the user. 
     * After the data was inserted the reader is closed.
     */
    private void readFloatsFromFile() {
        BufferedReader reader = generatBufferedReader();
        if (reader == null) 
            return;
        int i = -1;
        try {
            for (; reader.ready() && i < size; i++) { 
                if (floatArray == null) {
                    setSize(Integer.parseInt(reader.readLine()));
                    floatArray = generateFloatArray();
                    i++;
                }
                floatArray[i] = Float.parseFloat(reader.readLine());
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Problem with reading data from file");
            System.out.println("Reader has stoped on line number: " + i);
        } finally{
            closeStream(reader);
        }
    }

    /**
     * Method which genrates float array in case user specifies data type as float  
     * @return - float array of specific size
     */
    private float[] generateFloatArray() {
        return new float[size];
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
    private void generateFloatsForArray() {
        Random rand_float = new Random();
        Random rand_int = new Random();
        for (int i = 0; i < floatArray.length; i++) 
            floatArray[i] = rand_int.nextInt(100) +  rand_float.nextFloat(); 
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
        int pod;
        if (left >= right)  return;
        if (typeOfData.equals("float")) 
            pod = partitionOfFloatArray(left, right);
        else  
            pod = partitionOfIntArray(left, right);
        quikcSorting(left, pod);
        quikcSorting(pod+1, right);
    }

    /**
     * Implements handling swap method for both types of data of object.
     */
    @Override
    protected void swap(int left, int right) {
        if (typeOfData.equals("float")) {
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
     * Invokes the quickSorting algorithm. Additional to check if type of array is float.   
     */
    @Override
    protected void sortAlgorithm() {
        quikcSorting(0, size-1);
        if (floatArray != null)
            floatArray = copyFloatArray(unsortedFloatArray);
    }

    /**
     * Checks if array is not null for type specified for object. Overriden from the SortClass class.
     */
    @Override
    public boolean checkArrayState() {
        if (typeOfData.equals("float"))
            return !(floatArray == null);
        else 
            return !(array == null);
    } 

    /**
     *  Method prints unsorted array based on type specified for the object
     */
    @Override
    public String printUnsoretedArray() {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (typeOfData.equals("float")) {
            for (float element : unsortedFloatArray) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            }
        }
        else 
            for (int element : unsortedArray) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            } 
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Method prints array based on type specified for object.
     */
    @Override
    public String printArray() {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (typeOfData.equals("float")) {
            for (float element : floatArray) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            }
        }
        else 
            for (int element : array) {
                stringBuilder.append(element);
                stringBuilder.append(" ");
            } 
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     *  Method copies generated/loaded/saved source float array to new float array 
     * @param source - float array from which data will be read
     * @return - returns new float array which is copie of source array
     */
    private float[] copyFloatArray(float[] source) {
        float[] floatArray = new float[this.floatArray.length];
        int i = 0;
        for (float element : this.floatArray) {
            floatArray[i] = element;
            i++;
        }
        return floatArray; 
    }

    public void setPivot(int  pivot) {
        this.pivot = pivot;
    }
}
