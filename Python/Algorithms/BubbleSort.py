# Regular bubble sort implementation
def bubbleSort(arr):
    size = len(arr)
    for i in range(size - 1):
        for j in range(size - i - 1):
            if arr[j] > arr[j + 1]:
                swap(arr, j, j + 1)

# A faster version of bubble sort!
def bubbleSortFaster(arr):
    size = len(arr)
    for i in range(size - 1):
        swapped = False
        for j in range(size - i - 1):
            if  arr[j] > arr[j + 1]:
                swap(arr,  j, j + 1)
                swapped = True
        
        if not swapped:
            break

# Helper function to swap elements in place
def swap(arr, a, b):
    tmp = arr[a]
    arr[a] = arr[b]
    arr[b] = tmp

# ----------
# Demo Code
# ----------
array = [2,5,3,7,9,4,6]
print(bubbleSort(array.copy()))
print(bubbleSortFaster(array.copy()))
