package com.my.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.dao.CustomerDAOOracle;
import com.my.vo.Customer;

public interface Handler{

	
	public String execute(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException;
		
	
	
}  //end interface Handler


