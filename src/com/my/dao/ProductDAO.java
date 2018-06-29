package com.my.dao;
import java.util.List;
import com.my.vo.Customer;
import com.my.vo.Product;

public interface ProductDAO {
	
	
	public void insert(Product p) throws Exception;
	
	
	public List<Product> selectAll() throws Exception;
	
	
	public Product selectByNo(String no) throws Exception;
	
	
	public List<Product> selectByName(String word) throws Exception;
	/*실제상품명이 a1, a2, b1, ba가 있을때
	selecByName("a"); 로 했을 때 a를 포함하고 있는 모든상품을 검색할 것*/
	

}
