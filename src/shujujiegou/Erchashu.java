package shujujiegou;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Erchashu {

    class BtNode //BinaryTreeNode
    {
        private BtNode leftchild;
        private BtNode rightchild;
        private char data = '#';

        public BtNode getLeftchild() {
            return leftchild;
        }

        public void setLeftchild(BtNode leftchild) {
            this.leftchild = leftchild;
        }

        public BtNode getRightchild() {
            return rightchild;
        }

        public void setRightchild(BtNode rightchild) {
            this.rightchild = rightchild;
        }

        public char getData() {
            return data;
        }

        public void setData(char data) {
            this.data = data;
        }

        public BtNode() {
        }

        public BtNode(char data) {
            this.data = data;
        }

        public BtNode(BtNode leftchild, BtNode rightchild, char data) {
            this.leftchild = leftchild;
            this.rightchild = rightchild;
            this.data = data;
        }
    }


    class Int {
        private int index;

        public Int() {
            index = 0;
        }

        public Int(int x) {
            index = x;
        }

        int GetIndex() {
            return index;
        }

        void Inc() {
            index += 1;
        }
    }

    class StkNode {
        public BtNode ptr = null;
        public int popnum;

        public StkNode(BtNode p) {
            ptr = p;
            popnum = 0;
        }
    }

    class BinaryTree {
        private BtNode root = null;
        private int Index;

        //中序遍历
        public void InOrder(BtNode ptr) {
            if (ptr != null) {
                InOrder(ptr.getLeftchild());
                System.out.print(ptr.getData() + " ");
                InOrder(ptr.getRightchild());
            }

        }

        //先序遍历
        public void PreOrder(BtNode ptr) {
            if (ptr != null) {
                System.out.print(ptr.getData() + " ");
                PreOrder(ptr.getLeftchild());
                PreOrder(ptr.getRightchild());
            }

        }

        //后序遍历
        public void PastOrder(BtNode ptr) {
            if (ptr != null) {
                PastOrder(ptr.getLeftchild());
                PastOrder(ptr.getRightchild());
                System.out.print(ptr.getData() + " ");
            }

        }

        //创建二叉树 键盘输入
        public BtNode CreateTreeA() {
            char item;
            BtNode s = null;
            // item = (char)new BufferedReader(new InputStreamReader(System.in)).read();
            item = new java.util.Scanner(System.in).next().charAt(0);
            if (item != '#') {
                s = new BtNode(item);
                s.setLeftchild(CreateTreeA());
                s.setRightchild(CreateTreeA());
            }
            return root = s;
        }

        //创建二叉树 字符串
        public BtNode CreateStr(String str) {
            BtNode s = null;
            if (str.charAt(Index) != '#') {
                s = new BtNode(str.charAt(Index));
                Index++;
                s.setLeftchild(CreateStr(str));
                Index++;
                s.setRightchild(CreateStr(str));
            }
            return root = s;
        }

        private int FindIs(String istr, int n, char val) {
            int pos = -1;
            for (int i = 0; i < n; ++i) {
                if (istr.charAt(i) == val) {
                    pos = i;
                    break;
                }
            }
            return pos;
        }

        private BtNode CreatePI(String pstr, String istr, int n) {
            BtNode s = null;
            if (n > 0) {
                s = new BtNode(pstr.charAt(0));
                int pos = FindIs(istr, n, s.getData());
                if (pos == -1) return null;
                s.setLeftchild(CreatePI(pstr.substring(1, pos + 1), istr.substring(0, pos), pos));
                s.setRightchild(CreatePI(pstr.substring(pos + 1), istr.substring(pos + 1), n - pos - 1));
            }
            return s;
        }

        private BtNode CreateIL(String istr, String lstr, int n) {
            BtNode s = null;
            if (n > 0) {
                s = new BtNode(lstr.charAt(n - 1));
                int pos = FindIs(istr, n, s.getData());
                if (pos == -1) return null;
                s.setLeftchild(CreateIL(istr.substring(0, pos), lstr.substring(0, pos), pos));
                s.setRightchild(CreateIL(istr.substring(pos + 1), lstr.substring(pos, n - 1), n - pos - 1));
                //                                                       pos ,n  -1
            }
            return s;
        }

        public BinaryTree() {
        }

        public void CreateTreePI(String pstr, String istr) {
            if (pstr != null && istr != null) {
                root = CreatePI(pstr, istr, pstr.length());
                //     NiceCreatePI();
            }
        }

        public void CreateTreeIL(String istr, String lstr) {
            if (istr != null && lstr != null) {
                root = CreateIL(istr, lstr, istr.length());
            }
        }

        //栈中序遍历
        public void NiceInOrder() {
            if (root == null) return;
            BtNode ptr = root;
            Stack<BtNode> st = new Stack<BtNode>();
            while (!st.empty() || ptr != null) {
                while (ptr != null) {
                    st.push(ptr);
                    ptr = ptr.getLeftchild();
                }
                ptr = st.pop();
                System.out.print(ptr.getData() + " ");
                ptr = ptr.getRightchild();
            }
            System.out.println(" ");
        }

        public void NicePastOrder() {
            if (root == null) return;
            BtNode tag = null;
            BtNode ptr = root;
            Stack<BtNode> st = new Stack<BtNode>();
            while (!st.empty() || ptr != null) {
                while (ptr != null) {
                    st.push(ptr);
                    ptr = ptr.getLeftchild();
                }
                ptr = st.pop();
                if (ptr.getRightchild() == null || ptr.getRightchild() == tag) {
                    System.out.print(ptr.getData() + " ");
                    tag = ptr;
                    ptr = null;
                } else {
                    st.push(ptr);
                    ptr = ptr.getRightchild();
                }
            }
            System.out.println(" ");
        }

        public void NicePreOrder() {
            if (root == null) return;
            Stack<BtNode> st = new Stack<BtNode>();
            st.push(root);
            while (!st.empty()) {
                BtNode ptr = st.pop();
                System.out.print(ptr.getData() + " ");
                if (ptr.getRightchild() != null) {
                    st.push(ptr.getRightchild());
                }
                if (ptr.getLeftchild() != null) {
                    st.push(ptr.getLeftchild());
                }
            }
            System.out.println();
        }

        public void NiceLevelOrder() {
            if (root == null) return;
            Queue<BtNode> qu = new LinkedList<BtNode>();
            qu.offer(root);
            while (!qu.isEmpty()) {
                BtNode ptr = qu.poll();
                System.out.print(ptr.getData() + " ");
                if (ptr.getLeftchild() != null) {
                    qu.offer(ptr.getLeftchild());
                }
                if (ptr.getRightchild() != null) {
                    qu.offer(ptr.getRightchild());
                }
            }
            System.out.println();
        }

        public void NiceStkPastOrder() {
            Stack<StkNode> st = new Stack<StkNode>();
            if (root == null) return;
            st.push(new StkNode(root));
            while (!st.empty()) {
                StkNode node = st.pop();
                if (++node.popnum == 3) {
                    System.out.print(node.ptr.getData() + " ");
                } else {
                    st.push(node);
                    if (node.popnum == 1 && node.ptr.getLeftchild() != null) {
                        st.push(new StkNode(node.ptr.getLeftchild()));
                    } else if (node.popnum == 2 && node.ptr.getRightchild() != null) {
                        st.push(new StkNode(node.ptr.getRightchild()));
                    }
                }
            }
            System.out.println();
        }

        public void NiceStkInOrder() {
            Stack<StkNode> st = new Stack<StkNode>();
            if (root == null) return;
            st.push(new StkNode(root));
            while (!st.empty()) {
                StkNode node = st.pop();
                if (++node.popnum == 2) {
                    System.out.print(node.ptr.getData() + " ");
                    if (node.ptr.getRightchild() != null) {
                        st.push(new StkNode(node.ptr.getRightchild()));
                    }
                } else {
                    st.push(node);
                    if (node.popnum == 1 && node.ptr.getLeftchild() != null) {
                        st.push(new StkNode(node.ptr.getLeftchild()));
                    }
                }
            }
            System.out.println();
        }

        public void PrintK(BtNode ptr, int k) {
            if (ptr != null && k == 0) {
                System.out.print(ptr.getData() + "  ");
            } else if (ptr != null) {
                PrintK(ptr.getLeftchild(), k - 1);
                PrintK(ptr.getRightchild(), k - 1);
            }
        }

        public int GetSize(BtNode p) {
            if (p == null) return 0;
            else return GetSize(p.getLeftchild()) + GetSize(p.getRightchild()) + 1;
        }

        public int GetDepth(BtNode p) {
            if (p == null) return 0;
            else return Math.max(GetDepth(p.getLeftchild()), GetDepth(p.getRightchild())) + 1;
        }

        public BtNode FindValue(BtNode root, char val) {
            if (root == null || root.getData() == val)
            //if(root.GetData() == val || root == null)
            {
                return root;
            } else {
                BtNode ptr = FindValue(root.getLeftchild(), val);
                if (ptr == null) {
                    ptr = FindValue(root.getRightchild(), val);
                }
                return ptr;
            }
        }

        public BtNode GetParent(BtNode root, BtNode child) {
            if (root == null || root.getLeftchild() == child || root.getRightchild() == child) {
                return root;
            } else {
                BtNode ptr = GetParent(root.getLeftchild(), child);
                if (ptr == null) {
                    ptr = GetParent(root.getRightchild(), child);
                }
                return ptr;
            }
        }

        public boolean Is_Full(BtNode p) {
            return (p == null) ||
                    (Is_Full(p.getLeftchild()) && Is_Full(p.getRightchild()) && (GetDepth(p.getLeftchild()) == GetDepth(p.getRightchild())));
        }

        public boolean Is_Full() {
            Queue<BtNode> qu = new LinkedList<BtNode>();
            int n = 1;
            boolean res = true;
            if (root == null) return res;
            qu.offer(root);
            while (!qu.isEmpty()) {
                if (qu.size() < n) {
                    res = false;
                    break;
                }
                for (int i = 0; i < n; ++i) {
                    BtNode p = qu.poll();
                    if (p.getLeftchild() != null) {
                        qu.offer(p.getLeftchild());
                    }
                    if (p.getRightchild() != null) {
                        qu.offer(p.getRightchild());
                    }
                }
                n += n;
            }
            return res;
        }
    }

    public boolean Is_Comp_Tree(BtNode root)
    {
        boolean res = true;
        if(root == null) return res;
        Queue<BtNode> qu = new LinkedList<BtNode>();
        qu.offer(root);
        while(!qu.isEmpty())
        {
            BtNode p = qu.poll();
            if(p == null) break;
            qu.offer(p.getLeftchild());
            qu.offer(p.getRightchild());
        }
        while(!qu.isEmpty())
        {
            BtNode p = qu.poll();
            if(p != null)
            {
                res = false;
                break;
            }
        }
        return res;
    }
}





