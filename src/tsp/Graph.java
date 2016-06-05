package tsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Voki
 */
public class Graph {

    public final Node nodes[];

    public final double adjazenzmatrix[][];

    public Graph(File file) throws FileNotFoundException {
        nodes = fileToNodes(file);
        adjazenzmatrix = generateAdjazenzmatrix();
    }

    private Node[] fileToNodes(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        int size = Integer.parseInt(scanner.nextLine());

        Node[] nodes = new Node[size];
        scanner.useDelimiter(" ");

        for (int i = 0; scanner.hasNextLine(); i++) {
            double x = Double.parseDouble(scanner.next());
            double y = Double.parseDouble(scanner.nextLine());
            nodes[i] = new Node(x, y);
        }
        return nodes;
    }

    private double[][] generateAdjazenzmatrix() {
        double[][] adjazenzmatrix = new double[nodes.length][nodes.length];
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y <= x; y++) {
                adjazenzmatrix[x][y] = (adjazenzmatrix[y][x] = distance(nodes[x], nodes[y]));
            }
        }
        return adjazenzmatrix;
    }

    public static final double distance(Node n, Node m) {
        double Δx = n.x - m.x;
        double Δy = n.y - m.y;
        return Math.sqrt(Δx * Δx + Δy * Δy);
    }

    public void printNodes() {
        for (int i = 0; i < nodes.length; i++) {
            System.out.println(nodes[i]);
        }
    }

    public void printAdjazenzmatrix() {
        for (int y = 0; y < adjazenzmatrix.length; y++) {
            for (int x = 0; x < adjazenzmatrix[y].length; x++) {
                System.out.print(((int) (adjazenzmatrix[y][x] * 100) / 100.0) + "\t");
            }
            System.out.println();
        }
    }

    public double getPathLength(Integer... nodeIndices) {
        double sum = 0;
        for (int i = 1; i < nodeIndices.length; i++) {
            sum += adjazenzmatrix[nodeIndices[i - 1]][nodeIndices[i]];
        }
        return sum;
    }

    public static final long getCpuTime() {
        return getCpuTime(Thread.currentThread());
    }

    public static final long getCpuTime(Thread thread) {
        return ManagementFactory.getThreadMXBean().getThreadCpuTime(thread.getId()) / 1000000;
    }
}
