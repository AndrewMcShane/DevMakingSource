<?php

class ShellSort {
    public static function sort(&$arr) {
        $arrLen = count($arr);
        $gap = 0;
        while($gap < (int)($arrLen / 3)) {
            $gap = $gap * 3 + 1;
        }

        while ($gap > 0) {
            for($i = $gap; $i < $arrLen; $i++) {
                $j = $i;
                $tmp = $arr[$i];
                while($j >= $gap && $arr[$j - $gap] > $tmp) {
                    $arr[$j] = $arr[$j - $gap];
                    $j = $j - $gap;
                }
                $arr[$j] = $tmp;
            }
            $gap = (int)(($gap - 1) / 3);
        }
        
    }
}
