package sorting;

public class MergeSort {

    public static void sort(int[] original) {
        int[] workSpace = new int[original.length];
        mergeSort(original, workSpace, 0, original.length - 1);
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
