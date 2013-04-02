package fun.rivercrossing

import org.junit.Test

import static fun.rivercrossing.BoatLocation.EastBank
import static fun.rivercrossing.BoatLocation.WestBank


class FarmerWolfGoatRiddleTest {

    FarmerWolfGoatRiddle riddle = new FarmerWolfGoatRiddle()

    @Test
    public void findFirstPossibleSolution() {
        def solution = riddle.findTheFirstWorkingSolution()

        // assert with the correct expectations regardless of which is the first solution
        if (solution.contains(new RiverState([riddle.wolf], [riddle.farmer, riddle.goat, riddle.cabbage], EastBank))) {
            assertFirstSolution(solution)
        } else {
            assertSecondSolution(solution)
        }
    }

    @Test
    public void findAllPossibleSolutions() {
        def solutions = riddle.findAllPossibleSolutions()
        // the ordering of solutions will be random
        assert solutions.size() == 2
        assert solutions[0].size() == 8
        assert solutions[1].size() == 8

        def assertAllSolutions = { Deque<RiverState> solution1, Deque<RiverState> solution2 ->
            assertFirstSolution(solution1)
            assertSecondSolution(solution2)
        }

        // assert with the correct expectations regardless of which is the first solution
        if (solutions[0].contains(new RiverState([riddle.wolf], [riddle.farmer, riddle.goat, riddle.cabbage], EastBank))) {
            assertAllSolutions(solutions[0], solutions[1])
        } else {
            assertAllSolutions(solutions[1], solutions[0])
        }
    }

    private void assertFirstSolution(Deque<RiverState> solution) {
        assert solution.poll() == new RiverState([riddle.farmer, riddle.wolf, riddle.goat, riddle.cabbage], [], WestBank)
        assert solution.poll() == new RiverState([riddle.wolf, riddle.cabbage], [riddle.farmer, riddle.goat], EastBank)
        assert solution.poll() == new RiverState([riddle.farmer, riddle.wolf, riddle.cabbage], [riddle.goat], WestBank)
        assert solution.poll() == new RiverState([riddle.wolf], [riddle.farmer, riddle.goat, riddle.cabbage], EastBank)
        assert solution.poll() == new RiverState([riddle.farmer, riddle.wolf, riddle.goat], [riddle.cabbage], WestBank)
        assert solution.poll() == new RiverState([riddle.goat], [riddle.farmer, riddle.wolf, riddle.cabbage], EastBank)
        assert solution.poll() == new RiverState([riddle.farmer, riddle.goat], [riddle.wolf, riddle.cabbage], WestBank)
        assert solution.poll() == new RiverState([], [riddle.farmer, riddle.wolf, riddle.goat, riddle.cabbage], EastBank)
        assert solution.poll() == null
    }

    private void assertSecondSolution(Deque<RiverState> solution) {
        assert solution.poll() == new RiverState([riddle.farmer, riddle.wolf, riddle.goat, riddle.cabbage], [], WestBank)
        assert solution.poll() == new RiverState([riddle.wolf, riddle.cabbage], [riddle.farmer, riddle.goat], EastBank)
        assert solution.poll() == new RiverState([riddle.farmer, riddle.wolf, riddle.cabbage], [riddle.goat], WestBank)
        assert solution.poll() == new RiverState([riddle.cabbage], [riddle.farmer, riddle.goat, riddle.wolf], EastBank)
        assert solution.poll() == new RiverState([riddle.farmer, riddle.cabbage, riddle.goat], [riddle.wolf], WestBank)
        assert solution.poll() == new RiverState([riddle.goat], [riddle.farmer, riddle.wolf, riddle.cabbage], EastBank)
        assert solution.poll() == new RiverState([riddle.farmer, riddle.goat], [riddle.wolf, riddle.cabbage], WestBank)
        assert solution.poll() == new RiverState([], [riddle.farmer, riddle.wolf, riddle.goat, riddle.cabbage], EastBank)
        assert solution.poll() == null
    }
}
