#include <list>

class Graph {
    public:
        Graph(int vertices);
        ~Graph();
        void addEdge(int from, int to);
        std::list<int> getEdges(int vertex);

    private:
        int V;
        std::list<int> * adj;
};

Graph::Graph(int vertices) {
    this->V = vertices;
    adj = new std::list<int>[vertices];
    for(int i = 0; i < vertices; ++i) {
        adj[i] = std::list<int>();
    }
}

Graph::~Graph() {
    delete[] this->adj;
}

void Graph::addEdge(int from, int to) {
    adj[from].push_front(to);
    // adj[to].push_front(from);
}

std::list<int> Graph::getEdges(int vertex) {
    if(vertex > this->V) return std::list<int>();
    return this->adj[vertex];
}