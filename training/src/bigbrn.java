/*
ID: sungoda1
LANG: JAVA
TASK: bigbrn
*/

import java.util.*;
import java.io.*;

class bigbrn 
{
	static int[][] a;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bigbrn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken()) + 1;
		int blocks = Integer.parseInt(st.nextToken());
		int[][] map = new int[size][size];
		a = new int[size][size];
		while(blocks-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		for(int r = 1; r < size; r++)
			for(int c = 1; c < size; c++)
				a[r][c] = a[r][c - 1] + a[r - 1][c] - a[r - 1][c - 1] + map[r][c];
//		for(int[] temp : map)
//			System.out.println(Arrays.toString(temp));
//		System.out.println("--------------------");
//		for(int[] temp : a)
//			System.out.println(Arrays.toString(temp));
		out.println(solve());
		out.close();
	}
	
	public static int solve()
	{
		int result = 0;
		int n = a.length;
		for(int r = 1; r < n; r++)
			for(int c = 1; c < n; c++)
			{
//				System.out.println(r + " " + c + " " + search(r, c));
				result = Math.max(result, search(r, c) + 1);
			}
		return result;
	}
	
	public static int search(int r, int c)
	{
		int n = a.length;
		int low = 0;
		int high = n - Math.max(r, c);
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(getRect(r, c, mid) == 0 && (r + mid == a.length - 1 || c + mid == a.length - 1 || getRect(r, c, mid + 1) != 0))
				return mid;
			else if(getRect(r, c, mid) > 0)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}
	
	public static int getRect(int r, int c, int mid)
	{
		int r2 = r + mid;
		int c2 = c + mid;
		r--;
		c--;
		return a[r2][c2] - a[r][c2] - a[r2][c] + a[r][c];
	}
}