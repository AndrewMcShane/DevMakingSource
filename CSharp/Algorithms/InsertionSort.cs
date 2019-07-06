public static class InsertionSort 
{
	public static void Sort(int[] arr) 
	{
		// Iterate over the array (0..n).
		for(int i = 0; i < arr.Length; i++) 
		{
			int tmp = arr[i];
			int j = i - 1;
			// While j is out of place, iterate backwards.
			while(j >= 0 && arr[j] > tmp) 
			{
				arr[j+1] = arr[j];
				j = j - 1;
			}
			// Assign the correct location of i where j stops. 
			arr[j+1] = tmp;
		}
	}
}