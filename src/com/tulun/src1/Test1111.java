package com.tulun.src1;


import java.util.Arrays;

public class Test1111 {

 public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

     TreeNode(int x) {
         val = x;
     }
 }


    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        // 在中序中找到前序的根
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左子树，注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        System.out.println(root.val);
        return root;
    }

    public static void main(String[] args) {
        int[] pre={1,2,3,4,5,6,7};
        int[] in={3,2,4,1,6,5,7};
        Test1111 test = new Test1111();
        System.out.println(test.reConstructBinaryTree(pre, in));
    }
}

