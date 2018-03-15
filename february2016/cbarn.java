import java.util.*;
import java.io.*;

class cbarn 
{
	static UF uf;
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int size = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Door[] a = new Door[size];
		uf = new UF(size);
		for(int i = 0; i < size; i++)
			a[i] = new Door(Integer.parseInt(f.readLine()));
		f.close();
		for(int i = 0; i < size - k; i++)
		{
			long min = Long.MAX_VALUE;
			int index = -1;
			int prev = -1;
			for(int j = 0; j < size; j++)
			{
				if(a[j] == null)
					continue;
				int p = uf.root((j + a.length - 1) % size);
				if(p == j)
					continue;
				long cost = a[p].cost(a[j]);
				if(cost < min)
				{
					min = cost;
					index = j;
					prev = p;
				}
			}
			uf.union(prev, index);
			a[prev].add(a[index]);
			a[index] = null;
		}
		long result = 0;
		for(Door d : a)
			if(d != null)
				result += d.curr;
		out.println(result);
		out.close();
		System.exit(0);
	}
	
	static class Door
	{
		long curr, sum, next;
		int length;
		public Door(int val)
		{
			next = sum = val;
			length = 1;
			curr = 0;
		}
		
		public long cost(int length)
		{
			return next + (length - 1) * sum;
		}
		
		public long cost(Door d)
		{
			return d.cost(length);
		}
		
		public void add(Door d)
		{
			sum += d.sum;
			next += d.cost(length + 1);
			curr += d.cost(length);
			length += d.length;
		}
		
		public String toString()
		{
			return curr + " " + length + " " + next + " " + sum;
		}
	}
	
	static class UF
	{
		int[] a;
		public UF(int size)
		{
			a = new int[size];
			for(int i = 1; i < size; i++)
				a[i] = i;
		}
		
		public int root(int p)
		{
			while(a[p] != p)
				p = a[p] = a[a[p]];
			return p;
		}
		
		public void union(int a, int b)
		{
			int p = root(a);
			int q = root(b);
			if(p == q)
				return;
			this.a[q] = p;
		}
	}
}