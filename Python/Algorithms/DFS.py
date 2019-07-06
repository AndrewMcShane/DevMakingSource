
# Basic graph implementation for reference.
class Graph:
    def __init__(self, size):
        self.adj = [[]] * size
        self.size = size

# Iterative version of DFS.
def dfsIterative(G, startVert):
    visited = [False] * G.size
    s = []

    visited[startVert] = True
    s.append(startVert)

    while s:
        vert = s.pop()
        for adjV in G.adj[vert]:
            if not visited[adjV]:
                visited[adjV] = True
                s.append(adjV)

# Recursive implementation.
def dfsRecursive(G, startVert):
    visited = [False] * G.size
    dfsAux(G, startVert, visited)

# Recursive helper method.
def dfsAux(G, v, visited):
    visited[v] = True
    for adjV in G.adj[v]:
        if not visited[adjV]:
            # Do something before visit
            dfsAux(G, adjV, visited)
            # Do something after visiit
