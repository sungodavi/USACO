/*
ID: sungoda1
LANG: JAVA
TASK: heritage
 */

import java.util.*;
import java.io.*;

public class heritage 
{
	static Node head;
	static char[] in, pre;
	static int place;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"heritage.out")));
		in = f.readLine().toCharArray();
		pre = f.readLine().toCharArray();
		int size = 0;
		for(int i = 0; i < in.length; i++)
			size = size * 2 + 2;
		head = build(0, in.length - 1);
		out.println(postOrder(head));
		out.close();
	}
	
	public static Node build(int s, int e)
	{
		Node n = null;
		for(int i = s; i <= e && place < pre.length; i++)
			if(in[i] == pre[place])
			{
				n = new Node(in[i]);
				place++;
				n.left = build(s, i - 1);
				n.right = build(i + 1, e);
			}
		return n;
	}

	public static String postOrder(Node n)
	{
		if(n == null)
			return "";
		return postOrder(n.left) + postOrder(n.right) + n.val;
	}
	
	static class Node
	{
		char val;
		Node left, right;
		
		public Node(char c)
		{
			val = c;
		}
	}
}