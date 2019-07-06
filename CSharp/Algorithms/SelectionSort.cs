public static class SelectionSort {

	public static void Sort(int[] arr) 
    {
		// Iterate over the entire array, except the last element (0...n-1).
		for(int i = 0; i < arr.Length - 1; i++) 
        {
			int min = i;
			// Iterate the remaining array (i...n)
			for(int j = i + 1; j < arr.Length; j++) 
            {
				// If j is less than the minimum, update the min index.
				if(arr[j] < arr[min]) 
                {
					min = j;
				}
			}
			// Do an in place swap.
			int tmp = arr[min];
			arr[min] = arr[i];
			arr[i] = tmp;
		}
	}
}