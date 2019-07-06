# Easy, single parameter method call to get the method going.
def quickSort(arr):
    quickSortAux(arr, 0, len(arr))

# Actual quicksort algorithm
def quickSortAux(arr, low, high):
    if low < high:
        # Partition the array
        p = partition(arr, low, high)
        # Sort the left half
        quickSortAux(arr, low, p - 1)
        # Sort the right half
        quickSortAux(arr, p + 1, high)

# Partition 
def partition(arr, low, high):
    # Pick a pivot
    pivot = arr[high]
    i = low
    # If a number is less than the pivot, put it on the left
    # if more, put it on the right
    for j in range(low, high):
        if arr[j] < pivot:
            swap(arr, i, j)
            i += 1
    swap(arr, i, high)
    return i

# Auxiliary swap method
def swap(arr, a, b):
    tmp = arr[a]
    arr[a] = arr[b]
    arr[b] = tmp
