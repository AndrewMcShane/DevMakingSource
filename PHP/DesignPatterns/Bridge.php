<?php

// Bridge pattern in PHP

// Implementor class: a Theme
interface Theme
{
    public function Background(): string;
    public function Foreground(): string;
    public function LineColor(): string;
    public function FontColor(): string;
    public function AltFontColor(): string;
}

// The abstraction: UI Object:
abstract class UIObject
{
    protected $theme;

    function __construct(Theme $theme)
    {
        $this->theme = $theme;
    }

    public abstract function Draw(): void;
}

// Theme 1: Light Mode
class LightMode implements Theme
{
    public function Background(): string
    { 
        return "#ffffff"; 
    }
    public function Foreground(): string
    {
        return "#aaaaaa";
    }
    public function LineColor(): string
    {
        return "#0c60cd";
    }
    public function FontColor(): string
    {
        return "#222222";
    }
    public function AltFontColor(): string
    {
        return "#000000";
    }
}

// Theme 2: Dark Mode
class DarkMode implements Theme
{
    public function Background(): string
    { 
        return "#111111"; 
    }
    public function Foreground(): string
    {
        return "#2d2d2d";
    }
    public function LineColor(): string
    {
        return "#0c60cd";
    }
    public function FontColor(): string
    {
        return "#dfdfdf";
    }
    public function AltFontColor(): string
    {
        return "#efefef";
    }
}

// UI Object 1: Button
class UIButton extends UIObject
{
    function __construct(Theme $theme)
    {
        parent::__construct($theme);
    }

    public function Draw(): void
    {
        echo "Drawing a button on the screen.\n";
        echo "\tText Color: " . $this->theme->FontColor() . "\n";
        echo "\tButton Color: " . $this->theme->Background() . "\n";
        echo "\tHighlight Text Color: " . $this->theme->AltFontColor() . "\n";
    }
}

class UIGraph extends UIObject
{
    function __construct(Theme $theme)
    {
        parent::__construct($theme);
    }

    public function Draw(): void
    {
        echo "Drawing a graph on the screen.\n";
        echo "\tMain Text Color: " . $this->theme->FontColor() . "\n";
        echo "\tLine Color: " . $this->theme->LineColor() . "\n";
        echo "\tAxis Text Color: " . $this->theme->AltFontColor() . "\n";
        echo "\tGraph Background Color: " . $this->theme->Foreground() . "\n"; 
    }
}

// Demo Code:
// Create the themes:
$light = new LightMode();
$dark = new DarkMode();

// Create the UI Objects:
$button = new UIButton($light);
$graph = new UIGraph($dark);

// Draw the objects:
$button->draw();
$graph->draw();

// Output:
/*
    Drawing a button on the screen.
        Text Color: #222222
        Button Color: #ffffff
        Highlight Text Color: #000000

    Drawing a graph on the screen.
        Main Text Color: #dfdfdf
        Line Color: #0c60cd
        Axis Text Color: #efefef
        Graph Background Color: #2d2d2d
*/