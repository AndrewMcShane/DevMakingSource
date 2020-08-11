class BubbleSort
{
    public sort(arr: number[])
    {
        const size = arr.length;

        for(let i = 0; i < size - 1; i++)
        {
            for(let j = 0; j < size - i - 1; j++)
            {
                if(arr[j] > arr[j + 1])
                {
                    this.swap(arr, j, j + 1);
                }
            }
        }
    }

    public sortFaster(arr: number[])
    {
        const size = arr.length;
        let swapped: boolean = false;

        for(let i = 0; i < size - 1; i++)
        {
            swapped = false;

            for(let j = 0; j < size - i - 1; j++)
            {
                if(arr[j] > arr[j + 1])
                {
                    this.swap(arr, j,  j + 1);
                    swapped = true;
                }
            }
            // If no swaps happened, we have a sorted array:
            if(!swapped)
            {
                break;
            }
        }
    }

    private swap(arr: number[], a: number, b: number)
    {
        const tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}