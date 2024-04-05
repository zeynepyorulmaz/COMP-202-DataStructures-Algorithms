package code;

import java.util.Arrays;

import given.AbstractArraySort;

public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {

    public MergeSort() {
        name = "Mergesort";
    }

    @Override
    public void sort(K[] inputArray) {
        if (inputArray == null || inputArray.length <= 1)
            return;

        mergeSort(inputArray, 0, inputArray.length - 1);
    }

    private void mergeSort(K[] inputArray, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;

            mergeSort(inputArray, low, mid);
            mergeSort(inputArray, mid + 1, high);

            merge(inputArray, low, mid, high);
        }
    }

    public void merge(K[] inputArray, int low, int mid, int high) {
        int leftLength = mid - low + 1;
        int rightLength = high - mid;

        K[] leftArray = Arrays.copyOfRange(inputArray, low, mid + 1);
        K[] rightArray = Arrays.copyOfRange(inputArray, mid + 1, high + 1);

        int leftIndex = 0, rightIndex = 0, mergedIndex = low;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (compare(leftArray[leftIndex], rightArray[rightIndex]) <= 0) {
                inputArray[mergedIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                inputArray[mergedIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        while (leftIndex < leftLength) {
            inputArray[mergedIndex] = leftArray[leftIndex];
            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < rightLength) {
            inputArray[mergedIndex] = rightArray[rightIndex];
            rightIndex++;
            mergedIndex++;
        }
    }
}
