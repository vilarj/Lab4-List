package classes;

import java.util.Arrays;

public class ArrayList<T> {
	private T[] list;
	private int numberOfEntries, capacity;
	private static final int DEFAULT_CAPACITY = 10;
	private static final int MAX_CAPACITY = 10000;

	public ArrayList(int capacity) {
		if (capacity < DEFAULT_CAPACITY) {
			capacity = DEFAULT_CAPACITY;
		}

		else {
			checkCapacity (capacity);

			this.capacity = capacity;

			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Object[capacity];
			list = temp;
			numberOfEntries = 0;
		}
	}

	public ArrayList() {this(DEFAULT_CAPACITY);}

	public void add(T newEntry) {
		list[numberOfEntries] = newEntry;
		numberOfEntries ++;
		ensureCapacity();
	}


	public void add(int newPosition, T newEntry) {
		if (newPosition < 0 || newPosition > numberOfEntries) {
			throw new IndexOutOfBoundsException();
		}

		makeRoom (newPosition);
		list[newPosition] = newEntry;
		numberOfEntries ++;
		ensureCapacity();
	}

	public T remove(int givenPosition) {
		T theEntry;
		if (givenPosition < 0 || givenPosition >= numberOfEntries) {
			throw new IndexOutOfBoundsException("Illegal Position given to remove operation");
		}

		theEntry = list[givenPosition];
		removeGap(givenPosition);
		numberOfEntries--;
		return theEntry;
	}

	public boolean remove(T entry) {
		boolean result = false;
		int position = getPosition(entry);
		if (position > 0){
			list[position] = remove(position);
			result = true;
		} // end if
		return result;
	}

	public int getPosition(T anEntry){
		int position = 1;
		int length = list.length;
		// Find position of anEntry
		while ( (position <= length) && (list.length > 0) ){
			position++;
		} // end while
		// See whether anEntry is in list
		if ( (position > length) || (list.length) != 0) {
			position = -position; // anEntry is not in list
		} // end if
		return position;
	}

	public void clear() {
		for(int i = 0; i < numberOfEntries; i++) {
			list[numberOfEntries - i] = null;
		}
	}


	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}

	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		for (int idx = 0; idx < numberOfEntries; idx ++)
			result[idx] = list[idx];
		return result;
	}

	public boolean contains(T anEntry) {
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries)){
			if (anEntry.equals(list[index]))
				found = true;
			index++;
		}
		return found;
	}

	public int getLength() {return numberOfEntries;}

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
