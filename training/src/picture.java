/*
ID: sungoda1
LANG: JAVA
TASK: picture
 */

import java.util.*;
import java.io.*;

public class picture
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("picture.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("picture.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
	}
	
	static class SegTree
	{
		int[] a;
		public SegTree(int size)
		{
			a = new int[4 * size];
		}
	}
}