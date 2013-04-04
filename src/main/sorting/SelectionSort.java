package sorting;

public class SelectionSort {

    public static void sort(int[] original) {
        int outer, inner, minIndex;
        for (outer = 0; outer < original.length - 1; outer++) { // from 1st element to next-to-last element
            minIndex = outer; // each round the min index starts from the outer index
            for (inner = outer + 1; inner < original.length; inner++) { // from outer + 1 element to the last element
                if (original[inner] < original[minIndex]) { // remember the index for the new minimum
                    minIndex = inner;
                }
            }

            // swap the elements between outer index and min index
            int temp = original[outer];
            original[outer] = original[minIndex];
            original[minIndex] = temp;

        }
    }
}
