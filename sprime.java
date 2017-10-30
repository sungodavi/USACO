/*
ID: sungoda1
LANG: JAVA
TASK: sprime
*/
import java.util.*;
import java.io.*;


class sprime
{
	public static boolean checkPrime(int num)
	{
		if(num < 2)
			return false;
		if(num == 2)
			return true;
		if(num % 2 == 0)
			return false;
		for(int i = 3; i <= Math.sqrt(num); i+=2)
			if(num % i == 0)
				return false;
		return true;
	}
	public static void findPrimes(int num, int size, PrintWriter out)
	{
		if(size == 0)
		{
			if(checkPrime(num))
				out.println(num);
		}
		if(num > 0 && !checkPrime(num))
			return;
		num *= 10;
		findPrimes(num + 1, size - 1, out);
		findPrimes(num + 3, size - 1, out);
		findPrimes(num + 7, size - 1, out);
		findPrimes(num + 9, size - 1, out);
	}
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int num = Integer.parseInt(st.nextToken()) - 1;
		findPrimes(2, num, out);		
		findPrimes(3, num, out);		
		findPrimes(5, num, out);		
		findPrimes(7, num, out);		
		out.close();
	}
}	
