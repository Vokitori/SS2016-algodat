package tsp;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Voki
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException{
        File numbers = new File("vorgabe.txt");
        Graph matrix = new Graph(numbers);
        matrix.printAdjazenzmatrix();
        
    }

}
