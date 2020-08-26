// Command pattern in TypeScript

class TextDocument
{
    protected content: string;

    constructor()
    {
        this.content = "";
    }

    public printDocument()
    {
        console.log(this.content);
    }

    public addContent(content: string)
    {
        this.content += content;
    }

    public length(): number
    {
        return this.content.length;
    }

    public charAt(index: number): string
    {
        return this.content[index];
    }

    public removeContent(numChars: number)
    {
        if(numChars < 0) return;
        if(numChars > this.content.length - 1)
        {
            this.content = "";
            return;
        }

        this.content = this.content.substring(0, this.content.length - numChars);

    }
}

interface IUndoableCommand
{
    execute(): void;
    undo(): void;
    redo(): void;
}

class AddContentCommand implements IUndoableCommand
{
    document: TextDocument;
    content: string;
    
    constructor(document: TextDocument, content: string)
    {
        this.document = document;
        this.content = content;
    }

    execute() {
        this.document.addContent(this.content);
    }
    undo() {
        this.document.removeContent(this.content.length);
    }
    redo() {
        this.execute();
    }
    
}

class BackspaceCommand implements IUndoableCommand
{
    document: TextDocument;
    removedChar: string;
    
    constructor(document: TextDocument)
    {
        this.document = document;
        this.removedChar = null;
    }

    execute()
    {
        this.removedChar = this.document.charAt(this.document.length() - 1);
        this.document.removeContent(1);
    }
    undo() 
    {
        this.document.addContent(this.removedChar);
    }
    redo()
    {
        this.execute();
    }
    
}

class DocumentWriter
{
    undoStack: Array<IUndoableCommand>;
    redoStack: Array<IUndoableCommand>;

    document: TextDocument;

    constructor(document: TextDocument)
    {
        this.document = document;
        this.undoStack = new Array<IUndoableCommand>();
        this.redoStack = new Array<IUndoableCommand>();
    }

    public write(content: string)
    {
        const command = new AddContentCommand(this.document, content);
        this.undoStack.push(command);
        this.redoStack = new Array<IUndoableCommand>();

        command.execute();
    }

    public backspace()
    {
        if(this.document.length() == 0) return;
        const command = new BackspaceCommand(this.document);
        this.undoStack.push(command);
        this.redoStack = new Array<IUndoableCommand>();

        command.execute();
    }

    public undo()
    {
        if(this.undoStack.length > 0)
        {
            const command = this.undoStack.pop();
            this.redoStack.push(command);
            command.undo();
            
        }
    }

    public redo()
    {
        if(this.redoStack.length > 0)
        {
            const command = this.redoStack.pop();
            this.undoStack.push(command);
            command.redo();
        }
    }
}


class CommandSolution
{
    public execute()
    {
        let doc = new TextDocument();
        let writer = new DocumentWriter(doc);

        // Write to the document:
        writer.write("Hello world!");
        doc.printDocument(); // Hello world!
        writer.backspace();
        doc.printDocument(); // Hello world
        writer.undo();
        doc.printDocument(); // Hello world!
        writer.redo();
        doc.printDocument(); // Hello world

        writer.write(" Goodbye!"); 
        doc.printDocument(); // Hello world Goodbye!
        writer.undo();
        doc.printDocument(); // Hello world
        writer.undo();
        doc.printDocument(); // Hello world!


    }
}

const solution = new CommandSolution();
solution.execute();