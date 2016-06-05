package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import tsp.heuristics.NearestNeighbour;
import java.util.Arrays;
import tsp.heuristics.FullEnumeration;

/**
 * namen der autoren austauschen, paketnamen aendern, klassennamen
 * protokoll schreiben, Onotation: neares neighbour: n^2, enumeration: n! 
 * methoden in enumeration löschen: print2dintarray, tointarray, to2dintarray, kommentare
 * TESTFILES ERSTELLEN!!!! 
 * testfiles in dist ordner schieben
 * aufruf mit: java -jar tsp.jar -e|-n testfilename
 * @author Voki
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        File inputFile = new File(args[1]);
        Graph matrix = new Graph(inputFile);

        if (args[0].contains("-e")) {
            Integer[] shortestPath = FullEnumeration.fullEnumeration(0, matrix);
            System.out.println(Arrays.toString(shortestPath));
            System.out.println(matrix.getPathLength(shortestPath));
            
        } else if (args[0].contains("-n")) {
            System.out.println(NearestNeighbour.nearestNeighbour(matrix));
        }

    }

}
