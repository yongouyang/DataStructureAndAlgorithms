package sorting;

public class ShellSort {

    public static void sort(int[] original) {
        int inner, outer;
        //find initial value of h (Knuth's Interval Sequence)
        int h = 1;
        while (h <= original.length / 3) {
            h = h * 3 + 1; // (1, 4, 13, 40, 121, ...)
        }

        while (h > 0) // decreasing h, until h=1
        {
            // h-sort the array
            for (outer = h; outer < original.length; outer++) {
                int temp = original[outer];
                inner = outer;
                // one subpass (eg 0, 4, 8)
                while (inner > h - 1 && original[inner - h] >= temp) {
                    original[inner] = original[inner - h];
                    inner -= h;
                }
                original[inner] = temp;
            }
            h = (h - 1) / 3; // decrease h
        }
    }
}
