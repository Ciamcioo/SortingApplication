package sortAlgorithms;
public class InsertSort  extends SortClass{
    protected InsertSort(String fileName) throws Exception {
        super(fileName);
    }

    protected InsertSort(int size) {
        super(size);
    }


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
