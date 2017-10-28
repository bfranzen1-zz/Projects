
public abstract class BinaryHeapTest {

	public static void main(String[] args) {
		BinaryHeap test = new BinaryHeap();
		BinaryHeap test2 = new BinaryHeap(10);
		System.out.println("testEmpty pass: " + testEmpty(test) + " " + testEmpty(test2));
		System.out.println("testOne pass: " + testOne(test) + " " + testOne(test2));
		System.out.println("testMany pass: " + testMany(test) + " " + testMany(test2));
	}

	//tests the functions of the BinaryHeap when it is empty
	public static boolean testEmpty(BinaryHeap toTest){
		if (!toTest.isEmpty()) {
			System.out.println("failed at isEmpty");
			return false;
		} else if (toTest.size() != 0) {
			System.out.println("failed at size");
			return false;
		} else if (toTest.findMin() != null) {
			System.out.println("failed at findMin");
			return false;
		} else if (toTest.deleteMin() != null) {
			System.out.println("failed at deleteMin");
			return false;
		} else if (toTest.changePriority("test", 1)) {
			System.out.println("failed at changePriority");
			return false;
		} else {
			toTest.insert("test", 1);
			if (!toTest.findMin().equals("test") || !toTest.deleteMin().equals("test")) {
				System.out.println("failed after insert");
				return false;
			}
			toTest.makeEmpty();
		}
		return true;
	}

	//tests the functions of the BinaryHeap when there is one value inside it
	public static boolean testOne(BinaryHeap toTest){
		toTest.insert("test1", 2);
		if (toTest.isEmpty()) {
			System.out.println("failed at isEmpty");
			return false;
		} else if (toTest.size() == 0) {
			System.out.println("failed at size");
			return false;
		} else if (toTest.findMin() == null) {
			System.out.println("failed at findMin");
			return false;
		} else if (!toTest.deleteMin().equals("test1")) {
			System.out.println("failed at deleteMin");
			return false;
		} else {
			toTest.insert("test1", 3);
			if (!toTest.changePriority("test1", 1)) {
				System.out.println("failed at changePriority");
				return false;
			}
			if (!toTest.findMin().equals("test1") || !toTest.deleteMin().equals("test1")) {
				System.out.println("failed after insert");
				return false;
			}
			toTest.makeEmpty();
		}
		return true;
	}

	//tests the functions of the BinaryHeap when there are a bunch of values in it
	public static boolean testMany(BinaryHeap toTest){
		return helperMany(toTest, 15) && helperMany(toTest, 25) && helperMany(toTest, 50);
	}

	//Takes in BinaryHeap to test and the desired size of said heap.
	//tests various functions of heap, prints deleteMin until empty,
	//values should be in increasing order.
	private static boolean helperMany(BinaryHeap toTest, int size) {
		BinaryHeap comp = new BinaryHeap();
		for (int i = 1; i < size; i++) {
			toTest.insert("test" + i, i / 2);
			comp.insert("test" + i, i / 2);
		}
		if (toTest.isEmpty()) {
			System.out.println("failed at isEmpty");
			return false;
		} else if (toTest.size() == 0) {
			System.out.println("failed at size");
			return false;
		} else if (toTest.findMin() == null) {
			System.out.println("failed at findMin");
			return false;
		} else if (!toTest.deleteMin().equals("test1")) {
			System.out.println("failed at deleteMin");
			return false;
		} else {
			if (!toTest.changePriority("test2", 4)) {
				System.out.println("failed at changePriority");
				return false;
			}
			comp.deleteMin(); //to make min value equal to toTest() root
			comp.changePriority("test2", 4); //update test2 to match toTest
			int deleteSize = toTest.size(); //toTest size changes
			for(int i = 0; i < deleteSize; i++) {
				if (!toTest.deleteMin().equals(comp.deleteMin())) {
					System.out.println("failed at delete");
					return false;
				}
			}
		}
		return true;
	}
}