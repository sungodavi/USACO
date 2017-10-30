/*
ID: sungoda1
LANG: JAVA
TASK: runround
*/
import java.util.*;
import java.io.*;


class runround
{
	public static int[] toArray(long num)
	{
		int[] a = new int[(int)Math.log10(num) + 1];
		for(int i = a.length - 1; i >= 0; i--)
		{
			a[i] = (int)(num % 10);
			num /= 10;
		}
		return a;
	}
	public static boolean isUnique(int[] a)
	{
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] == 0 || set.contains(a[i]))
				return false;
			set.add(a[i]);
		}
		return true;
	}

	public static boolean check(long num)
	{
		int[] a = toArray(num);
		if(!isUnique(a))
			return false;
		boolean[] visited = new boolean[a.length];
		int unvisited = visited.length;
		int index = 0;
		do
		{
			if(visited[index] || unvisited == 0)
				return false;
			visited[index] = true;
			unvisited--;
			index = (index + a[index]) % a.length;
		}while(index != 0);
		return unvisited == 0;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		long num = Integer.parseInt(f.readLine()) + 1;
		while(!check(num))
			num++;
		out.println(num);
		out.close();
	}
}	
