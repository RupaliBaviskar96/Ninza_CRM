package GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
import java.sql.Statement;

public class DatabaseUtility 
{
	Connection conn;
	public void getDBConnection(String uname, String url,String password) throws SQLException
	{
		try
		{
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url,uname,password);
		
		}
		catch(Exception e)
		{
			System.out.println();
			
		}
		
		
	}
	public void closeConnection() throws SQLException
	{
		conn.close();
	}
	
	
	
	
	public ResultSet excuteQuery(String query)
	{
		ResultSet result=null;
		try
		{
		Statement stmt=conn.createStatement();
			result=stmt.executeQuery(query);
			
		}
		catch(Exception e)
		{
			
		}
		return result;
	}
	public int excecuteNonSelectQuery(String query)
	{
		int result = 0;
		try
		{
			Statement stmt=conn.createStatement();
			result =stmt.executeUpdate(query);
		}
		catch(Exception e)
		{
			
		}
		
		return result ;
		
		
	}

}
