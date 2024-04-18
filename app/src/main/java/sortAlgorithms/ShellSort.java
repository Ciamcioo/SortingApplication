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
    public void sortingControler() {
        System.out.println("Shell class has two implementations of calculating the algorithm's gap to show time differences between those two algorithms");
        sortingArrayState();
        sortAlgorithm();
        shellResultTime = Math.ceilDiv(shellResultTime, numberOfRepetitions);
        hibbardResultTime = Math.ceilDiv(hibbardResultTime, numberOfRepetitions);
        sortingArrayState();


        System.out.println("Time of sorting with default gap in Shell algorithm: " + shellResultTime + " ms");
        System.out.println("Time of sorting with Hibbard gap in hibrad algorithm:  " + hibbardResultTime + " ms");
    }

    /**
     *  Handels the method from the parent class, but due to options of sorting it is throwing exception
     */
    @Override
    protected void sortAlgorithm() {
        for (int i = 0; i < this.numberOfRepetitions; i++) {
            long startTime = System.currentTimeMillis();
            shellAlgorithm();
            long endTime = System.currentTimeMillis();
            this.shellResultTime += (endTime-startTime);
            if (sortErrorChecker(i))
                break;
            array = copyArray(unsortedArray);
            startTime = System.currentTimeMillis();
            hibardAlogrithm();
            endTime = System.currentTimeMillis();
            this.hibbardResultTime += (endTime - startTime);
            if (sortErrorChecker(i))
                break;
        }
    }

    /**
     * Method implements calculations of next intervals in Shell Algorithm with gap calcualted by Mr. Shell 
     */
    private void shellAlgorithm() {
        for (int gap = array.length/2; gap > 0; gap/=2) 
            array = iterationWithDefineGap(array, gap);
        
    }

    /**
     * Method implements calculations of next intervals in Shell Algorithm with gap calcualted by Mr. Hibard
     */
    private void hibardAlogrithm() {
        int gap = 0;
        for (int i = 2; gap != 1; i++) {
            gap = 2* (int) Math.abs(array.length/Math.pow(2, i)) + 1;
            array = iterationWithDefineGap(array, gap);
        }
    }

    /**
     * Method implementing sorthing during one iteration with predefined gap between memeory cells
     * @param gap - variable which determins the jump between memory cells
     */
    private int[] iterationWithDefineGap(int[] tab, int gap) {
        for (int i = gap; i < tab.length; i++) {
            int tmp = tab[i];
            int j;
            for (j = i; j >= gap && array[j-gap] > tmp; j-= gap)
                array[j] = array[j-gap];
            tab[j] = tmp;
        }
        return array;
    }

    /**
     *  Method saves the results of Shell sorting to the file - result.txt. If an exception happens method handles it. No matter the result of try-catch block writer is closed.
     */
    @Override
    public void saveResults() {
        BufferedWriter writer = generaBufferedWriter() ; 
        try {
            StringBuilder msg = new StringBuilder("Avrage time of sroting: ").append(shellResultTime).append(" for ").append(this.getClass()).append(" - Shell interval. Array size: ").append(this.size).append(", number of repetitions: ").append(this.numberOfRepetitions).append(" , source: ").append(this.inputDataFileName != null ? inputDataFileName : "generated");
            writer.write(msg.toString());
            writer.newLine();
            msg = new StringBuilder("Avrage time of sroting: ").append(hibbardResultTime).append(" for ").append(this.getClass()).append(" - Hibard interval. Array size: ").append(this.size).append(", number of repetitions: ").append(this.numberOfRepetitions).append(" , source: ").append(this.inputDataFileName != null ? inputDataFileName : "generated");
            writer.write(msg.toString());
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Line cound't be saved to file");
        } finally {
            closeStream(writer);
        }
    }
}
