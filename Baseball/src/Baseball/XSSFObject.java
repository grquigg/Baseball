package Baseball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFObject {
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	/**
	 * This constructor instantiates a new instance of XSSFObject with a reference to a specific XSSFSheet and the file name. 
	 * @param fileName - the name of the file
	 * @param sheetName - the name of the sheet inside the file to be read to and written from. 
	 * @throws IOException 
	 */
	public XSSFObject(String fileName, XSSFSheet sheetName) throws IOException {
		File fs = new File(fileName);
        if (!fs.exists())
        {
            workbook = new XSSFWorkbook();
        }
        workbook = new XSSFWorkbook("teams.xlsx");
	}
	
	public void writeToFile(int [] args) {
		
	}
	
	public void writeToFile(ArrayList<Object> args) {
		
	}
	
	public void readFromFile(int [] args) {
		
	}
	
	public void readFromFile(ArrayList<Object> args) {
		
	}

}
