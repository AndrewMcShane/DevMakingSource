// Composite pattern in TypeScript

abstract class FileSystem
{
    public isDir: boolean;
    public isFile: boolean;
    public name: string;

    constructor()
    {
        this.isDir = false;
        this.isFile = false;
    }

    // On mac and linux, ls means list the current directory.
    // On windows, this is done using dir
    public abstract ls(indent: number): void;

    tab(indent: number = 0) : string
    {
        let space = "";
        for(let i = 0; i < indent; i++)
        {
            space += "    ";
        }
        return space;
    }
}

class Directory extends FileSystem
{
    protected listings: Array<FileSystem>;

    constructor(name: string)
    {
        super();
        this.isDir = true;
        this.name = name;

        this.listings = new Array<FileSystem>();
    }

    public ls(indent: number = 0)
    {
        console.log();
        console.log(this.tab(indent) + this.name + "/");
        
        for(let item of this.listings)
        {
            item.ls(indent + 1);
        }
        
    }

    public add(listing : FileSystem)
    {
        this.listings.push(listing);
    }

    public remove(listing: FileSystem)
    {
        const index = this.listings.indexOf(listing);
        if(index >= 0)
        {
            this.listings.splice(index, 1);
        }
    }
    
}

class FileType extends FileSystem
{
    protected ext: string;

    constructor(name: string, ext: string)
    {
        super();
        this.isFile = true;
        this.name = name;
        this.ext = ext;
    }

    public ls(indent: number = 0)
    {
        console.log(this.tab(indent) + this.name + "." + this.ext);
    }
}

class CompositeSolution
{
    public execute()
    {
        let root = new Directory("usr");

        // Create a home directory:
        let homeDir = new Directory("home");
        // Add a few files:
        homeDir.add(new FileType("howToCode", "pdf"));
        homeDir.add(new FileType("ThingsToDo", "txt"));
        homeDir.add(new FileType("grandCanyon", "png"));
        
        // Add home dir to the root:
        root.add(homeDir);

        // Create a projects bin:
        let projectDir = new Directory("codeProjects");
        // Create a new project:
        let devmakingProj = new Directory("devmaking");
        devmakingProj.add(new FileType("index", "html"));
        // Create another project:
        let ticTacToeProj = new Directory("AI_tic-tac-toe");
        ticTacToeProj.add(new FileType("app", "py"));

        // Add the projects to the project directory:
        projectDir.add(devmakingProj);
        projectDir.add(ticTacToeProj);

        // Add the project dir to the root:
        root.add(projectDir);
        

        // list out the file system:
        root.ls();
    }
}

// Run the demo:
const compositeSolution = new CompositeSolution();
compositeSolution.execute();

// Output:
/*
    usr/

        home/
            howToCode.pdf
            ThingsToDo.txt
            grandCanyon.png

        codeProjects/

            devmaking/
                index.html

            AI_tic-tac-toe/
                app.py
 */