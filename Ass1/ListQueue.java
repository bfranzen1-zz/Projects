/* Blake Franzen
 * 4/3/2017
 * Section AD - Chloe Lathe
 * Homework 1
 * This class represents an implementation of a queue
 * using a Linked List data structure
 */

public class ListQueue {
    
	//class used to keep track of data and 
	//link to next node for queue implementation
	private class Node{
        private String data;
        private Node next;

        public Node (String data) {
            this(data, null);
        }
        public Node (String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    
    private Node front; //keeps track of front of queue
    
    //constructs queue initializing front to null
    public ListQueue(){
        front = null;
    }
    
    //function that adds String toInput to queue
    public void enqueue(String toInput) {
        if (front == null) {
            front = new Node(toInput);
        } else {
            Node temp = front;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(toInput);
         }
    }

    //function that returns String at front of queue
    //and removes it from the queue
    public String dequeue(){
        if (front != null) {
            String temp = front.data;
            front = front.next;
            return temp;
        } else {
            return null;
        }
    }
    
    //function that returns the String at the front
    //of the queue without removing it 
    public String front(){
        if (front != null) {
            return front.data;
        } else {
            return null;
        }
    }
}

