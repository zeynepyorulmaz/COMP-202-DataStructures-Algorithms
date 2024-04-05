package code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import given.iMap;
import given.iBinarySearchTree;

public class BinarySearchTree<Key, Value> implements iBinarySearchTree<Key, Value>, iMap<Key, Value> {

    private BinaryTreeNode<Key, Value> root;
    private Comparator<Key> comparator;
    private int size = 0;


    @Override
    public Value get(Key k) {
        BinaryTreeNode<Key, Value> node = getNode(k);
        if (node != null)
            return node.getValue();
        return null;
    }

    @Override
    public Value put(Key k, Value v) {
        BinaryTreeNode<Key, Value> node = getNode(k);

        if (node == null) {
          BinaryTreeNode<Key, Value> newNode = new BinaryTreeNode<Key, Value>(k, v);
          if (root == null) {
            root = newNode;
          } else {
            BinaryTreeNode<Key, Value> current = root;
            while (true) {
              if (comparator.compare(k, current.getKey()) < 0) {
                if (current.getLeftChild() == null) {
                  current.setLeftChild(newNode);
                  newNode.setParent(current);
                  break;
                } else {
                  current = current.getLeftChild();
                }
              } else {
                if (current.getRightChild() == null) {
                  current.setRightChild(newNode);
                  newNode.setParent(current);
                  break;
                } else {
                  current = current.getRightChild();
                }
              }
            }
          }

          size++;

          return null;
        } else {
          Value oldValue = node.getValue();
          node.setValue(v);
          return oldValue;
        }
    }

    @Override
    public Value remove(Key k) {
        BinaryTreeNode<Key, Value> node = getNode(k);
        if (node == null) {
            return null;
        }
        Value oldValue = node.getValue();
        if (node.isEnd()) {
            if (node == root) {
                root = null;
            } else {
                node.getParent().removeTheChild(node);
            }
        } else if (node.getLeftChild() == null) {
            if (node == root) {
                root = node.getRightChild();
                if (root != null) 
                    root.setParent(null);
            } else if (isLeftChild(node)) {
                node.getParent().setLeftChild(node.getRightChild());
                if (node.getRightChild() != null)
                    node.getRightChild().setParent(node.getParent());
            } else {
                node.getParent().setRightChild(node.getRightChild());
                if (node.getRightChild() != null)
                    node.getRightChild().setParent(node.getParent());
            }
        } else if (node.getRightChild() == null) {
            if (node == root) {
                root = node.getLeftChild();
                if (root != null) 
                    root.setParent(null);
            } else if (isLeftChild(node)) {
                node.getParent().setLeftChild(node.getLeftChild());
                if (node.getLeftChild() != null)
                    node.getLeftChild().setParent(node.getParent());
            } else {
                node.getParent().setRightChild(node.getLeftChild());
                if (node.getLeftChild() != null)
                    node.getLeftChild().setParent(node.getParent());
            }
        } else {
            BinaryTreeNode<Key, Value> rc = node.getRightChild();
            while (rc.hasLeftChild()) {
                rc = rc.getLeftChild();
            }
            node.setKey(rc.getKey());
            node.setValue(rc.getValue());
            if (isLeftChild(rc)) {
                rc.getParent().setLeftChild(rc.getRightChild());
            } else {
                rc.getParent().setRightChild(rc.getRightChild());
            }
            if (rc.hasRightChild()) {
                rc.getRightChild().setParent(rc.getParent());
            }
        }
        size--;
        return oldValue;
    }

    @Override
    public boolean isLeftChild(BinaryTreeNode<Key, Value> node) {
        return node != null && node.getParent() != null && node.getParent().getLeftChild() == node;
    }

    @Override
    public boolean isRightChild(BinaryTreeNode<Key, Value> node) {
        return node != null && node.getParent() != null && node.getParent().getRightChild() == node;
    }

    @Override
    public Iterable<Key> keySet() {
        ArrayList<Key> keys = new ArrayList<Key>();
        for (BinaryTreeNode<Key, Value> node : getNodesInOrder()) {
          keys.add(node.getKey());
        }
        return keys;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public BinaryTreeNode<Key, Value> getRoot() {
        return root;
    }

    @Override
    public BinaryTreeNode<Key, Value> getParent(BinaryTreeNode<Key, Value> node) {
        return node.getParent();
    }

    public boolean isInternal(BinaryTreeNode<Key, Value> node) {
        return node != null && (node.getLeftChild() != null || node.getRightChild() != null);
    }


    public boolean isExternal(BinaryTreeNode<Key, Value> node) {
        return node == null || (node.getLeftChild() == null && node.getRightChild() == null);
    }

    @Override
    public boolean isRoot(BinaryTreeNode<Key, Value> node) {
        return node == root;
    }

    @Override
    public BinaryTreeNode<Key, Value> getNode(Key k) {
        return search(root, k);
    }

    private BinaryTreeNode<Key, Value> search(BinaryTreeNode<Key, Value> node, Key k) {
        if (node == null || k == null)
            return null;

        int compareResult = compare(k, node.getKey());
        if (compareResult == 0)
            return node;
        else if (compareResult < 0)
            return search(node.getLeftChild(), k);
        else
            return search(node.getRightChild(), k);
    }

    @Override
    public Value getValue(Key k) {
        BinaryTreeNode<Key, Value> node = getNode(k);
        if (node != null)
            return node.getValue();
        return null;
    }

    @Override
    public BinaryTreeNode<Key, Value> getLeftChild(BinaryTreeNode<Key, Value> node) {
        if (node == null) {
            return null;
        }
        return node.getLeftChild();
    }

    @Override
    public BinaryTreeNode<Key, Value> getRightChild(BinaryTreeNode<Key, Value> node) {
        if (node == null) {
            return null;
        }
        return node.getRightChild();
    }

    @Override
    public BinaryTreeNode<Key, Value> sibling(BinaryTreeNode<Key, Value> node) {
        if (node == null || node.getParent() == null)
            return null;

        BinaryTreeNode<Key, Value> parent = node.getParent();
        if (parent.getLeftChild() == node)
            return parent.getRightChild();
        else
            return parent.getLeftChild();
    }



    @Override
    public List<BinaryTreeNode<Key, Value>> getNodesInOrder() {
        List<BinaryTreeNode<Key, Value>> nodeList = new ArrayList<>();
        inOrderTraversal(root, nodeList);
        return nodeList;
    }

    private void inOrderTraversal(BinaryTreeNode<Key, Value> node, List<BinaryTreeNode<Key, Value>> nodeList) {
        if (node == null)
            return;

        inOrderTraversal(node.getLeftChild(), nodeList);
        nodeList.add(node);
        inOrderTraversal(node.getRightChild(), nodeList);
    }

    @Override
    public void setComparator(Comparator<Key> C) {
        this.comparator = C;
    }

    @Override
    public Comparator<Key> getComparator() {
        return comparator;
    }

    @Override
    public BinaryTreeNode<Key, Value> ceiling(Key k) {
        BinaryTreeNode<Key, Value> current = root;
        BinaryTreeNode<Key, Value> ceiling = null;
        while (current != null) {
          if (comparator.compare(k, current.getKey()) == 0) {
            return current;
          } else if (comparator.compare(k, current.getKey()) < 0) {
            ceiling = current;
            current = current.getLeftChild();
          } else {
            current = current.getRightChild();
          }
        }
        return ceiling;
    }

    @Override
    public BinaryTreeNode<Key, Value> floor(Key k) {
        BinaryTreeNode<Key, Value> current = root;
        BinaryTreeNode<Key, Value> floor = null;
        while (current != null) {
          if (comparator.compare(k, current.getKey()) == 0) {
            return current;
          } else if (comparator.compare(k, current.getKey()) < 0) {
            current = current.getLeftChild();
          } else {
            floor = current;
            current = current.getRightChild();
          }
        }
        return floor;
    }


    private int compare(Key k1, Key k2) {
        if (comparator != null)
            return comparator.compare(k1, k2);
        else
            return ((Comparable<Key>) k1).compareTo(k2);
    }
}
