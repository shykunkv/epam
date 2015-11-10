package avl;

public class Main {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		tree.add(55);
		tree.add(22);
		tree.add(77);
		tree.add(78);
		tree.add(79);
		tree.add(100);
		tree.delete(100);
		tree.delete(79);
		tree.print();
	}

}
