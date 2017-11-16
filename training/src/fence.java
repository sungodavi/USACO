/*
ID: sungoda1
LANG: JAVA
TASK: fence
*/
import java.util.*;
import java.io.*;

class fence 
{
	static int[] path;
	static class Edge
	{
		int v, w;
		boolean used;
		public Edge(int v, int w)
		{
			this.v = v;
			this.w = w;
			this.used = false;
		}
		public int other(int source)
		{
			if(source == v)
				return w;
			return v;
		}
		public String toString()
		{
			if(used)
				return "USED";
			return v + " " + w;
		}
	}
	
	static PrintWriter out;
	static HashMap<Integer, ArrayList<Edge>> map;
	public static void recurse(int source, ArrayList<Integer> result)
	{
		ArrayList<Edge> list = map.get(source);
		//System.out.println(source + " " + list);
		for(Edge e : list)
		{
			if(!e.used)
			{
				e.used = true;
				recurse(e.other(source), result);
			}
		}
		result.add(source);
	}
	
	static class C implements Comparator<Edge>
	{
		int num;
		public C(int num)
		{
			this.num = num;
		}
		public int compare(Edge e, Edge v)
		{
			return e.other(num) - v.other(num);
		}
	}
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("fence.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		int times = Integer.parseInt(f.readLine());
		
		map = new HashMap<Integer, ArrayList<Edge>>();
		int min = Integer.MAX_VALUE;
		int start = Integer.MAX_VALUE;
		for(; times > 0; times--)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(!map.containsKey(r))
				map.put(r, new ArrayList<Edge>());
			if(!map.containsKey(c))
				map.put(c, new ArrayList<Edge>());
			Edge e = new Edge(r, c);
			map.get(r).add(e);
			map.get(c).add(e);
			min = Integer.min(Integer.min(r, c), min);
		}
		for(int num : map.keySet())
		{
			Collections.sort(map.get(num), new C(num));
			if(map.get(num).size() % 2 != 0)
			{
				start = Integer.min(start, num);
			}
				
		}
		//System.out.println(map);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(start < Integer.MAX_VALUE)
			recurse(start, list);
		else
			recurse(min, list);
		Collections.reverse(list);
		for(int num : list)
			out.println(num);
		out.close();
	}
}
