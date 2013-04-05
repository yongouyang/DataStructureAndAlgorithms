package graph;

import java.util.*;

public class TopologicalSort {

    // using Depth-First Search to find out the sorted dependencies of a given node
    public static <T> Deque<T> findSortedDependenciesUsingDFS(DirectedGraph<T> graph, T node) {
        Deque<T> visited = new ArrayDeque<T>();
        Deque<T> toBeVisited = new ArrayDeque<T>();
        toBeVisited.push(node);

        while (!toBeVisited.isEmpty()) {
            T currentNode = toBeVisited.pop();
            visited.push(currentNode);
            Set<T> dependencies = graph.edgesFrom(currentNode);

            for (T dependency : dependencies) {
                if (!visited.contains(dependency)) {
                    toBeVisited.push(dependency);
                }
            }
        }

        return visited;
    }

    public static <T> List<T> sortRecursively(DirectedGraph<T> graph) {
        DirectedGraph<T> reversedGraph = graph.reverse();

        List<T> result = new ArrayList<T>();
        Set<T> visited = new HashSet<T>();

        /* We'll also maintain a third set consisting of all nodes that have
         * been fully expanded.  If the graph contains a cycle, then we can
         * detect this by noting that a node has been explored but not fully
         * expanded.
         */
        Set<T> expanded = new HashSet<T>();

        // Fire off a Depth-First Search from each node in the graph
        for (T node : reversedGraph)
            explore(node, reversedGraph, result, visited, expanded);

        return result;
    }


    /**
     * Recursively performs a Depth-First Search from the specified node, marking all nodes
     * encountered by the search.
     *
     * @param node     The node to begin the search from.
     * @param graph    The graph in which to perform the search.
     * @param result   A list holding the topological sort of the graph.
     * @param visited  A set of nodes that have already been visited.
     * @param expanded A set of nodes that have been fully expanded.
     */
    private static <T> void explore(T node, DirectedGraph<T> graph, List<T> result, Set<T> visited, Set<T> expanded) {
        if (visited.contains(node)) {
            // if this node has already been expanded, then it's already been assigned a
            // position in the final topological sort and we don't need to explore it again.
            if (expanded.contains(node)) return;

            // if it hasn't been expanded, it means that we've just found a node that is currently being explored,
            // and therefore is part of a cycle.  In that case, we should report an error.
            throw new IllegalArgumentException("A cycle was detected within the Graph when exploring node " + node.toString());
        }

        visited.add(node);

        // recursively explore all predecessors of this node
        for (T predecessor : graph.edgesFrom(node))
            explore(predecessor, graph, result, visited, expanded);

        result.add(node);
        expanded.add(node);
    }

}
