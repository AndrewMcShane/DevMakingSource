<?php

class BubbleSort {

    public static function sort(&$arr) {
        $size = count($arr);

        for($i = 0; $i < $size - 1; $i++) {
            // For each element, loop over and find inversions.
            for($j = 0; $j < $size - $i - 1; $j++) {
                // If i and j are inverted, swap them
                if($arr[$j] > $arr[$j + 1]) {
                    BubbleSort::swap($arr, $j, $j + 1);
                }
            }
        }
    }

    public static function sortFaster(&$arr) {

        $size = count($arr);
        
        for($i = 0; $i < $size - 1; $i++) {
            // Set swapped flag to false.
            $swapped = false;
            // For each element, loop over and find inversions.
            for($j = 0; $j < $size - $i - 1; $j++) {
                // If i and j are inverted, swap them
                if($arr[$j] > $arr[$j + 1]) {
                    BubbleSort::swap($arr, $j, $j + 1);
                    // set the swap flag to true.
                    $swapped = true;
                }
            }
            // Check for any swaps
            if(!$swapped) {
                // If not, we are done.
                break;
            }
        }
    }

    public static function swap(&$arr, $a, $b) {
        $tmp = $arr[$a];
        $arr[$a] = $arr[$b];
        $arr[$b] = $tmp;
    }
}