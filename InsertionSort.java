package code;



import given.AbstractArraySort;

public class InsertionSort<K extends Comparable<K>> extends AbstractArraySort<K> {

    public InsertionSort() {
        name = "Insertionsort";
    }

    @Override
    public void sort(K[] inputArray) {
	        for (int i = 1; i < inputArray.length; i++) {
	            int j = i - 1;
	            while (j >= 0 && compare(inputArray[j], inputArray[i]) > 0) {
	                inputArray[i] = inputArray[j];
	                j--;
	            }
	
	            if (j + 1 != i) {
	                swap(inputArray, (j + 1), i);
	            }
	        }

    }
}
