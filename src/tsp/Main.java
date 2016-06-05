package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import tsp.heuristics.NearestNeighbour;
import java.util.Arrays;
import tsp.heuristics.FullEnumeration;

/**
 * @author Voki
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File numbers = new File("vorgabe.txt");
        Graph matrix = new Graph(numbers);
        matrix.printAdjazenzmatrix();
        matrix.printNodes();
        System.out.println(NearestNeighbour.nearestNeighbour(matrix));
        System.out.println(Graph.getCpuTime());
        Integer[] shortestPath = FullEnumeration.fullEnumeration(0, matrix);
        System.out.println(Arrays.toString(shortestPath));
        System.out.println(matrix.getPathLength(shortestPath));
    }

}
