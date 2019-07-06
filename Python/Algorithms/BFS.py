from queue import Queue

# Basic graph implementation for reference
class Graph:
    def __init__(self, size):
        self.adj = [[]] * size
        self.size = size

# BFS method that accepts a Graph and start vertex 
def bfs(G, startVert):
    
    visited = [False] * G.size
    q = Queue()

    visited[startVert] = True

    q.put(startVert)

    while not q.empty():
        v = q.get()
        for adjV in G.adj[v]:
            if not visited[adjV]:
                visited[adjV] = True
                q.put(adjV)
