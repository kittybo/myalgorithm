package structure.tree.bintree;

import junit.framework.Assert;
import junit.framework.TestCase;

public class BinaryTreeTest extends TestCase {

    public void testLookup() throws Exception {
        BinaryTree bt = new BinaryTree();
        bt.BuildBTree();
        Assert.assertEquals(true,bt.lookup(11));
        Assert.assertEquals(false,bt.lookup(0));
    }

    public void testInsert() throws Exception {
        BinaryTree bt = new BinaryTree();
        bt.BuildBTree();
        bt.printInOrder();
        bt.insert(11);
        bt.insert(15);
        bt.insert(100);
        bt.printInOrder();
        Assert.assertEquals(true,bt.lookup(100));
    }

    public void testInsertWithLoop() throws Exception {
        BinaryTree bt = new BinaryTree();
        bt.BuildBTree();
        bt.printInOrder();

        bt.insertWithLoop(11);
        bt.insertWithLoop(15);
        bt.insertWithLoop(100);
        bt.printInOrder();
        Assert.assertEquals(true,bt.lookup(100));
    }

    public void testSize() throws Exception {
        BinaryTree bt = new BinaryTree();
        bt.BuildBTree();
        int size = bt.size();
        Assert.assertEquals(9, size);
    }

    public void testConvertToDList(){
        BinaryTree bt = new BinaryTree();
        bt.BuildBTree();
        bt.convertToDList();

    }
}