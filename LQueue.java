/*
 * Zach Martin
 * zrmartin@calply.edu 
 * 11/6/15
 * Project 3 
 */

/*
 * LQueue is a queue ADT implemented through using a linked list approach
 * the queue is traversed and elements are added/removed through the use of the 
 * front and end reference nodes 
 */

public class LQueue<T>
{
	private Node front; //Serves as the front index of the queue
	private Node end; // Serves as the end index of the queue
	
	/*
	 * Creates a new queue and sets the front and end 
	 * references to null to represent that to queue is empty
	 */
	public LQueue()
	{
		this.front = null;
		this.end = null;
	}
	
	/*
	 * Adds an item to the end of the queue
	 * @param element the element to be added to the queue
	 */ 
	
	public void enqueue(T element)
	{
		if(isEmpty())
		{
			end = front = new Node(element);
		}
		/*
		 * Same as: end.link new Node(element, end);
		 * 			end = new Node(element, end);
		 */
		else
		{
			end = end.link = new Node(element);
		}
		
	}
	
	/*
	 * Removes the oldest item in the list(The item in the front of the queue)
	 * @return T the element in the front of the queue 
	 */
	
	public T deqeue()
	{
		if(isEmpty())
		{
			throw new MyException("Invalid opperation: the queue is empty");
		}
		T firstElement = front.listElement;
		front = front.link;
		return firstElement;
	}
	
	/*
	 * returns whether or not the queue is empty
	 * @return boolean true if empty (front is equal to null). false otherwise
	 */
	public boolean isEmpty()
	{
		if(front == null)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * Inner class to represent each 'link' in the queue.
	 * Each Node stores a type T element, and a Node that 
	 * is it's link or next in the queue
	 */
	
	public class Node
	{
		public T listElement;
		public Node link;
		
		public Node(T listElement)
		{
			this.listElement = listElement;
		}
	}
	
	/*
	 * Inner custom exception class to deal with erroneous situations, 
	 * such as user trying to dequeue an empty queue
	 */
	
	public static class MyException extends RuntimeException
	{
		/*
		 * Creates a new MyException with no message
		 */
		
		public MyException()
		{
			super();
		}
		
		/*
		 * Creates a new MyException with the given error message
		 */
		
		public MyException(String message)
		{
			super(message);
		}
	}
}
