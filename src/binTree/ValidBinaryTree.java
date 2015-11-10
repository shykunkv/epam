package binTree;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int val) {
		this.val = val;
	}
}

public class ValidBinaryTree {
	
	public static boolean check(TreeNode node, Integer min, Integer max) {
        
		if (node == null) return true;
        if ((min != null &&  node.val <= min) || (max != null && node.val >= max) ) return false;
        
        return check(node.left, min, node.val) && check(node.right, node.val, max);
    }
	
	
	public static void main(String[] args) {
		TreeNode head = new TreeNode(5);
		head.left = new TreeNode(2);
		head.right = new TreeNode(6);
		
		System.out.println(check(head, null, null));
	}

}
