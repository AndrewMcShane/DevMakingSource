class QueueNode
{
    public value: any;
    public next: QueueNode;
}

class Queue
{
    private head: QueueNode;
    private tail: QueueNode;

    private size: number;

    public isEmpty(): boolean
    {
        return this.size <= 0;
    }

    public length():number
    {
        return this.size;
    }

    public peek(): any
    {
        if(this.isEmpty())
        {
            throw new Error("Queue is empty.");
        }
        return this.head.value;
    }

    public enqueue(value: any)
    {
        if(this.isEmpty())
        {
            this.tail = new QueueNode;
            this.tail.value = value;
            this.head = this.tail;
            this.size++;
        }
        else
        {
            this.tail.next = new QueueNode();
            this.tail.next.value = value;
            this.tail = this.tail.next;

            this.size++;
        }
    }

    public dequeue(): any
    {
        if(this.isEmpty())
        {
            throw new Error("Queue is empty.");
        }
        if(this.size == 1)
        {
            let tmp = this.head;
            this.head = null;
            this.tail = null;
            this.size = 0;
            return tmp.value;
        }
        else
        {
            let tmp = this.head;
            this.head = this.head.next;
            this.size--;
            return tmp.value;
        }
    }

    public poll(): any
    {
        if(this.isEmpty())
        {
            return null;
        }
        if(this.size == 1)
        {
            let tmp = this.head;
            this.head = null;
            this.tail = null;
            this.size = 0;
            return tmp.value;
        }
        else
        {
            let tmp = this.head;
            this.head = this.head.next;
            this.size--;
            return tmp.value;
        }
    }
}