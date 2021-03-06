/*
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be
set to NULL.
Initially, all next pointers are set to NULL.

Constraints:
The number of nodes in the tree is in the range [0, 212 - 1].
-1000 <= Node.val <= 1000
 */
public class PopulatingNextRightPointersEachNode {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    //Approach 1: Recursively solve this problem using DFS
    //Time: 100 percentile
    public Node connect1(Node root) {

        //if root is null return root
        if (root == null)
            return root;

        //if root has left node then left.next will point to root.right
        if (root.left != null) {
            root.left.next = root.right;
        }

        //if root has right node and root.next is not null then right.next == next.left
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }

        //recursively call on left and right subtrees
        connect1(root.left);
        connect1(root.right);

        return root;
    }

    //Approach 2: Using Iterative approach by 1st moving down levels and then moving within the level
    //Time: 100 percentile
    public Node connect2(Node root) {

        //if root is null return root
        if (root == null) {
            return null;
        }

        Node levelStart = root;

        // Moving across down the levels
        while (levelStart != null) {

            Node curr = levelStart;

            // Move within the levels
            while (curr != null) {
                if (curr.left != null) curr.left.next = curr.right;
                if (curr.right != null && curr.next != null) curr.right.next = curr.next.left;
                curr = curr.next;
            }

            levelStart = levelStart.left;
        }

        return root;
    }

    public static void main(String[] args) {

    }
}
