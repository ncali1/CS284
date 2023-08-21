package Homeworks;

import java.util.ArrayList;
//Nicholas Cali
//I pledge my honor that I have abided by the Stevens Honor System

public class IDLList<E> {
	
	private static class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		
		Node(E elem) {
			this.data = elem;
			next = null;
			prev= null;
		}
		
		
		public Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	
	
	public boolean add(int index, E elem) {
		
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		if (size ==0) {
			Node<E> newN = new Node<E>(elem);
			head = newN;
			tail = newN;
			size++;
			indices.add(0,newN);
		}
		
		if (index == 0) {
			head = new Node<>(elem, null, head);
			indices.add(0, this.head);
			size++;
			return true;
			

		}else {
			Node<E> curr = indices.get(index);
			Node<E> newCurr = new Node<>(elem, curr, curr.prev);
			curr.prev.next = newCurr;
			curr.prev = newCurr;
			size++;
			indices.add(index, newCurr);
		}
		return true;
		
	
	}
	
	
	
	public boolean add(E elem) {
		if (head == null) {
			head = new Node<E>(elem);
			tail = head;
		}else {
			Node<E> current = head;
			head = new Node<E>(elem, null, current);
			current.prev = head;
		}
		size++;
		return true;
	}
	
	
	 
	public boolean append(E elem) {
		 if (elem == null) {
			 throw new IllegalStateException();
		 }else if (tail ==null) {
			 add(elem);
		 }else {
			 Node<E> current = tail;
			 tail = new Node<E>(elem);
			 current.next = tail;
			 indices.add(tail);
			 size++;
		 }
		return true; 
	}
	
	
	public E get(int index) {
		return indices.get(index).data;
	}
	
	
	public E getHead() {
		return this.get(0);
	}
		

	
	public E getLast() {
		return this.get(size-1);
	}
	
	
	
	public int size() {
		return size;
	}

	
	public E remove() {
		return removeAt(0);
	}


	
	public E removeLast() {
		return removeAt(size-1);
	}
	
	
	public E removeAt(int index) {
		if (index < 0 || size < index) {
			throw new IllegalStateException();
		}
		Node<E> current = head;
		if (index == 0) {
			head = head.next;
			current.next = null;
			head.prev = null;
			indices.remove(index);
			size--;
			return current.data;
		} 
		if(current.next == null) {
			tail = current.prev;
			current.prev = null;
			indices.remove(index);
			size--;
			return current.data;
		}
		current.next.prev = current.prev;
		current.prev.next = current.next;
		current.prev = null;
		current.next = null;
		indices.remove(index);
		size--;
		return current.data;
	}
		
		
	public boolean remove(E elem) {
			for(int i = 0; i < size; i++) {
				if (indices.get(i).data.equals(elem)) {
					removeAt(i);
					return true;
				}
			}
			return false;
		}
	
		
	public String toString() {
			Node<E> curr = head;
			StringBuilder s1 = new StringBuilder('[');
			while (curr != null) {
					s1.append(",");
					s1.append(curr.data);
					curr = curr.next;
			}
			s1.append("]");
			return s1.toString();
	}
	
}






