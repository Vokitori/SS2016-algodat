package tsp;

/**
 * @author link
 */
public class Node {

    public final double x;
    public final double y;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Node{" + x + ", " + y + '}';
    }
    
    

}
