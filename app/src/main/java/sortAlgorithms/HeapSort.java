package sortAlgorithms;

public class HeapSort extends SortClass {

    private HeapSort(int size) {
        super(size);
    }

    private HeapSort(String fileName) throws Exception {
        super(fileName);
    }

    public void sortAlgorithm() { 
        buildHeap();
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            fixHeapDown(0, array.length - i);
        }
    }

    private void buildHeap() {
        for (int i = array.length/2-1; i>=0; i--) 
            fixHeapDown(i, 0);
    }

    private void fixHeapDown(int rootOnInv, int readuction)  {
        int root = rootOnInv;
        int leftDescentent = root * 2 + 1, rightDescedant = root * 2 + 2;
        if (leftDescentent < array.length - readuction && array[root] < array[leftDescentent]) 
            root = leftDescentent;
        
        if (rightDescedant < array.length - readuction && array[root] < array[rightDescedant] ) 
            root = rightDescedant;

        if (root != rootOnInv) {
            swap(array, rootOnInv, root);
            fixHeapDown(root, readuction);
        }
    }
}
