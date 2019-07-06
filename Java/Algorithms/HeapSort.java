package Algorithms;

public class HeapSort {
	
	public static void sort(int[] arr) {
		int size = arr.length;
		// Heapify
		for(int i = size / 2 - 1; i >= 0; i--) {
			heapify(arr, size, i);
		}
		
		int i = size - 1;
		while (i >= 1) {
			// Swap the first with arr[i]. 
			swap(arr, 0, i);
			// Heapify the array again.
			heapify(arr, i, 0);
			// Then decrement i.
			i--;
		}
	}
	
	private static void heapify(int[] arr, int size, int i) {
		
		int largest = i;
		// i-th element's left child.
		int leftLeaf = 2*i + 1;
		// i-th element's right child.
		int rightLeaf = 2*i + 2;
		
		// If the left child is larger than the current largest.
		if (leftLeaf < size && arr[leftLeaf] > arr[largest]) {
			largest = leftLeaf;
		}
		// If the right child is larger than the current largest.
		if (rightLeaf < size && arr[rightLeaf] > arr[largest]) {
			largest = rightLeaf;
		}
		
		// If the largest of the two is not the original largest
		if (largest != i) {
			// Swap i and the largest.
			swap(arr, i, largest);
			// Heapify the sub-tree. 
			heapify(arr, size, largest); 
		}
	}
	
	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a]  = arr[b];
		arr[b] = tmp;
	}

}
