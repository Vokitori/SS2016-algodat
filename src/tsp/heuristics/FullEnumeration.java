package tsp.heuristics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tsp.Graph;

/**
 * @author Thomas Schierl und Viktoria Paschinger
 */
public class FullEnumeration {
/**
 * array mit allen permutationen
 */
    private static Integer[][] permutations;
    /**
     * index der permutation welche gerade in behandlung ist
     */
    private static int y;
    /**
     * array der nodeindizes
     */
    private static Integer[] values;

    private FullEnumeration() {
    }

    /**
     * verwendet den fullenumeration algorithmus um den kuerzesten weg zu berechnen
     * @param start node bei dem begonnen werden soll
     * @param g graph dessen kuerzester rundweg berechnet werden soll
     * @return gibt array mit der kuerzesten nodesreihenfolge zurueck
     */
    public static synchronized Integer[] fullEnumeration(Integer start, Graph g) {
        values = removeStartIndex(start, g);
        y = 0;
        permutations = new Integer[factorial(values.length)][values.length];
        permute(Arrays.asList(values), 0);
        int shortestPathIndex = 0;
        double shortestPathLength = distanceSum(g, start, permutations[0]);
        for (int i = 1; i < permutations.length; i++) {
            Integer[] path = permutations[i];
            double pathLength = distanceSum(g, start, path);
            if (shortestPathLength > pathLength) {
                shortestPathIndex = i;
                shortestPathLength = pathLength;
            }
        }
        Integer[] shortestPath = new Integer[permutations[shortestPathIndex].length + 2];
        System.arraycopy(permutations[shortestPathIndex], 0, shortestPath, 1, permutations[shortestPathIndex].length);
        shortestPath[0] = 0;
        shortestPath[shortestPath.length - 1] = 0;
        return shortestPath;
    }
/**
 * liest den abstand zwischen zwei nodes aus der adjazenzmatrix aus
 * @param g graph dessen nodes und adjazenzmatrix ausgelesen werden
 * @param start ausgehender node
 * @param end endnode
 * @return double mit der distanz zwischen den beiden nodes
 */
    private static double distance(Graph g, Integer start, Integer end) {
        return g.adjazenzmatrix[start][end];
    }

    /**
     * berechnet die summe der distanzen
     *
     * @param g graph dessen daten verwendet werden sollen
     * @param start node bei dem begonnen wird
     * @param node array oder einzelne zahlen dessen distanzen addiert werden
     * sollen
     * @return double mit der summe der distanzen
     */
    private static double distanceSum(Graph g, Integer start, Integer... node) {
        double sum = distance(g, start, node[0]);
        for (int i = 1; i < node.length; i++) {
            sum += distance(g, node[i - 1], node[i]);
        }
        sum += distance(g, node[node.length - 1], start);
        return sum;
    }

    /**
     * macht alle permutationen eines arrays.
     *
     * @param list liste auf welche die permutation ausgefuehrt werden soll
     * @param k element welches vertauscht werden soll
     */
    private static void permute(List<Integer> list, Integer k) {
        for (int i = k; i < list.size(); i++) {
            Collections.swap(list, i, k);
            permute(list, k + 1);
            Collections.swap(list, k, i);
        }
        if (k == list.size() - 1) {
            permutations[y++] = list.toArray(new Integer[0]);
        }
    }

    /**
     * gibt eine zweidimensionales array aus
     *
     * @param array array welches ausgegeben werden soll
     */
    private static void print2dIntArray(Integer[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * konvertiert eine liste zu einem array
     *
     * @param list liste welche zu einem array konvertiert werden soll
     * @return gibt array zurueck
     */
    private static int[] toIntArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    /**
     * konvertiert eine liste zu einem zweidimensionalen array
     *
     * @param list liste welche zu einem zwei dimensionalen array konvertiert
     * werden soll
     * @return gibt zweidimensinales array zurueck
     */
    private static Integer[][] to2dIntArray(List<Integer[]> list) {
        Integer[][] l = new Integer[list.size()][list.get(0).length];
        for (int i = 0; i < l.length; i++) {
            System.arraycopy(list.get(i), 0, l[i], 0, l[i].length);
        }
        return l;
    }

    /**
     * entfernt das erste element eines arrays
     *
     * @param start nummer des start index
     * @param g graph dessen nodes array genommen werden soll
     * @return gibt das neue array ohne start index an
     */
    private static Integer[] removeStartIndex(Integer start, Graph g) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < g.nodes.length; i++) {
            if (i != start) {
                list.add(i);
            }
        }
        return list.toArray(new Integer[0]);
    }

    /**
     * berechnet die fakultaet einer zahl. ONLY FOR POSITIVE INTEGER.
     *
     * @param f zahl deren fakultaet berechnet werden soll
     * @return fakultaet der angegebenen zahl
     */
    public static int factorial(int f) {
        int v = 1;
        for (int i = 1; i <= f; i++) {
            v *= i;
        }
        return v;
    }
}
