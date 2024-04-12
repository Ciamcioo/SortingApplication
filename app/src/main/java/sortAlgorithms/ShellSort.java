package sortAlgorithms;

public class ShellSort extends SortClass {
    private int[] copieOfArray;

    public ShellSort(String fileName) {
        super(fileName);
        copieOfArray = copyArray(array);
    }

    public ShellSort(int size) {
        super(size);
        copieOfArray = copyArray(array);
    }

    /**
     * Method sorts both orginal and copy array and calculates the time of procedure with inputing to the user the time of both sorting and sorted array
     */
    @Override
    public void sorting() {
        System.out.println("Shell class has two implementations of calculating the algorithm's gap to show time differences between those two algorithms");
        if (array.length < 2000)
            System.out.println("Both arrays before sorting: " + printArray(array)); 
        long startTime = System.currentTimeMillis();
        shellSortAlgorithm();
        long endTime = System.currentTimeMillis();
        System.out.println("Time of sorting with default gap in Shell alogorithm: " + (endTime - startTime) + " ms");
        array = copyArray(unsortedArray);
        startTime = System.currentTimeMillis();
        hibbardSortAlgorithm();
        endTime = System.currentTimeMillis();
        System.out.println("Time of sorting with Hibbard gap in Shell algorithm:  " + (endTime - startTime) + " ms");
        if (array.length < 2000)
            System.out.println("Both arrays after sorting: " + printArray(array));
    }

    /**
     *  Handels the method from the parent class, but due to options of sorting it is throwing exception
     */
    @Override
    protected void sortAlgorithm() {
        new RuntimeException("Unimplemented method");

    }

    /**
     * Method implementing shell sroting algorithm with the gap invented by Shell
     */
    private void shellSortAlgorithm() {
        for (int gap = array.length/2; gap > 0;gap/=2) 
            iterationWithDefineGap(array, gap);
    }

    /**
     * Method implementing shell sroting algorithm with the gap invented by Hibbard
     */
    private void hibbardSortAlgorithm() {
        int gap = 0;
        for (int i = 2; gap != 1; i++) {
            gap = 2* (int) Math.abs(array.length/Math.pow(2, i)) + 1;
            iterationWithDefineGap(copieOfArray, gap);
        }
    }

    /**
     * Method implementing sorthing during one iteration with predefined gap between memeory cells
     * @param gap - variable which determins the jump between memory cells
     */
    private void iterationWithDefineGap(int[] tab, int gap) {
        for (int i = tab.length-1; i-gap >= 0; i--)
            if (tab[i] < tab[i-gap])
                swap(i-gap, i);
    }
}
