# Iterative binary search
def binarySearchIterative(arr, num):
    low = 0
    high = len(arr) - 1
    
    while low <= high:
        mid = int((low + high) / 2)

        if num < arr[mid]:
            high = mid - 1
        elif num > arr[mid]:
            low = mid + 1
        else:
            return mid
    
    return -1

# Recursive binary search
def binarySearchRecursive(arr, num):
    return binarySearchRecursiveAux(arr, num, 0, len(arr) - 1)

# Recursive binary search helper
def binarySearchRecursiveAux(arr, num, low, high):
    mid = int((low + high) / 2)

    if low > high:
        return -1
    
    if num < arr[mid]:
        return binarySearchRecursiveAux(arr, num, low, mid - 1)
    elif num > arr[mid]:
        return binarySearchRecursiveAux(arr, num, mid + 1, high)
    else:
        return mid
    

# ---------------
# Demo code
# ---------------
array = [1,2,3,4,5,6]
print("Does the array contain 4?")
print(binarySearchIterative(array, 4))
print("Does the array contain 2?")
print(binarySearchRecursive(array, 2))
