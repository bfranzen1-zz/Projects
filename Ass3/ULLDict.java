/*Blake Franzen
4/16/17
Section AD - Chloe Lathe
Homework 3 - Dictionaries
This class represents an implementation of a dictionary using
an unsorted linked list
 */

public class ULLDict implements Dictionary {

	//private class that constructs dictionary key/value pairs
	private class Node{
		private int key;
		private String value;
		private Node next;

		//constructs Node with given int key, String value pair
		protected Node(int key, String value) {
			this(key, value, null);
		}

		//constructs Node with given int key, String value, Node next
		protected Node(int key, String value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
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

	private Node first;

	//constructs empty dictionary
	public ULLDict() {
		first = null;
	}

	//inserts given key/value pair, array resizes
	//if it needs to
	public void insert(int key, String value) {
		if (first == null) {
			first = new Node(key, value);
		} else {
			Node temp = new Node(key, value, first);
			first = temp;
		}
	}

	//uses given int key to search for key/value pair
	//returns String value if found, null if not
	public String find(int key) {
		Node temp = first;
		while (temp != null) {
			if (temp.getKey() == key) {
				return temp.getVal();
			}
			temp = temp.next;
		}
		return null;
	}

	//finds (if it can) and deletes key/value pair given int key,
	//returns true or false if it is able to find the pair
	public boolean delete(int key) {
		Node temp = first;
		if (temp != null) {
			if (temp.getKey() == key) {
				first = first.next;
				return true;
			} else {
				Node prev = null;
				while (temp.next != null) {
					if (temp.next.getKey() == key) {
						temp.next = temp.next.next;
						return true;
					}
					prev = temp;
					temp = temp.next;
				}
				if (temp.getKey() == key) {
					prev.next = null;
					return true;
				}
			}
		}
		return false;
	}
}
