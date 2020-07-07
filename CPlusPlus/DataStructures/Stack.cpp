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

class Stack {
    public:
        Stack()
            :size(0), root(nullptr)
        {}
        ~Stack();
        bool isEmpty();
        int peek();
        void push(int value);
        int pop();
    private:
        Node* root;
        int size;
};

Stack::~Stack() {
    Node* tmp = this->root;
    this->size = 0;
    while(tmp != nullptr) {
        Node* deadPtr = tmp;
        tmp = tmp->next;
        delete deadPtr;
    }
    
    this->root = nullptr;
}

bool Stack::isEmpty() {
    return size == 0;
}

int Stack::peek() {
    if(this->isEmpty()) {
        throw std::exception();
    }
    return this->root->value;
}

void Stack::push(int value) {
    if(this->isEmpty()) {
        this->root = new Node(value);
        ++this->size;
    }
    else {
        Node* tmp = new Node(value, this->root);
        this->root = tmp;
        ++this->size;
    }
}

int Stack::pop() {
    if(this->isEmpty()) {
        throw std::exception();
    }
    if(this->size == 1) {
        int tmpVal = this->root->value;
        Node* tmp = this->root;
        this->root = nullptr;
        this->size = 0;
        delete tmp;
        return tmpVal;
    }
    else {
        Node* tmp = this->root;
        int tmpVal = tmp->value;
        this->root = this->root->next;
        --this->size;
        delete tmp;
        return tmpVal;
    }
}