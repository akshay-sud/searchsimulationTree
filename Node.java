package pa2;

public class Node {
	pageRank data;
	int key;
	Node left;
	Node right;
	Node parent;
	
	public Node(){
		key = -1;
	}
	public Node(pageRank entry) {
		data = entry;
		key = entry.rank;
		left = new Node();		// left & right defined like this to make their keys -1
		right = new Node();		// to signify it's nil, as defined in the pseudo code
		parent = new Node();	// same for parent
	}
	public Node(pageRank entry, Node p) {
		data = entry;
		key = entry.rank;
		parent = p;
		left = new Node();
		right = new Node();
		parent = new Node();
	}
	public void setLeft(Node l) {
		left = l;
	}
	public void setRight(Node r) {
		right = r;
	}
	public void setParent(Node p) {
		parent = p;
	}
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	public Node getParent() {
		return parent;
	}
}
