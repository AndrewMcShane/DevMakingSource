def selectionSort(arr):
    # Iterate the entire array, except the last element.
    for i in range(len(arr) - 1):
        min = i
        # Iterate the remaining array
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[min]:
                min = j
        # In place  swap
        tmp = arr[min]
        arr[min] = arr[i]
        arr[i] = tmp