package testEshopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testEshopping.DBconnection.DBconnector;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        boolean isLoggedIn = false;
        try {
        	Connection con = DBconnector.initializeDatabase();
        	PreparedStatement st = con.prepareStatement("select * from users where name = ? and password = ?");
        	st.setString(1, username);
        	st.setString(2, password);
        	ResultSet rs = st.executeQuery();
        	
			if(rs.next())
        	{  
				isLoggedIn = true;
				System.out.println("username : " + rs.getString(2));
    			System.out.println("password : " + rs.getString(3));
        		System.out.println("entered if condition");
        		System.out.println(isLoggedIn);
        		request.setAttribute("loggedIn", isLoggedIn);
                RequestDispatcher rd=request.getRequestDispatcher("home");  
                rd.forward(request, response);  
            }
        	else{  
        		isLoggedIn = false;
        		System.out.println("entered else condition");
                request.setAttribute("error", "Incorrect Credentials!!");  
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");  
                rd.include(request, response);  
            }
	        	rs.close();
        	}
        catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
