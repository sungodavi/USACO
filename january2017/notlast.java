/*
ID: sungoda1
LANG: JAVA
TASK: notlast
 */

import java.util.*;
import java.io.*;

public class notlast 
{
	static TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	public static void main(String[] args) throws IOException {
		load();
		BufferedReader f = new BufferedReader(new FileReader("notlast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"notlast.out")));
		int times = Integer.parseInt(f.readLine());
		int[] counts = new int[7];
		Cow[] a = new Cow[7];
		for(int i = 1; i <= times; i++)
		{
			StringTokenizer st = new StringTokenizer(f.readLine());
			int index = map.get(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			counts[index] += amount;
		}
		String[] temp = {"Annabelle", "Bessie", "Daisy", "Elsie", "Gertie", "Henrietta", "Maggie"};
		for(int i = 0; i < 7; i++)
			a[i] = new Cow(temp[i], counts[i]);
		Arrays.sort(a);
		int index = 1;
		while(index < a.length && a[index].amount == a[0].amount)
			index++;
		if(index == a.length || index < a.length - 1 && a[index].amount == a[index + 1].amount)
			out.println("Tie");
		else
			out.println(a[index].name);
		out.close();
	}
	
	static void load()
	{
		map.put("Annabelle", 0);
		map.put("Bessie", 1);
		map.put("Daisy", 2);
		map.put("Elsie", 3);
		map.put("Gertie", 4);
		map.put("Henrietta", 5);
		map.put("Maggie", 6);
		
	}
	
	static class Cow implements Comparable<Cow>
	{
		String name;
		int amount;
		public Cow(String name, int amount)
		{
			this.name = name;
			this.amount = amount;
		}
		
		public int compareTo(Cow c)
		{
			return amount - c.amount;
		}
		
		public String toString()
		{
			return name + " " + amount;
		}
	}
}