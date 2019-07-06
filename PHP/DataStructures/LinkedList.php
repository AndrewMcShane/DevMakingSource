<?php

class LinkedList {

    private $root;

    public function __construct() {
        $this->root = null;
    }

    public function isEmpty() {
        return $this->root == null;
    }

    // Does the list contain the value?
    public function contains($value) {
        if($this->isEmpty()) {
            return false;
        }
        $tmp = $this->root;
        while($tmp) {
            if($tmp->value == $value) {
                return true;
            }
            $tmp = $tmp->next;
        }
        return false;
    }

    // Get a value at the specified index.
    public function get($index) {
        if($this->isEmpty()) {
            throw new OutOfBoundsException("Index out of range: " . $index);
        }
        $tmp = $this->root;
        for($i = 0; $i < $index; $i++) {
            $tmp = $tmp->next;
            if(!$tmp) {
                throw new OutOfBoundsException("Index out of range: " . $index);
            }
        }
        return $tmp->value;
    }

    // Retrieve the first element in the list.
    public function getFirst() {
        if($this->root) {
            return $this->root->value;
        }
        return null;
    }

    // Get the last element in the list.
    public function getLast() {
        if($this->isEmpty()) {
            return null;
        }
        else {
            $tmp = $this->root;
            while($tmp->next) {
                $tmp = $tmp->next;
            }
            return $tmp->value;
        }
    }

    // Insert into the end of the list.
    public function add($value) {
        if($this->isEmpty()) {
            $this->root = new Node($value, null);
        }
        else {
            while($tmp->next) {
                $tmp = $tmp->next;
            }
            $tmp->next = new Node($value, null);
        }
    }

    // Insert to the beginning of the list.
    public function addFirst($value) {
        $firstNode = new Node($value, $this->root);
        $this->root = $firstNode;
    }

    // Insert after a specified value.
    public function addAfter($key, $toAdd) {
        if($this->isEmpty()) {
            throw new Exception("Specified key " . $key . " does not exist.");
        }
        $tmp = $this->root;
        while($tmp->next) {
            if($tmp->value == $key) {
                $newNode = new Node($toAdd, $tmp->next);
                $tmp->next = $newNode;
                return;
            }
            $tmp->tmp->next;
        }

        throw new Exception("Specified key " . $key . " does not exist.");
    }

    // Insert before a given value.
    public function addBefore($key, $toAdd) {
        if($this->isEmpty()) {
            throw new Exception("Specified key " . $key . " does not exist.");
        }
        $tmpPrev = $this->root;
        $tmp = $this->root->next;
        if($tmpPrev->value == $key) {
            $newNode = new Node($value, $this->root);
            $this->root = $firstNode;
        }
        while($tmp) {
            if($tmp->value == $key) {
                $newNode = new Node($toAdd, $tmp);
                $tmpPrev->next = $newNode;
            }
            $tmpPrev = $tmp;
            $tmp = $tmp->next;
        }

        throw new Exception("Specified key " . $key . " does not exist.");
    }

    // Removes a value from the list.
    public function remove($value) {
        if($this->isEmpty()) {
            return;
        }
        $tmp = $this->root;
        if(!$tmp->next && $tmp->value == $value) {
            $this->root = null;
            return;
        }
        while($tmp->next) {
            if($tmp->next->value == $value) {
                $tmp->next = $tmp->next->next;
                return;
            }
            $tmp = $tmp->next;
        }
    }

    // Removes the first node from the list if one exists.
    public function removeFirst() {
        if($this->isEmpty()) {
            return;
        }
        $this->root = $this->root->next;
    }

    // Removes the last node from the list.
    public function removeLast() {
        if($this->isEmpty()) {
            return;
        }
        $tmp = $this->root;
        if(!$tmp->next) {
            $this->root = null;
            return;
        }

        while($tmp->next->next) {
            $tmp = $tmp->next;
        }
        $tmp->next = null;
    }

    // Gets the length of the list in O(n) time. 
    // Keeping a size variable for each insert/delete would make this O(1) time.
    public function length() {
        $count = 0;
        $tmp = $this->root;
        while($tmp) {
            $count++;
            $tmp = $tmp->next;
        }
        return $count;
    }

    // Gets the index of a value, returns -1 if DNE.
    public function indexOf($value) {
        $count = 0;
        if($this->isEmpty()) {
            return -1;
        }
        $tmp = $this->root;
        while($tmp) {
            if($tmp->value == $value) {
                return $count;
            }
            $count++;
            $tmp = $tmp->next;
        }
        return -1;
    }

    // Resets a list.
    public function clear() {
        $this->root = null;
    }

    // Converts the list to a string format.
    public function __toString() {
        $res = "{";
        $tmp = $this->root;
        while($tmp) {
            $res .= (string) $tmp->value;
            if($tmp->next) {
                $res .= ", ";
            }
            $tmp = $tmp->next;
        }
        return $res;
    }
}

// Supporting Node class for the linked list
class Node {
    
    public $value;
    public $next;

    public function __construct($value, $next) {
        $this->value = $value;
        $this->next = $next;
    }
}