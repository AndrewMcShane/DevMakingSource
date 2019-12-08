#include <stdexcept>

class Node {
    public:
        Node::Node(int value)
            : value(value), next(nullptr), prev(nullptr)
        {}
        Node::Node(int value, Node* next, Node* prev)
            : value(value), next(next), prev(prev)
        {}
        int value;
        Node* next;
        Node* prev;
};

class DoublyLinkedList {
    public:
        DoublyLinkedList::DoublyLinkedList()
            : size(0), head(nullptr), tail(nullptr)
        {}
        DoublyLinkedList::~DoublyLinkedList();
        bool isEmpty();
        bool contains(int value);
        int get(int index);
        int getFirst();
        int getLast();
        void add(int value);
        void addFirst(int value);
        void remove(int value);
        int length();
    private:
        Node* head;
        Node* tail;
        int size;
};

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

bool DoublyLinkedList::isEmpty() {
    return this->size == 0;
}

bool DoublyLinkedList::contains(int value) {
    if(this->isEmpty()) {
        return false;
    }
    Node* tmp = this->head;
    while(tmp != nullptr) {
        if(tmp->value == value) {
            return true;
        }
        tmp = tmp->next;
    }
    return false;
}

int DoublyLinkedList::get(int index) {
    if(index > this->size || this->isEmpty()) {
        throw std::out_of_range("get(int index) : Index out of range");
    }
    Node* tmp;
    if(index > this->size / 2) {
        index = (this->size - 1) - index;
        tmp = this->tail;

        while(index > 0) {
            tmp = tmp->prev;
            --index;
        }
    }
    else {
        tmp = this->head;
        while(index > 0) {
            tmp= tmp->next;
            --index;
        }
    }
    return tmp->value;
}

int DoublyLinkedList::getFirst() {
    if(!this->isEmpty()) {
        return this->head->value;
    }
    throw std::out_of_range("getFirst() : list is empty");
}

int DoublyLinkedList::getLast() {
    if(!this->isEmpty()) {
        return this->tail->value;
    }
    throw std::out_of_range("getLast() : list is empty");
}

void DoublyLinkedList::add(int value) {
    if(this->isEmpty()) {
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

void DoublyLinkedList::addFirst(int value) {
    if(this->isEmpty()) {
        Node* tmp = new Node(value);
        this->head = tmp;
        this->tail = tmp;
        ++this->size;
        return;
    }
    else {
        Node* tmp = this->head;
        tmp->prev = new Node(value, tmp, nullptr);
        this->head = tmp;
        ++this->size;
    }
}

void DoublyLinkedList::remove(int value) {
    if(this->isEmpty()) {
        return;
    }

    Node* tmp = this->head;
    while(tmp != nullptr) {
        if(tmp->value == value) {
            if(tmp->prev != nullptr) tmp->prev->next = tmp->next;
            else this->head = tmp->next;
            if(tmp->next != nullptr) tmp->next->prev = tmp->prev;
            else this->tail = tmp->prev;
            --this->size;
            return;
        }
        tmp = tmp->next;
    }
}

int DoublyLinkedList::length() {
    return this->size;
}