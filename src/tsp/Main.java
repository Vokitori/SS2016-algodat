package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import tsp.heuristics.NearestNeighbour;
import java.util.Arrays;
import tsp.heuristics.FullEnumeration;
import tsp.heuristics.NaturalSelection;

/**
 *
 * -jar tsp.jar -e|-n|-s testfilename
 *
 * @author Thomas Schierl und Viktoria Paschinger
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File(args[args.length - 1]);
        Graph matrix = new Graph(inputFile);

        for (int i = 0; i < args.length - 1; i++) {
            String arg = args[i];
            long cpuStart = Graph.getCpuTime();
            long timeStart = System.nanoTime();
            if (arg.contains("-e")) {
                System.out.print("Full Enumeration: ");
                Integer[] shortestPath = FullEnumeration.fullEnumeration(0, matrix);
                System.out.println(Arrays.toString(shortestPath));
                System.out.println(matrix.getPathLength(shortestPath) + "\n");
            } else if (arg.contains("-n")) {
                System.out.println(NearestNeighbour.nearestNeighbour(matrix));
            } else if (arg.contains("-s")) {
                System.out.println(NaturalSelection.naturalSelection(matrix));
            }
            long cpuStop = Graph.getCpuTime();
            long timeStop = System.nanoTime();
            long cpuDelta = cpuStop - cpuStart;
            long timeDelta = timeStop - timeStart;
            System.out.println("CPU-Time:\t" + cpuDelta + "ms");
            System.out.println("Time:\t\t" + Math.round(timeDelta / 1000000.0 + 0.5) + "ms");
            System.out.println("-------------------------------");
        }
         String content1, contet2;
        
//        int number, number2;
//        for (int i = 0; i < 40; i++) {
//            number = (int)(Math.random() * 100);
//            number2 = (int)(Math.random() * 100);
//            System.out.println(number+" "+number2);
//        }

    }
}
