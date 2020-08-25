// Command Pattern Demo in C++

#include <vector>
#include <string>
#include <algorithm>
#include <iostream>

// The WebBrowser Class:
class WebBrowser
{
    public:
        WebBrowser();
        std::string GetCurrentUrl();
        void Navigate(std::string url);
        void BookmarkCurrentPage();
        void RemoveBookmark(std::string url);
        void PrintBookmarks();
    protected:
        std::vector<std::string> bookmarks;
        std::string currentUrl;
};

WebBrowser::WebBrowser()
{
    this->currentUrl = std::string();
};

std::string WebBrowser::GetCurrentUrl()
{
    return this->currentUrl;
};

void WebBrowser::Navigate(std::string url)
{
    this->currentUrl = url;
};

void WebBrowser::BookmarkCurrentPage()
{
    // If the current url is not empty 
    // and the bookmark list doesn't contain the current url
    if(!currentUrl.empty() 
    && std::find(bookmarks.begin(), bookmarks.end(), currentUrl) == bookmarks.end())
    {
        bookmarks.push_back(currentUrl);
    }
};

void WebBrowser::RemoveBookmark(std::string url)
{
    // Get the item from the list and remove it:
    std::cout << "Removing Bookmark: " << url <<  std::endl;

    std::vector<std::string>::iterator itr = std::find(bookmarks.begin(), bookmarks.end(), url);
    if(itr != bookmarks.end())
    {
        bookmarks.erase(itr);
    }
};

void WebBrowser::PrintBookmarks()
{
    std::vector<std::string>::iterator itr = this->bookmarks.begin();

    std::cout << "Bookmarks:" << std::endl;

    for(itr; itr != this->bookmarks.end(); ++itr)
    {
        std::cout << "\t" << *itr << std::endl;
    }
}


// The Command abstract class:
class UndoableCommand
{
    public:
        virtual void Execute() = 0;
        virtual void Undo() = 0;
        virtual void Redo() = 0;
};

// The Add Bookmark Command:
class AddBookmarkCommand: public UndoableCommand
{
    public:
        AddBookmarkCommand(WebBrowser* browser)
        : pBrowser(browser),url(std::string())
        {};
        void Execute() override;
        void Undo() override;
        void Redo() override;

    public:
        WebBrowser* pBrowser;
        std::string url;
};

void AddBookmarkCommand::Execute()
{
    this->url = this->pBrowser->GetCurrentUrl();
    std::cout << "Bookmarked: " << this->url << std::endl;
    this->pBrowser->BookmarkCurrentPage();
};

void AddBookmarkCommand::Undo()
{
    this->pBrowser->RemoveBookmark(this->url);
};

void AddBookmarkCommand::Redo()
{
    this->Execute();
};


// The bookmarker class:
class Bookmarker
{
    public:
        Bookmarker(WebBrowser* browser);
        ~Bookmarker();
        void BookmarkCurrentPage();
        void UndoBookmark();
        void RedoBookmark();
    private:
        std::vector<AddBookmarkCommand*> undoStack;
        std::vector<AddBookmarkCommand*> redoStack;
        WebBrowser* pBrowser;
};

Bookmarker::Bookmarker(WebBrowser* browser)
{
    this->pBrowser = browser;
};

Bookmarker::~Bookmarker()
{
    // Delete all the objects:
    std::vector<AddBookmarkCommand*>::const_iterator it = this->undoStack.begin();

    for(; it != this->undoStack.end(); ++it)
    {
        delete *it;
    }
    this->undoStack.clear();

    it = this->redoStack.begin();
    for(; it != this->redoStack.end(); ++it)
    {
        delete *it;
    }

    this->redoStack.clear();

}

void Bookmarker::BookmarkCurrentPage()
{
    AddBookmarkCommand* command = new AddBookmarkCommand(this->pBrowser);
    this->undoStack.push_back(command);
    this->redoStack.clear();

    command->Execute();
};

void Bookmarker::UndoBookmark()
{
    if(!this->undoStack.empty())
    {   
        AddBookmarkCommand* command = this->undoStack.back();
        this->undoStack.pop_back();
        this->redoStack.push_back(command);

        command->Undo();
    }
};

void Bookmarker::RedoBookmark()
{
    if(!this->redoStack.empty())
    {
        AddBookmarkCommand* command = this->redoStack.back();
        this->redoStack.pop_back();

        this->undoStack.push_back(command);
        command->Redo();
    }
};

// Demo of the Command pattern:
int main()
{
    WebBrowser* memeSurfer = new WebBrowser();
    Bookmarker bookmarker = Bookmarker(memeSurfer);

    memeSurfer->Navigate("dankMemes.gov");

    bookmarker.BookmarkCurrentPage();

    // Print current bookmarks:
    memeSurfer->PrintBookmarks();

    memeSurfer->Navigate("normieMemes.co");
    bookmarker.BookmarkCurrentPage();

    memeSurfer->PrintBookmarks();

    bookmarker.UndoBookmark();

    memeSurfer->PrintBookmarks();

    bookmarker.RedoBookmark();

    memeSurfer->PrintBookmarks();

}