package tsp.heuristics;

import java.util.Arrays;
import tsp.Graph;
import tsp.Node;

/**
 * @author Voki
 */
public class NearestNeighbour {

    private static boolean visitedNodes[];
    private static String tripOrder = "RoundTrip: ";
    private static double sum = 0;
    private static int remainingNodes;
   

    private NearestNeighbour() {

    }

    public static String nearestNeighbour(Graph graph) {
        remainingNodes = graph.nodes.length -1;
        return calcRoundTrip(graph);
    }

    private static String calcRoundTrip(Graph graph) {

        int startNodeIndex = 0;
        int currentNodeIndex = startNodeIndex;
        visitedNodes = new boolean[graph.nodes.length];
        for (int i = 0; i < visitedNodes.length; i++) {
            visitedNodes[i] = false;
        }
        visitedNodes[0] = true;
        while (!allNodesVisited(visitedNodes)) {
            
            currentNodeIndex = getNextEdge(currentNodeIndex, graph);
        }
        backToStart(startNodeIndex, currentNodeIndex, graph);
        tripOrder += ("\nSum: "+ sum);
        return tripOrder;
    }
    
    private static void backToStart(int startNodeIndex, int currentNodeIndex, Graph graph){
        double lastEdge = graph.adjazenzmatrix[startNodeIndex][currentNodeIndex];
        tripOrder += (" ") + graph.nodes[currentNodeIndex].toString()+ ("-> ") + (((int)(lastEdge*100))/100.0);
        sum += lastEdge;
    }

    private static int getNextEdge(int currentNodeIndex, Graph graph) {
        double currentShortestEdge = 0;
        
        if(graph.adjazenzmatrix[currentNodeIndex][0] == 0){
            currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][1];
        }else{
            currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][0];
        }

        int nextNode = 0;
        for (int i = 0; i < graph.nodes.length; i++) {
            
            if (remainingNodes == 1) {
                for (int j = 0; j < visitedNodes.length; j++) {
                    if(visitedNodes[j] == false){
                        currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][j];
                        nextNode = j;
                    }
                }
               break;
            }
            if (i == currentNodeIndex) {
                continue;
            }
            if (visitedNodes[i]) {
                continue;
            }
            if(graph.adjazenzmatrix[currentNodeIndex][i] == 0){
                continue;
            }
            
            if (graph.adjazenzmatrix[currentNodeIndex][i] < currentShortestEdge) {
                currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][i];
                nextNode = i;
            }
            
        }
        visitedNodes[nextNode] = true;
        remainingNodes -= 1;
        tripOrder += (" ") + graph.nodes[currentNodeIndex].toString()+ ("-> ") + (((int)(currentShortestEdge*100))/100.0);
        sum += currentShortestEdge;

        return nextNode;
    }

    private static boolean allNodesVisited(boolean visitedNodes[]) {
        boolean allVisited = true;
        for (int i = 0; i < visitedNodes.length; i++) {
            if (!visitedNodes[i]) {
                allVisited = false;
                break;
            }
        }
        return allVisited;
    }
    

}
