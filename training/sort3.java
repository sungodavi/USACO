/*
ID: sungoda1
LANG: JAVA
TASK: sort3
*/
import java.util.*;
import java.io.*;


class sort3
{
	public static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

		int size = Integer.parseInt(f.readLine());
		int[] master = new int[size];
		int[] a = new int[size];
		int swaps = 0;
		int[] count = new int[4];
		int index = 0;

		for(int i = 0; i < a.length; i++)
		{
			int num = Integer.parseInt(f.readLine());
			count[num]++;
			a[i] = num;
		}

		for(int i = 1; i <= 3; i++)
			for(int j = 0; j < count[i]; j++)
				master[index++] = i;

		boolean change = true;
		while(change)
		{
			change = false;
			for(int i = 0; i < a.length; i++)
			{
				if(a[i] != master[i])
				{
					boolean found = false;
					for(int j = i + 1; j < a.length; j++)
					{
						if(a[j] == master[i] && a[i] == master[j])
						{
							swaps++;
							swap(a, i, j);
							change = true;
							found = true;
							break;
						}
					}
					if(!found)
					{
						for(int j = i + 1; j < a.length; j++)
						{
							if(a[j] == master[i] && a[j] != master[j])
							{
								swaps++;
								swap(a, i, j);
								change = true;
								found = true;
								break;
							}
						}
					}
				}
			}
		}
		out.println(swaps);
		out.close();
	}
}	
