<?php

class Stack {
    // The top of the stack and the size.
    private $root;
    private $size;

    public function isEmpty() {
        return $this->size <= 0;
    }
    
    public function peek() {
        if($this->isEmpty()) {
            throw new Exception("Stack is empty!");
        }
        return $this->root->value;
    }

    public function push($value) {
        if($this->isEmpty()) {
            $this->root = new Node($value, null);
            $this->size++;
        }
        else {
            $tmp = new Node($value, $this->root);
            $this->root = $tmp;
            $this->size++;
        }
    }

    public function pop() {
        if($this->isEmpty()) {
            throw new Exception("Stack is empty!");
        }
        else {
            $tmp = $this->root;
            $this->root = $this->root->next;
            $this->size--;
            return $tmp->value;
        }
    }

    public function poll() {
        if($this->isEmpty()) {
            return null;
        }
        else {
            $tmp = $this->root;
            $this->root = $this->root->next;
            $this->size--;
            return $tmp->value;
        }
    }

    public function size() {
        return $this->size;
    }

    public function __toString() {
        $res = "{";
        $tmp = $this->head;
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

// Supporting Node class for the stack
class Node {
    public $value;
    public $next;

    public function __constructor($value, $next) {
        $this->value = $value;
        $this->next = $next;
    }
}