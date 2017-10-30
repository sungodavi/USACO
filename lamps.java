/*
ID: sungoda1
LANG: JAVA
TASK: lamps
*/
import java.util.*;
import java.io.*;

class lamps
{
	public static void recurse(boolean[] lights, int counter, int index, ArrayList<Integer> on, ArrayList<Integer> off, TreeSet<String> result)
	{
		if(counter != 1)
		{	
			boolean found = true;
			for(int i : on)
				if(!lights[i - 1]) 
				{
					found = false;
					break;
				}
			if(found)
				for(int i : off)
					if(lights[i - 1]) 
					{
						found = false;
						break;
					}
			if(found)
				result.add(convert(lights));
		}

		if(counter > 0)
		{
			if(index < 1)
			{
				b1(lights);
				recurse(lights, counter - 1, 1, on, off, result);
				b1(lights);
			}
			if(index < 2)
			{
				b2(lights);
				recurse(lights, counter - 1, 2, on, off, result);
				b2(lights);
			}
			if(index < 3)
			{
				b3(lights);
				recurse(lights, counter - 1, 3, on, off, result);
				b3(lights);
			}
			if(index < 4)
			{
				b4(lights);
				recurse(lights, counter - 1, 4, on, off, result);
				b4(lights);
			}
		}
	}
	public static void b1(boolean[] a)
	{
		for(int i = 0; i < a.length; i++)
			a[i] = !a[i];
	}
	public static void b2(boolean[] a)
	{
		for(int i = 0; i < a.length; i += 2)
			a[i] = !a[i];
	}
	public static void b3(boolean[] a)
	{
		for(int i = 1; i < a.length; i += 2)
			a[i] = !a[i];
	}
	public static void b4(boolean[] a)
	{
		for(int i = 0; i < a.length; i += 3)
			a[i] = !a[i];
	}
	public static String convert(boolean[] a)
	{
		String s = "";
		for(boolean b: a)
			s += b ? "1" : "0";
		return s;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		boolean[] a = new boolean[Integer.parseInt(st.nextToken())];
		Arrays.fill(a, true);
		st = new StringTokenizer(f.readLine());
		int counter = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		ArrayList<Integer> on = new ArrayList<Integer>();
		while(true)
		{
			int num = Integer.parseInt(st.nextToken());
			if(num == -1)
				break;
			on.add(num);
		}

		st = new StringTokenizer(f.readLine());
		ArrayList<Integer> off = new ArrayList<Integer>();
		while(true)
		{
			int num = Integer.parseInt(st.nextToken());
			if(num == -1)
				break;
			off.add(num);
		}
		TreeSet<String> result = new TreeSet<String>();
		recurse(a, Integer.min(4,counter), 0, on, off, result);
		if(result.isEmpty())
		{
			out.println("IMPOSSIBLE");
		}
		else
		{
			for(String s: result)
				out.println(s);
		}
		out.close();
	}
}	
