/*
ID: sungoda1
LANG: JAVA
TASK: bphoto
 */

import java.util.*;
import java.io.*;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

public class bphoto 
{
	static int left, right, index;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"bphoto.out")));
		int size = Integer.parseInt(f.readLine());
		int[] a = new int[size];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(f.readLine());
		Tree t = new Tree(a);
		int count = 0;
		for(int i = 0; i < a.length; i++)
		{
			left = right = 0;
			t.count(t.head, a[i], i);
			if(left < right && left << 1 < right)
				count++;
			else if(right < left && right << 1 < left)
				count++;
		}
		out.println(count);
		out.close();
	}
	
	static class Tree
	{
		Node head;
		public Tree(int[] a)
		{
			head = new Node(a[0], 0);
			for(int i = 1; i < a.length; i++)
				add(head, a[i], i);
		}
		
		public void count(Node n, int val, int index)
		{
			if(n == null)
				return;
			if(n.val > val)
			{
				if(n.index > index)
					right++;
				else
					left++;
				count(n.right, val, index);
				count(n.left, val, index);
			}
			else if(n.max > val)
				count(n.right, val, index);
		}
		public void add(Node n, int curr, int index)
		{
			if(curr > n.val)
			{
				if(curr > n.max)
					n.max = curr;
				if(n.right == null)
					n.right = new Node(curr, index);
				else
					add(n.right, curr, index);
			}
			else
			{
				if(n.left == null)
					n.left = new Node(curr, index);
				else
					add(n.left, curr, index);
			}
		}
		public void display(Node n)
		{
			if(n != null)
			{
				System.out.println(n);
				display(n.left);
				display(n.right);
			}
		}
	}
	
	static class Node
	{
		int val, max, index;
		Node left, right;
		public Node(int val, int index)
		{
			this.val = val;
			this.max = -1;
			this.index = index;
		}
		public String toString()
		{
			return val + " " + max;
		}
	}
}