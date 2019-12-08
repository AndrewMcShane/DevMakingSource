#include <stdio.h>

// Iterative implementation of Binary Search:
int BinarySearchIterative(int arr[], int size, int num) {
    int mid, low, high;
    low = 0;
    high = size - 1;

    while(low <= high) {
        mid = (int)((low + high) / 2);

        printf("l: %d, m:%d, h:%d\n", low, mid, high);

        if(num < arr[mid]) {
            high = mid - 1;
        }
        else if(num > arr[mid]) {
            low = mid + 1;
        }
        else {
            return mid;
        }
    }
    return -1;
}


int BinarySearchRecursiveAux(int arr[], int num, int low, int high) {
    int mid = (int)((low + high) / 2);

    if(low >= high) return -1;

    if(num < arr[mid]) {
        return BinarySearchRecursiveAux(arr, num, low, mid - 1);
    }
    else if (num > arr[mid]) {
        return BinarySearchRecursiveAux(arr, num, mid + 1, high);
    }
    else {
        return mid;
    }
}


int BinarySearchRecursive(int arr[], int size, int num) {
    return BinarySearchRecursiveAux(arr, num, 0, size - 1);
}




int main() {
    // Test array:
    int a[] = {1,2,3,4,5,6,7,8,9};
    // Get the number of elements in the array:
    int s = sizeof(a) / sizeof(a[0]);

    // Check the iterative approach:
    if(BinarySearchIterative(a, s, 8) > 0) {
        printf("Array contains 8\n");
    } 
    else {
        printf("Array does not contain 8\n");
    }
    // Check the recursive approach:
    if(BinarySearchRecursive(a, s, 10) > 0) {
        printf("Array contains 10\n");
    }
    else {
        printf("Array does not contain 10\n");
    }

    // exit
    return 0;
}