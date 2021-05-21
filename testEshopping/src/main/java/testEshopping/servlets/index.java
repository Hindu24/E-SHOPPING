package testEshopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testEshopping.DBconnection.DBconnector;

/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	request.getRequestDispatcher("index.jsp").include(request, response);
        }
        finally {
        	out.close();
        }
	}
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String useremail = request.getParameter("email");
        String password = request.getParameter("password");
        //do the JDBC thing here
        try {
        	Connection con = DBconnector.initializeDatabase();
        	PreparedStatement st = con.prepareStatement("insert into users(name, email, password) values(?, ?, ?)");
        	st.setString(1, username);
        	st.setString(2, useremail);
        	st.setString(3, password);
        	st.executeUpdate();
        	st.close();
        	con.close();
        	System.out.println("Successfully inserted!");
        	PrintWriter out = response.getWriter();
            try {
//                out.println("details : "+ username + " " + useremail + " " + password);
                request.setAttribute("messege", "You successfully registered!!");
                request.setAttribute("welcome", "login using the account you created!! Happy shopping :)");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            finally {
            	out.close();
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
	}

}
