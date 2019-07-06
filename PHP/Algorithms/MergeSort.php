<?php

class MergeSort {
    
    public static function sort(&$arr) {
        $arrClone = $arr;
        MergeSort::_sort($arr, 0, count($arr) - 1, $arrClone);
    }

    public static function _sort(&$arr, $low, $high, $arrClone) {
        if($low < $high) {
            $mid = (int) (($low + $high) / 2);

            MergeSort::_sort($arr, $low, $mid, $arrClone);
            MergeSort::_sort($arr, $mid + 1, $high, $arrClone);

            MergeSort::merge($arr, $low, $mid, $high, $arrClone);
        }
    }

    public static function merge(&$arr, $low, $mid, $high, $arrClone) {
        $i = $low;
        $j = $mid + 1;

        for($k = $low; $k <= $high; $k++) {
            $arrClone[$k] = $arr[$k];
        }
        for($k = $low; $k <= $high; $k++) {
            if($i > $mid) {
                $arr[$k] = $arrClone[$j];
                $j++;
            }
            elseif($j > $high) {
                $arr[$k] = $arrClone[$i];
                $i++;
            }
            elseif($arrClone[$i] > $arrClone[$j]) {
                $arr[$k] = $arrClone[$j];
                $j++;
            }
            else {
                $arr[$k] = $arrClone[$i];
                $i++;
            }
        }
    }
}