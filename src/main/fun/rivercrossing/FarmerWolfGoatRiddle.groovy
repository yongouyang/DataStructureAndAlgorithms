package fun.rivercrossing

import graph.DirectedGraph
import org.codehaus.groovy.runtime.MethodClosure

import static fun.rivercrossing.BoatLocation.EastBank
import static fun.rivercrossing.BoatLocation.WestBank

public class FarmerWolfGoatRiddle {

    /*  Farmer Wolf Goat Puzzle
        A farmer is on the east bank of a river with a wolf, goat and cabbage in his care. Without his presence the wolf would eat
        the goat or the goat would eat the cabbage. The wolf is not interested in the cabbage. The farmer wishes to bring his three
        charges across the river. However the boat available to him can only carry one of the wolf, goat and cabbage besides
        himself. The puzzle is: is there a sequence of river crossings so that the farmer can transfer himself and the other three all
        intact to the west bank?
    */

    RiverRole farmer = new RiverRole("farmer", true);
    RiverRole wolf = new RiverRole("wolf", false);
    RiverRole goat = new RiverRole("goat", false);
    RiverRole cabbage = new RiverRole("cabbage", false);

    RiverState initialState = new RiverState([farmer, wolf, goat, cabbage], [], BoatLocation.WestBank)
    RiverState finalState = new RiverState([], [farmer, wolf, goat, cabbage], EastBank)

    Closure<Boolean> areRolesHarmonisedOnRiverBank = { Collection<RiverRole> roles ->
        // describe clearly what the rules are
        if (!roles.contains(farmer)) {
            if (roles.contains(wolf) && roles.contains(goat)) return false
            if (roles.contains(goat) && roles.contains(cabbage)) return false
        }
        return true
    }


    public Collection<Deque<RiverState>> findAllPossibleSolutions() {
        DirectedGraph<RiverState> graph = buildCompleteGraph()

        Collection<Deque<RiverState>> solutions = []
        Deque<RiverState> currentSolution = new ArrayDeque<RiverState>()

        explore(graph, initialState, currentSolution, solutions)

        return solutions
    }

    // using Depth First Search (Stack Implementation) to identify a solution
    private void explore(DirectedGraph<RiverState> graph, RiverState currentNode, Deque<RiverState> currentSolution, Collection<Deque<RiverState>> solutions) {
        currentSolution.push(currentNode)
        graph.edgesFrom(currentNode).each { RiverState node ->
            if (node == finalState) {
                // add the current solution, including the final node, to the solutions
                currentSolution.push(node)
                solutions.add(new ArrayDeque<RiverState>(currentSolution))
                currentSolution.pop()
            } else {
                // recursively explore
                explore(graph, node, currentSolution, solutions)
            }
        }
        currentSolution.pop()
    }

    // using Breath First Search to build the complete graph
    private DirectedGraph<RiverState> buildCompleteGraph() {
        DirectedGraph<RiverState> graph = new DirectedGraph<RiverState>()
        graph.addNode(initialState)

        Set<RiverState> visitedNodes = new HashSet<RiverState>()

        // use a queue to keep the states to be visited in sequence
        Deque<RiverState> currentStates = new ArrayDeque<RiverState>()
        currentStates.add(initialState)

        while (!currentStates.isEmpty()) {
            if (currentStates.peek() != finalState) {
                def currentState = currentStates.removeFirst()
                if (!visitedNodes.contains(currentState)) {
                    visitedNodes.add(currentState)
                    def possibleStates = findNextPossibleStates(currentState)
                    def validStates = possibleStates.findAll { RiverState possibleState ->
                        // matched state should not be one of the visited states, and it should pass the validations
                        return !visitedNodes.contains(possibleState) && passRuleValidations(possibleState)
                    }
                    // add successors and edges
                    graph.addNodes(validStates)
                    validStates.each { graph.addEdge(currentState, it) }

                    // add to current states so that we can visit them next
                    currentStates.addAll(validStates)
                }
            } else {
                currentStates.removeFirst() // remove the final state from the currentStates queue
            }
        }

        return graph
    }

    // using Depth-First Search to find the first solution
    public Deque<RiverState> findTheFirstWorkingSolution() {
        Deque<RiverState> result = new ArrayDeque<RiverState>()
        result.addLast(initialState)

        // build the tree until it reaches the final state
        while (result.peekLast() != (finalState)) {
            def possibleStates = findNextPossibleStates(result.peekLast())
            def firstMatch = possibleStates.find { RiverState possibleState ->
                // matched state should not be one of the visited states, and it should pass the validations
                return !result.contains(possibleState) && passRuleValidations(possibleState)
            }
            result.addLast(firstMatch)
        }

        return result
    }

    private boolean passRuleValidations(RiverState state) {
        return areRolesHarmonisedOnRiverBank(state.eastBank) && areRolesHarmonisedOnRiverBank(state.westBank)
    }

    private List<RiverState> findNextPossibleStates(RiverState currentState) {
        // from the last visited state, find out who can sail the boat, and where is the sailor
        List<RiverState> possibleStates = []

        transitFrom(currentState, possibleStates)

        return possibleStates
    }

    private void transitFrom(RiverState currentState, List<RiverState> possibleStates) {
        // check where the boat is
        BoatLocation boatOrigin = currentState.boatLocation
        BoatLocation boatDestination

        // we use method closure to get a copy of west bank or east bank roles
        MethodClosure origin, destination

        // multiple assignments
        (origin, destination, boatDestination) = boatOrigin == WestBank ?
            [currentState.&getWestBank, currentState.&getEastBank, EastBank] :
            [currentState.&getEastBank, currentState.&getWestBank, WestBank]

        def possibleSailors = origin().findAll { RiverRole role -> role.canSailTheBoat }

        possibleSailors?.each { RiverRole sailor ->
            // pick 0 passengers onto the boat
            Collection<RiverRole> onBoat = [sailor]
            possibleStates.add(createNextPossibleState(origin, destination, onBoat, boatDestination))

            Collection<RiverRole> possiblePassengers = origin() as Collection<RiverRole>
            possiblePassengers.remove(sailor)

            // pick 1 passenger onto the boat
            possiblePassengers.each { RiverRole passenger ->
                onBoat = [passenger, sailor]
                possibleStates.add(createNextPossibleState(origin, destination, onBoat, boatDestination))
            }
        }
    }

    private RiverState createNextPossibleState(MethodClosure origin, MethodClosure destination, Collection<RiverRole> onBoat, BoatLocation boatDestination) {
        Collection<RiverRole> newDestination = destination() as Collection<RiverRole>
        Collection<RiverRole> newOrigin = origin() as Collection<RiverRole>

        // boat transits from origin to destination
        newOrigin.removeAll(onBoat)
        newDestination.addAll(onBoat)

        return boatDestination == WestBank ? new RiverState(newDestination, newOrigin, boatDestination) : new RiverState(newOrigin, newDestination, boatDestination)
    }


}
