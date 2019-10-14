package classes;

public class ArrayList<T> {
	private Node head;
	private int numberOfEntries;
	
	
	public ArrayList() {
		head = null;
		numberOfEntries = 0;
	}
	
	public void add(T newEntry) {
		 Node toInsert = new Node(newEntry, null);
		 Node curNode, nextNode;
		 numberOfEntries++;
		 if (head == null) {
			 head = head;
			 return;
	        }
		 	
		 curNode = head;
		 
		 for (nextNode = curNode.getNext(); nextNode != null; ) {
			 curNode = nextNode;
			 nextNode = curNode.getNext();
		   }
		 // loop finished when nextNode == null, currNode at the end
		 curNode.setNext (toInsert);  
	}
	
	public void add(int newPosition, T newEntry) {
		if (newPosition < 0 || newPosition > getLength() )
			throw new IndexOutOfBoundsException();
			
		Node toInsert = new Node (newEntry, null);
	     numberOfEntries ++;
			
		if (newPosition == 0) { // including empty list
			toInsert.setNext (head);
			head = toInsert;
			return;
		}
		int pos = 1;
		Node before = head;
		for (Node after = head.getNext(); after != null; ) {
			if (pos == newPosition) { //insert here
				before.setNext (toInsert);
				toInsert.setNext (after);
				return;
			}
			before = after;
			after = after.getNext();
			pos ++;	
		}
		before.setNext (toInsert); // When loop finished, after == null;
	                               // newPosition == length; end of chain
	}
	
	public T remove(int givenPosition) {
	    if (isEmpty())
	        throw new NullPointerException();
	     if (givenPosition < 0 || givenPosition >= getLength())
	        throw new IndexOutOfBoundsException();
	     T dataItem = (T) head.getData();
	     numberOfEntries --;        
	     if (givenPosition == 0)
	        head = head.getNext();
	     else {
	        int idx = 0;
	        Node nextNode = head;
	        for (Node currNode = head; nextNode != null; 
	                                      currNode = nextNode) {
	           idx ++;
	           nextNode = currNode.getNext();
	           if (idx == givenPosition ){ // nextNode is to be removed 
	              assert (nextNode != null);
	              dataItem = (T) head.getData();
	              currNode.setNext(nextNode.getNext());
	              break;
	             } 
	          }
	      }
	     return dataItem;
	}
	
	public boolean remove(T entry) {
		return false;
	}
	public void clear() {
		
	}
	public T replace(int givenPosition, T newEntry) {
		return null;
	}
	public T getEntry(int givenPosition) {
		return null;
	}
	public Object[] toArray() {
		return null;
	}
	public boolean contains(T anEntry) {
		return false;
	}
	public int getLength() {
	    int numEntries = 0;
	    
	    for (Node currNode = head; currNode != null;
	    	currNode = currNode.getNext())
	    	numEntries++;
	    
	    return numEntries;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	private Node getNodeAt(int index) {
	   assert (index >= 0 && index < getLength());
	   Node currNode = head;
	  
	   for (int i = 0; i < index; i ++)
	       currNode = currNode.getNext();
	    return currNode;
	}
}
