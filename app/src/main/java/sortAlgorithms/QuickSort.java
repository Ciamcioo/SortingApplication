package sortAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class QuickSort extends SortClass {
    private int pivot;
    private float[] floatArray;
    private String typeOfData;

    public QuickSort(int size, String pivot) {
        super(size);
    }

    public QuickSort(String fileName, String pivot) throws Exception {
        super(fileName);
    }

    public QuickSort(int size, String typeOfData, String pivot) {
        super(size, !typeOfData.equals("float"));
        if (typeOfData.equals("float")) {
            floatArray = new float[size];
            generateFloatsForArray();
        } 
        else 
            printErrorMessage();
        
    }

    public QuickSort(String fileName, String typeOfData, String pivot) throws Exception {
        super(fileName, !typeOfData.equals("float"));
        if (typeOfData.equals("float")) 
            readFloatsFromFile();
        else 
            printErrorMessage();
        setPivot(pivot);
    }

    private void readFloatsFromFile() {
        BufferedReader reader = generatBufferedReader();
        int i = -1;
        try {
            for (; reader.ready() && i < size; i++) { 
                if (array == null) {
                    setSize(Integer.parseInt(reader.readLine()));
                    floatArray = generateFloatArray();
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

    private float[] generateFloatArray() {
        return new float[size];
    }

    private void printErrorMessage() {
        System.out.println("Type doesn't supported");
        System.out.println("Generation for default type was invoked");
    }

    private void generateFloatsForArray() {
        Random rand_float = new Random();
        Random rand_int = new Random();
        for (int i = 0; i < floatArray.length; i++) 
            floatArray[i] = rand_int.nextInt() + rand_float.nextFloat(); 
    }

    private int partitionOfArray(int left, int right) {
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

    private void swap(int left, int right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public void sortAlgorithms(int left, int right) {
        if (left >= right)  return;
        int pod = partitionOfArray(left, right);
        sortAlgorithms(left, pod);
        sortAlgorithms(pod+1, left);
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

    public String getTypeOfData() {
        return typeOfData;
    }

    public void setTypeOfData(String typeOfData) {
        this.typeOfData = typeOfData;
    }
}
