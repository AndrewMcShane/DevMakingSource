using System;

public static class BubbleSort {

	public static void Sort(int[] arr) 
    {
		int size = arr.Length;

		for(int i = 0; i < size - 1; i++) 
        {
			// For each element, loop over and find inversions.
			for(int j = 0; j < size - i - 1; j++ ) 
            {
				// If i and j are inverted..
				if ( arr[j] > arr[j+1] ) 
                {
					// .. Swap the elements.
					Swap( arr, j, j + 1 );
				}
			}
	    }   
	}
	
	public static void SortFaster(int[] arr) 
    {	
		int size = arr.Length;
		bool swapped;
		// Loop over the array
		for( int i = 0; i < size - 1; i++ ) 
        {
			// Set the swapped flag to false.
			swapped = false;
			// For each element, loop over and find inversions.
			for( int j = 0; j < size - i - 1; j++ ) 
            {
				// If i and j are inverted..
				if ( arr[j] > arr[j+1] ) 
                {
					// .. Swap the elements..
					Swap( arr, j, j + 1 );
					// .. And set swapped to true.
					swapped = true;
				}
			}
			// Check if any swaps occurred.
			if (!swapped) 
            {
				// If not, we can exit.
				break;
			}
		}
	}
	
	private static void Swap(int[] arr, int a, int b) 
    {
		int tmp = arr[a];
		arr[a]  = arr[b];
		arr[b] = tmp;

	}
}
