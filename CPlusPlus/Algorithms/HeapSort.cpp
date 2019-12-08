#include <stdio.h>


void swap(int arr[], const int a, const int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
}

void heapify(int arr[], const int size, const int i) {
    int largest = i;
    int leftLeaf = 2*i + 1;
    int rightLeaf = 2*i + 2;

    if(leftLeaf < size && arr[leftLeaf] > arr[largest]) {
        largest = leftLeaf;
    }

    if(rightLeaf < size && arr[rightLeaf] > arr[largest]) {
        largest = rightLeaf;
    }

    if(largest != i) {
        swap(arr, i, largest);
        heapify(arr, size, largest);
    }
}

void sort(int arr[], const int size) {

    for(int i = (int)(size / 2 - 1); i >= 0; --i) {
        heapify(arr, size, i);
    }

    int j = size - 1;
    while(j >= 1) {
        swap(arr, 0, j);
        heapify(arr,j,0);
        --j;
    }
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