/*
ID: sungoda1
LANG: JAVA
TASK: picture
 */

import java.util.*;
import java.io.*;

public class picture
{
	static Edge[] vq, hq;
	static int INF = (int)1e9;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("picture.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("picture.out")));
		StringTokenizer st;
		int size = Integer.parseInt(f.readLine());
		vq = new Edge[2 * size];
		hq = new Edge[2 * size];
		for(int i = 0; i < vq.length; i += 2)
		{
			st = new StringTokenizer(f.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			vq[i] = new Edge(y1, y2, x1, i >> 1, false);
			vq[i + 1] = new Edge(y1, y2, x2, i >> 1, true);
			hq[i] = new Edge(x1, x2, y1, i >> 1, false);
			hq[i + 1] = new Edge(x1, x2, y2, i >> 1, true);
		}
		Arrays.sort(vq);
		Arrays.sort(hq);
		out.println(sweep());
		out.close();
	}
	
	public static int sweep()
	{
		int lPrev = 0, xPrev = vq[0].p, result = 0;
		boolean[] flags = new boolean[vq.length / 2];
		for(Edge e : vq)
		{
			int sets = countSets(flags);
			flags[e.id] = !flags[e.id];
			int len = getLength(flags);
			result += Math.abs(len - lPrev) + 2 * sets * (e.p - xPrev);
//			System.out.println(e.p + " " + sets + " " + len + " " + result);
			lPrev = len;
			xPrev = e.p;
		}
		return result;
	}
	
	static int getLength(boolean[] flags)
	{
		int len = 0;
		int open = 0;
		int x1 = -INF;
		for(Edge e : hq)
		{
			if(!flags[e.id])
				continue;
			if(e.isEnd)
			{
				open--;
				if(open == 0)
				{
					len += e.p - x1;
					x1 = -INF;
				}
			}
			else
			{
				open++;
				if(x1 == -INF)
					x1 = e.p;
			}
		}
		return len;
	}
	
	static int countSets(boolean[] flags)
	{
		int result = 0;
		int open = 0;
		for(Edge e : hq)
		{
			if(!flags[e.id])
				continue;
			if(e.isEnd)
			{
				if(--open == 0)
					result++;
			}
			else
				open++;
		}
		return result;
	}
	
	static class Edge implements Comparable<Edge>
	{
		int start, end, p, id;
		boolean isEnd;
		public Edge(int start, int end, int p, int id, boolean isEnd)
		{
			this.start = start;
			this.end = end;
			this.p = p;
			this.id = id;
			this.isEnd = isEnd;
		}
		
		public int compareTo(Edge e)
		{
			if(p == e.p && isEnd != e.isEnd)
				return isEnd ? 1 : -1;
			return p - e.p;
		}
		
		public String toString()
		{
			return start + " " + end + " " + p + " " + isEnd;
		}
	}
}