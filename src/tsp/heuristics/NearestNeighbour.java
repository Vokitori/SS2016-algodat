package tsp.heuristics;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import tsp.Graph;

/**
 * @author Thomas Schierl und Viktoria Paschinger
 */
public class NearestNeighbour {
    private static boolean visitedNodes[];
    private static String tripOrder = "Nearest Neighbour:";
    private static double sum = 0;
    private static int remainingNodes;

    private NearestNeighbour() {

    }

    /**
     * verbindet die nodes eines graphen indem eine verbindung von einem node
     * zum naechst gelegenen gemacht wird. beginnend bei einem beliebigen node
     *
     * @param graph graph dessen nodes verbunden werden sollen
     * @return gibt einen String mit der nodesreihenfolge und der laenge des
     * weges zurueck
     */
    public static String nearestNeighbour(Graph graph) {
        remainingNodes = graph.nodes.length - 1;
        return calcRoundTrip(graph);
    }

    /**
     * verbindet die nodes eines graphen
     *
     * @param graph graph dessen nodes verbunden werden sollen
     * @return gibt einen String mit der nodesreihenfolge und der laenge des
     * weges zurueck
     */
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
        tripOrder += ("\nSum: " + sum + "\n");
        return tripOrder;
    }

    /**
     * fuegt die kante zwischen anfangs und endknoten zum string hinzu
     *
     * @param startNodeIndex erster node der reihenfolge
     * @param currentNodeIndex letzter node des pfades
     * @param graph graph dessen nodes verbunden werden sollen
     */
    private static void backToStart(int startNodeIndex, int currentNodeIndex, Graph graph) {
        double lastEdge = graph.adjazenzmatrix[startNodeIndex][currentNodeIndex];
        tripOrder += (" ") + graph.nodes[currentNodeIndex].toString() + ("-> ") + (((int) (lastEdge * 100)) / 100.0);
        sum += lastEdge;
    }

    /**
     * sucht den node dessen kante zum momentanen node am kuerzesten ist
     *
     * @param currentNodeIndex der momentane knoten
     * @param graph graph dessen nodes verbunden werden sollen
     * @return gibt den index des naechsten knoten an
     */
    private static int getNextEdge(int currentNodeIndex, Graph graph) {
        double currentShortestEdge = 0;

        if (graph.adjazenzmatrix[currentNodeIndex][0] == 0) {
            currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][1];
        } else {
            currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][0];
        }

        int nextNode= 0;
        //System.out.println("--------------------------------");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(NearestNeighbour.class.getName()).log(Level.SEVERE, null, ex);
//        }
        for (int i = 0; i < graph.nodes.length; i++) {
            if (remainingNodes == 1) {
                for (int j = 0; j < visitedNodes.length; j++) {
                    if (visitedNodes[j] == false) {
                        currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][j];
                        nextNode = j;
                        //System.out.println("remaining nodes = 1");
                    }
                }
                break;
            }
            if (i == currentNodeIndex) {
              //  System.out.println("i == currentNodeIndex");
                continue;
            }
            if (visitedNodes[i]) {
                //System.out.println("visitedNodes ist true");
                continue;
            }
            if (graph.adjazenzmatrix[currentNodeIndex][i] == 0) {
               // System.out.println("adjazenzmatrix ist null");
                continue;
            }

            if (graph.adjazenzmatrix[currentNodeIndex][i] < currentShortestEdge) {
                currentShortestEdge = graph.adjazenzmatrix[currentNodeIndex][i];
                //System.out.println("next node wird gesetzt");
                nextNode = i;
            }
           // System.out.println("end of for");
            //printVisitedNodes();
            if (remainingNodes == 0) {
               // System.out.println("remaining 0");
            }
        }
        
        visitedNodes[nextNode] = true;
        remainingNodes -= 1;
        tripOrder += (" ") + graph.nodes[currentNodeIndex].toString() + ("-> ") + (((int) (currentShortestEdge * 100)) / 100.0);
        sum += currentShortestEdge;
        //System.out.println(nextNode);
        return nextNode;
    }
    private static void printVisitedNodes(){
        for (int i = 0; i < visitedNodes.length; i++) {
            System.out.println(visitedNodes[i]);
        }
    }

    /**
     * uberprueft ob alle nodes bereits verbunden sind
     *
     * @param visitedNodes array mit information ob alle nodes besucht wurden
     * @return gibt true zurueck wenn alle nodes besucht wurden
     */
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
