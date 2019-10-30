/*
 * Author: shunran.li
 * Time: 10/30/19 11:08 AM
 * Email: shunran.li@guanshantech.com
 */

package com.ashun.Tree;

public class BinarySearchTree {

    private BinaryTree tree;

    public BinarySearchTree(BinaryTree tree){
        this.tree = tree;
    }

    public BinaryTree find(int data){
        BinaryTree p = this.tree;
        while (p != null){
            if(data < p.data){
                p = p.left;
            }else if(data > p.data){
                p = p.right;
            }else{
                return p;
            }
        }
        return p;
    }

    public void insert(int data){
        if(tree == null){
            tree = new BinaryTree(data);
            return;
        }
        BinaryTree p = this.tree;
        while (p != null){
            if(data < p.data){
                if(p.left == null){
                    p.left = new BinaryTree(data);
                }
                p = p.left;
            }else if(data > p.data){
                if(p.right == null){
                    p.right = new BinaryTree(data);
                }
                p = p.right;
            }else{
                return;
            }
        }
    }

    public void delete(int data){
        if(tree == null) return;

        BinaryTree p = this.tree;
        BinaryTree pp = null;

        while (p != null){
            if(data < p.data){
                pp = p;
                p = p.left;
            }else if(data > p.data){
                pp = p;
                p = p.right;
            }else{
                break;
            }
        }

        if(p == null) return;//没有找到要被删除的节点
        // 被删除的节点有三种情况
        // 1.无子节点
        // 2.有一个子节点，或左或右
        // 3.有两个子节点

        //有两个子节点时
        if(p.left != null && p.right != null){
            BinaryTree minp = p;
            BinaryTree minpp = p.right;
            while (minp.left != null){
                minpp = minp;
                minp = minp.left;
            }
            //转化为情况1、2解决
            p.data = minp.data;
            p = minp;
            pp = minpp;
        }

        //有0或1个子节点时
        BinaryTree child = null;
        if(p.left != null){
            child = p.left;
        }else if(p.right != null){
            child = p.right;
        }

        if(pp == null){
            this.tree = child;
        }else if(pp.left == p){
            pp.left = child;
        }else{
            pp.right = child;
        }

        return;
    }

    public BinaryTree findMin(){
        if(this.tree == null) return null;
        BinaryTree node = this.tree;
        while (node.left != null){
            node = node.left;
        }

        return node;
    }

    public BinaryTree findMax(){
        if(this.tree == null) return null;
        BinaryTree node = this.tree;
        while (node.right != null){
            node = node.right;
        }

        return node;
    }

    public void printCenterOrder(BinaryTree node){
        if(node == null) return;

        printCenterOrder(node.left);
        System.out.println(node.data);
        printCenterOrder(node.right);
    }

    public void printNode(BinaryTree node){
        if(node == null) return;
        System.out.println(node.data);
    }

    public static class BinaryTree {
        private int data;
        private BinaryTree left;
        private BinaryTree right;

        public BinaryTree(int data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(9);
        BinarySearchTree bsTree = new BinarySearchTree(root);
        bsTree.insert(8);
        bsTree.insert(10);
        bsTree.insert(12);
        bsTree.insert(1);
        bsTree.insert(4);
        bsTree.insert(50);
        bsTree.insert(24);
        bsTree.insert(3);
        bsTree.insert(11);

        bsTree.delete(10);

        BinaryTree max = bsTree.findMax();
        BinaryTree min = bsTree.findMin();

        bsTree.printCenterOrder(root);


        bsTree.printNode(max);
        bsTree.printNode(min);
    }
}
