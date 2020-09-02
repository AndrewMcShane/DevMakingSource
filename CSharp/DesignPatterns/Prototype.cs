// Prototype Pattern in C#

using System.Collections.Generic;


// Computer interface:
public interface IComputer
{
    // Clone Method:
    IComputer Clone();
}


// The Desktop class for a computer type:
public class Desktop: IComputer
{
    public string cpu;
    public string gpu;
    public int gigsOfRam;

    public Desktop()
    {
        this.cpu = "C1";
        this.gpu = "Integrated Graphics";
        this.gigsOfRam = 2;
    }

    // Copy constructor: helps with the clone method!
    public Desktop(Desktop prototype)
    {
        this.cpu = prototype.cpu;
        this.gpu = prototype.gpu;
        this.gigsOfRam = prototype.gigsOfRam;
    }

    // Clone implementation:
    public IComputer Clone()
    {
        return new Desktop(this);
    }
}

// A catalogue of Desktop computers:
public class DesktopCatalogue
{
    // A Map of the cataloged items:
    Dictionary<string, IComputer> catalogue;

    public DesktopCatalogue()
    {
       this.catalogue = new Dictionary<string, IComputer>();
    }

    public void Add(string key, IComputer prototype)
    {
        // Add in a *clone* of the prototype to prevent editing its properties!
        catalogue.Add(key, prototype.Clone());
    }

    public IComputer GetComputer(string key)
    {
        if(catalogue.ContainsKey(key))
        {
            // Return a *clone* of the catalogued prototype!
            return catalogue[key].Clone();
        }
        else
        {
            throw new System.Exception("No Computer of this key exists.");
        }
    }
}


// Demo of the Prototype Pattern:

public class Solution
{
    public static void Main(string[] args)
    {
        // Create the catalogue:
        DesktopCatalogue catalogue = new DesktopCatalogue();

        // Create the first prototype:
        Desktop basicWorkstation = new Desktop();
        basicWorkstation.cpu  = "j5";
        basicWorkstation.gpu = "cornea Graphics 9001";
        basicWorkstation.gigsOfRam = 4;

        // Throw it in the catalogue:
        catalogue.Add("Everyday Computing", basicWorkstation);

        // Create another prototype:
        Desktop bigGamerSetup = new Desktop();
        bigGamerSetup.cpu = "X1 Hyper Thread";
        bigGamerSetup.gpu = "Hive 7series";
        bigGamerSetup.gigsOfRam = 1024;

        // Add into the mix:
        catalogue.Add("Gaming", bigGamerSetup);

        // Using the Prototypes:
        
        // Get a computer from the Catalogue:
        Desktop myBasicComputer = (Desktop)catalogue.GetComputer("Everyday Computing");
        // Let's add a bit more ram as an upgraded version:
        myBasicComputer.gigsOfRam = 8;


        // Get another one:
        Desktop mySuperComputer = (Desktop)catalogue.GetComputer("Gaming");
        // Add even more ram!
        mySuperComputer.gigsOfRam = 4096;
        mySuperComputer.cpu = "Octa-Streamed X1 Giga Thread";
        mySuperComputer.gpu = "Quad Hive 9series";
        
    }
}