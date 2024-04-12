package sortAlgorithms;

import java.io.BufferedWriter;

public class ShellSort extends SortClass {
    private long shellResultTime, hibbardResultTime;

    public ShellSort(String fileName) {
        super(fileName);
    }

    public ShellSort(int size) {
        super(size);
    }

    /**
     * Method sorts both orginal and copy array and calculates the time of procedure with inputing to the user the time of both sorting and sorted array
     */
    @Override
    public void sorting() {
        System.out.println("Shell class has two implementations of calculating the algorithm's gap to show time differences between those two algorithms");
        if (array.length < 2000 || this.numberOfRepetitions > 10)
            System.out.println("Both arrays before sorting: " + printArray(array)); 
        sortAlgorithm();
        System.out.println("Time of sorting with default gap in Shell alogorithm: " + shellResultTime + " ms");
        System.out.println("Time of sorting with Hibbard gap in Shell algorithm:  " + hibbardResultTime + " ms");
        if (array.length < 2000 || this.numberOfRepetitions > 10)
            System.out.println("Both arrays after sorting: " + printArray(array));
    }

    /**
     *  Handels the method from the parent class, but due to options of sorting it is throwing exception
     */
    @Override
    protected void sortAlgorithm() {
        int i = 0, gap = 0;
        for (; i < this.numberOfRepetitions; i++) {
            long startTime = System.currentTimeMillis();
            for (gap = array.length/2; gap > 0; gap/=2)
                iterationWithDefineGap(array, gap);
            long endTime = System.currentTimeMillis();
            this.shellResultTime += (endTime-startTime);
            array = copyArray(unsortedArray);
            gap = 0;
            for (i = 2; gap != 1; i++) {
                gap = 2* (int) Math.abs(array.length/Math.pow(2, i)) + 1;
                iterationWithDefineGap(array, gap);
            }
        }
        shellResultTime = Math.ceilDiv(shellResultTime, numberOfRepetitions);
        hibbardResultTime = Math.ceilDiv(hibbardResultTime, numberOfRepetitions);
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

    @Override
    public void saveResults() {
        BufferedWriter writer = generaBufferedWriter() ; 
        try {
            writer.write("Avrage time of sroting: " + shellResultTime + " ms for " + numberOfRepetitions + " number of repetitions for Shell Alogorithm of " + this.getClass());
            writer.newLine();
            writer.write("Avrage time of sroting: " + hibbardResultTime+ " ms for " + numberOfRepetitions + " number of repetitions for Hibard Alogorithm of " + this.getClass());
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Line cound't be saved to file");
        } finally {
            closeStream(writer);
        }
    }
}
