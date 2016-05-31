package tsp;

import java.io.File;
import java.util.Vector;

/**
 * @author Voki
 */
public class Graph {

    private Node nodes[];

    private double adjazenzmatrix[][];

    public Graph(File numbers) {
        nodes = new Node[5];
    }

    private void generateAdjazenzmatrix() {
        adjazenzmatrix = new double[nodes.length][nodes.length];
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y <= x; y++) {
                adjazenzmatrix[x][y]
                        = (adjazenzmatrix[y][x] = distance(nodes[x], nodes[y]));             
            }
        }
    }

    public static final double distance(Node n, Node m) {
        double Δx = n.x - m.x;
        double Δy = n.y - m.y;
        return Math.sqrt(Δx * Δx + Δy * Δy);
    }
}
