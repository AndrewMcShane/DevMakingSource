using System;
using System.Collections.Generic;

public class Graph
{
    List<int>[] adj;
    int V;

    public Graph(int vertices)
    {
        V = vertices;
        adj = new List<int>[V];
        for(int i = 0; i < V; i++)
        {
            adj[i] = new List<int>();
        }
    }

    public void AddEdge(int from, int to)
    {
        adj[from].Add(to);
        // adj[to].Add(from);
    }

    public int Size()
    {
        return V;
    }

    public List<int> GetEdges(int vertex)
    {
        if (vertex > V) return null;
        return adj[vertex];
    }
}