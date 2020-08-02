// Singleton design pattern in Java
public class Singleton
{
    // The private instance of the singleton class:
    private static Singleton instance;

    // Private data associated with the singleton:
    bool logEnabled;

    // Private constructor:
    private Singleton()
    {
        logEnabled = true;
    }

    // Private instance getter:
    private static Singleton GetInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    // Public static methods for accessing the singleton class:

    // For this example, we'll use it as a logging service:
    public static void EnableLogging(bool state)
    {
        // Get the instance via the static method:
        Singleton logger = Singleton.GetInstance();
        // Set the instance state:
        logger.logEnabled = state;
    }

    public static void LogMessage(string message)
    {
        // Get the instance via the static method:
        Singleton logger = Singleton.GetInstance();
        // Check the instance's logging state:
        if(logger.logEnabled)
        {
            // Write to the console window:
            System.out.println(message);
        }
    }
}