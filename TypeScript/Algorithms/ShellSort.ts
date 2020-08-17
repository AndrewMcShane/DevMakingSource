class ShellSort
{
    public sort(arr: number[])
    {
        let gap = 0;
        while(gap < Math.floor(arr.length / 3))
        {
            gap = gap * 3 + 1;
        }

        while(gap > 0)
        {
            for(let i = gap; i < arr.length; i++)
            {
                let j = i;
                const tmp = arr[i];

                while(j >= gap && arr[j - gap] > tmp)
                {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = tmp;
            }
            gap = Math.floor((gap - 1) / 3);
        }
    }
}