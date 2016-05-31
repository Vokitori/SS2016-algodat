package tsp;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Voki
 */
public class Graph {
    
    private Node nodes[];
    
    private double adjazenzmatrix[][];

    public Graph(File file) throws FileNotFoundException {
        fileToNodes(file);
    }

    private void fileToNodes(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        
        int size = Integer.parseInt(scanner.nextLine());
        
        nodes = new Node[size];
        scanner.useDelimiter(" ");
        
        
        for (int i = 0; scanner.hasNextLine(); i++) {
            
            double x = Double.parseDouble(scanner.next());
            double y = Double.parseDouble(scanner.nextLine());
            nodes[i] = new Node(x, y);
            System.out.println(nodes[i].toString());
        }
       
    }
    

}
