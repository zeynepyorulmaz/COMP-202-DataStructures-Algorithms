package code;

import java.util.ArrayList;
import java.util.Comparator;

import given.Entry;
import given.iAdaptablePriorityQueue;

public class ArrayBasedHeap<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {

    protected ArrayList<Entry<Key, Value>> nodes;
    protected Comparator<Key> comparator;
    private int size;

    public ArrayBasedHeap() {
        nodes = new ArrayList<>();
        comparator = null; 
        size = 0; 
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0; 
    }

    @Override
    public void setComparator(Comparator<Key> C) {
        comparator = C;
    }

    @Override
    public Comparator<Key> getComparator() {
        return comparator;
    }

    @Override
    public void insert(Key k, Value v) {
        Entry<Key, Value> entry = new Entry<>(k, v);
        nodes.add(entry);
        size++;
        upHeap(size - 1); 
    }

    @Override
    public Entry<Key, Value> pop() {
        if (isEmpty())
            return null;

        Entry<Key, Value> root = nodes.get(0);
        Entry<Key, Value> lastEntry = nodes.remove(size - 1);
        size--;
        if (!isEmpty()) {
            nodes.set(0, lastEntry);
            downHeap(0);
        }
        return root;
    }

    @Override
    public Entry<Key, Value> top() {
        if (isEmpty())
            return null;
        return nodes.get(0);
    }

    @Override
    public Value remove(Key k) {
        for (int i = 0; i < size; i++) {
            Entry<Key, Value> entry = nodes.get(i);
            if (entry.getKey().equals(k)) {
                Entry<Key, Value> lastEntry = nodes.get(size - 1); 
                nodes.set(i, lastEntry); 
                nodes.remove(size - 1); 
                size--;
                if (i < size) { 
                    if (comparator != null) {
                        if (compare(lastEntry.getKey(), entry.getKey()) < 0) {
                            upHeap(i); 
                        } else {
                            downHeap(i); 
                        }
                    } else {
                        if (((Comparable<Key>) lastEntry.getKey()).compareTo(entry.getKey()) < 0) {
                            upHeap(i); 
                        } else {
                            downHeap(i); 
                        }
                    }
                }
                return entry.getValue();
            }
        }
        return null; 
    }


    public Key replaceKey(Entry<Key, Value> entry, Key k) {
        for (int i = 0; i < size; i++) {
            if (nodes.get(i).equals(entry)) {
                Key oldKey = nodes.get(i).getKey();
                nodes.get(i).setKey(k);
                if (comparator.compare(k, oldKey) < 0) {
                    upHeap(i);
                } else if (comparator.compare(k, oldKey) > 0) {
                    downHeap(i);
                }
                return oldKey;
            }
        }
        return null;
    }

    @Override
    public Key replaceKey(Value v, Key k) {
        for (int i = 0; i < size; i++) {
            if (nodes.get(i).getValue().equals(v)) {
                Key oldKey = nodes.get(i).getKey();
                nodes.get(i).setKey(k);
                if (comparator.compare(k, oldKey) < 0) {
                    upHeap(i);
                } else {
                    downHeap(i);
                }
                return oldKey;
            }
        }
        return null;
    }

    @Override
    public Value replaceValue(Entry<Key, Value> entry, Value v) {
        Value oldValue = entry.getValue();
        for (int i = 0; i < size; i++) {
            if (nodes.get(i).getValue().equals(oldValue) && nodes.get(i).getKey().equals(entry.getKey())) {
                nodes.get(i).setValue(v);
                return oldValue;
            }
        }
        return null;
    }

    private void upHeap(int index) {
        if (index <= 0 || index >= size) { 
            return;
        }

        Entry<Key, Value> entry = nodes.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            Entry<Key, Value> parent = nodes.get(parentIndex);
            if (compare(entry.getKey(), parent.getKey()) >= 0)
                break;
            nodes.set(index, parent);
            index = parentIndex;
        }
        nodes.set(index, entry);
    }

    private void downHeap(int index) {
        int lastIndex = size - 1;
        int leftChildIndex = 2 * index + 1;

        while (leftChildIndex <= lastIndex) {
            int smallerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;

            if (rightChildIndex <= lastIndex && compare(nodes.get(rightChildIndex).getKey(),
                    nodes.get(leftChildIndex).getKey()) < 0) {
                smallerChildIndex = rightChildIndex;
            }

            if (compare(nodes.get(smallerChildIndex).getKey(), nodes.get(index).getKey()) >= 0) {
                break;
            }

            Entry<Key, Value> temp = nodes.get(index);
            nodes.set(index, nodes.get(smallerChildIndex));
            nodes.set(smallerChildIndex, temp);

            index = smallerChildIndex;
            leftChildIndex = 2 * index + 1;
        }
    }


    private int compare(Key k1, Key k2) {
        if (comparator != null)
            return comparator.compare(k1, k2);
        else
            return ((Comparable<Key>) k1).compareTo(k2);
    }
}
