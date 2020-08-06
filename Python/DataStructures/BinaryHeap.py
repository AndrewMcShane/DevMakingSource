# Heap Demo in Python

class MinHeap(object):
    def __init__(self):
        self.data = []
        self.currentSize = 0

    
    def FindMin(self):
        '''Raises ValueError if heap is empty.'''

        if self.currentSize <= 0:
            raise ValueError(self.currentSize)
        else:
            return self.data[self.currentSize - 1]
    
    def IsEmpty(self):
        return self.currentSize <= 0

    def Insert(self, value):
        self.currentSize += 1
        
        self.data.append(value)
        self._Swim(self.currentSize - 1)
        
    def _Swim(self, index):

        if index != 0:
            # Get the parent node:
            parent = int((index - 1) / 2.0)

            if self.data[parent] > self.data[index]:
                # Swap:
                tmp = self.data[parent]
                self.data[parent] = self.data[index]
                self.data[index] = tmp
                # Swim up again:
                self._Swim(parent)

    
    def DeleteMin(self):
        '''Raises ValueError if heap is empty'''

        if self.IsEmpty():
            raise ValueError(self.currentSize)
        else:
            # Get the largest value:
            lastVal = self.data.pop()
            self.currentSize -= 1
            # If the array wasn't 1 to begin with:
            if self.currentSize >= 1:
                self.data[0] = lastVal
                self._Sink(0)

    def _Sink(self, index):
        leftChild = (index * 2) + 1
        rightChild = (index * 2) + 2
        iMin = leftChild

        if rightChild >= self.currentSize:
            if leftChild >= self.currentSize:
                return
                
        elif self.data[leftChild] > self.data[rightChild]:
            iMin = rightChild

        if self.data[index] > self.data[iMin]:
            tmp = self.data[iMin]
            self.data[iMin] = self.data[index]
            self.data[index] = tmp
 
            self._Sink(iMin)


# Demo of the Binary (Min)Heap:

myHeap = MinHeap()

myHeap.Insert(1)
myHeap.Insert(30)
myHeap.Insert(5)
myHeap.Insert(201)
myHeap.Insert(9)
myHeap.Insert(29)
myHeap.Insert(21)
myHeap.Insert(35)
myHeap.Insert(4)

print(myHeap.data)

myHeap.DeleteMin()

print(myHeap.data)
