using System;
using System.Collections.Generic;

public class BFS
{
    public class Graph
    {
        public LinkedList<int>[] adj;
        public int Size;
    }

    public static void bfs(Graph G, int startVert)
    {
        bool[] visited = new bool[G.Size];
        // NOTE: the namespace reference isn't required if you have this file on it's own, but if you download
        // all the files with this in the same folder, it will mistakenly reference 
        // the local Queue implementation, and not the built in!
        System.Collections.Generic.Queue<int> q = new System.Collections.Generic.Queue<int>();

        visited[startVert] = true;

        q.Enqueue(startVert);

        while(q.Count > 0)
        {
            int v = q.Dequeue();
            foreach(int adjV in G.adj[v])
            {
                if (!visited[adjV])
                {
                    visited[adjV] = true;
                    q.Enqueue(adjV);
                }
            }
        }

    }
}
