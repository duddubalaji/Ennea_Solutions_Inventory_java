package com.example.stock.Inventory.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.Inventory.domain.Inventory;
import com.example.stock.Inventory.repository.InventoryRepository;
@Service
public class InventoryService {
	@Autowired
	InventoryRepository inventoryRepository;
	public List<Inventory> getdetails() {
		
		return inventoryRepository.findAll();
	}
	public List<Inventory> getquerydetails(String company,String supplier){
		return inventoryRepository.findByCompanyOrSupplier(company,supplier);
	}
	public List<Inventory> getquerydetailswithcode(String company,String supplier,String code){
		return inventoryRepository.findByCompanyOrSupplierAndCode(company,supplier,code);
	}
	public List<Inventory> findAllByExpLessThan(Date exp){
		return inventoryRepository.findByExpLessThan(exp);
	}
	
	
	
	public List<Inventory>  savedetails(MultipartFile file) throws IOException, NumberFormatException, ParseException {
		//FileInputStream fis=new FileInputStream(file);  
		Workbook workbook = new XSSFWorkbook( file.getInputStream());

		Sheet sheet = workbook.getSheetAt(0);
		List<Inventory> in=new ArrayList<Inventory>();
		DataFormatter dataFormatter = new DataFormatter();
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			Row row=sheet.getRow(i);
			//System.out.println(row.getCell(0).getStringCellValue());
			String code=dataFormatter.formatCellValue(row.getCell(0));
			 String name=row.getCell(1).getStringCellValue();
			  String batch=null;
			  if(row.getCell(2)==null || row.getCell(2).equals("null")) {
				  batch=null;
			  }
			  else {
				  batch=dataFormatter.formatCellValue(row.getCell(2));
			  }
			  int stock=(int) row.getCell(3).getNumericCellValue();
			  String deal=dataFormatter.formatCellValue(row.getCell(4));
			  String free=dataFormatter.formatCellValue(row.getCell(5));
			  String mrp=dataFormatter.formatCellValue(row.getCell(6));
			  String rate=dataFormatter.formatCellValue(row.getCell(7));
			  String exp= dataFormatter.formatCellValue(row.getCell(8));
			  String company=dataFormatter.formatCellValue(row.getCell(9));
			  String supplier=null;
			  if(row.getCell(10)==null || row.getCell(10).equals("null")) {
				  supplier=null;
			  }
			  else {
				  supplier=dataFormatter.formatCellValue(row.getCell(10));
			  }		 
			  
			  Inventory inv=new Inventory(code,name,batch,(stock),deal,free,mrp,rate,Parsedate(exp),company,supplier);
			 in.add(inv);
			  // List<String> SECONDARY_CI_CODES=Arrays.asList(row.getCell(7).getStringCellValue().split("\\s+"));
			
		}
		inventoryRepository.saveAll(in);
		return in;
		
	}
	public Date Parsedate(String date) {
		Date d=null;
		SimpleDateFormat sf=new SimpleDateFormat("d/M/yy");
		
		try {
			d=(sf.parse(date));
			System.out.println(d);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return d;
		}
		//return d;
	}
	
}
