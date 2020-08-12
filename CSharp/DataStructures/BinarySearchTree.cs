using System;
using System.Collections;


public class Node<T> where T : IComparable<T>
{
    public T value;
    public Node<T> right;
    public Node<T> left;


    public Node(T value, Node<T> right, Node<T> left)
    {
        this.value = value;
        this.right = right;
        this.left = left;
    }
}

public class BinarySearchTree<T> where T: IComparable<T>
{
  
    private Node<T> root;

    public bool IsEmpty()
    {
        return root == null;
    }

    public void Add(T value)
    {
        if(root == null)
        {
            root = new Node<T>(value, null, null);
            return;
        }
        Add(value, root);
    }

    // helper method for Add.
	private void Add(T value, Node<T> treeNode)
     {
		if(value.CompareTo(treeNode.value) > 0) 
        {
			if(treeNode.right != null) Add(value, treeNode.right);
			else treeNode.right = new Node<T>(value, null, null);
		} else if(value.CompareTo(treeNode.value) < 0)
        {
			if(treeNode.left != null) Add(value, treeNode.left);
			else treeNode.left = new Node<T>(value, null, null);
		} 
		// if the value is equal, do nothing.
	}
	
	public bool Contains(T value) 
    {
		if(root == null) return false;
		return  Contains(value, root);
	}
	
	private bool Contains(T value, Node<T> treeNode)
     {
		if(treeNode == null) return false;
		
		if(value.CompareTo(treeNode.value) > 0) return Contains(value, treeNode.right);
		else if(value.CompareTo(treeNode.value) < 0) return Contains(value, treeNode.left);	
		else return true;
	}
	
	public Node<T> Find(T value) 
    {
		if(root == null) return null;
		return Find(value, root);
	}
	
	public Node<T> Find(T value, Node<T> treeNode)
    {
		if(treeNode == null) return null;
		
		int cmp = value.CompareTo(treeNode.value);
		
		if(cmp == 0) return treeNode;
		else if (cmp > 0) return Find(value, treeNode.right);
		else return Find(value, treeNode.left);	
		
	}
	
	public void Remove(T value) 
    {
		if(root == null) return;
		root = Remove(value, root);
	}
	
	private Node<T> Remove(T value, Node<T> treeNode) 
    {
		if (treeNode == null) return null;
		
		// We're looking for something bigger.
		if(value.CompareTo(treeNode.value) > 0) {
			// go to the right subtree
			treeNode.right = Remove(value, treeNode.right);
		}
		// We're looking for something smaller
		else if(value.CompareTo(treeNode.value) < 0) 
        {
			// go to the left subtree
			treeNode.left = Remove(value, treeNode.left);
		}
		// This is the node we're looking for!
		else 
        {
			// if it has children on both sides...
			if(treeNode.left != null && treeNode.right != null) 
            {
				// We get the smallest node on its right subtree.
				Node<T> tmp = treeNode;
				Node<T> leftMost = GetLeftMost(tmp.right);
				// With the smallest value, we replace it 
				treeNode.value = leftMost.value;	
				// then, we remove the smallest node in the right subtree.
				treeNode.right = Remove(leftMost.value, treeNode.right);
			}
			// if the left child isn't null (then by logic the right is)...
			else if (treeNode.left != null) 
            {
				// we set the node to it's left subtree to remove it. 
				treeNode = treeNode.left;
			}
			// if the right child isn't null (logic => left side is)...
			else if (treeNode.right != null) 
            {
				// we set the node to it's right subtree to remove it.
				treeNode = treeNode.right;
			}
			// If the left and right side children are null, make the node null
			else treeNode = null;
		}
		// Give back the node that was removed.
		return treeNode;
	}
	
	private Node<T> GetLeftMost(Node<T> treeNode)
    {
		if(treeNode == null) return null;
		else if (treeNode.left == null) return treeNode;
		else return GetLeftMost(treeNode.left);
	}
	
	public void Clear() 
    {
		root = null;
	}

    override
	public String ToString() 
    {
		return "{" + ToString(root).Trim() + "}";
	}
	
	private String ToString(Node<T> treeNode) 
    {
		String res = "";
		if(treeNode == null) return "";
		
		if(treeNode.left != null) {
			res += ToString(treeNode.left);
		}
		res += treeNode.value.ToString() + " ";
		if(treeNode.right != null) {
			res += ToString(treeNode.right);
		} 
		return res;	
	}

	public static void main(string[] args)
	{
		BinarySearchTree<int> bst = new BinarySearchTree<int>();
		bst.Add(5);
		bst.Add(3);
		bst.Add(7);
		bst.Add(2);
		bst.Add(4);

        Console.WriteLine(bst);
	}
}