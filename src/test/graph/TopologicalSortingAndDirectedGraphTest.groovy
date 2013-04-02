package graph

import org.junit.Test

class TopologicalSortingAndDirectedGraphTest {

    @Test
    public void someTest() {
        def graph = new DirectedGraph<String>()
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addNode("I");
        graph.addNode("J");

        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("D", "G");
        graph.addEdge("E", "H");
        graph.addEdge("F", "H");
        graph.addEdge("G", "H");

        def sorted = TopologicalSort.sort(graph)

        println sorted
    }

    public static void main(String[] args) {
        DirectedGraph<String> graph = new DirectedGraph<String>()
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addNode("I");
        graph.addNode("J");

        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("D", "G");
        graph.addEdge("E", "H");
        graph.addEdge("F", "H");
        graph.addEdge("G", "H");

        List<String> sorted = TopologicalSort.sort(graph)

        System.out.println(sorted)
    }

}
