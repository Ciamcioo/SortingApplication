package sortAlgorithms;

public class ShellSort extends SortClass {
    private int[] copieOfArray;

    public ShellSort(String fileName) throws Exception {
        super(fileName);
        copieOfArray = array;
    }

    public ShellSort(int size) {
        super(size);
        copieOfArray = array;
    }

    @Override
    public void sorting() {

    }
    @Override
    protected void sortAlgorithm() {

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
