<?php

class BinarySearchTree {
    private $root;

    public function isEmpty() {
        return $this->root == null;
    }
    // Insert a new element into the BST.
    public function add($value) {
        if($this->root == null) {
            $this->root = new Node($value, null, null);
            return;
        }
        $this->addAux($value, $this->root);
    }

    // Private helper method for the add function.
    private function addAux($value, $treeNode) {
        // If the value is greater, go to the right subtree.
        if($value > $treeNode->value) {
            if($treeNode->right != null) $this->addAux($value, $treeNode->right);
            else $treeNode->right = new Node($value, null, null);
            
        }
        // Otherwise attempt the left subtree.
        elseif($value < $treeNode->value) {
            if($treeNode->left != null) $this->addAux($value, $treeNode->left);
            else $treeNode->left = new Node($value, null, null);

        }
        // If equal, do nothing.
        
    }

    // If the BST contains a specific value.
    public function contains($value){
        if($this->root == null) return false;
        else return $this->containsAux($value, $this->root);
    }

    // Helper method for finding if a BST contains a value.
    private function containsAux($value, $treeNode) {
        if($treeNode == null) return false;

        if($value > $treeNode->value) return $this->containsAux($value, $treeNode->right);
        elseif ($value < $treeNode->value) return $this->containsAux($value, $treeNode->left);
        else return true;
    }

    // Like contains, finds a value in the BST and returns it.
    public function find($value) {
        if($this->root == null) return null;
        else return $this->findAux($value, $this->root);
    }

    // Helper method for the find function.
    private function findAux($value, $treeNode) {
        if($treeNode == null) return null;

        if($value > $treeNode->value) return $this->findAux($value, $treeNode->right);
        elseif($value < $treeNode->value) return $this->findAux($value, $treeNode->left);
        else return $treeNode;
    }

    // Remove a value from a BST.
    public function remove($value) {
        if($this->root == null) return;
        else $this->root = $this->removeAux($value, $this->root);
    }

    // Helper method for the remove function that does most of the heavy lifting.
    private function removeAux($value, $treeNode) {
        if($treeNode == null) return null;

        // Traversal start.
        if($value > $treeNode->value) {
            $treeNode->right = $this->removeAux($value, $treeNode->right);
        }
        elseif($value < $treeNode->value) {
            $treeNode->left = $this->removeAux($value, $treeNode->left);
        }
        // Value found, Now remove;
        else {
            // If both subtrees are not null, 
            // find the smallest element int the right subtree.
            if($treeNode->left != null && $treeNode->right != null) {
                $tmp = $treeNode;
                $leftMost = $this->getLeftMost($tmp->right);

                $treeNode->value = $leftMost->value;
                $treeNode->right = $this->removeAux($leftMost->value, $treeNode->right);
            }
            // If the left subtree isn't null (and the right is), set the left.
            elseif ($treeNode->left != null) {
                $treeNode = $treeNode->left;
            }
            // Else if the right subtree isn't null and the left is.
            elseif ($treeNode->right != null) {
                $treeNode = $treeNode->right;
            }
            // Else no subtrees exist and this node can be set to null.
            else {
                $treeNode = null;
            }
        }
        return $treeNode;
    }

    // Helper method to get the left-most node of a subtree.
    private function getLeftMost($treeNode) {
        if($treeNode == null) return null;
        elseif ($treeNode->left == null) return $treeNode;
        else return $this->getLeftMost($treeNode->left);
    }

    // Clear out the BST.
    public function clear() {
        $this->root = null;
    }

    // Convert the BST to a string.
    public function __toString() {
        $res = "{ " . $this->toStringAux($this->root) . "}";
        return $res;
    }

    // Helper method for the toString function.
    private function toStringAux($treeNode) {
        $res = "";
        
        if(!$treeNode) {
            return "";
        }
        
        if($treeNode->left != null) {
            $res = $res . $this->toStringAux($treeNode->left);
        }
        $res .= (string) $treeNode->value . " ";
        if($treeNode->right != null) {
            $res .= $this->toStringAux($treeNode->right);
        }

        return $res;
    }
    
}

// Node class for the BST that contains a value and a left + right subtree.
class Node {
    
    public $value;
    public $right;
    public $left;

    public function __construct($val, $rightNode, $leftNode){   
        $this->value = $val;
        $this->right = $rightNode;
        $this->left = $leftNode;
    }
}

/* Example usage of the BST */
$bst = new BinarySearchTree();
$bst->add(5);
$bst->add(3);
$bst->add(7);
$bst->add(1);
$bst->add(4);
echo $bst->toString() . "<br>";
$bst->remove(3) . "<br>";
echo $bst->toString() . "<br>";
