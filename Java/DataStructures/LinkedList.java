package DataStructures;

import java.util.*;

// Linked List implementation using generic type for flexibility.
public class LinkedList<T> implements Iterable<T> {

	// Private inner node class
	private static class Node<T> {
		// The generic value contained in the node.
		public T value;
		// The next node in the sequence of nodes.
		public Node<T> next;
		// Constructor for the Node class
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}
	
	// The root element in the linked list.
	private Node<T> root;
	
	// Parameterless constructor for the linked list.
	public LinkedList() {
		root = null;
	}
	
	// If the root node is null, then the list must be empty.
	public boolean isEmpty() {
		return root == null;
	}
	
	// Does the linked list contain the value of type T?
	public boolean contains(T value) {
		// Check to make sure the list isn't empty!
		if(isEmpty()) {
			return false;
		}
		// Make a temporary node that points to the root.
		Node<T> tmp = root;
		// Iterate through the linked list.
		while(tmp != null) {
			// If the node value is equal to the value searched, return true.
			if(tmp.value.equals(value)) {
				return true;
			}
			// Otherwise, keep searching.
			tmp = tmp.next;
		}
		// If no value was found, then return false.
		return false;
	}
	
	// Returns the value of the node at the index.
	public T get(int index) {
		// Ensure the list is not empty.
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		// Temporary node pointing to the root node.
		Node<T> tmp = root;
		// Iterate up the list while counting to the index. 
		for(int i = 0; i < index; i++) {
			tmp = tmp.next;
			// If the temporary node becomes null, throw an exception.
			if(tmp == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		// Return the value at the specified index.
		return tmp.value;
		
	}

	// Returns the first element of the linked list.
	public T getFirst() {
		if(root != null) {
			return root.value;
		}
		return null;
	}
	
	// Returns the last element of the linked list.
	public T getLast() {
		// If the root is null, return null.
		if(root == null) {
			return null;
		}
		// Otherwise, iterate the linked list.
		else 
		{
			// Temporary node value that points to the root node.
			Node<T> tmp = root;
			// While the temporary node has a next node, keep iterating.
			while(tmp.next != null) {
				tmp = tmp.next;
			}
			// Now that the end of the list is found, return the value of that node.
			return tmp.value;
		}
	}
	
	// Appends an item to the end of the linked list.
	public void add(T value) {
		// Check if the list is empty.
		if(isEmpty()) {
			// If the list is empty, insert the node at the beginning.
			root = new Node<T>(value, null);
			return;
		}
		// Create a temporary node to traverse the linked list.
		Node<T> tmp = root;
		// Iterate the linked list.
		while(tmp.next != null) {
			tmp = tmp.next;
		}
		// Create a new node with the value and add it to the end.
		tmp.next = new Node<T>(value, null);
		return;
	}
	
	// Appends a new node to the beginning of the linked list.
	public void addFirst(T value) {
		// Create a new node with the root node as its next value.
		Node<T> firstNode = new Node<T>(value, root);
		// Set the root node to the first node.
		root = firstNode;
		return;
	}
	
	// Appends a new node after a specified node if it exists, otherwise to the end of the linked list.
	public void addAfter(T key, T toAdd) {
		// Check if the list is empty.
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		// Define a temporary node to traverse the linked list.
		Node<T> tmp = root;
		// Iterates the linked list.
		while(tmp.next != null) {
			// If the temporary node's value is the value being looked for
			if(tmp.value.equals(key)) {
				// Create a new node, with it's next node as the tmp node's next.
				Node<T> newNode = new Node<T>(toAdd, tmp.next);
				// Now, set the tmp node's next to the new node.
				tmp.next = newNode;
				return;
			}
			tmp = tmp.next;
		}
		throw new NoSuchElementException();

	}
	
	// Appends a node before a specified value if it exists, otherwise to the end of the linked list.
	public void addBefore(T key, T toAdd) {
		// Check if the list is empty.
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		// Also define a temporary node to act as a trailer for the previous node.
		Node<T> tmpPrev = root;
		// Define a temporary node to traverse the linked list.
		Node<T> tmp = root.next;
		if(tmpPrev.value.equals(key)) {
			addFirst(toAdd);
			return;
		}
		
		// Iterate the linked list
		while(tmp != null) {
			// if the temporary value is the key, add the new value before it.
			if(tmp.value.equals(key)) {
				// the previous node's next should be the new node, whose next is the tmp node.
				Node<T> newNode = new Node<T>(toAdd, tmp);
				tmpPrev.next = newNode;
			}
			// Reassign the tmp nodes.
			tmpPrev = tmp;
			tmp = tmp.next;	
		}
		
		throw new NoSuchElementException();
	}
	
	// Removes the first encounter of a specified node.
	public void remove(T value) {
		// If the list is empty, return.
		if(isEmpty()) {
			return;
		}
		// Define a temporary node to iterate the list.
		Node<T> tmp = root;
		// If the second node is null, do a check.
		if(tmp.next == null) {
			// Check value
			if(tmp.value.equals(value)) {
				root = null;
			}
			
			return;
		}
		// While the tmp's next node is not null, check it for being the correct node. 
		while(tmp.next != null) {
			// Remove the next node if it is the correct node.
			if(tmp.next.value.equals(value)) {
				tmp.next = tmp.next.next;
				return;
			}
			// Continue the iteration
			tmp = tmp.next;
		}
		return;
	}
	
	// Removes the first node.
	public void removeFirst() {
		// Check if the list is empty
		if(isEmpty()) {
			// Nothing to remove, just return.
			return;
		}
		// Just assign the root to the next, and the GC will take care of the rest!
		root = root.next;
		return;
	}
	
	// Removes the last node.
	public void removeLast() {
		// Check if the list is empty
		if(isEmpty()) {
			// nothing to remove, so return.
			return;
		}
		// Temporary node for iterating the list
		Node<T> tmp = root;
		// If the first node has no next node, then make the root null.
		if(tmp.next == null) {
			root = null;
			return;
		}
		// Iterate, checking 2 nodes ahead so that we know when the next node is the last node.
		while(tmp.next.next != null) {
			tmp = tmp.next;
		}
		// Set the next, last, node to null. 
		tmp.next = null;
		return;
	}
	
	// Gets the length of the list.
	public int length() {
		int count = 0;
		// Define a temporary node to traverse the list.
		Node<T> tmp = root;
		while(tmp != null) {
			count++;
			tmp = tmp.next;
		}
		return count;
	}
	
	// Return the index of a specified value.
	public int indexOf(T value) {
		int count = 0;
		// Check to make sure the list isn't empty.
		if(isEmpty()) {
			return -1;
		}
		// Make a temporary node that points to the root.
		Node<T> tmp = root;
		// Iterate through the linked list.
		while(tmp != null) {
			// If the node value is equal to the value searched, return count.
			if(tmp.value.equals(value)) {
				return count;
			}
			count++;
			// Otherwise, keep searching.
			tmp = tmp.next;
		}
		// If no value was found, then return -1.
		return -1;
	}
	
	// Clears the linked list.
	public void clear() {
		root = null;
	}
	
	// Returns a string representation of the linked list.
	public String toString() {
		String res = "";
		// Define a temporary node.
		Node<T> tmp = root;
		// Iterate the linked list.
		while(tmp != null) {
			// Append the string representation of the node value, comma separated.
			res += tmp.value.toString();
			if(tmp.next != null ) {
				res += ", ";
			}
			tmp = tmp.next;
		}
		return res;
	}

	
	// Implements the iterator interface to allow for tokenized iterations.
	@Override
	public Iterator<T> iterator() {
		
	      return new ListIterator();
	}
	// List Iterator class that implements the iterator methods.
	private class ListIterator  implements Iterator<T> {
		// The node being looked at.
		private Node<T> tokenNode;

		// Constructs a new list iterator.
		public ListIterator() {
			tokenNode = root;
		}

		// Does this iterator have a next node?
		public boolean hasNext() {
			return tokenNode != null;
		}
		
		// Retrieves the next node in the list.
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			T res = tokenNode.value;
			tokenNode = tokenNode.next;
			return res;
		}

		// Not implemented for Linked list as it cannot be done in an in-place fashion without traversing the entire list up to that point again. 
		public void remove() { 
			throw new UnsupportedOperationException();
		}
	}
}