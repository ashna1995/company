package com.casestudy.stockmarket.service;

import java.util.List;

import com.casestudy.stockmarket.exceptions.CompanyCodeAlreadyExistsException;
import com.casestudy.stockmarket.model.StockMarket;



public interface Stock_Service {
	public List<StockMarket>getAllCompanyDetails();
	public StockMarket getSingleCompanyDetails(int companyCode);
	public StockMarket addCompany(StockMarket company)throws CompanyCodeAlreadyExistsException;
    public boolean deleteCompany(int companyCode);
    public boolean updateCompanyDetails(StockMarket companyCode);
    public boolean addStockPrice(int companyCode,StockMarket company);
    public boolean updateStockPrice(int companyCode,StockMarket company);

}
