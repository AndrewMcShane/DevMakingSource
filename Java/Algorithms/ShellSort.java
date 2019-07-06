package Algorithms;

public class ShellSort {
	
	public static void sort(int[] arr) {
		
		int gap = 0;
		while ( gap < arr.length / 3 ) {
			gap = gap * 3 + 1;
		}
		
		while ( gap > 0 ) {
			// Perform insertion sort on the gap.
			for ( int i = gap; i < arr.length; i++ ) {
				int j = i;
				int tmp = arr[i];
				// While there exists an inversion on the sub array..
				while ( j >= gap && arr[j - gap] > tmp ) {
					// .. Shift the elements down..
					arr[j] = arr[j - gap];
					j = j - gap;
				}
				// .. And set the correct element.
				arr[j] = tmp;
				
			}
			gap = ( gap - 1 ) / 3;
		}
	}
}