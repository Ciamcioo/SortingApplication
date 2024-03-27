package sortAlgorithms;
public class InsertSort  extends SortClass{
    public InsertSort(String fileName) {
        super(fileName);
    }

    public InsertSort(int size) {
        super(size);
    }

    
    /** 
     *  Method which implements the alogrithm of insertion sort.
    */
    protected void sortAlgorithm() {
        for (int i = 1; i < array.length; i++) {
            int key = array[i], j = i;
            while (array[j-1] > key && j > 0) {
                array[j] = array[j-1];
            }
            array[j] = key;
        }
    }


}
