package Algorithms;

public class BubbleSort {

	public static void bubbleSort(int[] arr) {
		
		int size = arr.length;

		for(int i = 0; i < size - 1; i++) {
			// For each element, loop over and find inversions.
			for(int j = 0; j < size - i - 1; j++ ) {
				// If i and j are inverted..
				if ( arr[j] > arr[j+1] ) {
					// .. Swap the elements.
					swap( arr, j, j + 1 );
				}
			}
	}
	}
	
	public static void bubbleSortFaster(int[] arr) {
		
		int size = arr.length;
		boolean swapped;
		// Loop over the array
		for( int i = 0; i < size - 1; i++ ) {
			// Set the swapped flag to false.
			swapped = false;
			// For each element, loop over and find inversions.
			for( int j = 0; j < size - i - 1; j++ ) {
				// If i and j are inverted..
				if ( arr[j] > arr[j+1] ) {
					// .. Swap the elements..
					swap( arr, j, j + 1 );
					// .. And set swapped to true.
					swapped = true;
				}
			}
			// Check if any swaps occurred.
			if (!swapped) {
				// If not, we can exit.
				break;
			}
		}

	}
	
	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a]  = arr[b];
		arr[b] = tmp;

	}

	public static void main(String[] args) {
		int[] arr = {9,8,7,6,5,4,3,2,1};
		bubbleSortFaster(arr);
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
