
class Graph:

    def __init__(self, vertices):
        self.adj = {}
        self.V = vertices
    
    def addEdge(self, fromVert, toVert):
        self.adj[fromVert].append(toVert)
        # self.adj[toVert].append(fromVert)

    def size(self):
        return self.V

    def getEdges(self, vertex):
        if vertex > self.V:
            return None
        return self.adj[vertex]
