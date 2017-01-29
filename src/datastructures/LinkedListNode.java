package datastructures;
/**
 * A generic LinkedListNode<T> class that has exactly two instance fields (data
 * and next)
 * 
 */
public class LinkedListNode<T> {
	private T data;
	private LinkedListNode<T> next;

	/**
	 * Set the data stored at this node.
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Get the data stored at this node.
	 */
	public T getData() {
		if (data != null)
			return data;
		else
			return null;
	}

	/**
	 * Set the next pointer to passed node.
	 */
	public void setNext(LinkedListNode<T> node) {
		next = node;
	}

	/**
	 * Get (pointer to) next node.
	 */
	public LinkedListNode<T> getNext() {
		if (next != null)
			return next;
		else
			return null;
	}

	/**
	 * Returns a String representation of this node.
	 */
	public String toString() {
		// if data is not null
		if (data != null)
			// return data
			return data.toString();
		else
			// return null
			return null;
	}
}
