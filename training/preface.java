/*
ID: sungoda1
LANG: JAVA
TASK: preface
*/
import java.util.*;
import java.io.*;


class preface
{
	public static HashMap<Integer, String[]> map = new HashMap<Integer, String[]>();
	public static HashMap<Character, Integer> indices = new HashMap<Character, Integer>();
	public static void load()
	{
		map.put(4, new String[]{"M"});
		map.put(3, new String[]{"C", "D"});
		map.put(2, new String[]{"X", "L"});
		map.put(1, new String[]{"I", "V"});
		
		indices.put('I', 0);
		indices.put('V', 1);
		indices.put('X', 2);
		indices.put('L', 3);
		indices.put('C', 4);
		indices.put('D', 5);
		indices.put('M', 6);
	}

	public static int digits(int num)
	{
		return (int)Math.log10(num) + 1;
	}

	public static String convert(int num)
	{
		String result = "";
		while(num > 0)
		{
			int length = digits(num);
			int power = (int)Math.pow(10, length - 1);
			int digit = num / power;
			String[] a = map.get(length);
			if(digit == 4)
				result +=  a[0] + a[1];
			else if(digit == 9)
				result += a[0] + map.get(length + 1)[0]; 
			else if(digit >= 5)
			{
				result += a[1];
				for(int i = 6; i <= digit; i++)
					result += a[0];
			}
			else
				for(int i = 1; i <= digit; i++)
					result += a[0];
			num = num % power;
		}
		return result;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		load();
		int[] sum = new int[7];
		int limit = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= limit; i++)
		{
			String s = convert(i);
			for(int j = 0; j < s.length(); j++)
				sum[indices.get(s.charAt(j))]++;
		}
		String[] a = {"I", "V", "X", "L", "C", "D", "M"};	
		for(int i = 0; i < sum.length; i++)
		{
			if(sum[i] == 0)
				break;
			out.println(a[i] + " " + sum[i]);
		}
		out.close();
	}
}	
