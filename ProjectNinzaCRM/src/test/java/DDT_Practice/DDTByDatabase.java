package DDT_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DDTByDatabase 
{

	public static void main(String[] args)  throws SQLException
	
	{
		
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_CRM","root","System123@");
		
		Statement stmt = conn.createStatement();
		
		ResultSet result= stmt.executeQuery("select * from ninza_crm_details");
		
		
		while (result.next())
		{
			String browser= result.getString(1);
			String url= result.getString(2);
			String username= result.getString(3);
			String password= result.getString(4);
			
			System.out.print(browser+" ");
			System.out.print(url+" ");
            System.out.print(username+" ");
			System.out.println(password);

			
		}
		
	}

}
