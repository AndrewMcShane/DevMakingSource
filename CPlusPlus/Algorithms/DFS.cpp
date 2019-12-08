#include <list>

// Example class of a graph implementation:
class Graph {
    public:
        Graph::Graph(int Size) 
            :Size(Size) {
            adj = new std::list<int>[Size];
        }
        std::list<int>* adj;
        int Size;
};


void dfs(Graph & G, const int startVert) {

    bool* visited = new bool[G.Size];
    for(int i = 0; i < G.Size; ++i) {
        visited[i] = false;
    }

    std::list<int> queue;

    // Mark and enqueue our start vert
    visited[startVert] = true;
    queue.push_front(startVert);

    while(!queue.empty()) {
        // Get the next vert
        int v = queue.front();
        queue.pop_front();
        // Explore it:
        std::list<int>::iterator it;
        for(it = G.adj[v].begin(); it != G.adj[v].end(); ++it) {

            if(!visited[*it]) {
                visited[*it] = true;
                queue.push_front(*it);
            }

        }
    }
    delete[] visited;
}

void dfsAux(Graph & G, int v, bool* visited) {
    visited[v] = true;

    std::list<int>::iterator it;
    for(it = G.adj[v].begin(); it != G.adj[v].end(); ++it) {

        if(!visited[*it]) {
            // pre(); something can happen before the visit.
            dfsAux(G, *it, visited);
            // post(); something can happpen after the visit.
        }
    }
}

void dfsRecursive(Graph & G, const int startVert) {
    bool* visited = new bool[G.Size];
    dfsAux(G, startVert, visited);
    delete[] visited;
}

