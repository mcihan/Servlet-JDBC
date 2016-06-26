package jdbc;

import data.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletJDBC extends HttpServlet
{  
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet( HttpServletRequest request, 
    		              HttpServletResponse response)
                          throws ServletException, IOException 
	{
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		
		try 
		{
			DB db = new DB(); 
			
            String adi = request.getParameter("adi");
            String soyadi = request.getParameter("soyadi");
            String meslegi = request.getParameter("meslegi");
            String dogumyili = request.getParameter("dy");
            String button = request.getParameter("action"); 
           
            if (button.equals("Kaydet")) 
            {
            	 db.query("INSERT INTO UYELER(adi,soyadi,meslegi,dogumyili) VALUES('"+adi+"','"+soyadi+"','"+meslegi+"',"+dogumyili+")");
                 out.print("<h3>Kayıt Başarıyla Tamamlandı</h3>"); 
            } 
            else if (button.equals("Goster"))
            {
                ResultSet rs = db.getResult("SELECT * FROM UYELER");
                
                out.println("<table border=1 width=50%>");
                out.println("<tr><th width=10%>ID</th>"
                		       + "<th width=25%>ADI</th>"
                              + "<th width=25%>SOYADI</th>"
                              + "<th width=25%>MESLEGI</th>"
                              + "<th width=15%>DOGUMYILI</th> </tr>");

                while (rs.next())
                {
                    String pId = rs.getString("id");
                    String pAdi = rs.getString("adi");
                    String pSoyadi = rs.getString("soyadi");
                    String pMeslegi = rs.getString("meslegi");
                    String pDogumyili = rs.getString("dogumyili");
                    
                    out.println("<tr><td>" + pId + "</td>"
                                  + "<td>" + pAdi + "</td>"
                                  + "<td>" + pSoyadi + "</td>"
                                  + "<td>" + pMeslegi + "</td>"
                                  + "<td>" + pDogumyili + "</td></tr>");
                }  
            }
            
            out.print("<form action='form.html' type='POST'><input type='submit' name='action' value='Geri Don' style='background-color:#4285f4; color:white'/></form><br/><br/>");
        
		} 
		catch (Exception e)
		{ 	
			e.printStackTrace();
			out.print("<h2>İŞLEM BAŞARISIZ!</h2>");
		}     
    }
}

