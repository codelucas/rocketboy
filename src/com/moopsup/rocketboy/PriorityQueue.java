package com.moopsup.rocketboy;

public class PriorityQueue 
{

	private static class PQNode
	{
		int key;
		ScoreEntry value;
		boolean status; //True if existing, false if deleted
		
		public PQNode(int key, ScoreEntry value, boolean status)
		{
			this.key = key;
			this.value = value;
			this.status = status;
		}
		
		public void setStatus(boolean status)
		{
			this.status = status;
		}
	}
	
	private LinkedList<PQNode> ourList;
	
	public PriorityQueue()
	{
		ourList = new LinkedList<PQNode>(); 
	}
	
	public void insert(int key, ScoreEntry value) 
	//Add to back, our PQ's "add" function is O(1), so this makes sense
	{ 
		//All List nodes are set to default true, because we never actually delete
		//anything, we just mark it as true or false to speed things up.
		ourList.addToBack(new PQNode(key, value, true)); 
	}
		 
	public ScoreEntry removeMax()
	//The critical part of this method is that K can be any key, so we can
	//use this method generically for any sort we want, intensity rows, cols, etc.
	//To save time, we will mark items as removed, but not actually remove them.
	{	
		LinkedList<PQNode>.Iterator	iterator = ourList.iterator();
		
		//This process is very important because we need to make sure the starting
		//node to compare with the rest actually is still "present" in the list.
		//If there is only one element left we will claim it as our max and return it!
		PQNode max = iterator.next();
		while(iterator.hasNext() && (max.status == false))
		{
			max = iterator.next();
		}
		
		//If the size of our list = 1, we will simply return the only Node as the max,
		//If the size is < 1, this method will never be called, so we are safe.
		while(iterator.hasNext())
		{
		    PQNode nodeToCompare = iterator.next();
		    //If the status is false, that means that this Node has already been "fake deleted".
			if (nodeToCompare.key > max.key && (nodeToCompare.status == true))
			{ 
				max = nodeToCompare;
			}
		}
		//We need to pretend to decrease count
	    //because we never remove anything actually, we are marking items as
		//Existent or non-existent to save time.
		ourList.artificialDecrement();
		
		//We never actually remove anything from the list, its just marked as true
		//or false, if it is false, it wont be checked again.
		max.setStatus(false);
		return max.value;
	}
	
	public void refill()
	{
		LinkedList<PQNode>.Iterator iterator = ourList.iterator();
		while(iterator.hasNext())
		{
			iterator.next().status = true;
			ourList.artificialIncrement();
		}
	}
	
	public int size()
	{
		return ourList.size();
	}
		
	public boolean isEmpty()
	{
		if(ourList.size() == 0)
		{
			return true;
		}
		return false;
	}
}