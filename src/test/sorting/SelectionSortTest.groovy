package sorting

import org.junit.Test

public class SelectionSortTest {

    @Test
    public void canSort() {
        int[] array = [129, 213, 30, 84, 39, 95, 9, 743, 26]
        SelectionSort.sort(array)

        assert array == [9, 26, 30, 39, 84, 95, 129, 213, 743]
    }

}
