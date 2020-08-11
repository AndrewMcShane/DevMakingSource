
// Simplistic representation of a Graph:
class BFSGraph
{
    public adj: Array<number>[];
    public size: number;
}

// Breadth-First Search on a graph:
class BFS
{
    public bfs(G: BFSGraph, startVert: number)
    {
        let visited: boolean[] = Array<boolean>();
        // Pre-populate array:
        for(let i = 0; i < G.size; i++)
        {
            visited.push(false);
        }

        // Use an array as our queue representation:
        let q: number[] = new Array<number>();

        visited[startVert] = true;

        q.push(startVert);

        while(q.length > 0)
        {
            const v = q.shift();
            for(let adjV of G.adj[v])
            {
                if(!visited[adjV])
                {
                    visited[adjV] = true;
                    q.push(adjV);
                }
            }
        
        }

    }
    
}