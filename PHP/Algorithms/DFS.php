<?php

class DFS {

    public static function dfsIterative($G, $startVert) {
        $visited = array();
        for($i = 0; $i < $G->size; $i++) {
            array_push($visited, false);
        }
        $s = SplStack();
        
        $s->push($startVert);

        while(!$s->isEmpty()) {
            $v = $s->pop();
            foreach($G->adj[$v] as $adjV) {
                if(!$visited[$adjV]) {
                    $visited[$adjV] = true;
                    $s->push($adjV);
                }
            }
        }
    }

    public static function dfsRecursive($G, $startVert) {
        $visited = array();
        for($i = 0; $i < $G->size; $i++) {
            array_push($visited, false);
        }
        DFS::dfsAux($G, $startVert, $visited);
    }

    public static function dfsAux($G, $v, $visited) {
        $visited[$v] = true;
        foreach($G->adj[$v] as $adjV) {
            if(!$visited[$adjV]) {
                DFS::dfsAux($G, $adjV, $visited);
            }
        }
    }
}

// Example class of what a graph might look like.

class Graph {
    public $adj;
    public $size;
}