package ds;

public class MyStack {
	private Node top;
	private int size = 0;

	// Adds a new element to the top of the stack
	public void push(String data) {
		// TODO
        Node node = new Node(data); //creates a new node
        node.next = top; 
        top = node; //making node top and making its next itself
        size++; //incrementing size because we push new node
	}

	// Removes and returns the top element of the stack
	public String pop() {
		// TODO
		peek(); //checks if it is empty
        String data = top.data; //to get data of the top
        top = top.next; //deletes the first element
        size--; //decrement the size because we deleted an element
        return data;
	}

	// Returns the top element of the stack without removing it
	public String peek() {
		// TODO
        if (isEmpty()) { //exception if stack is empty
            throw new IllegalStateException("Stack is empty");
        }
        return top.data; //returns top element's data
	}

	// Checks if the stack is empty
	public boolean isEmpty() {
		// TODO
		return size == 0; //it is empty return true, else false
	}

	// Returns the number of elements in the stack
	public int size() {
		// TODO
		return size; //returns the size
	}

	// Checks if the index is in the range of the stack size.
	// You can use this method while implementing the other ones.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
