public class Quicksort {
	
	public static void Sort(int[] arr) 
    {
		quicksort(arr, 0, arr.Length - 1);
	}
	
	private static void quicksort(int[] arr, int low, int high) 
    {
		if(low < high) 
        {
			int p = Partition(arr, low, high);
			
			quicksort(arr, low, p - 1);
			quicksort(arr, p + 1, high);
		}
	}
	
	private static int Partition(int[] arr, int low, int high) 
    {
		int pivot = arr[high];
		int i = low;
		for(int j = low; j< high; j++) 
        {
			if(arr[j] < pivot) {
				Swap(arr, i, j);
				i++;
			}
		}
		Swap(arr, i, high);
		return  i;
	}
	
	private static void Swap(int[] arr, int a, int b) 
    {
		int tmp = arr[a];
		arr[a]  = arr[b];
		arr[b] = tmp;

	}
}