package sorting;

import java.util.Arrays;
import java.util.Random;

public class SelectionSortTest {

    static int numberOfElements = 10;
    static int maxValue = 100;

    public static void main(String[] args) {
        int[] original = createArrayWithRandomValues();

        selectionSort(original);

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


    private static void selectionSort(int[] original) {
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
