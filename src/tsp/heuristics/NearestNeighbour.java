package tsp.heuristics;

import tsp.Graph;
import tsp.Node;

/**
 * @author Voki
 */
public class NearestNeighbour {

    private static int visitedNodes[];
    private static String tripOrder = "RoundTrip: ";
    private static double sum = 0;

    private NearestNeighbour() {

    }

    public static String nearestNeighbour(Graph graph) {
        return calcRoundTrip(graph);
    }

    private static String calcRoundTrip(Graph graph) {

        int startNodeIndex = 0;
        int currentNodeIndex = startNodeIndex;
        visitedNodes = new int[graph.nodes.length];//if visitedNodes[i] == 0 -> not visited, if visitedNodes[i] == 1 -> visited
        for (int i = 0; i < visitedNodes.length; i++) {
            visitedNodes[i] = 0;
        }

        while (!allNodesVisited(visitedNodes)) {

            currentNodeIndex = getNextEdge(currentNodeIndex, graph);
        }
        tripOrder += ("->start \nSum: "+ sum);
        return tripOrder;
    }

    private static int getNextEdge(int currentNodeIndex, Graph graph) {
        double currentShortestEdge = 0;

        if (currentNodeIndex + 1 >= graph.nodes.length) {
            currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex - 1][currentNodeIndex];
        } else {
            currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex + 1][currentNodeIndex];
        }

        int nextNode = 0;
        for (int i = 0; i < graph.nodes.length; i++) {
            if (i == currentNodeIndex) {
                continue;
            }
            if (visitedNodes[i] > 0) {
                continue;
            }
            if (graph.adjazenzmatrix[currentNodeIndex][i] < currentShortestEdge) {
                currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][i];
                nextNode = i;
            }

        }
        visitedNodes[nextNode] = 1;
        tripOrder += (" ") + graph.nodes[currentNodeIndex].toString()+ ("-> ") + (((int)(currentShortestEdge*100))/100.0);
        sum += currentShortestEdge;

        return nextNode;
    }

    private static boolean allNodesVisited(int visitedNodes[]) {
        boolean allVisited = true;
        for (int i = 0; i < visitedNodes.length; i++) {
            if (visitedNodes[i] == 0) {
                allVisited = false;
                break;
            }
        }
        return allVisited;
    }

}
