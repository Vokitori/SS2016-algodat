package tsp;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Thomas Schierl und Viktoria Paschinger
 */
public class FileToNodeTest {
    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph(new File("vorgabe.txt") );
    }

}
