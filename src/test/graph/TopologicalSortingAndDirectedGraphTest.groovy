package graph

import org.junit.Before
import org.junit.Test

import static graph.TopologicalSort.findSortedDependenciesUsingDFS

class TopologicalSortingAndDirectedGraphTest {

    DirectedGraph<String> graph

    @Before
    public void before() {
        /*  The direction means evaluation direction, which is the reverse of dependency direction
            In below example, to evaluate D, you need to evaluate A first, which means D depends on A

              A --> D --> G --> H
              B --> E -->       H
              C --> F -->       H
              I, J
         */

        graph = DirectedGraph.Builder.newBuilder()
            .withNodes(["G", "B", "C", "A", "F", "D", "E", "H", "I", "J"])
            .withEdges("A", ["D", "E"])
            .withEdges("B", ["E"])
            .withEdges("C", ["F"])
            .withEdges("D", ["G"])
            .withEdges("E", ["H"])
            .withEdges("F", ["H"])
            .withEdges("G", ["H"])
            .build()

    }

    @Test
    public void canSortADirectedGraphUsingRecursion() {
        def sorted = TopologicalSort.sortRecursively(graph)
        println sorted
        assert sorted == ["A", "D", "B", "E", "C", "F", "G", "H", "I", "J"]
    }

    @Test
    public void canFindDependenciesGivenANodeUsingDepthFirstSearch() {
        assert findSortedDependenciesUsingDFS(graph.reverse(), "H").toList() == ["B", "E", "C", "F", "A", "D", "G", "H"]

        assert findSortedDependenciesUsingDFS(graph.reverse(), "E").toList() == ["A", "B", "E"]
    }

}
