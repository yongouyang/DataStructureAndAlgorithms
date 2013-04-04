package sorting;

public class BubbleSort {

    public static void sort(int[] original) {
        int outer, inner;
        for (outer = original.length - 1; outer > 1; outer--) { // from last element to the 2nd element
            for (inner = 0; inner < outer; inner++) { // from the 1st element to outer index - 1
                if (original[inner] > original[inner + 1]) { // bubble up the bigger value to the right
                    int temp = original[inner];
                    original[inner] = original[inner + 1];
                    original[inner + 1] = temp;
                }
            }
        }
    }
}
