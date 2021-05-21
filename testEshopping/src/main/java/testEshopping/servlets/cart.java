package testEshopping.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testEshopping.DBconnection.DBconnector;

/**
 * Servlet implementation class cart
 */
@WebServlet("/cart")
public class cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

@SuppressWarnings("resource")
	//	@SuppressWarnings("resource")
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("qty"));
		try {
        	Connection con = DBconnector.initializeDatabase();
//        	PreparedStatement st = con.prepareStatement("insert into cart(name, qty) values(?, ?)");
        	PreparedStatement st;
        	st = con.prepareStatement("select * from cart where name = ?");
        	st.setString(1, name);
        	ResultSet rs =  st.executeQuery();
        	if(rs.next()) {
        		st = con.prepareStatement("select qty from cart where name = ?");
        		st.setString(1, name);
        		ResultSet ans = st.executeQuery();
        		ans.next();
        		System.out.println(ans.getString(1));
        		st = con.prepareStatement("update cart set qty = ? + ? where name = ?");
        		st.setString(1, ans.getString(1));
        		st.setInt(2, quantity);
        		st.setString(3, name);
        		st.executeUpdate();
        		System.out.println("cart table successfully updated");
        		try {
	                request.setAttribute("messege", quantity + " quantities of " + name + " added to the cart successfully!");
	                request.getRequestDispatcher("home.jsp").include(request, response);
	            }
	            catch(Exception e){
	            	e.printStackTrace();
	            }
        	}
        	else {
	        	st = con.prepareStatement("insert into cart(name, qty) values(?, ?)");
	        	st.setString(1, name);
	        	st.setInt(2, quantity);
	        	st.executeUpdate();
	        	System.out.println("Successfully inserted!");
	            try {
	                request.setAttribute("messege", quantity + " quantities of " + name + " added to the cart successfully!");
	                request.getRequestDispatcher("home.jsp").include(request, response);
	            }
	            catch(Exception e){
	            	e.printStackTrace();
	            }
        	}
        	st.close();
        	con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
