package Homeworks;

import java.util.Arrays;

public class BinaryNumber {

	private int data[];
	private int length;
	
	public BinaryNumber(int length) {
		this.length = length;
		this.data = new int[length];
	}
	
	public BinaryNumber(String str) {
		this.length = str.length();
		this.data = new int[str.length()];
		for (int i=0; i < str.length(); i++) {
			if (str.charAt(i) != '0' && str.charAt(i) != '1')
				throw new IllegalStateException();
		this.data[i] = Character.getNumericValue(str.charAt(i));
		}
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getDigit(int index) {
		if(index>= this.length | index<0)
			throw new ArrayIndexOutOfBoundsException("The given index is out of bounds.");
		return this.data[index];
	}
	

	public int[] getInnerArray() {
		return data;
	}
	
	public int toDecimal() {
		int num = 0;
		for (int i=0; i < length; i++) {
			if(this.data[i]==1)
				num += Math.pow(2, length - i -1);
			}
		return num;
		}

	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		int array[] = new int[bn1.length];
		if (bn1.length == bn2.length) {
			for (int i=0; i <bn1.length; i++) {
				if (bn1.data[i] == 1 || bn2.data[i] == 1)
					array[i] = 1;
				else 
					array[i] = 0;
				}
			}
		return array;
		}
		
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		int array[] = new int[bn1.length];
		if (bn1.length == bn2.length) {
			for (int i=0; i <bn1.length; i++) {
				if(bn1.data[i] == 1 && bn2.data[i] == 1)
					array[i] = 1;
				else
					array[i] = 0;
			}
		}
		return array;
	}
	
	
	public void bitShift(int direction, int amount) {
		int array[];
		if (amount<0) {
			System.out.print("Invalid Length");
		}
		if(direction == 1) {
			if (amount > this.length)
				throw new NegativeArraySizeException("");
			array = new int[this.length - amount];
			array = Arrays.copyOf(this.data, this.length-amount);
			this.data = array;
			this.length = this.data.length;
		}
		
	}
	
	public void add(BinaryNumber aBinaryNumber) {
		int carry = 0;
		if(this.length < aBinaryNumber.length) {
				int newarray[] = new int[aBinaryNumber.length];
				for(int i = 0; i < this.length; i++) {
						newarray[i + this.length] = this.data[i];
			}
			this.data = newarray;
			this.length = this.data.length;
		
		
		}	
		else if(this.length > aBinaryNumber.length) {
				int newarray[] = new int[this.length];
				for(int i = 0; i < aBinaryNumber.length; i++) {
					newarray[i + aBinaryNumber.length] = aBinaryNumber.data[i];
				}
				aBinaryNumber.data = newarray;
				aBinaryNumber.length = aBinaryNumber.data.length;
		}
		for(int i = this.length - 1; i >= 0; i--) {
				int x = this.data[i] + aBinaryNumber.data[i] + carry;
				if(x < 2) {
					this.data[i] = x;
					carry = 0;
				}
				else{
					this.data[i] = x - 2;
					carry = 1;
			}
		}
		if(carry == 1) {
				int newarray[] = new int[this.length + 1];
				for(int i = 0; i < this.length; i++) {
					newarray[i + 1] = this.data[i];
			}
			newarray[0] = 1;
			this.data = newarray;
		}
		this.length = this.data.length;
	}
	public String toString() {
		String s = "";
		for(int i = 0; i < this.length; i++)
			s += String.valueOf(this.data[i]);
	return s;
}
	
	public static void main(String[] args) {
		BinaryNumber bn1 = new BinaryNumber("1001");
		BinaryNumber bn2 = new BinaryNumber("0111");
		bn1.add(bn2);
		System.out.println(bn1);
	}

	
	
}
