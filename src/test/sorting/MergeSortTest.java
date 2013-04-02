package sorting;

import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {

    static int numberOfElements = 10;
    static int maxValue = 100;

    public static void main(String[] args) {
        int[] original = createArrayWithRandomValues();
        // workspace array is used to keep the partial sorted results
        int[] workspace = new int[original.length];
        mergeSort(original, workspace, 0, numberOfElements - 1);

        System.out.println("The sorted array is " + Arrays.toString(original));
    }

    private static int[] createArrayWithRandomValues() {
        Random random = new Random();
        int[] original = new int[numberOfElements];
        for (int i = 0; i < numberOfElements; i++) {
            original[i] = random.nextInt(maxValue);
        }
        System.out.println("The original array is " + Arrays.toString(original));
        return original;
    }

    private static void mergeSort(int[] original, int[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound != upperBound) {
            // find midpoint
            int mid = (lowerBound + upperBound) / 2;
            // sort lower half
            mergeSort(original, workSpace, lowerBound, mid);
            // sort upper half
            mergeSort(original, workSpace, mid + 1, upperBound);
            // merge them
            merge(original, workSpace, lowerBound, mid, upperBound);
        }
    }

    private static void merge(int[] original, int[] workSpace, int lowerBound, int middle, int upperBound) {
        // Copy both parts into the workspace array
        System.arraycopy(original, lowerBound, workSpace, lowerBound, upperBound + 1 - lowerBound);

        int leftPointer = lowerBound;
        int rightPointer = middle + 1;
        int originalPointer = lowerBound;

        // Copy the smallest values from either the left or the right side back to the original array
        while (leftPointer <= middle && rightPointer <= upperBound) {
            if (workSpace[leftPointer] <= workSpace[rightPointer]) {
                // copy and move the pointer on workspace to the next smallest value on left (if there is any)
                original[originalPointer++] = workSpace[leftPointer++];
            } else {
                // copy and move the pointer on workspace to the next smallest value on right (if there is any)
                original[originalPointer++] = workSpace[rightPointer++];
            }
        }
        // Copy the rest of the left side of the array into the target array
        while (leftPointer <= middle) {
            original[originalPointer++] = workSpace[leftPointer++];
        }

        // if there are elements left in the right side of the workspace, those
        // are the largest values of the merged array, so no action is required
    }

}
