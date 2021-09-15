import java.util.HashMap;

/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is
the postorder traversal of the same tree, construct and return the binary tree.

Constraints:
1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 */

//Approach : Store the values and indexes of the Inorder traversal in a HashMap for searching them in O(1) time. Then
//recursively iterate the postorder and inorder arrays. The last index of the postorder will be the root node, find its
//index in the inorder, left side of the index will be left child and right side of the index will be right child
//In recursion we 1st use root, then right subtree and then left, since POST(Left, Right, Root) and we are traversing
//from backwards of postorder.
//Time: 97 percentile
public class ConstructBinaryTreeInorderPostorder {

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //Index to store the length and map to store the index of inorder traversal
    int index;
    HashMap<Integer, Integer> map = new HashMap();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        //Store the indexes and values in hashmap
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        index = postorder.length - 1;

        return helper(inorder, postorder, 0, postorder.length - 1);

    }

    public TreeNode helper(int[] inorder, int[] postorder, int start, int end) {

        if (start > end)
            return null;

        TreeNode node = new TreeNode(postorder[index--]);

        int mid = map.get(node.val);

        node.right = helper(inorder, postorder, mid + 1, end);
        node.left = helper(inorder, postorder, start, mid - 1);

        return node;
    }

}
