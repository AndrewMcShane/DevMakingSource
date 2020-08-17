class Quicksort
{
    public sort(arr: number[])
    {
        this.quicksort(arr, 0, arr.length - 1);
    }

    private quicksort(arr: number[], low: number, high: number)
    {
        if(low < high)
        {
            const p = this.partition(arr, low, high);

            this.quicksort(arr, low, p - 1);
            this.quicksort(arr, p + 1, high);
        }
    }

    private partition(arr: number[], low: number, high: number) : number
    {
        const pivot = arr[high];
        let i = low;
        for(let j = low; j < high; j++)
        {
            if(arr[j] < pivot)
            {
                this.swap(arr, i, j);
                i++;
            }
        }
        this.swap(arr, i, high);
        return i;
    }

    private swap(arr: number[], a: number, b: number)
    {
        const tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}