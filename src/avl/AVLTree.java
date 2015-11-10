package avl;


public class AVLTree {
	
	static class AVLNode {
		private int val;
		private int height;
		private AVLNode left, right;
		
		AVLNode(int val) {
			this.val = val;
			this.height = 1;
			this.left = this.right = null;
		}

		public int getVal() {
			return val;
		}

		public void setVal(int val) {
			this.val = val;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
		
		public void setHeight() {
			int lh = (left == null) ?  0 : left.getHeight();
			int rh = (right == null) ? 0 : right.getHeight();
			
			if (lh > rh) {
				this.height = lh + 1;
			} else {
				this.height = rh + 1;
			}
		}

		public AVLNode getLeft() {
			return left;
		}

		public void setLeft(AVLNode left) {
			this.left = left;
		}

		public AVLNode getRight() {
			return right;
		}

		public void setRight(AVLNode right) {
			this.right = right;
		}
		
		public int balance() {
			int lh = (left == null) ?  0 : left.getHeight();
			int rh = (right == null) ? 0 : right.getHeight();
			return rh - lh;
		}
		
	}
	
	
	private AVLNode root;
	
	
	public AVLNode rightRotate(AVLNode node) {
		AVLNode temp = node.getLeft();
		node.setLeft(temp.getRight());
		temp.setRight(node);
		node.setHeight();
		temp.setHeight();
		return temp;
	}
	
	public AVLNode leftRotate(AVLNode node) {
		AVLNode temp = node.getRight();
		node.setRight(temp.getLeft());
		temp.setLeft(node);
		node.setHeight();
		temp.setHeight();
		return temp;
	}
	
	public AVLNode balance(AVLNode node) {
		node.setHeight();
		
		if (node.balance() == 2) {
			if (node.getRight().balance() < 0) {
				node.setRight(rightRotate(node.getRight()));
			}
			return leftRotate(node);
		}
		if (node.balance() == -2) {
			if (node.getLeft().balance() > 0) {
				node.setLeft(leftRotate(node.getLeft()));
			}
			return rightRotate(node);
		}
		return node;
	}
	
	public void add(int val) {
		root = addNode(root, val);
	}
	
	private AVLNode addNode(AVLNode root, int val) {
		if (root == null) {
			root = new AVLNode(val);
			return root;
		}
		
		if (val < root.getVal()) {
			root.setLeft(addNode(root.getLeft(), val));
		} else {
			root.setRight(addNode(root.getRight(), val));
		}
		return balance(root);
	}
	
	private void printTree(AVLNode node, int k) {
		if (node != null) {
			printTree(node.getLeft(), k+4);
			for (int i = 0; i < k; i++) System.out.print(" ");
			System.out.println(node.getVal());
			printTree(node.getRight(), k + 4);
		}
	}
	
	public void print() {
		this.printTree(root, 0);
	}
	
	
	private AVLNode getMin(AVLNode t) {
		while (t.getLeft() != null) {
			t = t.getLeft();
		}
		return t;
	}
	
	private AVLNode delMin(AVLNode t) {
		if (t.getLeft() == null) {
			return t.getRight();
		}
		t.setLeft(delMin(t.getLeft()));
		return balance(t);
	}
	
	private AVLNode deleteNode(AVLNode node, int val) {
		if (node == null) {
			return null;
		}
		
		if (val < node.getVal()) {
			node.setLeft(deleteNode(node.getLeft(), val));
		} else if (val > node.getVal()) {
			node.setRight(deleteNode(node.getRight(), val));
		} else {
			AVLNode l = node.getLeft();
			AVLNode r = node.getRight();
			if (r == null) {
				return l;
			}
			
			AVLNode min = getMin(r);
			min.setRight(delMin(r));
			min.setLeft(l);
			return balance(min);
		}
		return balance(node);
	}
	
	public void delete(int val) {
		root = deleteNode(root, val);
	}
	
	private int findNode(AVLNode node, int key) {
		if (node != null) {
			if (node.getVal() == key) return key;
		
			if (key > node.getVal()) {
				return findNode(node.getRight(), key);
			} else {
				return findNode(node.getLeft(), key);
			}
		} else {
			return -1;
		}
	}
	
	public boolean contains(int key) {
		return findNode(root, key) != -1;
	}
}