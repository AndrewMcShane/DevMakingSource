#include <stdexcept>
#include <iostream>

// DLink Node class:
class Node {
    public:
        Node(int value)
            : value(value), next(nullptr), prev(nullptr)
        {}
        Node(int value, Node* next, Node* prev)
            : value(value), next(next), prev(prev)
        {}
        int value;
        Node* next;
        Node* prev;
};

// =====================
// Iterators!
// =====================

// Forward Iterator:
class DLinkIterator
{
    public:
        DLinkIterator(Node* start)
        : current(start)
        {};
        bool HasNext() { return this->current != nullptr; }
        int Next();

    protected:
        Node* current;
};

int DLinkIterator::Next()
{
    if(!this->HasNext())
    {
        throw std::out_of_range("No more nodes to iterate!");
    }

    int val = this->current->value;
    this->current = this->current->next;

    return val;
}

// Reverse Iterator:
class DLinkReverseIterator
{
    public:
        DLinkReverseIterator(Node* start)
        : current(start)
        {};
        bool HasNext() { return this->current != nullptr; }
        int Next();

    protected:
        Node* current;
};

int DLinkReverseIterator::Next()
{
    if(!this->HasNext())
    {
        throw std::out_of_range("No more nodes to iterate!");
    }

    int val = this->current->value;
    // Iterate backwards!
    this->current = this->current->prev;
    return val;
}


// =============================================
// Dumbed-Down version of a Doubly-Linked-List:
// =============================================
class DoublyLinkedList {
    public:
        DoublyLinkedList()
            : size(0), head(nullptr), tail(nullptr)
        {}
        ~DoublyLinkedList();
        bool IsEmpty();
        void Add(int value);
        int Length();

        DLinkIterator GetIterator() { return DLinkIterator(this->head); };
        DLinkReverseIterator GetReverseIterator() { return DLinkReverseIterator(this->tail); };

    private:
        Node* head;
        Node* tail;
        int size;
};

// DLink Implementation:
DoublyLinkedList::~DoublyLinkedList() {
    Node* tmp = this->head;
    this->tail = nullptr;
    while(tmp != nullptr) {
        Node* deadPtr = tmp;
        tmp = tmp->next;
        delete tmp;
    }
    this->head = nullptr;
}

bool DoublyLinkedList::IsEmpty() {
    return this->size == 0;
}

void DoublyLinkedList::Add(int value) {
    if(this->IsEmpty()) {
        Node* tmp = new Node(value);
        this->head = tmp;
        this->tail = tmp;
        ++this->size;
        return;
    }
    else {
        Node* tmp = this->tail;
        tmp->next = new Node(value, nullptr, tmp);
        tail = tmp->next;
        ++this->size;
    }
}

int DoublyLinkedList::Length() {
    return this->size;
}


// Demo:

int main()
{
    
    DoublyLinkedList myList = DoublyLinkedList();

    myList.Add(1);
    myList.Add(2);
    myList.Add(3);
    myList.Add(4);
    myList.Add(5);

    // Iterate forwards:
    std::cout << "Forward iterator:" << std::endl;
    DLinkIterator it = myList.GetIterator();

    while(it.HasNext())
    {
        std::cout << it.Next() << std::endl;
    }

    /*
        Output:
            1
            2
            3
            4
            5
    */

    // Iterate backwards:
    std::cout << "Reverse iterator:" << std::endl;
    DLinkReverseIterator reverseIt = myList.GetReverseIterator();

    while(reverseIt.HasNext())
    {
        std::cout << reverseIt.Next() << std::endl;
    }

    /*
        Output:
            5
            4
            3
            2
            1
    */

}