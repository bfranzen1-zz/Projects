/* Blake Franzen 
 * 4/3/2017
 * Section AD - Chloe Lathe
 * Homework 1
 * This class tests my implementation of a queue
 * against java's implementation of a queue
 */
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    /*Your functions (on the left) correspond to the following java Queue functions (on the right)
     * toTest.enqueue(String a) = toCompare.add(String a)
     * toTest.dequeue() = toCompare.poll()
     * toTest.front() = toCompare.peek()
     *
     * This private class performs this interface for you.
     */
    private static class JavaQueue{
        //DO NOT EDIT THIS CLASS
        Queue<String> queue;
        protected JavaQueue(){
            queue = new LinkedList<String>();
        }
        protected void enqueue(String a){
            queue.add(a);
        }
        protected String dequeue(){
            return queue.poll();
        }
        protected String front(){
            return queue.peek();
        }
    }

    //runs tests, outputs fail if any of the tests failed
    public static void main(String[] args){
        if (!testEmpty(new ListQueue(),new JavaQueue())) {
            System.out.print("fail");
        } else if (!testOne(new ListQueue(),new JavaQueue())) {
            System.out.print("fail");
        } else if (!testMany(new ListQueue(),new JavaQueue())) {
            System.out.print("fail");
        } else {
            System.out.print("pass");
        }

    }
    
    //method that tests my implementation against java's when both are expected
    //to be empty. Takes in ListQueue and JavaQueue
    public static boolean testEmpty(ListQueue yourQueue, JavaQueue correctQueue) {
        if (yourQueue.front() != correctQueue.front()) {
            return false;
        } else if (yourQueue.dequeue() != correctQueue.dequeue()){
            return false;
        } else {
            yourQueue.enqueue("test");
            correctQueue.enqueue("test");
            if (!yourQueue.front().equals(correctQueue.front())) {
                return false;
            }
        }
        return true;
    }

    //method that tests my implementation against java's when both are expected
    //to have one value or node in them. Takes in ListQueue and JavaQueue
    public static boolean testOne(ListQueue a, JavaQueue b){
        a.enqueue("test1");
        b.enqueue("test1");
        if ((a.front() != b.front())) {
            return false;
        } else if (!a.dequeue().equals(b.dequeue())) {
            return false;
        } else {
            a.enqueue("test2");
            b.enqueue("test2");
            if (!a.dequeue().equals(b.dequeue())) {
                return false;
            }
        }
        return true;
    }

    //method that tests my implementation against java's when both are expected
    //to have several values or nodes in them. Takes in ListQueue and JavaQueue
    public static boolean testMany(ListQueue a, JavaQueue b){
        return test(a, b, 10) && test(a, b, 12) && test(a, b, 15);
    }
    
    //helper method for testMany that takes in a ListQueue, JavaQueue, and integer representing
    //the number of values to add. adds desired number of strings to each queue and tests
    //if both queues match. 
    private static boolean test(ListQueue a, JavaQueue b, int numWords) {
        boolean pass = true;
        for (int i = 0; i < numWords; i++) {
            a.enqueue("test" + i);
            b.enqueue("test" + i);
            i++;
        }

        if (!a.front().equals(b.front())) {
            pass = false;
        } else {
            while (a.front() != null && b.front() != null) {
                if (!a.dequeue().equals(b.dequeue())) {
                    pass = false;
                }
            }
        }
        return pass;
    }
}
