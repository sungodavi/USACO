/*
ID: sungoda1
LANG: JAVA
TASK: rectbarn
*/

import java.util.*;
import java.io.*;
import java.awt.Point;

class rectbarn 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("rectbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rectbarn.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		int[][] a = new int[rows][cols];
		int size = Integer.parseInt(st.nextToken());
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			a[x][y] = 1;
		}
		f.close();
		int[] dp = new int[cols];
		for(int i = 0; i < cols; i++)
			if(a[0][i] == 0)
				dp[i] = 1;
		int result = max(dp);
		for(int r = 1; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				if(a[r][c] == 0)
					dp[c]++;
				else
					dp[c] = 0;
			}
			
			result = Math.max(result, max(dp));
		}
		out.println(result);
		out.close();
		System.exit(0);
	}
	
	public static int max(int[] a)
	{
		Stack<Integer> st = new Stack<Integer>();
		int result = 0;
		for(int i = 0; i < a.length; i++)
		{
			while(!st.isEmpty() && a[st.peek()] > a[i])
			{
				int index = st.pop();
				int area = a[index] * (st.isEmpty() ? i : i - st.peek() - 1);
				result = Math.max(result, area);
			}
			st.push(i);
		}
		while(!st.isEmpty())
		{
			int index = st.pop();
			int area = a[index] * (st.isEmpty() ? a.length : a.length - st.peek() - 1);
			result = Math.max(result, area);
		}
		return result;
	}
}