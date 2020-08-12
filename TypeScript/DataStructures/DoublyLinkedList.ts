class DoublyLinkedListNode
{
    public value: any;
    public next: DoublyLinkedListNode;
    public prev: DoublyLinkedListNode;
}

class DoublyLinkedList
{
    private head: DoublyLinkedListNode;
    private tail: DoublyLinkedListNode;

    private size: number;

    constructor()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public length(): number
    {
        return this.size;
    }

    public isEmpty(): boolean
    {
        return this.size <= 0;
    }

    public contains(value: any): boolean
    {
        if(this.isEmpty())
        {
            return false;
        }
        let tmp = this.head;
        while(tmp != null)
        {
            if(tmp.value === value)
            {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    public get(index: number): any
    {
        if(index > this.size || this.isEmpty())
        {
            throw new RangeError("Index out of range.");
        }

        if(index > this.size / 2)
        {
            let i = (this.size - 1) - index;
            let tmp = this.tail;
            while(i > 0)
            {
                tmp = tmp.prev;
                i--;
            }
            return tmp.value;
        }
        else
        {
            let tmp = this.head;
            for(let i = 0; i < index; i++)
            {
                tmp = tmp.next;
            }
            return tmp.value;
        }
    }

    public getFirst(): any
    {
        if(this.head != null)
        {
            return this.head.value;
        }
        return null;
    }

    public getLast(): any
    {
        if(this.tail != null)
        {
            return this.tail.value;
        }
        return null;
    }

    public addLast(value: any)
    {
        if(this.isEmpty())
        {
            let tmp = new DoublyLinkedListNode();
            this.head = tmp;
            this.tail = tmp;
            this.size++;
            return;
        }
        else
        {
            let tmp = new DoublyLinkedListNode();
            tmp.next = null;
            tmp.prev = this.tail;
            tmp.value = value;

            this.tail.next = tmp;

            this.tail = tmp;
            this.size++;
        }
    }

    public addFirst(value: any)
    {
        if(this.isEmpty())
        {
            let tmp = new DoublyLinkedListNode();
            tmp.value = value;
            this.head = tmp;
            this.tail = tmp;
            this.size++;
        }
        else
        {
            let tmp = new DoublyLinkedListNode();
            tmp.next = this.head;
            tmp.prev = null;
            tmp.value = value;

            this.head.prev = tmp;

            this.head = tmp;
            this.size++;
        }
    }

    public remove(value: any)
    {
        if(this.isEmpty())
        {
            return;
        }
        let tmp = this.head;
        while(tmp != null)
        {
            if(tmp.value === value)
            {
                if(tmp.prev != null)
                {
                    tmp.prev.next = tmp.next;
                }
                else
                {
                    this.head = tmp.next;
                }
                if(tmp.next != null)
                {
                    tmp.next.prev = tmp.prev;
                }
                else
                {
                    this.tail = tmp.prev;
                }
                this.size--;
                return;
            }

            tmp = tmp.next;
        }
    }

    public removeFirst()
    {
        if(this.isEmpty())
        {
            return;
        }
        if(this.size == 1)
        {
            this.head = null;
            this.tail = null;
            this.size--;

        }
        else
        {
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
        }
    }

    public removeLast()
    {
        if(this.isEmpty())
        {
            return;
        }
        if(this.size == 1)
        {
            this.head = null;
            this.tail = null;
            this.size--;
            
        }
        else
        {
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
        }
    }

    public indexOf(value: any)
    {
        if(this.isEmpty())
        {
            return  -1;
        }
        let index = 0;
        let tmp = this.head;
        while(tmp != null)
        {
            if(tmp.value === value)
            {
                return index;
            }
            tmp = tmp.next;
            index++;

        }
        return -1;
    }
}