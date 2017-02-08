

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
		public GNode getPrevious() { return previous; }
        public GNode getNext() { return next; }
		public void setData(E e) { data = e; }
		public void setPrevious(GNode p) { previous = p; }
        public void setNext(GNode p) { next = p; }
	}
   
   	
   private GNode<E> head;
   private GNode<E> tail;
   private int size;       // number of nodes in a list
   
   /** no-arg constructor creates an empty list
   **/ 
  
   public GDList( )
   {
      head = null;
      tail = null;
      size = 0;
   }
   
   /** add a new node with data e to the head
    * if a node with e already exists, return 1
    * if not, create and add a new node with e to the head (this new node is the head now), and return 0
    * increment the size
   **/
   public int addToHead(E e){
		GNode temp = new GNode(e);
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
		
		GNode temp = new GNode(e);
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
	
	/** insert a new node with data e after node n
	 * n is not null
	 * if a node with e already exists, return 1
	 * if not, create and add a new node with e after node n, and return 0
	 * increment the size
	**/
	public int addAfter(GNode n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;
		if (n == tail){
			addToTail(e);
		}
		else{
			GNode temp = new GNode(e);
			GNode tempNext = n.getNext();
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
	public int addBefore(GNode n, E e){
		
		if (n == null)
	         throw new IllegalArgumentException
	         ("The node n cannot be null");
		
		if (findNode(head, e) != null) return 1;		
		if (n == head) addToHead(e);
		else{
			GNode temp = new GNode(e);
			GNode tempPrevious = n.getPrevious();
			temp.setNext(n);
			n.setPrevious(temp);
			tempPrevious.setNext(temp);		
			temp.setPrevious(tempPrevious);		
			size++;
		}		
		return 0;
	}

	/** delete the node with data e
	 * if a node with e does not exist, return null
	 * if exists, delete the node and return the pointer to the deleted node
	 * decrement the size
	**/
	public GNode deleteNode(E e){
		// implement this method
		
	}

	/** delete the node which is located after the node with data e
	 * if the node with e is tail, return null
	 * if a node with e does not exist, return null
	 * if a noide with e exists, delete the node after that node and return the pointer to the deleted node
	 * decrement the size
	**/
	public GNode deleteAfter(E e){
		GNode temp = findNode(head, e);
		if (temp == tail || temp == null) return null;
		return (deleteNode((E)temp.getNext().data));
	}

	/** delete the node which is located before the node with data e
	 * if the node with e is head, return null
	 * if a node with e does not exist, return null
	 * if a node with e exists, delete the node before that node and return the pointer to the deleted node
	 * decrement the size
	**/
	public GNode deleteBefore(E e){
		GNode temp = findNode(head, e);
		if (temp == head || temp == null) return null;
		return (deleteNode((E)temp.getPrevious().data));
	}
	
	/**
	 * find a node with element e
	 * start the search beginning at node p
	 * if node with e does not exist, return null
	 * if node with e exists, return the pointer to the node
	**/
	public GNode findNode(GNode p, E e){
		GNode current = p;
		while (current != null && current.data != e)
			current = current.getNext();
		return current; 
	}
	
	/** exchange two nodes n1 and n2
	 * n1 is not null
	 * n2 is not null
	 * exchange node n1 and node n2 (do not just exchange the data).
	**/
	public void exchange(GNode n1, GNode n2){
		
		// implement this method
	}

	/** add a new node with e at the specified position
	 * pos specifies where new node is added
	 * pos of the first element in a list is 0
	 * pos >= 0
	 * if a node with e already exists, return 1
	 * if not, create and add a new node with e at position pos and return 0
	 * increment the size
	 */
	public int addPos(E e, int pos){
		// implement this method
	}
	
	/** replace the node at the specified position with a new node with e
	 * pos specifies the position of the node to be replaced
	 * pos of the first element in a list is 0
	 * pos >= 0
	 * if a node with e already exists, return null
	 * if not, create a new node with e and let it replace the node at position pos
	 * return the pointer to the replaced node
	 */
	public GNode replacePos(E e, int pos){
		// implement this method
		
	}
	
	public void printList(){
		System.out.print("Number of nodes = " + size + ", List is: ");
		if (head != null){
			GNode current = head;
		    while (current != null){
			   System.out.print(current.data + " ");
			   current = current.getNext();
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
	   names.addToTail("Smith");
	   names.addToTail("Whatley");
	   names.addToTail("Lewis");
	   System.out.println();
	   names.printList();
	   
	   GNode temp;
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
           
