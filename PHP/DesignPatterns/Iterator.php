<?php


// Node class used for the Doubly Linked List.
class Node {
    
    public $value;
    public $next;
    public $prev;

    public function __construct($value, $next, $previous) { 
        $this->value = $value;
        $this->next = $next;
        $this->prev = $previous;
    }
}

// Simplified DLink:
class DoublyLinkedList {

    // Member variables for the access points of the list.
    private $head;
    private $tail;
    private $size;

    // Constructor
    public function __construct() {
        $this->head = null;
        $this->tail = null;
        $this->size = null;
    }

    public function isEmpty() {
        return $this->size <= 0;
    }

    public function size() {
        return $this->size;
    }

    // Inserts to the end of the list.
    public function addLast($value) {
        if($this->isEmpty()) {
            // If empty, insert and setup.
            $tmp = new Node($value, null, null);
            $this->head = $tmp;
            $this->tail = $tmp;
            $this->size++;
        }
        else 
        {
            $this->tail->next = new Node($value, null, $this->tail);
            $this->tail = $this->tail->next;
            $this->size++;
        }
    }

    public function getIterator(): DLinkIterator
    {
        return new DLinkIterator($this->head);
    }

    public  function getReverseIterator(): DLinkReverseIterator
    {
        return new DLinkReverseIterator($this->tail);
    }
}

class DLinkIterator
{
    protected $current;

    public function __construct($start)
    {
        $this->current = $start;
    }
    
    public function HasNext(): bool
    {
        return $this->current !== null;
    }

    public function Next()
    {
        if(!$this->HasNext())
        {
            throw new Exception("No more nodes to iterate!");
        }

        $val = $this->current->value;
        $this->current = $this->current->next;

        return $val;
    }
}

// Reverse iterator:
class DLinkReverseIterator
{
    protected $current;

    public function __construct($start)
    {
        $this->current = $start;
    }
    
    public function HasNext(): bool
    {
        return $this->current !== null;
    }

    public function Next()
    {
        if(!$this->HasNext())
        {
            throw new Exception("No more nodes to iterate!");
        }

        $val = $this->current->value;
        $this->current = $this->current->prev;

        return $val;
    }
}


// Demo:
$myList = new DoublyLinkedList();
$myList->addLast('Hello');
$myList->addLast('World');
$myList->addLast('Goodbye');
$myList->addLast('World');

echo "Forward iterator:\n";
$iter = $myList->getIterator();

while($iter->HasNext())
{
    echo $iter->Next() . "\n";
}

/*
    Output:
        Hello
        World
        Goodbye
        World
*/

echo "Reverse iterator:\n";
$revIter = $myList->getReverseIterator();

while($revIter->HasNext())
{
    echo $revIter->Next() . "\n";
}

/*
    Output:
        World
        Goodbye
        World
        Hello
*/
