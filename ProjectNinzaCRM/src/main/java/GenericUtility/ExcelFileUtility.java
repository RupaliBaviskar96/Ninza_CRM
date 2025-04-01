package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility 
{
	public String readinDataFromExcelFile(String sheetname,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\\\Users\\\\Administrator\\\\eclipse-workspace\\\\ProjectNinzaCRM\\\\src\\\\test\\\\resources\\\\TestNinzaCrm.xlsx");
		Workbook wb =WorkbookFactory.create(fis);
		String data =wb.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
		return data;
		
		
	}
}
