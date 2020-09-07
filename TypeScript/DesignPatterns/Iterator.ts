module IteratorPattern
{
    class DLinkNode
    {
        public value: any;
        public next: DLinkNode;
        public prev: DLinkNode;
    }
    
    // Simplified Doubly Linked List:
    export class DoublyLinkedList
    {
        private head: DLinkNode;
        private tail: DLinkNode;

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

        public addLast(value: any)
        {
            if(this.isEmpty())
            {
                let tmp = new DLinkNode();
                this.head = tmp;
                this.tail = tmp;
                this.size++;
                return;
            }
            else
            {
                let tmp = new DLinkNode();
                tmp.next = null;
                tmp.prev = this.tail;
                tmp.value = value;

                this.tail.next = tmp;

                this.tail = tmp;
                this.size++;
            }
        }

        public getIterator(): DLinkIterator
        {
            return new DLinkIterator(this.head);
        }

        public getReverseIterator(): DLinkReverseIterator
        {
            return new DLinkReverseIterator(this.tail);
        }
    }

    // Iterator Classes:
    export class DLinkIterator
    {
        protected current: DLinkNode;

        constructor(start: DLinkNode)
        {
            this.current = start;
        }

        public hasNext(): boolean
        {
            return this.current != null;
        }

        public next(): any
        {
            if(!this.hasNext())
            {
                throw new RangeError("No more nodes to iterate");
            }

            const val = this.current.value;
            this.current = this.current.next;

            return val;
        }
    }

    export class DLinkReverseIterator
    {
        protected current: DLinkNode;

        constructor(start: DLinkNode)
        {
            this.current = start;
        }

        public hasNext(): boolean
        {
            return this.current != null;
        }

        public next(): any
        {
            if(!this.hasNext())
            {
                throw new RangeError("No more nodes to iterate");
            }

            const val = this.current.value;
            this.current = this.current.prev;

            return val;
        }
    }

    export class Solution
    {
        public static execute()
        {
            let myList = new DoublyLinkedList();
            myList.addLast("Hello");
            myList.addLast("World");
            myList.addLast("Goodbye");
            myList.addLast("World");

            // Iterate forwards:
            console.log("Iterating forwards:");
            const iter = myList.getIterator();
            while(iter.hasNext())
            {
                console.log(iter.next());
            }

            /*
                Output:
                    Hello
                    World
                    Goodbye
                    World
            */

            console.log("==========");

            
            // Iterate backwards:
            console.log("Iterating backwards:");
            const rIter = myList.getReverseIterator();
            while(rIter.hasNext())
            {
                console.log(rIter.next());
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
}

// Run the demo:
IteratorPattern.Solution.execute();
