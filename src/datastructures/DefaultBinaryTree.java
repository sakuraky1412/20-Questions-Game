package datastructures;

/**
 * This class implements the BinaryTree interface in order to implement a binary
 * tree data structure
 * 
 * @author Kuan Chi Chen
 *
 * @param <T>
 *            DefaultBinaryTree
 */
public class DefaultBinaryTree<T> implements BinaryTree<T> {
	/** The root of the tree **/
	private BinaryTreeNode<T> root = null;

	/**
	 * Constructor of the default binary tree
	 */
	public DefaultBinaryTree() {
		// Initiate the root
		root = new DefaultBinaryTreeNode<T>();
	}

	/**
	 * Create a DefaultBinaryTree instance, and manually create a tree
	 * corresponding to the Seven Dwarves example from lecture.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// DefaultBinaryTreeNode of type <String> for each Dwarf
		DefaultBinaryTreeNode<String> happy = new DefaultBinaryTreeNode<String>(
				"happy");
		DefaultBinaryTreeNode<String> doc = new DefaultBinaryTreeNode<String>(
				"doc");
		DefaultBinaryTreeNode<String> bashful = new DefaultBinaryTreeNode<String>(
				"bashful");
		DefaultBinaryTreeNode<String> grumpy = new DefaultBinaryTreeNode<String>(
				"grumpy");
		DefaultBinaryTreeNode<String> sleepy = new DefaultBinaryTreeNode<String>(
				"sleepy");
		DefaultBinaryTreeNode<String> sneezy = new DefaultBinaryTreeNode<String>(
				"sneezy");

		// make a binary tree data structure by calling the setLeftChild and
		// setRightChild methods on the DefaultBinaryTreeNode objects
		happy.setLeftChild(doc);
		doc.setLeftChild(bashful);
		doc.setRightChild(grumpy);
		happy.setRightChild(sleepy);
		sleepy.setRightChild(sneezy);
		// our tree made of dwarves
		DefaultBinaryTree<String> tree = new DefaultBinaryTree<String>();
		// setRoot on the the DefaultBinaryTree object
		tree.setRoot(happy);

		// print out the linked list with different traversals
		System.out.println("preorderTraversal is "
				+ tree.preorderTraversal().toString());
		System.out.println("inorderTraversal is "
				+ tree.inorderTraversal().toString());
		System.out.println("postorderTraversal is "
				+ tree.postorderTraversal().toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * a public method to kickstart the process for our inorder Traversal
	 */
	public LinkedList inorderTraversal() {
		// create a LinkedList traversal
		LinkedList<String> traversal = new LinkedList<String>();
		// if we have a root that is not null
		if (this.getRoot() != null)
			// pass in the root
			inorderTraversal(this.getRoot(), traversal);
		// return the linked list
		return traversal;
	}

	/**
	 * a private recursive method for our inorder Traversal
	 * 
	 * @param binaryTreeNode
	 * @param traversal
	 */
	private void inorderTraversal(BinaryTreeNode<T> binaryTreeNode,
			LinkedList<String> traversal) {
		// if there is a left child
		if (binaryTreeNode.getLeftChild() != null) {
			// call the method on the left child
			// in order to put the left child into the traversal
			inorderTraversal(binaryTreeNode.getLeftChild(), traversal);
		}
		// put the node into the traversal
		traversal.insertLast((String) binaryTreeNode.getData());

		// if there is a right child
		if (binaryTreeNode.getRightChild() != null)
			// call the method on the right child
			// in order to put the right child into the traversal
			inorderTraversal(binaryTreeNode.getRightChild(), traversal);

	}

	/**
	 * a private recursive method for our leaf order Traversal
	 * 
	 * @param binaryTreeNode
	 * @param traversal
	 */
	public void leafTraversal(BinaryTreeNode<T> binaryTreeNode,
			LinkedList<String> traversal) {

		if (binaryTreeNode.isLeaf()) {
			// put the node into the traversal
			traversal.insertFirst((String) binaryTreeNode.getData());
		}

		// if there is a left child
		if (binaryTreeNode.getLeftChild() != null) {
			// call the method on the left child
			// in order to put the left child into the traversal
			inorderTraversal(binaryTreeNode.getLeftChild(), traversal);
		}

		// if there is a right child
		if (binaryTreeNode.getRightChild() != null)
			// call the method on the right child
			// in order to put the right child into the traversal
			inorderTraversal(binaryTreeNode.getRightChild(), traversal);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * a public method to kickstart the process for our preorder Traversal
	 */
	public LinkedList preorderTraversal() {
		// create a LinkedList traversal
		LinkedList<String> traversal = new LinkedList<String>();
		// if we have a root that is not null
		if (this.getRoot() != null)
			// pass in the root
			preorderTraversal(this.getRoot(), traversal);
		// return the linked list
		return traversal;
	}

	private void preorderTraversal(BinaryTreeNode<T> binaryTreeNode,
			LinkedList<String> traversal) {
		// put the node into the traversal
		traversal.insertLast((String) binaryTreeNode.getData());
		// if there is a left child
		if (binaryTreeNode.getLeftChild() != null) {
			// call the method on the left child
			// in order to put the left child into the traversal
			preorderTraversal(binaryTreeNode.getLeftChild(), traversal);
		}
		// if there is a right child
		if (binaryTreeNode.getRightChild() != null)
			// call the method on the right child
			// in order to put the right child into the traversal
			preorderTraversal(binaryTreeNode.getRightChild(), traversal);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * a public method to kickstart the process for our preorder Traversal
	 */
	public LinkedList postorderTraversal() {
		// create a LinkedList traversal
		LinkedList<String> traversal = new LinkedList<String>();
		// if we have a root that is not null
		if (this.getRoot() != null)
			// pass in the root
			postorderTraversal(this.getRoot(), traversal);
		// return the linked list
		return traversal;
	}

	private void postorderTraversal(BinaryTreeNode<T> binaryTreeNode,
			LinkedList<String> traversal) {
		// if there is a left child
		if (binaryTreeNode.getLeftChild() != null) {
			// call the method on the left child
			// in order to put the left child into the traversal
			postorderTraversal(binaryTreeNode.getLeftChild(), traversal);
		}
		// if there is a right child
		if (binaryTreeNode.getRightChild() != null)
			// call the method on the right child
			// in order to put the right child into the traversal
			postorderTraversal(binaryTreeNode.getRightChild(), traversal);
		// put the node into the traversal
		traversal.insertLast((String) binaryTreeNode.getData());
	}

	@Override
	/**
	 * get the root
	 */
	public BinaryTreeNode<T> getRoot() {
		if (root == null) {
			return null;
		}
		return root;
	}

	@Override
	/**
	 * set the root
	 */
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;

	}

	@Override
	/**
	 * check if the tree is empty
	 */
	public boolean isEmpty() {
		if (root.getData() == null)
			return true;
		return false;
	}
}
