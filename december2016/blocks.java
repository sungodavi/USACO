/*
ID: sungoda1
LANG: JAVA
TASK: blocks
*/
import java.util.*;
import java.io.*;


class blocks
{
	public static HashMap<Character, Integer> make(String s)
	{
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c: s.toCharArray())
		{
			if(map.containsKey(c))
				map.put(c, map.get(c) + 1);
			else
				map.put(c, 1);
		}
		return map;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("blocks.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));

		int[] letters = new int[26];
		int times = Integer.parseInt(f.readLine());
		for(; times > 0; times--)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			HashMap<Character, Integer> map1 = make(st.nextToken());
			HashMap<Character, Integer> map2 = make(st.nextToken());
			Set<Character> keySet = new TreeSet<Character>(map1.keySet());
			keySet.addAll(map2.keySet());
			for(char c : keySet)
			{
				int count1 = map1.containsKey(c) ? map1.get(c) : 0;
				int count2 = map2.containsKey(c) ? map2.get(c) : 0;
				letters[c - 'a'] += Integer.max(count1, count2);
			}
		}
		for(int num : letters)
			out.println(num);
		out.close();

	}
}	
