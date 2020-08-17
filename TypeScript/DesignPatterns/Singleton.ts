// Singleton Demo in TypeScript

class Logger
{
    // Static instance:
    private static instance;

    // Instance data:
    private loggingEnabled: boolean;

    constructor()
    {
        this.loggingEnabled = true;
    }

    private static getInstance(): Logger
    {
        if(!Logger.instance)
        {
            Logger.instance = new Logger();
        }

        return Logger.instance;
    }

    // -------------------------------
    // Public-facing static methods:
    // -------------------------------
    
    public static enableLogging(state: boolean)
    {
        let logger = Logger.getInstance();
        logger.loggingEnabled = state;
    }

    public static log(message: any)
    {
        let logger = Logger.getInstance();
        if(logger.loggingEnabled)
        {
            console.log(message);
        }
    }
}