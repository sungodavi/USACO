/*
ID: sungoda1
LANG: JAVA
TASK: holstein
*/
import java.util.*;
import java.io.*;

class Vitamin
{
	int[] food;
	ArrayList<Integer> feeds;
	public Vitamin(int[] a, ArrayList<Integer> list)
	{
		food = a;
		feeds = list;
	}

}
class Vitamins
{
	int[][] feeds;
	int[] target;
	public Vitamins(int[][] a, int[] goal)
	{
		feeds = a;
		target = goal;
	}
	public boolean check(int[] a)
	{
		for(int i = 0; i < a.length; i++)
			if(a[i] < target[i])
				return false;
		return true;
	}
	public ArrayList<Integer> bfs()
	{
		Queue<Vitamin> q = new LinkedList<Vitamin>();
		q.add(new Vitamin(new int[target.length], new ArrayList<Integer>()));
		while(!q.isEmpty())
		{
			Vitamin v = q.poll();
			if(check(v.food))
				return v.feeds;
			int start;
			if(v.feeds.size() == 0)
				start = 0;
			else
				start = v.feeds.get(v.feeds.size() - 1);
			for(int i = start; i < feeds.length; i++)
			{
				ArrayList<Integer> list = new ArrayList<Integer>(v.feeds);
				int[] total = new int[target.length];
				for(int j =0; j < total.length; j++)
					total[j] = v.food[j] + feeds[i][j];
				list.add(i + 1);
				q.add(new Vitamin(total, list));
			}
		}
		return null;
	}
	

}
class holstein
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int[] target = new int[Integer.parseInt(st.nextToken())];
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < target.length; i++)
			target[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[][] feeds = new int[Integer.parseInt(st.nextToken())][target.length];
		
		for(int r = 0; r < feeds.length; r++)
		{
			st = new StringTokenizer(f.readLine());
			for(int c = 0; c < target.length; c++)
				feeds[r][c] = Integer.parseInt(st.nextToken());
		}
		Vitamins v = new Vitamins(feeds, target);
		ArrayList<Integer> list = v.bfs();
		out.print(list.size() + " ");
		for(int i =0; i < list.size(); i++)
			out.print(list.get(i) + (i == list.size() - 1 ? "" : " "));
		out.println();
		out.close();
	}
}	
