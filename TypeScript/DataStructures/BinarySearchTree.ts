class BSTNode
{
    public value: number;
    public right: BSTNode;
    public left: BSTNode;
}

class BinarySearchTree
{
    private root: BSTNode;

    public isEmpty(): boolean
    {
        return this.root == null;
    }   

    public add(value: number)
    {
        if(this.isEmpty())
        {
            this.root = new BSTNode();
            this.root.value = value;
        }
        else
        {
            this.addAux(value, this.root);
        }
    }
    
    // Helper method for add:
    private addAux(value: number, treeNode: BSTNode)
    {
        if(value > treeNode.value)
        {
            if(treeNode.right != null)
            {
                this.addAux(value, treeNode.right);
            }
            else
            {
                treeNode.right = new BSTNode();
                treeNode.right.value = value;
            }
        }
        else if(value < treeNode.value)
        {
            if(treeNode.left != null)
            {
                this.addAux(value, treeNode.left);
            }
            else
            {
                treeNode.left = new BSTNode();
                treeNode.left.value = value;
            }
        }
        // Equal: do nothing. (you could also throw.)
    }

    public contains(value: number): boolean
    {
        if(this.isEmpty()) return false;
        return this.containsAux(value, this.root);
    }

    private containsAux(value: number, treeNode: BSTNode): boolean
    {
        if(treeNode == null) return false;

        if(value > treeNode.value)
        {
            return this.containsAux(value, treeNode.right);
        }
        else if (value < treeNode.value)
        {
            return this.containsAux(value, treeNode.left);
        }
        else
        {
            return true;
        }
    }

    public remove(value: number)
    {
        if(this.root == null) return;
        this.root = this.removeAux(value, this.root);
    }

    private removeAux(value: number, treeNode: BSTNode): BSTNode
    {
        if(treeNode == null)
        {
            return null;
        }

        if(value > treeNode.value)
        {
            treeNode.right = this.removeAux(value, treeNode.right);
        }
        else if(value < treeNode.value)
        {
            treeNode.left = this.removeAux(value, treeNode.left);
        }
        else
        {
            if(treeNode.left != null && treeNode.right != null)
            {
                const tmp = treeNode;
                const leftMost = this.getLeftMost(tmp.right);
                treeNode.value = leftMost.value;
                treeNode.right = this.removeAux(leftMost.value, treeNode.right);
            }
            else if(treeNode.left != null)
            {
                treeNode = treeNode.left;
            }
            else if(treeNode.right != null)
            {
                treeNode = treeNode.right;
            }
            else
            {
                treeNode = null;
            }
        }

        return treeNode;
    }

    private getLeftMost(treeNode: BSTNode): BSTNode
    {
        if(treeNode == null) return null;
        let tmp = treeNode;
        while(tmp.left != null)
        {
            tmp = tmp.left;
        }
        return tmp;
    }
    
}