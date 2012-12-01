package com.moopsup.rocketboy;
import java.util.NoSuchElementException;

// Lucas Ou-Yang Id-Number: 27404511
//Originally implemented by Alex Thornton in lab four.
public class LinkedList<E> 
{
	private static class Node<E>
	{
		public E data;
		public Node<E> next;
		
		public Node(E data, Node<E> next)
		{
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> tails; //This is for the queue
	private int count;

	// The constructor initializes this linked list to be empty.
	public LinkedList()
	{
		head = null;
		tails = null;
		count = 0;
	}

	public void addToBack(E e)
	{
		if(tails == null)
		{
			tails = new Node<E>(e, null);
			head = tails;			
			//The head node will remain the very first node while tails moves backwards
			count = 1;
			return;
		}
		tails.next = new Node<E>(e, null); 
		tails = tails.next; 
		count++;
	}
	
	public void artificialDecrement()
	{
		count--;
	}
	
	public void artificialIncrement()
	{
		count++;
	}
	
	// size() returns the number of elements in this list.
	public int size()
	{
		return this.count;
	}
	
	// clear() makes this list empty.
	public void clear()
	{
		head = null;
		count = 0;
	}
	
	public Iterator iterator()
	{
		return new Iterator();
	}

	public class Iterator
	{
		private Node<E> currentNode;

		public Iterator()
		{
			currentNode = head;
		}
		
		public boolean hasNext()
		{
			if (currentNode == null)      
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		public E next()
		{  
			Node<E> temp = currentNode;  
			//This variable is necessary because we need to store the currentNode somewhere
			//because we are setting the currentNode reference to the next node before 
			//returning
			if (currentNode == null)  
			{
				throw new NoSuchElementException();
			}
			currentNode = currentNode.next;
			return temp.data;
		}
	}
}