package sorting;

public class InsertionSort {

    public static void sort(int[] original) {
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
