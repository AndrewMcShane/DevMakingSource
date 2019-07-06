package Algorithms;

public class Quicksort {
	
	public static void sort(int[] arr) {
		quicksort(arr, 0, arr.length - 1);
	}
	
	private static void quicksort(int[] arr, int low, int high) {
		if(low < high) {
			int p = partition(arr, low, high);
			
			quicksort(arr, low, p - 1);
			quicksort(arr, p + 1, high);
		}
	}
	
	private static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low;
		for(int j = low; j< high; j++) {
			if(arr[j] < pivot) {
				swap(arr, i, j);
				i++;
			}
		}
		swap(arr, i, high);
		return i;
	}
	
	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a]  = arr[b];
		arr[b] = tmp;

	}
	
	public static void main(String[] args) {
		int[] arr = {5,2,3,7,4};
		Quicksort.sort(arr);
		for(int i: arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
