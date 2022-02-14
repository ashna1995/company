package com.casestudy.stockmarket.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.stockmarket.model.StockMarket;

@Repository
@Transactional
public interface Stock_Repository extends JpaRepository<StockMarket,Integer>
{

}
