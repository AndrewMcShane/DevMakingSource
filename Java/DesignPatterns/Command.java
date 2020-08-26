import java.util.*;

public class Document
{
    String content;

    public Document()
    {
        content = "";
    }

    public void PrintDocument()
    {
        System.out.println(content);
    }

    public void AddContent(String content)
    {
        this.content += content;
    }

    public int Length()
    {
        return content.length();
    }

    public String CharAt(int index)
    {
        return String.valueOf(content.charAt(index));
    }

    public void RemoveContent(int numChars)
    {
        if(numChars < 0) return;
        if(numChars > content.length() - 1)
        {
            content = "";
            return;
        }

        //content = content.Remove(content.Length - numChars);
        content = content.substring(0,  content.length() - numChars);
        
    }
}

public interface IUndoableCommand
{
    void Execute();
    void Undo();
    void Redo();
}

public class AddContentCommand implements IUndoableCommand
{
    Document document;
    String content;

    public AddContentCommand(Document document, String content)
    {
        this.document = document;
        this.content = content;
    }

    public void Execute()
    {
        this.document.AddContent(content);
    }

    public void Undo()
    {
        this.document.RemoveContent(content.length());
    }

    public void Redo()
    {
        this.Execute();
    }
}

public class BackspaceCommand implements IUndoableCommand
{
    Document document;
    String removedChar;

    public BackspaceCommand(Document document)
    {
        this.document = document;
        removedChar = null;
    }

    public void Execute()
    {
        removedChar = document.CharAt(document.Length() - 1);
        this.document.RemoveContent(1);
    }

    public void Undo()
    {
        this.document.AddContent(this.removedChar);
    }

    public void Redo()
    {
        this.Execute();
    }
    
}

public class DocumentWriter
{
    Stack<IUndoableCommand> undoStack;
    Stack<IUndoableCommand> redoStack;

    Document document;

    public DocumentWriter(Document document)
    {
        this.document = document;
        this.undoStack = new Stack<IUndoableCommand>();
        this.redoStack = new Stack<IUndoableCommand>();
    }

    public void Write(String content)
    {
        IUndoableCommand command = new AddContentCommand(this.document, content);

        this.undoStack.push(command);
        this.redoStack.clear();

        command.Execute();
    }

    public void Backspace()
    {
        if(this.document.Length() == 0) return;

        IUndoableCommand command  = new BackspaceCommand(this.document);
        this.undoStack.push(command);
        this.redoStack.clear();

        command.Execute();
    }

    public void Undo()
    {
        if(this.undoStack.size() > 0)
        {
            IUndoableCommand command = this.undoStack.pop();
            this.redoStack.push(command);
            command.Undo();
        }
    }

    public void Redo()
    {
        if(this.redoStack.size() > 0)
        {
            IUndoableCommand command = this.redoStack.pop();
            this.undoStack.push(command);
            command.Redo();
        }
    }
}


// Document Writer Demo:
public class Solution
{
    public static void Main(String[] args)
    {
        
        Document doc = new Document();
        DocumentWriter writer = new DocumentWriter(doc);

        // Write to the document:
        writer.Write("Hello world!");

        writer.Backspace();
        doc.PrintDocument(); // Hello world
        writer.Undo();
        doc.PrintDocument(); // Hello world!
        writer.Redo();
        doc.PrintDocument(); // Hello world

        writer.Write(" Goodbye!"); 
        doc.PrintDocument(); // Hello world Goodbye!
        writer.Undo();
        doc.PrintDocument(); // Hello world
        writer.Undo();
        doc.PrintDocument(); // Hello world!

    }
}