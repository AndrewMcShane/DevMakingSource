using System;
using System.Collections.Generic;

public class DFS
{
    public class Graph
    {
        public LinkedList<int>[] adj;
        public int Size;
    }

    public static void dfs(Graph G, int startVert)
    {
        bool[] visited = new bool[G.Size];
        // TODO change this for release. Currently references local Queue implementation otherwise.
        System.Collections.Generic.Stack<int> s = new System.Collections.Generic.Stack<int>();

        visited[startVert] = true;

        s.Push(startVert);

        while(s.Count > 0)
        {
            int v = s.Pop();
            foreach(int adjV in G.adj[v])
            {
                if (!visited[adjV])
                {
                    visited[adjV] = true;
                    s.Push(adjV);
                }
            }
        }

    }

    public static void dfsRecursive(Graph G, int startVert)
    {
        bool[] visited = new bool[G.Size];
        dfsAux(G, startVert, visited);
    }

    private static void dfsAux(Graph G, int v, bool[] visited)
    {
        visited[v] = true;
        foreach(int adjV in G.adj[v])
        {
            if (!visited[adjV])
            {
                // foo(); // Something can happen before the visit.
                dfsAux(G, adjV, visited); // Visiting.
                // bar(); // Something cal happen after the visit.
            }
        }
    }
}