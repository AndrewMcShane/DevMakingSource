package DataStructures;

import java.util.*;

public class DoublyLinkedList<T> implements Iterable<T>{

	// Doubly linked node class.
	private static class Node<T> {
		public T value;
		public Node<T> next;
		public Node<T> prev;
		
		public Node(T value, Node<T> next, Node<T> previous) {
			this.value = value;
			this.next = next;
			this.prev = previous;
		}
	}
	
	// The head (first) node of the linked list.
	private Node<T> head;
	// The tail (last) node of the linked list.
	private Node<T> tail;
	// The size of the linked list.
	private int size;
	
	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size <= 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean contains(T value) {
		if(isEmpty()) {
			return false;
		}
		Node<T> tmp = head;
		while(tmp != null) {
			if(tmp.value.equals(value)) {
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}
	
	public T get(int index) {
		// Ensure the index is within limits.
		if(index > size || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		// Neat trick: worst case can be half the size of the list by storing size.
		if(index > size / 2) {
			index = (size - 1) - index;
			Node<T> tmp = tail;
			while(index > 0) {
				tmp = tmp.prev;
				index--;
			}
			return tmp.value;
		}
		else {
			Node<T> tmp = head;
			while(index > 0) {
				tmp = tmp.next;
				index--;
			}
			return tmp.value;
		}
		
	}
	
	public T getFirst() {
		if(head != null) {
			return head.value;
		}
		return null;
	}
	
	public T getLast() {
		if(tail != null) {
			return tail.value;
		}
		return null;
	}
	
	public void addLast(T value) {
		// In the empty case, assign head and tail.
		if(isEmpty()) {
			Node<T> tmp = new Node<T>(value, null, null);
			head = tmp;
			tail = tmp;
			size++;
			return;
		}
		Node<T> tmp = tail;
		tmp.next = new Node<T>(value, null, tmp);
		tail = tmp.next;
		size++;
	}
	
	public void addFirst(T value) {
		// head and tail check
		if(isEmpty()) {
			Node<T> tmp = new Node<T>(value, null, null);
			head = tmp;
			tail = tmp;
			size++;
			return;
		}
		Node<T> tmp = head;
		tmp.prev  = new Node<T>(value, tmp, null);
		head = tmp;
		size++;
	}
	
	public void addAfter(T key, T toAdd) {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<T> tmp = head;
		while(tmp != null) {
			if(tmp.value.equals(key)) {
				Node<T> newNode = new Node<T>(toAdd, tmp.next, tmp);
				tmp.next = newNode;
				// Contrived looking, but necessary. 
				if(newNode.next != null) newNode.next.prev = newNode;
				else tail = newNode;
				size++;
				return;
			}
			tmp = tmp.next;
		}
		// End of list iterated: element DNE.
		throw new NoSuchElementException();
		
	}
	
	public void addBefore(T key, T toAdd) {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<T> tmp = head;
		while(tmp != null) {
			if(tmp.value.equals(key)) {
				Node<T> newNode = new Node<T>(toAdd, tmp, tmp.prev);
				tmp.prev = newNode;
				if(newNode.prev != null) {
					newNode.prev.next = newNode;
					
				} else {
					head = tmp;
				}
				
				size++;
				return;
			}
			tmp = tmp.next;
		}
		
		throw new NoSuchElementException();
		
	}
	
	public void remove(T value) {
		if(isEmpty()) {
			return;
		}
		Node<T> tmp = head;
		while(tmp != null) {
			if(tmp.value.equals(value)) {
				if(tmp.prev != null) tmp.prev.next = tmp.next;
				else head = tmp.next;
				if(tmp.next != null) tmp.next.prev = tmp.prev;
				else tail = tmp.prev;
				size--;
				return;
			}
			tmp = tmp.next;
		}
		return;
	}
	
	public void removeFirst() {
		if(isEmpty()) {
			return;
		}
		if(size == 1) {
			head = null;
			tail = null;
			size--;
			return;
		}
		head = head.next;
		head.prev = null;
		size--;
	}
	
	public void removeLast() {
		if(isEmpty()) {
			return;
		}
		if(size == 1) {
			head = null;
			tail = null;
			size--;
			return;
		}
		tail = tail.prev;
		tail.next = null;
		size--;
	}
	
	public int indexOf(T value) {
		int index = 0;
		Node<T> tmp = head;
		while(tmp != null) {
			if(tmp.value.equals(value)) {
				return index;
			}
			tmp = tmp.next;
			index++;
		}
		return -1;
	}
	
	public void clear() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	public String toString() {
		String res = "{";
		// Define a temporary node.
		Node<T> tmp = head;
		// Iterate the linked list.
		while(tmp != null) {
			// Append the string representation of the node value, comma separated.
			res += tmp.value.toString();
			if(tmp.next != null) res += ", ";
			tmp = tmp.next;
		}
		res += "}";
		return res;
	}
	
	
	public static void main(String[] args) {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		ll.addFirst(1);
		ll.removeFirst();
		ll.addFirst(1);
		ll.addLast(2);
		ll.addLast(3);
		int index = ll.indexOf(3);
		System.out.println(ll.get(index));
		System.out.println("LinkedList = " + ll.toString());
		ll.clear();
		System.out.println("LinkedList = " + ll.toString());
	}
	
	@Override
	public Iterator<T> iterator() {
	      return new ListIterator();
	   }

   private class ListIterator  implements Iterator<T> {
      private Node<T> tokenNode;

      public ListIterator() {
    	  tokenNode = head;
      }

      public boolean hasNext() {
         return tokenNode != null;
      }

      public T next() {
         if (!hasNext()) throw new NoSuchElementException();
         T res = tokenNode.value;
         tokenNode = tokenNode.next;
         return res;
      }

      public void remove() { 
    	  throw new UnsupportedOperationException(); 
      }
   }

}