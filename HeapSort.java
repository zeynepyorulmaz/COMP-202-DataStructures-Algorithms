package code;

import given.AbstractArraySort;

public class HeapSort<K extends Comparable<K>> extends AbstractArraySort<K> {

    public HeapSort() {
        name = "Heapsort";
    }

    @Override
    public void sort(K[] inputArray) {
        heapify(inputArray); 
        int n = inputArray.length;

        for (int i = n - 1; i > 0; i--) {
            swap(inputArray, 0, i); 
            downheap(inputArray, 0, i); 
        }
    }

    public void heapify(K[] inputArray) {
        int n = inputArray.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            downheap(inputArray, i, n);
        }
    }

    protected void downheap(K[] inputArray, int i, int heapSize) {
        int largest = i; 
        int leftChild = 2 * i + 1; 
        int rightChild = 2 * i + 2; 


        if (leftChild < heapSize && compare(inputArray[leftChild], inputArray[largest]) > 0) {
            largest = leftChild;
        }

        if (rightChild < heapSize && compare(inputArray[rightChild], inputArray[largest]) > 0) {
            largest = rightChild;
        }

        if (largest != i) {
            swap(inputArray, i, largest);
            downheap(inputArray, largest, heapSize); 
        }
    }
}
