package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility 
{
	public String readingDataFromPropFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\ProjectNinzaCRM\\src\\test\\resources\\NinzaCrm.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String data=prop.getProperty(key);
		return data ;
		
	}
}
