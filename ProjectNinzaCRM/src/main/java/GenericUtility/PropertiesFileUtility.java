package GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility 
{
	public String readingDataFromPropFile(String key) throws IOException,FileNotFoundException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\git\\ninza_CRM_project\\ProjectNinzaCRM\\src\\test\\resources\\NinzaCrm.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String data=prop.getProperty(key);
		return data ;
		
	}
}
