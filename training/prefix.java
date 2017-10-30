/*
ID: sungoda1
LANG: JAVA
TASK: prefix
*/
import java.util.*;
import java.io.*;


class prefix
{
	public static boolean check(String s, String prefix, int index)
	{
		int k = 0;
		for(int i = index - prefix.length(); i < index; i++)
			if(s.charAt(i) != prefix.charAt(k++))
				return false;
		return true;
	}

	public static int find(String s, ArrayList<String> list)
	{
		boolean[] a = new boolean[s.length() + 1];
		int max = 0;
		a[0] = true;
		for(int i = 1; i <= s.length(); i++)
		{
			int maxSize = 0;
			for(String prefix: list)
			{
				if(prefix.length() > i)
					continue;
				if(check(s, prefix, i) && a[i- prefix.length()])
				{
					a[i] = true;
					break;
				}
			}
			if(a[i])
				max = i;
		}
		return max;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		ArrayList<String> list = new ArrayList<String>();
		outer:
		while(true)
		{
			while(st.hasMoreTokens())
			{
				String temp = st.nextToken();
				if(temp.equals("."))
					break outer;
				list.add(temp);
			}
			st = new StringTokenizer(f.readLine());
		}
		String s = "";
		String temp = f.readLine();
		while(temp != null)
		{
			s += temp;
			temp = f.readLine();
		}


		out.println(find(s, list));
		out.close();
	}
}	
