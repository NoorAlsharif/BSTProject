/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreesPackage;
// intBST.java
// Author: Dr. Jonathan Cazalas
// 6/12/2013
// Class from which we can create fully functional BSTs
public class intBST {

    private BSTnode root;

    // CONSTRUCTORS
    public intBST() {
        root = null;
    }

    /* Below are MANY methods that are used on Binary Search Trees.
	 * 
	 * Examples:
	 * search, insert, delete, isEmpty, minVal, maxVal, inorder, sumNodes, and many more
     */
    //
    // boolean | isEmpty()
    //
    public boolean isEmpty() {
        return root == null;
    }

    //
    // boolean | search(int)
    //
    public boolean search(int data) {
        return search(root, data);
    }
    //
    // boolean | search(BSTnode, int)
    //

    private boolean search(BSTnode p, int data) {
        if (p == null) {
            return false;
        } else {
            // if the data we are searching for is found at p (at the current root)
            if (data == p.getData()) {
                return true;
            } else if (data < p.getData()) {
                return search(p.getLeft(), data);
            } else {
                return search(p.getRight(), data);
            }
        }
    }

    //
    // void | insert(int)
    //
    public void insert(int data) {
        BSTnode newNode = new BSTnode(data);
        root = insert(root, newNode);
    }
    //
    // BSTnode | insert(BSTnode, BSTnode)
    //

    private BSTnode insert(BSTnode p, BSTnode newNode) {
        // IF there is no tree, newNode will be the root, so just return it
        if (p == null) {
            return newNode;
        } // ELSE, we have a tree. Insert to the right or the left
        else {
            // Insert to the RIGHT of root
            if (newNode.getData() > p.getData()) {
                // Recursively insert into the right subtree
                // The result of insertion is an updated root of the right subtree
                BSTnode temp = insert(p.getRight(), newNode);
                // Save this newly updated root of right subtree into p.right
                p.setRight(temp);
            } // Insert to the LEFT of root
            else {
                // Recursively insert into the left subtree
                // The result of insertion is an updated root of the left subtree
                BSTnode temp = insert(p.getLeft(), newNode);
                // Save this newly updated root of left subtree into p.left
                p.setLeft(temp);
            }
        }
        // Return root of updated tree
        return p;
    }

    //
    // void | inorder()
    //
    public void inorder() {
        inorder(root);
    }
    //
    // void | inorder(BSTnode)
    //

    private void inorder(BSTnode p) {
        if (p != null) {
            inorder(p.getLeft());
            System.out.print(p.getData() + ", ");
            inorder(p.getRight());
        }
    }

    //
    // int | sumNodes()
    //
    public int sumNodes() {
        return sumNodes(root);
    }
    //
    // int | sumNodes(BSTnode)
    //

    private int sumNodes(BSTnode p) {
        if (p != null) {
            return p.getData() + sumNodes(p.getLeft()) + sumNodes(p.getRight());
        } else {
            return 0;
        }
    }

    //
    // void | delete(int)
    //
    public void delete(int data) {
        root = delete(root, data);
    }
    //
    // BSTnode | delete(BSTnode, int)
    //

    private BSTnode delete(BSTnode p, int data) {
        BSTnode node2delete, newnode2delete, node2save, parent;
        int saveValue;

        // Step 1: Find the p we want to delete
        node2delete = findNode(p, data);
        // If p is not found (does not exist in tree), we clearly cannot delete it.
        if (node2delete == null) {
            return root;
        }

        // Step 2: Find the parent of the p we want to delete
        parent = parent(p, node2delete);

        // Step 3: Perform Deletion based on three possibilities
        // **1** :  node2delete is a leaf p
        if (isLeaf(node2delete)) {
            // if parent is null, this means that node2delete is the ONLY p in the tree
            if (parent == null) {
                return null; // we return null as the updated root of the tree
            }
            // Delete p if it is a left child
            if (data < parent.getData()) {
                parent.setLeft(null);
            } // Delete p if it is a right child
            else {
                parent.setRight(null);
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **2** : node2delete has only a left child
        if (hasOnlyLeftChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getLeft();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some p
            // IF it is the left child of some p
            if (data < parent.getData()) {
                parent.setLeft(parent.getLeft().getLeft());
            } // ELSE it is the right child of some p
            else {
                parent.setRight(parent.getRight().getLeft());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **3** :  node2delete has only a right child
        if (hasOnlyRightChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getRight();
            }

            // If node2delete is not the root,
            // it must the left OR the right child of some p
            // IF it is the left child of some p
            if (data < parent.getData()) {
                parent.setLeft(parent.getLeft().getRight());
            } // ELSE it is the right child of some p
            else {
                parent.setRight(parent.getRight().getRight());
            }

            // Finally, return the root of the tree (in case the root got updated)
            return p;
        }

        // **4** :  node2delete has TWO children.
        // Remember, we have two choices: the minVal from the right subtree (of the deleted p)
        // or the maxVal from the left subtree (of the deleted p)
        // We choose to find the minVal from the right subtree.
        newnode2delete = minNode(node2delete.getRight());
        // Now we need to temporarily save the data value(s) from this p
        saveValue = newnode2delete.getData();

        // Now, we recursively call our delete method to actually delete this p that we just copied the data from
        p = delete(p, saveValue);

        // Now, put the saved data (in saveValue) into the correct place (the original p we wanted to delete)
        node2delete.setData(saveValue);

        // Finally, return the root of the tree (in case the root got updated)
        return p;
    }

    //
    // BSTnode | minNode(BSTnode)
    //
    public BSTnode minNode(BSTnode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getLeft() == null) {
                return root;
            } else {
                return minNode(root.getLeft());
            }
        }
    }

    //
    // BSTnode | maxNode(BSTnode)
    //
    public BSTnode maxNode(BSTnode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getRight() == null) {
                return root;
            } else {
                return maxNode(root.getRight());
            }
        }
    }

    //
    // BSTnode | findNode(int)
    //
    public BSTnode findNode(int data) {
        return findNode(root, data);
    }
    //
    // BSTnode | findNode(BSTnode, int)
    //

    private BSTnode findNode(BSTnode p, int data) {
        if (p == null) {
            return null;
        } else {
            // if the data we are searching for is found at p (at the current root)
            if (data == p.getData()) {
                return p;
            } else if (data < p.getData()) {
                return findNode(p.getLeft(), data);
            } else {
                return findNode(p.getRight(), data);
            }
        }
    }

    //
    // BSTnode | parent(BSTnode)
    //
    public BSTnode parent(BSTnode p) {
        return parent(root, p);
    }
    //
    // BSTnode | parent(BSTnode, BSTnode)
    //

    private BSTnode parent(BSTnode root, BSTnode p) {
        // Take care of NULL cases
        if (root == null || root == p) {
            return null; // because there is on parent
        }
        // If root is the actual parent of p p
        if (root.getLeft() == p || root.getRight() == p) {
            return root; // because root is the parent of p
        }
        // Look for p's parent in the left side of root
        if (p.getData() < root.getData()) {
            return parent(root.getLeft(), p);
        } // Look for p's parent in the right side of root
        else if (p.getData() > root.getData()) {
            return parent(root.getRight(), p);
        } // Take care of any other cases
        else {
            return null;
        }
    }

    //
    // boolean | isLeaf(BSTnode)
    //
    public boolean isLeaf(BSTnode p) {
        return (p.getLeft() == null && p.getRight() == null);
    }

    //
    // boolean | hasOnlyLeftChild(BSTnode)
    //
    public boolean hasOnlyLeftChild(BSTnode p) {
        return (p.getLeft() != null && p.getRight() == null);
    }

    //
    // boolean | hasOnlyRightChild(BSTnode)
    //
    public boolean hasOnlyRightChild(BSTnode p) {
        return (p.getLeft() == null && p.getRight() != null);
    }

    //print height of tree
    public int heightBST() {
        return heightBST(root);
    }

    private int heightBST(BSTnode p) {
        int leftHeight, rightHeight;
        if (p == null) {
            return 0;
        }
        leftHeight = heightBST(p.getLeft());
        rightHeight = heightBST(p.getRight());
        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        }
        return rightHeight + 1;
    }

    //print nodes in postorder
    public void postorder() {
        postorder(root);
    }

    private void postorder(BSTnode p) {
        if (p != null) {
            postorder(p.getLeft());
            postorder(p.getRight());
            //Visit the p by Printing the p data     
            System.out.print(p.getData() + ", ");
        }
    }

    //print node from the largest to smallest 
    public void descendingorder() {
        descendingorder(root);
    }

    private void descendingorder(BSTnode p) {
        if (p != null) {
            if (p.getRight() != null) {
                descendingorder(p.getRight());
            }
            System.out.print(p.getData() + ", ");
            if (p.getLeft() != null) {
                descendingorder(p.getLeft());
            }
        }
    }

    //get number of nodes that able to divide by 7 
    public int count7() {
        return count7(root);
    }

    private int count7(BSTnode p) {
        if (p == null) {
            return 0;
        }
        int count = (p.getData() % 7 == 0) ? 1 : 0;
        return count + count7(p.getLeft()) + count7(p.getRight());

    }

    //modify all nodes 
    public void modifyAllNodes(int val) {
        modifyAllNodes(root, val);
    }

    private void modifyAllNodes(BSTnode p, int val) {
        if (p == null) {
            return;
        }
        p.setData(p.getData() + val);
        modifyAllNodes(p.getLeft(), val);
        modifyAllNodes(p.getRight(), val);

    }

}
