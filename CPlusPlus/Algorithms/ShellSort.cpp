#include <stdio.h>

void sort(int arr[], int size) {

    int gap = 0;
    while( gap < (int)(size / 3)) {
        gap = gap * 3 + 1;
    }

    while(gap > 0) {
        // Insertion sort on the gap:
        for(int i = gap; i < size; ++i) {
            int j = i;
            int tmp = arr[i];
            // While there exists an inversion in the sub array:
            while(j >= gap && arr[j - gap] > tmp) {
                arr[i] = arr[j-gap];
                j -= gap;
            }
            // Set the correct element:
            arr[j] = tmp;
        }
        gap = (int)((gap - 1) / 3);
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