import java.util.*;
import java.io.*;

public class haybales
{
	public static void main(String[] args) throws IOException
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int queries = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		long[] a = new long[size];
		for(int i = 0; i < size; i++)
			a[i] = Integer.parseInt(st.nextToken());
		Tree t = new Tree(a);
		while(queries-- > 0)
		{
			st = new StringTokenizer(f.readLine());
			char type = st.nextToken().charAt(0);
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			switch(type)
			{
			case 'M': 
				out.println(t.min(s, e));
				break;
			case 'P': 
				int val = Integer.parseInt(st.nextToken());
				t.update(s, e,  val);
				break;
			case 'S':
				out.println(t.sum(s, e)); 
				break;
			}
		}
		out.close();
	}
	
	static class Tree
	{
		long[] a, lazy, min, sum;
		public Tree(long[] a)
		{
			this.a = a;
			int n = a.length << 2;
			lazy = new long[n];
			min = new long[n];
			sum = new long[n];
			build(0, a.length - 1, 0);
		}
		
		public void build(int s, int e, int index)
		{
			if(s == e)
				min[index] = sum[index] = a[s];
			else
			{
				int mid = s + e >> 1;
				build(s, mid, 2 * index + 1);
				build(mid + 1, e, 2 * index + 2);
				min[index] = Math.min(min[2 * index + 1], min[2 * index + 2]);
				sum[index] = sum[2 * index + 1] + sum[2 * index + 2];
			}
		}
		
		public long sum(int s, int e)
		{
			return sum(0, a.length - 1, s, e, 0);
		}
		
		public long min(int s, int e)
		{
			return min(0, a.length - 1, s, e, 0);
		}
		
		public void update(int s, int e, int val)
		{
			update(0, a.length - 1, s, e, 0, val);
		}
		
		public long sum(int s, int e, int qs, int qe, int index)
		{
			if(s > e || e < qs || s > qe)
				return 0;
			updateNode(s, e, index);
			if(s >= qs && e <= qe)
				return sum[index];
			int mid = s + e >> 1;
			long p1 = sum(s, mid, qs, qe, 2 * index + 1);
			long p2 = sum(mid + 1, e, qs, qe, 2 * index + 2);
			return p1 + p2;
		}
		
		public long min(int s, int e, int qs, int qe, int index)
		{
			if(s > e || e < qs || s > qe)
				return Integer.MAX_VALUE;
			updateNode(s, e, index);
			if(s >= qs && e <= qe)
				return min[index];
			int mid = s + e >> 1;
			long p1 = min(s, mid, qs, qe, 2 * index + 1);
			long p2 = min(mid + 1, e, qs, qe, 2 * index + 2);
			return Math.min(p1, p2);
		}
		
		public void update(int s, int e, int qs, int qe, int index, long val)
		{
			updateNode(s, e, index);
			if(e < qs || s > qe)
				return;
			if(s >= qs && e <= qe)
			{
				if((e - s + 1) * val < 0)
					System.out.println("error");
				sum[index] += (e - s + 1) * val;
				min[index] += val;
				if(s != e)
				{
					lazy[2 * index + 1] += val;
					lazy[2 * index + 2] += val;
				}
				return;
			}
			if(s != e)
			{
				int mid = s + e >> 1;
				update(s, mid, qs, qe, 2 * index + 1, val);
				update(mid + 1, e, qs, qe, 2 * index + 2, val);
				long temp = sum[index];
				sum[index] = sum[2 * index + 1] + sum[2 * index + 2];
				min[index] = Math.min(min[2 * index + 1], min[2 * index + 2]);
			}
		}
		
		public void updateNode(int s, int e, int index)
		{
			if(lazy[index] != 0)
			{
				sum[index] += (e - s + 1) * lazy[index];
				min[index] += lazy[index];
				if(s != e)
				{
					lazy[2 * index + 1] += lazy[index];
					lazy[2 * index + 2] += lazy[index];
				}
				lazy[index] = 0;
			}
		}
		
		public String toString()
		{
			return String.format("%s\n%s\n%s", Arrays.toString(min), Arrays.toString(sum), Arrays.toString(lazy));
		}
	}
}