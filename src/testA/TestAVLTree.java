package testA;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.Before;
import phaseA.AVLTree;
import phaseA.StringComparator;
import providedCode.*;

//Xiaoyan Chen (h13579)  (AC)
//Yingying Wang (yingyw) (AA)
//CSE 332 Project2

public class TestAVLTree {
	private static final int TIMEOUT = 2000;
	AVLTree<String> tree;

	@Before
	public void setUp() throws Exception {
		tree = new AVLTree<String>(new StringComparator());
	}

	/** Test Size =======================================================================================**/
	@Test(timeout = TIMEOUT)
	public void size0WhenCreatedTree() {
		assertEquals("Constructed a tree and checked its size", 0,
				tree.getSize());
	}

	private void insertAndTestSize(String[] AVLTree, int expected) {
		for (int i = 0; i < AVLTree.length; i++)
			tree.incCount(AVLTree[i]);
		assertEquals("Added list of numbers to tree", expected, tree.getSize());
	}

	@Test(timeout = TIMEOUT)
	public void size1WhenAdding1Element() {
		insertAndTestSize(new String[] { "a" }, 1);
	}

	@Test(timeout = TIMEOUT)
	public void size1AfterAddingDuplicatedElements() {
		insertAndTestSize(new String[] { "a", "a", "a", "a" }, 1);
	}

	@Test(timeout = TIMEOUT)
	public void size8AfterAdding8UniqueElements() {
		insertAndTestSize(new String[] { "a", "b", "c", "d", "e", "f", "g", "h" }, 8);
	}

	@Test(timeout = TIMEOUT)
	public void size8AfterAddingRepeat0To7s() {
		insertAndTestSize(new String[] { "a", "a", "b", "b", "c", "c","d", "d", "e", 
				"e", "f", "f", "g", "g", "h", "h" }, 8);
	}

	@Test(timeout = TIMEOUT)
	public void size8AfterAddingRepeat0To7InSequence() {
		insertAndTestSize(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", 
				"a", "b", "c", "d", "e", "f", "g", "h" }, 8);
	}

	/** Test getCount =======================================================================================**/
	private void getCountAfterInsert(String[] AVLTree, String getNum, int expected) {
		for (int i = 0; i < AVLTree.length; i++)
			tree.incCount(AVLTree[i]);
		int actual = tree.getCount(getNum);
		assertEquals("Added node and got count of " + getNum, expected, actual);
	}

	@Test(timeout = TIMEOUT)
	public void treeHas5Of7() {
		getCountAfterInsert(new String[] { "a", "a", "a", "a", "a" }, "a", 5);
	}

	@Test(timeout = TIMEOUT)
	public void treeHas4Of2() {
		getCountAfterInsert(new String[] { "a", "b", "cd", "ab", "a", "a", "er", "a"}, "a", 4);
	}

	@Test(timeout = TIMEOUT)
	public void treeNotContainExpectedNum() {
		getCountAfterInsert(new String[] { "a", "c", "ed", "as", "3e", "sdf", "f", "xsd", "ee" }, "z",
				0);
	}

	@Test(timeout = TIMEOUT)
	public void emptyTreeGetCount() {
		getCountAfterInsert(new String[] {}, "dd", 0);
	}

	/** Test Iterator =======================================================================================**/
	
	@Test(timeout = TIMEOUT)
	public void test_iterator_get_all_data() {
		String[] startArray = {"a", "eee", "c", "f", "e", "d", "g", "h", "b", "sss"};
		
		// Expected array has no duplicates and is sorted
		for(int i = 0; i < startArray.length; i++) { tree.incCount(startArray[i]); }
		String[] expected = {"a", "b", "c", "d", "e", "eee", "f", "g", "h", "sss"};
		
		// Actual array returned by the iterator
		int i = 0;
		SimpleIterator<DataCount<String>> iter = tree.getIterator();
		String[] actual = new String[expected.length];
		while(iter.hasNext()) { actual[i++] = iter.next().data; }
		
		// Sort and test
		Arrays.sort(actual);
		assertArrayEquals("Added " + Arrays.toString(startArray), expected, actual);
	}
	

	@Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
	public void test_iterator_empty() {
		SimpleIterator<DataCount<String>> iter = tree.getIterator();
		iter.next();
	}

	/** Private methods =======================================================================================**/
	private void add(String[] AVLTree, int expected) {
		for (int i = 0; i < AVLTree.length; i++)
			tree.incCount(AVLTree[i]);
		assertEquals(expected, tree.height());
	}

	@Test(timeout = TIMEOUT)
	public void heightNoInsertTest() {
		add(new String[] {}, -1);
	}

	@Test(timeout = TIMEOUT)
	public void height7InsertsOf9Test() {
		add(new String[] { "a", "a", "a", "a", "a", "a", "a" }, 0);
	}
    
	@Test(timeout = TIMEOUT)
	public void checkBalanceAfterInsertingDescendingNum(){
		add(new String[] { "e", "a", "g", "s", "ea", "ggg", "zzz" }, 2);
	}
	@Test(timeout = TIMEOUT)
	public void heightWith2ELements() {
		add(new String[] { "ge", "ss" }, 1);
	}

	@Test(timeout = TIMEOUT)
	public void heightWithMoreELements() {
		add(new String[] { "g", "a", "sd", "gg", "i", "am", "gggg", "efef" }, 3);
	}
	
	// Test rotation
	@Test(timeout = TIMEOUT)
	public void testLeftRotations() {
		// test the size and the height of the tree after every different insert
		tree.incCount("a");
		assertEquals(1, tree.getSize());
		assertEquals(0, tree.height());

		tree.incCount("b");
		assertEquals(2, tree.getSize());
		assertEquals(1, tree.height());

		tree.incCount("c");
		assertEquals(3, tree.getSize());
		assertEquals(1, tree.height());

		tree.incCount("d");
		assertEquals(4, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("e");
		assertEquals(5, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("f");
		assertEquals(6, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("g");
		assertEquals(7, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("h");
		assertEquals(8, tree.getSize());
		assertEquals(3, tree.height());

		tree.incCount("i");
		assertEquals(9, tree.getSize());
		assertEquals(3, tree.height());

		tree.incCount("j");
		assertEquals(10, tree.getSize());
		assertEquals(3, tree.height());
	}

	@Test(timeout = TIMEOUT)
	public void testRightRotations() {
		// test the size and the height of the tree after every different insert
		tree.incCount("j");
		assertEquals(1, tree.getSize());
		assertEquals(0, tree.height());

		tree.incCount("i");
		assertEquals(2, tree.getSize());
		assertEquals(1, tree.height());

		tree.incCount("h");
		assertEquals(3, tree.getSize());
		assertEquals(1, tree.height());

		tree.incCount("g");
		assertEquals(4, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("f");
		assertEquals(5, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("e");
		assertEquals(6, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("d");
		assertEquals(7, tree.getSize());
		assertEquals(2, tree.height());

		tree.incCount("c");
		assertEquals(8, tree.getSize());
		assertEquals(3, tree.height());

		tree.incCount("b");
		assertEquals(9, tree.getSize());
		assertEquals(3, tree.height());

		tree.incCount("a");
		assertEquals(10, tree.getSize());
		assertEquals(3, tree.height());
	}

	@Test(timeout = TIMEOUT)
	public void testLeftRightRotation() {
		tree.incCount("6");
		tree.incCount("4");
		tree.incCount("8");
		tree.incCount("2");
		tree.incCount("5");
		tree.incCount("7");
		tree.incCount("9");
		tree.incCount("1");
		tree.incCount("3");
		tree.incCount("5");
		tree.incCount("6");
		tree.incCount("6");
		assertEquals(9, tree.getSize());
		assertEquals(3, tree.height());
	}

	@Test(timeout = TIMEOUT)
	public void testRightLeftRotation() {
		tree.incCount("e");
		tree.incCount("b");
		tree.incCount("h");
		tree.incCount("f");
		tree.incCount("i");
		tree.incCount("g");
		assertEquals(6, tree.getSize());
		assertEquals(2, tree.height());
	}

}




