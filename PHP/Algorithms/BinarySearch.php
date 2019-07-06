<?php

class BinarySearch {
    // Assumes 'arr' is in sorted order

    // Iterative implementation of Binary Search
    public static function binarySearchIterative($arr, $num) {
        $low = 0;
        $high = count($arr) - 1;

        while($low <= $high) {
            $mid = (int)(($low + $high) / 2);

            if($num < $arr[$mid]) {
                $high = $mid - 1;
            }
            elseif ($num > $arr[$mid]) {
                $low = $mid + 1;
            }
            else {
                return $mid;
            }
        }
        return -1;
    }

    // Recursive implementation of binary search
    public static function binarySearchRecursive($arr, $num) {
        return BinarySearch::binarySearchRecursiveAux($arr, $num, 0, count($arr) - 1);
    }

    public static function binarySearchRecursiveAux($arr, $num, $low, $high) {
        $mid = (int)(($low + $high) / 2);

        if($low > $high) return -1;

        if($num < $arr[$mid]) {
            return BinarySearch::binarySearchRecursiveAux($arr, $num, $low, $mid - 1);
        }
        elseif($num > $arr[$mid]) {
            return BinarySearch::binarySearchRecursiveAux($arr, $num, $mid + 1, $high);
        }
        else return $mid;
    }
}