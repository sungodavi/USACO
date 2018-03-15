/*
ID: sungoda1
LANG: JAVA
TASK: fencedin
*/

import java.util.*;
import java.io.*;

class fencedin {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int rows = Integer.parseInt(st.nextToken());
		int cols = Integer.parseInt(st.nextToken());
		long[] v = new long[Integer.parseInt(st.nextToken()) + 1];
		long[] h = new long[Integer.parseInt(st.nextToken()) + 1];
		
		int[] temp = new int[v.length - 1];
		for(int i = 0; i < temp.length; i++)
			temp[i] = Integer.parseInt(f.readLine());
		Arrays.sort(temp);
		int prev = 0;
		for(int i = 0; i < temp.length; i++)
		{
			v[i] = temp[i] - prev;
			prev = temp[i];
		}
		v[v.length - 1] = rows - prev;
		Arrays.sort(v);
		temp = new int[h.length - 1];
		for(int i = 0; i < temp.length; i++)
			temp[i] = Integer.parseInt(f.readLine());
		Arrays.sort(temp);
		prev = 0;
		for(int i = 0; i < temp.length; i++)
		{
			h[i] = temp[i] - prev;
			prev = temp[i];
		}
		h[h.length - 1] = cols - prev;
		Arrays.sort(h);
		f.close();
//		System.out.println(Arrays.toString(h));
//		System.out.println(Arrays.toString(v));
		long result = h[0] * (v.length - 1) + v[0] * (h.length - 1);
		int i1 = 1, i2 = 1;
		while(i1 < h.length && i2 < v.length)
		{
			if(h[i1] < v[i2])
			{
				result += h[i1++] * (v.length - i2);
			}
			else
			{
				result += v[i2++] * (h.length - i1);
			}
		}
		out.println(result);
		out.close();
	}
	
	static class Wall implements Comparable<Wall>
	{
		int w;
		boolean flag; //true = vertical
		public Wall(int w, boolean flag)
		{
			this.w = w;
			this.flag = flag;
		}
		
		public int compareTo(Wall wall)
		{
			if(w == wall.w)
			{
				if(flag == wall.flag)
					return 0;
				if(flag)
					return 1;
				return -1;
			}
			return w - wall.w;
		}
	}
}