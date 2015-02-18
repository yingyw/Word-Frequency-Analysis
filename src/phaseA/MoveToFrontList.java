package phaseA;
import providedCode.*;
//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2
/*
* This class extends from the DataCounter<E>. Increments count of given 
* data, and move the given data to the front of list.
*/
public class MoveToFrontList<E> extends DataCounter<E> {
	protected Comparator<? super E> comparator;
	protected LinkNode front;
	protected int size;

	// Initialize original empty list
	public MoveToFrontList(Comparator<? super E> c) {
		front = null;
		size = 0;
		comparator = c;
	}

	/*
	 * Increment the count for given data element if it's already in list and
	 * move the given data to the front of list.If given data is not in the
	 * list, add it to the front of list.
	 */
	@Override
	public void incCount(E data) {
		boolean notFound = true;
		// if list is empty add given data to list
		if (front == null) {
			front = new LinkNode(data);
			size++;
			notFound = false;
			// if list is not empty, check whether first data in list
			// equals to given data or not
			// If it is, increment the count
		} else if (comparator.compare(front.data, data) == 0) {
			front.count++;
			notFound = false;
		}
		// checks remaining data in list, if found, increment its count
		// and move it to front of the list
		LinkNode temp = front;
		while (temp.next != null && notFound) {
			if (comparator.compare(data, temp.next.data) == 0) {
				temp.next.count++;
				notFound = false;
				moveToFront(temp, data);
			} else {
				temp = temp.next;
			}
		}
		// If not found given data in list, add given data to front
		// of list, increment list size.
		if (notFound) {
			LinkNode cur = new LinkNode(data, front);
			front = cur;
			front.count = 1;
			size++;
		}
	}
	
	public void insert (E data, int count){
		LinkNode insertion = new LinkNode(data,count);
		insertion.next=front;
		front = insertion;
		size++;
	}

	// returns size of list
	@Override
	public int getSize() {
		return size;
	}

	// Returns count of given data and if given data is already inside list
	// move it to front of list
	@Override
	public int getCount(E data) {
		// If the list is empty returns 0
		if (front == null) {
			return 0;
		}
		// Checks first element in list
		if (comparator.compare(front.data, data) == 0) {
			return front.count;
		}
		boolean notFound = true;
		LinkNode temp = front;
		// Checks remaining elements in list, stop checking when finds
		// it. Move found element to front of list.
		while (temp.next != null && notFound) {
			if (comparator.compare(data, temp.next.data) == 0) {
				moveToFront(temp, data);
				notFound = false;
			} else {
				temp = temp.next;
			}
		}
		return front.count;
	}

	// Helper method to move given data to front of list
	private void moveToFront(LinkNode temp, E data) {
		LinkNode cur = temp.next;
		temp.next = temp.next.next;
		cur.next = front;
		front = cur;
	}

	// Gets each data in the list in the order of front to end.
	@Override
	public SimpleIterator<DataCount<E>> getIterator() {

		return new SimpleIterator<DataCount<E>>() {
			GStack<LinkNode> stack = new GArrayStack<LinkNode>();
			{
				if (front != null) {
					stack.push(front);
				}
			}

			public boolean hasNext() {
				return size != 0;
			}

			public DataCount<E> next() {
				if (!hasNext()) {
					throw new java.util.NoSuchElementException();
				}
				LinkNode nextNode = stack.pop();
				if (nextNode.next != null) {
					stack.push(nextNode.next);
				}
				return new DataCount<E>(nextNode.data, nextNode.count);
			}
		};
	}

	// Inner class creates a linked list node object that stores a
	// element inside, and a link to next list node, and count of
	// this element
	private class LinkNode {
		public E data;
		public LinkNode next;
		public int count;

		// Initialize the single LinkNode.
		public LinkNode(E data) {
			this(data, null);
		}

		// Initialize the ListNode attached with another LinkNode.
		public LinkNode(E data, LinkNode next) {
			this.data = data;
			this.next = next;
			this.count = 1;
		}
		// Initialize the ListNode attached with another LinkNode.
				public LinkNode(E data, int count) {
					this.data = data;
					this.count = count;
				}
	}

}
