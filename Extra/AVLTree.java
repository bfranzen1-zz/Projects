/**
 * Blake Franzen
 * Section AD - Chloe Lathe
 * 5/28/17
 * Extra Assignment AVLTree
 * This program represents an implementation of an AVLTree algorithm
 * It creates a tree of String key/value pairs and balances the nodes
 * to satisfy AVL properties
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AVLTree implements StringTree{

    private class AVLNode{
        //Do not change these variable names
        String key;
        String value;
        AVLNode left;
        AVLNode right;
        int height;

        //Place any additional fields you need here

        //constructs AVLNode with key, value, left branch, right branch
        public AVLNode(String key, String value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
        //constructs AVLNode with key, value, makes left/right branch null
        public AVLNode(String key, String value) {
            this(key, value, null, null);
        }

        //returns string representing key of this AVLNode
        public String getKey() {
            return key;
        }

        //returns String representing value of this AVLNode
        public String getVal() {
            return value;
        }
    }

    AVLNode root;
    private int size;

    //constructs AVLTree
    public AVLTree() {
        this.root = null;
        size = 0;
    }

    //makes AVLTree empty
    public void makeEmpty() {
        root = null;
    }

    //returns size of tree
    public int size() {
        return size;
    }

    //returns height of desired AVLNode node
    public int height(AVLNode node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    //takes in String key and String value. Inserts/balances node into tree.
    //duplicate keys not allowed. Throws IllegalArgumentException if user
    //attempts to enter duplicate key
    public void insert(String key, String value) {
        // TODO Insert the <key,value> pair into the AVLTree
        // Throw an IllegalArgumentException if the client attempts to insert a duplicate key
        if (find(key, root) != null && find(key, root).equals(key)) {
            throw new IllegalArgumentException("Duplicate key");
        }
        root = insert(key, value, root);
        size++;
    }

    //Private helper method that takes String key, String value, AVLNode node.
    //returns result of inserting and balancing key/value pair.
    private AVLNode insert(String key, String value, AVLNode node) {
        if (node == null) {
            return new AVLNode(key, value);
        } else if (key.compareTo(node.getKey()) > 0) {
            node.right = insert(key, value, node.right);
        } else {
            node.left = insert(key, value, node.left);
        }
        return makeBalance(node);
    }

    //private helper method that returns balanced AVLNode node. Checks
    //to see what balance operation needs to be completed to preserve AVL structure.
    private AVLNode makeBalance(AVLNode node) {
        if (node != null) {
            if (height(node.left) - height(node.right) > 1) { //left subtree unbalanced
                if (height(node.left.left) >= height(node.left.right)) {
                    node = rotateLeft(node);
                } else { //left right rotation
                    node = doubleLeft(node);
                }
            } else if (height(node.right) - height(node.left) > 1){ //right subtree unbalanced
                if (height(node.right.right) >= height(node.right.left)) {
                    node = rotateRight(node);
                } else { //right left rotation
                    node = doubleRight(node);
                }
            }
            node.height = Math.max(height(node.left), height(node.right)) + 1; //update height of node
        }
        return node;
    }

    //private helper method that performs single left rotation on
    //AVLNode node and returns result
    private AVLNode rotateLeft(AVLNode node) {
        AVLNode temp = node.left;
        node.left = temp.right;
        temp.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return temp;
    }

    //private helper method that performs single right rotation on
    //AVLNode node and returns result
    private AVLNode rotateRight(AVLNode node) {
        AVLNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return temp;
    }

    //private helper method that performs left right rotation on
    //AVLNode node and returns result
    private AVLNode doubleLeft(AVLNode node) {
        node.left = rotateRight(node.left);
        return rotateLeft(node);
    }

    //private helper method that performs right left rotation on
    //AVLNode node and returns result
    private AVLNode doubleRight(AVLNode node) {
        node.right = rotateLeft(node.right);
        return rotateRight(node);
    }

    //method that takes String key, searches for it in the
    //tree and returns the value of that node if able to do so.
    //Throws NoSuchElementException if not found.
    public String find(String key) {
        if (find(key, root) == null && root != null) {
            throw new NoSuchElementException("Node not in tree");
        }
        return find(key, root);
    }

    //uses binary search to find node with String key starting at AVLNode node
    //returns null if unable to find
    private String find(String key, AVLNode node) {
        if (node != null) {
            if (node.getKey().equals(key)) {
                return node.getVal();
            } else {
                if (key.compareTo(node.getKey()) > 0) {
                    return find(key, node.right);
                } else {
                    return find(key, node.left);
                }
            }
        } else {
            return null;
        }
    }

    //returns BFS Iterator
    public Iterator<String> getBFSIterator() {
        return new BFSIterator();
    }

	private class BFSIterator implements Iterator<String>{

        private AVLNode[] toVisit; //nodes to keep track of
        private int curr; //curr position in array
        private int addPos; //index to add AVLNode

        public BFSIterator() {
            toVisit = new AVLNode[size];
            curr = 0;
            toVisit[addPos] = root; //add root first
            addPos = 1;
        }

        //returns whether or not iterator has another value to return
		public boolean hasNext() {
            return curr != toVisit.length;
		}

		//returns String key of next AVLNode in iterator array, adds children
        //of node (left then right)
		public String next() {
            if (hasNext()) {
                AVLNode temp = toVisit[curr];
                curr++;
                if (temp != null) {
                    if (temp.left != null) {
                        toVisit[addPos++] = temp.left;
                    }
                    if (temp.right != null) {
                        toVisit[addPos++] = temp.right;
                    }
                    return temp.getKey();
                }
            }
            return null;
		}
	}
}