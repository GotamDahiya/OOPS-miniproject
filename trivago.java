//entire backend
//comments beside classes names defines what they do
import java.sql.*;
import java.util.Date;
import java.util.*;
public class trivago
{
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
class Register/*for new register*/
{
	
	String name,email,address,username,password;
	Date date;
	Register(String name,Date date,String email,String address,String username,String password)
	{
		this.name=name;
		this.date=date;
		this.email=email;
		this.address=address;
		this.username=username;
		this.password=password;
	}
	boolean register()
	{
		int c=0;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trivago?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str2="SELECT Username FROM User WHERE Username='"+username+"'";
			ResultSet result=query.executeQuery(str2);
			if(!result.next())
			{
			String str="INSERT INTO User(Name,DateOfBirth,Email,Address,Username,Password) VALUES ('"+name+"','"+date+"','"+email+"','"+address+"','"+username+"','"+password+"')";
			query.executeUpdate(str);
			c=1;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(c==1)
		return true;
			else return false;
	}
}
class Login/*for logining*/
{
	String username,password;
	int c=0;
	Login(String username,String password)
	{
		this.username=username;
		this.password=password;
	}
	boolean login()
	{
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trivago?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str="SELECT Username,Password FROM User WHERE Username='"+username+"'";
			ResultSet result=query.executeQuery(str);
			while(result.next())
			{
				String a=result.getString("Username");
				String b=result.getString("Password");
				int u=a.compareTo(username);
				int p=b.compareTo(password);
				if((u==0) && (p==0))
				{
					c=1;
					break;
				}
				else c=0;
			}
			// if(c==1)
			// return true;
			// else return false;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	if(c==1)
		return true;
	else return false;
	}
}
//number of people should not exceed thrice the number of rooms
class Travel/*travelling details*/
{
	String location;
	Date checkin,checkout;
	int nrooms,npeople;
	Travel(String location,Date checkin,Date checkout,int nrooms,int npeople)
	{
		this.location=location;
		this.checkin=checkin;
		this.checkout=checkout;
		this.nrooms=nrooms;
		this.npeople=npeople;
	}
	boolean travel()//city selection
	{
		if((npeople)<(3*nrooms))
		{
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trivago?autoReconnect=true&useSSL=false","root","Orion@1234");
				Statement query=con.createStatement();
				String str="INSERT INTO Travel VALUES ('"+location+"','"+checkin+"','"+checkout+"','"+nrooms+"','"+npeople+"')";
				query.executeUpdate(str);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return true;
		}
		else return false;
	}
}
class Booking//view hotels
{
	String hotel,location,username;
	int nrooms;
	Booking(String hotel,String location,String username,int nrooms)
	{
		this.hotel=hotel;
		this.location=location;
		this.username=username;
		this.nrooms=nrooms;
	}
	boolean booking()//view hotels
	{
		int k=0,z=1;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trivago?autoReconnect=true&useSSL=false","root","Orion@1234");
			Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotels?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			Statement query1=con1.createStatement();
			String str4="SELECT Number FROM RoomA WHERE Hotel='"+hotel+"' AND Location='"+location+"'";
			ResultSet result2=query1.executeQuery(str4);
			while(result2.next())
			{
				k=result2.getInt("Number");
			}
			if(k>nrooms)
			{
				String str="SELECT Name,Email FROM User WHERE Username='"+username+"'";
				ResultSet result=query.executeQuery(str);
				String a="",b="";
				while(result.next())
				{
					a=result.getString("Name");
					b=result.getString("Email");
				}
				int c=(int)Math.random()*10;
				String str1="INSERT INTO booking VALUES ('"+a+"','"+hotel+"','"+location+"','"+b+"','"+c+"','"+nrooms+"')";
				query.executeUpdate(str1);
				
				String str2="SELECT Number FROM RoomA WHERE Hotel='"+hotel+"' AND Location='"+location+"'";
				ResultSet result1=query1.executeQuery(str2);
				int u=0;
				while(result1.next())
				{
					u=result1.getInt("Number");
				}
				u=u-nrooms;
				String str3="UPDATE RoomA SET Number='"+c+"' WHERE Location='"+location+"' AND Hotel='"+hotel+"'";
				query1.executeUpdate(str3);
			}
		else z=0;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(z == 1)
			{
				return true;
			}
			else return false;
	}
}
class Waiting//waiting list
{
	String username,hotel,location;
	Waiting(String username,String hotel,String location)
	{
		this.username=username;
		this.hotel=hotel;
		this.location=location;
	}
	String waiting()
	{
		int nwaitingi=0;
		String nwaiting;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trivago?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str="SELECT Name,Email,MobileNumber FROM User WHERE Username='"+username+"'";
			String str2="SELECT COUNT(*) as total FROM Waiting";
			ResultSet result=query.executeQuery(str);
			ResultSet result1=query.executeQuery(str2);
			String a="",b="";
			int c=0;
			while(result.next());
			{
				a=result.getString("Name");
				b=result.getString("Email");
				c=result.getInt("MobileNumber");
			}
			if(result1.next())
			{
				nwaitingi=result1.getInt("total");
			}
			String str3="INSERT INTO Waiting VALUES ('"+a+"','"+location+"','"+hotel+"','"+b+"','"+c+"')";
			query.executeUpdate(str3);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		nwaiting=String.valueOf(nwaitingi);
		return nwaiting;
	}
}
class Hotel/*includes hotels,room availability,facilities*/
{
	/*String[] hotel()
	{
		String details[]=new String[100];
		String hotel="",location="";
		int i=0;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotels?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str="SELECT * FROM Hotel";
			ResultSet result=query.executeQuery(str);
			while(result.next())
			{
				hotel=result.getString("Hotel");
				location=result.getString("Location");
				details[i]=hotel;
				details[i+1]=location;
				i++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return details;
	}*/
	String[] room()//room details new bookings->travel->s
	{
		String rdetails[]=new String[100];
		String price="",hotel="";
		int i=0;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotels?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str="SELECT Hotel,Location,Price FROM RoomA";
			ResultSet result=query.executeQuery(str);
			while(result.next())
			{
				hotel=result.getString("Hotel");
				price=result.getString("Price");
				rdetails[i]=hotel;
				rdetails[i+1]=price;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return rdetails;
	}
	// boolean[] cfacilities(String hotel,String location)
	// {
	// 	boolean comp[]=new boolean[100];
	// 	boolean wifi,tv,hwater,kettle,satchets;
	// 	int i=0;
	// 	try
	// 	{
	// 		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Facilities?autoReconnect=true&useSSL=false","root","Orion@1234");
	// 		Statement query=con.createStatement();
	// 		String str="SELECT * FROM Comp WHERE Hotel='"+hotel+"' AND Location='"+location+"'";
	// 		ResultSet result=query.executeQuery(str);
	// 		while(result.next());
	// 		{
	// 			wifi=result.getBoolean("Wifi");
	// 			tv=result.getBoolean("TV");
	// 			hwater=result.getBoolean("HWater");
	// 			kettle=result.getBoolean("Kettle");
	// 			satchets=result.getBoolean("Satchets");
	// 			comp[i]=wifi;
	// 			comp[i+1]=tv;
	// 			comp[i+2]=hwater;
	// 			comp[i+3]=kettle;
	// 			comp[i+4]=satchets;
	// 			i++;
	// 		}
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e);
	// 	}
	// 	return comp;
	// }
	// boolean[] pfacilities(String hotel,String location)
	// {
	// 	boolean paid[]=new boolean[100];
	// 	boolean meals,laundry,crental,swimming,gym,jaccuzi;
	// 	int i=0;
	// 	try
	// 	{
	// 		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Facilities?autoReconnect=true&useSSL=false","root","Orion@1234");
	// 		Statement query=con.createStatement();
	// 		String str="SELECT * FROM Paid WHERE Hotel='"+hotel+"' AND Location='"+location+"'";
	// 		ResultSet result=query.executeQuery(str);
	// 		while(result.next())
	// 		{
	// 			meals=result.getBoolean("3Meals");
	// 			laundry=result.getBoolean("Laundry");
	// 			crental=result.getBoolean("CRental");
	// 			swimming=result.getBoolean("Swimming");
	// 			gym=result.getBoolean("Gym");
	// 			jaccuzi=result.getBoolean("Jaccuzi");
	// 			paid[i]=meals;
	// 			paid[i+1]=laundry;
	// 			paid[i+2]=crental;
	// 			paid[i+3]=swimming;
	// 			paid[i+4]=gym;
	// 			paid[i+5]=jaccuzi;
	// 			i++;
	// 		}
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		System.out.println(e);
	// 	}
	// 	return paid;
	// }
	boolean[] finalf(String hotel,String location)
	{
		boolean fac[]=new boolean[100];
		boolean ac,wifi,parking,tv,card,elevator,dining,netflix,swimming;
		int i=0;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Facilities?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str="SELECT * FROM final WHERE Hotel='"+hotel+"' AND Location='"+location+"'";
			ResultSet result=query.executeQuery(str);
			while(result.next())
			{
				ac=result.getBoolean("AC");
				wifi=result.getBoolean("Wifi");
				parking=result.getBoolean("Parking");
				tv=result.getBoolean("TV");
				elevator=result.getBoolean("Elevator");
				dining=result.getBoolean("Dining");
				netflix=result.getBoolean("Netflix");
				swimming=result.getBoolean("Swimming");
				fac[i]=ac;
				fac[i+1]=wifi;
				fac[i+2]=parking;
				fac[i+3]=tv;
				fac[i+4]=elevator;
				fac[i+5]=dining;
				fac[i+6]=netflix;
				fac[i+7]=swimming;
				i++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return fac;
	}
	String[] ratings(String hotel,String location)
	{
		int nfeedbacki=0,ratingi=0;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotels?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str="SELECT AVG(Ratings) as ratings FROM Ratings WHERE Hotel='"+hotel+"' AND Location='"+location+"'";
			String str1="SELECT COUNT(Feedback) as total FROM Ratings WHERE Hotel='"+hotel+"' AND Location='"+location+"'";
			ResultSet result=query.executeQuery(str);
			ResultSet result1=query.executeQuery(str1);
			while(result.next())
			{
				ratingi=result.getInt("ratings");				
			}
			if(result1.next())
			{
				nfeedbacki=result1.getInt("total");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		String rating=String.valueOf(ratingi);
		String nfeedback=String.valueOf(nfeedbacki);
		String feedback[]=new String[2];
		feedback[0]=rating;feedback[1]=nfeedback;
		return feedback;
	}
}

class CancelBooking//cancellation 
{
	String username;
	int brnumber;//booking reference number
	CancelBooking(String username,int brnumber)
	{
		this.username=username;
		this.brnumber=brnumber;
	}
	boolean cancel()
	{
		int x=0;
		try
		{
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trivago?autoReconnect=true&useSSL=false","root","Orion@1234");
			Statement query=con.createStatement();
			String str="SELECT * FROM booking WHERE ReferenceNo='"+brnumber+"'";
			ResultSet result =query.executeQuery(str);
			if(result.next())
			{
				String str1="DELETE FROM booking WHERE ReferenceNo='"+brnumber+"'";
				query.executeUpdate(str1);
				x=1;
			}
			else x=0;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(x == 1)
			return true;
		else return false;
	}
}
//to verify pan and aadhar number
class Verify
{
	int aadhar;
	String pan;
	Verify(int aadhar,String pan)
	{
		this.aadhar=aadhar;
		this.pan=pan;
	}
	boolean verify()
	{
		int lp=pan.length();
		int la=(int)(Math.log10(aadhar)+1);
		if((lp == 0) &&(la == 0))
			return false;
		else 
		{
			if((lp == 10) || (la == 12))
			return true;
			else return false;
		}
	}
}