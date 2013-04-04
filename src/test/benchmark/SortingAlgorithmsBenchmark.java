package benchmark;

import com.google.caliper.SimpleBenchmark;
import sorting.MergeSort;
import sorting.ShellSort;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithmsBenchmark extends SimpleBenchmark {

    private static final int SIZE = 100000;
    private static final int MAX_VALUE = 100000;
    private int[] values;

    @Override
    protected void setUp() throws Exception {
        values = new int[SIZE];
        Random generator = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = generator.nextInt(MAX_VALUE);
        }
    }

    public void timeShellSort(int reps) {
        for (int i = 0; i < reps; i++) {
            ShellSort.sort(values);
        }
    }

    public void timeMergeSort(int reps) {
        for (int i = 0; i < reps; i++) {
            MergeSort.sort(values);
        }
    }

    public void timeArraysSort(int reps) {
        for (int i = 0; i < reps; i++) {
            Arrays.sort(values);
        }
    }

}
