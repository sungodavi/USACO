import java.util.*;
import java.io.*;

public class promote
{
	static LinkedList<Integer>[] list;
	static boolean[] visited;
	static int counter;
	static int[] curr, max;
	public static void main(String[] args) throws IOException
	{
//		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		BufferedReader f = new BufferedReader(new FileReader("promote.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
		int size = Integer.parseInt(f.readLine());
		curr = new int[size];
		max = new int[size];
		list = new LinkedList[size];
		for(int i = 0; i < list.length; i++)
			list[i] = new LinkedList<Integer>();
		visited = new boolean[size];
		Cow[] a = new Cow[size];
		int[] result = new int[size];
		for(int i = 0; i < a.length; i++)
			a[i] = new Cow(i, Integer.parseInt(f.readLine()));
		Arrays.sort(a);
		for(int v = 1; v < size; v++)
		{
			int u = Integer.parseInt(f.readLine()) - 1;
			list[u].add(v);
			list[v].add(u);
		}
		dfs(0);
//		System.out.println(Arrays.toString(curr));
//		System.out.println(Arrays.toString(max));
//		System.out.println(Arrays.toString(a));
		FenwickTree tree = new FenwickTree(size);
		for(int i = a.length - 1; i >= 0; i--)
		{
			int index = a[i].index;
			result[index] = tree.sum(curr[index], max[index]);
			tree.update(curr[index], 1);
		}
		for(int i = 0; i < size; i++)
			out.println(result[i]);
		out.close();
		f.close();
	}
	
	public static int dfs(int u)
	{
		if(visited[u])
			return -1;
		visited[u] = true;
		int m = curr[u] = counter++;
		for(int v : list[u])
			m = Math.max(m, dfs(v));
		return max[u] = m;
	}
	
	static class FenwickTree
	{
		int[] a;
		public FenwickTree(int size)
		{
			a = new int[size + 1];
		}
		
		public int sum(int index)
		{
			int sum = 0;
			for(index++; index > 0; index -= index & -index)
				sum += a[index];
			return sum;
		}
		
		public int sum(int s, int e)
		{
//			System.out.println(Arrays.toString(a));
//			System.out.println(s + " " + e + " " + sum(e) + " " + sum(s - 1));
			return sum(e) - sum(s - 1);
		}
		
		public void update(int index, int val)
		{
			for(index++; index < a.length; index += index & -index)
				a[index] += val;
		}
	}
	
	static class Cow implements Comparable<Cow>
	{
		int index, rating;
		public Cow(int index, int rating)
		{
			this.index = index;
			this.rating = rating;
		}
		
		public int compareTo(Cow c)
		{
			return Integer.compare(rating, c.rating);
		}
		
		public String toString()
		{
			return index + " " + rating;
		}
	}
}