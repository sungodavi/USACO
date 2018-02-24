import java.util.*;
import java.io.*;

public class censor2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("input.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.out")));
		String s = f.readLine();
		Trie t = new Trie();
		int times = Integer.parseInt(f.readLine());
		while(times-- > 0)
			t.add(f.readLine());
		
		
		char[] result = new char[s.length() + 1];
		Node[] prev = new Node[s.length()];
		int length = 0;
		for(int i = 0; i < s.length(); i++)
		{
			result[length++] = s.charAt(i);
			int index = s.charAt(i) - 'a';
			Node curr;
			if(length < 2)
				curr = t.root;
			else
				curr = prev[length - 2];
			if(curr.kids[index] == null)
			{
				if(t.root.kids[index] == null)
					prev[length - 1] = t.root;
				else
					prev[length - 1] = t.root.kids[index];
			}
			else
			{
				prev[length - 1] = curr.kids[index];
			}
			if(prev[length - 1].size > 0)
				length -= prev[length - 1].size;
			//System.out.println(Arrays.toString(prev) + length);
		}
		for(int i = 0; i < length; i++)
			out.print(result[i]);
		out.close();
	}
	
	static class Trie
	{
		Node root;
		public Trie()
		{
			root = new Node();
		}
		public void add(String s)
		{
			Node n = root;
			for(int i = 0; i < s.length(); i++)
			{
				int index = s.charAt(i) - 'a';
				if(n.kids[index] == null)
					n.kids[index] = new Node();
				n = n.kids[index];
			}
			n.size = s.length();
		}
	}
	
	static class Node
	{
		Node[] kids;
		int size;
		public Node()
		{
			kids = new Node[26];
		}
		
		public String toString()
		{
			String s = "";
			for(int i = 0; i < kids.length; i++)
				if(kids[i] != null)
					s += (char)(i + 'a');
			return s;
		}
	}
}