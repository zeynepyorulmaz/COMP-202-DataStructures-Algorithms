package code;

import given.AbstractArraySort;

/*
 * Implement the quick-sort algorithm here. You can look at the slides for the pseudo-codes.
 * Make sure to use the swap and compare functions given in the AbstractArraySort!
 * 
 */

public class QuickSort<K extends Comparable<K>> extends AbstractArraySort<K> {  
  //Add any fields here
  
  public QuickSort()
  {
    name = "Quicksort";
    
  //Initialize anything else here
  }
  
  //useful if we want to return a pair of indices from the partition function.
  //You do not need to use this if you are just returning and integer from the partition
  public class indexPair {
    public int p1, p2;
    
    indexPair(int pos1, int pos2)
    {
      p1 = pos1;
      p2 = pos2;
    }
    
    public String toString()
    {
      return "(" + Integer.toString(p1) + ", " + Integer.toString(p2) + ")";
    }
  }
  
  
  @Override
  public void sort(K[] inputArray)
  {
    quickSort(inputArray, 0, inputArray.length - 1);
  }

  public void quickSort(K[] inputArray, int lo, int hi)
  {
    if (lo < hi) {
    	int pivot = pickPivot(inputArray, lo, hi);
      int p = partition(inputArray, lo, hi, pivot); 
      quickSort(inputArray, lo, p - 1); 
      quickSort(inputArray, p + 1, hi); 
    }
  }
  

  protected int pickPivot(K[] inputArray, int lo, int hi)
  {
    return hi;
  }

	public int partition(K[] inputArray, int lo, int hi, int p) {
		K pivot = inputArray[p];
	    int i = lo;
	    swap(inputArray, p, hi);
	    
	    for (int j = lo; j < hi; j++) {
	      if (compare(inputArray[j], pivot) < 0) {
	        swap(inputArray, i, j);
	        i++;
	      }
	    }
	    swap(inputArray, i, hi);
	    return i;
	}
}
