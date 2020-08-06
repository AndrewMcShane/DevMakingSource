//Heap Demo in Java

import java.util.ArrayList;

public class MinHeap
{	
	 private ArrayList<Integer> data;
	 private int currentSize;
	
	 public MinHeqp()
	 {
	     this.data = new ArrayList<Integer>();
	     this.currentSize = 0;
	 }
	
	 // Throws exception if the heap is empty.
	 public int FindMin()
	 {
	     if(currentSize <= 0)
	     {
	         throw new ArrayIndexOutOfBoundsException();
	     }
	
	     return this.data.get(0);
	 }
	
	 public boolean IsEmpty()
	 {
	     return this.currentSize <= 0;
	 }
	
	 public void Insert(int value)
	 {
	     this.currentSize++;
	     this.data.add(value);
	     this.Swim(this.currentSize - 1);
	 }
	 
	 private void Swim(int index)
	 {
		 if(index != 0)
		 {
			 int parent = (int)((index - 1) / 2.0f);
			 
			 if(data.get(parent) > data.get(index)) 
			 {
				 // Swap:
				 int temp = data.get(parent);
				 data.set(parent,  data.get(index));
				 data.set(index,  temp);
				 
				 // Swim up further:
				 this.Swim(parent);
			 }
		 }
	 }
	 
	 //Throws and exception if the heap is empty.
	 public void DeleteMin()
	 {
		 if(this.IsEmpty())
		 {
			 throw new ArrayIndexOutOfBoundsException();
		 }
		 
		 int lastValue = this.data.remove(this.currentSize - 1);
		 this.currentSize--;
		 
		 if(this.currentSize >= 1)
		 {
			 this.data.add(0, lastValue);
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
		 else if(data.get(leftChild) > data.get(rightChild))
		 {
			 minimum = rightChild;
		 }
		 
		 
		 if(data.get(index) > data.get(minimum))
		 {
			 int temp = data.get(minimum);
			 data.set(minimum, data.get(index));
			 data.set(index, temp);
			 
			 this.Sink(minimum);
		 }
	 }
}