<?php
/* Command pattern in PHP */

class WebBrowser
{
    private $currentUrl;
    private $bookmarks;

    function __construct()
    {
        $this->currentUrl = "";
        $this->bookmarks = array();
    }

    public function Navigate($url)
    {
        $this->currentUrl = $url;
    }

    public function GetCurrentUrl()
    {
        return $this->currentUrl;
    }

    public function BookmarkCurrentPage()
    {
        if(strlen($this->currentUrl) > 0 
        && !in_array($this->currentUrl, $this->bookmarks))
        {
            $this->bookmarks[] = $this->currentUrl;
        }
    }

    public function RemoveBookmark($url)
    {
        if (($key = array_search($url, $this->bookmarks)) !== false) {
            unset($this->bookmarks[$key]);
        }
    }

    public function PrintBookmarks()
    {
        echo "Bookmarks:\n";
        foreach($this->bookmarks as $bookmark)
        {
            echo "\t" . $bookmark . "\n";
        }
    }
}

interface IUndoableCommand
{
    public function Execute();
    public function Undo();
    public function Redo();
}

class AddBookmarkCommand implements IUndoableCommand
{
    private $webBrowser;
    private $url;

    function __construct($webBrowser)
    {
        $this->webBrowser = $webBrowser;
        $this->url = "";
    }

    public function Execute()
    {
        $this->url = $this->webBrowser->GetCurrentUrl();
        $this->webBrowser->BookmarkCurrentPage();
    }

    public function Undo()
    {
        $this->webBrowser->RemoveBookmark($this->url);
    }

    public function Redo()
    {
        $this->Execute();
    }

}

class Bookmarker
{
    private $webBrowser;
    private $undoStack;
    private $redoStack;

    function __construct($webBrowser)
    {
        $this->webBrowser = $webBrowser;
        $this->undoStack = array();
        $this->redoStack = array();
    }

    public function BookmarkCurrentPage()
    {
        $command = new AddBookmarkCommand($this->webBrowser);
        $this->undoStack[] = $command;

        $this->redoStack = array();
        
        $command->Execute();
    }

    public function UndoBookmark()
    {
        if(!empty($this->undoStack))
        {
            $command = array_pop($this->undoStack);
            $this->redoStack[] = $command;
            $command->Undo();
        }
    }

    public function RedoBookmark()
    {
        if(!empty($this->redoStack))
        {
            $command = array_pop($this->redoStack);
            $this->undoStack[] = $command;
            if($command instanceof AddBookmarkCommand)
            {
                $command->Redo();
            }
            
        }
    }
}

// Demo of the command pattern:
$memeSurfer = new WebBrowser();
$bookmarker = new Bookmarker($memeSurfer);

$memeSurfer->Navigate("dankMemes.gov");
$bookmarker->BookmarkCurrentPage();

$memeSurfer->PrintBookmarks();

$memeSurfer->Navigate("normieMemes.co");
$bookmarker->BookmarkCurrentPage();

$memeSurfer->PrintBookmarks();

$bookmarker->UndoBookmark();

$memeSurfer->PrintBookmarks();

$bookmarker->RedoBookmark();

$memeSurfer->PrintBookmarks();