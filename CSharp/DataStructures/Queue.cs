using System;
using System.Collections;
using System.Collections.Generic;

public class Queue<T> : IEnumerable<T>
{
    public class Node
    {
        public T value;
        public Node next;

        public Node(T value, Node next)
        {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    public int Size { get; private set; }

    public bool IsEmpty()
    {
        return Size <= 0;
    }

    public T Peek()
    {
        if (IsEmpty())
        {
            throw new InvalidOperationException();
        }
        return head.value;
    }

    public void Enqueue(T value)
    {
        if (IsEmpty())
        {
            tail = new Node(value, null);
            head = tail;
            Size++;
            return;
        }
        tail.next = new Node(value, null);
        tail = tail.next;
        Size++;
        return;
    }

    public T Dequeue()
    {
        if (IsEmpty())
        {
            throw new InvalidOperationException();
        }
        if(Size == 1)
        {
            Node tmp = head;
            head = null;
            tail = null;
            Size = 0;
            return tmp.value;
        }
        else
        {
            Node tmp = head;
            head = head.next;
            Size--;
            return tmp.value;
        }
    }

    public T Poll()
    {
        if (IsEmpty())
        {
            return default(T);
        }
        if(Size == 1)
        {
            Node tmp = head;
            head = null;
            tail = null;
            Size = 0;
            return tmp.value;
        }
        else
        {
            Node tmp = head;
            head = head.next;
            Size--;
            return tmp.value;
        }
    }

    override
    public string ToString()
    {
        string res = "{";
        Node tmp = head;
        while(tmp != null)
        {
            res += tmp.value.ToString();
            if (tmp.next != null) res += ", ";
            tmp = tmp.next;
        }
        res += "}";
        return res;
    }

    public IEnumerator<T> GetEnumerator()
    {
        Node node = this.head;
        while (node != null)
        {
            yield return node.value;
            node = node.next;
        }
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return this.GetEnumerator();
    }
}