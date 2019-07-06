package DataStructures;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

	// For stack we will utilize a singly linked list style.
	private static class Node<T> {
		public T value;
		public Node<T> next;
		
		public Node(T value, Node<T> next){
			this.value = value;
			this.next = next;
		}
	}
	
	private Node<T> root;
	private int size;
	
	public boolean isEmpty() {
		return size <= 0;
	}
	
	public T peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack is empty!");
		}
		return root.value;
	}
	
	public void push(T value) {
		if(isEmpty()) {
			root = new Node<T>(value, null);
			size++;
			return;
		}
		Node<T> tmp = new Node<T>(value, root);
		root = tmp;
		size++;
		return;
	}
	
	public T pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack is empty!");
		}
		Node<T> tmp = root;
		root = root.next;
		size--;
		return tmp.value;
	}
	
	// Notice that poll is identical to pop, except it returns null instead of an error.
	public T poll() {
		if(isEmpty()) {
			return null;
		}
		Node<T> tmp = root;
		root = root.next;
		size--;
		return tmp.value;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		String res = "{";
		Node<T> tmp = root;
		while(tmp != null) {
			res += tmp.value.toString();
			if(tmp.next != null) res += ", ";
			tmp = tmp.next;
		}
		res += "}";
		return res;
	}
	
	public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> tokenNode = root;  

        public boolean hasNext() {
            return tokenNode != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T res = tokenNode.value;
            tokenNode = tokenNode.next;
            return res;
        }
    }
	
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.pop();
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		
		System.out.println(s.size());
		System.out.println(s.toString());
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
		System.out.println(s.poll());
	}
}