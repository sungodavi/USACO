/*
ID: sungoda1
LANG: JAVA
TASK: bigbrn
*/

import java.util.*;
import java.io.*;

class bigbrn {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bigbrn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigbrn.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken()) + 1;
		int blocks = Integer.parseInt(st.nextToken());
		int[][] map = new int[size][size];
		int[][] a = new int[size][size];
		while(blocks-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		for(int r = 0; r < size; r++)
			for(int c = 1; c < size; c++)
				a[r][c] = a[r][c - 1] + map[r][c];
		for(int[] temp : a)
			System.out.println(Arrays.toString(temp));
		out.println(solve(a));
		out.close();
	}
	
	public static int solve(int[][] a)
	{
		int n = a.length;
		int result = 0;
		for(int size = n - 1; size > result; size--)
		{
			for(int c = 1; c <= n - size; c++)
			{
				int count = 0;
				for(int r = 1; r < n; r++)
				{
					if(a[r][c - 1] == a[r][c + size - 1])
					{
						count++;
						if(count == size)
							return size;
						result = Math.max(result, count);
					}
					else
					{
						count = 0;
						if(n - 1 - r < size)
							break;
					}
				}
			}
		}
		return result;
	}
	
	public static int search(int[] a, int low)
	{
		int high = a.length - 1;
		int val = a[low];
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(a[mid] == val && (mid == a.length - 1 || a[mid + 1] != val))
				return mid;
			if(a[mid] > val)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}
}