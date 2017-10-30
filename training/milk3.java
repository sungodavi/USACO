/*
ID: sungoda1
LANG: JAVA
TASK: milk3
*/
import java.util.*;
import java.io.*;


class milk3
{
	public static boolean[][] visited;
	public static HashSet<Integer> set = new HashSet<Integer>();
	public static void solve(int[] a, int[] limits, boolean first)
	{
		if(visited[a[0]][a[1]])
			return;
		visited[a[0]][a[1]] = true;
		if(a[0] == 0)
			set.add(a[2]);
		if(a[0] > 0)
		{
			if(a[1] != limits[1])
			{
				int num;
				if(limits[1] - a[1] > a[0])
					num = a[0];
				else
					num = limits[1] - a[1];
				a[1] += num;
				a[0] -= num;
				solve(a, limits, first);
				a[1] -= num;
				a[0] += num;
			}
			if(a[2] != limits[2])
			{
				int num;
				if(limits[2] - a[2] > a[0])
					num = a[0];
				else
					num = limits[2] - a[2];
				a[2] += num;
				a[0] -= num;
				solve(a, limits, first);
				a[2] -= num;
				a[0] += num;
			}
		}
		if(a[1] > 0)
		{
			if(a[0] != limits[0])
			{
				int num;
				if(limits[0] - a[0] > a[1])
					num = a[1];
				else
					num = limits[0] - a[0];
				a[0] += num;
				a[1] -= num;
				solve(a, limits, first);
				a[0] -= num;
				a[1] += num;
			}
			if(a[2] != limits[2])
			{
				int num;
				if(limits[2] - a[2] > a[1])
					num = a[1];
				else
					num = limits[2] - a[2];
				a[2] += num;
				a[1] -= num;
				solve(a, limits, first);
				a[2] -= num;
				a[1] += num;
			}
		}
		if(a[2] > 0)
		{
			if(a[1] != limits[1])
			{
				int num;
				if(limits[1] - a[1] > a[2])
					num = a[2];
				else
					num = limits[1] - a[1];
				a[1] += num;
				a[2] -= num;
				solve(a, limits, first);
				a[1] -= num;
				a[2] += num;
			}
			if(a[0] != limits[0])
			{
				int num;
				if(limits[0] - a[0] > a[2])
					num = a[2];
				else
					num = limits[0] - a[0];
				a[0] += num;
				a[2] -= num;
				solve(a, limits, first);
				a[0] -= num;
				a[2] += num;
			}
		}		
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int[] a = new int[3];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		visited = new boolean[a[0] + 1][a[1] + 1];
		solve(new int[]{0,0,a[2]}, a, true);
		ArrayList<Integer> list = new ArrayList<Integer>(set);
		Collections.sort(list);
		for(int i = 0; i < list.size(); i++)
		{
			out.print(list.get(i));
			if(i != list.size() - 1)
				out.print(" ");
		}
		out.println();
		out.close();
	}
}	
