package datastructures;
/**
 * This class implements the BinaryTreeNode interface, in order to implement a
 * binary tree data structure
 * 
 * @author Kuan Chi Chen
 *
 * @param <T>
 */
public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T> {
	/** the left child **/
	private BinaryTreeNode<T> left = null;
	/** the right child **/
	private BinaryTreeNode<T> right = null;
	/** the data inside the node **/
	private T data;

	/**
	 * Constructor when you have information about the left child, the right
	 * child and the data
	 * 
	 * @param initData
	 * @param initLeft
	 * @param initRight
	 */
	public DefaultBinaryTreeNode(T initData, DefaultBinaryTreeNode<T> initLeft, DefaultBinaryTreeNode<T> initRight) {
		data = initData;
		left = initLeft;
		right = initRight;
	}

	/**
	 * Constructor when you only have information on the data
	 * 
	 * @param initData
	 */
	public DefaultBinaryTreeNode(T initData) {

		this(initData, null, null);
	}

	/**
	 * Constructor when you have no information
	 */
	public DefaultBinaryTreeNode() {
		this(null, null, null);
	}

	@Override
	/**
	 * return the data
	 */
	public T getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	/**
	 * set the data
	 */
	public void setData(T data) {
		// TODO Auto-generated method stub
		this.data = data;
	}

	@Override
	/**
	 * get the left child
	 */
	public BinaryTreeNode<T> getLeftChild() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	/**
	 * get the right child
	 */
	public BinaryTreeNode<T> getRightChild() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	/**
	 * set the left child
	 */
	public void setLeftChild(BinaryTreeNode<T> left) {
		// TODO Auto-generated method stub
		this.left = left;
	}

	@Override
	/**
	 * set the right child
	 */
	public void setRightChild(BinaryTreeNode<T> right) {
		// TODO Auto-generated method stub
		this.right = right;
	}

	@Override
	/**
	 * check if there is a left child or a right child, if not the node is a
	 * leaf
	 */
	public boolean isLeaf() {
		if (left == null && right == null)
			return true;
		return false;
	}

}
