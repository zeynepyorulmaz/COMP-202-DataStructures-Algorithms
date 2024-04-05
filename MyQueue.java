package ds;

public class MyQueue {
	
    private Node head;
    private Node tail;
    private int size = 0;

    // Adds a new element to the end of the queue
    public void enqueue(String data) {
    	//TODO
        Node node = new Node(data); //creates a new node
        if (isEmpty()) {
            head = tail = node; //if it is the first node ever, the head and tails is itself
        } else {
            tail.next = node; //get the tail before adding the node and make its next the node
            tail = node; 
        }
        size++;
    }

    // Removes and returns the element at the front of the queue
    public String dequeue() {
    	//TODO 
        peek(); //checks if it is empty
        String data = head.data; //get the front element's data
        head = head.next; //now the new front is assigned.
        size--; //decrement size
        if (isEmpty()) {
            tail = null; 
        }
        return data; //returns the fronts element data
    }

    // Returns the element at the front of the queue without removing it
    public String peek() {
    	//TODO 
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return head.data;
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
    	//TODO 
    	return size == 0;
    }

    // Returns the number of elements in the queue
    public int size() {
    	//TODO 
        return size;
    }
    
    // Checks if the index is in the range of the queue size.
    // You can use this method while implementing the other ones.
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
