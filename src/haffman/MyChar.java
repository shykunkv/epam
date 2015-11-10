package haffman;

public class MyChar {
	private char value;
	private int freq;
	
	public MyChar(char ch, int freq) {
		this.value = ch;
		this.freq = freq;
	}

	public char getCh() {
		return value;
	}

	public void setCh(char ch) {
		this.value = ch;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	
	
	
}
