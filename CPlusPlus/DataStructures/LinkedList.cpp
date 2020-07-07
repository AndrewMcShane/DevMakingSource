#include <stdio.h>
#include <stdexcept>

class Node {
    public:
        Node(int value)
            : value(value), next(nullptr)
        {}
        Node(int value, Node* next)
            : value(value), next(next)
        {}
        int value;
        Node* next;
};

class LinkedList {
    public:
        LinkedList()
            :size(0), root(nullptr)
        {};
        ~LinkedList();
        bool isEmpty();
        bool contains(int value);
        int get(int index);
        int getFirst();
        int getLast();
        void add(int value);
        void addFirst(int value);
        void addAfter(int key, int toAdd);
        void addBefore(int key, int toAdd);
        void remove(int value);
        int length();
    private:
        Node* root;
        int size;
};

LinkedList::~LinkedList() {
    Node* tmp = this->root;
    while(tmp != nullptr) {
        Node* deadPtr = tmp;
        tmp = tmp->next;
        delete deadPtr;
    }
    tmp = nullptr;
}


bool LinkedList::isEmpty() {
    return size == 0;
}

bool LinkedList::contains(int value) {
    if(this->isEmpty()) {
        return false;
    }
    Node* tmp = this->root;
    while(tmp != nullptr) {
        if(tmp->value == value) {
            return true;
        }
        tmp = tmp->next;
    }
    return false;
}

int LinkedList::get(int index) {
    if(this->isEmpty()) {
        throw std::out_of_range("LinkedList::get(int index) : list is empty"); 
    }
    Node* tmp = this->root;
    for(int i = 0; i < index; ++i) {
        tmp = tmp->next;
        if(tmp == nullptr) {
            throw std::out_of_range("LinkedList::get(int index) : index out of range");
        }
    }
    return tmp->value;
}

int LinkedList::getFirst() {
    if(this->root == nullptr) {
        throw std::out_of_range("LinkedList::getFirst() : list is empty");
    }
    return this->root->value;
}

int LinkedList::getLast() {
    if(this->root == nullptr) {
        throw std::out_of_range("LinkedList::getLast() : list is empty");
    }
    Node* tmp = this->root;
    while(tmp->next != nullptr) {
        tmp = tmp->next;
    }
    return tmp->value;
}

void LinkedList::add(int value) {
    if(this->isEmpty()) {
        this->root = new Node(value);
        ++this->size;
    }
    Node* tmp = this->root;
    while(tmp->next != nullptr) {
        tmp = tmp->next;
    }
    tmp->next = new Node(value);
    ++this->size;
}

void LinkedList::addFirst(int value) {
    Node* tmp = new Node(value, this->root);
    this->root = tmp;
    ++this->size;
}

void LinkedList::addAfter(int key, int toAdd) {
    if(this->isEmpty()) {
        throw std::out_of_range("LinkedList::addAfter(int key, int toAdd) : list is empty"); 
    }
    Node* tmp = this->root;
    while(tmp->next != nullptr) {
        if(tmp->value == key) {
            Node* newNode = new Node(toAdd, tmp->next);
            tmp->next = newNode;
            ++this->size;
            return;
        }

        tmp = tmp->next;
    }
    // Key DNE
    throw std::exception();
}

void LinkedList::addBefore(int key, int toAdd) {
    if(this->isEmpty()) {
        throw std::out_of_range("LinkedList::addBefore(int key, int toAdd) : list is empty"); 
    }
    if(this->root->value == key) {
        Node* tmp = new Node(toAdd, this->root);
        this->root = tmp;
        ++this->size;
        return;
    } 

    Node* tmpPrev = this->root;
    Node* tmp = this->root->next;

    while(tmp != nullptr) {
        if(tmp->value == key) {
            tmpPrev->next = new Node(toAdd, tmp);
            ++this->size;
            return;
        }
        tmpPrev = tmp;
        tmp = tmp->next;
    }
    // Key DNE
    throw std::exception();
}

void LinkedList::remove(int value) {
    if(isEmpty()) {
        return;
    }

    Node* tmp = root;

    if(tmp->next == nullptr && tmp->value == value) {
        delete this->root;
        this->root = nullptr;
        this->size = 0;
    }

    while(tmp->next != nullptr) {
        if(tmp->next->value == value) {
            Node* deadPtr = tmp->next;
            tmp->next = tmp->next->next;
            delete deadPtr;
            return;
        }

        tmp = tmp->next;
    }
}

int LinkedList::length() {
    return this->size;
}