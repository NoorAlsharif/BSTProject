/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreesPackage;
// BSTdemo.java
// Author: Dr. Jonathan Cazalas
// 6/12/2013
// Program to test the intBST (int Binary Search Tree) class
import java.util.Scanner;

public class BSTdemo {

    public static void main(String[] args) {
        // Make Scanner
        Scanner input = new Scanner(System.in);

        // Other variables
        int choice; // user choice
        int value;  // value to insert, delete, or search for

        // Make a new BST called myTree
        intBST myTree = new intBST();

        // Do/while loop showing menu, getting user choice, and performing actions
        do {
            // Show menu and get user choice
            showMenu();
            choice = input.nextInt();

            // INSERT new node into tree
            if (choice == 1) {
                System.out.print(">    What value do you want to insert: ");
                value = input.nextInt();

                // First, check to see if value already exists in tree. If so, give error message.
                if (myTree.search(value)) {
                    System.out.println(">    " + value + " already exists in the tree. Duplicates are not allowed.");
                } else {
                    myTree.insert(value);
                    System.out.println(">    " + value + " was successfully inserted into the tree.");
                }
                System.out.println();
            } // DELETE node from tree
            else if (choice == 2) {
                System.out.print(">    What value do you want to delete: ");
                value = input.nextInt();

                // First, check to see if value is actually in the tree.
                // IF it is in the tree, delete it.
                if (myTree.search(value)) {
                    myTree.delete(value);
                    System.out.println(">    " + value + " was successfully deleted from the tree.");
                } // ELSE, print message stating that no delete is needed (since value is not in the tree)
                else {
                    System.out.println(">    " + value + " is not in the tree (no delete needed).");
                }
                System.out.println();
            } else if (choice == 3) {
                System.out.print(">    What value do you want to search for: ");
                value = input.nextInt();
                if (myTree.search(value)) {
                    System.out.println(">    " + value + " was found in the tree.");
                } else {
                    System.out.println(">    " + value + " was not found in the tree.");
                }
                System.out.println();
            } else if (choice == 4) {
                System.out.print(">    Which node do you want to find the parent of: ");
                value = input.nextInt();
                // First make sure the value exists in the tree
                if (myTree.search(value)) {
                    BSTnode temp, par;
                    // find the actual node that value is in, and then save into temp
                    temp = myTree.findNode(value);
                    // Now find the parent of temp
                    par = myTree.parent(temp);

                    // IF par is null, this means that temp was the root and has no parent
                    if (par == null) {
                        System.out.println(">    " + value + " is the root of the tree (has no parent).");
                    } else {
                        System.out.println(">    The parent of " + value + " is " + par.getData() + ".");
                    }
                } else {
                    System.out.println(">    " + value + " was not found in the tree.");
                }
                System.out.println();
            } else if (choice == 5) {
                if (myTree.isEmpty()) {
                    System.out.println(">    Error: cannot perform sum (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println(">    The sum of all nodes is " + myTree.sumNodes() + ".");
                    System.out.println();
                }
            } else if (choice == 6) {
                if (myTree.isEmpty()) {
                    System.out.println(">    Error: cannot print nodes (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println(">    Inorder Traversal of nodes:");
                    System.out.print(">    ");
                    myTree.inorder();
                    System.out.println();
                    System.out.println();
                }
            } else if (choice == 7) {
                if (myTree.isEmpty()) {
                    System.out.println(">    Error: cannot print nodes (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println(">Height of tree is :" + myTree.heightBST());
                    System.out.print(">    ");
                    System.out.println();
                    System.out.println();
                }
            } else if (choice == 8) {
                if (myTree.isEmpty()) {
                    System.out.println(">    Error: cannot print nodes (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println(">Postorder traversal of nodes is :");
                    System.out.print(">    ");
                    myTree.postorder();
                    System.out.println();
                    System.out.println();
                }
            } else if (choice == 9) {
                if (myTree.isEmpty()) {
                    System.out.println(">    Error: cannot print nodes (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println(">Nodes of tree in descending order:");
                    System.out.print(">    ");
                    myTree.descendingorder();
                    System.out.println();
                    System.out.println();
                }
            } else if (choice == 10) {
                if (myTree.isEmpty()) {
                    System.out.println(">    Error: cannot print nodes (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println(">Number of nodes divisible by 7 is: " + myTree.count7());
                    System.out.print(">    ");
                    System.out.println();
                    System.out.println();
                }
            } else if (choice == 11) {
                if (myTree.isEmpty()) {
                    System.out.println(">    Error: cannot print nodes (the tree is empty)");
                    System.out.println();
                } else {
                    System.out.println(">What value you want tot add in all nodes?");
                    myTree.modifyAllNodes(input.nextInt());
                    System.out.println(">All nodes of the tree have been successfully modified");
                    System.out.println();
                    System.out.println();
                }
            } else if (choice == 12) {
                System.out.println(">    Goodbye!");
                System.out.println();
            } else {
                System.out.println(">    Wrong selection. Try again.");
                System.out.println();
            }

        } while (choice != 12);

    }

    public static void showMenu() {
        System.out.println("|-----------------------------------------------|");
        System.out.println("|------      Binary Search Tree Menu      ------|");
        System.out.println("|-----------------------------------------------|");
        System.out.println("|   1. Insert an item into the tree             |");
        System.out.println("|   2. Delete an item from the tree             |");
        System.out.println("|   3. Search for an item in the tree           |");
        System.out.println("|   4. Find the parent of some node             |");
        System.out.println("|   5. Print the sum of all data values         |");
        System.out.println("|   6. Print an inorder traversal of the tree   |");
        System.out.println("|   7. Print height of the tree                 |");
        System.out.println("|   8. Print postorder traversal of the tree    |");
        System.out.println("|   9. Print nodes in descending order          |");
        System.out.println("|   10. Count nodes which are divisible by 7    |");
        System.out.println("|   11. Modify all nodes by adding some value   |");
        System.out.println("|   12. Quit                                    |");
        System.out.println("|-----------------------------------------------|");
        System.out.println();
        System.out.print("> Please enter your choice: ");
    }
}
