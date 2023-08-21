package Homeworks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;





/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: There are number of "TODO" instructions within this code, make sure to complete all of them fully.
 * Third: Test you code.
 */


// TODO: Name and Pledge

// Pledge: I pledge my honor that I have abided by the Stevens Honors System.
// Name: Nicholas Cali


/**
 * HW4 CS284 Fall 2020
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
        
        public Node(int freq) {
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
    }
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node {
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
        /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
        public String toString() {
            return "(freq= "+frequency+")";
        }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}
	
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}
	
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	
	
	public String bitsToString(Boolean[] encoding) {
		StringBuilder s = new StringBuilder();
		for (Boolean b: encoding) 
			s.append((b)? "1": "0");
		return s.toString();
		
		
	}
	
	public String toString() {
		if (root instanceof InternalNode) {
			return toString((InternalNode) root, 0).toString();
		}else {
			return root.toString();
		}
	}
	
	private StringBuilder toString(InternalNode current, int level) {
		StringBuilder s = new StringBuilder();
		StringBuilder nextLine = new StringBuilder();
		
		for (int i=0; i<level; i++) {
			 nextLine.append("--");
		 }
		 s.append(nextLine.toString());
		 s.append(current.toString()+"\n");
		 
		 
		 if (current.left instanceof LeafNode) { 
			 s.append(nextLine + " " + current.left.toString()+"\n");
		 }else { 	 
			 s.append(toString((InternalNode) current.left, level+1));
		 }
		 
		 if (current.right instanceof LeafNode) { 
			 s.append(nextLine + " " + current.right.toString()+"\n");
		 }else { 	 
			 s.append(toString((InternalNode) current.right, level+1));
		 }
		  
		 
		 return s;
	}
	
	public String decode(Boolean[] coding) {
		if (coding == null)
			throw new IllegalArgumentException();
		Node current = root;
		StringBuilder s = new StringBuilder();
		
		for (Boolean b: coding) {
			if (current instanceof InternalNode) {
				if (b) {
					current = ((InternalNode) current).right;
				}else { 
					current = ((InternalNode) current).left;
				}
			}
			if  (current instanceof LeafNode) {
				s.append(((LeafNode)current).data);
				current = root;
			}
		}	
		if (current != root) {
			throw new IllegalArgumentException();
		}
		return s.toString();
	}
	
	public Boolean[] encode(String inputText) {
		List<Boolean> encode = new ArrayList<>();

		for (Character b : inputText.toCharArray()) {
			List<Boolean> temp = new ArrayList<>();
			if (!deepFirst(root, temp, b))  
				throw new IllegalArgumentException();
			encode.addAll(temp);
		}
		return (Boolean[]) encode.toArray(new Boolean[encode.size()]);
	}
		
	
	

	
	
	public Boolean[] efficientEncode(String inputText) {
		Map<Character, List<Boolean>> m = new HashMap<>();
		List<Boolean> encode = new ArrayList<>();
		
		for (Character b : inputText.toCharArray()) {
			if (m.containsKey(b)) {
				encode.addAll(m.get(b));
			}else {	
				List<Boolean> temp = new ArrayList<>();
				if (!deepFirst(root, temp, b)) 
					throw new IllegalArgumentException();
				m.put(b, temp);
				encode.addAll(temp);
			}
		}		
	return (Boolean[]) encode.toArray(new Boolean[encode.size()]);
	}	
		
	
	
	private boolean deepFirst(Node current, List<Boolean> p, Character b) {
		if (current instanceof LeafNode) {
			if(((LeafNode) current).data == b) 
				return true;
			return false;
		}else if (deepFirst(((InternalNode) current).left, p, b)) {
			p.add(0, false);
			return true;
		}else if (deepFirst(((InternalNode) current).right, p, b)){
			p.add(0, true);
			return true;
		}else {
			return false;
		}
	}		
	
	
	
	public static void main(String[] args) {
		// Code to see if stuff works...
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
	}
}

