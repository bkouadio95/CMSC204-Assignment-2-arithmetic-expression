import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {
	
	private T[] s;
	private int Size = 20;
	private int numNodes = 0;
	private int top = 0;
	private int bottom = 0;
	@SuppressWarnings("unused")
	private int frontLocation = 0;

	
	
	@SuppressWarnings("unchecked")
	/**
	 * default constructor
	 */
	public NotationQueue() {
		
		s = (T[]) new Object[Size];
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * constructor with one-argument 
	 * @param n
	 */
	public NotationQueue(int n) {
		Size = n;
		s = (T[]) new Object[n];
	}
	
	@Override
	/**
	 * isEmpty method
	 */
	public boolean isEmpty() {
		if (numNodes == 0) {
			return true;
			} else
			return false;
	}

	@Override
	/**
	 * isFull method
	 */
	public boolean isFull() {
		if (numNodes >= s.length) {
			//throw new StackOverflowException("Stack is full");
			return true;
		} else 
			return false;
	}

	@Override
	/**
	 * denqueue method
	 */
	public T dequeue() throws QueueUnderflowException {
		int newtop;
		if (numNodes == 0) {
			throw new QueueUnderflowException("Stack is Empty");
		}
			newtop = top;
			top = (top + 1)%Size;
			numNodes = numNodes -1;
			//size = size - 1;
		
		
			
		T value = s[newtop];
	
		return value;
	}

	@Override
	/**
	 * size method
	 */
	public int size() {
		return numNodes;
	}

	@Override
	/**
	 * enqueue method
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		if (numNodes >= s.length) {
			throw new QueueOverflowException("Queue is full");
		}
		
		numNodes = numNodes + 1;
		s[bottom] = e;
		bottom = (bottom+1)%Size;
		return true;
		
	}
/**
 * toString method
 */
	public String toString() {
		String str = "";
		for (int i= 0; i<Size&&s[i]!=null; i++) {
			str+= s[i];
		}
		return str;
	}
	@Override
	/**
	 * toString method with one-argument
	 */
	public String toString(String delimiter) {
		String str = "";
		for (int i= 0; i<Size&&s[i]!=null; i++) {
			str+= s[i];
			if(i<numNodes-1)
				str+= delimiter;
		}
		return str;
	}
/**
 * fill method
 */
	@Override
	public void fill(ArrayList<T> list) {
		for(int i=0; i<list.size(); i++) {
			s[i] = (T) list.get(i);
			numNodes++;
	
		}

	}

}
