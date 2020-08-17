class LinkedListNode
{
    public next: LinkedListNode;
    public value: any;
}

class LinkedList
{
    private root: LinkedListNode;

    constructor()
    {
        this.root = null;
    }

    public isEmpty(): boolean
    {
        return this.root == null;
    }

    public contains(value: any): boolean
    {
        if(this.isEmpty())
        {
            return false;
        }

        let tmp = this.root;
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
        if(this.isEmpty())
        {
            throw new RangeError("List is empty.");
        }

        let tmp = this.root;
        for(let i = 0; i < index; i++)
        {
            tmp = tmp.next;

            if(tmp == null)
            {
                throw new RangeError("Index out of bounds.");
            }
        }
        return tmp.value;
    }

    public getFirst(): any
    {
        if(this.root != null)
        {
            return this.root.value;
        }
        else
        {
            return null;
        }
    }

    public getLast(): any
    {
        // Optimization opportunity!
        // Add a tail pointer to make this O(1), not O(n)!
        if(this.root == null)
        {
            return null;
        }
        else
        {
            let tmp = this.root;
            while(tmp.next != null)
            {
                tmp = tmp.next;
            }
            return tmp.value;
        }

    }

    public add(value: any)
    {
        if(this.isEmpty())
        {
            this.root = new LinkedListNode();
            this.root.value = value;
            return;
        }
        else
        {
            let tmp = this.root;
            while(tmp.next != null)
            {
                tmp = tmp.next;

            }
            tmp.next = new LinkedListNode();
            tmp.next.value = value;
            return;
        }
    }   

    public addFirst(value: any)
    {
        const firstNode = new LinkedListNode();
        firstNode.value = value;
        firstNode.next = this.root;
        this.root = firstNode;

    }

    public remove(value: any)
    {
        if(this.isEmpty())
        {
            return;
        }

        let tmp = this.root;
        // Edge-case: LL is only 1 node.
        if(tmp.next == null)
        {
            if(tmp.value === value)
            {
                this.root = null;
            }
            return;
        }
        // Find the node to remove:
        while(tmp.next != null)
        {
            if(tmp.next.value === value)
            {
                tmp.next = tmp.next.next;
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
        this.root = this.root.next;
    }

    public indexOf(value: any): number
    {
        let count = 0;
        if(this.isEmpty())
        {
            return -1;
        }

        let tmp = this.root;
        while(tmp != null)
        {
            if(tmp.value === value)
            {
                return count;
            }
            count++;
            tmp = tmp.next;
        }

        return -1;
    }

}