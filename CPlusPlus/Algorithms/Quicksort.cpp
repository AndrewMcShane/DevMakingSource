#include <stdio.h>

void swap(int arr[], const int a, const int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
}

int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = low;

    for(int j = low; j < high; ++j) {
        if(arr[j] < pivot) {
            swap(arr, i, j);
            ++i;
        }
    }
    swap(arr, i, high);
    return i;
}

void quicksort(int arr[], int low, int high) {
    if(low < high) {
        int p = partition(arr, low, high);

        quicksort(arr, low, p - 1);
        quicksort(arr, p + 1, high);
    }
}

void sort(int arr[], const int size) {
    quicksort(arr, 0, size - 1);
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