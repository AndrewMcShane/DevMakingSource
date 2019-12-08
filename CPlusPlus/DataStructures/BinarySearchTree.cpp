#include <stdio.h>

class Node {
    public:
        Node(int value)
            : value(value), left(nullptr), right(nullptr)
        {}
        int value;
        Node* right;
        Node* left;
};

class BinarySearchTree {
    public:
        BinarySearchTree()
            : root(nullptr)
        {}
        ~BinarySearchTree();
        bool isEmpty();
        void add(int value);
        bool contains(int value);
        Node* find(int value);
        void remove(int value);
        void clear();
    
    private:
        void addAux(int value, Node* treeNode);
        bool containsAux(int value, Node* treeNode);
        Node* findAux(int value, Node* treeNode);
        Node* removeAux(int value, Node* treeNode);
        Node* getLeftMost(Node* treeNode);
        void freeHelper(Node* treeNode);
        Node* root;
};

BinarySearchTree::~BinarySearchTree() {
    this->freeHelper(this->root);
    this->root = nullptr;
}

void BinarySearchTree::freeHelper(Node* treeNode) {
    if(treeNode == nullptr) {
        return;
    }
    if(treeNode->left != nullptr) {
        this->freeHelper(treeNode->left);
    }
    if(treeNode->right != nullptr) {
        this->freeHelper(treeNode->right);
    }
     
    delete treeNode;
}

bool BinarySearchTree::isEmpty() {
    return this->root == nullptr;
}

void BinarySearchTree::add(int value) {
    if(this->root == nullptr) {
        root = new Node(value);
        return;
    }

    this->addAux(value, root);
}


void BinarySearchTree::addAux(int value, Node* treeNode) {
    if(value > treeNode->value) {
        if(treeNode->right != nullptr) this->addAux(value, treeNode->right);
        else treeNode->right = new Node(value);
    }
    else if(value < treeNode->value) {
        if(treeNode->left != nullptr) this->addAux(value, treeNode->left);
        else treeNode->left = new Node(value);
    }
    else {
        return;
    }
}

bool BinarySearchTree::contains(int value) {
    return this->containsAux(value, this->root);
}

bool BinarySearchTree::containsAux(int value, Node* treeNode) {
    if(treeNode == nullptr) return false;

    if(value > treeNode->value) return this->containsAux(value, treeNode->right);
    else if(value < treeNode->value) return this->containsAux(value, treeNode->left);
    else return true;
}

Node* BinarySearchTree::find(int value) {
    return this->findAux(value, this->root);
}

Node* BinarySearchTree::findAux(int value, Node* treeNode) {
    if(treeNode == nullptr) return nullptr;

    if(value > treeNode->value) return this->findAux(value, treeNode->right);
    else if(value < treeNode->value) return this->findAux(value, treeNode->left);
    else return treeNode;
}

void BinarySearchTree::remove(int value) {
    root = this->removeAux(value, root);
}

Node* BinarySearchTree::removeAux(int value, Node* treeNode) {
    if(treeNode == nullptr) return nullptr;

    if(value > treeNode->value) {
        treeNode->right = this->removeAux(value, treeNode->right);
    }
    else if(value < treeNode->value) {
        treeNode->left = this->removeAux(value, treeNode->left);
    }
    else {
        if(treeNode->left != nullptr && treeNode->right != nullptr) {
            Node* tmp = treeNode;
            Node* leftMost = this->getLeftMost(tmp->right);
            treeNode->value = leftMost->value;

            treeNode->right = this->removeAux(leftMost->value, treeNode->right);
        }
        else if(treeNode->left != nullptr) {
            Node* deadPtr = treeNode;
            treeNode = treeNode->left;
            delete deadPtr;
        }
        else if(treeNode->right != nullptr) {
            Node* deadPtr = treeNode;
            treeNode = treeNode->right;
            delete deadPtr;
        }
        else {
            delete treeNode;
            treeNode = nullptr;
        }
    }
    return treeNode;
}

Node* BinarySearchTree::getLeftMost(Node* treeNode) {
    if(treeNode == nullptr) return nullptr;
    else if(treeNode->left == nullptr) return treeNode;
    else return this->getLeftMost(treeNode->left);
}
