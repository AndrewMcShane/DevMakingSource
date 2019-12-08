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

class Queue {
    public:
        Queue()
            :size(0), head(nullptr), tail(nullptr)
        {}
        ~Queue();
        bool isEmpty();
        int peek();
        void enqueue(int value);
        int dequeue();
    private:
        Node* head;
        Node* tail;
        int size;
};

Queue::~Queue() {
    Node* tmp = this->head;
    this->tail = nullptr;
    this->size = 0;
    while(tmp != nullptr) {
        Node* deadPtr = tmp;
        tmp = tmp->next;
        delete deadPtr;
    }
    
    this->head = nullptr;
}

bool Queue::isEmpty() {
    return size == 0;
}

int Queue::peek() {
    if(this->isEmpty()) {
        throw std::exception();
    }
    return this->head->value;
}

void Queue::enqueue(int value) {
    if(this->isEmpty()) {
        this->tail = new Node(value);
        this->head = this->tail;
        ++this->size;
    }
    else {
        this->tail->next = new Node(value);
        this->tail = this->tail->next;
        ++this->size;
    }
}

int Queue::dequeue() {
    if(this->isEmpty()) {
        throw std::exception();
    }
    if(this->size == 1) {
        int tmpVal = this->head->value;
        Node* tmp = this->head;
        this->head = nullptr;
        this->tail = nullptr;
        this->size = 0;
        delete tmp;
        return tmpVal;
    }
    else {
        Node* tmp = this->head;
        int tmpVal = tmp->value;
        this->head = this->head->next;
        --this->size;
        delete tmp;
        return tmpVal;
    }
}
