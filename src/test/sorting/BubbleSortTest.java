package sorting;

import java.util.Arrays;
import java.util.Random;

public class BubbleSortTest {

    static int numberOfElements = 10;
    static int maxValue = 100;

    public static void main(String[] args) {
        int[] original = createArrayWithRandomValues();

        bubbleSort(original);

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

    private static void bubbleSort(int[] original) {
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
