package code;

import given.Entry;

/*
 * The binary node class which extends the entry class.
 * This will be used in linked tree implementations
 * 
 */
public class BinaryTreeNode<Key, Value> extends Entry<Key, Value> {
  private BinaryTreeNode<Key, Value> leftChild;
  private BinaryTreeNode<Key, Value> rightChild;
  private BinaryTreeNode<Key, Value> parent;

  public BinaryTreeNode(Key k, Value v) {
    super(k, v);
    this.leftChild = null;
    this.rightChild = null;
    this.parent = null;
  }
  
  public BinaryTreeNode<Key, Value> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(BinaryTreeNode<Key, Value> leftChild) {
    this.leftChild = leftChild;
  }

  public BinaryTreeNode<Key, Value> getRightChild() {
    return rightChild;
  }

  public void setRightChild(BinaryTreeNode<Key, Value> rightChild) {
    this.rightChild = rightChild;
  }

  public BinaryTreeNode<Key, Value> getParent() {
    return parent;
  }

  public void setParent(BinaryTreeNode<Key, Value> parent) {
    this.parent = parent;
  }
  
  public boolean isEnd() {
	return leftChild == null && rightChild == null;
  }
  public boolean hasLeftChild() {
	return leftChild != null;
  }

  public boolean hasRightChild() {
	return rightChild != null;
  }

  public boolean hasParent() {
    return parent != null;
  }
	  
  public void removeTheChild(BinaryTreeNode<Key, Value> child) {
	    if (leftChild == child) {
	        leftChild = null;
	        if (child != null) {
	            child.setParent(null); 
	        }
	    } else if (rightChild == child) {
	        rightChild = null;
	        if (child != null) {
	            child.setParent(null); 
	        }
	    }
	}

}
