// Header:
#include <iostream>

class Singleton
{
    /*
        Implementation notes:
            This is thread-safe as of C++ 11/17/..
            This is NOT thread-safe in C++ 98/03
    */

    public:
        // Public-facing methods:

        static void EnableLogging(const bool state)
        {
            Singleton& instance = GetInstance();
            instance.logEnabled = state;
        };

        static void LogMessage(const char* message)
        {
            Singleton& instance = GetInstance();
            if(instance.logEnabled)
            {
                std::cout << message << std::endl;
            }
        };

    private:
        // Private constructor:
        Singleton()
            : logEnabled(true)
        { };

        // Unlike other languages, we can declare it static
        // Makes instancing much nicer for us.
        static Singleton& GetInstance()
        {
            static Singleton instance;
            return instance;
        }

    public:
        // We don't want these to be usable:
        Singleton(const Singleton&) = delete;
        Singleton& operator=(const Singleton&) = delete;

    private:
        // Instance data:
        bool logEnabled;
};
