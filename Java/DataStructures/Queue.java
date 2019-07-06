package DataStructures;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Queue<T> implements Iterable<T> {

	// For queue we will utilize a singly linked list style.
	private static class Node<T> {
		public T value;
		public Node<T> next;
		
		public Node(T value, Node<T> next){
			this.value = value;
			this.next = next;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public boolean isEmpty() {
		return size <= 0;
	}
	
	public T peek() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue is Empty!");
		}
		return head.value;
	}
	
	public void enqueue(T value) {
		if(isEmpty()) {
			tail = new Node<T>(value, null);
			head = tail;
			size++;
			return;
		}
		tail.next = new Node<T>(value, null);
		tail = tail.next;
		size++;
		return;
	}
	
	public T dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue is Empty!");
		}
		if(size == 1) {
			Node<T> tmp = head;
			head = null;
			tail = null;
			size = 0;
			return tmp.value;
		}
		Node<T> tmp = head;
		head = head.next;
		size--;
		return tmp.value;
	}
	
	public T poll(){
		if(isEmpty()) {
			return null;
		}
		if(size == 1) {
			Node<T> tmp = head;
			head = null;
			tail = null;
			size = 0;
			return tmp.value;
		}
		Node<T> tmp = head;
		head = head.next;
		size--;
		return tmp.value;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		String res = "{";
		Node<T> tmp = head;
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
        private Node<T> tokenNode = head;  

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
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(1);
		q.dequeue();
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		
		System.out.println(q.size());
		System.out.println(q.toString());
		System.out.println(q.peek());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.isEmpty());
	}
}