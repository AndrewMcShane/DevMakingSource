// Min-Heap Example in C#
using System.Collections.Generic;

public class MinHeap
{
    private List<int> data;
    private int currentSize;

    public MinHeap()
    {
        data = new List<int>();
        currentSize = 0;
    }

    // Throws an exception if the heap is empty:
    public int FindMin()
    {
        if(this.currentSize <= 0)
        {
            throw new System.IndexOutOfRangeException();
        }

        return this.data[0];
    }

    public bool IsEmpty()
    {
        return this.currentSize <= 0;
    }

    public void Insert(int value)
    {
        this.currentSize++;
        this.data.Add(value);
        this.Swim(this.currentSize - 1);
    }

    private void Swim(int index)
    {
        if(index != 0)
        {
            int parent = (int)((index - 1) / 2.0f);

            if(data[parent] > data[index])
            {
                int tmp = data[parent];
                data[parent] = data[index];
                data[index] = tmp;

                // Swim up again:
                this.Swim(parent);
            }
        }
    }

    // Throws exception if heap is empty.
    public void DeleteMin()
    {
        if(this.currentSize <= 0)
        {
            throw new System.IndexOutOfRangeException();
        }

        int lastValue = this.data[this.currentSize - 1];
        this.data.RemoveAt(this.currentSize - 1);

        this.currentSize--;

        if(this.currentSize >= 1)
        {
            this.data[0] = lastValue;
            this.Sink(0);
        }
    }

    private void Sink(int index)
    {
        int leftChild = (index * 2) + 1;
        int rightChild = (index * 2) + 2;
        int minimum = leftChild;

        if(rightChild >= this.currentSize)
        {
            if(leftChild >= this.currentSize)
            {
                return;
            }
        }
        else if(data[leftChild] > data[rightChild])
        {
            minimum = rightChild;
        }

        // If the current root is greater than the min (not in heap order)
        if(data[index] > data[minimum])
        {
            int tmp = data[minimum];
            data[minimum] = data[index];
            data[index] = tmp;
            // Sink again while there's still elements out of place:
            this.Sink(minimum);
        }
    }
}