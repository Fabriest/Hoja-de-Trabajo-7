public class BinaryTree<E extends Comparable<E>>{
    private class Node{
        E data;
        Node left;
        Node right;

        Node(E data){
            this.data = data;
            this.left = null;
            this.right = null;

        }
    }
    private Node root;

    public BinaryTree(){
        root = null;
    }

    public void insert(E data){
        root = insertNode(root, data);
    }

    private Node insertNode(Node node, E data){
        if (node==null){
            return new Node(data);
        }
        int cmp = data.compareTo(node.data);

        if (cmp<0){
            node.left = insertNode(node.left, data);
        }
        else if (cmp>0){
            node.right=insertNode(node.right, data);
        }
        return node;
    }
    public E search(E data){
        return searchNode(root, data);
    }

    private E searchNode(Node node, E data){
        if (node == null){
            return null;
        } 
        int cmp = data.compareTo(node.data);

        if (cmp<0){
            return searchNode(node.left, data);
        }
        else if (cmp>0){
            return searchNode(node.right, data);
        }
        else {
            return node.data;
        }
    }

    public void inOrder(){
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node){
        if (node == null){
            return;
        }
        inOrderTraversal(node.left);
        System.out.print(node.data + " ");
        inOrderTraversal(node.right);
    }
}