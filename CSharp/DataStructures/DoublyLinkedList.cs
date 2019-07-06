using System;
using System.Collections;
using System.Collections.Generic;

public class DoublyLinkedList<T>: IEnumerable<T>
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

    private Node head;
    private Node tail;

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

    public bool Contains(T value)
    {
        if(IsEmpty())
        {
            return false;
        }
        Node tmp = head;
        while(tmp != null)
        {
            if(tmp.value.Equals(value))
            {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    public T Get(int index)
    {
        if(index > Size || IsEmpty())
        {
            throw new IndexOutOfRangeException();
        }
        if(index > Size / 2)
        {
            index = (Size - 1) - index;
            Node tmp = tail;
            while(index > 0)
            {
                tmp = tmp.prev;
                index--;
            }
            return tmp.value;
        }
        else
        {
            Node tmp = head;
            while(index > 0)
            {
                tmp = tmp.next;
                index--;
            }
            return tmp.value;
        }
    }

    public T GetFirst()
    {
        if (head != null)
        {
            return head.value;
        }
        return default(T);
    }

    public T GetLast()
    {
        if (tail != null)
        {
            return tail.value;

        }
        return default(T);
    }

    public void AddLast(T value)
    {
        if(IsEmpty())
        {
            Node tmp = new Node(value, null, null);
            head = tmp;
            tail = tmp;
            Size++;
            return;
        }
        else
        {
            Node tmp = tail;
            tmp.next = new Node(value, null, tmp);
            tail = tmp.next;
            Size++;

        }
        
    }

    public void AddFirst(T value)
    {
        if (IsEmpty())
        {
            Node tmp = new Node(value, null, null);
            head = tmp;
            tail = tmp;
            Size++;
            return;
        }
        else
        {
            Node tmp = head;
            tmp.prev = new Node(value, tmp, null);
            head = tmp;
            Size++;
        }
    }

    public void AddAfter(T key, T toAdd)
    {
        if (IsEmpty())
        {
            throw new KeyNotFoundException();
        }
        Node tmp = head;
        while(tmp != null)
        {
            if (tmp.value.Equals(key))
            {
                Node newNode = new Node(toAdd, tmp.next, tmp);
                tmp.next = newNode;

                if (newNode.next != null) newNode.next.prev = newNode;
                else tail = newNode;
                Size++;
                return;
            }
            tmp = tmp.next;
        }

        throw new KeyNotFoundException();
    }

    public void AddBefore(T key, T toAdd)
    {
        if (IsEmpty())
        {
            throw new KeyNotFoundException();
        }
        Node tmp = head;
        while(tmp != null)
        {
            if (tmp.value.Equals(key))
            {
                Node newNode = new Node(toAdd, tmp, tmp.prev);
                tmp.prev = newNode;

                if (newNode.prev != null) newNode.prev.next = newNode;
                else head = tmp;
                Size++;
                return;
            }
            tmp = tmp.next;
        }

        throw new KeyNotFoundException();
    }

    public void Remove(T value)
    {
        if (IsEmpty())
        {
            return;
        }
        Node tmp = head;
        while(tmp != null)
        {
            if (tmp.value.Equals(value))
            {
                if (tmp.prev != null) tmp.prev.next = tmp.next;
                else head = tmp.next;
                if (tmp.next != null) tmp.next.prev = tmp.prev;
                else tail = tmp.prev;
                Size--;
                return;
            }
            tmp = tmp.next;
        }
        return;
    }

    public void RemoveFirst()
    {
        if (IsEmpty())
        {
            return;
        }
        if(Size == 1)
        {
            head = null;
            tail = null;
            Size--;
            return;
        }
        head = head.next;
        head.prev = null;
        Size--;
    }

    public void RemoveLast()
    {
        if (IsEmpty())
        {
            return;
        }
        if (Size == 1)
        {
            head = null;
            tail = null;
            Size--;
            return;
        }
        tail = tail.prev;
        tail.next = null;
        Size--;
    }

    public int IndexOF(T value)
    {
        int index = 0;
        Node tmp = head;
        while(tmp != null)
        {
            if (tmp.value.Equals(value))
            {
                return index;
            }
            tmp = tmp.next;
            index++;
        }
        return -1;
    }

    public void Clear()
    {
        this.Size = 0;
        this.head = null;
        this.tail = null;
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

    /* IEnumerator support for the linked list */
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