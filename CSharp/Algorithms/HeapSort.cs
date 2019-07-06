using System;
public static class HeapSort {
	
	public static void Sort(int[] arr) {
		int size = arr.Length;
		// Heapify
		for(int i = size / 2 - 1; i >= 0; i--) 
        {
			Heapify(arr, size, i);
		}
		
		int j = size - 1;
		while (j >= 1) 
        {
			// Swap the first with arr[i]. 
			Swap(arr, 0, j);
			// Heapify the array again.
			Heapify(arr, j, 0);
			// Then decrement i.
			j--;
		}
	}
	
	private static void Heapify(int[] arr, int size, int i) 
    {	
		int largest = i;
		// i-th element's left child.
		int leftLeaf = 2*i + 1;
		// i-th element's right child.
		int rightLeaf = 2*i + 2;
		
		// If the left child is larger than the current largest.
		if (leftLeaf < size && arr[leftLeaf] > arr[largest]) 
        {
			largest = leftLeaf;
		}
		// If the right child is larger than the current largest.
		if (rightLeaf < size && arr[rightLeaf] > arr[largest]) 
        {
			largest = rightLeaf;
		}
		
		// If the largest of the two is not the original largest
		if (largest != i) 
        {
			// Swap i and the largest.
			Swap(arr, i, largest);
			// Heapify the sub-tree. 
			Heapify(arr, size, largest); 
		}
	}
	
	private static void Swap(int[] arr, int a, int b) 
    {
		int tmp = arr[a];
		arr[a]  = arr[b];
		arr[b] = tmp;
	}

}