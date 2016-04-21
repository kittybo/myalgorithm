package structure.tree.bintree;

/**
 * Created by Administrator on 2016-04-20.
 */
public class BinaryTree {
    //Root node pointer, will be null for an empty tree
    private Node root = null;

    /*
    * --Node--- Binary Tree is built using this nested node class.
    * 每个节点都包含三部分，数据元素、指向左子树的指针、指向右子树的指针
    * */
    private class Node {
        Node left;
        Node right;
        int data;

        Node(int nData) {
            left = null;
            right = null;
            data = nData;
        }
    }

    public BinaryTree() {
        root = null;
    }

    /*
    * Recursive lookup -- given a node, recur down searching for the given
     * data.
    */
    private boolean lookup(Node node, int data) {
        if (node == null)
            return false;
        if (data == node.data) {
            return true;
        } else if (data < node.data) {
            return lookup(node.left, data);
        } else {
            return lookup(node.right, data);
        }
    }

    /*
    *对外提供查询的接口
    * */
    public boolean lookup(int data) {
        return lookup(root, data);
    }

    public void insert(int data) {
        root = insert(root, data); //最终回退到初始节点
    }
    public void insertWithLoop(int data) {
        root = insertWithLoop(root, data);
    }
    /**
     * Recursive insert -- given a node pointer, recur down and insert the given
     * data into the tree. Returns the new node pointer (the standard way to
     * communicate a changed pointer back to the caller).
     * <p/>
     * 关键点：node为null时需要直接建立，构建node时需要指定该node的左右子树指针
     */
    private Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (node.data >= data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }
        return node;
    }

    /*尝试采用非递归方式*/
    private Node insertWithLoop(Node root, int data) {
        Node zNode = new Node(data);
        if (root == null) {
            return zNode;
        } else {
            Node t = root;
            Node y = null;
            while (t != null) {//深度查找到结点应该插入到的父结点
                y = t;
                if (t.data > zNode.data) {
                    t = t.left;
                } else {
                    t = t.right;
                }
            }
            //确定zNode在y节点的位置
            if (y.data > zNode.data) {
                zNode.left = y.left;
                y.left = zNode;
            } else {
                zNode.right = y.right;
                y.right = zNode;
            }
        }
        return root;
    }

    /**
     * Returns the number of nodes in the tree. Uses a recursive helper that
     * recurs down the tree and counts the nodes.
     */
    public int size() {
        return (size(root));
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        } else {
            return (size(root.left) + size(root.right) + 1);
        }
    }

    /*
    *二叉树的中、前、后续遍厉
     *  */
     public void printInOrder(){
         printInOrder(root);
         System.out.println();
     }

    private void printInOrder(Node root) {
        if(root == null)
            return;
        //left, node self, right
        printInOrder(root.left);
        System.out.print(root.data+" ");
        printInOrder(root.right);
    }

    public void printPreOrder(){
        printPreOrder(root);
        System.out.println();
    }

    private void printPreOrder(Node root) {
        if(root == null)
            return;
        // node self, left, right
        System.out.print(root.data+" ");
        printInOrder(root.left);
        printInOrder(root.right);
    }

    public void printPostOrder(){
        printPreOrder(root);
        System.out.println();
    }

    private void printPostOrder(Node root) {
        if(root == null)
            return;
        // left, right, node self
        printInOrder(root.left);
        printInOrder(root.right);
        System.out.print(root.data+" ");
    }

    /**
     * Build 123 by calling insert() three times. Note that the '2' must be
     * inserted first.
     */
    public void BuildBTree() {
        root = null;
        root = insert(root, 2);
        root = insert(root, 1);
        root = insert(root, 3);
        root = insert(root, 5);
        root = insert(root, 11);
        root = insert(root, 4);
        root = insert(root, 23);
        root = insert(root, 11);
        root = insert(root, 22);
    }
    /****************二叉树转换为链表****************/
    //找到右子树的head,即最小节点
    private Node getRTHead(Node root){
        Node rtHead = root;
        while(true){
            if(rtHead.left!=null){
                rtHead = rtHead.left;
            }else{
                break;
            }
        }
        return rtHead;
    }
    //找到左子树的tail,即最大节点
    private Node getLTTail(Node root){
        Node ltTail = root;
        while(true){
            if(ltTail.right!=null){
                ltTail = ltTail.right;
            }else{
                break;
            }
        }
        return ltTail;
    }
    //转换为双链表结构
    private void convertToDList(Node root){
        if(root.left != null){
            Node tailNode = getLTTail(root.left);
            convertToDList(root.left);
            tailNode.right = root;
            root.left = tailNode;
        }
        if(root.right != null){
            Node headNode = getRTHead(root.right);
            convertToDList(root.right);
            headNode.left = root;
            root.right = headNode;
        }
    }

    public void convertToDList(){
        Node head = root;
        while (head.left!=null){
            head = head.left;
        }
        convertToDList(root);
        Node cNode = head;
        while(cNode.right!=null){
            System.out.println("cNode.data is: " + cNode.data);
            cNode=cNode.right;
        }
    }

}
