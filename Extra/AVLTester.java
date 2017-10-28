/**
 * Blake Franzen
 * Section AD - Chloe Lathe
 * 5/28/17
 * Extra Assignment - AVLTree Tester
 *
 * This program tests my AVL implementation by
 * comparing the results returned from my BFS iterator
 * to the results that would be seen from a correct
 * AVL Tree implementation with the same key/value pairs.
 * Uses a text file containing keys to construct tree
 * and another text file containing a solution to compare to.
 */

import java.util.*;
import java.io.*;

public abstract class AVLTester {
    public static void main(String[] args) {
        //file containing String keys
        AVLTree tree = buildTree(args[0]); //buildtree from file args[0]
        if (verifyAVL(tree, args[1])) { //compare values from tree and args[1]
            System.out.println("Test passed! Tree verified as AVL!");
        } else {
            System.out.println("NOT AN AVL TREE!");
        }
    }

    //returns true or false if tree satisfies AVL implementation
    //takes in tree toTest and string solution representing file path for BFS solution
    public static boolean verifyAVL(StringTree toTest, String solution){
        Scanner sol = null;
        Iterator<String> it = toTest.getBFSIterator();
        try {
            //contains BFS solution for specific tree
            sol = new Scanner(new File(solution));
        } catch(FileNotFoundException e) {
            System.out.println("Can't find file, check path");
        }
        while (it.hasNext()) {
            if (!it.next().equals(sol.next())) { //compare BFS iterator next() with expected BFS of solution
                return false;
            }
        }
        return true;

    }

    //builds tree from string file, throws exception if file not found
    //file must contain strings representing keys to insert, inserts empty
    //value for each key being that they aren't necessary to verify balance
    public static AVLTree buildTree(String file) {
        Scanner s = null;

        try {
            s = new Scanner(new File(file));
        } catch(FileNotFoundException e) {
            System.out.println("Invalid File");
            System.exit(2);
        }
        AVLTree temp = new AVLTree();
        while (s.hasNext()) {
            try {
                String key = s.next();
                String value = "  ";
                temp.insert(key, value);
            } catch(NoSuchElementException e) {
                System.out.println("Invalid input file format (must be key/value pairs!)");
                System.exit(3);
            }
        }
        return temp;
    }
}