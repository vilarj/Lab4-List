package classes;

public interface ListInterface<T> {

	public void add(T newEntry);
	public void add(int newPosition, T newEntry);
	public T remove(int givenPosition);
	public boolean remove(T entry);
	public void clear();
	public T replace(int givenPosition, T newEntry);
	public T getEntry(int givenPosition);
	public Object[] toArray();
	public boolean contains(T anEntry);
	public int getLength();
	public boolean isEmpty();
	
}
