package com.casestudy.stockmarket.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.stockmarket.exceptions.CompanyCodeAlreadyExistsException;
import com.casestudy.stockmarket.model.StockMarket;
import com.casestudy.stockmarket.response.ResponseHandler;
import com.casestudy.stockmarket.service.Stock_Service;

@RestController
@RequestMapping("/api/v1/market")
public class StockMarketController {
	@Autowired
	private Stock_Service stockService;
	@GetMapping("/company/getall")
	public ResponseEntity<?> getAllBooks()
	{
	   List<StockMarket> companyList = stockService.getAllCompanyDetails();
	   if(companyList!=null)
	   {
		   return new ResponseEntity<List<StockMarket>>(companyList, HttpStatus.OK);
	   }
	   
	   return new ResponseEntity<String>("Company List is empty!", HttpStatus.NO_CONTENT);
		   
		   
	}
	 @GetMapping("/company/info/{cid}")
	 public ResponseEntity<?> addSingleCompany(@PathVariable("cid") int cid)
	 {
	 StockMarket companyList = stockService.getSingleCompanyDetails(cid);
	  if(companyList!=null)
	     
	    {
		// return new ResponseEntity<StockMarket>(companyList, HttpStatus.OK);
	    //}
	  // return new ResponseEntity<String>("Cannot find the company id to display the details ", HttpStatus.INTERNAL_SERVER_ERROR);
	

	   /*The Cache control in API call*/
	  CacheControl cacheControl = CacheControl.maxAge(5, TimeUnit.MINUTES);
	   return ResponseEntity.ok()
			   .cacheControl(cacheControl)
			   .body(ResponseHandler.generateResponse("Succesfully retrieved the data", HttpStatus.OK,companyList));
 }
 
        return new ResponseEntity<String>("Cannot find the company id to display the details", HttpStatus.NO_CONTENT);
         }  
	 
	 
	 
	
	@PostMapping(value="/company/register", consumes = "application/json; charset=utf-8")
	public ResponseEntity<?> addCompany(@Valid @RequestBody StockMarket company)throws CompanyCodeAlreadyExistsException
	{
		if(stockService.addCompany(company)!=null)
		{
			return new ResponseEntity<StockMarket>(company, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Sorry data is not inserted!", HttpStatus.CONFLICT);
	}
	@DeleteMapping("/company/delete/{cid}")
	public ResponseEntity<?> deleteCompany(@PathVariable("cid") int cid)
	{
		if(stockService.deleteCompany(cid))
		{
			return new ResponseEntity<String>("Record Deleted!", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("Cannot delete due to internal error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/updatecompany")
	public ResponseEntity<?> updatecompany(@RequestBody StockMarket company)
	{
		if(stockService.updateCompanyDetails(company))
	{
			return new ResponseEntity<>(company,HttpStatus.CREATED);
			
	}
		return new ResponseEntity<String>("Update unsuccessfull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@PostMapping("/stock/add/{cid}")
	
	public ResponseEntity<?> addStockPrice(@PathVariable("cid") int cid,  @RequestBody StockMarket company )
	{
		if(stockService.addStockPrice(cid,company))
	{
			return new ResponseEntity<>(company,HttpStatus.CREATED);
			
	}
		return new ResponseEntity<String>("Stockprice adding unsuccessfull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
@PutMapping("/stock/put/{cid}")
	
	public ResponseEntity<?> updateStockPrice(@PathVariable("cid") int cid,  @RequestBody StockMarket company )
	{
		if(stockService.addStockPrice(cid,company))
	{
			return new ResponseEntity<>(company,HttpStatus.CREATED);
			
	}
		return new ResponseEntity<String>("Stockprice adding unsuccessfull",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
