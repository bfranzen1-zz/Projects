/**
 * Created by Blake on 5/6/2017.
 */
import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {

    private Map<Vertex, Set<Edge>> graph;

    private Map<Vertex, Set<Vertex>> adjacentVertices;

    private Set<Edge> edges;

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
        graph = new HashMap<>();
        edges = new HashSet<>();
        adjacentVertices = new HashMap<>();
        for (Edge ed : e) {
            if (ed.getWeight() < 0) {
                throw new IllegalStateException("Weight of Edge can't be negative");
            }
            if (!checkEdge(ed)) {
                throw new IllegalArgumentException("Same edge can't have different weight");
            }
            Vertex dest = ed.getDestination();
            Vertex src = ed.getSource();
            if (v.contains(src) && v.contains(dest)) {
                if (!graph.containsKey(src)) {
                    graph.put(src, new HashSet<Edge>());

                }
                graph.get(src).add(ed);
                if (!adjacentVertices.containsKey(src)) {
                    adjacentVertices.put(src, new HashSet<Vertex>());
                }
                adjacentVertices.get(src).add(dest);
                if (!edges.contains(ed)) {
                    edges.add(ed);
                }
            } else {
                throw new IllegalArgumentException("Edge " + ed.toString() + " does not exist");
            }
        }
    }

    /**
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
        return graph.keySet();
    }

    /**
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
        return edges;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
        checkVertex(v);
        return adjacentVertices.get(v);
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph,
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {
        checkVertex(a);
        checkVertex(b);

        Collection<Edge> edgeList = graph.get(a);
        int cost = -1;
        for (Edge e : edgeList) {
            if (e.getDestination().equals(b)) {
                cost = e.getWeight();
                break;
            }
        }
        return cost;
    }

    //private helper method that checks to make sure the vertex v is in the graph
    private void checkVertex(Vertex v) {
        if (!graph.containsKey(v)) {
            throw new IllegalArgumentException("Vertex " + v.toString() + " does not exist");
        }
    }

    //private helper method that checks tyhe edge e to see if there is a duplicate with
    //a different weight in the set of edgess
    private boolean checkEdge(Edge e) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(e.getSource())
                && edge.getDestination().equals(e.getDestination())
                && edge.getWeight() != (e.getWeight())) {
                return false;
            }
        }
        return true;
    }
}