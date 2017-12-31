/*
ID: sungoda1
LANG: JAVA
TASK: censor
*/
import java.util.*;
import java.io.*;

class censor 
{
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("censor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
		String s = f.readLine();
		String key = f.readLine();
		char[] result = new char[s.length()];
		int index = 0;
		outer:
		for(char c : s.toCharArray())
		{
			result[index++] = c;
			if(index >= key.length())
			{
				for(int i = index - key.length(), j = 0; i < index; i++, j++)
					if(result[i] != key.charAt(j))
						continue outer;
				index -= key.length();
			}
		}
		for(int i = 0; i < index; i++)
			out.print(result[i]);
		out.println();
		out.close();
	}
}
