using System;
using System.Collections;
using System.Collections.Generic;

public class Stack<T> : IEnumerable<T>
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

    private Node root;
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
        return root.value;
    }

    public void Push(T value)
    {
        if (IsEmpty())
        {
            root = new Node(value, null);
            Size++;
        }
        else
        {
            Node tmp = new Node(value, root);
            root = tmp;
            Size++;
        }
    }

    public T Pop()
    {
        if (IsEmpty())
        {
            throw new InvalidOperationException();
        }
        Node tmp = root;
        root = root.next;
        Size--;
        return tmp.value;
    }

    public T Poll()
    {
        if (IsEmpty())
        {
            return default(T);
        }
        Node tmp = root;
        root = root.next;
        Size--;
        return tmp.value;
    }

    override
    public string ToString()
    {
        string res = "{";
        Node tmp = root;
        while(tmp != null)
        {
            res += tmp.value.ToString();
            if(tmp.next != null)
            {
                res += ", ";
            }
            tmp = tmp.next;
        }
        res += "}";
        return res;
    }

    public IEnumerator<T> GetEnumerator()
    {
        Node node = this.root;
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