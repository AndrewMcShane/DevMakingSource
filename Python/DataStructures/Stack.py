class Node:
    def __init__(self, value, next):
        self.value = value
        self.next = next

class Stack:
    def __init__(self):
        self.head = None
        self.size = 0
    
    def isEmpty(self):
        return self.size <= 0

    def peek(self):
        if self.isEmpty():
            raise IndexError
        return self.head.value
    
    def push(self, value):
        if self.isEmpty():
            self.head = Node(value, None)
            self.size += 1
            return
        tmp = Node(value, self.head)
        self.head = tmp
        self.size += 1
    
    def pop(self):
        if self.isEmpty():
            raise IndexError
        tmp = self.head
        self.head = self.head.next
        self.size -= 1
        return tmp.value
    
    def poll(self):
        if self.isEmpty():
            return None
        tmp = self.head
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