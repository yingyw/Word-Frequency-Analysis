package phaseA;
import providedCode.*;

//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2
/*
* This class called AVLTree which implements the DataCounter interface
* also extends from BinarySearchTree, and AVLTree is a self-balanced tree.
*/
public class AVLTree<E> extends BinarySearchTree<E> {

	/*
	 * Given a Comparator c, use it to create an empty AVLTree
	 */
	public AVLTree(Comparator<? super E> c) {
		super(c);
	}

	/*
	 * Given a E type data and increments given data's count
	 * inside AVLTree
	 */
	public void incCount(E data) {
		overallRoot = insert(data, overallRoot);
	}

	/*
	 * Helper method that insert given data into tree, increment counts if the  
	 * given value is already inside the tree, otherwise, adds the value in to 
	 * tree as a new term
	 * Returns the new tree after inserting.
	 */
	private AVLNode insert(E data, BSTNode overallRoot) {
		@SuppressWarnings("unchecked")
		AVLNode currentNode = (AVLNode) overallRoot;
		//if the tree is empty initially, add given data to the tree.
		if (currentNode == null) {
			return new AVLNode(data);
		}
		int compareResult = comparator.compare(data, currentNode.data);
		if (compareResult < 0) {
			currentNode.left = insert(data, currentNode.left);
		} else if (compareResult > 0) {
			currentNode.right = insert(data, currentNode.right);
		} else {
			// Duplicate, increase the count of given data
			currentNode.count++;
		}

		return balance(currentNode);
	}

	private static final int ALLOWED_IMBALANCE = 1;

	/*
	 * Given an AVLNode checking its balance to make sure that the difference 
	 * of children's heights is acceptable. In this case, the height difference 
	 * should be within one. Returns the balanced node.
	 */
	private AVLNode balance(AVLNode currentNode) {
		if (currentNode == null)
			return currentNode;

		if (height(currentNode.left) - height(currentNode.right) > ALLOWED_IMBALANCE)
			if (height(currentNode.left.left) >= height(currentNode.left.right))
				currentNode = rotateWithLeftChild(currentNode);
			else
				currentNode = doubleWithLeftChild(currentNode);
		else if (height(currentNode.right) - height(currentNode.left) > ALLOWED_IMBALANCE)
			if (height(currentNode.right.right) >= height(currentNode.right.left))
				currentNode = rotateWithRightChild(currentNode);
			else
				currentNode = doubleWithRightChild(currentNode);

		currentNode.height = Math.max(height(currentNode.left),
				height(currentNode.right)) + 1;
		return currentNode;
	}

	/*
	 * Returns the height of subtree t.
	 * Returns -1 if given an empty subtree
	 */
	@SuppressWarnings("unchecked")
	private int height(BSTNode currentNode) {
		return currentNode == null ? -1 : ((AVLNode) currentNode).height;
	}

	/*
	 * Rotate binary tree node with left child. For AVL trees, this is a single
	 * rotation. Update heights, then return new root.
	 */
	private AVLNode rotateWithLeftChild(AVLNode node) {
		@SuppressWarnings("unchecked")
		AVLNode current = (AVLNode) node.left;
		node.left = current.right;
		current.right = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		current.height = Math.max(height(current.left), node.height) + 1;
		return current;
	}

	/*
	 * Rotate binary tree node with right child. For AVL trees, this is a single
	 * rotation. Update heights, then return new root.
	 */
	private AVLNode rotateWithRightChild(AVLNode node) {
		@SuppressWarnings("unchecked")
		AVLNode current = (AVLNode) node.right;
		node.right = current.left;
		current.left = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		current.height = Math.max(height(current.right), node.height) + 1;
		return current;
	}

	/*
	 * Double rotate binary tree node: first left child with its right child;
	 * then node k3 with new left child. For AVL trees, this is a double
	 * rotation. Update heights, then return new root.
	 */
	@SuppressWarnings("unchecked")
	private AVLNode doubleWithLeftChild(AVLNode node) {
		node.left = rotateWithRightChild((AVLNode) node.left);
		return rotateWithLeftChild(node);
	}

	/*
	 * Double rotate binary tree node: first right child with its left child;
	 * then node k1 with new right child. For AVL trees, this is a double
	 * rotation. Update heights, then return new root.
	 */
	@SuppressWarnings("unchecked")
	private AVLNode doubleWithRightChild(AVLNode node) {
		node.right = rotateWithLeftChild((AVLNode) node.right);
		return rotateWithRightChild(node);
	}

	/*
	 * Returns height of the AVL tree
	 */
	public int height() {
		return height(overallRoot);
	}

	/*
	 * Extends BinarySearchTree and constructs an AVLTree node with height.
	 */
	private class AVLNode extends BSTNode {
		public int height;

		/*
		 * Creates an AVLNode with given data d, setting count of 
		 * new node to be 1 and height to be 0.
		 */
		public AVLNode(E d) {
			super(d);
			height = 0;
		}
	}
}