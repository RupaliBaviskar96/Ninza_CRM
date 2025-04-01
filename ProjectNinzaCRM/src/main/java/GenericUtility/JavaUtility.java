package GenericUtility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class JavaUtility 
{
	public int getRandomNum(int limit)
	{
		Random ran=new Random();
		int RandomNum = ran.nextInt(limit);
		return RandomNum;
		
	}
	public String getSystemDate()
	{
		Date dateObj=new Date();
		
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-YYYY");
		
		String todaysDate = sim.format(dateObj);
		
		
		return todaysDate;
		
	}
	public String getRequestDate(int days) 
	{
		Date dateObj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-YYYY");

		String todaysDate = sim.format(dateObj);

		System.out.println(todaysDate);

		Calendar cal = sim.getCalendar();

		cal.add(Calendar.DAY_OF_MONTH, days);

		String closeDate = sim.format(cal.getTime());
		System.out.println(closeDate);
		return closeDate;

		
	}
	
}
