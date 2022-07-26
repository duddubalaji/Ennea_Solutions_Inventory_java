package com.example.stock.Inventory.controller;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.stock.Inventory.domain.Inventory;
import com.example.stock.Inventory.service.InventoryService;



@Slf4j
@RestController
public class Inventorydetails {
 
@Autowired
InventoryService inventoryService;
  /**
   * Handler for getting product status of vehicle.
   *
   * @param vin VIN to search for
   * @param ciCode CICode to search with
   * @return Registration data about the vehicle
   */
  @GetMapping(
      path = "/Getproducts",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
 
  public ResponseEntity<List<Inventory>> getProductStatus(     ) {
    log.info("Retrieving registration status of vehicle {} and dealer {}");
System.out.println(inventoryService.getdetails());
    return  new ResponseEntity<>(inventoryService.getdetails(), HttpStatus.OK); 
  }
  
  @PutMapping("/uploadexcel")	
	@CrossOrigin // TODO REMOVE when we fix the webapp
	public ResponseEntity<List<Inventory>> uploadFile(@RequestParam("file") MultipartFile file) throws SQLException, NumberFormatException, IOException, ParseException {
	  
	  System.out.println("inside the formdata");
	List <Inventory> ls=inventoryService.savedetails(file);
		return new ResponseEntity<>(ls, HttpStatus.OK);
	}
  @GetMapping(
	      path = "/Getqueryproducts",
	      produces = MediaType.APPLICATION_JSON_VALUE)
	  @CrossOrigin
	 
	  public ResponseEntity<List<Inventory>> getProductStatus1(@RequestParam("company")String company ,@RequestParam("supplier")String supplier,@RequestParam("code")String code) {
	    log.info("Retrieving registration status of vehicle {} and dealer {}"+supplier);
	    List<Inventory> ls=new ArrayList<Inventory>();
	    if(code!= "") {
	    		for(Inventory k:inventoryService.getquerydetails(company,supplier) ) {
	    		log.info("printing matching kableus" +k.toString());
	    		if(k.getCode().equalsIgnoreCase(code)) {
	    			
	    			ls.add(k);
	    		}
	    	}
	    }
	    else {
	    	ls=inventoryService.getquerydetails(company,supplier);
	    }
	    return  new ResponseEntity<>(ls, HttpStatus.OK); 
	  }
  @GetMapping(
	      path = "/expiryproducts",
	      produces = MediaType.APPLICATION_JSON_VALUE)
	  @CrossOrigin
	 
	  public ResponseEntity<List<Inventory>> getexpProductStatus(@RequestParam("date")String date) throws ParseException {
	    log.info("Retrieving registration status of vehicle {} and dealer {}");
	    SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
	    Date d=sf.parse(date);
	    return  new ResponseEntity<>(inventoryService.findAllByExpLessThan(d), HttpStatus.OK); 
	  }
	  
}
