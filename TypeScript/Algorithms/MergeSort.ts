class MergeSort
{
    arrClone : number[];

    public sort(arr: number[])
    {
        // Clone the array for sorting.
        this.arrClone = Object.assign([], arr);
        this.sortAux(arr, 0, arr.length - 1);

    }

    private sortAux(arr: number[], low: number, high: number)
    {
        if(low < high)
        {
            const mid = Math.floor((low + high) / 2);

            this.sortAux(arr, low, mid);
            this.sortAux(arr, mid + 1, high);

            this.merge(arr, low, mid, high);

        }
    }

    private merge(arr: number[], low: number, mid: number, high: number)
    {
        let i = low;
        let j = mid + 1;
        let k = 0;

        for(k = low; k <= high; k++)
        {
            this.arrClone[k] = arr[k];
        }

        for(k = low; k <= high; k++)
        {
            if( i > mid)
            {
                arr[k] = this.arrClone[j];
                j++;
            }
            else if(j > high)
            {
                arr[k] = this.arrClone[i];
                i++;
            }
            else if(this.arrClone[i] > this.arrClone[j])
            {
                arr[k] = this.arrClone[j];
                j++;
            }
            else
            {
                arr[k] = this.arrClone[i];
                i++;
            }
        }
    }
}