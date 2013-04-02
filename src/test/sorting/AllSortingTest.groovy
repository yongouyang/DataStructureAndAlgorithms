package sorting

import org.junit.Test


class AllSortingTest {

    def numberOfElements = 11

    @Test
    public void verifyBubbleSort() {
        int[] original = createArrayWithRandomValues()
        bubbleSort(original)

        println "The sorted array is ${original}"
    }

    private int[] createArrayWithRandomValues() {
        def random = new Random()
        int[] original = new int[numberOfElements]
        numberOfElements.times { int index ->
            original[index - 1] = random.nextInt(100)
        }
        println "The original array is ${original}"
        original
    }

    private void bubbleSort(int[] original) {
        int outer, inner;
        for (outer = original.length - 1; outer > 1; outer--) { // from last element to the 2nd element
            for (inner = 0; inner < outer; inner++) { // from the 1st element to outer index - 1
                if (original[inner] > original[inner + 1]) { // bubble up the bigger value to the right
                    int temp = original[inner]
                    original[inner] = original[inner + 1]
                    original[inner + 1] = temp
                }
            }
        }
    }

    @Test
    public void verifySelectionSort() {
        int[] original = createArrayWithRandomValues()

        def stats = selectionSort(original)

        println "The sorted array is ${original}"
        println stats

    }


    private Map<String, Integer> selectionSort(int[] original) {
        int outer, inner, minIndex;
        int numberOfSwaps = 0, numberOfComparisons = 0;
        for (outer = 0; outer < original.length - 1; outer++) { // from 1st element to next-to-last element
            minIndex = outer; // each round the min index starts from the outer index
            for (inner = outer + 1; inner < original.length; inner++) { // from outer + 1 element to the last element
                numberOfComparisons++;
                if (original[inner] < original[minIndex]) { // remember the index for the new minimum
                    minIndex = inner;
                }
            }
            numberOfSwaps++;

            // swap the elements between outer index and min index
            int temp = original[outer]
            original[outer] = original[minIndex]
            original[minIndex] = temp

        }

        return [numberOfSwaps: numberOfSwaps, numberOfComparisons: numberOfComparisons]
    }

    @Test
    public void verifyInsertionSort() {
        int[] original = createArrayWithRandomValues()

        insertionSort(original)

        println "The sorted array is ${original}"
    }

    private void insertionSort(int[] original) {
        int outer, emptySlotIndex;
        for (outer = 1; outer < original.length; outer++) {
            int temp = original[outer] // removed marked element, and use a temp variable to store it
            emptySlotIndex = outer;
            while (emptySlotIndex > 0 && original[emptySlotIndex - 1] >= temp) {
                original[emptySlotIndex] = original[emptySlotIndex - 1]; // shift element right
                emptySlotIndex--; // moves one position to left
            }
            original[emptySlotIndex] = temp; // insert marked element
        }
    }

    @Test
    public void verifyMergeSort() {
        int[] original = createArrayWithRandomValues()
//        numberOfElements = 7
//        int[] original = [38,27,43,3,9,82,10]
        def workspace = new int[numberOfElements] // workspace array is used to keep the partial sorted results
        mergeSort(original, workspace, 0, numberOfElements - 1)

        println "The sorted array is ${original}"
    }

    private void mergeSort(int[] original, int[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound != upperBound) { // find midpoint
            int mid = (lowerBound + upperBound) / 2;
            // sort lower half
            mergeSort(original, workSpace, lowerBound, mid);
            // sort upper half
            mergeSort(original, workSpace, mid + 1, upperBound);
            // merge them
            merge(original, workSpace, lowerBound, mid, upperBound);
        }
    }

    private void merge(int[] original, int[] workSpace, int lowerBound, int middle, int upperBound) {

        // Copy both parts into the workspace array
        for (int i = lowerBound; i <= upperBound; i++) {
            workSpace[i] = original[i];
        }

        int leftPointer = lowerBound;
        int rightPointer = middle + 1;
        int originalPointer = lowerBound;

        // Copy the smallest values from either the left or the right side back to the original array
        while (leftPointer <= middle && rightPointer <= upperBound) {
            if (workSpace[leftPointer] <= workSpace[rightPointer]) {
                original[originalPointer++] = workSpace[leftPointer++]; // copy and move the pointer on workspace to the next smallest value on left (if there is any)
            } else {
                original[originalPointer++] = workSpace[rightPointer++]; // copy and move the pointer on workspace to the next smallest value on right (if there is any)
            }
        }
        // Copy the rest of the left side of the array into the target array
        while (leftPointer <= middle) {
            original[originalPointer++] = workSpace[leftPointer++];
        }

        // if there is one left in the right side of the workspace, that is the largest value of the merged array, so no action is required

    }

}
