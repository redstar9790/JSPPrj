import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.function.IntPredicate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nana")
public class Nana extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		
//		OutputStream os = response.getOutputStream();

//		PrintWriter out = new PrintWriter(os, true);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

//		http://www.newlecture.com/hello?cnt=1
			
		String _cnt = request.getParameter("cnt");
		
		int cnt = 100;
		
		if(_cnt != null)
			cnt = Integer.parseInt(_cnt);
		
		PrintWriter out = response.getWriter();
		for(int i=0; i<cnt; i++)
		{	
			out.println((i+1) + ": ¾È³ç Servlet!!<br />");
		}
	}
}
