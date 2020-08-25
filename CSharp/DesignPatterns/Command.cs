using System.Collections.Generic;
using System;

public class Document
{
    string content;

    public Document()
    {
        content = "";
    }

    public void PrintDocument()
    {
        Console.WriteLine(content);
    }

    public void AddContent(string content)
    {
        this.content += content;
    }

    public int Length()
    {
        return content.Length;
    }

    public string CharAt(int index)
    {
        return content[index];
    }

    public void RemoveContent(int numChars)
    {
        if(numChars < 0) return;
        if(numChars > content.Length - 1)
        {
            content = "";
            return;
        }

        content = content.Remove(content.Length - numChars - 1);
    }
}

public interface IUndoableCommand
{
    void Execute();
    void Undo();
    void Redo();
}

public class AddContentCommand: IUndoableCommand
{
    Document document;
    string content;

    public AddContentCommand(Document document, string content)
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
        this.document.RemoveContent(content.Length);
    }

    public void Redo()
    {
        this.Execute();
    }
}

public class BackspaceCommand: IUndoableCommand
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

    public void Write(string content)
    {
        IUndoableCommand command = new AddContentCommand(this.document, content);

        this.undoStack.Push(command);
        this.redoStack.Clear();

        command.Execute();
    }

    public void Backspace()
    {
        if(this.document.Length == 0) return;

        IUndoableCommand command  = new BackspaceCommand(this.document);
        this.undoStack.Push(command);
        this.redoStack.Clear();

        command.Execute();
    }

    public void Undo()
    {
        if(this.undoStack.Count > 0)
        {
            IUndoableCommand command = this.undoStack.Pop();
            this.redoStack.Push(command);
            command.Undo();
        }
    }

    public void Redo()
    {
        if(this.redoStack.Count > 0)
        {
            IUndoableCommand command = this.redoStack.Pop();
            this.undoStack.Push(command);
            command.Redo();
        }
    }
}


// Document Writer Demo:
public class Solution
{
    public static void Main(string[] args)
    {
        
        Document doc = new Document();
        DocumentWriter writer = new DocumentWriter(doc);

        // Write to the document:
        writer.Write("Hello world!");
        writer.Backspace();
        doc.PrintDocument();
        writer.Undo();
        doc.PrintDocument();
        writer.Redo();
        doc.PrintDocument();


        writer.Write("Goodbye!");
        doc.PrintDocument();
        writer.Undo();
        doc.PrintDocument();
        writer.Undo();
        doc.PrintDocument();

    }
}