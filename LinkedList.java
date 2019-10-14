package classes;

import java.util.Arrays;

public class LinkedList<T> implements ListInterface<T> {
	private T[] list;
	private int numberOfEntries, capacity;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 10000;
	
	public LinkedList(int capacity) {
		if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
		}
		
        else
            checkCapacity (capacity);
        
		this.capacity = capacity;
        
        @SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
        list = temp;
        numberOfEntries = 0;
	}

	public LinkedList() {this(DEFAULT_CAPACITY);}

	@Override
	public void add(T newEntry) {
		list[numberOfEntries] = newEntry;
	      numberOfEntries ++;
	      ensureCapacity();
	}

	@Override
	public void add(int newPosition, T newEntry) {
        if (newPosition < 0 || newPosition > numberOfEntries)
            throw new IndexOutOfBoundsException();
        makeRoom (newPosition);
        list[newPosition] = newEntry;
        numberOfEntries ++;
        ensureCapacity();
	}

	@Override
	public T remove(int givenPosition) {
	      T theEntry;
	      if (givenPosition < 0 || givenPosition >= numberOfEntries)
	            throw new IndexOutOfBoundsException(
	                    "Illegal Position given to remove operation");
	      theEntry = list[givenPosition];
	      removeGap(givenPosition);
	      numberOfEntries--;
	      return theEntry;
	}

	@Override
	public boolean remove(T entry) {
		
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getEntry(int givenPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
        for (int idx = 0; idx < numberOfEntries; idx ++)
            result[idx] = list[idx];
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		
		return false;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isEmpty() {return list == null;}
	
	// Private methods
	private void checkCapacity(int capacity) {
		if(capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}
	
	private void ensureCapacity() {
        if (numberOfEntries >= capacity) {
            capacity *= 2;
            checkCapacity (capacity); // too big ?
            list = Arrays.copyOf(list, capacity);       
        }
	}
	
	private void makeRoom(int newPosition) {
		assert (newPosition >= 0 && newPosition <= numberOfEntries);
        for (int idx = numberOfEntries; idx > newPosition; idx --)
            list[idx] = list[idx-1];
	}
	
	private void removeGap(int givenPosition) {
		 assert (givenPosition >= 0 && givenPosition < numberOfEntries);
		 
		 for (int index = givenPosition; index < numberOfEntries - 1; index ++){
		            list[index] = list[index+1];
		  }
	}
}
