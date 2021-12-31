package com.bl.registration.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.bl.registration.model.User;

public class ExcelHelper {
	
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	//Converts Exel to list of users
	
	public static List<User> convertExcelToList(InputStream is){
		List<User> list =  new ArrayList<>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			
			
			User user = new User();
			while(iterator.hasNext()) {
				Row row = iterator.next();
				
				if(rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				
				int cid = 0;
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
					switch(cid) {
					case 0:
						user.setFirstName(cell.getStringCellValue());
						break;
					case 1:
						user.setLastName(cell.getStringCellValue());
						break;
					case 3:
						user.setDob(cell.getDateCellValue());
						break;
					case 2:
						user.setKyc(cell.getStringCellValue());
						break;
					case 4:
						user.setEmail(cell.getStringCellValue());
						break;
					case 5:
						user.setPassword(cell.getStringCellValue());
						break;
					case 6:
						user.setVerify(cell.getBooleanCellValue());
						break;
					}
					cid++;
				}
				list.add(user);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
