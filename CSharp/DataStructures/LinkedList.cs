using System;
using System.Collections;
using System.Collections.Generic;

public class LinkedList<T>: IEnumerable<T>
{
    public class Node 
    {
        public T value;
        public Node next; 

        public Node(T value, Node next) 
        {
            this.value = value;
            this.next = next;
        }
    }

    // The root element in the linked list.
	private Node root;
	
	// Parameterless constructor for the linked list.
	public LinkedList() 
    {
		root = null;
	}
	
	// If the root node is null, then the list must be empty.
	public bool IsEmpty() 
    {
		return root == null;
	}
	
	// Does the linked list contain the value of type T?
	public bool Contains(T value) 
    {
		if(IsEmpty()) 
        {
			return false;
		}
		Node tmp = root;
		// Iterate through the linked list.
		while(tmp != null) 
        {
			// If the node value is equal to the value searched, return true.
			if(tmp.value.Equals(value)) 
            {
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}
	
	// Returns the value of the node at the index.
	public T Get(int index) 
    {
		if(IsEmpty()) 
        {
            throw new IndexOutOfRangeException();
		}
		// Temporary node pointing to the root node.
		Node tmp = root;
		// Iterate up the list while counting to the index. 
		for(int i = 0; i < index; i++) 
        {
			tmp = tmp.next;
			// If the temporary node becomes null, throw an exception.
			if(tmp == null) 
            {
				throw new IndexOutOfRangeException();
			}
		}
		return tmp.value;
	}

	// Returns the first element of the linked list.
	public T GetFirst() 
    {
		if(root != null) 
        {
			return root.value;
		}
		return default(T);
	}
	
	// Returns the last element of the linked list.
	public T GetLast() 
    {
		if(root == null) 
        {
            return default(T);
        }
		else 
		{
			Node tmp = root;
			// While the temporary node has a next node, keep iterating.
			while(tmp.next != null) 
            {
				tmp = tmp.next;
			}
			return tmp.value;
		}
	}
	
	// Appends an item to the end of the linked list.
	public void Add(T value) 
    {
		if(IsEmpty()) 
        {
			// If the list is empty, insert the node at the beginning.
			root = new Node(value, null);
			return;
		}
		// Create a temporary node to traverse the linked list.
		Node tmp = root;
		// Iterate the linked list.
		while(tmp.next != null) 
        {
			tmp = tmp.next;
		}
		// Create a new node with the value and add it to the end.
		tmp.next = new Node(value, null);
		return;
	}
	
	// Appends a new node to the beginning of the linked list.
	public void AddFirst(T value) 
    {
		// Create a new node with the root node as its next value.
		Node firstNode = new Node(value, root);
		// Set the root node to the first node.
		root = firstNode;
		return;
	}
	
	// Appends a new node after a specified node if it exists, otherwise to the end of the linked list.
	public void AddAfter(T key, T toAdd) 
    {
		// Check if the list is empty.
		if(IsEmpty()) 
        {
			throw new IndexOutOfRangeException();
		}
		// Define a temporary node to traverse the linked list.
		Node tmp = root;
		// Iterates the linked list.
		while(tmp.next != null) 
        {
			// If the temporary node's value is the value being looked for
			if(tmp.value.Equals(key)) 
            {
				// Create a new node, with it's next node as the tmp node's next.
				Node newNode = new Node(toAdd, tmp.next);
				// Now, set the tmp node's next to the new node.
				tmp.next = newNode;
				return;
			}
			tmp = tmp.next;
		}
		// Otherwise add on to the end. 
		tmp.next = new Node(toAdd, null);
		return;
	}
	
	// Appends a node before a specified value if it exists. 
	public void AddBefore(T key, T toAdd) 
    {
		// Check if the list is empty.
		if(IsEmpty()) 
        {
			throw new IndexOutOfRangeException();
		}
		// Define a temporary node to act as a trailer for the previous node.
		Node tmpPrev = root;
		// Define a temporary node to traverse the linked list.
		Node tmp = root.next;
		if(tmpPrev.value.Equals(key)) 
        {
			AddFirst(toAdd);
			return;
		}
		
		// Iterate the linked list
		while(tmp != null) 
        {
			// if the temporary value is the key, add the new value before it.
			if(tmp.value.Equals(key)) 
            {
				// the previous node's next should be the new node, whose next is the tmp node.
				break;
			}
			// Reassign the tmp nodes.
			tmpPrev = tmp;
			tmp = tmp.next;	
		}
		// If the list has been iterated without finding the value, add to the end.
		tmpPrev.next = new Node(toAdd, tmp);
		return;
	}
	
	// Removes the first encounter of a specified node.
	public void Remove(T value) 
    {
		// If the list is empty, return.
		if(IsEmpty()) 
        {
			return;
		}
		// Define a temporary node to iterate the list.
		Node tmp = root;
		// If the second node is null, do a check.
		if(tmp.next == null) 
        {
			// Check value
			if(tmp.value.Equals(value)) 
            {
				root = null;
			}
			return;
		}
		// While the tmp's next node is not null, check it for being the correct node. 
		while(tmp.next != null) 
        {
			// Remove the next node if it is the correct node.
			if(tmp.next.value.Equals(value)) 
            {
				tmp.next = tmp.next.next;
				return;
			}
			// Continue the iteration
			tmp = tmp.next;
		}
		return;
	}
	
	// Removes the first node.
	public void RemoveFirst() 
    {
		// Check if the list is empty
		if(IsEmpty()) 
        {
			// Nothing to remove, just return.
			return;
		}
		// Just assign the root to the next, and the GC will take care of the rest!
		root = root.next;
		return;
	}
	
	// Removes the last node.
	public void RemoveLast() 
    {
		// Check if the list is empty
		if(IsEmpty()) 
        {
			// nothing to remove, so return.
			return;
		}
		Node tmp = root;
		// If the first node has no next node, then make the root null.
		if(tmp.next == null) 
        {
			root = null;
			return;
		}
		// Iterate, checking 2 nodes ahead so that we know when the next node is the last node.
		while(tmp.next.next != null) 
        {
			tmp = tmp.next;
		}
		// Set the next, last, node to null. 
		tmp.next = null;
		return;
	}
	
	// Gets the length of the list.
	public int Length() 
    {
		int count = 0;
		// Define a temporary node to traverse the list.
		Node tmp = root;
		while(tmp != null) 
        {
			count++;
			tmp = tmp.next;
		}
		return count;
	}
	
	// Return the index of a specified value.
	public int IndexOf(T value) 
    {
		int count = 0;
		// Check to make sure the list isn't empty.
		if(IsEmpty()) 
        {
			return -1;
		}
		// Make a temporary node that points to the root.
		Node tmp = root;
		// Iterate through the linked list.
		while(tmp != null) 
        {
			// If the node value is equal to the value searched, return count.
			if(tmp.value.Equals(value)) 
            {
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
	public void Clear()
    {
		root = null;
	}
	
	// Returns a string representation of the linked list.
    override
	public String ToString()
    {
		String res = "";
		// Define a temporary node.
		Node tmp = root;
		// Iterate the linked list.
		while(tmp != null)
        {
			// Append the string representation of the node value, comma separated.
			res += tmp.value.ToString();
			if(tmp.next != null )
            {
				res += ", ";
			}
			tmp = tmp.next;
		}
		return res;
	}

    /* IEnumerator support for the linked list */
    public IEnumerator<T> GetEnumerator()
    {
        Node node = this.root;
        while(node != null) 
        {
            yield return node.value;
            node = node.next;
        }
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}