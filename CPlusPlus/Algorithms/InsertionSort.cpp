
#include <stdio.h>
/* Insertion Sort in C++ */

void sort(int arr[], int size) {
    
    for(int i = 0; i < size; ++i) {
        // Set a temp of the current variable
        int tmp = arr[i];
        int j = i - 1;
        // While j is out of place in the array, iterate backwards.
        while(j >= 0 && arr[j] > tmp) {
            arr[j+1] = arr[j];
            j -= 1;
        }
        // Assign the correct location of i where j stops.
        arr[j+1] = tmp;
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