class StackNode
{
    public value: any;
    public next: StackNode;
}

class Stack
{
    private root: StackNode;
    private size: number;

    public isEmpty(): boolean
    {
        return this.size <= 0;
    }

    public length(): number
    {
        return this.size;
    }

    public peek(): any
    {
        if(this.isEmpty())
        {
            throw new Error("Stack is empty.");
        }
        return this.root.value;
    }

    public push(value: any)
    {
        if(this.isEmpty())
        {
            this.root = new StackNode();
            this.root.value = value;
        }
        else
        {
            let tmp = new StackNode();
            tmp.value = value;
            tmp.next = this.root;
            this.root = tmp;
        }
        this.size++;
    }

    public pop(): any
    {
        if(this.isEmpty())
        {
            throw new Error("Stack is empty.");
        }
        let tmp = this.root;
        this.root = this.root.next;
        this.size--;
        return tmp.value;
    }

    public poll(): any
    {
        if(this.isEmpty())
        {
            return null;
        }
        let tmp = this.root;
        this.root = this.root.next;
        this.size--;
        return tmp.value;
    }
}