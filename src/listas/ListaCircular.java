package listas;

/**
* Lista Circular
* @author https://github.com/derrickcodes/SLinkedCircularList
* Modificada para tomar objetos genericos
*/
public class ListaCircular<T>
{
	
	private Node firstNode;			
	private Node lastNode;
	private Node currentNode;
	private int length;				
	
	public ListaCircular()
	{
		clear();
	} 

	public boolean add(T newEntry)
	{
		Node newNode = new Node(newEntry);
		
		if (isEmpty())
		{	
			firstNode = newNode;
			lastNode = newNode;
			firstNode.next = newNode;	
			lastNode.next = newNode;	
		}
		else
		{
			Node currLast = lastNode;	
			currLast.next = newNode;	
			newNode.next = firstNode;	
			lastNode = newNode;			
		} 
		
		length++;	
		return true;
	} 
	
	public boolean add(int newPosition, T newEntry)
	{
		boolean isSuccessful = true;
		
		if ((newPosition >= 1) && (newPosition <= length + 1))
		{
			Node newNode = new Node(newEntry);
			
			if (isEmpty())
			{
				firstNode = newNode;
				lastNode = newNode;
				firstNode.next = newNode;	
				lastNode.next = newNode;
				currentNode = newNode;
			}
			else if (newPosition == 1)
			{
				newNode.next = firstNode;	
				firstNode = newNode;		
				lastNode.next = firstNode;	
			}
			else if (newPosition == length + 1) 
			{
				lastNode.next = newNode;	 
				lastNode = newNode;
				lastNode.next = firstNode;
			} 
			else
			{
				Node nodeBefore = getNodeAt(newPosition -1);
				Node nodeAfter = nodeBefore.next;
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			} 
			
			length++;
		}
		else
		{
			isSuccessful = false;
		}
		
		return isSuccessful;
	} 

	public T remove(int givenPosition)
	{
		T result = null; 				
		
		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))
		{
			if (givenPosition == 1)
			{
				result = firstNode.data;
				firstNode = firstNode.next;
				lastNode.next = firstNode;
				if (length == 1)
				{
					lastNode = null;		
				} 
			} 
			else
			{
				Node nodeBefore = getNodeAt(givenPosition -1);
				Node nodeToRemove = nodeBefore.next;
				Node nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter; 	
				result = nodeToRemove.data;		
				if (givenPosition == length)
				{
					lastNode = nodeBefore;		
					lastNode.next = firstNode;
				} 
			} 
			
			length--;
		} 
		
		return result;
	} 

	public final void clear()
	{
		firstNode = null;
		lastNode = null;
		currentNode = null;
		length = 0;
	} 

	public boolean replace(int givenPosition, T newEntry)
	{
		boolean isSuccessful = true;
		
		if (!isEmpty() && (givenPosition >=1) && (givenPosition <= length))
		{
			Node desiredNode = getNodeAt(givenPosition);
			desiredNode.data = newEntry;
		}
		else
		{
			isSuccessful = false;
		}
		return isSuccessful;
	} 

	public T getEntry(int givenPosition)
	{
		T result = null;	
		
		if (!isEmpty() && (givenPosition >= 1) && (givenPosition <= length))
			result = getNodeAt(givenPosition).data;
		
		return result;
	} 
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		do
		{
			if (anEntry.equals(currentNode.data))
				found = true;
			else
				currentNode = currentNode.next;
		} while (!found && (currentNode != lastNode));
		
		return found;
	} 

	public int getLength() {
		return length;
	}

	/**
	 * 
	 */
	public boolean isEmpty()
	{
		return length == 0;
	}

	
	public boolean isFull() {
		return false;
	}
	
	public void reset() {
		currentNode = this.getNodeAt(1);
	}
	
	public T next() {
		currentNode = currentNode.next;
		return currentNode.data;
	}
	
	private Node getNodeAt(int givenPosition)
	{
		Node currentNode = firstNode;
		
		
		for (int counter = 1; counter < givenPosition; counter++)
			currentNode = currentNode.next;
		
		return currentNode;
	} 

	private class Node		
	{
		private T data;		
		private Node next;			
		
		private Node(T dataPortion)
		{
			data = dataPortion;
			next = null;
		} 
		
		private Node(T dataPortion, Node nextNode)
		{
			data = dataPortion;
			next = nextNode;
		} 
	} 
	
}