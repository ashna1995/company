package com.casestudy.stockmarket.model;


import java.time.LocalDateTime;

//import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

@Validated
@Entity
public class StockMarket {
	@Id
	private int companyCode;
	@NotBlank(message="CompanyName is Required")
	private String companysName;
	@NotBlank(message="CompanysCEO is Required")
	private String companysCEO;
	@Min(value=100000000,message="Company Turnover must be greater than 10Cr")
	private double companyTurnover;
	@NotBlank(message="CompanyWebsite is Required")
	private String companyWebsite;
	@NotBlank(message="stockExchange is Required")
	private String stockExchange;
	private double stockprice;
	private LocalDateTime time;
	public StockMarket() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StockMarket(int companyCode, String companysName, String companysCEO, double companyTurnover,
			String companyWebsite, String stockExchange, double stockprice, LocalDateTime time) {
		super();
		this.companyCode = companyCode;
		this.companysName = companysName;
		this.companysCEO = companysCEO;
		this.companyTurnover = companyTurnover;
		this.companyWebsite = companyWebsite;
		this.stockExchange = stockExchange;
		this.stockprice = stockprice;
		this.time = time;
	}
	public int getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanysName() {
		return companysName;
	}
	public void setCompanysName(String companysName) {
		this.companysName = companysName;
	}
	public String getCompanysCEO() {
		return companysCEO;
	}
	public void setCompanysCEO(String companysCEO) {
		this.companysCEO = companysCEO;
	}
	public double getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(double companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	public double getStockprice() {
		return stockprice;
	}
	public void setStockprice(double stockprice) {
		this.stockprice = stockprice;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
