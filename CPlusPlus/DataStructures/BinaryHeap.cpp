// Binary Min-Heap Demo in C++

#include <algorithm>
#include <iostream>
#include <vector>
#include <stdexcept>

class MinHeap
{
    public:
        MinHeap()
        : currentSize(0)
        {};

        int FindMin();
        bool IsEmpty();
        void Insert(int value);
        void DeleteMin();
        void PrintHeap();

    protected:
        void Swim(int index)
        {
            if(index != 0)
            {
                int parent = (int)((index - 1) / 2.0f);

                if(this->data[parent] > this->data[index])
                {
                    // Swap:
                    this->Swap(parent, index);
                    // Continue swimming:
                    this->Swim(parent);
                }
            }
        }

        void Sink(int index)
        {
            int leftChild = (index * 2) + 1;
            int rightChild = (index * 2)  + 2;
            int minimum = leftChild;

            if(rightChild >= this->currentSize)
            {
                if(leftChild >= currentSize)
                {
                    return;
                }
            }
            else if(this->data[leftChild] > this->data[rightChild])
            {
                minimum = rightChild;
            }


            // If the current root is larger than the min:
            if(this->data[index] > this->data[minimum])
            {
                this->Swap(minimum, index);
                this->Sink(minimum);
            }
        }

        void Swap(int indexA, int indexB)
        {
            int tmp = this->data[indexA];
            this->data[indexA] = this->data[indexB];
            this->data[indexB] = tmp;
        }

        std::vector<int> data;
        int currentSize;

};

int MinHeap::FindMin()
{
    if(this->currentSize <= 0)
    {
        throw new std::out_of_range("Heap is empty.");
    }

    return this->data[0];
}

bool MinHeap::IsEmpty()
{
    return this->currentSize <= 0;
}

void MinHeap::Insert(int value)
{
    this->currentSize++;
    this->data.push_back(value);
    this->Swim(this->currentSize - 1);
}

void MinHeap::DeleteMin()
{
    if(this->currentSize <= 0)
    {
        throw new std::out_of_range("Heap is empty.");
    }

    int lastValue = this->data[this->currentSize - 1];
    this->data.pop_back();
    this->currentSize--;

    if(this->currentSize >= 1)
    {
        this->data[0] = lastValue;
        this->Sink(0);
    }
}

void MinHeap::PrintHeap()
{
    // Print out the heap contents:
    for(int i = 0; i < data.size(); i++)
    {
        printf("%d ", data[i]);
    }
    printf("\n");
}


// Demo:
int main()
{
    MinHeap myHeap;

    myHeap.Insert(1);
    myHeap.Insert(30);
    myHeap.Insert(5);
    myHeap.Insert(201);
    myHeap.Insert(9);
    myHeap.Insert(29);
    myHeap.Insert(21);
    myHeap.Insert(35);
    myHeap.Insert(4);

    myHeap.PrintHeap();

    myHeap.DeleteMin();

    myHeap.PrintHeap();

}