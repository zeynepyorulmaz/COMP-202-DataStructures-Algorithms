package code;

import given.AbstractArraySort;

public class CountingSort<K extends Comparable<K>> extends AbstractArraySort<K> {

    // Add any fields here

    public CountingSort() {
        name = "Countingsort";
    }

    @Override
    public void sort(K[] inputArray) {

        int max = Integer.MIN_VALUE;
        for (K element : inputArray) {
            int value = (int) element;
            if (value > max) {
                max = value;
            }
        }

        int[] countArray = new int[max + 1];

    
        for (K element : inputArray) {
            int value = (int) element;
            countArray[value]++;
        }

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }


        Object[] outputArray = new Object[inputArray.length];


        for (int i = inputArray.length - 1; i >= 0; i--) {
            int value = (int) inputArray[i];
            int index = countArray[value] - 1;
            outputArray[index] = inputArray[i];
            countArray[value]--;
        }

        System.arraycopy(outputArray, 0, inputArray, 0, inputArray.length);
    }
}
