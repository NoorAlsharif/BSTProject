/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TreesPackage;
// BSTnode.java
// Author: Dr. Jonathan Cazalas
// 6/12/2013
// Class from which we can BST node objects

public class BSTnode {

    private int data;
    private BSTnode left;
    private BSTnode right;

    // CONSTRUCTORS
    public BSTnode() {
        data = 0;
        left = right = null;
    }

    public BSTnode(int data) {
        this.data = data;
        left = right = null;
    }

    public BSTnode(int data, BSTnode left, BSTnode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // ACCESSORS
    public int getData() {
        return data;
    }

    public BSTnode getLeft() {
        return left;
    }

    public BSTnode getRight() {
        return right;
    }

    // MUTATORS
    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(BSTnode left) {
        this.left = left;
    }

    public void setRight(BSTnode right) {
        this.right = right;
    }
}
