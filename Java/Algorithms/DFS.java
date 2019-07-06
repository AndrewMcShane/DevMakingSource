package Algorithms;

import java.util.LinkedList;
import java.util.Stack;

public class DFS {

	// Primitive definition of a Graph.
	public static class Graph {
		LinkedList<Integer>[] adj;
		int size;
	}
	
	// Iterative DFS implementation.
	public static void dfsIterative(Graph G, int startVert) {
		
		boolean[] visited = new boolean[G.size];
		Stack<Integer> s = new Stack<>();
		
		visited[startVert] = true;
		s.push(startVert);
		
		while (!s.isEmpty()) {
			int vert = s.pop();
			for(int adjV: G.adj[vert]) {
				if (!visited[adjV]) {
					visited[adjV] = true;
					s.push(adjV);
				}
			}
		}
	}
	
	// Recursive DFS that allows for function calls before and after visits.
	public static void dfsRecursive(Graph G, int startVert) {
		boolean[] visited = new boolean[G.size];
		dfsAux(G, startVert, visited);
	}
	
	private static void dfsAux(Graph G, int v, boolean[] visited) {
		visited[v] = true;
		for(int adjV: G.adj[v]) {
			if(!visited[adjV]) {
				dfsAux(G, adjV, visited);
			}
		}
	}
}
