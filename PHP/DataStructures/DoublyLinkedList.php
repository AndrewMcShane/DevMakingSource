<?php

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

    // If the list contains the value, it will reutrn true, else false.
    public function contains($value) {
        if($this->isEmpty()) {
            return false;
        }
        $tmp = $this->head;
        while($tmp) {
            if($tmp->value == $value) {
                return true;
            }
            $tmp = $tmp->next;
        }
        return false;
    }

    // Retrieves a value from the list, otherwise throws out of bounds exception.
    public function get($index) {
        if($index > $this->size || $this->isEmpty()) {
            throw new OutOfBoundsException("Index out of range: " . $index);
        }
        if($index > $this->size / 2) {
            $index = ($this->size - 1) - $index;
            $tmp = $this->tail;
            while($index > 0) {
                $tmp = $tmp->prev;
                $index--;
            }
            return $tmp->value;
        }
        else {
            $tmp = $this->head;
            while($index > 0) {
                $tmp = $tmp->next;
                $index--;
            }
            return $tmp->value;
        }
    }
    // Retrieves the first element from the list.
    public function getFirst() {
        if($this->head) {
            return $this->head->value;
        }
        return null;
    }
    // Retrieves the last element from the list.
    public function getLast() {
        if($this->tail) {
            return $this->tail->value;
        }
        return null;
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
            $tmp = $this->tail;
            $tmp->prev = new Node($value, $tmp, null);
            $this->head = $tmp;
            $this->size++;
        }
    }

    // Inserts to the beginning of the list.
    public function addFirst($value) {
        if($this->isEmpty()) {
            $tmp = new Node($value, null, null);
            $this->head = $tmp;
            $this->tail = $tmp;
            $this->size++;
        }
        else {
            $tmp = $this->head;
            $tmp->prev = new Node($value, $tmp, null);
            $this->head = $tmp;
            $this->size++;
        }
    }

    // Inserts a value after a specified key is found.
    public function addAfter($key, $toAdd) {
        if($this->isEmpty()) {
            throw new Exception("Key ". $key . " does not exist!");
        }
        $tmp = $this->head;
        while($tmp) {
            if($tmp->value == $key) {
                $newNode = new Node($toAdd, $tmp->next, $tmp);
                $tmp->next = $newNode;
                if($newNode->next) $newNode->next->prev = $newNode;
                else $this->tail = $newNode;
                $this->size++;
                return;
            }
            $tmp = $tmp->next;
        }
        // If we make it here, throw exception.
        throw new Exception("Key ". $key . " does not exist!");
    }

    // Inserts a value before a specified key.
    public function addBefore($key,  $toAdd) {
        if($this->isEmpty()) {
            throw new Exception("Key ". $key . " does not exist!");
        }
        $tmp = $this->head;
        while($tmp) {
            if($tmp->value == $key) {
                $newNode = new Node($toAdd, $tmp, $tmp->prev);
                $tmp->prev = $newNode;
                if($newNode->prev) {
                    $newNode->prev->next = $newNode;
                } else {
                    $this->head = $tmp;
                }

                $this->size++;
                return;
            }
            $tmp = $tmp->next;
        }
        throw new Exception("Key ". $key . " does not exist!");
    }

    // Removes an element from the linked list.
    public function remove($value) {
        if($this->isEmpty()) {
            return;
        }
        $tmp = $this->head;
        while($tmp) {
            // If we found the node, remove it.
            if($tmp->value == $value) {
                // Cover the bases for the possible combinations of nodes.
                if($tmp->prev) $tmp->prev->next = $tmp->next;
                else $this->head = $tmp->next;
                if($tmp->next) $tmp->next->prev = $tmp->prev;
                else $this->tail = $tmp->prev;

                $this->size--;
                return;
            }
            // Iterate.
            $tmp = $tmp->next;
        }
    }

    // Remove the first element from the list, if any.
    public function removeFirst() {
        if($this->isEmpty()) {
            return;
        }
        if($this->size == 1) {
            $this->head = null;
            $this->tail = null;
            $this->size--;
        }
        else {
            $this->head = $this->head->next;
            $this->head->prev = null;
            $this->size--;
        }
    }

    // Removes the last element from the list.
    public function removeLast() { 
        if($this->isEmpty()) {
            return;
        }
        if($this->size == 1) {
            $this->head = null;
            $this->tail = null;
            $this->size--;
        }
        else {
            $this->tail = $this->tail->prev;
            $this->tail->next = null;
            $this->size--;
        }
    }
    
    // Gets the index of a particular element, or -1 if DNE.
    public function indexOf($value) {
        $index = 0;
        $tmp = $this->head;
        while($tmp) {
            if($this->tmp->value == $value) {
                return $index;
            }
            $tmp = $tmp->next;
            $index++;
        }
        return -1;
    }

    // Resets the linked list.
    public function clear() {
        $this->head = null;
        $this->tail = null;
        $this->size = 0;
    }

    // Creates a string format of the linked list.
    public function __toString() {
        $res = "{";
        $tmp = $this->head;
        while($tmp) {
            $res .= (string) $tmp->value;
            if($tmp->next) $res .= ", ";
            $tmp = $tmp->next;
        }
        $res .= "}";
        return $res;
    }
}

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