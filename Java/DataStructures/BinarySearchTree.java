package DataStructures;

public class BinarySearchTree<T extends Comparable<T>> {

	private static class Node<T extends Comparable<T>>{
		private T value;
		private Node<T> right;
		private Node<T> left;
		
		public Node(T value, Node<T> right, Node<T> left) {
			this.value = value;
			this.right = right;
			this.left = left;
		}
	}
	
	private Node<T> root;
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void add(T value) {
		if(root == null) {
			root = new Node<T>(value, null, null);
			return;
		}
		add(value, root);
	}
	
	// helper method for add.
	private void add(T value, Node<T> treeNode) {
		if(value.compareTo(treeNode.value) > 0) {
			if(treeNode.right != null) add(value, treeNode.right);
			else treeNode.right = new Node<T>(value, null, null);
		} else if(value.compareTo(treeNode.value) < 0){
			if(treeNode.left != null) add(value, treeNode.left);
			else treeNode.left = new Node<T>(value, null, null);
		} 
		// if the value is equal, do nothing.
	}
	
	public boolean contains(T value) {
		if(root == null) return false;
		return  contains(value, root);
	}
	
	private boolean contains(T value, Node<T> treeNode) {
		if(treeNode == null) return false;
		
		if(value.compareTo(treeNode.value) > 0) return contains(value, treeNode.right);
		else if(value.compareTo(treeNode.value) < 0) return contains(value, treeNode.left);	
		else return true;
	}
	
	public Node<T> find(T value) {
		if(root == null) return null;
		return find(value, root);
	}
	
	public Node<T> find(T value, Node<T> treeNode){
		if(treeNode == null) return null;
		
		int cmp = value.compareTo(treeNode.value);
		
		if(cmp == 0) return treeNode;
		else if (cmp > 0) return find(value, treeNode.right);
		else return find(value, treeNode.left);	
		
	}
	
	public void remove(T value) {
		if(root == null) return;
		root = remove(value, root);
	}
	
	private Node<T> remove(T value, Node<T> treeNode) {
		if (treeNode == null) return null;
		
		// We're looking for something bigger.
		if(value.compareTo(treeNode.value) > 0) {
			// go to the right subtree
			treeNode.right = remove(value, treeNode.right);
		}
		// We're looking for something smaller
		else if(value.compareTo(treeNode.value) < 0) {
			// go to the left subtree
			treeNode.left = remove(value, treeNode.left);
		}
		// This is the node we're looking for!
		else {
			// if it has children on both sides...
			if(treeNode.left != null && treeNode.right != null) {
				// We get the smallest node on its right subtree.
				Node<T> tmp = treeNode;
				Node<T> leftMost = getLeftMost(tmp.right);
				// With the smallest value, we replace it 
				treeNode.value = leftMost.value;	
				// then, we remove the smallest node in the right subtree.
				treeNode.right = remove(leftMost.value, treeNode.right);
			}
			// if the left child isn't null (then by logic the right is)...
			else if (treeNode.left != null) {
				// we set the node to it's left subtree to remove it. 
				treeNode = treeNode.left;
			}
			// if the right child isn't null (logic => left side is)...
			else if (treeNode.right != null) {
				// we set the node to it's right subtree to remove it.
				treeNode = treeNode.right;
			}
			// If the left and right side children are null, make the node null
			else {
				treeNode = null;
			}
		}
		// Give back the node that was removed.
		return treeNode;
	}
	
	private Node<T> getLeftMost(Node<T> treeNode){
		if(treeNode == null) return null;
		else if (treeNode.left == null) return treeNode;
		else return getLeftMost(treeNode.left);
	}
	
	public void clear() {
		root = null;
	}
	
	public String toString() {
		return "{" + toString(root).trim() + "}";
	}
	
	private String toString(Node<T> treeNode) {
		String res = "";
		if(treeNode == null) return "";
		
		if(treeNode.left != null) {
			res += toString(treeNode.left);
		}
		res += treeNode.value.toString() + " ";
		if(treeNode.right != null) {
			res += toString(treeNode.right);
		} 
		return res;	
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		bst.add(5);
		bst.add(3);
		bst.add(7);
		bst.add(1);
		bst.add(4);

		System.out.println(bst.toString());
		bst.remove(3);
		System.out.println(bst.toString());
		
	}

}
