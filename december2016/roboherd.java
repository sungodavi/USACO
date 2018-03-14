import java.util.*;
import java.io.*;

public class roboherd 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		//BufferedReader f = new BufferedReader(new FileReader("roboherd.in"));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("roboherd.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		ArrayList<Integer> list = new ArrayList<Integer>();
		int size = Integer.parseInt(st.nextToken());
		int count = Integer.parseInt(st.nextToken());
		int[][] a = new int[size][];
		for(int r = 0; r < a.length; r++)
			a[r] = load(f.readLine());
		
		for(int i = 0; i < a[0].length; i++)
			for(int j = 0; j < a[1].length; j++)
				for(int k = 0; k < a[2].length; k++)
					list.add(a[0][i] + a[1][j] + a[2][k]);
		Collections.sort(list);
		int sum = 0;
		for(int i = 0; i < count; i++)
			System.out.println(list.get(i));
	}
	
	public static int[] load(String s)
	{
		StringTokenizer st = new StringTokenizer(s);
		int[] a = new int[Integer.parseInt(st.nextToken())];
		for(int i = 0; i < a.length; i++)
			a[i] = Integer.parseInt(st.nextToken());
		return a;
	}
	
}