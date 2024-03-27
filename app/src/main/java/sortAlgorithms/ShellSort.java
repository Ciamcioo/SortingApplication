package sortAlgorithms;

public class ShellSort extends SortClass {
    private int[] copieOfArray;

    public ShellSort(String fileName) {
        super(fileName);
        copieOfArray = array;
    }

    public ShellSort(int size) {
        super(size);
        copieOfArray = array;
    }

    /**
     * Method sorts both orginal and copy array and calculates the time of procedure with inputing to the user the time of both sorting and sorted array
     */
    @Override
    public void sorting() {
        System.out.println("Shell class has two implementations of calculating the algorithm's gap to show time differences between those two algorithms");
        System.out.println("Both arrays before sorting: " + array.toString()); 
        long startTime = System.currentTimeMillis();
        shellSortAlgorithm();
        long endTime = System.currentTimeMillis();
        System.out.println("Time of sorting with default gap in Shell alogorithm: " + (endTime - startTime));
        startTime = System.currentTimeMillis();
        hibbardSortAlgorithm();
        endTime = System.currentTimeMillis();
        System.out.println("Time of sorting with Hibbard gap in Shell algorithm:  " + (endTime - startTime));
        System.out.println("Both arrays after sorting: " + array.toString());
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
            iterationWithDefineGap(gap);
    }

    /**
     * Method implementing shell sroting algorithm with the gap invented by Hibbard
     */
    private void hibbardSortAlgorithm() {
        int gap = 0;
        for (int i = 2; gap != 1; i++) {
            gap = 2* (int) Math.abs(array.length/Math.pow(2, i)) + 1;
            iterationWithDefineGap(gap);
        }
    }

    /**
     * Method implementing sorthing during one iteration with predefined gap between memeory cells
     * @param gap - variable which determins the jump between memory cells
     */
    private void iterationWithDefineGap(int gap) {
        for (int i = array.length; i-gap >= 0; i--)
            if (array[i] < array[i-gap])
                swap(array, i-gap, i);
    }

    public int[] getCopierOfArray() {
        return copieOfArray;
    }

    public void setCopierOfArray(int[] copierOfArray) {
        this.copieOfArray = copierOfArray;
    }
}
