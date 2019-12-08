#include <stdio.h>

void sort(int arr[], int size) {

    // Iterate over 0..n-1
    for(int i = 0; i < size - 1; ++i) {
        int min = i;
        // Iterate over i..n
        for(int j = i + 1; j < size; ++j) {
            // If j is less than min, replace it as the new min.
            if(arr[j] < arr[min]) {
                min = j;
            }
        }
        // Swap in place.
        int tmp = arr[min];
        arr[min] = arr[i];
        arr[i] = tmp;
    }
}

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