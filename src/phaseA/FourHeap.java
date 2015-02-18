package phaseA;
import java.util.NoSuchElementException;
import providedCode.*;
//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2

/*
* This class called FourHep is an implementation extends from Heap 
* that represent a heap, such that each node has four children and
* only leaf will have fewer children. And minimum value always at 
* top of the tree which will be more convenient to find it.
*/
public class FourHeap<E> extends Heap<E> {
	private Comparator<? super E> comparator;
	private int size;
	private E[] heapArray;

	/*
	 * Creates an empty FourHeap object using given Comparator
	 * to compare data.
	 */
	@SuppressWarnings("unchecked")
	public FourHeap(Comparator<? super E> c) {
		// constructor
		comparator = c;
		size = 0;
		heapArray = (E[]) new Object[10];
	}

	/*
	 * Inserts given data into the priority queue, maintaining heap order.
	 * Increase size of FourHeap. 
	 * Double the heap size when it is full.
	 */
	@Override
	public void insert(E item) {
		if (size == heapArray.length) {
			enlargeArray(heapArray.length * 2 + 1);
		}
		size++;
		heapArray[size - 1] = item;
		percolateUp();
	}

	/*
	 * Internal method to percolate up inserted data to suitable position in the heap. 
	 * The minimum data must always at the top of the heap.
	 * And parent must be larger than all of its children
	 */
	private void percolateUp() {
		int hole = size - 1;
		boolean done = false;
		while (!done && hole > 0) {
			int parent = (hole - 1) / 4;
			if (comparator.compare(heapArray[hole], heapArray[parent]) < 0) {
				E temp = heapArray[hole];
				heapArray[hole] = heapArray[parent];
				heapArray[parent] = temp;
				hole = parent;
			} else {
				done = true;
			}
		}

	}

	/*
	 * Helper method to enlarge of the structures that represent FourHeap
	 * with given size.
	 */
	@SuppressWarnings("unchecked")
	private void enlargeArray(int newSize) {
		E[] old = heapArray;
		heapArray = (E[]) new Object[newSize];
		for (int i = 0; i < old.length; i++)
			heapArray[i] = old[i];
	}

	/*
	 * Remove the smallest item from the priority queue. Returns the smallest
	 * item, or throws an UnderflowException if empty.
	 */
	@Override
	public E deleteMin() {
		exception();
		E minItem = heapArray[0];
		heapArray[0] = heapArray[size - 1];
		size--;
		percolateDown(0);
		return minItem;

	}

	/*
	 * Internal method to percolate down in the heap. Starts percolate from the
	 * given index.
	 */
	private void percolateDown(int hole) {
		int child;
		E tmp = heapArray[hole];

		for (; hole * 4 + 1 < size; hole = child) {
			child = hole * 4 + 1;
			child = minChild(hole);
			if (comparator.compare(heapArray[child], tmp) < 0)
				heapArray[hole] = heapArray[child];
			else
				break;
		}
		heapArray[hole] = tmp;
	}

	/*
	 * Helper method to find the smallest child of given parent and
	 * returns its position.
	 */
	private int minChild(int hole) {
		int first = 4 * hole + 1;
		int i = 1;
		int min = first;
		while (first < size && first + i < size && i <= 3) {
			if (comparator.compare(heapArray[min], heapArray[first + i]) > 0) {
				min = first + i;
			}
			i++;
		}
		return min;
	}

	/*
	 * Checks if the heap is empty. Returns true if it is empty, otherwise,
	 * return false;
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * Returns the smallest value in the heap without deleting it
	 * Throws an exception if Heap is empty.
	 */
	@Override
	public E findMin() {
		exception();
		return heapArray[0];
	}

	/*
	 * Helper method to throw exception if the heap is empty.
	 */
	private void exception() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	}
}