package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {	
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(first); 
		first = (SNode<E>) nuevo; 
		length++; 
		if(length==1){
			last=first;
		}
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());
	    ((SNode<E>) target).setNext((SNode<E>) nuevo); 
	    
		if(target==last){
			last = (SNode<E>) nuevo;
		}
		
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		if (target == first)
			this.addFirstNode(nuevo); 
		else { 
			Node<E> prevNode = findNodePrevTo(target);  
			this.addNodeAfter(prevNode, nuevo); 
		}
		
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (first == null)
			throw new NoSuchElementException("getLastNode(): Empty list."); 
		else { 
			return last; 
		}
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		if(target==first){
			first=first.getNext();
		}
		else if(target==last){
			last= (SNode<E>)this.getNodeBefore(target);
		}
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		((SNode<E>) target).clean();   // clear all references from target
		
		length--; 
		
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}

}
