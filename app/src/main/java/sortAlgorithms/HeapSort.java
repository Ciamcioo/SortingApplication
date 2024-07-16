package sortAlgorithms;

public class HeapSort extends SortClass {

    public HeapSort(int size) {
        super(size);
    }

    public HeapSort(String fileName) {
        super(fileName);
    }

    /**
     * Method which invokes buildHeap method and implements the algorithm of sorthing array with use of the heap
     */
    protected void sortAlgorithm() { 
        buildHeap();
        for (int i = array.length - 1; i >= 1; i--) {
            swap(0, i);
            fixHeapDown(0, array.length - i);
        }
    }

    /**
     * Method is generating the heap from the array using Rober Floyed algorithm. 
     */
    private void buildHeap() {
        for (int i = array.length/2-1; i>=0; i--) 
            fixHeapDown(i, 0);
    }

    /**
     * Method is fixing heap by goind down from rootOnInv to the moment when there is no more descentent of current root
     * @param rootOnInv - index from which fix should begin
     * @param readuction - variable which is used while heapSorting to not allow change of sorted memory cells
     */
    private void fixHeapDown(int rootOnInv, int readuction)  {
        int root = rootOnInv;
        int leftDescentent = root * 2 + 1, rightDescedant = root * 2 + 2;

        if (leftDescentent < array.length - readuction && array[root] < array[leftDescentent]) 
            root = leftDescentent;
        
        if (rightDescedant < array.length - readuction && array[root] < array[rightDescedant] ) 
            root = rightDescedant;

        if (root != rootOnInv) {
            swap(rootOnInv, root);
            fixHeapDown(root, readuction);
        }
    }
}
