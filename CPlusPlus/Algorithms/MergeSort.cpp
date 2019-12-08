#include <stdio.h>
#include <cstring>

void merge(int arr[], int arrClone[], int low, int mid, int high) {
    int i,j,k;

    i = low;
    j = mid + 1;
    for(k = low; k <= high; ++k) {
        arrClone[k] = arr[k];
    }

    for(k = low; k <= high; ++k) {
        if(i > mid) {
            arr[k] = arrClone[j++];
        }
        else if(j > high) {
            arr[k] = arrClone[i++];
        }
        else if(arrClone[i] > arrClone[j]) {
            arr[k] = arrClone[j++];
        }
        else {
            arr[k] = arrClone[i++];
        }
        
    }
}

void mergeSort(int arr[], int arrClone[], int low, int high) {
    if(low < high) {
        int mid = (int)((low + high) / 2);

        mergeSort(arr, arrClone, low, mid);
        mergeSort(arr, arrClone, mid + 1, high);

        merge(arr, arrClone, low, mid, high);
    }
}

void sort(int arr[], const int size) {
    int * arrClone = new int[size];
    memcpy(arrClone, arr, size);

    mergeSort(arr, arrClone, 0, size - 1);
    
    delete[] arrClone;
}

// Demo:
int main() {
    // Test array:
    int a[] = {4, 8, 5, 1, 2, 0, 9};
    // Get the number of elements in the array:
    int s = sizeof(a) / sizeof(a[0]);

    // Sort the array:
    sort(a, s);

    for(int i = 0; i < s; ++i) {
        printf("%d ", a[i]);
    }
    printf("\n");

    return 0;
}