package testB;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;
import phaseB.HashTable;
import phaseA.StringComparator;
import phaseB.StringHasher;
import providedCode.DataCount;
import providedCode.SimpleIterator;


	public class TestHashTable {
		
		 private static final int TIMEOUT = 2000;
		    HashTable<String> table;		    
		    @Before
		    //replace with an empty tree before every test
		    public void setUp() throws Exception{
		    	table = new HashTable<String>(new StringComparator(), new StringHasher());  	
		    }	   
		    
		    /** Test getSize and incCount==========================================================================**/

		    @Test(timeout=TIMEOUT)
		    // The table is empty when created
		    public void EmptyAtFirst(){
		    	assertEquals("Make a new tree and check size of tree", 0, table.getSize());
		    }
		    
		    //Helper method to add string to created hashTable and then test
		    //the size of hashTable.
		    private void testSize(String[] a, int expected){
		    	for(String s: a){
		    		table.incCount(s);
		    	}
		    	assertEquals("", expected, table.getSize());
		    }
		    @Test(timeout=TIMEOUT)
		    //add only 1 element
		    public void Adding1StringAndTestSize(){
		    	String[] s = new String[]{"X"};
		    	testSize(s,1);
		    }
		    
		    @Test(timeout=TIMEOUT)
		    //add multiple elements
		    public void AddingMultipleStrings(){
		    	String[] s = new String[]{"X", "adfs", "qerq", "urqowe", "adfadsf"};
		    	testSize(s,5);
		    }
		    
		    @Test(timeout=TIMEOUT)
		    //add repeated elements
		    public void AddingRepeatedStrings(){
		    	String[] s = new String[]{"abcs","abcs","abcs","abcs","abcs","abcs","abcs",};
		    	testSize(s,1);
		    }
	    
		    @Test(timeout=TIMEOUT)
		    //add long elements
		    public void AddingLongStrings(){
		    	String[] s = new String[]{"abcsabcsabcsabcsabcsabcsfsadfabcs", "afdsjlkajfdslhflakjdhflakjhsdflkaj"};
		    	testSize(s,2);
		    }
		    
		    @Test(timeout=TIMEOUT)
		    //add multiple repeated elements
		    public void AddingMultipleRepeatedStrings(){
		    	String[] s = new String[]{"D", "O", "D", "O", "X", "Y","X","Y"};
		    	testSize(s,4);
		    }
		    
		    @Test(timeout=TIMEOUT)
		    //add multiple repeated elements
		    public void Add1000elements(){
		    	
		    	String[] s = new String[1000];
		    	for(int i =0; i <100;i++){
		    		s[i] = "df";
		    	}
		    	testSize(s,1000);
		    }
		    
		    /** Test getCount =====================================================================================================**/
		    
		    //Helper method to add string to created hashTable and then test
		    //the size of hashTable.
		    private void testGetCount(String[] a, int expected, String st){
		    	for(String s: a){
		    		table.incCount(s);
		    	}    	
		    	assertEquals("", expected, table.getCount(st));
		    }
		    @Test(timeout=TIMEOUT)
		    //add only 1 element
		    public void Adding1StringAndTestGetCount(){
		    	String[] s = new String[]{"X"};
		    	testGetCount(s,1,"X");
		    }
		    
		    @Test(timeout=TIMEOUT)
		    //add multiple elements
		    public void AddingMultipleStringTestGetCount(){
		    	String[] s = new String[]{"X", "adfs", "qerq", "urqowe", "adfadsf"};
		    	testGetCount(s,1,"adfs");
		    }
		    
		    @Test(timeout=TIMEOUT)
		    //add repeated elements
		    public void AddingRepeatedStringsTestGetCount(){
		    	String[] s = new String[]{"abcs","abcs","abcs","abcs","abcs","abcs","abcs"};
		    	testGetCount(s,7,"abcs");
		    }
	    
		    @Test(timeout=TIMEOUT)
		    //add long elements
		    public void AddingLongStringsTestGetCount(){
		    	String[] s = new String[]{"abcsabcsabcsabcsabcsabcsfsadfabcs", "abcsabcsabcsabcsabcsabcsfsadfabcs"};
		    	testGetCount(s,2,"abcsabcsabcsabcsabcsabcsfsadfabcs");
		    }
		    
		    @Test(timeout=TIMEOUT)
		    //add multiple repeated elements
		    public void AddingMultipleRepeatedStringsTestGetCount(){
		    	String[] s = new String[]{"D", "O", "D", "O", "X", "Y","X","Y"};
		    	testGetCount(s,2,"O");
		    }

		    	
		    @Test(timeout=TIMEOUT)
			//add multiple repeated elements
			public void AddingMoreStringsTestGetCount(){
			   	String[] s = new String[]{"D", "O", "D", "O", "X", "Y","X","Y","D", "O", "D", "O"};
			   	testGetCount(s,4,"O");
		    }
		    	
		    @Test(timeout=TIMEOUT)
			//add long elements
			public void AddingMultipleLongStringsTestGetCount(){
			    String[] s = new String[]{"abcsabcsabcsabcsabcsabcsfsadfabcs", "abcsabcsabcsabcsabcsabcsfsadfabcs"
			    		,"THIS IS A REALLY LONG LONG STRING", "STRING STRING StringStringa"};
			    testGetCount(s,2,"abcsabcsabcsabcsabcsabcsfsadfabcs");
			  }		    
		    
		    /** Test Iterator =====================================================================================================**/
		    
//			@Test(timeout = TIMEOUT)
//			public void test_iterator_get_all_data() {
//				String[] startArray = {"a","b","a","b","c","f","g","h","i","j"};
//				// Expected array that being sorted and has no duplicates
//				for(int i=0; i<startArray.length;i++){
//					table.incCount(startArray[i]);
//				}
//				String[] expected = {"a","b","c","f","g","h","i","j"};
//				
//				int i =0;
//				SimpleIterator<DataCount<String>> itr = table.getIterator();
//				// actual array that returned by iterator
//				String[] actual= new String[expected.length];
//				
//				while(itr.hasNext()){
//					actual[i]=itr.next().data;
//					i++;
//				}				
//				// Sort and test
//				Arrays.sort(actual);
//				assertArrayEquals("Added " + Arrays.toString(startArray), expected, actual);
//			}
			

			@Test(timeout = TIMEOUT, expected = java.util.EmptyStackException.class)
			public void test_iterator_empty() {
				SimpleIterator<DataCount<String>> itr = table.getIterator();
				itr.next();
			}


			@Test(timeout = TIMEOUT, expected = java.util.EmptyStackException.class)
			public void iteratorThrowException(){
				// Start by adding an item, because we get NullPointerException if 
				// we try to create an iterator from an empty table. 
				table.incCount("x"); 
				SimpleIterator<DataCount<String>> iter = table.getIterator();
				iter.next(); // OK (should return x)
				iter.next(); // Exception!
			}
			
			
			
	}