import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {

	private static final int DEFAULT_CAPACITY = 20;
	private T[] s;
	private int Size = 0;
	private int Capacity;
	private int Top = -1;
	
	@SuppressWarnings("unchecked")
	/**
	 * Default constructor
	 */
	public NotationStack() {
		Capacity = DEFAULT_CAPACITY;
		s = (T[]) new Object[DEFAULT_CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * constructor with one parameterized arg
	 * @param capacity
	 */
	public NotationStack(int capacity) {
		this.Capacity = capacity;
		s = (T[]) new Object[capacity];
	}
	@Override
	/**
	 * isEmpty method
	 */
	public boolean isEmpty() {
		if (Size == 0) {
		return true;
		} else
		return false;
	}
/**
 * isFull method
 */
	@Override
	public boolean isFull() {
		if (Size >= s.length) {
			//throw new StackOverflowException("Stack is full");
			return true;
		} else 
			return false;

	}

	@Override
	/**
	 * pop method
	 */
	public T pop() throws StackUnderflowException {

		if (Size == 0) 
			throw new StackUnderflowException("Stack is Empty");
		
			Size = Size - 1;
		T value = s[Size];
		//store[size] = null;
		
		int reducedSize = Size;
		if (Size >= Capacity && Size < (reducedSize + (reducedSize << 1))) {
			System.arraycopy(s, 0, s, 0, Size);
		}
		return value;
	}

	@Override
	/**
	 * top method
	 */
	public T top() throws StackUnderflowException {
		if (Size <= 0) {
			throw new StackUnderflowException("Stack is Empty");
		} else 
			
		return s[Size-1];
	}

	@Override
	/**
	 * size method
	 */
	public int size() {
		return Size;
	}
/**
 * push method
 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (Size >= s.length) {
			throw new StackOverflowException("Stack is full");
		}
		s[Size++] =  e;
		Top = Top + 1;
		return true;
	}
/**
 * toString method with one-argument
 */
	@Override
	public String toString(String delimiter) {
		String str = "";
		int i;
		for ( i= 0; i<Size-1; i++) {
			 str+= s[i]+ delimiter;
		}
		str += s[i];
		return str;

	}
	/**
	 * fill method
	 */

	@Override
	public void fill(ArrayList<T> list) {

		for(int i=0; i<list.size(); i++) {
			s[Size++] =  (T) list.get(i);
			Top = Top + 1;
		}

	}
	/**
	 * toString method
	 */
	
	public String toString() {
		String str = "";
		for (int i= 0; i<Size; i++) {
			str+= s[i];
			
		}
		return str;
	}
}

	
