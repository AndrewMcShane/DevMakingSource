class MinHeap
{
    private data: Array<number>;
    private size: number;

    constructor()
    {
        this.data = new Array<number>();
        this.size = 0;
    }

    public isEmpty(): boolean
    {
        return this.size <= 0;
    }

    public findMin(): number
    {
        if(this.isEmpty())
        {
            throw new RangeError("Heap is empty.");
        }
        return this.data[0];
    }

    public insert(value: number)
    {
        this.size++;
        this.data.push(value);
        this.swim(this.size - 1);
    }

    private swim(index: number)
    {
        if(index != 0)
        {
            const parent = Math.floor((index - 1) / 2.0);

            if(this.data[parent] > this.data[index])
            {
                const tmp = this.data[parent];
                this.data[parent] = this.data[index];
                this.data[index] = tmp;

                this.swim(parent);
            }
        }
    }

    public deleteMin()
    {
        if(this.isEmpty())
        {
            throw new RangeError("Heap is empty.");
        }

        const lastValue = this.data.pop();

        this.size--;

        if(this.size >= 1)
        {
            this.data[0] = lastValue;
            this.sink(0);
        }
    }

    private sink(index: number)
    {
        const leftChild = (index * 2) + 1;
        const rightChild = (index * 2) + 2;
        let minimum = leftChild;

        if(rightChild >= this.size)
        {
            if(leftChild >= this.size)
            {
                return;
            }
        }
        else if(this.data[leftChild] > this.data[rightChild])
        {
            minimum = rightChild;
        }

        if(this.data[index] > this.data[minimum])
        {
            const tmp = this.data[minimum];
            this.data[minimum] = this.data[index];
            this.data[index] = tmp;

            this.sink(minimum);
        }
    }
}