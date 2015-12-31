/*
 * Zachary Martin (zrmartin@calpoly.edu)
 * 10/8/2015
 * Project 1
 */

import java.util.EmptyStackException;

/*
 * MyStack<T> is a basic, singly-linked Stack ADT.
 * Any changes made to the stack are made through the variable topOfStack
 */

public class MyStack<T>
{
	private Node topOfStack; //Top of stack
	
	/*
	 * MyStack has one constructor that creates a new, empty stack.
	 */
	public MyStack()
	{
		topOfStack = null;
	}
	
	/* Push places an element onto the top of the stack
	 * @param e: Can be an element of any type to push onto the stack
	 */
	public void push(T e)
	{
		if(isEmpty())
		{
			topOfStack = new Node(e);
		}
		else
		{
			Node tosCurrent = topOfStack;
			topOfStack = new Node(e);
			topOfStack.next = tosCurrent;
		}
	}

	/*
	 * Pop removes the top element in the stack, and returns it's value
	 * @return T: is the top element on the stack
	 */
	public T pop()
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		else
		{
			T firstElement = topOfStack.listElement;
			topOfStack = topOfStack.next;
			return firstElement;
		}
	}
	
	/*
	 * Peek returns what is on top of the stack, without removing it
	 * @return T: The top element on the stack
	 */
	public T peek()
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		else
		{
			return topOfStack.listElement;
		}
	}
	
	/*
	 * isEmpty returns true if the stack is empty, and false if not
	 * @return boolean: true or false based on whether stack is empty or not
	 */
	public boolean isEmpty()
	{
		if(topOfStack == null)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * Node is a subclass used in the MyStack ADT.
	 * A node contains an element, and a link to the next node in the stack
	 */
	private class Node
	{
		public T listElement;
		public Node next;
		public Node(T listElement)
		{
			this.listElement = listElement;
		}
	}

}
