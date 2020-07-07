#include <list>

// Example class of a graph implementation:
class Graph {
    public:
        Graph(int Size) 
            :Size(Size) {
            adj = new std::list<int>[Size];
        }
        ~Graph()
        {
            delete[] adj;
        }
        std::list<int>* adj;
        int Size;
};


void bfs(Graph & G, const int startVert) {

    bool* visited = new bool[G.Size];
    for(int i = 0; i < G.Size; ++i) {
        visited[i] = false;
    }

    std::list<int> queue;

    // Mark and enqueue our start vert
    visited[startVert] = true;
    queue.push_back(startVert);

    while(!queue.empty()) {
        // Get the next vert
        int v = queue.front();
        queue.pop_front();
        // Explore it:
        std::list<int>::iterator it;
        for(it = G.adj[v].begin(); it != G.adj[v].end(); ++it) {

            if(!visited[*it]) {
                visited[*it] = true;
                queue.push_back(*it);
            }

        }
    }
    delete[] visited;
}