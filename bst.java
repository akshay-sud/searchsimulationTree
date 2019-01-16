package pa2;

public class bst {
	Node root;
	int index;
	
	public bst() {
		index = 1;
		root = new Node();
	}
	public void sortedWalk(Node x) { // first time called, x should be root of tree. This is a modified inOrder walk, but in reverse order
		if (x.key != -1) {
			sortedWalk(x.right);
			System.out.println(index + ". " + "score: " + x.key + "   " + "URL: " + x.data.url);
			index++;
			sortedWalk(x.left);
		}
	}
	public void resetIndex() { // used to reset index if you want to do another sortedWalk
		index = 1;
	}
	public Node search(Node x, int k) {
		if ((x.key == -1) || (k == x.key)) {
			return x;
		}
		if (k < x.key) {
			return search(x.left, k);
		}
		else {
			return search(x.right, k);
		}
	}
	public void insert(Node z) {
		Node y = new Node();
		Node x = root;
		while (x.key != -1) {
			y = x;
			if (z.key < x.key) {
				x = x.left;
			}
			else {
				x = x.right;
			}
		}
		z.parent = y;
		if (y.key == -1) {
			root = z;
		}
		else if (z.key < y.key) {
			y.left = z;
		}
		else {
			y.right = z;
		}
	}
	public Node minimum(Node x) {
		while (x.left.key != -1) {
			x = x.left;
		}
		return x;
	}
	public Node maximum(Node x) {
		x = root;
		while (x.right.key != -1) {
			x = x.right;
		}
		return x;
	}
	public void transplant(Node u, Node v) {
		if (u.parent.key == -1) {
			root = v;
		}
		else if (u.key == u.parent.left.key) {
			u.parent.left = v;
		}
		else {
			u.parent.right = v;
		}
		if (v.key == -1) {
			v.parent = u.parent;
		}
	}
	public void delete(Node z) {
		if (z.left.key == -1) {
			transplant(z, z.right);
		}
		else if (z.right.key == -1) {
			transplant(z, z.left);
		}
		else {
			Node y = minimum(z.right);
			if (y.parent.key != z.key) {
				transplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
		}
	}
}
