package testA;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import phaseA.MoveToFrontList;
import phaseA.StringComparator;
import providedCode.Comparator;
import providedCode.DataCount;
import providedCode.SimpleIterator;

//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2
public class TestMoveToFrontList {

	private static final int TIMEOUT = 2000; 
	MoveToFrontList<String> m;
	
	/** Creates MoveToFrontList before each test cases **/
	@Before
	public void setUp() throws Exception {
		m = new MoveToFrontList<String>(new StringComparator());
	}
	
	/** Test Size =======================================================================================**/

	@Test(timeout = TIMEOUT)
	public void getSizeEmptyList(){
		assertEquals("Tree should have size 0 when constructed", m.getSize(), 0);
	}
	
	@Test(timeout = TIMEOUT)
	public void getSizeOneInsert(){
		m.incCount("a");
		assertEquals("Tree should have size 1 after adding single stirng", m.getSize(), 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void getSizeTwoInsert(){
		m.incCount("a");
		m.incCount("b");
		assertEquals("Tree should have size 2 after adding two unique strings", m.getSize(), 2);
	}
	
	@Test(timeout = TIMEOUT)
	public void getSizeDuplicate1(){
		m.incCount("a");
		m.incCount("a");
		assertEquals("Tree should have size 1 after adding two same strings", m.getSize(), 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void getSizeDuplicate2(){
		m.incCount("a");
		m.incCount("b");
		m.incCount("a");
		m.incCount("b");
		m.incCount("a");
		m.incCount("b");
		assertEquals("Tree should have size 2 after adding duplicates of two strings", m.getSize(), 2);
	}
	
	@Test(timeout = TIMEOUT)
	public void getSizeGeneral(){
		for (int i = 0; i < 100; i++) {
			m.incCount(Integer.toString(i));
		}
		assertEquals("Tree should have size 100 after adding 100 unique strings", m.getSize(), 100);
	}
	
	/** Test incCount =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void incCountEmpty(){
		assertEquals(m.getSize(), 0);
	}
	
	@Test(timeout = TIMEOUT)
	public void incCountOneTime(){
		m.incCount("a");
		assertEquals(m.getSize(), 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void incCountTwo(){
		m.incCount("a");
		m.incCount("a");		
		assertEquals(m.getSize(), 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void incCountTwoDifferent(){
		m.incCount("a");
		m.incCount("a");		
		assertEquals(m.getSize(), 2);
	}
	
	@Test(timeout = TIMEOUT)
	public void incCountGeneral(){
		//check size after each insertion
		m.incCount("a");
		assertEquals(m.getSize(), 1);
		m.incCount("a");
		assertEquals(m.getSize(), 1);
		m.incCount("b");
		assertEquals(m.getSize(), 2);
		m.incCount("a");
		m.incCount("b");
		m.incCount("c");
		assertEquals(m.getSize(), 3);
		m.incCount("b");
		m.incCount("d");
		assertEquals(m.getSize(), 4);
	}
	
	/** Test getCount =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void getCountEmpty(){
		assertEquals(m.getCount("a"), 0);
	}
	
	@Test(timeout = TIMEOUT)
	public void getCountOneTime(){
		m.incCount("a");
		assertEquals(m.getCount("a"), 1);
	}
	
	@Test(timeout = TIMEOUT)
	public void getCountTwoTimes(){
		m.incCount("a");
		m.incCount("a");		
		assertEquals(m.getCount("a"), 2);
	}
	
	@Test(timeout = TIMEOUT)
	public void getCountGeneral(){
		m.incCount("a");
		m.incCount("a");
		m.incCount("a");
		m.incCount("a");
		m.incCount("b");
		m.incCount("c");
		m.incCount("b");
		m.incCount("d");
		assertEquals(m.getCount("d"), 1);
		assertEquals(m.getCount("a"), 4);
		assertEquals(m.getCount("b"), 2);
		assertEquals(m.getCount("c"), 1);
	}
	
	/** Test getIterator =======================================================================================**/

	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void test_iterator_empty() {
		SimpleIterator<DataCount<Integer>> iter = m.getIterator();
		iter.next(); 
	}
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data() {
		List<Integer> startArray = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			startArray.add(i);
		}
		
		for (int i = 0; i < 5; i ++) {
			m.incCount(Integer.toString(i));
		}
		List<String> expected = new LinkedList<String>();
		expected.add("4");
		expected.add("3");
		expected.add("2");
		expected.add("1");
		expected.add("0");

		// Actual array returned by the iterator
		SimpleIterator<DataCount<Integer>> iter = m.getIterator();
		List<String> actual = new LinkedList<String>();
		
		for (int j = 0; j < 5; j++) {
			actual.add(Integer.toString(iter.next().data)); 
		}
		assertEquals("Added " + startArray.toArray().toString(), expected, actual);
	}	
}

