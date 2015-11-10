package haffman;

import java.util.Map;

public class HuffmanTree {

	final static int NCHAR = 128;

	static abstract class Tree {
		int weight;
		abstract void buildCode(Code code, String path);
		public abstract String toString();
	}

	static class Node extends Tree {
		Tree left, right;

		Node(Tree l, Tree r, int w) {
			left = l;
			right = r;
			weight = w;
		}

		public String toString() {
			return "##"; 
		}

		void buildCode(Code code, String path) {
			
		}
	}

	static class Leaf extends Tree {
		char ch;

		Leaf(char c, int w) {
			ch = c;
			weight = w;
		}

		public String toString() {
			return "#" + ch;
		}

		void buildCode(Code code, String path) {
			code.table[ch] = path;
		}
	}



	static class Code {
		String[] table;

		Code(Tree tree) {
			table = new String[NCHAR];
			tree.buildCode(this, "");
		}

		double bitsPerChar(String text) {
			int count = 0;

			for (int i = 0; i < text.length(); i++)
				count += table[text.charAt(i)].length();

			return (double) count / text.length();
		}
	}

	static int[] freqCount(String text) {
		int freq[] = new int[NCHAR];

		for (int i = 0; i < text.length(); i++)
			freq[text.charAt(i)]++;

		return freq;
	}

	/* Make the Huffman tree for a text. */

	static Tree makeTree(String text) {


		int freq[] = freqCount(text);
		Tree root[] = new Tree[NCHAR];
		int n = 0;
		for (char i = 0; i < freq.length; i++)
			if (freq[i] != 0)
				root[n++] = new Leaf(i, freq[i]);

		// Repeatedly coalesce lightweight roots.

		while (n > 1) {
			int i0 = 0; // indexes of two least-weight roots
			int i1 = 1;
			if (root[i0].weight > root[i1].weight) {
				i0 = 1;
				i1 = 0;
			}
			for (int i = 2; i < n; i++)
				if (root[i].weight < root[i0].weight) {
					i1 = i0;
					i0 = i;
				} else if (root[i].weight < root[i1].weight)
					i1 = i;

			// Combine roots numbered i0 and i1 into i0.
			// Squeeze out the obsolete root i1.

			int weight = root[i0].weight + root[i1].weight;
			root[i0] = new Node(root[i0], root[i1], weight);
			for (int i = i1 + 1; i < n; i++)
				root[i - 1] = root[i];
			n--;
		}

		return root[0];
	}

	/*
	 * Reconstruct a Huffman tree from the string-form (see Tree.toString())
	 * starting at character position pos in a given string.
	 * 
	 * A Parse consists of the Huffman tree that the string-form denotes the
	 * position of the next character after the string-form.
	 */

	static class Parse {
		Tree tree;
		int next;

		Parse(String s, int pos) {
			if (s.charAt(pos) == '*') {
				tree = new Leaf('*', 1); // REPLACE
				next = pos + 1; // THIS CODE
			} else {
				tree = new Leaf(s.charAt(pos + 1), 0);
				next = pos + 2;
			}
		}
	}

	/*
	 * Use the codetable to encode a given message. Example: Encoder encoder =
	 * new Encoder(code); encoder.encode("my input");
	 */

	static class Encoder {
		String[] codetable;

		Encoder(Code code) {
			codetable = code.table;
		}

		public String encode(String text) {
			char[] input = text.toCharArray();
			String result = "";

			for (int i = 0; i < input.length; i++) { // for each character...
				result = result + codetable[input[i]];
			}

			return result;
		}
	}

	/*
	 * Use the Huffman-Tree to decode encoded message. Example: Decoder decoder
	 * = new Decoder(tree); decoder.decode("1110000");
	 */

	static class Decoder {
		Tree root; // root of the Huffman-Tree
		Tree pos; // position in the tree

		Decoder(Tree tree) {
			root = tree;
		}

		public String decode(String text) {
			char[] input = text.toCharArray();
			String result = "";
			pos = root;

			for (int i = 0; i < input.length; i++) { // for each character
				if (pos instanceof Node) {
					if (input[i] == '1')
						pos = ((Node) pos).left;
					if (input[i] == '0')
						pos = ((Node) pos).right;
				}
				if (pos instanceof Leaf) {
					result = result + ((Leaf) pos).ch;
					pos = root;
				}
			}

			return result;
		}
	}
}
