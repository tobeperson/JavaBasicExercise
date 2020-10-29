package com.company.tobeace;

import java.util.*;

/**
 *(一)二叉树练习
 * 能够运用递归方法解决树的为前序遍历、中序遍历和后序遍历问题
 * 能用运用迭代方法解决树的为前序遍历、中序遍历和后序遍历问题
 * 能用运用广度优先搜索解决树的层序遍历问题
 *
 * 作者：To_beace
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class BinaryTreeDemo {
    public static class BinaryTree{
        public Character data;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(Character data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        public void makeRightSubTree(Character data){
            this.right=new BinaryTree(data);
        }
        public void makeLeftSubTree(Character data){
            this.left=new BinaryTree(data);
        }
        public void makeSubTree(Character leftdata,Character rightdata){
            this.left=new BinaryTree(leftdata);
            this.right=new BinaryTree(rightdata);
        }
        public void preOrderTraversal(){
            System.out.print("["+this.data+"]");
            if (this.left!=null){
                this.left.preOrderTraversal();
            }
            if (this.right!=null){
                this.right.preOrderTraversal();
            }
        }
        public void inOrderTraversal(){
            if (this.left!=null){
                this.left.inOrderTraversal();
            }
            System.out.print("["+this.data+"]");
            if (this.right!=null){
                this.right.inOrderTraversal();
            }
        }
        public void postOrderTraversal(){
            if (this.left!=null){
                this.left.postOrderTraversal();
            }
            if (this.right!=null){
                this.right.postOrderTraversal();
            }
            System.out.print("["+this.data+"]");
        }

        public BinaryTree getLeftSubTree() {
            return this.left;
        }

        public BinaryTree getRightSubTree() {
            return this.right;
        }
        /**
         * 不停压栈，走到最左边。弹出，走右边的。
         * */
        public static List<Character>  inOrderTraversalIteration(BinaryTree root){
            Stack<BinaryTree> stack =new Stack<>();
            BinaryTree node=root;
            List res=new ArrayList<>();
            while(node!=null || !stack.isEmpty()){
                while(node!=null){
                    stack.push(node);
                    node=node.left;
                }
                node=stack.pop();
                res.add(node.data);
                node=node.right;
            }
            return res;
        }
        /**
         * 访问，走左边，不为空就弹出
         */

        public static List<Character> preOrderTraversalIteration(BinaryTree root){
            Stack <BinaryTree> stack=new Stack<>();
            List<Character> res=new ArrayList<>();
            BinaryTree node =root;
            while(node != null || ! stack.isEmpty()){
                while (node!=null) {
                    stack.push(node);
                    res.add(node.data);
                    node=node.left;
                }
                if (!stack.isEmpty()) {
                    node =stack.pop();
                    node=node.right;
                }
            }
            return res;
        }
        public static List<Character> postOrderTraversalIteration(BinaryTree root) {
            Stack <BinaryTree> stack1=new Stack<>();
            Stack <BinaryTree> stack2=new Stack<>();
            List<Character> res=new ArrayList<>();
            if(root!=null) { stack1.push(root); } else{ return res; }
            while(!stack1.isEmpty()){
                BinaryTree node =stack1.pop();
                stack2.push(node);
                if(node.left!=null){
                    stack1.push(node.left);
                }
                if (node.right!=null){
                    stack1.push(node.right);
                }
            }
            while(!stack2.isEmpty()){
                res.add(stack2.pop().data);
            }
            return res;
        }
        public static List<Character> treeLevelTraversal(BinaryTree root){
            Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
            queue.offer(root);
            List<Character>res=new ArrayList<>();
            int level=0;
            while(queue.size()!=0){
                int num=queue.size();
                for (int i = 0; i < num; i++) {
                    BinaryTree node = queue.poll();
                    assert node != null;
                    res.add(node.data);
                    if (node.left!=null)    queue.offer(node.left);
                    if (node.right!=null)   queue.offer(node.right);
                }
                level++;
            }
            return res;
        }
        public static int treeMaxDepth(BinaryTree root,int depth){
            return Math.max(root.left!=null ? treeMaxDepth(root.left,depth+1):depth, root.right!=null ? treeMaxDepth(root.right,depth+1):depth);
        }

    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree('-');
        root.makeLeftSubTree('+');
        root.makeRightSubTree('/');
        root.getLeftSubTree().makeLeftSubTree('a');
        root.getLeftSubTree().makeRightSubTree('*');
        root.getRightSubTree().makeLeftSubTree('e');
        root.getRightSubTree().makeRightSubTree('f');
        root.getLeftSubTree().getRightSubTree().makeSubTree('b', '-');
        root.getLeftSubTree().getRightSubTree().getRightSubTree().makeSubTree('c', 'd');

        System.out.print("前序遍历：");
        root.preOrderTraversal();
        System.out.print("\n中序遍历：");
        root.inOrderTraversal();
        System.out.print("\n后序遍历：");
        root.postOrderTraversal();
        System.out.println("\n");

        System.out.print("前序遍历：");
        List<Character> res= BinaryTree.preOrderTraversalIteration(root);
        for (Character re : res) {
            System.out.print("[" + re + "]");
        }
        System.out.print("\n中序遍历：");
        res= BinaryTree.inOrderTraversalIteration(root);
        for (Character re : res) {
            System.out.print("[" + re + "]");
        }
        System.out.print("\n后序遍历：");
        res= BinaryTree.postOrderTraversalIteration(root);
        for (Character re : res) {
            System.out.print("[" + re + "]");
        }
        System.out.print("\n层次遍历：");
        res=BinaryTree.treeLevelTraversal(root);
        for (Character re : res) {
            System.out.print("[" + re + "]");
        }
        System.out.println("\n树的深度"+BinaryTree.treeMaxDepth(root,0));
    }

}
