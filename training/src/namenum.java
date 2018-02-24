/*
ID: sungoda1
LANG: JAVA
TASK: namenum
*/
import java.util.*;
import java.io.*;

class namenum 
{
	public static int[] map;
	public static void main(String[] args) throws IOException 
	{
		load();
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader scan = new BufferedReader(new FileReader("dict.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		
		long num = Long.parseLong(f.readLine());
		LinkedList<String> list = new LinkedList<String>();
		
		for(String input = scan.readLine(); input != null; input = scan.readLine())
			if(convert(input) == num)
				list.add(input);
		if(list.isEmpty())
			out.println("NONE");
		else
			for(String s : list)
				out.println(s);
		out.close();
		
	}
	
	public static long convert(String s)
	{
		long result = 0;
		for(int i = 0; i < s.length(); i++)
			result += (long)Math.pow(10, s.length() - i - 1) * map[(int)s.charAt(i)];
		return result;
	}
	
	public static void load()
	{
		map = new int['Z' + 1];
		map['A'] = map['B'] = map['C'] = 2;
		map['D'] = map['E'] = map['F'] = 3;
		map['G'] = map['H'] = map['I'] = 4;
		map['J'] = map['K'] = map['L'] = 5;
		map['M'] = map['N'] = map['O'] = 6;
		map['P'] = map['R'] = map['S'] = 7;
		map['T'] = map['U'] = map['V'] = 8;
		map['W'] = map['X'] = map['Y'] = 9;
	}
}
