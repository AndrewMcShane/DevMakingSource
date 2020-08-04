// Our museum interface that accepts visitors to it:
public interface MuseumExhibit 
{
    void accept(Visitor v);
    String getName();
}

public class Painting implements MuseumExhibit 
{
    public String getName() {
        return "Sunday Afternoon";
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}

public class Sculpture implements MuseumExhibit 
{
    public String getName() {
        return "Contemplating Person";
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}

public class Artifact implements MuseumExhibit 
{
    public String getName() {
        return "Ancient Tablet";
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}

// Our visitor interface with the types of visits we can accomplish:
public interface Visitor 
{
    void visit(Painting painting);
    void visit(Sculpture sculpture);
    void visit(Artifact artifact);
}

// Our art critic:
public class ArtCritic implements Visitor 
{
    public void visit(Painting painting) {
        System.out.println("The critic inspects " + painting.getName() + " very closely");
    }
    public void visit(Sculpture sculpture) {
        System.out.println("The critic admires the form of " + sculpture.getName());
    }
    public void visit(Artifact artifact) {
        System.out.println("The critic isn't sure what to think of the " + artifact.getName());
    }
}
 
// Our historian, more interested in artifacts than art:
public class Historian implements Visitor 
{
   public void visit(Painting painting) {
        System.out.println("The historian enjoys looking at " + painting.getName());
    }
    public void visit(Sculpture sculpture) {
        System.out.println("The historian is perplexed by " + sculpture.getName());
    }
    public void visit(Artifact artifact) {
        System.out.println("The historian estimates the age of the " + artifact.getName());
    }
}