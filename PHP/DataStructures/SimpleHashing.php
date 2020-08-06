<?php

class SimpleHashing {

    private const BUCKETS_DEFAULT = 16;
    private $buckets;
    private $numBuckets;
    private $size;

    public function __construct($numBuckets = SimpleHashing::BUCKETS_DEFAULT) {
        $this->numBuckets = $numBuckets;
        $this->buckets = array();
        // Initialize an array of size num buckets.
        for($i = 0; $i < $numBuckets; $i++) {
            array_push($this->buckets, null);
        }
    }

    public function isEmpty() {
        return $this->size <= 0;
    }

    // PHP carries no default HashCode() method, so we'll use our own here.
    private function getHash($key) {
        $res = 0;
        $len = strlen($key);

        for($i = 0; $i < $len; $i++) {
            $res = $res * 31 + ord($key[$i]);
        }
        // Mod size, and mask to be positive.
        return ($res & 0x7FFFFFFF) % $this->numbBuckets;
    }

    public function put($key, $value) {
        $bucket = $this->getHash($key);
        $newNode = new HashNode($key, $value);
        if($this->buckets[$bucket]) {
            $this->buckets[$bucket] = $newNode;
            $this->size++;
        }
        else {
            $tmp = $this->buckets[$bucket];
            while($tmp->next) {
                if($tmp->key == $key) {
                    $tmp->value = $value;
                    return;
                }
                $tmp = $tmp->next;
                $this->size++;
            }
        }
    }

    public function get($key) {
        $bucket = $this->getHash($key);
        $tmp = $this->buckets[$bucket];
        while($tmp) {
            if($tmp->key == $key) {
                return $tmp->value;
            }
            $tmp = $tmp->next;
        }
        return null;
    }

    public function contains($key) {
        $bucket = $this->getHash($key);
        $tmp = $this->buckets[$bucket];
        while($tmp) {
            if($tmp->key == $key) {
                return true;
            }
            $tmp = $tmp->next;
        }
        return false;
    }

    public function remove($key) {
        $bucket = $this->getHash($key);
        if(!$this->buckets[$bucket]) {
            return;
        }
        $tmp = $this->buckets[$bucket];
        if($tmp->key == $key) {
            $this->buckets[$bucket] = $this->buckets[$bucket]->next;
            $this->size--;
            return;
        }
        while($tmp->next) {
            if($tmp->next->key == $key) {
                $tmp->next = $tmp->next->next;
                $this->size--;
                return;
            }
            $tmp = $tmp->next;
        }
    }

    public function clear() {
        $this->buckets = array();
        for($i = 0; $i < $this->numBuckets; $i++) {
            array_push($this->buckets, null);
        }
    }

    public function size() {
        return $this->size;
    }

    public function __toString() {
        $res  = "{ ";
        $count = 0;
        for($i = 0; $i < $this->numBuckets; $i++) {
            if($this->buckets[$i]) {
                $tmp = $this->buckets[$i];
                while($tmp) {
                    $res .= $tmp->key . ": " . $tmp->value;
                    $count++;
                    if($count < $this->size) {
                        $res .= ", ";
                    }
                    $tmp = $tmp->next;
                }
            }
        }
        $res .= " }";
        return $res;
    }

}

// Supporting hash node class.
class HashNode {
    public $key;
    public $value;
    public $next;

    public function __construct($key, $value) {
        $this->key = $key;
        $this->value = $value;
        $this->next = null;
    }
}