package shujujiegou;



class ErchashuTest {
    public static void main(String[] args) {
        Erchashu erchashu=new Erchashu();
        Erchashu.BinaryTree binaryTree=erchashu.new BinaryTree();
//        binaryTree.CreateTreeA();

//        binaryTree.CreateStr("abc##de##f##g#h##");
        Erchashu.BtNode btNode=binaryTree.CreateStr("abc##de##f##g#h##");
//        binaryTree.InOrder(btNode);
//        System.out.println();
//        binaryTree.PastOrder(btNode);
//        System.out.println();
//        binaryTree.PreOrder(btNode);
        binaryTree.NiceInOrder();
        System.out.println();
        binaryTree.NicePastOrder();

    }

}