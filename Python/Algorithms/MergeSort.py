# Helper merge sort function
def mergeSort(arr):
    # Clone the array for the merge later
    arrClone = arr.clone()
    mergeSortAux(arr, arrClone, 0, len(arr) - 1)

# Actual merge sort 
def mergeSortAux(arr, arrClone, low, high):
    if low < high:
        mid = (low + high) / 2
        # Sort left
        mergeSortAux(arr, arrClone, low, mid)
        # Sort right
        mergeSortAux(arr, arrClone, mid + 1, high)
        # Merge
        merge(arr, arrClone, low, mid, high)

# Merge definition that sorts two sub arrays
def merge(arr, arrClone, low, mid, high):
    i = low
    j = mid + 1
    # Copy the clone array parts over
    for k in range(low, high):
        arrClone[k] = arr[k]

    for k in range(low, high):
        if i > mid:
            arr[k] = arrClone[j]
            j += 1
        elif j > high:
            arr[k] = arrClone[i]
            i += 1
        elif arrClone[i] > arrClone[j]:
            arr[k] = arrClone[j]
            j += 1
        else:
            arr[k] = arrClone[i]
            i += 1