<?php

class Graph {

    private $adj;
    private $V;

    public function __construct($vertices) {
        $this->V = $vertices;
        $this->adj = array();
        for($i = 0; $i < $vertices; $i++) {
            array_push($this->adj, array());
        }
    }

    public function addEdge($from, $to) {
        array_push($this->adj[$from], $to);
        // If using an undirected Graph, uncomment this line:
        //array_push($this->adj[$to], $from);
    }

    public function size() {
        return $this->V;
    }

    public function getEdges($vertex) {
        if($vertex > $this->V) return null;
        return $this->adj[$vertex];
    }
}