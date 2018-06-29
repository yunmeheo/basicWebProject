package com.my.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.my.control.Handler;


public class ResultHandler implements Handler{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		//한글로바꿔주고
		String msg = (String)request.getAttribute("msg");
		String mime = "text/plain;sharset=UTF-8";
		response.setContentType(mime);
		
		//메세지를 프린트해주고
		PrintWriter out = response.getWriter();
		out.print(msg);
		
		return null;
	}

}
