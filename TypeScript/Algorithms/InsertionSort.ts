class InsertionSort
{
    public sort(arr: number[])
    {
        // Iterate the array (0..n)
        for(let i = 0; i < arr.length; i++)
        {
            const tmp = arr[i];
            let j = i - 1;
            // Iterate while J is out of place.
            while(j >= 0 && arr[j] > tmp)
            {
                arr[j + 1] = arr[j];
                j--;
            }
            // Assign the correct location of i where j stops.
            arr[j + 1] = tmp;
        }
    }
}