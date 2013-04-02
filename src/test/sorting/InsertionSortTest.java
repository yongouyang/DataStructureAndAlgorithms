package sorting;

import java.util.Arrays;
import java.util.Random;

public class InsertionSortTest {

    static int numberOfElements = 10;
    static int maxValue = 100;

    public static void main(String[] args) {
        int[] original = createArrayWithRandomValues();

        insertionSort(original);

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

    private static void insertionSort(int[] original) {
        int markerIndex, emptySlotIndex;
        for (markerIndex = 1; markerIndex < original.length; markerIndex++) {
            int marked = original[markerIndex]; // use a temp variable to store the marked number
            emptySlotIndex = markerIndex;
            while (emptySlotIndex > 0 && original[emptySlotIndex - 1] >= marked) {
                original[emptySlotIndex] = original[emptySlotIndex - 1]; // shift element right
                emptySlotIndex--; // moves the empty slot index one position to the left
            }
            original[emptySlotIndex] = marked; // insert marked element
        }
    }

}
