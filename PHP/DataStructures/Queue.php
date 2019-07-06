<?php

class Queue {

    // Head, tail, and size of queue.
    private $head;
    private $tail;
    private $size;

    public function isEmpty() {
        return  $this->size <=0;
    }
    
    public function peek() {
        if($this->isEmpty()) {
            throw new Exception("Queue is empty!");
        }
        return $this->head->value;
    }

    public function enqueue($value) {
        if($this->isEmpty()) {
            $this->tail = new Node($value, null);
            $this->head = $this->tail;
            $this->size++;
        }
        else {
            $this->tail->next = new Node($value, null);
            $this->tail = $this->tail->next;
            $this->size++;
        }
    }

    public function dequeue() {
        if($this->isEmpty()) {
            throw new Exception("Queue is empty!");
        }
        if($this->size == 1) {
            $tmp = $this->head;
            $this->head = null;
            $this->tail = null;
            $this->size = 0;
            return $tmp->value;
        }
        else {
            $tmp = $this->head;
            $this->head = $this->head->next;
            $this->size--;
            return $tmp->value;
        }
    }

    public function poll() {
        if($this->isEmpty()) {
            return null;
        }
        if($this->size == 1) {
            $tmp = $this->head;
            $this->head = null;
            $this->tail = null;
            $this->size = 0;
            return $tmp->value;
        }
        else {
            $tmp = $this->head;
            $this->head = $this->head->next;
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

// Supporting Node class.
class Node {
    public $value;
    public $next;

    public function __construct($value, $next) {
        $this->value = $value;
        $this->next = $next;
    }
}
