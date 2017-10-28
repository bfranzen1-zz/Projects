/*Blake Franzen
4/16/17
Section AD - Chloe Lathe
Homework 3 - Dictionaries
This class represents an implementation of a dictionary using
an unsorted array
 */

public class UADict implements Dictionary {

	//private class that constructs dictionary key/value pairs
	private class UAData {
		private String value;
		private int key;

		//constructs UAData with given int key and String value
		protected UAData(int key, String value) {
			this.value = value;
			this.key = key;
		}

		//returns String representing value in key/value pair
		public String getVal() {
			return value;
		}

		//returns int representing key in key/value pair
		public int getKey() {
			return key;
		}
	}

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private UAData[] dict;

	//constructs empty dictionary with default size
	public UADict() {
		dict = new UAData[DEFAULT_SIZE];
		size = 0;
	}

	//inserts given key/value pair, array resizes
	//if it needs to
	public void insert(int key, String value) {
		if (size == dict.length) {
			resize();
		}
		dict[size] = new UAData(key, value);
		size++;
	}

	//uses given int key to search for key/value pair
	//returns String value if found, null if not
	public String find(int key) {
		for (int i = 0; i < size; i++) {
			if (dict[i].getKey() == key) {
				return dict[i].getVal();
			}
		}
		return null;
	}

	//finds (if it can) and deletes key/value pair given int key,
	//returns true or false if it is able to find the pair
	public boolean delete(int key) {
		for (int i = 0; i < size; i++) {
			if (dict[i].getKey() == key) {
				dict[i] = dict[size - 1];
				dict[size - 1] = null;
				size--;
				return true;
			}
		}
		return false;
	}

	//private helper method that resizes the array if it reaches
	//capacity
	private void resize() {
		UAData[] temp = new UAData[dict.length * 2];
		for (int i = 0; i < size; i++) {
			temp[i] = dict[i];
		}
		dict = temp;
	}
}
