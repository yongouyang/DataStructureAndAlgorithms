package graph;

import java.util.*;

public class DirectedGraph<T> implements Iterable<T> {

    // key is a Node, value is a set of Nodes connected by outgoing edges from the key
    private final Map<T, Set<T>> graph = new HashMap<T, Set<T>>();

    private DirectedGraph() {
    }

    public boolean addNode(T node) {
        if (graph.containsKey(node)) {
            return false;
        }

        graph.put(node, new HashSet<T>());
        return true;
    }

    public void addNodes(Collection<T> nodes) {
        for (T node : nodes) {
            addNode(node);
        }
    }

    public void addEdge(T src, T dest) {
        validateSourceAndDestinationNodes(src, dest);

        // Add the edge by adding the dest node into the outgoing edges
        graph.get(src).add(dest);
    }

    public void removeEdge(T src, T dest) {
        validateSourceAndDestinationNodes(src, dest);

        graph.get(src).remove(dest);
    }

    public boolean edgeExists(T src, T dest) {
        validateSourceAndDestinationNodes(src, dest);

        return graph.get(src).contains(dest);
    }

    public Set<T> edgesFrom(T node) {
        // Check that the node exists.
        Set<T> edges = graph.get(node);
        if (edges == null)
            throw new NoSuchElementException("Source node does not exist.");

        return Collections.unmodifiableSet(edges);
    }

    public Iterator<T> iterator() {
        return graph.keySet().iterator();
    }

    public int size() {
        return graph.size();
    }

    public boolean isEmpty() {
        return graph.isEmpty();
    }

    public DirectedGraph<T> reverse() {
        DirectedGraph<T> result = new DirectedGraph<T>();

        // Add all the nodes from the original graph
        for (T node : graph.keySet()) {
            result.addNode(node);
        }

        // Scan over all the edges in the graph, adding their reverse to the reverse graph.
        for (T node : graph.keySet()) {
            for (T endpoint : edgesFrom(node)) {
                result.addEdge(endpoint, node);
            }
        }

        return result;
    }

    private void validateSourceAndDestinationNodes(T src, T dest) {
        // Confirm both endpoints exist
        if (!graph.containsKey(src) || !graph.containsKey(dest))
            throw new NoSuchElementException("Both nodes must be in the graph.");
    }

    public static class Builder<T> {

        private Set<T> nodes = new HashSet<T>();
        private final Map<T, Set<T>> nodeEdges = new HashMap<T, Set<T>>();


        public static <T> Builder<T> newBuilder() {
            return new Builder<T>();
        }

        public Builder<T> withNodes(Collection<T> nodes) {
            this.nodes.addAll(nodes);
            return this;
        }

        public Builder<T> withEdges(T node, Collection<T> edges) {
            nodeEdges.put(node, new HashSet<T>(edges));
            return this;
        }

        public DirectedGraph<T> build() {
            DirectedGraph<T> graph = new DirectedGraph<T>();
            graph.addNodes(nodes);
            for (Map.Entry<T, Set<T>> entry : nodeEdges.entrySet()) {
                T currentNode = entry.getKey();
                for (T edge : entry.getValue()) {
                    graph.addEdge(currentNode, edge);
                }
            }
            return graph;
        }
    }

}
