/*Blake Franzen
4/16/17
Section AD - Chloe Lathe
Homework 3 - Dictionaries
This class represents an implementation of a dictionary using
a sorted array
 */

import java.util.Arrays;

public class SADict implements Dictionary {

	//private class that constructs dictionary key/value pairs
	private class SAData {
		private String value;
		private int key;

		//constructs SAData with given int key and String value
		protected SAData(int key, String value) {
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
	private SAData[] dict;

	//constructs empty dictionary with default size
	public SADict() {
		dict = new SAData[DEFAULT_SIZE];
		size = 0;
	}

	//inserts given key/value pair, array resizes
	//if it needs to
	public void insert(int key, String value) {
		if (size + 1 >= dict.length) {
			resize();
		}
		if (size == 0) { //no elements in array
			dict[0] = new SAData(key, value);
		} else {
			int spot = insSearch(key);

			if (spot == -1) { //no spot, don't do anything
				return;
			}

			for (int i = size - 1; i >= spot; i--) {
				dict[i + 1] = dict[i];
			}
			dict[spot] = new SAData(key, value);
		}
		size++;
	}

	//private helper method that determines where key/value pair
	//needs to be inserted into sorted array, given int key.
	//Uses binary search, returns index to add pair at. Returns
	//-1 if pair not found.
	private int insSearch(int key) {
		if (dict[0].getKey() > key) {
			return 0;
		} else if (dict[size - 1].getKey() < key) {
			return size;
		} else {
			int min = 0;
			int max = size - 1;
			while (min <= max) {
				int mid = (min + max) / 2;
				if (dict[mid].getKey() > key && dict[mid - 1].getKey() < key) {
					return mid;
				} else {
					if (dict[mid].getKey() < key) {
						min = mid + 1;
					} else {
						max = mid - 1;
					}
				}
			}
			return -1; //not found
		}
	}

	//uses given int key to search for key/value pair
	//returns String value if found, null if not
	public String find(int key) {
		if (binSearch(key) == -1) {
			return null;
		} else {
			return dict[binSearch(key)].getVal();
		}
	}

	//finds (if it can) and deletes key/value pair given int key,
	//returns true or false if it is able to find the pair
	public boolean delete(int key) {
		if (binSearch(key) == -1) {
			return false;
		} else {
			int spot = binSearch(key);
			//System.out.println(Arrays.toString(dict));
			for (int i = spot; i < size; i++) {
				dict[i] = dict[i + 1];
			}
			size--;
			return true;
		}
	}

	//helper method that resizes array if it reaches maximum capacity
	private void resize() {
		SAData[] temp = new SAData[dict.length * 2];
		for (int i = 0; i < size; i++) {
			temp[i] = dict[i];
		}
		dict = temp;
	}

	//private helper method that searches for key/value pair
	//given int key, returns index at which pair exists,
	//returns -1 if it can't find pair.
	private int binSearch(int key) {
		int min = 0;
		int max = size - 1;
		while (min <= max) {
			int mid = (min + max) / 2;
			if (dict[mid].getKey() == key) {
				return mid;
			} else {
				if (dict[mid].getKey() < key) {
					min = mid + 1;
				} else {
					max = mid - 1;
				}
			}
		}
		return -1; //not found
	}
}
