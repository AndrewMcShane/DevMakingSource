// Composite pattern in C++

#include <string>
#include <vector>
#include <iostream>

// Abstract file system base class:
class FileSystem
{
    public:
        FileSystem()
        : isDir(false), isFile(false), name("")
        {};
        virtual ~FileSystem() {};

        // The virtual ls method to "list" out the contents:
        virtual void ls(int indent = 0) {indent;};

        bool isDir;
        bool isFile;
        std::string name;

    protected:
        // Helper for setting the tab correctly:
        std::string tab(int indent)
        {
            return std::string(indent * 4, ' ');
        }
};

// Directory class:
class Directory: public FileSystem
{
    public:
        Directory(std::string name)
        : FileSystem()
        {
            this->isDir = true;
            this->name = name;
        };
        virtual ~Directory();

        void ls(int indent = 0) override;

        void Add(FileSystem* listing);

    protected:
        std::vector<FileSystem*> listings;
};


void Directory::ls(int indent)
{
    std::cout  << "\n" << this->tab(indent) << this->name << "/\n";

    // List all items in the directory:
    std::vector<FileSystem*>::iterator itr = this->listings.begin();
    for(itr; itr != this->listings.end(); ++itr)
    {
        (*itr)->ls(indent + 1);
    }
}

void Directory::Add(FileSystem* listing)
{
    this->listings.push_back(listing);
}

Directory::~Directory()
{
    // Iterate all items, delete them:
    std::vector<FileSystem*>::const_iterator it = this->listings.begin();

    for(; it != this->listings.end(); ++it)
    {
        delete *it;
    }
    this->listings.clear();

}

// The FileType class for files:
class FileType: public FileSystem
{
    public:
        FileType(std::string name, std::string ext)
        : FileSystem()
        {
            this->name = name;
            this->ext = ext;
            this->isFile = true;
        };
        virtual ~FileType() {};

        void ls(int indent = 0) override;

    protected:
        std::string ext;
};

void FileType::ls(int indent)
{
    std::cout << this->tab(indent) << this->name << "." << this->ext << std::endl;
}

// Demo of the Composite pattern:
int main()
{
    Directory* root = new Directory("usr");

    // Create a home directory:
    Directory* homeDir = new Directory("home");
    // Add a few files:
    homeDir->Add(new FileType("howToCode", "pdf"));
    homeDir->Add(new FileType("ThingsToDo", "txt"));
    homeDir->Add(new FileType("grandCanyon", "png"));
      
    // Add home dir to the root:
    root->Add(homeDir);

    // Create a projects bin:
    Directory* projectDir = new Directory("codeProjects");
    // Create a new project:
    Directory* devmakingProj = new Directory("devmaking");
    devmakingProj->Add(new FileType("index", "html"));
    // Create another project:
    Directory* ticTacToeProj = new Directory("AI_tic-tac-toe");
    ticTacToeProj->Add(new FileType("app", "py"));

    // Add the projects to the project directory:
    projectDir->Add(devmakingProj);
    projectDir->Add(ticTacToeProj);

    // Add the project dir to the root:
    root->Add(projectDir); 


    // List everything:
    root->ls();

    
    // Clean up:
    delete root;
}

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