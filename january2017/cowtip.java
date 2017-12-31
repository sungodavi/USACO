/*
ID: sungoda1
LANG: JAVA
TASK: cowtip
 */

import java.util.*;
import java.io.*;


public class cowtip 
{
	static int[][] a;
	static int size;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowtip.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"cowtip.out")));
		size = Integer.parseInt(f.readLine());
		a = new int[size][size];
		for(int i = 0; i < a.length; i++)
		{
			String s = f.readLine();
			for(int c = 0; c < s.length(); c++)
				a[i][c] = s.charAt(c) - '0';
		}
		int[][] moves = new int[size][size];
		for(int r = 0; r < a.length; r++)
			for(int c =0; c < a.length; c++)
			{
				if(a[r][c] == 1)
				{
					a[r][c] = 0;
					moves[r][c] ^= 1;
					if(r > 0)
						moves[r - 1][c] ^= 1;
					if(c > 0)
						moves[r][c - 1] ^= 1;
					if(r > 0 && c > 0)
						moves[r - 1][c - 1] ^= 1;
				}
			}
		int sum = 0;
		for(int[] b : moves)
			for(int n: b)
				sum += n;
		out.println(sum);
		out.close();
	}
	
	public static boolean check(int[] a)
	{
		for(int num : a)
			if(num > 0)
				return false;
		return true;
	}
}