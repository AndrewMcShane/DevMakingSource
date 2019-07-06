using System;

public static class BinarySearch 
{

	// Assumes 'arr' is in sorted order.
	
	// Iterative implementation of Binary Search
	public static int BinarySearchIterative(int[] arr, int num) 
    {
		int mid, low, high;
		low = 0;
		high = arr.Length - 1;
		
		while(low <= high) 
        {
			
			mid = (low + high) / 2;
			
			if(num < arr[mid]) 
            {
				high = mid - 1;
			}
			else if(num > arr[mid]) 
            {
				low = mid + 1;
			}
			else  
            {
				return mid;
			}
		}
		return -1;
	}
	
	// Recursive implementation of Binary Search
	public static int BinarySearchRecursive(int[] arr, int num) 
    {
		return BinarySearchRecursiveAux(arr, num, 0, arr.Length  - 1);
	}
	// Helper method for the recursive approach.
	private static int BinarySearchRecursiveAux(int[] arr, int num, int low, int high) 
    {
		int mid =  (low + high) / 2;
		
		if(low >= high) return -1;

        if (num < arr[mid])
        {
            return BinarySearchRecursiveAux(arr, num, low, mid - 1);
        }
        else if (num > arr[mid])
        {
            return BinarySearchRecursiveAux(arr, num, mid + 1, high);
        }
        else
        {
            return mid;
        }
	}
}
