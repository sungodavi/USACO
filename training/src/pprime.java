/*
ID: sungoda1
LANG: JAVA
TASK: pprime
*/
import java.util.*;
import java.io.*;


class pprime
{
	public static boolean checkPrime(int num)
	{
		if(num % 2 == 0)
			return false;
		for(int i = 3; i *i <= num; i += 2)
			if(num % i == 0)
				return false;
		return true;
	}
	public static boolean check(int num, int start, int end)
	{
		return start <= num && num <= end && checkPrime(num);
	}
	public static boolean generate(int start, int end, int length, PrintWriter out)
	{
		int[] digits = {1, 3, 7, 9};
		boolean flag = false;
		if(length == 1)
		{
			for(int i = 5; i <= 7; i+=2)
				if(start <= i && i <= end)
				{
					out.println(i);
					flag = true;
				}
		}
		else if(length == 2)
		{
			if(start <= 11 && 11 <= end)
			{
				out.println(11);
				flag = true;
			}
		}
		else if(length == 3)
		{
			for(int d1: digits)
			{
				for(int d2 = 0; d2 < 10; d2++)
				{
					int num = d1 * 101 + d2 * 10;
					if(check(num, start, end))
					{
						out.println(num);
						flag = true;
					}
				}
			}
		}
		else if(length == 4)
		{
			for(int d1: digits)
			{
				for(int d2 = 0; d2 < 10; d2++)
				{
					int num = d1 * 1001 + d2 * 110;
					if(check(num, start, end))
					{
						out.println(num);
						flag = true;
					}
				}
			}
		}
		else if(length == 5)
		{
			for(int d1: digits)
			{
				for(int d2 = 0; d2 < 10; d2++)
				{
					for(int d3 = 0; d3 < 10; d3++)
					{
						int num = d1 * 10001 + d2 * 1010 + 100 * d3;
						if(check(num, start, end))
						{
							out.println(num);
							flag = true;
						}
					}
				}
			}
		}
		else if(length == 6)
		{
			for(int d1: digits)
			{
				for(int d2 = 0; d2 < 10; d2++)
				{
					for(int d3 = 0; d3 < 10; d3++)
					{
						int num = d1 * 100001 + d2 * 10010 + 1100 * d3;
						if(check(num, start, end))
						{
							out.println(num);
							flag = true;
						}
					}
				}
			}
		}
		else if(length == 7)
		{
			for(int d1: digits)
			{
				for(int d2 = 0; d2 < 10; d2++)
				{
					for(int d3 = 0; d3 < 10; d3++)
					{
						for(int d4 = 0; d4 < 10; d4++)
						{
							int num = d1 * 1000001 + d2 * 100010 + 10100 * d3 + 1000 * d4;
							if(check(num, start, end))
							{
								out.println(num);
								flag = true;
							}
						}
					}
				}
			}
		}
		else
		{
			for(int d1: digits)
			{
				for(int d2 = 0; d2 < 10; d2++)
				{
					for(int d3 = 0; d3 < 10; d3++)
					{
						for(int d4 = 0; d4 < 10; d4++)
						{
							int num = d1 * 10000001 + d2 * 1000010 + 100100 * d3 + 11000 * d4;
							if(check(num, start, end))
							{
								out.println(num);
								flag = true;
							}
						}
					}
				}
			}
		}
		return flag;
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		boolean found = false;	
		for(int i = 1; i <= 8; i++)
		{
			generate(start, end, i, out);
		}
		out.close();
	}
}	
