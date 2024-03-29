package sortAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
public class QuickSort extends SortClass {
    private String typeOfData;
    private int pivot;
    private float[] floatArray;

    public QuickSort(int size, int pivot) {
        super(size);
        setPivot(pivot);
    }

    public QuickSort(String fileName, int pivot) {
        super(fileName);
        setPivot(pivot);
    }

    /**
     * Constructor which additonaly enable user to set data type of array
     * @param size - size of array specified by user
     * @param typeOfData - array type specified by user
     * @param pivot  - point on pivot which should be use while sorting specified by user
    */
    public QuickSort(int size, String typeOfData, int pivot) {
        super(size, !typeOfData.equals("float"));
        if (array != null)
            this.typeOfData = typeOfData;
        if (typeOfData.equals("float")) {
            floatArray = new float[size];
            generateFloatsForArray();
            this.typeOfData  = typeOfData;
        } 
        else 
            printErrorMessage();
        setPivot(pivot);

        
    }

    /**
     * Consturcot which additionaly take infomration from the user about the type of data contained in file.
     * @param fileName - name of file from which data should be read
     * @param typeOfData - type of data stored in file
     * @param pivot - point on pivot which should be use while sorting specified by user
     */
    public QuickSort(String fileName, String typeOfData, int pivot) {
        super(fileName, !typeOfData.equals("float"));
        if (typeOfData.equals("float")) 
            readFloatsFromFile();
        else 
            printErrorMessage();
        setPivot(pivot);
    }

    /**
     * Method which reads the data from the file in case user specifies data type as float
     */
    private void readFloatsFromFile() {
        BufferedReader reader = generatBufferedReader();
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
            floatArray[i] = rand_int.nextInt() + rand_float.nextFloat(); 
    }

    /**
     * Method parttions the array on two subarrays where the left subarray contains numbers smaller than pivot. Whereas the right array contains numbers biger than the pivot.
     * @param left - first index of subbaray
     * @param right - last index of subbaray
     * @return - index where subbarays touches. Inclusivly for the smaller than pivot subarray and exclusivly for biger than pivot subarray.
     */
    private int partitionOfIntArray(int left, int right) {

        int pivot = array[this.pivot], l = left, r = right;
        while (true) {
            while (pivot < this.array[r]) r--;
            while (pivot > this.array[l]) l++;
            if (l < r) {
                swap(r, l); 
                r--;
                l++;
            }
            else {
                if (r == right) r--;
                return r;
            }
        }
    }
    private int partitionOfFloatArray(int left, int right){

        float pivot = floatArray[this.pivot];
        int l = left, r = right;
        while (true) {
            while (pivot < this.floatArray[r]) r--;
            while (pivot > this.floatArray[l]) l++;
            if (l < r) {
                swap(r, l); 
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
     * Method which contains the quickSort alogorihtm 
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
        quikcSorting(pod+1, left);
    }
    /**
     * Implements handling swap method for both types of data
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
     * Invokes the quickSorting algorithm  
     */
    @Override
    protected void sortAlgorithm() {
        quikcSorting(0, size-1);
    }

    /**
     * Print prints array based on type
     */
    @Override
    protected String printArray() {
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

    public int getPivot() {
        return pivot;
    }

    public void setPivot(int  pivot) {
        switch (pivot) {
            case 1 -> this.pivot = 0;
            case 2 -> this.pivot = size/2;
            default -> this.pivot = size - 1;
        }
    }

}
