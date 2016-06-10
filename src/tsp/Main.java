package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import tsp.heuristics.NearestNeighbour;
import java.util.Arrays;
import tsp.heuristics.FullEnumeration;
import tsp.heuristics.NaturalSelection;

/**
 * 
 * protokoll schreiben, Onotation: neares neighbour: n^2, enumeration: n! 
 *
 * TESTFILES ERSTELLEN!!!! 
 * testfiles in dist ordner schieben
 * aufruf mit: java -jar tsp.jar -e|-n|-s testfilename
 * @author Voki
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        File inputFile = new File(args[1]);
        Graph matrix = new Graph(inputFile);

        if (args[0].contains("-e")) {
            Integer[] shortestPath = FullEnumeration.fullEnumeration(0, matrix);
            System.out.println(Arrays.toString(shortestPath));
            System.out.println(matrix.getPathLength(shortestPath)+"\n");
            
        } else if (args[0].contains("-n")) {
            System.out.println(NearestNeighbour.nearestNeighbour(matrix));
            
        }else if (args[0].contains("-s")) {
            System.out.println();
        }
        System.out.println(NearestNeighbour.nearestNeighbour(matrix));
        System.out.println(NaturalSelection.naturalSelection(matrix));
        matrix.printAdjazenzmatrix();
    }

}
