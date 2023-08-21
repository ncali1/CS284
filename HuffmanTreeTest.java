package Homeworks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class HuffmanTreeTest {

	@Test
	void testBitsToString() {
		String s = "testing this string to see if it encodes";
		HuffmanTree t = new HuffmanTree(s);
		
		String res = "110";
		Boolean[] b1 = {true, true, false};
		assertEquals(res, t.bitsToString(b1));
	}

	@Test
	void testDecode() {
		String s = "testing this string to see if it encodes";
		HuffmanTree t = new HuffmanTree(s);
		
		Boolean[] b1 = { false, true, true, false, false, false, false, false, true, true, false, true, false, true,
				false, false, true, true, false, false, true, true, true, false, true, true };
		assertEquals("string", t.decode(b1));
		
	}

	@Test
	void testEncode() {
		String s = "testing this string to see if it encodes";
		HuffmanTree t = new HuffmanTree(s);
		
		String res = "[false, true, true, false, false, false, false, false, true, true, false, true, false, true, false, false, true, true, false, false, true, true, true, false, true, true]";
		assertEquals(res, Arrays.deepToString(t.encode("string")));
	}

	@Test
	void testEfficientEncode() {
		String s = "testing this string to see if it encodes";
		HuffmanTree t = new HuffmanTree(s);

		String res = "[false, true, true, false, false, false, false, false, true, true, false, true, false, true, false, false, true, true, false, false, true, true, true, false, true, true]";
		assertEquals(res, Arrays.deepToString(t.efficientEncode("string")));
	}

}
