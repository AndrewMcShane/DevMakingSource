# Heap sort method
def heapSort(arr):
    size = len(arr)

    for i in range(size / 2 - 1, 0, -1):
        heapify(arr, size, i)
    
    i = size - 1
    while i >= 1:
        swap(arr, 0, i)
        heapify(arr, i, 0)
        i -= 1

# Converts the remaining array to a max-heap binary tree  
def heapify(arr, size, i):
    largest = i
    leftLeaf = 2 * i + 1
    rightLeaf = 2 * i + 2

    if leftLeaf < size and arr[leftLeaf] > arr[largest]:
        largest = leftLeaf
    if rightLeaf < size and arr[rightLeaf] > arr[largest]:
        largest = rightLeaf
    
    if largest != i:
        swap(arr, i, largest)
        heapify(arr, size, largest)

# Helper method to swap in place
def swap(arr, a, b):
    tmp = arr[a]
    arr[a] = arr[b]
    arr[b] = tmp
