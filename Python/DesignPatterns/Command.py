# Command Pattern in Python

class WebBrowser(object):
    def __init__(self):
        self.bookmarks = []
        self.currentUrl = None
    
    def Navigate(self, url):
        self.currentUrl = url
    
    def BookmarkCurrentPage(self):
        valid = self.currentUrl is not None
        contains = self.currentUrl in self.bookmarks
        if valid and not contains:
            self.bookmarks.append(self.currentUrl)

    def RemoveBookmark(self, url):
        self.bookmarks.remove(url)
    
    def PrintBookmarks(self):
        print("Bookmarks:")
        for i in self.bookmarks:
            print("\t"+ str(i))


class AddBookmarkCommand(object):
    def __init__(self, webBrowser):
        self.webBrowser = webBrowser
        self.url = None

    def Execute(self):
        self.url = self.webBrowser.currentUrl
        self.webBrowser.BookmarkCurrentPage()

    def Undo(self):
        self.webBrowser.RemoveBookmark(self.url)

    def Redo(self):
        self.Execute()


class Bookmarker(object):
    def __init__(self, webBrowser):
        self.webBrowser = webBrowser
        self.undoStack = []
        self.redoStack = []

    def BookmarkCurrentPage(self):
        command = AddBookmarkCommand(self.webBrowser)
        self.undoStack.append(command)
        self.redoStack = []

        command.Execute()

    def UndoBookmark(self):
        if len(self.undoStack) != 0:
            command = self.undoStack.pop()
            self.redoStack.append(command)

            command.Undo()

    def RedoBookmark(self):
        if len(self.redoStack) != 0:
            command = self.redoStack.pop()
            self.undoStack.append(command)

            command.Redo()

    
# Demo of the command pattern:

memeSurfer = WebBrowser()
bookmarker = Bookmarker(memeSurfer)

memeSurfer.Navigate("dankMemes.gov")
bookmarker.BookmarkCurrentPage()

memeSurfer.PrintBookmarks()

memeSurfer.Navigate("normieMemes.co")
bookmarker.BookmarkCurrentPage()

memeSurfer.PrintBookmarks()

bookmarker.UndoBookmark()

memeSurfer.PrintBookmarks()

bookmarker.RedoBookmark()

memeSurfer.PrintBookmarks()