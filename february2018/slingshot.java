import java.util.*;
import java.io.*;

class slingshot 
{
	static ArrayList<Integer> y;
	static HashSet<Integer> set;
	static final long INF = (long)1e18;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("slingshot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("slingshot.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int queries = Integer.parseInt(st.nextToken());
		y = new ArrayList<Integer>(size);
		set = new HashSet<Integer>(size);
		SS[] a = new SS[size];
		for(int i = 0; i < size; i++)
		{
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			a[i] = new SS(x, y, t);
			if(set.add(y))
				slingshot.y.add(y);
		}
		Q[] q = new Q[queries];
		for(int i = 0; i < queries; i++)
		{
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			q[i] = new Q(x, y, i);
		}
		f.close();
		Collections.sort(y);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < y.size(); i++)
			map.put(y.get(i), i);
		int limit = y.get(y.size() - 1);
		Arrays.sort(a);
		Arrays.sort(q);

		long[] result = new long[q.length];
		for(int i = 0; i < result.length; i++)
			result[i] = Math.abs(q[i].a - q[i].b);
		SegTree front = new SegTree(), outer = new SegTree();
		int index = 0;
		for(int i = 0; i < q.length; i++)
		{
			int s = q[i].a;
			int e = q[i].b;
			while(index < a.length && a[index].x <= s)
			{
				front.update(map.get(a[index].y), a[index].t - a[index].x - a[index].y);
				outer.update(map.get(a[index].y), a[index].t - a[index].x + a[index].y);
				index++;
			}
			result[i] = min(result[i], s + e + front.query(0, e), s - e + outer.query(e, limit));
		}
		index = a.length - 1;
		SegTree inner = new SegTree(), last = new SegTree();
		for(int i = q.length - 1; i >= 0; i--)
		{
			int s = q[i].a;
			int e = q[i].b;
			while(index >= 0 && a[index].x >= s)
			{
				inner.update(map.get(a[index].y), a[index].t + a[index].x - a[index].y);
				last.update(map.get(a[index].y), a[index].t + a[index].x + a[index].y);
				index--;
			}
			result[i] = min(result[i], e - s + inner.query(0, e), last.query(e, limit) - s - e);
		}
		long[] temp = new long[result.length];
		for(int i = 0; i < temp.length; i++)
			temp[q[i].index] = result[i];
		for(long num : temp)
			out.println(num);
		out.close();
		System.exit(0);
	}
	
	static long min(long a, long b, long c)
	{
		if(a < b && a < c)
			return a;
		if(b < c)
			return b;
		return c;
	}
	
	static class Q implements Comparable<Q>
	{
		int a, b, index;
		public Q(int a, int b, int index)
		{
			this.a = a;
			this.b = b;
			this.index = index;
		}
		
		public int compareTo(Q q)
		{
			return a - q.a;
		}
	}
	
	static class SS implements Comparable<SS>
	{
		int x, y, t;
		public SS(int x, int y, int t)
		{
			this.x = x;
			this.y = y;
			this.t = t;
		}
		
		public int compareTo(SS s)
		{
			return x - s.x;
		}
	}
	
	static class SegTree
	{
		long[] a;
		int qs, qe, n;
		public SegTree()
		{
			n = y.size();
			a = new long[2 * n + 2];
			Arrays.fill(a, INF);
		}
		
		public void update(int pos, int val)
		{
			for(pos += n; pos > 0; pos >>= 1)
				a[pos] = Math.min(a[pos], val);
		}
		
		public long query(int s, int e)
		{
			long result = INF;
			if(s > e)
				return INF;
			s = search(s, true);
			e = search(e, false);
			if(s > e || s >= n || e < 0)
				return result;
			for(s += n, e += n + 1; s < e; s >>= 1, e >>= 1)
			{
				if(s % 2 != 0)
					result = Math.min(result, a[s++]);
				if(e % 2 != 0)
					result = Math.min(result, a[--e]);
			}
			return result;
		}
		
		public String toString()
		{
			return Arrays.toString(a);
		}
	}
	
	static int search(int val, boolean upper)
	{
		int low = 0;
		int high = y.size() - 1;
		while(low <= high)
		{
			int mid = low + high >> 1;
			if(val == y.get(mid))
				return mid;
			if(val > y.get(mid))
				low = mid + 1;
			else
				high = mid - 1;
		}
		return upper ? low : high;
	}
}