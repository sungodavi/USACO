/*
ID: sungoda1
LANG: JAVA
TASK: combo
*/
import java.util.*;
import java.io.*;


class combo
{
	public static int len(int a, int b, int n)
	{
		HashSet<Integer> set1 = makeSet(a, n);
		HashSet<Integer> set2 = makeSet(b, n);
		set1.retainAll(set2);
		return set1.size();
	}
	public static HashSet<Integer> makeSet(int a, int n)
	{
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = -2; i <= 2; i++)
			set.add((a + n + i) % n);
		return set;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int total = -1;
		if(n < 5)
			total = 2 * n * n * n;
		else
			total = 250;	
		int[] lock = new int[3];
		int[] master = new int[3];
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < 3; i++)
			lock[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < 3; i++)
			master[i] = Integer.parseInt(st.nextToken());
		int temp = 1;
		for(int i = 0; i < 3; i++)
			temp *= len(lock[i], master[i], n);
		out.println(total - temp);
		out.close();
	}
}	
