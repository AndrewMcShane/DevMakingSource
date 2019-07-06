<?php

class InsertionSort {
    public static function sort(&$arr) {
        // iterate over the array
        for($i = 0; $i < count($arr); $i++) {
            $tmp = $arr[$i];
            $j = $i - 1;
            while($j >= 0 && $arr[$j] > $tmp) {
                $arr[$j + 1] = $arr[$j];
                $j = $j - 1;
            }
            $arr[$j + 1] = $tmp;
        }
    }
}