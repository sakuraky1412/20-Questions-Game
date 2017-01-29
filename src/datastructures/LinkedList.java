package datastructures;
/**
 * Implement a generic LinkedList<T> class that has exactly one instance field
 * (head)
 */
public class LinkedList<T> {

	private LinkedListNode<T> head;

	/**
	 * Get data stored in head node of list.
	 */
	public T getFirst() {
		return head.getData();
	}

	/**
	 * Get the head node of the list.
	 */
	public LinkedListNode<T> getFirstNode() {
		return head;
	}

	/**
	 * Get data stored in last node of list.
	 */
	public T getLast() {
		// return the data of the last node
		return getLastNode().getData();
	}

	/**
	 * Get the tail node of the list.
	 */
	public LinkedListNode<T> getLastNode() {
		// run through the entire list till the last node
		LinkedListNode<T> currentNode = head;
		while (currentNode.getNext() != null)
			currentNode = currentNode.getNext();
		return currentNode;
	}

	/**
	 * Insert a new node with data at the head of the list.
	 */
	public void insertFirst(T data) {
		// create new node and set data
		LinkedListNode<T> newNode = new LinkedListNode<T>();
		newNode.setData(data);
		// set the next node to be the old head
		newNode.setNext(head);
		// set new node to be the new head
		head = newNode;
	}

	/**
	 * Insert a new node with data after currentNode
	 */
	public void insertAfter(LinkedListNode<T> currentNode, T data) {
		// create new node
		LinkedListNode<T> newNode = new LinkedListNode<T>();
		// set data
		newNode.setData(data);
		// set next to point at what current node originally points at
		newNode.setNext(currentNode.getNext());
		// set current node to point at new node
		currentNode.setNext(newNode);
	}

	/**
	 * Insert a new node with data at the end of the list.
	 */
	public void insertLast(T data) {
		if (head == null) {
			insertFirst(data);
		} else {
			// create new last node
			LinkedListNode<T> newLastNode = new LinkedListNode<T>();
			// get the last node
			LinkedListNode<T> lastNode = this.getLastNode();
			// set data
			newLastNode.setData(data);
			// set next to be null since it will be the last node
			newLastNode.setNext(null);
			// set last node to take new last node as it's next node
			lastNode.setNext(newLastNode);
		}
	}

	/**
	 * Remove the first node
	 */
	public void deleteFirst() {
		// get the node after head, that is, the second node
		LinkedListNode<T> secondNode = head.getNext();
		// head is now second node
		head = secondNode;
	}

	/**
	 * Remove the last node
	 */
	public void deleteLast() {
		// run through the list and get the last node
		LinkedListNode<T> currentNode = head;
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
		}
		// set the data of the last node to be null
		currentNode.setData(null);
	}

	/**
	 * Remove node following currentNode If no node exists (i.e., currentNode is
	 * the tail), do nothing
	 */
	public void deleteNext(LinkedListNode<T> currentNode) {
		// current node points to some node
		if (currentNode.getNext() != null)
			// let current node point to the node after it's original next node
			currentNode.setNext(currentNode.getNext().getNext());
	}

	/**
	 * Return the number of nodes in this list.
	 */
	public int size() {
		// run through the list
		LinkedListNode<T> currentNode = head;
		int i = 1;
		while (currentNode.getNext() != null) {
			currentNode = currentNode.getNext();
			// count the nodes
			i++;
		}
		return i;
	}

	/**
	 * Check if list is empty.
	 * 
	 * @return true if list contains no items.
	 */
	public boolean isEmpty() {
		// if head is null
		if (head == null)
			return true;
		else
			return false;
	}

	/**
	 * Return a String representation of the list.
	 */
	public String toString() {
		// create a StringBuffer str
		StringBuffer str = new StringBuffer();
		// run through the list
		LinkedListNode<T> currentNode = head;
		while (currentNode.getNext() != null) {
			// add the string of current node to str and add the pointer
			str.append(currentNode.getData().toString() + '\n');
			// move to the next node
			currentNode = currentNode.getNext();
		}
		// add the last node
		if (currentNode.getData() != null) {
			str.append(currentNode.getData().toString());
		}
		// // delete the extra pointer
		// else {
		// str.delete(str.length() - 4, str.length());
		// }
		return str.toString();
	}
}
