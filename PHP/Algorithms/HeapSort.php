<?php

class HeapSort {

    public static function sort(&$arr) {
        $size = count($arr);
        for($i = (int)($size / 2) - 1; $i >=0; $i--) {
            HeapSort::heapify($arr, $size, $i);
        }

        $j = $size - 1;
        while($j >= 1) {
            // Swap the first with arr[i].
            HeapSort::swap($arr, 0, $i);
            // Heapify the array again.
            HeapSort::heapify($arr, $i, 0);
            // Decrement i.
            $j--;
        }
    }

    public static function heapify(&$arr, $size, $i) {
        
        $largest = $i;
        $leftLeaf = $i *2 + 1;
        $rightLeaf = $i * 2 + 2;

        // If the left child is larger than the largest.
        if($leftLeaf < $size && $arr[$leftLeaf] > $arr[$largest]) {
            $largest = $leftLeaf;
        }
        // If the right child is larger than the largest.
        if($rightLeaf < $size && $arr[$rightLeaf] > $arr[$largest]) {
            $largest = $rightLeaf;
        }

        if($largest != $i) {
            // swap i and the largest.
            HeapSort::swap($arr, $i, $largest);
            // Heapify the subtree.
            HeapSort::heapify($arr, $size, $largest);
        }
    }

    public static function swap(&$arr, $a, $b) {
        $tmp = $arr[$a];
        $arr[$a] = $arr[$b];
        $arr[$b] = $tmp;
    }
}