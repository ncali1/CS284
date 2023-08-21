package Homeworks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

	@Test
	void testAddIntE() {
		IDLList<Integer> l1 = new IDLList<>();
		int size = 5;
		for (int i=0; i<size;i++) {
			l1.add(i);
		}
		l1.add(1, 5);
		assertTrue(5 == l1.get(0));
		
		l1.add(0, 13);
		assertTrue(13==l1.get(0));
		assertTrue(13==l1.getHead());
	}

	@Test
	void testAddE() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		for (int i=0;i<size;i++) {
			l1.add(i);
		}
		
		assertEquals(size, l1.size());
		
		l1.add(2);
		assertTrue(2==l1.get(0));
		assertTrue(l1.add(1));
		
		l1.add(3);
		assertTrue(3==l1.get(0));
		assertTrue(l1.add(0));
	}
	
	@Test
	void testAppend() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		for (int i=0;i<size;i++) {
			l1.add(i);
		}
		assertEquals(size,l1.size());
		l1.append(26);
		size++;
		assertEquals(size,l1.size());
		assertTrue(26 == l1.getLast());
	}

	@Test
	void testGetHead() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		int head = -2;
		for (int i=0;i<size;i++) {
			l1.add(i);
			head = i;
		}
		assertTrue(head == l1.getHead());
		l1.removeAt(0);
		assertTrue(head-1 == l1.getHead());
	}	

	@Test
	void testGetLast() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		int tail = -2;
		for (int i=0;i<size;i++) {
			l1.append(i);
			tail = i;
		}
		
		assertTrue(tail == l1.getLast());
		assertEquals(size,l1.size());
	}


	@Test
	void testRemove() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		for (int i=0;i<size;i++) {
			l1.add(i);
		}
		assertFalse(l1.remove(50));
		assertEquals(size, l1.size());
		
		assertTrue(l1.remove(0));
		size--;
		assertEquals(size-1,l1.size());
		assertFalse(l1.getLast()==0);
	}

	@Test
	void testRemoveLast() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		int last = -2;
		for (int i=0;i<size;i++) {
			l1.append(i);
			last = i;
		}
		
		assertTrue(last == l1.removeLast());
		assertEquals(size-1,l1.size());
	}

	@Test
	void testRemoveAt() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		for (int i=0;i<size;i++) {
			l1.append(i);
		}
		assertTrue(0 == l1.removeAt(0));
		size--;
		assertEquals(size,l1.size());
		
		assertTrue(5 == l1.removeAt(3));
		size--;
		assertEquals(size,l1.remove(3));
	}

	@Test
	void testRemoveE() {
		IDLList<Integer> l1 = new IDLList<>();
		int size =5;
		for (int i=0; i<size;i++) {
			l1.add(i);
		}
		assertFalse(l1.remove(50));
		assertEquals(size, l1.size());
		
		assertTrue(l1.remove(0));
		size--;
		assertEquals(size, l1.size());
		assertFalse(l1.getLast()==0);
	}

	@Test
	void testToString() {
		IDLList<Integer> l1 = new IDLList<>();
		for (int i =0; i<5;i++) {
			l1.add(i);
		}
		assertEquals("[5,4,3,2,1,0,]", l1.toString());
	}
}


















