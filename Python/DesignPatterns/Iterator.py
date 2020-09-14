# Simplified version of Doubly Linked List:

class Node(object):
    def __init__(self, value, nextNode, prevNode):
        self.value = value
        self.next = nextNode
        self.prev = prevNode

class DoublyLinkedList(object):
    def __init__(self):
        self.size = 0
        self.head = None
        self.tail = None
    
    def isEmpty(self):
        return self.head is None
    
    def addLast(self, value):
        if self.isEmpty():
            tmp = Node(value, None, None)
            self.head = tmp
            self.tail = tmp
            self.size += 1
            return
        
        self.tail.next = Node(value, None, self.tail)
        self.tail = self.tail.next
        self.size += 1

    def getIterator(self):
        return DLinkIterator(self.head)
    
    def getReverseIterator(self):
        return DLinkReverseIterator(self.tail)


# Forward Iterator:
class DLinkIterator(object):
    def __init__(self, startNode):
        self.current = startNode

    def hasNext(self):
        return self.current != None

    def next(self):
        if not self.hasNext():
            raise IndexError
        val = self.current.value
        self.current = self.current.next
        return val

# Backward Iterator:
class DLinkReverseIterator(object):
    def __init__(self, startNode):
        self.current = startNode

    def hasNext(self):
        return self.current != None

    def next(self):
        if not self.hasNext():
            raise IndexError
        val = self.current.value
        self.current = self.current.prev
        return val


# Demo:
myList = DoublyLinkedList()

myList.addLast("Hello")
myList.addLast("World")
myList.addLast("Goodbye")
myList.addLast("World")

# Iterate the list forwards:
forwardIter = myList.getIterator()

while forwardIter.hasNext():
    print(forwardIter.next())


'''
    Output:
        Hello
        World
        Goodbye
        World
'''

print("=======")

reverseIter = myList.getReverseIterator()

while reverseIter.hasNext():
    print(reverseIter.next())

'''
    Output:
        World
        Goodbye
        World
        Hello
'''