

import java.util.Scanner;
import java.io.*;

/**
 * GDList is a generic doubly linked list.
 * All elements are distinct
 * @author Matthew Tse
 *
 * @param <E>
 */
public class GDList<E> implements Cloneable
{
	/** Nested class
	 * GNode is a generic class representing a node in a list
	 * E is generic type parameter of data
	 * Has both previous and next pointers
	**/
   	private static class GNode<E> {
		private E data;
		private GNode<E> previous;
        private GNode<E> next;
		
        // constructor
        public GNode (E e) {
			data = e;
			previous = null;
            next = null;
		}
		
		public E getData() { return data; }
		public GNode<E> getPrevious() { return previous; }
        public GNode<E> getNext() { return next; }
		public void setData(E e) { data = e; }
		public void setPrevious(GNode<E> p) { previous = (GNode<E>) p; }
        public void setNext(GNode<E> p) { next = (GNode<E>) p; }
	}
   
   	
   private GNode<E> head;
   private GNode<E> tail;
   private int size;       // number of nodes in a list
   private GNode<E> prev;
   
   /** no-arg constructor creates an empty list
   **/ 
  
   public GDList( )
   {
      head = null;
      tail = null;
      size = 0;
   }	
	
	/** insert a new node with data e after node n
	 * n is not null
	 * if a node with e already exists, return 1
	 * if not, create and add a new node with e after node n, and return 0
	 * increment the size
	**/
	public int addAfter(GNode<E> n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;
		if (n == tail){
			addToTail(e);
		}
		else{
			GNode<E> temp = new GNode<E>(e);
			GNode<E> tempNext = (GNode<E>) n.getNext();
			temp.setNext(tempNext);
			tempNext.setPrevious(temp);
			temp.setPrevious(n);
			n.setNext(temp);
			size++;
		}		
		return 0;
	}
	
	/** insert a new node with data e before node n
	 * n is not null
	 * if a node with e already exists, return 1
	 * if not, create and add a new node with e before node n, and return 0
	 * increment the size
	**/
	public int addBefore(GNode<E> n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;		
		if (n == head) addToHead(e);
		else{
			GNode<E> temp = new GNode<E>(e);
			GNode<E> tempPrevious = (GNode<E>) n.getPrevious();
			temp.setNext(n);
			n.setPrevious(temp);
			tempPrevious.setNext(temp);		
			temp.setPrevious(tempPrevious);		
			size++;
		}		
		return 0;
	}
	
	   /** add a new node with data e to the head
	    * if a node with e already exists, return 1
	    * if not, create and add a new node with e to the head (this new node is the head now), and return 0
	    * increment the size
	   **/
	   public int addToHead(E e){
			GNode<E> temp = new GNode<E>(e);
			if (head == null){
				head = temp;
				tail = temp;
			}
			else{
				if (findNode(head, e) == null) {
					temp.setNext(head);
					head.setPrevious(temp);
					head = temp;
				}
				else return 1;
			}
			size++;
			return 0;
		}
	   
		/** add a new node with data e to the tail
		 * if a node with e already exists, return 1
		 * if not, creatre and add a new node with e to the tail (this new node is the tail now), and return 0
		 * increment the size
		**/
		public int addToTail(E e){
			
			GNode<E> temp = new GNode<E>(e);
			if (head == null){
				head = temp;
				tail = temp;
			}
			else{
				if (findNode(head, e) == null){
					temp.setPrevious(tail);
					tail.setNext(temp);
					tail = temp;
					
				}
				else return 1;
			}
			size++;
			return 0;
		}
		
		/** add a new node with e at the specified position
		 * pos specifies where new node is added
		 * pos of the first element in a list is 0
		 * pos >= 0
		 * if a node with e already exists, return 1
		 * if not, create and add a new node with e at position pos and return 0
		 * increment the size
		 * @resource
		 * http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/
		 * https://www.java2novice.com/data-structures-in-java/linked-list/doubly-linked-list/
		 */
		public int addPos(E e, int pos){
			//return val default to 0
			int stat = 0;
			//only if already exists
			if (findNode(head, e) != null) {
				stat = 1;
				return stat;
			}
			//do nothing if position is less than 0
			if (pos >= 0) {
				//temp node 
				GNode<E> temp = new GNode<E>(e);
				//at the beginning
				if (pos == 0) {
					addToHead(e);
				//at the end
				} else if (pos == size || pos > size) {
					addToTail(e);
				} else {
					GNode<E> current = head;
					//check for exception before iterate over current
					if (current != null) {
						//loop to the requested index or the last element in the list
						for (int cnt = 0; cnt < pos - 1 && current.getNext() != null; cnt++) {
							current = current.getNext();						
						}
					}			 
					//set the new node's next-node reference to this node's next-node reference
					temp.setNext(current.getNext());
					current.setPrevious(temp);			 
					//set this node's next-node reference to the new node
					current.setNext(temp);			 
					//update size
					size++;
				}
			}		    
		    return stat;
		}
		
		/** delete the node with data e
		 * if a node with e does not exist, return null
		 * if exists, delete the node and return the pointer to the deleted node
		 * decrement the size
		**/
		public GNode<E> deleteNode(E e){
			//new node with selected
			GNode<E> temp = findNode(head, e);		
			//if selected is not there
			if (temp == null) {
				return null;
			//set the data at link
			} else {
				//check head
				if (temp.previous == null) {
					head = head.getNext();
				//check tail
				} else if (temp.next == null) {					
					tail = tail.getPrevious();					
				//set new links
				} else {
					GNode<E> tempLt = temp.getPrevious();
					GNode<E> tempRt = temp.getNext();
					tempLt.next = tempRt;					
				}
				//update size
				size--;
			}
			return temp;
		}
		
		/** replace the node at the specified position with a new node with e
		 * pos specifies the position of the node to be replaced
		 * pos of the first element in a list is 0
		 * pos >= 0
		 * if a node with e already exists, return null
		 * if not, create a new node with e and let it replace the node at position pos
		 * return the pointer to the replaced node
		 */
		public GNode<E> replacePos(E e, int pos){
			//temp node
			GNode<E> temp = findNode(head, e);
			if (temp == null && pos < size) {
				//check head
				if (pos == 0) {
					head = head.getNext();
					addToHead(e);
				//check tail and adjust for index
				} else if (pos == (size - 1)) {
					tail = tail.getPrevious();
					addToTail(e);
				} else {
					//add to position
					GNode<E> newNode = new GNode<E>(e);
					GNode<E> current = head;
					//check for exception before iterate over current
					if (current != null) {
						//loop to the requested index or the last element in the list
						for (int cnt = 0; cnt < pos && current.getNext() != null; cnt++) {
							current = current.getNext();						
						}
					}			 
					//set the new node's next-node reference to this node's next-node reference
					newNode.setNext(current.getNext().getNext());
					current.setPrevious(newNode);			 
					//set this node's next-node reference to the new node
					current.setNext(newNode);					
				}
			}			
			
			return temp;
			
		}	
		
		/** exchange two nodes n1 and n2
		 * n1 is not null
		 * n2 is not null
		 * exchange node n1 and node n2 (do not just exchange the data).
		 * @reference http://stackoverflow.com/questions/37637894/java-generic-doubly-linked-list-swap
		 * http://stackoverflow.com/questions/27922497/java-double-linked-list-swap-node
		 * 
		**/
		public void exchange(GNode<E> n1, GNode<E> n2){	
			//check head and tail
	 		if (n1 == head && n2 == tail) {
	 			GNode<E> temp = findNode(head, n1.getData());
	 			head = n2;
	 			temp.setNext(n1.getNext());
	 			temp.setPrevious(null);
	 			tail = n1;
	 		//check tail and head	
			} else if (n1 == tail && n2 == head) {
	 			GNode<E> temp = findNode(head, n2.getData());
	 			head = n1;
	 			temp.setNext(n2.getNext());
	 			temp.setPrevious(null);
	 			tail = n2;
			//update only head
			} else if (n1 == head && n2 != tail) {
	 			GNode<E> temp = findNode(head, n1.getData());
	 			head = n2;
	 			temp.setNext(n1.getNext());
	 			temp.setPrevious(null);
	 		//update only tail	
			} else if (n1 != head && n2 == tail) {
				GNode<E> temp = findNode(head, n2.getData());
				tail = n1;
				temp.setPrevious(n2.getPrevious());
			}
	 		//Adjacent nodes
	 		if (n1 == n2.getPrevious() || n2 == n1.getPrevious()) {
		        //Order is relevant
				GNode<E> first;
				GNode<E> second;
				if (n1 == n2.getPrevious()) {
					first = n1;
					second = n2;
				} else {
					first = n2;
					second = n1;
				}
		  
				first.setNext(second.getNext());
				second.setPrevious(first.getPrevious());
		  
				if (first.getNext() != null)
					first.getNext().setPrevious(first);
		  
				if (second.getPrevious() != null)
					second.getPrevious().setNext(second);
		  
					second.setNext(first);
					first.setPrevious(second);
				//Non adjacent
				} else {				
					GNode<E> prevN1 = n1.getPrevious();
					GNode<E> nextN1 = n1.getNext();
					GNode<E> prevN2 = n2.getPrevious();
					GNode<E> nextN2 = n2.getNext();
				  
					n1.setPrevious(prevN2);
					n1.setNext(nextN2);
					n2.setPrevious(prevN1);
					n2.setNext(nextN1);
				  
					if (prevN1 != null)
						prevN1.setNext(n2);
					if (nextN1 != null)
						nextN1.setPrevious(n2);
					if (prevN2 != null)
						prevN2.setNext(n1);
					if (nextN2 != null)
						nextN2.setPrevious(n1);
				}
		}

	/** delete the node which is located after the node with data e
	 * if the node with e is tail, return null
	 * if a node with e does not exist, return null
	 * if a node with e exists, delete the node after that node and return the pointer to the deleted node
	 * decrement the size
	**/
	public GNode<E> deleteAfter(E e){
		GNode<E> temp = findNode(head, e);
		if (temp == tail || temp == null) return null;
		return (deleteNode((E)temp.getNext().data));
	}

	/** delete the node which is located before the node with data e
	 * if the node with e is head, return null
	 * if a node with e does not exist, return null
	 * if a node with e exists, delete the node before that node and return the pointer to the deleted node
	 * decrement the size
	**/
	public GNode<E> deleteBefore(E e){
		GNode<E> temp = findNode(head, e);
		if (temp == head || temp == null) return null;
		return (deleteNode((E)temp.getPrevious().data));
	}
	
	/**
	 * find a node with element e
	 * start the search beginning at node p
	 * if node with e does not exist, return null
	 * if node with e exists, return the pointer to the node
	**/
	public GNode<E> findNode(GNode<E> p, E e){
		GNode<E> current = p;
		while (current != null && current.data != e)
			current = (GNode<E>) current.getNext();
		return current; 
	}

	public void printList(){
		System.out.print("Number of nodes = " + size + ", List is: ");
		if (head != null){
			GNode<E> current = head;
		    while (current != null){
			   System.out.print(current.data + " ");
			   current = (GNode<E>) current.getNext();
		   }
		}
		else {
			System.out.println("The list is empty");
		}
		System.out.println();
	}
		
	public static void main(String[] args) throws Exception {
	   
	   GDList<String> names = new GDList<String>();
	   names.printList();
	   names.addToHead("Decker");
	   names.addToHead("Barbour");
	   names.addToHead("Franklin");
	   names.printList();
	   names.addToTail("Cow");
	   names.addToTail("Smith");
	   names.addToTail("Whatley");
	   names.addToTail("Lewis");
	   names.deleteNode("Lewis");
	   names.deleteNode("Franklin");
	   names.addPos("Whatley2", 7);
	   names.addPos("Whatley2", 0);
	   names.exchange(names.head.next.next.next.next, names.tail.previous);
	   names.exchange(names.head.next, names.tail);
	   names.replacePos("cow2", 7);
	   names.deleteNode("Cow");
	   System.out.println();
	   names.printList();
	   
	   GNode<String> temp;
	   // find and print Decker, search from head
	   System.out.println("\nFind and print Decker. Search from head.");
	   temp = names.findNode(names.head, "Decker");
	   System.out.println(temp != null ? temp.data : "The node does not exist.");
	   
	   // find Whatley first and add Morse after Whatley
	   System.out.println("\nFind Whatley first and add Morse after Whatley");
	   temp = names.findNode(names.head, "Whatley");
	   if (temp == null)
		   System.out.println("Such node does not exist in the list");
	   else
		   names.addAfter(temp, "Morse");
	   System.out.println();
	   names.printList();
	   
	}
}
           
