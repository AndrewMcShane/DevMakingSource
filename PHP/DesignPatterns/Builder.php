<?php

class SimpleHtmlBuilder
{
    // The HTML content we'll display:
    private $bodyContent;

    function __construct()
    {
        $this->bodyContent = "";
    }

    public function AddH1($text)
    {
        $this->bodyContent .= "<h1>{$text}</h1>";
    }

    public function AddH2($text)
    {
        $this->bodyContent .= "<h2>{$text}</h2>";
    }

    public function AddParagraph($text)
    {
        $this->bodyContent .= "<p>{$text}</p>";
    }

    public function AddHorizontalRule()
    {
        $this->bodyContent .= "<hr>";
    }

    public function AddLineBreak()
    {
        $this->bodyContent .= "<br>";
    }

    // Creates the HTML document content based on HTML5 standards.
    public function BuildDocument()
    {
        return "<!DOCTYPE html>
        <html lang=\"en\">
        <head>
            <meta charset=\"UTF-8\">
            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">
            <title>Document</title>
        </head>
        <body>
            {$this->bodyContent}
        </body>
        </html>";
    }
}