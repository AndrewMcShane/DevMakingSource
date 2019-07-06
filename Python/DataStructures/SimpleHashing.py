
class HashNode:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None

class HashTable:
    def __init__(self, numBuckets = 16):
        self.buckets = [None] * numBuckets
        self.numBuckets = numBuckets
        self.size = 0

    def isEmpty(self):
        return self.size <= 0
    
    def _getHash(self, key):
         return hash(key) % self.numBuckets

    def put(self, key, value):
        bucket = self._getHash(key)
        newNode = HashNode(key, value)
        if self.buckets[bucket] is None:
            self.buckets[bucket] = newNode
        else:
            tmp = self.buckets[bucket]
            while tmp.next is not None:
                if tmp.key == key:
                    tmp.value = value
                    return
                tmp = tmp.next
            tmp.next = newNode
            self.size += 1
        return
    
    def get(self, key):
        bucket = self._getHash(key)
        tmp = self.buckets[bucket]
        while tmp is not None:
            if tmp.key == key:
                return tmp.value
            tmp = tmp.next
        return None

    def contains(self, key):
        bucket = self._getHash(key)
        tmp = self.buckets[bucket]
        while tmp is not None:
            if tmp.key == key:
                return True
            tmp = tmp.next
        return False

    def remove(self, key):
        bucket = self._getHash(key)
        if self.buckets[bucket] is  None:
            return
        tmp = self.buckets[bucket]
        if tmp.key == key:
            self.buckets[bucket] = self.buckets[bucket].next
            self.size -= 1
        while tmp.next is not None:
            if tmp.next.key == key:
                tmp.next = tmp.next.next
                self.size -= 1
                return
            tmp = tmp.next
    
    def clear(self):
        self.buckets = [None] * self.numBuckets
    
    def __str__(self):
        res = "{"
        count = 0
        for i in range(0, self.numBuckets):
            if self.buckets[i] is not None:
                tmp = self.buckets[i]
                while tmp is not None:
                    res += tmp.key + ": " + tmp.value
                    count += 1
                    if count < self.size:
                        res += ", "
                    tmp = tmp.next
        
        res += " }"
        return  res

