class Node:
    def __init__(self, value, next):
        self.value = value
        self.next = next

class Queue:
    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0
    
    def isEmpty(self):
        return self.size <= 0
    
    def peek(self):
        if self.isEmpty():
            raise  IndexError
        return self.head.value
    
    def enqueue(self, value):
        if self.isEmpty():
            self.tail = Node(value, None)
            self.head = self.tail
            self.size += 1
            return
        
        self.tail.next = Node(value, None)
        self.tail = self.tail.next
        self.size += 1

    def dequeue(self):
        if self.isEmpty():
            raise IndexError
        tmp = self.head
        if self.size == 1:
            self.head = None
            self.tail = None
            self.size = 0
        else:   
            self.head = self.head.next
            self.size -= 1
        return tmp.value

    def poll(self):
        if self.isEmpty():
            return None
        tmp = self.head
        if self.size == 1:
            self.head = None
            self.tail = None
            self.size = 0
        else:   
            self.head = self.head.next
            self.size -= 1
        return tmp.value

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
