package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Voki
 */
public class Graph {

    private Node nodes[];

    private double adjazenzmatrix[][];

    public Graph(File file) throws FileNotFoundException {
        fileToNodes(file);
        generateAdjazenzmatrix();
    }

    private void fileToNodes(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        
        int size = Integer.parseInt(scanner.nextLine());
        
        nodes = new Node[size];
        scanner.useDelimiter(" ");
        
        
        for (int i = 0; scanner.hasNextLine(); i++) {
            
            double x = Double.parseDouble(scanner.next());
            double y = Double.parseDouble(scanner.nextLine());
            nodes[i] = new Node(x, y);
            System.out.println(nodes[i].toString());
        }
       
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
