def InsertionSort(arr):
    # Iterate over the array (0..n).
    for i in range(len(arr) - 1):
        tmp = arr[i]
        j = i - 1
        # While j is out of place, iterate backwards.
        while j >= 0 and arr[j] > tmp:
            arr[j+1] = arr[j]
            j -= 1

        # Assign the correct location of i where j stops. 
        arr[j+1] = tmp