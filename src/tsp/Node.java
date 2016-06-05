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
    
    public Node(Node node){
        this.x = node.x;
        this.y = node.y;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + '}';
    }

}
