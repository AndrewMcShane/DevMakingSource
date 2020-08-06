<?php

class SelectionSort {
    public static function sort(&$arr) {
        $arrLen = count($arr);
        // Iterate over the entire array, except the last element (0...n-1).
        for($i = 0; $i < $arrLen; $i++) {
            $min = $i;
            // Iterate the remaining array (i...n).
            for($j = $i + 1; $j < $arrLen; $j++) {
                // If j is less than the min, update the min index.
                if($arr[$j] < $arr[$min]) {
                    $min = $j;
                }
            }
            // Do an in place swap.
            $tmp = $arr[$min];
            $arr[$min] = $arr[$i];
            $arr[$i] = $tmp;
        }
    }
}