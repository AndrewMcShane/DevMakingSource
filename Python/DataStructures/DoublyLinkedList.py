
# Doubly linked List node class
class Node:
    def __init__(self, value, nextNode, prevNode):
        self.value = value
        self.next = nextNode
        self.prev = prevNode

# Doubly linked list implementation
class DoublyLinkedList:
    def __init__(self):
        self.size = 0
        self.head = None
        self.tail = None
    
    def isEmpty(self):
        return self.head is None
    
    def contains(self, value):
        if self.isEmpty():
            return False
        tmp = self.head
        while tmp is not None:
            if tmp.value == value:
                return True
            tmp = tmp.next
        return False
    
    def get(self, index):
        if index > self.size and self.isEmpty():
            raise IndexError
        
        if index > self.size / 2:
            index = (self.size - 1) - index
            tmp = self.tail
            while index > 0:
                tmp = tmp.prev
                index -= 1
            return tmp.value
        else:
            tmp = self.head
            while index > 0:
                tmp = tmp.next
                index -= 1
            return tmp.value
    
    # Python uses public variables so these aren't really necessary, but they
    # are added for code consistency
    def getFirst(self):
        return self.head
    def getLast(self):
        return self.tail

    def addLast(self, value):
        if self.isEmpty():
            tmp = Node(value, None, None)
            self.head = tmp
            self.tail = tmp
            self.size += 1
            return
        
        tmp = self.tail
        tmp.next = Node(value, None, tmp)
        self.tail = tmp.next
        self.size += 1
    
    def addFirst(self, value):
        if self.isEmpty():
            tmp = Node(value, None, None)
            self.head = tmp
            self.tail = tmp
            self.size += 1
            return
        
        tmp = self.head
        tmp.prev = Node(value, tmp, None)
        self.head = tmp
        self.size += 1

    def addAfter(self, key, toAdd):
        if self.isEmpty():
            raise IndexError
        tmp = self.head
        while tmp is not None:
            if tmp.value == key:
                newNode = Node(toAdd, tmp.next, tmp)
                tmp.next = newNode
                if(newNode.next is not None):
                    newNode.next.prev = newNode
                else:
                    self.tail = newNode
                self.size += 1
                return
            tmp = tmp.next
        
        raise IndexError
    
    def addBefore(self, key, toAdd):
        if self.isEmpty():
            raise IndexError
        tmp = self.head
        while tmp is not None:
            if tmp.value == key:
                newNode = Node(toAdd, tmp, tmp.prev)
                tmp.prev = newNode
                if newNode.prev is not None:
                    newNode.prev.next = newNode
                else:
                    self.head = tmp
                
                self.size += 1
                return
            tmp = tmp.next
        
        raise IndexError

    def remove(self, value):
        if self.isEmpty():
            return
        tmp = self.head
        while tmp is not None:
            if tmp.value == value:
                if tmp.prev is not None:
                    tmp.prev.next = tmp.next
                else:
                    self.head = tmp.next
                
                if tmp.next is not None:
                    tmp.next.prev = tmp.prev
                else:
                    self.tail = tmp.prev
                
                self.size -= 1
                return
            tmp = tmp.next
        return
    
    def removeFirst(self):
        if self.isEmpty():
            return

        if self.size == 1:
            self.head = None
            self.tail = None
            self.size -= 1
            return
        
        self.head = self.head.next
        self.head.prev = None
        self.size -= 1

    def removeLast(self):
        if self.isEmpty():
            return

        if self.size == 1:
            self.head = None
            self.tail = None
            self.size -= 1
            return
        
        self.tail = self.tail.prev
        self.tail.next = None
        self.size -= 1

    def indexOf(self, value):
        index = 0
        tmp = self.head
        while tmp != None:
            if tmp.value == value:
                return index
            tmp = tmp.next
            index += 1

        return -1

    def clear(self):
        self.size = 0
        self.head = None
        self.tail = None

    def __str__(self):
        res = "["
        tmp = self.head
        while tmp is not None:
            res += str(tmp.value)
            if tmp.next is not None:
                res += ", "
            tmp = tmp.next
        res += "]"
        return res

