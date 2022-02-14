package com.casestudy.stockmarket.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.stockmarket.exceptions.CompanyCodeAlreadyExistsException;
import com.casestudy.stockmarket.model.StockMarket;
import com.casestudy.stockmarket.repository.Stock_Repository;
import java.util.Optional;

@Service
public class StockServiceImpl implements Stock_Service
{
	@Autowired
	private Stock_Repository stockRepo;
	@Override
	public List<StockMarket>getAllCompanyDetails(){
		List<StockMarket> companyList = stockRepo.findAll();
		if(companyList!=null && companyList.size() >0)
		{
			return companyList;
			
		}
		else
			return null;
	}
	@Override
	public StockMarket getSingleCompanyDetails(int companyCode){
	
			return stockRepo.findById(companyCode).orElse(null);
	
				
	
	}
	
	@Override
	public StockMarket addCompany(StockMarket company)throws CompanyCodeAlreadyExistsException {
		Optional<StockMarket> optional = stockRepo.findById(company.getCompanyCode());
		
		if(optional.isPresent())
        {     
			
			throw new CompanyCodeAlreadyExistsException();
		}
		else
		{
			return stockRepo.saveAndFlush(company);
		}
	}
	@Override
	 public boolean deleteCompany(int code) {
		stockRepo.deleteById(code);
		return true;
	}

	@Override
	public boolean updateCompanyDetails(StockMarket stock)
	{
		StockMarket stock1 = stockRepo.getById(stock.getCompanyCode());
		
		if(stock1!=null)
		{
			stock1.setCompanyCode(stock.getCompanyCode());
			stock1.setCompanysCEO(stock.getCompanysCEO());
			stock1.setCompanysName(stock.getCompanysName());
			stock1.setCompanyTurnover(stock.getCompanyTurnover());
			stock1.setCompanyWebsite(stock.getCompanyWebsite());
			stock1.setStockExchange(stock.getStockExchange());
			
			stockRepo.saveAndFlush(stock1);
			
		}
		
		return true;
		
	}
	@Override
	public boolean addStockPrice(int companycode,StockMarket company)
	{
		LocalDateTime time = LocalDateTime.now();
		StockMarket company1 = stockRepo.getById(companycode);
		
		if(company1!=null)
		{
			company1.setStockprice(company.getStockprice());
			company1.setTime(time);
			stockRepo.saveAndFlush(company1);
			
		}
		
		return true;
		
	}
	@Override
	public boolean updateStockPrice(int companycode,StockMarket company)
	{
		LocalDateTime time = LocalDateTime.now();
		StockMarket company1 = stockRepo.getById(companycode);
		
		if(company1!=null)
		{
			company1.setStockprice(company.getStockprice());
			company1.setTime(time);
			stockRepo.saveAndFlush(company1);
			
		}
		
		return true;
		
	}
}
