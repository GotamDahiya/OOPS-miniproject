//walk i+1 and leap i+n
import java.util.*;
import java.io.*;
import java.lang.*;
public class game
{
	public static void main(String[] args)
	{
		Scanner read=new Scanner(System.in);
		int q=read.nextInt();
		while(q-->0)
		{
			int n=read.nextInt();//size of binary array
			int l=read.nextInt();//leap
			int[] a=new int[n];
			for(int i=0;i<n;i++)
			{
				a[i]=read.nextInt();
			}
			System.out.println((win(l,a,n))? "YES":"NO");
		}
	}
	static boolean win(int l,int[] a,int n)
	{
		int i=0;
		boolean state=false;
		if(a[0]==0)
		{
			while(i<n)
			{
				if(a[i+l]==0 && (i+l)>(n-1))
				{ state=true;
				  break;}
				else if(a[i+l]==0)
					{i=i+l;}
				else if(a[i+1]==0)
					{i++;}
				else if(a[i+1]==0 && (i+1)>(n-1))
					{state=true;
					 break;}
			}
		}
	}
}
