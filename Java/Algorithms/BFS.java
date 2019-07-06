package Algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public static class Graph {
		LinkedList<Integer>[] adj;
		int size;
	}
	
	public static void bfs(Graph G, int startVert) {
		
		boolean[] visited = new boolean[G.size];
		Queue<Integer> q = new LinkedList<>();
		
		visited[startVert] = true;
		
		q.add(startVert);
		
		while(!q.isEmpty()) {
			int v = q.remove();
			for(int adjV: G.adj[v]) {
				if(!visited[adjV]) {
					visited[adjV] = true;
					q.add(adjV);
				}
			}
		}
		
	}
}