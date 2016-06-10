/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp.heuristics;

import tsp.Graph;

/**
 *
 * @author voki
 */
public class NaturalSelection {

    private NaturalSelection() {

    }
/**
 * findet die kuerzeste rundreise via natural selection algorithmus
 * @param graph fuer den die kuerzeste strecke berechnet werden soll
 * @return string mit reihenfolge der nodes und laenge der rundreise
 */
    public static String naturalSelection(Graph graph) {
        Integer[] parent = new Integer[graph.adjazenzmatrix.length];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        return giveBirth(graph, parent);
    }

    /**
     * vertauscht zufaellig die reihenfolge der nodes und vergleicht neue laenge mit der alten.
     * ist die neue kuerzer wird die neue verwendet, sonst wird die alte behalten
     * @param graph graph dessen kuerzeste strecke berechnet werden soll
     * @param parent array mit reihenfolge der nodes
     * @return string mit reihenfolge der nodes und laenge der rundreise
     */
    private static String giveBirth(Graph graph, Integer[] parent) {
        
        String shortestPath = "Natural Selection:\nNode order: ";
        Integer[] rightChild = new Integer[parent.length];
        Integer[] leftChild = new Integer[parent.length];
        
        for (int i = 0; i < 8000; i++) {
            System.arraycopy(randomEdgeSwap(parent), 0, rightChild, 0, parent.length);
            for (int test = 0; test < parent.length; test++) {
            
        }
            System.arraycopy(randomEdgeSwap(parent), 0, leftChild, 0, parent.length);

            if (graph.getCycleLenght(parent) > graph.getCycleLenght(rightChild)) {
                System.arraycopy(rightChild, 0, parent, 0, parent.length);

            } else if (graph.getCycleLenght(parent) > graph.getCycleLenght(leftChild)) {
                System.arraycopy(rightChild, 0, parent, 0, parent.length);
            } else {
                continue;
            }
            
        }
        
        for (int j = 0; j < parent.length; j++) {
                shortestPath += parent[j];
            }
        return shortestPath += "\nPath lengh: " + graph.getCycleLenght(parent)+"\n";
    }
    
    
/**
 * vertauscht inhalte von zwei verschiedenen indizes
 * @param parent das array dessen inhalt zufällig getauscht werden soll
 * @return neues array mit vertauschten werten
 */
    private static Integer[] randomEdgeSwap(Integer[] parent) {
        Integer[] swappedArray = new Integer[parent.length];
        System.arraycopy(parent, 0, swappedArray, 0, parent.length);

        Integer first = (int) (Math.random() * parent.length);
        Integer second;

        do {
            second = (int) (Math.random() * parent.length);
        } while (second == first);

        Integer temp = swappedArray[first];
        
        
        swappedArray[first] = swappedArray[second];
        
        swappedArray[second] = temp;
        
        return swappedArray;

    }

}
