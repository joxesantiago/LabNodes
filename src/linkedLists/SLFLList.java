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

public class SLFLList<E> extends SLList<E> {
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
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		
		((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());
	    ((SNode<E>) target).setNext((SNode<E>) nuevo); 
	    
		if((((SNode<E>) nuevo).getNext()==null)){
			last = (SNode<E>) nuevo;
		}
		
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		
		if (target == first)
			this.addFirstNode(nuevo); 
		else { 
			Node<E> prevNode = getNodeAfter(target);  
			this.addNodeAfter(prevNode, nuevo); 
		}
		
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if(first==null) {
			throw new NoSuchElementException("getLastNode(): Empty list.");
		}
		else {
			return first;
		}
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (first == null)
			throw new NoSuchElementException("getLastNode(): Empty list."); 
		else { 
			return last; 
		}
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if (aNode == null)  
			throw new NoSuchElementException("getNextNode(...) : target is the last node."); 
		else 
			return aNode;
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if (target == first)  
			throw new NoSuchElementException("getPrevNode(...) : target is the first node."); 
		else {
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
			prev = prev.getNext();  
			return prev;
		}
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		if(target==first){
			first=first.getNext();
		}
		else if(target==last){
			last = (SNode<E>)this.getNodeBefore(target);
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
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
	
	public Object[] toArray() {
		Object[] a = new Object[length];
		int i = 0;
		for (SNode<E> n = first; i < length; n = n.getNext()) {
            a[i]=n.getElement();
            i++;
		}
		
        return a;
	}

}
