class SelectionSort
{
    public sort(arr: number[])
    {
        for(let i = 0; i < arr.length - 1; i++)
        {
            let min = i;
            
            for(let j = i + 1; j < arr.length; j++)
            {
                if(arr[j] < arr[min])
                {
                    // Choose the lesser of the two:
                    min = j;
                }
            }
            // In-place swap:
            const tmp = arr[min];
            arr[min] = arr[i];
            arr[i] = tmp;
        }
    }
}