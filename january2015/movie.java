import java.util.*;
import java.io.*;

class movie 
{
	static TreeMap<State, Integer> dp;
	static int time;
	static Interval[][] a;
	static final int LIMIT = 21;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("movie.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("movie.out")));
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int movies = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		a = new Interval[movies][];
		for(int i = 0; i < a.length; i++)
		{
			st = new StringTokenizer(f.readLine());
			int duration = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			a[i] = new Interval[size];
			for(int j = 0; j < size; j++)
			{
				int s = Integer.parseInt(st.nextToken());
				a[i][j] = new Interval(s, s + duration);
			}
		}
		dp = new TreeMap<State, Integer>();
		int result = recurse(0, 0);
		out.println(result != LIMIT ? result : -1);
		out.close();
	}
	
	public static int recurse(int mask, int t)
	{
		if(t >= time)
			return 0;
		State s = new State(mask, t);
		if(dp.containsKey(s))
			return dp.get(s);
		
		int best = LIMIT;
		for(int i = 0; i < a.length; i++)
		{
			int flag = 1 << i;
			if((mask & flag) == 0)
			{
				int index = binarySearch(a[i], t);
				if(index != -1)
					best = Math.min(best, recurse(mask | flag, a[i][index].e) + 1);
			}
		}
		dp.put(s, best);
		return best;
	}
	
	public static int binarySearch(Interval[] a, int t)
	{
		int low = 0; 
		int high = a.length - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(a[mid].contains(t) && (mid == a.length - 1 || a[mid + 1].compareTo(t) > 0))
			{
				return mid;
			}
			if(a[mid].compareTo(t) <= 0)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return -1;
	}
	
	static class State implements Comparable<State>
	{
		int mask, time;
		
		public State(int mask, int time)
		{
			this.mask = mask;
			this.time = time;
		}
		
		public int compareTo(State s)
		{
			if(time != s.time)
				return time - s.time;
			return mask - s.mask;
		}
	}
	
	static class Interval
	{
		int s, e;
		public Interval(int s, int e)
		{
			this.s = s;
			this.e = e;
		}
		
		public boolean contains(int t)
		{
			return s <= t && t <= e;
		}
		
		public int compareTo(int t)
		{
			if(contains(t))
				return 0;
			if(t < s)
				return 1;
			return -1;
		}
		
		public String toString()
		{
			return s + " " + e;
		}
	}
}