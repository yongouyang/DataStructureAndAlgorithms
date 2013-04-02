package structure

import org.junit.Before
import org.junit.Test

class SmartStackTest {

    SmartStack stack

    @Before
    public void before() {
        stack = new SmartStack()
        stack.push(72)
        stack.push(12)
        stack.push(55)
        stack.push(78)
        stack.push(22)
        stack.push(34)
    }

    @Test
    public void canGetCurrentMinValue() {
        assert stack.min == 12

        5.times { stack.pop() }

        assert stack.min == 72
    }

    @Test
    public void canGetCurrentMaxValue() {
        assert stack.max == 78

        4.times { stack.pop() }

        assert stack.max == 72
    }

    @Test
    public void canPeek() {
        assert stack.peek() == 34
    }

    @Test
    public void isEmpty() {
        assert !stack.empty()
    }

    @Test
    public void canSearch() {
        // the 1-based position from the top of the stack where the object is located
        assert stack.search(34) == 1
        assert stack.search(22) == 2
        assert stack.search(78) == 3
        assert stack.search(55) == 4
        assert stack.search(12) == 5
        assert stack.search(72) == 6
        // not in the stack should return -1
        assert stack.search(11112) == -1
    }

}
