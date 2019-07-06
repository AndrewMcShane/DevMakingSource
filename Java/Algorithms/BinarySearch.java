package Algorithms;

public class BinarySearch {

	// Assumes 'arr' is in sorted order.
	
	// Iterative implementation of Binary Search
	public static int binarySearchIterative(int[] arr, int num) {
		int mid, low, high;
		low = 0;
		high = arr.length - 1;
		
		while(low <= high) {
			
			mid = (low + high) / 2;
			
			if(num < arr[mid]) {
				high = mid - 1;
			}
			else if(num > arr[mid]) {
				low = mid + 1;
			}
			else  {
				return mid;
			}
		}
		
		return -1;
	}
	
	// Recursive implementation of Binary Search
	public static int binarySearchRecursive(int[] arr, int num) {
		return binarySearchRecursiveAux(arr, num, 0, arr.length  - 1);
	}
	// Helper method for the recursive approach.
	private static int  binarySearchRecursiveAux(int[] arr, int num, int low, int high) {
		int mid =  (low + high) / 2;
		
		if(low > high) return -1;
		
		if(num < arr[mid]) {
			return binarySearchRecursiveAux(arr, num, low, mid - 1);
		}
		else if(num > arr[mid]) {
			return binarySearchRecursiveAux(arr, num, mid + 1, high);
		}
		else return mid;
	}
}
