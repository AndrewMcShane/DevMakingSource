// Part of the observer demo.

public class Observer
{
    // The data:
    // Helper for a linked list structure:
    public Observer next;

    // Constructor:
    public Observer()
    {
        next = null;
    }

    // The update method, called to by the Subject class.
    public void Update(String message)
    {
        System.out.println(message);
    }

}