package classes;

public class LinkedList<T> implements ListInterface<T> {
	private Node firstNode;
	private int numberOfEntries;

	@Override
	public void add(T newEntry) {
		Node toInsert = new Node(newEntry, null);
		numberOfEntries++;

		if (firstNode == null) {// empty list
			firstNode = toInsert;
			return;
		}

		boolean endFound;
		Node currNode = firstNode;
		Node nextNode = firstNode.getNext();

		do {
			endFound = (nextNode == null);
			if (!endFound) {
				currNode = nextNode;
				assert (nextNode != null);
				nextNode = nextNode.getNext();
			}
		} 
		while (!endFound);
		assert (currNode != null);
		currNode.setNext(toInsert);
	}

	@Override
	public void add(int newPosition, T newEntry) {
		if (newPosition < 0 || newPosition > getLength())
			throw new IndexOutOfBoundsException();

		Node toInsert = new Node(newEntry, null);
		numberOfEntries++;

		if (newPosition == 0) {// the only value for the empty list
			toInsert.setNext(firstNode);
			firstNode = toInsert;
			return;
		}
		// else traverse the chain
		int idx = 0;
		boolean found = false;
		Node after = firstNode, before = null;
		do {
			if (idx == newPosition) {
				found = true;
				assert (before != null);
				before.setNext(toInsert);
				toInsert.setNext(after);
			} 
			else {
				before = after;
				after = after.getNext();
				idx++;
			}
		} 
		while (!found);
	}

	@Override
	public T remove(int givenPosition) {
		if (isEmpty())
			throw new NullPointerException();
		if (givenPosition < 0 || givenPosition >= getLength())
			throw new IndexOutOfBoundsException();
		T dataItem = (T) firstNode.getData();
		numberOfEntries--;
		if (givenPosition == 0)
			firstNode = firstNode.getNext();
		else {
			int idx = 0;
			Node nextNode = firstNode;
			
			for (Node currNode = firstNode; nextNode != null; currNode = nextNode) {
				idx++;
				nextNode = currNode.getNext();
				if (idx == givenPosition) { // nextNode is to be removed
					assert (nextNode != null);
					dataItem = (T) nextNode.getData();
					currNode.setNext(nextNode.getNext());
					break;
				}
			}
		}
		return dataItem;
	}

	@Override
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		
		if (nodeN != null) {
			nodeN.data = firstNode.data; // Replace located entry with entry
			// in first node
			firstNode = firstNode.next; // Remove first node
			numberOfEntries--;
			result = true;
		} // end if
		return result;
	}

	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} // end while
		return currentNode;
	}

	@Override
	public void clear() {
		if (!isEmpty()) {
			firstNode = null;
		}
	}

	@Override
	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T originalEntry = (T) desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	}

	private Node getNodeAt(int givenPosition) {
		Node lastNode = null;
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		if (givenPosition == numberOfEntries)
			currentNode = lastNode;
		else if (givenPosition > 1) {
			for (int counter = 1; counter < givenPosition; counter++)
				currentNode = currentNode.getNext();
		} // end if
		assert currentNode != null;
		return currentNode;
	}

	@Override
	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
			return (T) getNodeAt(givenPosition).getData();
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");  // end getEntry
	}
	
	@Override
	public int getLength() {
		int numEntries = 0;
		
		for (Node currNode = firstNode; currNode != null; currNode = currNode.getNext())
			numEntries++;
		return numEntries;
	}

	@Override
	public boolean isEmpty() {
		return firstNode == null;
	}

	@Override
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)){
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNext();
		} // end while
		return found;
	}

	@Override
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
		int index = 0;
		Node currentNode = firstNode;
		
		while ((index < numberOfEntries) && (currentNode != null)){
			result[index] = (T) currentNode.data;
			index++;
			currentNode = currentNode.next;
		} // end while
		return result;
	}

	public class Node<T> {
		private T data;
		private Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
}
