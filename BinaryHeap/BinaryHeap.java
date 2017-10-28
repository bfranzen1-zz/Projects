
public class BinaryHeap implements PriorityQueue{
	private class HeapData{
		private String data;
		private int priority;

		//constructs HeapData with given string and int priority
		protected HeapData(String dat,int pri){
			data = dat;
			priority = pri;
		}

		//changes priority to given int NewPri
		protected void changePriority(int newPri){
			priority = newPri;
		}

		//returns int representing current priority
        public int getPri() {
		    return priority;
        }

        //returns String representing current data
        public String getData() {
		    return data;
        }
		
	}
	
	private HeapData[] heap;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ROOT = 1; //Root Node


    //constructs empty BinaryHeap, has default size
	public BinaryHeap(){
        heap = new HeapData[DEFAULT_CAPACITY];
        size = 0;
	}

	//constructs empty BinaryHeap with given size startArray
	public BinaryHeap(int startArray){
        heap = new HeapData[startArray];
        size = 0;
	}

	//returns true or false if BinaryHeap is empty
	public boolean isEmpty(){
		return size == 0;
	}

	//returns int representing current size of heap
	public int size(){
		return size;
	}

	//returns String representing min value in heap
	public String findMin(){
        if (isEmpty()) {
            return null;
        }
        return heap[ROOT].data;
	}

	//inserts given String data with int priority into heap
	public void insert(String data, int priority){
        if (size >= heap.length - 1) {
            resize();
        }
        size++;
        heap[size] = new HeapData(data, priority);
        percolateUp();
	}

	//returns String representing min value in heap, deletes
    //value from heap as well
	public String deleteMin(){
        if (isEmpty()) {
            return null;
        }
        String min = findMin();
        heap[ROOT] = heap[size];
        size--;
        percolateDown();
        return min;
	}

	//makes the heap empty
	public void makeEmpty(){
        heap = new HeapData[DEFAULT_CAPACITY];
        size = 0;
	}

	//returns true or false if String data exists in heap, if it does
    //changes priority of that data to int newPri
	public boolean changePriority(String data, int newPri){
		for (int i = 1; i <= size; i++) {
		    if (heap[i].getData().equals(data)) {
		        heap[i].changePriority(newPri);
                if (checkParent(i)) {
                    percolateUp();
                } else {
                    percolateDown();
                }
		        return true;
            }
        }
        return false;
	}

    //swaps value down heap until it satisfies heap order property
	private void percolateDown() {
        int index = ROOT;
        while (checkChilds(index)) {
            int smaller = leftChild(index); //left child

            if (hasRightChild(index) && heap[rightChild(index)].getPri() < heap[smaller].getPri()) {
                smaller = rightChild(index); // right child
            }
            if (heap[smaller].getPri() < heap[index].getPri()) {
                swap(index, smaller);
                index = smaller;
            }
        }
    }

    //swaps value down until heap order property satisfied
    private void percolateUp() {
        int start = size; //index to percolate up
        while (checkParent(start)) {
            swap(start, parent(start));
            start = parent(start);
        }
    }

    //resizes array if it is full to twice original size
    private void resize() {
        HeapData[] temp = new HeapData[heap.length * 2];
        for (int i = 0; i <= size; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }

    //returns true or false if parent node priority is less than current node
    //priority
    private boolean checkParent(int index) {
        if (hasParent(index)) {
            if (heap[parent(index)].getPri() > heap[index].getPri()) {
                return true;
            }
        }
        return false;
    }

    //returns true or false if either child node priorities are less than current node
    //priority
    private boolean checkChilds(int index) {
	    if (hasLeftChild(index)) {
            if (heap[rightChild(index)].getPri() < heap[index].getPri() ||
                    heap[leftChild(index)].getPri() < heap[index].getPri()) {
                return true;
            }
        }
        return false;
    }

    //returns true or false if node at int index has a left child
    private boolean hasLeftChild(int index) {
	    return leftChild(index) <= size && heap[leftChild(index)].getData() != null;
    }
    //returns true or false if node at int index has a right child
    private boolean hasRightChild(int index) {
        return (rightChild(index)) <= size && heap[rightChild(index)] != null;
    }

    //returns true or false if node at int index has a parent
    private boolean hasParent(int index) {
	    return heap[parent(index)] != null;
    }

    //swaps nodes at index1 and index2
    private void swap(int index1, int index2) {
	    HeapData temp = heap[index1];
	    heap[index1] = heap[index2];
	    heap[index2] = temp;
    }

    //returns int representing the index at which the given index's left child is
    private int leftChild(int index) {
	    return index * 2;
    }

    //returns int representing the index at which the given index's right child is
    private int rightChild(int index) {
	    return index * 2 + 1;
    }

    //returns int representing the index at which the given index's parent is
    private int parent(int index) {
	    return index / 2;
    }
}