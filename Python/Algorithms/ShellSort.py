def shellSort(arr):
    gap = 0
    while gap < (len(arr) / 3):
        gap = gap * 3 + 1
    
    while gap > 0:
        for i in range(gap, len(arr)):
            j = i
            tmp = arr[i]

            while j >= gap and arr[j - gap] > tmp:
                arr[j] = arr[j - gap]
                j -= gap
            
            arr[j] = tmp

        gap = (gap - 1) / 3

# ----------
# Demo Code
# ----------
array = [7,4,6,2,3,9,1]
print(shellSort(array))
