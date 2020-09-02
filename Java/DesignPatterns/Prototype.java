// Prototype in Java
import java.util.*;

// Car Package interface:
public interface CarPackage
{
    CarPackage Clone();
}

// Car Implementation:
public class SportsCarPackage implements CarPackage
{
    public String seatType;
    public String engineModel;
    public boolean hasSunRoof;

    public SportsCarPackage()
    {
        this.seatType = "Basic";
        this.engineModel = "V6";
        this.hasSunRoof = false;
    }

    public CarPackage Clone()
    {
        SportsCarPackage copy = new SportsCarPackage();
        copy.seatType = this.seatType;
        copy.engineModel = this.engineModel;
        copy.hasSunRoof = this.hasSunRoof;
        return copy;
    }
}

// Cache the prototypes in a catalogue:
public class CarCatalogue
{
    protected HashMap<String, CarPackage> catalogue;

    public CarCatalogue()
    {
        this.catalogue = new HashMap<String, CarPackage>();
    }

    public void Add(String key, CarPackage prototype)
    {
        this.catalogue.put(key, prototype.Clone());
    }

    public CarPackage Get(String key)
    {
        if(this.catalogue.containsKey(key))
        {
            return this.catalogue.get(key).Clone();
        }
        else
        {
            throw new IllegalArgumentException("Key does not exist.");
        }
    }
}

// Demo:
public class Solution
{
    public static void main(String[] args)
    {
        CarCatalogue catalogue = new CarCatalogue();

        // Create a basic package with default values:
        SportsCarPackage basicPackage = new SportsCarPackage();
        catalogue.Add("Basic", basicPackage);

        // Create the next level up:
        SportsCarPackage trackPackage = new SportsCarPackage();
        trackPackage.seatType = "Leather";
        trackPackage.hasSunRoof = true;
        
        catalogue.Add("Track", trackPackage);

        // Add the sporty option:
        // Base it off of the Track package:
        SportsCarPackage sportPackage = (SportsCarPackage)catalogue.Get("Track");
        sportPackage.engineModel = "V8";

        catalogue.Add("Sport", sportPackage);

        // Create a custom package:
        SportsCarPackage myCustomPackage = (SportsCarPackage)catalogue.Get("Sport");
        // Extra power:
        myCustomPackage.engineModel = "V8 Hemi";
        // But no sun roof :(
        myCustomPackage.hasSunRoof = false;
    }
}