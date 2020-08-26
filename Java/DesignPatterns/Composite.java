//Composite pattern in Java
import java.util.LinkedList;

public abstract class FileSystem
{
    public boolean isDir;
    public boolean isFile;
    public String name;

    public FileSystem()
    {
        this.isDir  = false;
        this.isFile = false;
        this.name  = null;
    }

    // Work-around since Java doesn't support default value parameters:
    public void ls()
    {
        this.ls(0);
    }

    public abstract void ls(int indent);

    public String Tab(int indent)
    {
        return " ".repeat(indent * 4);
    }
}

public class Directory extends FileSystem
{
    protected LinkedList<FileSystem> listings;

    public Directory(String name)
    {
        this.isDir = true;
        this.name = name;
        this.listings = new LinkedList<FileSystem>();
    }

    public void ls(int indent)
    {
        System.out.println("\n" + this.Tab(indent) + this.name + "/");

        for(FileSystem item: this.listings)
        {
            item.ls(indent + 1);
        }
    }

    public void Add(FileSystem listing)
    {
        this.listings.push(listing);
    }

    public void Remove(FileSystem listing)
    {
        this.listings.remove(listing);
    }
}

public class FileType extends FileSystem
{
    protected String ext;

    public FileType(String name, String ext)
    {
        this.ext = ext;
        this.name = name;
        this.isFile = true;
    }

    public void ls(int indent)
    {
        System.out.println(this.Tab(indent) + this.name + "." + this.ext);
    }
}

public class CompositeSolution
{
    public static void main(String[] args)
    {
        Directory root = new Directory("usr");

        // Create the home dir:
        Directory homeDir = new Directory("home");
        homeDir.Add(new FileType("howToCodeInJava", "pdf"));
        homeDir.Add(new FileType("ThingsToDo", "txt"));
        homeDir.Add(new FileType("grandCanyon", "png"));

        // Add home to the root;
        root.Add(homeDir);

        // Create a directory for projects:
        Directory projectDir = new Directory("codeProjects");
        // Create a new project:
        Directory devmakingProj = new Directory("devmaking");
        devmakingProj.Add(new FileType("index", "html"));
        // Create another project:
        Directory ticTacToeProj = new Directory("AI_tic-tac-toe");
        ticTacToeProj.Add(new FileType("app", "py"));

        // Add the projects to the project directory:
        projectDir.Add(devmakingProj);
        projectDir.Add(ticTacToeProj);

        // Add the project dir to the root:
        root.Add(projectDir);

        // list out the file system:
        root.ls();
    }
}