public class MergeSort {

	private int[] arrClone;
	
	public void sort(int [] arr) 
    {
		arrClone = arr.Clone() as int[];
		sort(arr, 0, arr.Length - 1);
	}
	
	private void sort(int[] arr, int low, int high) 
    {
		
		if(low < high) {
			int mid = (low + high) / 2;
			
			sort(arr, low, mid);
			sort(arr, mid + 1, high);
			
			merge(arr, low, mid, high);	
		}
	}
	
	private void merge(int[] arr, int low, int mid, int high) 
    {
		int i,j,k;
		i = low;
		j = mid + 1;
		
		for(k = low; k <= high; k++) 
        {
			arrClone[k] = arr[k];
		}
		
		for(k = low; k <= high; k++) 
        {
			if ( i > mid ) {
				arr[k] = arrClone[j];
				j++;
			}
			else if ( j > high ) {
				arr[k] = arrClone[i];
				i++;
			}
			else if ( arrClone[i] > arrClone[j] ) 
            {
				arr[k] = arrClone[j];
				j++;
			}
			else 
            {
				arr[k] = arrClone[i];
				i++;
			}
		}
	}
}
