package sortAlgorithms;

public class ShellSort extends SortClass {
    private int[] copieOfArray;

    protected ShellSort(String fileName) throws Exception {
        super(fileName);
        copieOfArray = array;
    }

    protected ShellSort(int size) {
        super(size);
        copieOfArray = array;
    }

    private void shellSortAlgorithm() {
        for (int gap = array.length/2; gap > 0;gap/=2) 
            iterationWithDefineGap(gap);
    }

    private void hibbardSortAlgorithm() {
        int gap = 0;
        for (int i = 2; gap != 1; i++) {
            gap = 2* (int) Math.abs(array.length/Math.pow(2, i)) + 1;
            iterationWithDefineGap(gap);
        }
    }

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
