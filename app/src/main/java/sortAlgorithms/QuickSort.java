package sortAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class QuickSort extends SortClass {
    private int pivot;
    private float[] floatArray;
    private String dataType;

    public QuickSort(int size, String pivot) {
        super(size);
        setPivot(pivot);
    }

    public QuickSort(String fileName, String pivot) throws Exception {
        super(fileName);
        setPivot(pivot);
    }

    /**
     * Constructor which additonaly enable user to set data type of array
     * @param size - size of array specified by user
     * @param typeOfData - array type specified by user
     * @param pivot  - point on pivot which should be use while sorting specified by user
    */
    public QuickSort(int size, String typeOfData, String pivot) {
        super(size, !typeOfData.equals("float"));
        if (typeOfData.equals("float")) {
            floatArray = new float[size];
            generateFloatsForArray();
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
     * @throws Exception
     */
    public QuickSort(String fileName, String typeOfData, String pivot) throws Exception {
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
    private int partitionOfArray(int left, int right) {
        int pivot = array[this.pivot], l = left, r = right;
        while (true) {
            while (pivot < this.array[r]) r--;
            while (pivot > this.array[l]) l++;
            if (l < r) {
                swap(array, r, l); 
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
        if (left >= right)  return;
        int pod = partitionOfArray(left, right);
        quikcSorting(left, pod);
        quikcSorting(pod+1, left);
    }

    public int getPivot() {
        return pivot;
    }

    public void setPivot(String pivot) {
        pivot = pivot.toLowerCase();
        switch (pivot) {
            case "center" -> this.pivot = size/2;
            case "right" -> this.pivot = size;
            default -> this.pivot = 0;
        }
    }

    public void setTypeOfData(String typeOfData) {
        dataType = typeOfData;
    }

    @Override
    protected void sortAlgorithm() {
        quikcSorting(0, size-1);
    }

}
