package DataStructures;

import java.util.LinkedList;

public class Graph {

	LinkedList<Integer>[] adj;
	int V;
	
	public Graph(int vertices) {
		V = vertices;
		adj = new LinkedList[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int from, int to) {
		// Adding an edge between from and to.
		adj[from].addFirst(to);
		// For undirected graphs, do the same in reverse (below)
		// adj[to].addFirst(from);
	}
	
	public int size() {
		return V;
	}
	
	public LinkedList<Integer> getEdges(int vertex) {
		if(vertex > V) return null;
		return adj[vertex];
	}
}
