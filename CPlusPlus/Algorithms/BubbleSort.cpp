#include <stdio.h>

void sort(int arr[], int size) {

    for(int i = 0; i < size - 1; ++i) {
        for(int j = 0; j < size - i - 1; ++j) {

            if(arr[j] > arr[j+1]) {
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }
    }
}

// Faster version of sort
void sortFaster(int arr[], int size) {
    bool swapped;
    for(int i = 0; i < size - 1; ++i) {
        swapped = false;
        for(int j = 0; j < size - i - 1; ++j) {
            if(arr[j] > arr[j+1]) {
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;

                swapped = true;
            }
        }
        if(!swapped) {
            break;
        }
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