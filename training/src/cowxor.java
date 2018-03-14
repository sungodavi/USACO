/*
ID: sungoda1
LANG: JAVA
TASK: cowxor
*/

import java.util.*;
import java.io.*;

class cowxor 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("cowxor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowxor.out")));
		int size = Integer.parseInt(f.readLine());
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		int mask = 0;
		int result = -1;
		int start = 0;
		int end = 0;
		int curr = 0;
		map.put(0, 0);
		for(int i = 1; i <= size; i++)
		{
			int n = Integer.parseInt(f.readLine());
			curr ^= n;
			int best = curr;
			int s = map.get(0) + 1;
			int target = mask & ~curr;
//			System.out.println("Target: " + target);
//			System.out.println(map.keySet());
			int key = 0;
			while(target > 0)
			{
				int bit = 1 << lg(target);
				key |= bit;
//				System.out.println("Key: " + key);
				Integer k = map.ceilingKey(key);
				if(k == null)
					break;
				if((k & key) != key)
				{
//					System.out.println(Integer.toBinaryString(curr) + " " + Integer.toBinaryString(k) + " " + Integer.toBinaryString(target + key - bit));
					s = map.get(k) + 1;
					best = curr ^ k;
				}
				target ^= bit;
			}
			if(best > result)
			{
				result = best;
				start = s;
				end = i;
			}
//			System.out.println(result + " " + start + " " + end);
			mask |= getMask(curr);
			map.put(curr, i);
		}
		out.printf("%d %d %d\n", result, start, end);
		f.close();
		out.close();
		System.exit(0);
	}
	
	static int getMask(int num)
	{
		return (1 << lg(num) + 1) - 1;
	}
	
	static int lg(int num)
	{
		return 31 - Integer.numberOfLeadingZeros(num);
	}
}