// Prototype Pattern in TypeScript

interface IWebsiteTemplate
{
    clone(): IWebsiteTemplate;
}

// Basic website class:
class BasicWebsite implements IWebsiteTemplate
{
    public headerStyle: string;
    public bodyStyle: string;
    public footerStyle: string;

    constructor()
    {
        this.headerStyle = "Basic";
        this.bodyStyle = "Basic";
        this.footerStyle = "Basic";
    }

    public clone(): IWebsiteTemplate
    {
        let copy = new BasicWebsite();

        copy.headerStyle = this.headerStyle;
        copy.bodyStyle = this.bodyStyle;
        copy.footerStyle = this.footerStyle;

        return copy;
    }
}

// Let's make a library of website templates:
class WebsiteTemplateCache
{
    public library: Map<string, IWebsiteTemplate>;

    constructor()
    {
        this.library = new Map<string, IWebsiteTemplate>();
    }

    public add(key: string, prototype: IWebsiteTemplate)
    {
        this.library.set(key, prototype.clone());
    }

    public get(key: string): IWebsiteTemplate
    {
        if(this.library.has(key))
        {
            return this.library.get(key).clone();
        }
        else
        {
            throw new RangeError("No template exists with that key!");
        }
    }
}


class PrototypeSolution
{
    public static execute()
    {
        let library = new WebsiteTemplateCache();

        // Create a basic template:
        let basicTemplate = new BasicWebsite();
        library.add("Basic", basicTemplate);
        
        // Add a modern template:
        let modernTemplate = new BasicWebsite();
        modernTemplate.headerStyle = "Clean and small";
        modernTemplate.bodyStyle = "Neutral colors";
        modernTemplate.footerStyle = "Dark with subscribe button";

        library.add("Modern", modernTemplate);

        // Create a website starting with the modern template:
        let myWebsite: BasicWebsite = <BasicWebsite>library.get("Modern");
        myWebsite.headerStyle = "Dark and subtle";

        // Create a website starting with the basic template:
        let myBlog: BasicWebsite = <BasicWebsite>library.get("Basic");
        myBlog.bodyStyle = "Light with shadows on article cards.";
    }
}