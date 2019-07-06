#Linked List python implementation
# Node class
class Node:
    def __init__(self, value, nextNode):
        self.value = value
        self.next = nextNode

#Linked List class
class LinkedList:
    def __init__(self):
        self.root = None
        
    def isEmpty(self):
        return self.root is None
    
    # Does this linked list contain a certain value?
    def contains(self, value):
        if self.isEmpty():
            return False
        tmp = self.root
        # While the temp is valid
        while tmp is not None:
            # If the value is a match, return true.
            if tmp.value == value:
                return True
            # Otherwise, iterate.
            tmp = tmp.next
        # No matches found, so return false.
        return False
    
    def get(self, index):
        if self.isEmpty():
            raise IndexError('index out of range on LinkedList->get method')
        tmp = self.root
        # iterate from the root to the given index.
        for i in range(0, index):
            tmp = tmp.next
            # We are out of bounds, raise an exception.
            if tmp is None:
                raise IndexError('index out of range on LinkedList->get method')
        # Return the value of the current node.
        return tmp.value

    
    def getFirst(self):
        return self.root
    
    
    def getLast(self):
        if self.isEmpty():
            return None
        tmp = self.root
        # Iterate over the linked list.
        while tmp.next is not None:
            tmp = tmp.next
        # Return the value of the last node.
        return tmp.value

    # Add a specified value to the end of the linked list.
    def add(self, value):
        if self.isEmpty():
            self.root = Node(value, None)
            return
        
        tmp = self.root
        # Iterate over the list.
        while tmp.next is not None:
            tmp = tmp.next
        # Once at the last node, set it's next node to the new node.
        tmp.next = Node(value, None)
    
    def addFirst(self, value):
        # Create a new node with the root as it's next node.
        tmp = Node(value, self.root)
        # Change the reference of the root to the new node.
        self.root = tmp

    # Add a new node after a specified key.
    def addAfter(self, key, toAdd):
        if self.isEmpty():
            self.root = Node(toAdd, None)
        tmp = self.root
        # Iterate until we find a match.
        while tmp.next is not None:
            # Found a match.
            if tmp.value == key:
                newNode = Node(toAdd, tmp.next)
                tmp.next = newNode
                return
            tmp = tmp.next
        # Depends on implementation, but in this version
        # we add a new node at the end if no node was matched.
        tmp.next = Node(toAdd, None)
    
    # Add a new node before a specified key.
    def addBefore(self, key, toAdd):
        # Base cases:
        if self.isEmpty():
            self.root = Node(toAdd, None)
        tmpPrev = self.root
        tmp = self.root.next
        if tmpPrev.value == key:
            self.addFirst(toAdd)
            return
        # Main case:
        # Iterate the list.
        while tmp is not None:
            # If the lead node is a match, break...
            if tmp.value == key:
                break
            # ( Iterate )
            tmpPrev = tmp
            tmp = tmp.next
        # ... and insert the new node at the intermediate.
        tmpPrev.next = Node(toAdd, tmp)
    
    # Remove a node with the specified value from the list.
    def remove(self, value):
        if self.isEmpty():
            return
        tmp = self.root
        # Iterate the list.
        while tmp.next is not None:
            # If the next node is a match,...
            if tmp.next.value == value:
                # set the next to be the next next.
                # (gc will help us out).
                tmp.next = tmp.next.next
                return
            # Otherwise iterate.
            tmp = tmp.next
                
    # Remove the first node from the list.
    def removeFirst(self):
        if self.isEmpty():
            return
        # We just increment the root to the next node.
        self.root = self.root.next
    
    # Remove the last node from the list
    def removeLast(self):
        # Base cases:
        if self.isEmpty():
            return
        tmp = self.root
        if tmp.next is None:
            self.root = None
            return
        # Main case:
        # We need to look 2 ahead to set the 'next' node to none.
        while tmp.next.next is not None:
            tmp = tmp.next
        # Set the 'next' node to none.
        tmp.next = None

    # Get the length of the linked list.
    def length(self):
        count = 0
        tmp = self.root
        while tmp is not None:
            count+=1
            tmp = tmp.next
        return count
    
    # Get the index of a specified value, if it exists.
    def indexOf(self, value):
        if self.isEmpty():
            return -1
        count = 0
        tmp = self.root
        # Iterate the list.
        while tmp is not None:
            if tmp.value == value:
                return count
            # Increment and iterate.
            count+=1
            tmp = tmp.next
        # Value doesn't exist, return -1.
        return -1
    
    # Clear the linked list.
    def clear(self):
        self.root = None
        return

    # Format the linked list to a string.
    def __str__(self):
        res = "["
        tmp = self.root
        while tmp is not None:
            res += str(tmp.value)
            if tmp.next is not None:
                res += ", "
            tmp = tmp.next
        res += "]"
        return res
