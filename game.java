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
		int i=0,c=0;
		while(i<n)
		{
			if(a[i+l]==0)
			{
				if((i+l)>(n-1))
				{	
					c=1;
					break;
				}
			}
			else if(a[i+1]==0)
			{
				if(a[i+1+l]==0)
				{
					if((i+1+l)>(n-1))
					{
						c=1;
						break;
					}
				}
			}
			else if(a[i+l]==0 && (i+l)<(n-1)) i=i+l;
			else if((a[i+1]==0 && a[i+l]==1)) i++;
			System.out.println("b");
			if(a[i+1]==1 || a[i+l]==1)
			{
				c=0;
				break;
			}
		}
		System.out.println("a");
		if(c==1) return true;
		else return false;
	}
}