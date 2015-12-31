/*
 * Zach Martin
 * zrmartin@calply.edu 
 * 11/6/15
 * Project 3 
 */

import java.util.Iterator;
import java.util.Scanner;

/*
 * BSTDriver is the accompanying tester class for the previously created BST ADT class.
 * BSTDriver creates a new BST to hold integers and a menu is printed, displaying
 * all possible tree operations for testing.
 * Test keeps running until "q" is entered by the user
 */

public class BSTDriver 
{
	public static void main(String[] args)
	{
		BST<Integer> tree = new BST<Integer>();
		Scanner in = new Scanner(System.in);
		String greeting = "Choose one of the following opperations by entering the provided letter:\n"
						+ "a - add the element\n"
						+ "d - delete the element\n"
						+ "f - find the element\n"
						+ "e - check if the tree is empty\n"
						+ "k - make the tree empty\n"
						+ "n - get the number of nodes (the size) of the tree\n"
						+ "m - find the minimal element\n"
						+ "x - find the maximal element\n"
						+ "p - print the tree in preorder using iterator\n"
						+ "i - print the tree in inorder using iterator\n"
						+ "l - print the tree in levelorder using iterator\n"
						+ "t - print the tree using print tree\n"
						+ "o - output the tree using toString\n"
						+ "q - Quit the program\n";
		System.out.println(greeting);
		mainloop:
		while(in.hasNext())
		{
			String choice = in.next();
			switch(choice)
			{
				case "a":
				{
					in.nextLine();
					System.out.println("Enter an integer to add");
					int next = in.nextInt();
					try
					{
						tree.insert(next);
						System.out.println(next + " has been added to the tree");
					}
					catch(BST.MyException e)
					{
						System.out.println(e);
					}
					break;
				}
				case "d":
				{
					in.nextLine();
					System.out.println("Enter an integer to delete");
					int next = in.nextInt();
					tree.delete(next);
					System.out.println(next +" has been removed from the tree");
					break;
				}
				case "f":
				{
					in.nextLine();
					System.out.println("Enter an inter to search for");
					int next = in.nextInt();
					if(tree.find(next))
					{
						System.out.println(next + " is in the tree");
					}
					else
					{
						System.out.println(next + " is not in the tree");
					}
					break;
				}
				case "e":
				{
					if(tree.isEmpty())
					{
						System.out.println("The tree is empty");
					}
					else
					{
						System.out.println("The tree is not empty");
					}
					break;
				}
				case "k":
				{
					tree.makeEmpty();
					System.out.println("Tree is now empty");
					break;
				}
				case "n":
				{
					System.out.println("There are " + tree.size() + " nodes in the tree");
					break;
				}
				case "m":
				{
					try
					{
						System.out.println(tree.findMinimum() + " is the minimum element in the tree");
					}
					catch(BST.MyException e)
					{
						System.out.println(e);
					}
					break;
				}
				case "x":
				{
					try
					{
						System.out.println(tree.findMaximum() + " is the maximum element in the tree");
					}
					catch(BST.MyException e)
					{
						System.out.println(e);
					}
					break;
				}
				case "p":
				{
					Iterator<Integer> itr = tree.iteratorPre();
					while(itr.hasNext())
					{
						System.out.print(itr.next() + " ");
					}
					break;
				}
				case "i":
				{
					Iterator<Integer> itr = tree.iteratorIn();
					while(itr.hasNext())
					{
						System.out.print(itr.next() + " ");
					}
					break;
				}
				case "l":
				{
					Iterator<Integer> itr = tree.iteratorLevel();
					while(itr.hasNext())
					{
						System.out.print(itr.next() + " ");
					}
					break;
				}
				case "t":
				{
					try
					{
						tree.printTree();
					}
					catch(BST.MyException e)
					{
						System.out.println(e);
					}
					break;
				}
				case "o":
				{
					System.out.println(tree.toString());
					break;
				}
				case "q":
				{
					System.out.println("Quitting, have a nice day!");
					break mainloop;
				}
				default:
				{
					System.out.println("Not a valid option");
					break;
				}	
			}
		}
	}
}
