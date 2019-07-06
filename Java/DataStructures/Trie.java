package DataStructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trie {
	
	private static class Node {
		private Map<Character, Node> children;
		boolean isWord;
		
		public Node() {
			children = new HashMap<>();
			isWord = false;
		}
	}
	
	private Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public void put(String word) {
		Node current = root;
		for(int i = 0; i < word.length(); i++) {
			char child = word.charAt(i);
			Node tmp = current.children.get(child);
			if(tmp == null) {
				tmp = new Node();
				current.children.put(child, tmp);
			}
			current = tmp;
		}
		
		current.isWord = true;
	}
	
	public boolean contains(String word) {
		Node current = root;
		for(int i = 0; i < word.length(); i++) {
			char child = word.charAt(i);
			Node tmp = current.children.get(child);
			if(tmp == null) {
				return false;
			}
			current = tmp;
		}
		return current.isWord;
	}
	
	
	public void remove(String word) {
		remove(root, word, 0);
	}
	
	private Node remove(Node current, String word, int depth) {
		if(current == null ) return null;
		if(depth == word.length()) {
			current.isWord = false;
		}
		else {
			char child = word.charAt(depth);
			Node childNode = remove(current.children.get(child), word, depth + 1);
			if(childNode == null) {
				current.children.remove(child);
			}
		}
		
		if(!current.children.isEmpty()) {
			return current;
		}
		return null;
	}
	
	public List<String> startsWith(String prefix){
		List<String> res = new LinkedList<String>();
		Node current = root;
		for(int i = 0; i < prefix.length(); i++) {
			char child = prefix.charAt(i);
			Node tmp = current.children.get(child);
			if(tmp == null) return res;
			else current = tmp;
		}
		if(current.isWord) {
			res.add(prefix);
		}
		res.addAll(startsWithAux(prefix, current));
		return res;
	}
	
	private List<String> startsWithAux(String prefix, Node current){
		List<String> res  = new LinkedList<String>();
		
		// Java 8+ implementation using forEach and anonymous function
		current.children.entrySet().stream().forEach(child -> {
			if(child.getValue().isWord) {
				res.add(prefix + child.getKey());
			}
			res.addAll(startsWithAux(prefix + child.getKey(), child.getValue()));
		});
		// Older Java versions: Iterating over the entry set would be used.
		/*--
		 for(Map.Entry<Character, Node> child : current.children.entrySet()) {
			if(child.getValue().isWord) {
				res.add(prefix + child.getKey());
			}
			res.addAll(startsWithAux(prefix + child.getKey(), child.getValue()));
		}
		--*/
		return res;
	}
	
	
	public static void main(String[] args) {
		Trie t = new Trie();
		t.put("Hello");
		t.put("He");
		t.put("Him");
		t.put("Her");
		t.put("Howdy");
		
		System.out.println(t.contains("Hello"));
		System.out.println(t.startsWith("He").toString());
	}
	
}