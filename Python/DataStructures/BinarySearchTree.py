
class TreeNode:
    def __init__(self, value, right, left):
        self.value = value
        self.right = right
        self.left = left
    

class BinarySearchTree:

    def __init__(self):
        self.root = None

    def isEmpty(self):
        return self.root is None
    
    def add(self, value):
        if self.root is None:
            self.root = TreeNode(value, None, None)
            return
        self._addRecursive(value, self.root)

    def _addRecursive(self, value, node):
        if value > node.value:
            if node.right is not None:
                self._addRecursive(value, node.right)
            else:
                node.right = TreeNode(value, None, None)
        elif value < node.value:
            if node.left is not None:
                self._addRecursive(value, node.left)
            else:
                node.left = TreeNode(value, None, None)
            
    def contains(self, value):
        if self.root is None:
            return False
        return self._containsRecursive(value, self.root)
    
    def _containsRecursive(self, value, node):
        if node is None:
            return False
        if value > node.value:
            return self._containsRecursive(value, node.right)
        elif value < node.value:
            return self._containsRecursive(value, node.left)
        else:
            return True
    
    def find(self, value):
        if self.root is None:
            return None
        return self._findRecursive(value, self.root)

    def _findRecursive(self, value, node):
        if node is None:
            return None
        
        if value > node.value:
            return self._findRecursive(value, node.right)
        elif value < node.value:
            return self._findRecursive(value, node.left)
        else:
            return node
    
    def remove(self, value):
        if self.root is None:
            return
        else:
            self.root = self._removeRecursive(value, self.root)
    
    def _removeRecursive(self, value, node):

        if node is None:
            return None
        
        if value > node.value:
            node.right = self._removeRecursive(value, node.right)
        elif value < node.value:
            node.left = self._removeRecursive(value, node.left)
        else:
            if node.left is not None and node.right is not None:
                tmp = node
                leftMost = self._getLeftMost(tmp.right)
                node.value = leftMost.value

                self.root.right = self._removeRecursive(leftMost.value, node.right)

            elif node.left is not None:
                node = node.left
            elif node.right is not None:
                node = node.right
            else:
                node = None
                
        return node
    
    def _getLeftMost(self, node):
        if node is None:
            return None
        elif node.left is None:
            return node
        else:
            return self._getLeftMost(node.left)

    def clear(self):
        self.root = None

    def __str__(self):
        return "[" + self.toString(self.root) + "]"

    def toString(self, node):
        res = ""
        if node is None:
            return res
        
        if node.left is not None:
            res += self.toString(node.left)
        res += str(node.value) + " "
        if node.right is not None:
            res += self.toString(node.right)
        return res