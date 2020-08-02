#include <iostream>

// The Observer class, which the subject calls out to with updates:
class Observer
{
    public:
        // Constructor:
        Observer()
        : pNext(nullptr)
        {};
        // Helper pointer for a linked-list structure:
        // however feel free to use another structure as you see fit.
        Observer* pNext;

        // The update method for the observer. This is usually called from the Subject class.
        void Update(const char* message)
        {
            std::cout << message << std::endl;
        }

};

// The Subject class, responsible for sending out updates to all observing objects:
class Subject
{
    public:
        // Constructor:
        Subject()
        : pRoot(nullptr)
        {};

        // We can either delete things here, or let the owners deal with them.
        // Since we do not create observers in this class, 
        // we might not want this class to be responsible for deleting them.
        ~Subject() { };

        // The notify message in this example passes a string along to all of the subscribers.
        // In other use cases, this could be objects, states, controller inputs, and more!
        void Notify(const char* message)
        {
            Observer* pTmp = this->pRoot;
            while (pTmp != nullptr)
            {
                pTmp->Update(message);

                pTmp = pTmp->pNext;
            }
            
        }

        // Insert an observer into the list.
        // For example sake, we won't worry about unsubscribing, 
        // which is just a simple linked-list removal for us.
        void AddObserver(Observer& observer)
        {
            // Insert at the front of the list:
            observer.pNext = this->pRoot;
            this->pRoot = &observer;
        }
    
    private:
        // Our Subject data: the root Observer in our list:
        Observer* pRoot;
};