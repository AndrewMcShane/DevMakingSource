// Simplistic representation of a Graph:
class DFSGraph
{
    public adj: Array<number>[];
    public size: number;
}

class DFS
{
    public dfs(G: DFSGraph, startVert: number)
    {
        let visited: boolean[] = Array<boolean>();
        // Pre-populate array:
        for(let i = 0; i < G.size; i++)
        {
            visited.push(false);
        }

        let s: number[] = new Array();

        visited[startVert] = true;

        s.push(startVert);

        while(s.length > 0)
        {
            const v = s.pop();
            for(let adjV of G.adj[v])
            {
                if(!visited[adjV])
                {
                    visited[adjV] = true;
                    s.push(adjV);
                }
            }
        }
    }

    public dfsRecursive(G: DFSGraph, startVert: number)
    {
        let visited: boolean[] = Array<boolean>();
        // Pre-populate array:
        for(let i = 0; i < G.size; i++)
        {
            visited.push(false);
        }
        this.dfsAux(G, startVert, visited);
    }

    private dfsAux(G: DFSGraph, v: number, visited: boolean[])
    {
        visited[v] = true;
        for(let adjV of G.adj[v])
        {
            if(!visited[adjV])
            {
                // this.foo(); // Something can happen before the visit.
                this.dfsAux(G, adjV, visited);
                // this.bar(); // Something can happen after the visit.
            }
        }
    }
}