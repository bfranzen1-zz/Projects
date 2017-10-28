/*Blake Franzen
4/16/17
Section AD - Chloe Lathe
Homework 3 - Dictionaries
This class represents an implementation of a dictionary using
a binary search tree
 */

public class BSTDict implements Dictionary {

	//private class that constructs dictionary key/value pairs
	private class BSTNode{
		private int key;
		private String value;
		private BSTNode left;
		private BSTNode right;

		//constructs BSTNode with given int key, String value
		protected BSTNode(int key, String value) {
			this(key, value, null, null);
		}

		//constructs BSTNode with given int key, String value, and left/right BSTNodes
		protected BSTNode(int key, String value, BSTNode left, BSTNode right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}

		//returns String representing value in key/value pair
		public int getKey() {
			return key;
		}

		//returns int representing key in key/value pair
		public String getVal() {
			return value;
		}
	}

	private BSTNode root;

	//inserts given key/value pair, array resizes
	//if it needs to
	public void insert(int key, String value) {
		BSTNode node = search(key, value, root);
		root = node == null ? root : node; //ignores duplicate keys for testing
	}

	//private helper method that takes in int key, String value,
	//and BSTNode node, returns BSTNode to be inserted into dictionary
	private BSTNode search(int key, String value, BSTNode node) {
		if (node == null) {
			node = new BSTNode(key, value);
		} else if (key > node.getKey()) {
			node.right = search(key, value, node.right);
		} else if (key == node.getKey()) {
			return null;
		} else {
			node.left = search(key, value, node.left);
		}
		return node;
	}

	//uses given int key to search for key/value pair
	//returns String value if found, null if not
	public String find(int key) {
		return find(key, root);
	}

	//private helper method that takes in int key and BSTNode node
	//uses binary search to find desired key/value pair, if found returns
	//String value, returns null if not found
	private String find(int key, BSTNode node) {
		if (node != null) {
			if (node.getKey() == key) {
				return node.getVal();
			} else {
				if (key > node.getKey()) {
					return find(key, node.right);
				} else {
					return find(key, node.left);
				}
			}
		} else {
			return null;
		}
	}

	//finds (if it can) and deletes key/value pair given int key,
	//returns true or false if it is able to find the pair
	public boolean delete(int key) {
		if (find(key) == null) {
			return false;
		} else {
			root = delete(key, root);
			return true;
		}
	}

	//private helper method that uses given int key and BSTNode node to locate
	//node to be deleted. Returns new tree with node deleted, preserves binary search
	//property of tree.
	private BSTNode delete(int key, BSTNode node) {
		if (node != null) {
			if (key > node.getKey()) {
				node.right = delete(key, node.right);
			} else if (key < node.getKey()) {
				node.left = delete(key, node.left);
			} else {
				if (node.right == null && node.left == null) {
					return null;
				} else if (node.left == null) {
					node = node.right;
				} else if (node.right == null) {
					node = node.left;
				} else {
					BSTNode temp = minNode(node.right);
					node.right = delete(temp.getKey(), node.right);
					temp.left = node.left;
					temp.right = node.right;
					node = temp;
				}
			}
		}
		return node;
	}

	//private helper method that returns the minimum
	//BSTNode in a given BSTNode node tree
	private BSTNode minNode(BSTNode node) {
		if (node.left == null) {
			return node;
		} else {
			return minNode(node.left);
		}
	}
}
