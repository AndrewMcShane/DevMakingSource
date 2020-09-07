// Iterator Pattern in C#
using System;

public class DoublyLinkedList<T>
{
    public class Node
    {
        public T value;
        public Node next;
        public Node prev;

        public Node(T value, Node next, Node prev)
        {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    protected Node head;
    protected Node tail;

    public int Size { get; private set; } = 0;

    public DoublyLinkedList()
    {
        head = null;
        tail = null;
        Size = 0;
    }

    public bool IsEmpty()
    {
        return Size <= 0;
    }

    /*
        For this example we'll skip some of the nitty gritty methods
        in order to focus on just the iterator.
    */

    // Insertion method:
    public void Push(T value)
    {
        if(this.IsEmpty())
        {
            Node tmp = new Node(value, null, null);
            this.head = tmp;
            this.tail = tmp;
            this.Size++;
            return;
        }
        else
        {
            Node tmp = this.tail;
            tmp.next = new Node(value, null, tmp);
            tail = tmp.next;
            Size++;
        }
    }

    // ====================== //
    // Iterators!             //
    // ====================== //

    // Forward Iterator:

    public DLinkIterator GetForwardIterator()
    {
        return new DLinkIterator(this.head);
    }

    // The Forward Iterator class:
    public class DLinkIterator
    {
        Node current;

        // Accepts a node starting point:
        public DLinkIterator(Node start)
        {
            current = start;
        }

        public bool HasNext()
        {
            return current != null;
        }

        public T Next()
        {
            if(!HasNext())
            {
                throw new System.IndexOutOfRangeException("No Nodes to iterate.");
            }
            T val = current.value;
            current = current.next;
            return val;
        } 
    }

    // Reverse Iterator:
    public DLinkReverseIterator GetReverseIterator()
    {
        return new DLinkReverseIterator(this.tail);
    }

    public class DLinkReverseIterator
    {
        Node current;

        public DLinkReverseIterator(Node start)
        {
            this.current = start;
        }

        // This might be a little confusing at first:
        // In the context of our iterator, the "Next" node is actually the previous!
        public bool HasNext()
        {
            return current != null;
        }

        public T Next()
        {
            if(!HasNext())
            {
                throw new System.IndexOutOfRangeException("No more nodes to iterate!");
            }

            T val = current.value;
            current = current.prev;
            return val;
            
        }
    }
}


/* 
   ==========================
     Demo of the Iterators:
   ========================== 
*/
public class Solution
{
    public static void Main(string[] args)
    {
        // Construct a DLink:

        DoublyLinkedList<string> myList = new DoublyLinkedList<string>();

        myList.Push("Hello");
        myList.Push("World");
        myList.Push("Goodbye");
        myList.Push("World");

        // Iterate the list forwards:
        DoublyLinkedList<string>.DLinkIterator forwardIter = 
        myList.GetForwardIterator();

        while(forwardIter.HasNext())
        {
            Console.WriteLine(forwardIter.Next());
        }

        /*
            Output:
                Hello
                World
                Goodbye
                World
        */

        Console.WriteLine("=======");


        DoublyLinkedList<string>.DLinkReverseIterator reverseIter = 
        myList.GetReverseIterator();

        while(reverseIter.HasNext())
        {
            Console.WriteLine(reverseIter.Next());
        }

        /*
            Output:
                World
                Goodbye
                World
                Hello
        */
    }
}