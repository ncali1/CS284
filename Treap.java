package Homeworks;


import java.util.Stack;
import java.util.Random;


public class Treap <E extends Comparable<E>> {
	private static class Node<E>{
		//Data fields
		E data;
		int priority;
		Node<E> left;
		Node<E> right;

		//Constructor
		public Node(E data, int priority) {
			if(data == null) {
				throw new IllegalArgumentException("Data is null which it can't be");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}

		//Methods


		//rotates treap to the right
		public Node<E> rotateRight(){
			if (left == null) {
				return this;
			}else {
				Node<E> root = left;
				left = left.right;
				root.right = this;
				return root;
			}
		}

		//rotates treap to the left
		public Node<E> rotateLeft(){
			if(right == null){
				return this;
			}else {
				Node<E> root = right;
				right = right.left;
				root.left = this;
				return root;
			}
		}


		public String toString() {
			return "(key=" + data + ", priority=" + priority + ")";
		}
	}
	//data fields
	private Random priorityGenerator;
	private Node<E> root;

	//Constructors
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}

	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}


	//Methods
	//helper function to restore the heap invariant
	private void reheap(Node<E> current, Stack<Node<E>> length) {
		while (!length.isEmpty()) {
			if (length.peek().priority < current.priority){
				Node<E> temp = length.pop(); //parent
				if (temp.left == current) {
					if (length.isEmpty()) {
						root = temp.rotateRight();
					}
					else if (length.peek().left == temp) {
						length.peek().left = temp.rotateRight();
					}else if (length.peek().right == temp) {
						length.peek().right = temp.rotateRight();
					}
					
				} else if (temp.right == current) {
					if (length.isEmpty()) {
						root = temp.rotateLeft();
					}else if (length.peek().right == temp) {
						length.peek().right = temp.rotateLeft();
					}else if (length.peek().left == temp) {
						length.peek().left = temp.rotateLeft();
					}
				}
			} else {
				return;
			}
		}
	}






	//adds random priority with the given key
	public boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}


	//helper function for add	
	public boolean add(E key, int priority) {
		if(root == null) {
			root = new Node<E>(key, priority);
			return true;
		}else {
			Node<E> holder = new Node<E>(key,priority);
			Stack<Node<E>> stack = new Stack<Node<E>>();
			Node<E> curr = root;
			while(curr != null) {
				int sim = curr.data.compareTo(key);
				if(sim == 0) {
					return false;
				}else {
					if(sim < 0) {
						stack.push(curr);
						if(curr.right == null) {
							curr.right = holder;
							reheap(holder, stack);
							return true;
						}else {
							curr = curr.right;
						}
					}else {
						stack.push(curr);
						if(curr.left == null) {
							curr.left = holder;
							reheap(holder, stack);
							return true;
						}else {
							curr = curr.left;
						}
					}
				}
			}
			return false;
		}
	}

	//Deletes the node with the given key and returns true if found
	public boolean delete(E key) {
		if(!find(key)) {
			return false;
		}else {
			root = delete(root,key);
			return true;
		}
	}

	// helper function for delete	
	private Node<E> delete(Node<E> root,E key){
		if (root == null) {
			throw new IllegalStateException("Key not in tree");
		} else {
			if (root.data.compareTo(key) < 0) {
				root.right = delete(root.right, key);
			} else {
				if (root.data.compareTo(key) > 0) {
					root.left = delete(root.left, key);
				} else {
					if (root.right == null) {
						return root.left;
					} else if (root.left == null) {
						return root.left;
					} else {
						if (root.right.priority < root.left.priority) {
							root = root.rotateRight();
							root.right = delete(root.right, key);
						} else {
							root = root.rotateLeft();
							root.left = delete(root.left, key);
						}
					}
				}
			}
		}
		return root;
	}





	//finds the node with given key in the treap	
	private boolean find(Node<E> root, E key) {
		if (root == null){
			return false;
		}else {
			int eq = root.data.compareTo(key);
			if (eq == 0) {
				return true;
			}else {
				return find(root.left, key) || find(root.right, key);
			}
		}
	}

	//if found, returns true if root is found with the given key
	public boolean find(E key) {
		return find(root, key);
	}




	//Carries out a preorder traversal of the tree and returns a representation of the nodes as a string
	private StringBuilder toString(Node<E> current, int level) {
		 StringBuilder r = new StringBuilder();
		 
		 for (int i=0; i<level; i++) {
			 r.append("--");
		 }
		 if (current==null) {
			 r.append("null\n");
			 return r;
		 }
		 r.append(current.toString()+"\n");
		 r.append(toString(current.left,level+1));
		 r.append(toString(current.right,level+1));
		 return r;
	}	

	public String toString() {
		return toString(root,0).toString();	
	}
	
	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		System.out.println(testTree.toString());
	}


}


