package com.my.control;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String realPath;
	
    public LifeCycleServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		//web.xml에서 내용을 읽어오기때문에 꼭 필요하다. ★★super.init(config);
		//미리만들어져있는 서블릿객체와 연결해주는 작업도 하기때문에 
		//this.getServletContext(); 작업도 가능해지는거다.
		
		super.init(config);
		
        //xml파일에서 file이름을 가지고오면 유지보수성이 좋아진다.
		fileName = this.getInitParameter("fileName");
		
		//서블릿 컨텍스트 경로를 가지고 있다.
		ServletContext sc = this.getServletContext();
		
		//ServletContext에 있는 realpath를통해 path값을 가지고올 수 있다.
		realPath = sc.getRealPath(fileName);
		System.out.println(realPath);
		
		//개발자 이름도 가져와보자
		System.out.println(sc.getInitParameter("devName"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FileReader fr = new FileReader(realPath);
		int readValue = -1;
		while((readValue = fr.read()) != -1){
			System.out.print((char) readValue);
		}fr.close();
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
	public void destroy() {
		
	}
}
