// Xiaoyan Chen (h13579)  (AC)
// Yingying Wang (yingyw) (AA)
// CSE 332 Project2
/*
 * Provides different sorting methods, and sorts given counts in different ways.
 */
package main;

import phaseA.FourHeap;
import providedCode.Comparator;

public class Sorter {

	/**
	 * Sort the count array in descending order of count. If two elements have
	 * the same count, they should be ordered according to the comparator. This
	 * code uses insertion sort. The code is generic, but in this project we use
	 * it with DataCount<String> and DataCountStringComparator.
	 * 
	 * @param counts
	 *            array to be sorted.
	 * @param comparator
	 *            for comparing elements.
	 */
	public static <E> void insertionSort(E[] array, Comparator<E> comparator) {
		for (int i = 1; i < array.length; i++) {
			E x = array[i];
			int j;
			for (j = i - 1; j >= 0; j--) {
				if (comparator.compare(x, array[j]) >= 0) {
					break;
				}
				array[j + 1] = array[j];
			}
			array[j + 1] = x;
		}
	}
	
	/*
	 * Sort the given array in descending order of counts. If more than one
	 * elements have the same amount, sort them according to the given 
	 * comparator.
	 */
	public static <E> void heapSort(E[] array, Comparator<E> comparator) {
		FourHeap<E> heap = new FourHeap<E>(comparator);
		for (int i = 0; i < array.length; i++) {
			heap.insert(array[i]);
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = heap.deleteMin();
		}
	}

	public static <E> void topKSort(E[] array, Comparator<E> comparator, int k) {
		FourHeap<E> heap = new FourHeap<E>(comparator);
		for (int i = 0; i < array.length; i++) {
			heap.insert(array[i]);
		}
		for (int i = 0; i < k; i++) {
			array[i] = heap.deleteMin();
		}
		for (int i = k; i < array.length; i ++) {
			array[i] = null;
		}
	}

	public static <E> void otherSort(E[] array, Comparator<E> comparator) {

		QuickSort<E> quick = new QuickSort<E>(comparator);
		quick.quicksort(array);
	}

}