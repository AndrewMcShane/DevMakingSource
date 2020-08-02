// Part of the Observer demo.

public class Subject
{
    // The root of the subscribed objects in the linked-list structure:
    Observer root;

    public Subject()
    {
        root = null;
    }

    // Add an observer to the list:
    // You can also feel free to use a built-in structure like a List<T> instead!
    public void AddObserver(Observer observer)
    {
        // Insert head-first into the list.
        observer.next = root;
        root = observer;
    }

    public void Notify(String message)
    {
        // Iterate the observers, notifying them all:
        Observer tmp = this.root;
        while(tmp != null)
        {
            tmp.Update(message);
            tmp = tmp.next;
        }
    }

}