/**
 * Created by Blake on 5/6/2017.
 */
/**
 * Representation of a graph vertex
 */
public class Vertex {
    private final String label;   // label attached to this vertex
    private final Vertex path;
    private final int cost;

    /**
     * Construct a new vertex
     * @param label the label attached to this vertex
     */
    public Vertex(String label) {
        this(label, null, Integer.MAX_VALUE);
    }

    public Vertex(String label, Vertex path, int cost) {
        if(label == null)
            throw new IllegalArgumentException("null");
        this.label = label;
        this.path = path;
        this.cost = cost;
    }

    /**
     * Get a vertex label
     * @return the label attached to this vertex
     */
    public String getLabel() {
        return label;
    }

    public Vertex getPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }

    /**
     * A string representation of this object
     * @return the label attached to this vertex
     */
    public String toString() {
        return label;
    }

    //auto-generated: hashes on label
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        return result;
    }

    //auto-generated: compares labels
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Vertex other = (Vertex) obj;
        if (label == null) {
            return other.label == null;
        } else {
            return label.equals(other.label);
        }
    }
}

