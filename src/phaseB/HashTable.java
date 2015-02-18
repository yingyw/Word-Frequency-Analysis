package phaseB;
import phaseA.GArrayStack;
import phaseA.MoveToFrontList;
import providedCode.*;


/**
 * TODO: Replace this comment with your own as appropriate.
 * 1. You may implement any kind of HashTable discussed in class; the only 
 *    restriction is that it should not restrict the size of the input domain 
 *    (i.e., it must accept any key) or the number of inputs (i.e., it must 
 *    grow as necessary).
 * 2. You should use this implementation with the -h option.
 * 3. To use your HashTable for WordCount, you will need to be able to hash 
 *    strings. Implement your own hashing strategy using charAt and length. 
 *    Do not use Java's hashCode method.
 * TODO: Develop appropriate JUnit tests for your HashTable.
 */
public class HashTable<E> extends DataCounter<E> {
	private Comparator<? super E> comparator;
	private Hasher<E> hasher;
	private MoveToFrontList<E>[] hashArray;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashTable(Comparator<? super E> c, Hasher<E> h) {
		comparator = c;
		hasher = h;
		hashArray = (MoveToFrontList<E>[])new MoveToFrontList[37];		
		size = 0;
	}
	
	
	@Override
	public void incCount(E data) {
		
		double loadFac = size/hashArray.length;
		if(loadFac >= 0.5){
			doubleArray();
		}
		int index = Math.abs(hasher.hash(data)%hashArray.length);
		if(hashArray[index] == null){
			hashArray[index] = new MoveToFrontList<E>(comparator);
		}
		int previous = hashArray[index].getSize();
		hashArray[index].incCount(data);
		if(previous < hashArray[index].getSize()){
			size++;
		}

		
	}
	private void doubleArray(){
		@SuppressWarnings("unchecked")
		MoveToFrontList<E>[] doubled = (MoveToFrontList<E>[])new MoveToFrontList[primeLength(hashArray.length*2+1)];
		SimpleIterator<DataCount<E>> itr = getIterator();
		while (itr.hasNext()) {
			DataCount<E> word = itr.next();
			int index = hasher.hash(word.data) % doubled.length;
			if (doubled[index] == null) {
				doubled[index] = new MoveToFrontList<E>(comparator);
			}
			doubled[index].insert(word.data, word.count);
		}
		hashArray = doubled;
	}	
	
	private int primeLength(int n) {
		while (!isPrime(n)) {
			n += 2;
		}
		return n;
	}
	
	private boolean isPrime(int n) {
		for (int i = 3; i < n; i ++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getCount(E data) {
		int index = Math.abs(hasher.hash(data)%hashArray.length);
		if(hashArray[index] != null) {
			return hashArray[index].getCount(data);
		}
		return 0;
	}

	@Override
	public SimpleIterator<DataCount<E>> getIterator() {
		return new SimpleIterator<DataCount<E>>(){
			private GArrayStack<DataCount<E>> stack = new GArrayStack<DataCount<E>>();{
				for(int i = 0; i<hashArray.length;i++){
      				MoveToFrontList<E> list = hashArray[i];	
					if(hashArray[i] != null){
						SimpleIterator<DataCount<E>> itr = list.getIterator();
						while(itr.hasNext()){
							stack.push(itr.next());
						}
					}
				}
			}	   
		   public boolean hasNext(){
			   return !stack.isEmpty();
		   }
		   public DataCount<E> next(){	
			   return stack.pop();
		   }
		};	
		
	}
	
//	@Override
//	/** {@inheritDoc} */
//	public SimpleIterator<DataCount<E>> getIterator() {
//		// anonymous inner class
//		return new SimpleIterator<DataCount<E>>() {
//    		// keep tracked of which slot is iterating and number of elements iterated
//    		int currentTableIndex;
//    		int iterated;
//    		SimpleIterator<DataCount<E>> currentItr;
//    		{
//    			//start with slot 0 if size is not 0
//    			iterated = 0;
//    			currentTableIndex = 0;
//    			if(size != 0) {
//    				updateCurrentItr();
//    			}
//    		}
//    		
//    		//true when iterated elements is not equal to size
//    		public boolean hasNext() {
//        		return iterated != size;
//        	}
//        	public DataCount<E> next() {
//        		//throw exception when empty
//        		if(!hasNext()) {
//        			throw new java.util.NoSuchElementException();
//        		}
//        		
//        		//if the current slot is empty, find the next slot
//        		if(!currentItr.hasNext()) {
//        			currentTableIndex++;
//        			updateCurrentItr();
//        		}
//        		
//        		iterated++;
//        		return currentItr.next();
//        	}
//        	
//        	//helper function that finds the iterator of the next slot that is not null
//        	private void updateCurrentItr() {
//        		while(hashArray[currentTableIndex] == null)
//					currentTableIndex++;
//				currentItr = hashArray[currentTableIndex].getIterator();
//        	}
//    	};
//	}
//
//
}
