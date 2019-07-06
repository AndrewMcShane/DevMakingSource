package Algorithms;

public class MergeSort {

	private int[] arrClone;
	
	public void sort(int [] arr) {
		arrClone = arr.clone();
		sort(arr, 0, arr.length - 1);
	}
	
	private void sort(int[] arr, int low, int high) {
		
		if(low < high) {
			int mid = (low + high) / 2;
			
			sort(arr, low, mid);
			sort(arr, mid + 1, high);
			
			merge(arr, low, mid, high);	
		}
	}
	
	private void merge(int[] arr, int low, int mid, int high) {
		int i,j,k;
		i = low;
		j = mid + 1;
		
		for(k = low; k <= high; k++) {
			arrClone[k] = arr[k];
		}
		
		for(k = low; k <= high; k++) {
			if ( i > mid ) {
				arr[k] = arrClone[j];
				j++;
			}
			else if ( j > high ) {
				arr[k] = arrClone[i];
				i++;
			}
			else if ( arrClone[i] > arrClone[j] ) {
				arr[k] = arrClone[j];
				j++;
			}
			else {
				arr[k] = arrClone[i];
				i++;
			}
		}
	}
	
	private static void print(int[] arr) {
		String res = "[";
		for(int i = 0; i< arr.length; i++) {
			res += arr[i];
			if(i < arr.length - 1) {
				res += ", ";
			}
		}
		res +="]";
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		// Array to sort.
		MergeSort m = new MergeSort();
		int[] arr = {10,9,8,7,5,6,4,3,2,1};
		
		m.sort(arr);
		print(arr);
		
	}
	
	
}
