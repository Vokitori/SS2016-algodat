package tsp.heuristics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tsp.Graph;
import tsp.Ref;

/**
 * @author link
 */
public class FullEnumeration {

    private static Integer[][] permutations;
    private static int y;
    private static Integer[] values;

    private FullEnumeration() {
    }

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

    private static double distance(Graph g, Integer start, Integer end) {
        return g.adjazenzmatrix[start][end];
    }

    private static double distanceSum(Graph g, Integer start, Integer... node) {
        double sum = distance(g, start, node[0]);
        for (int i = 1; i < node.length; i++) {
            sum += distance(g, node[i - 1], node[i]);
        }
        sum += distance(g, node[node.length - 1], start);
        return sum;
    }

    private static void permute(List<Integer> arr, Integer k) {
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            permutations[y++] = arr.toArray(new Integer[0]);
        }
    }

    private static void print2dIntArray(Integer[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    private static int[] toIntArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    private static Integer[][] to2dIntArray(List<Integer[]> list) {
        Integer[][] l = new Integer[list.size()][list.get(0).length];
        for (int i = 0; i < l.length; i++) {
            System.arraycopy(list.get(i), 0, l[i], 0, l[i].length);
        }
        return l;
    }

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
     * ONLY FOR POSITIVE INTEGER.
     *
     * @param f
     * @return
     */
    public static int factorial(int f) {
        int v = 1;
        for (int i = 1; i <= f; i++) {
            v *= i;
        }
        return v;
    }
}
