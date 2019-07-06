class TrieNode:
    def __init__(self):
        self.children = {}
        self.isWord = False

class Trie:
    def __init__(self):
        self.root = TrieNode()
    
    def put(self, word):
        current = self.root
        for i in range(0, len(word)):
            child = word[i]
            tmp = None
            try:
                tmp = current.children[child]
            except KeyError:
                tmp = TrieNode()
                current.children[child] = tmp
            current = tmp
        current.isWord = True
    
    def contains(self, word):
        current = self.root
        for i in range(0, len(word)):
            child = word[i]
            try:
                current = current.children[child]
            except KeyError:
                return False
        return current.isWord
    
    def remove(self, word):
        self._removeRecursive(self.root, word, 0)
    
    def _removeRecursive(self, current, word, depth):
        if current is None:
            return  None
        
        if depth == len(word):
            current.isWord = False
        else:
            child = word[depth]
            if child in current.children:
                self._removeRecursive(current.children[child], word,  depth + 1)
            else:
                del current.children[child]
        
        if not bool(current.children):
            return current
        return None
    
    