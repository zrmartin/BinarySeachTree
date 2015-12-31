/*
 * Zach Martin
 * zrmartin@calply.edu 
 * 11/6/15
 * Project 3 
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

/*
 * DictionaryMaker is an application class for the BST ADT created previously.
 * Reads in strings from a given file and inserts each string into the BST if it is 
 * not already in it.
 * When the file is completely read the contents of the tree are printed to the given
 * output file in lexicographically order.
 */

public class DictionaryMaker 
{
	public static void main(String[] args) throws IOException
	{
		//Initializing necessary scanners for user input and scanning the given file
		Scanner in = new Scanner(System.in);
		System.out.println("Enter input filename");
		Scanner file = null;
		try
		{
			File input = new File(in.next());
			file = new Scanner(input);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		System.out.println("Enter output filename");
		PrintStream output = new PrintStream(in.next());
		BST<String> words = new BST<String>();
		
		//Checking each string in the file before adding it to the tree
		while(file.hasNext())
		{
			String word = file.next();
			if(!words.find(word))
			{
				words.insert(word);
			}
		}
		
		//Creating a new in-order iterator for the given tree
		Iterator<String> itr = words.iteratorIn();
		//Writing the contents of the tree to the given file in lexicographical order
		while(itr.hasNext())
		{
			output.println(itr.next());
		}
		//Closing resources
		in.close();
		file.close();
		output.close();
	}

}
