/*
 * Zach Martin
 * zrmartin@calply.edu 
 * 11/6/15
 * Project 3 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * BST is an ADT that is used for efficiently inserting, deleting, 
 * and searching elements in a list.
 * BST holds a child-parent property of left children being less than the parent
 * and right children being larger than the parent.
 */

public class BST<T extends Comparable<? super T>> 
{
	private BSTNode root; //Reference to the root of the tree
	
	/*
	 * Constructs a new, empty binary search tree by setting
	 * it's root to null
	 */
	
	public BST()
	{
		root = null;
	}
	
	/*
	 * Public insert method for user to input an element into the tree
	 * @param element the element to insert into the tree
	 */
	
	public void insert(T element)
	{
		if(root == null)
		{
			root = new BSTNode(element);
		}
		else
		{
			insert(element, root);
		}
	}
	
	/*
	 * private helper method to deal with the recursion of insertion 
	 * @param element the element to insert into the tree
	 * @param current the BSTNode being compared to the given element at the time
	 */
	
	private void insert(T element, BSTNode current)
	{
		if(element.compareTo(current.element) > 0)
		{
			if(current.right == null)
			{
				current.right = new BSTNode(element);
			}
			else
			{
				insert(element, current.right);
			}
		}
		else if(element.compareTo(current.element) < 0)
		{
			if(current.left == null)
			{
				current.left = new BSTNode(element);
			}
			else
			{
				insert(element, current.left);
			}
		}
		else
		{
			throw new MyException("Element already in the list");
		}
	}
	
	/*
	 * public delete method for user to use to delete a given element 
	 * from the tree
	 * @param element the element to search for and delete from the tree
	 */
	
	public void delete(T element)
	{
		if(root == null)
		{
			return;
		}
		delete(element, root);
	}
	
	/*
	 * Private helper class to deal with the recursion.
	 * Calls itself until the node with the given element is found.
	 * Deletion has 3 various cases determined by the number of children of the node
	 */
	
	private BSTNode delete (T element, BSTNode current)
	{
		if(element.compareTo(current.element) < 0)
		{
			current.left = delete(element, current.left);
		}
		else if(element.compareTo(current.element) > 0)
		{
			current.right = delete(element, current.right);
		}
		else if(current.right != null && current.left != null)
		{
			T min = findMinimum(current.right);
			current.element = min;
			current.right = delete(min, current.right);
		}
		else
		{
			if(current.left != null)
			{
				if(current.element == root)
				{
					root = current.left;
				}
				else
				{
					current = current.left;
				}
			}
			else if(current.right != null)
			{
				if(current.element == root)
				{
					root = current.right;
				}
				else
				{
					current = current.right;
				}
			}
			else
			{
				if(current.element == root)
				{
					makeEmpty();
				}
				else
				{current = null;}
			}
		}
		return current;
	}
	
	/*
	 * public method for users to search if a given element is in the tree
	 * @param item the item to search for in the tree
	 * @return boolean true if found, false otherwise
	 */
	
	public boolean find(T item)
	{
		if(root == null)
		{
			return false;
		}
		return find(item, root);
	}
	
	/*
	 * Private helper method with recursion.
	 * Recursively calls itself, searching through the tree for the given element,
	 * until either the element is found or the bottom of the tree is reached.
	 * @return boolean true if found, false otherwise
	 */
	
	private boolean find(T item, BSTNode current)
	{
		boolean result = false;
		if(item.compareTo(current.element) > 0 && current.right != null)
		{
			return find(item, current.right);
		}
		else if(item.compareTo(current.element) < 0 && current.left != null)
		{
			return find(item, current.left);
		}
		else if(item.compareTo(current.element) == 0)
		{
			result = true;
		}
		return result;
	}
	
	/*
	 * Tells whether the current tree is empty or not
	 * @return boolean true if the tree is empty(root is null) false otherwise
	 */
	
	public boolean isEmpty()
	{
		if(root == null)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * Makes the current BST empty by setting the root equal to null
	 */
	
	public void makeEmpty()
	{
		root = null;
	}
	
	/*
	 * public user method to find the size (current number of nodes) of the tree
	 * @return int the number of nodes in the tree
	 */
	
	public int size()
	{
		return size(root);
	}
	
	/*
	 * Private helper method to deal with recursion.
	 * Starts at the root and checks whether or not the root, and every node
	 * in the left and right subtree have corresponding elements.
	 * If the node has an element, increment the total by 1
	 * @param BSTNode the current node we are at
	 */

	private int size(BSTNode current)
	{
		int answer;
		if(current == null)
		{
			answer = 0;
		}
		else
		{
			answer = 1 + size(current.left) + size(current.right);		
		}
		return answer;
	}
	
	/*
	 * public method for user to find the minimum element in the tree
	 * @return T the minimum element in the tree
	 * @throws MyException if tree is empty, minimum cannot be found
	 */
	
	public T findMinimum() throws MyException
	{
		if(isEmpty())
		{
			throw new MyException("Cannot find minimum; tree empty");
		}
		return findMinimum(root);	
	}
	
	/*
	 * Private helper method with recursion.
	 * Finds the furthest left element in the tree, which will be the minimum.
	 * @param BSTNode what node we are currently checking
	 * @return T the minimum element in the tree
	 */
	
	private T findMinimum(BSTNode current)
	{
		if(current.left != null)
		{
			return findMinimum(current.left);
		}
		return current.element;
	}
	
	/*
	 * public method for user to find the maximum element of the tree
	 * @return T the maximum element in the tree
	 * @throws MyException if the tree is empty, cannot find a maximum
	 */
	
	public T findMaximum() throws MyException
	{
		if(isEmpty())
		{
			throw new MyException("Cannot find maximum; tree empty");
		}
		return findMaximum(root);
	}
	
	/*
	 * Private helper class to deal with recursion
	 * Finds the furthest right Node in the tree and returns that Node's element
	 * @return T the maximum element in the tree
	 * @param BSTNode the current node we are checking.
	 */
	private T findMaximum(BSTNode current)
	{
		if(current.right != null)
		{
			return findMaximum(current.right);
		}
		return current.element;
	}
	
	/*
	 * returns an iterator of the tree as it currently is. The iterator 
	 * traverses the tree in a pre-order manner. 
	 * @return Iterator<T> a pre-order iterator of the given tree
	 */
	
	public Iterator<T> iteratorPre()
	{
		return new PreIter();
	}
	
	/*
	 * returns an iterator of the tree as it currently is. The iterator
	 * traverses the tree in an in-order manner.
	 * @return Iterator<T> an in-order iterator of the given tree.
	 */
	
	public Iterator<T> iteratorIn()
	{
		return new InIter();
	}
	
	/*
	 * returns an iterator of the tree as it currently is. The iterator
	 * traverses the tree in a level-order manner.,
	 * @return Iterator<T> a level-order iterator of the given tree. 
	 */
	
	public Iterator<T> iteratorLevel()
	{
		return new LevelItr();
	}
	
	/*
	 * public method for user to print the tree.
	 * @throws MyException if the tree is empty. Cannot print an empty tree
	 */
	
	/*
	 * public method for user to print the tree.
	 * @throws MyException if the tree is empty. Cannot print an empty tree
	 */
	
	public void printTree()
	{
		if(root == null)
		{
			throw new MyException("Cannot print an empty tree");
		}
		printTree(0, root);
	}

	
	/*
	 * Private helper method for recursion.
	 * Prints out the tree with one element on each line. The indentation
	 * of the element corresponds to what level it is located.
	 * @param level the level the node is currently at
	 * @param current the current node we are at. 
	 */
	
	private void printTree(int level, BSTNode current)
	{
		Iterator<T> itr = iteratorPre();
		while(itr.hasNext())
		{
			T element = itr.next();
			int depth = level(root, element);
			for(int i = 0; i < depth * 4; i++)
			{
				System.out.print(" ");
			}
			System.out.println(element);
		}
	}
	
	private int level(BSTNode start, T end)
	{
		int level = 0;
		if(end.compareTo(start.element) == 0)
		{
			return level;
		}
		else if(end.compareTo(start.element) < 0)
		{
			return 1 + level(start.left, end);
		}
		else
		{
			return 1 + level(start.right, end);
		}
	}
	
	/*
	 * Prints the tree in the same order as the printTree method above; However,
	 * instead of showing the level through indentation, each element is printed 
	 * on the same line with a space separating each.
	 */
	
	public String toString()
	{
		Iterator<T> itr = iteratorIn();
		String result = "";
		while(itr.hasNext())
		{
			result += itr.next();
			result += " ";
		}
		return result;
	}
	
	/*
	 * Private inner exception class to deal with erroneous situations such as
	 * calling findMax, findMin, printTree on an empty tree. Helps provide 
	 * meaningful feedback instead of letting the program crash.
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
	
	/*
	 * Private inner class to represent a Node in the BST. Each node contains
	 * a T type element, and a BSTNode left and right child.
	 */
	
	private class BSTNode
	{
		public T element; //The element of the Node
		public BSTNode left; //Left child of the Node
		public BSTNode right; // Right child of the Node
	
		/*
		 * Constructs a new BSTNode with the given element and both children set to null
		 */
		public BSTNode(T element)
		{
			this.element = element;
			left = null;
			right = null;
		}
	}
	
	/*
	 * private inner Iterator class to help iterate through the BST 
	 * in a pre-order manner
	 */
	
	private class PreIter implements Iterator<T>
	{
		public MyStack<BSTNode> stack; //used to sort through the tree
		
		/*
		 * Creates a new iterator by creating a new stack and pushing
		 * the root onto the stack if the tree is not empty
		 */
		public PreIter()
		{
			stack = new MyStack<BSTNode>();
			if(root != null)
			{
				stack.push(root);
			}
		}
		
		/*
		 * Checks whether or not there is a Node we have no been to yet by
		 * checking if the stack is empty or not.
		 * @return boolean true if has an un-visited element, false otherwise
		 */
		
		public boolean hasNext()
		{
			if(stack.isEmpty())
			{
				return false;
			}
			return true;
		}
		
		/*
		 * @return T the next element in the tree according to the given traversal type
		 * @throws NoSuchElementException if the iterator has no more Nodes to visit
		 */
		
		public T next() throws NoSuchElementException
		{
			if(! hasNext())
			{
				throw new NoSuchElementException();
			}
			
			BSTNode current = stack.pop();
			if(current.right != null)
			{
				stack.push(current.right);
			}
			if(current.left != null)
			{
				stack.push(current.left);
			}
			return current.element;
		}
		
		/*
		 * Optional method, not implemented.
		 */
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
	}
		
		private class InIter implements Iterator<T>
		{
			public MyStack<BSTNode> stack; //Used to sort through the Nodes
			
			/*
			 * Creates a new iterator by creating a new stack and pushing
			 * the root onto the stack if the tree is not empty.
			 * Next, all nodes currently smaller than the root are pushed onto the stack (stackupLefts)
			 */
			
			public InIter()
			{
				stack = new MyStack<BSTNode>();
				if(root != null)
				{
					stack.push(root);
					stackUpLefts(root);
				}
			}
			
			/*
			 * helper method for grabbing the left-most Nodes from a given Node
			 * @param BSTNode where to start stacking up left children.
			 */
			private void stackUpLefts(BSTNode start)
			{
				while(start.left != null)
				{
					stack.push(start.left);
					start = start.left;
				}
			}
			
			/*
			 * Checks whether or not there is a Node we have no been to yet by
			 * checking if the stack is empty or not.
			 * @return boolean true if has an un-visited element, false otherwise
			 */
			
			public boolean hasNext()
			{
				if(stack.isEmpty())
				{
					return false;
				}
				return true;
			}
			
			/*
			 * @return T the next element in the tree according to the given traversal type
			 * @throws NoSuchElementException if the iterator has no more Nodes to visit
			 */
			
			public T next() throws NoSuchElementException
			{
				if(!hasNext())
				{
					throw new NoSuchElementException();
				}
				BSTNode current = stack.pop();
				if(current.right != null)
				{
					stack.push(current.right);
					stackUpLefts(current.right);
				}
				return current.element;
			}
			
			/*
			 * Optional method, not implemented.
			 */
			
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		}	
		
		private class LevelItr implements Iterator<T>
		{
			public LQueue<BSTNode> q; //Used to sort Nodes
			
			/*
			 * Creates a new iterator by creating a new queue and enqueueing
			 * the root onto the stack if the tree is not empty
			 */
			
			public LevelItr()
			{
				q = new LQueue<BSTNode>();
				if(root != null)
				{
					q.enqueue(root);
				}	
			}
			
			/*
			 * Checks whether or not there is a Node we have no been to yet by
			 * checking if the queue is empty or not.
			 * @return boolean true if has an un-visited element, false otherwise
			 */
			
			public boolean hasNext()
			{
				if(q.isEmpty())
				{
					return false;
				}
				return true;
			}
			
			/*
			 * @return T the next element in the tree according to the given traversal type
			 * @throws NoSuchElementException if the iterator has no more Nodes to visit
			 */
			
			public T next() throws NoSuchElementException
			{
				if(! hasNext())
				{
					throw new NoSuchElementException();
				}
				BSTNode current = q.deqeue();
				if(current.left != null)
				{
					q.enqueue(current.left);
				}
				if(current.right != null)
				{
					q.enqueue(current.right);
				}
				return current.element;
			}
				
			/*
			 * Optional method, not implemented.
			 */
			
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		}
}
