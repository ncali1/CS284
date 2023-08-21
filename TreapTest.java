package Homeworks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class TreapTest {

	@Test
	void test1() {
		Treap<Integer> t1 = new Treap<Integer>();
		assertTrue(t1.add(2, 19));
		assertTrue(t1.add(3, 98));
		assertTrue(t1.add(1, 23));
		assertTrue(t1.add(7, 54));
		assertTrue(t1.add(6, 34));
		assertTrue(t1.add(5, 45));
		assertTrue(t1.add(8, 40));
		assertTrue(t1.find(3));
		assertTrue(t1.find(45));
		assertFalse(t1.find(37));
		assertFalse(t1.find(25));
		assertTrue(t1.delete(5));
		assertFalse(t1.delete(41));
	}
	
	@Test
	void test2() {
		Treap<Integer> t2 = new Treap<Integer>();
		assertTrue(t2.add(10, 10));
		assertTrue(t2.add(3, 20));
		assertTrue(t2.add(14, 30));
		assertTrue(t2.add(35, 40));
		assertTrue(t2.add(7, 50));
		assertTrue(t2.add(11, 80));
		assertTrue(t2.add(12, 100));
		assertTrue(t2.find(12));
		assertTrue(t2.find(100));
		assertFalse(t2.find(47));
		assertFalse(t2.find(96));
		assertTrue(t2.delete(14));
		assertFalse(t2.delete(90));
		assertTrue(t2.find(7));
	}
	
}
