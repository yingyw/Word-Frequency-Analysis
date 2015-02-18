// Xiaoyan Chen (h13579)  (AC)
// Yingying Wang (yingyw) (AA)
// CSE 332 Project2
package main;
import java.io.IOException;

import phaseA.*;
import phaseB.HashTable;
import phaseB.StringHasher;
import providedCode.*;

/*
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {

	/*
	 * Given a DataCounter object of type E, counter, and constructs
	 * and returns an array with unique words of DataCount objects.
	 */
    private static void countWords(String file, DataCounter<String> counter) {
        try {
            FileWordReader reader = new FileWordReader(file);
            String word = reader.nextWord();
            while (word != null) {
                counter.incCount(word);
                word = reader.nextWord();
            }
        }catch (IOException e) {
            System.err.println("Error processing " + file + " " + e);
            System.exit(1);
        }
    }
    

    /*
     * Constructs a list of given counting of the words
     * Returns the list
     */
 	@SuppressWarnings("unchecked")
	private static <E> DataCount<E>[] getCountsArray(DataCounter<E> counter) {
		DataCount<E>[] list = (DataCount<E>[]) new DataCount[counter.getSize()];
		SimpleIterator<DataCount<E>> itr = counter.getIterator();
 		for (int i = 0; i < counter.getSize(); i++) {
 			list[i] = itr.next();
 		}
 		return list;
 	}
    
 	
    // IMPORTANT: Used for grading. Do not modify the printing format!
 	// You may modify this method if you want.
 	/*
 	 * Prints out the given counting of the words
 	 */
    private static void printDataCount(DataCount<String>[] counts) {
    	for (DataCount<String> c : counts) {
            System.out.println(c.count + "\t" + c.data);
        }
    }
    
    
    
    /*
     * Sort the counts in descending order, prints all the counts with corresponding words.
     * If more than one words with same count appear, sort them alphabetically.
     */
    public static void main(String[] args) {
//    	if (args.length != 1) {
//            System.err.println("Usage: filename of document to analyze");
//            System.exit(1);
//        }
//        DataCounter<String> counter = new BinarySearchTree<String>(new StringComparator());
//        countWords(args[0], counter); 
//        DataCount<String>[] counts = getCountsArray(counter);
//        Sorter.heapSort(counts, new DataCountStringComparator());
//        printDataCount(counts);
    	
    	if (args.length < 3) {
    		throw new IllegalArgumentException();
    	}

		DataCounter<String> counter;
    	StringComparator comparator = new StringComparator();
    	
    	if (comparator.compare(args[0], "-b") == 0) {
    		counter = new BinarySearchTree<String>(comparator);
    	} else if (comparator.compare(args[0], "-a") == 0) {
    		counter = new AVLTree<String>(comparator);
    	} else if (comparator.compare(args[0], "-m") == 0) {
    		counter = new MoveToFrontList<String>(comparator);
    	} else if (comparator.compare(args[0], "-h") == 0) {
    		counter = new HashTable<String>(comparator, new StringHasher());
    	} else {
    		throw new IllegalArgumentException();
    	}
    	
    	countWords(args[args.length - 1], counter); 
    	DataCount<String>[] counts = getCountsArray(counter);
    	if (comparator.compare(args[1], "-is") == 0) {
    		Sorter.insertionSort(counts, new DataCountStringComparator());
    	} else if (args[1].charAt(1) == 'k') {
    		String num = args[2];
    		int k = Integer.parseInt(num);
    		Sorter.topKSort(counts, new DataCountStringComparator(), k);
    	} else if (comparator.compare(args[1], "-os") == 0) {
    		Sorter.otherSort(counts, new DataCountStringComparator());
    	} else if (comparator.compare(args[1], "-hs") == 0) {
    		Sorter.heapSort(counts, new DataCountStringComparator());
    	} else {
    		throw new IllegalArgumentException();
    	}
    	
    	printDataCount(counts);
    }
}

