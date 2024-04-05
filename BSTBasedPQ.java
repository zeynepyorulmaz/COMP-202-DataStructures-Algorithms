package code;

import given.Entry;
import given.iAdaptablePriorityQueue;

public class BSTBasedPQ<Key, Value> extends BinarySearchTree<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

    /*
     * 
     * YOUR CODE BELOW THIS
     * 
     */

    @Override
    public void insert(Key k, Value v) {
        put(k, v); 
    }

    @Override
    public Entry<Key, Value> pop() {
        if (isEmpty())
            return null;

        Entry<Key, Value> minEntry = findMinEntry(getRoot());
        remove(minEntry.getKey());
        return minEntry;
    }

    private Entry<Key, Value> findMinEntry(BinaryTreeNode<Key, Value> node) {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return new Entry<>(node.getKey(), node.getValue());
    }


    @Override
    public Entry<Key, Value> top() {
        if (isEmpty())
            return null;
        return findMinEntry(getRoot());
    }

    @Override
    public Key replaceKey(Entry<Key, Value> entry, Key k) {
        BinaryTreeNode<Key, Value> node = getNode(entry.getKey());
        if (node == null)
            return null;

        Value value = node.getValue();
        remove(entry.getKey());
        put(k, value);
        return entry.getKey();
    }

    @Override
    public Key replaceKey(Value v, Key k) {
        Entry<Key, Value> entry = null;
        for (BinaryTreeNode<Key, Value> node : getNodesInOrder()) {
            if (node.getValue().equals(v)) {
                entry = new Entry<>(node.getKey(), node.getValue());
                break;
            }
        }
        if (entry != null)
            return replaceKey(entry, k);
        else
            return null;
    }

    @Override
    public Value replaceValue(Entry<Key, Value> entry, Value v) {
        BinaryTreeNode<Key, Value> node = getNode(entry.getKey());
        if (node == null)
            return null;

        Value oldValue = node.getValue();
        node.setValue(v);
        return oldValue;
    }
}
