import java.util.*;
import java.io.*;

class lifeguards 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("lifeguards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Interval[] a = new Interval[size];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			a[i] = new Interval(s, e);
		}
		Arrays.sort(a);
		ArrayList<Interval> list = new ArrayList<Interval>();
		list.add(a[0]);
		for(int i = 1; i < size; i++)
		{
			Interval prev = list.get(list.size() - 1);
			if(!(prev.s <= a[i].s && prev.e >= a[i].e))
				list.add(a[i]);
			else
				k--;
		}
	}
	
	static class Interval implements Comparable<Interval>
	{
		int s, e;
		public Interval(int s, int e)
		{
			this.s = s;
			this.e = e;
		}
		
		public int size()
		{
			return e - s;
		}
		
		public int compareTo(Interval i)
		{
			if(s == i.s)
				return i.e - e;
			return s - i.s;
		}
	}
}