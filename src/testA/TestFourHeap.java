package testA;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import phaseA.FourHeap;
import phaseA.StringComparator;

//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2
public class TestFourHeap {
	private static final int TIMEOUT = 2000;
	FourHeap<String> heap;

	/** Creates FourHeap before each test cases **/
	@Before
	public void setUp() throws Exception {
		heap = new FourHeap<String>(new StringComparator()); 
	}

	/** Test Size =======================================================================================**/
	@Test(timeout = TIMEOUT)
	public void emptyHeap() {
		assertTrue("Initially empty", heap.isEmpty());
	}

	/** Test Insert =======================================================================================**/
	@Test(timeout = TIMEOUT)
	public void insertOneElement() {
		heap.insert("a");
		assertFalse("one insert", heap.isEmpty());
	}

	@Test(timeout = TIMEOUT)
	public void insertTwoElements() {
		heap.insert("a");
		heap.insert("b");
		assertFalse("two inserts", heap.isEmpty());
	}

	@Test(timeout = TIMEOUT)
	public void hundredInsertTest() {
		for (int x = 0; x < 100; x++) {
			heap.insert(Integer.toString(x));
		}
		assertNotNull("Hundred inserts", heap);
	}

	@Test(timeout = TIMEOUT)
	public void notNullAfterInsert() {
		heap.insert("a");
		assertNotNull("one insert", heap);
	}

	/** Test DeleteMin =======================================================================================**/
	
	@Test (expected = java.util.NoSuchElementException.class)
	public void deleteMinthrowException() {
		heap.deleteMin();
	}

	@Test(timeout = TIMEOUT)
	public void deleteOneElement() {
		heap.insert("a");
		String expected = heap.deleteMin();
		assertEquals("Simple delete", "a", expected);
	}

	@Test(timeout = TIMEOUT)
	public void deleteMultipleElements() {
		String[] lst = {"a", "adf", "azf", "ddd","aff", "zad", "b", "hel"};
		for (String item: lst) {
			heap.insert(item);
		}
		String expected = heap.deleteMin();
		assertEquals("Repeated deletes", "a", expected);
		expected = heap.deleteMin();
		assertEquals("Repeated deletes", "adf", expected);
		expected = heap.deleteMin();
		assertEquals("Repeated deletes", "aff", expected);
		expected = heap.deleteMin();
		assertEquals("Repeated deletes", "azf", expected);
		expected = heap.deleteMin();
		assertEquals("Repeated deletes", "b", expected);
		expected = heap.deleteMin();
		assertEquals("Repeated deletes", "ddd", expected);
		expected = heap.deleteMin();
		assertEquals("Repeated deletes", "hel", expected);
		expected = heap.deleteMin();
		assertEquals("Repeated deletes", "zad", expected);
		
	}
	
	@Test(timeout = TIMEOUT)
	public void notNullAfterAlternatingInsertionAndDeletion() {
		for (int x = 0; x < 100; x++) {
			heap.insert(Integer.toString(x));
			heap.deleteMin();
		}
		assertNotNull("Alternating insert", heap);
	}
	
	@Test(timeout = TIMEOUT)
	public void insertionOneAndDeleteMin() {
		heap.insert("a");
		heap.deleteMin();
		assertTrue("Sole value deleted", heap.isEmpty());
	}

	@Test(timeout = TIMEOUT)
	public void alternatingInsertionAndDeletion() {
		for (int x = 0; x < 50; x++) {
			heap.insert(Integer.toString(x));
			heap.deleteMin();
		}
		assertTrue("Alternating insertion and deleteion", heap.isEmpty());
	}

	/** Test FindMin =======================================================================================**/
	@Test(timeout = TIMEOUT)
	public void findMinOneString() {
		heap.insert("a");
		String expected = heap.findMin();
		assertEquals("Simple find", "a", expected);
	}

	@Test(timeout = TIMEOUT)
	public void findMinTwoStrings() {
		heap.insert("a");
		heap.insert("b");
		String expected = heap.findMin();
		assertEquals("2 inserts, find", "a", expected);
	}

	@Test(timeout = TIMEOUT)
	public void findMinMultipleStrings() {
		// test similar strings
		heap.insert("aab");
		heap.insert("aba");
		heap.insert("baa");
		String expected = heap.findMin();
		assertEquals("3 similar inserts, find", "aab", expected);
	}

	@Test(timeout = TIMEOUT)
	public void findMinDuplicate() {
		heap.insert("a");
		heap.insert("a");
		heap.insert("a");
		String expected = heap.findMin();
		assertEquals("Duplicate insert, find", "a", expected);
	}

	@Test(timeout = TIMEOUT)
	public void findMinDuplicate2() {
		heap.insert("a");
		heap.insert("b");
		heap.insert("a");
		heap.insert("c");
		heap.insert("e");
		heap.insert("a");
		String expected = heap.findMin();
		assertEquals("Duplicate with others, find", "a", expected);
	}

	@Test(timeout = TIMEOUT)
	public void findMinDuplicate3() {
		// test similar strings
		heap.insert("ababa");
		heap.insert("ababc");
		heap.insert("ababa");
		heap.insert("ababa");
		heap.insert("ababe");
		String expected = heap.findMin();
		assertEquals("Duplicate with others, find", "ababa", expected);
	}


	@Test(timeout = TIMEOUT)
	public void findMinGeneral() {
		for (int x = 0; x < 100; x++) {
			heap.insert(Integer.toString(x));
			String z = heap.findMin();
			assertEquals("Min remains the same after multiple calls to find",
					"0", z);
		}
	}
}
