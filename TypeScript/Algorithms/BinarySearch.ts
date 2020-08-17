class BinarySearch
{
    public binarySearchIterative(arr: number[], num: number) : number
    {
        let low = 0;
        let mid = 0;
        let high = arr.length - 1;

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
        // Arbitrary value to signify non-existance.
        return -1;
    }

    public binarySearchRecursive(arr: number[], num: number) : number
    {
        return this.binarySearchRecursiveAux(arr, num, 0, arr.length - 1);
    }

    private binarySearchRecursiveAux(arr: number[], num: number, low: number, high: number)
    {
        const mid = (low + high) / 2;

        if(low >= high) return -1;

        if(num < arr[mid])
        {
            return this.binarySearchRecursiveAux(arr, num, low, mid - 1);
        }
        else if(num > arr[mid])
        {
            return this.binarySearchRecursiveAux(arr, num, mid + 1, high);
        }
        else
        {
            return mid;
        }
    }
}