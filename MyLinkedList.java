package ds;

public class MyLinkedList {
	private Node head;
	private int size = 0;

	public MyLinkedList() {
		this.head = null;
	}

	// Adds a new node with the specified data at the end of the list
	public void add(String data) {
		// TODO
	    Node node = new Node(data);
	    if (head == null) {
	        head = node; //if the list is empty, the head is the new node
	    } else {
	        Node current = head;
	        while (current.getNext() != null) {
	            current = current.getNext(); //getting the last node
	        }
	        current.setNext(node); //linking for the last element with new node
	    }
	    size++; 
	}

	// Inserts a new node with the specified data at the given index
	public void insert(int index, String data) {	
		// TODO
        checkIndex(index);
        if (index == 0) {
            Node node = new Node(data); 
            node.setNext(head); //if it is the first element, the head and the next is the same new element
            head = node;
        } else {
            Node node = new Node(data);
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext(); //getting the last node before
            }
            node.setNext(current.getNext());
            current.setNext(node);
        }
        size++;
	}

	// Removes the node at the specified index
	public void remove(int index) {
		// TODO
        checkIndex(index);
        if (index == 0) {
            head = head.getNext();
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext()); //linking before the element that will be removed to removed element's next
        }
        size--;
	}

	// Returns the data of the node at the specified index
	public String get(int index) {
		// TODO
        checkIndex(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
	}

	// Returns the number of elements in the list
	public int size() {
		// TODO
		return size;
	}

	// Checks if the index is in the range of the list size.
	// You can use this method while implementing the other ones.
	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
