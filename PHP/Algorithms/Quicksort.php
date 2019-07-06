<?php

class Quicksort {
    public static function sort(&$arr) {
        Quicksort::_quicksort($arr, 0, count($arr) - 1);
    }

    public static function _quicksort(&$arr, $low, $high) {
        if($low < $high) {
            $p = Quicksort::partition($arr, $low, $high);
            
            Quicksort::_quicksort($arr, $low, $p - 1);
            Quicksort::_quicksort($arr, $p + 1, $high);
        }
    }

    public static function partition(&$arr, $low, $high) {
        // Taking the high pivot method. 
        $pivot = $arr[$high];
        $i = $low;
        for($j = $low; $j < $high; $j++) {
            if($arr[$j] < $pivot) {
                Quicksort::swap($arr, $i, $j);
                $i++;
            }
        }
        Quicksort::swap($arr, $i, $high);
        return $i;
    }

    public static function swap(&$arr, $a, $b) {
        $tmp = $arr[$a];
        $arr[$a] = $arr[$b];
        $arr[$b] = $tmp;
    }
}

$arr = array(5,2,3,7,4);
Quicksort::sort($arr);
echo json_encode($arr);