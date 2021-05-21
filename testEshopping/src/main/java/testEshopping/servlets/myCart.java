package testEshopping.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testEshopping.DBconnection.DBconnector;

/**
 * Servlet implementation class myCart
 */
//@WebServlet("/myCart")
public class myCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Vector<Vector<String>> bulkOrders = new Vector<Vector<String>>();
		Vector<Vector<String>> NotbulkOrders = new Vector<Vector<String>>();
		Vector<String> allItems = new Vector<String>();
		try {
        	Connection con = DBconnector.initializeDatabase();
        	
        	//finding bulk orders
        	PreparedStatement st = con.prepareStatement("select * from cart where qty >= ?");
        	st.setInt(1, 5);
        	ResultSet rs = st.executeQuery();
        	while(rs.next()) {
        		Vector<String> v = new Vector<String>();
        		v.add(rs.getString(1));
        		allItems.add(rs.getString(1));
//        		System.out.println(rs.getString(1));
        		v.add(String.valueOf(rs.getInt(2)));
//        		System.out.println(rs.getString(1));
        		bulkOrders.add(v);
        	}
        	
        	//finding not bulk orders
        	st = con.prepareStatement("select * from cart where qty < ?");
        	st.setInt(1, 5);
        	ResultSet rss = st.executeQuery();
        	while(rss.next()) {
        		Vector<String> v = new Vector<String>();
        		v.add(rss.getString(1));
        		allItems.add(rss.getString(1));
        		System.out.println(rss.getString(1));
        		v.add(String.valueOf(rss.getInt(2)));
//        		System.out.println(rs.getString(1));
        		NotbulkOrders.add(v);
        	}
        	
        	//calculating total
        	int total = 0;
        	for(String itemName: allItems) {
        		st = con.prepareStatement("select price from items where name = ? ");
        		st.setString(1, itemName);
        		ResultSet set1 = st.executeQuery();
        		set1.next();
        		int price = set1.getInt(1);
        		st = con.prepareStatement("select qty * ? from cart where name = ?");
        		st.setInt(1, price);
        		st.setString(2, itemName);
        		ResultSet set2 = st.executeQuery();
        		set2.next();
        		total += (set2.getInt(1));
        	}
        	
        	System.out.println(total);
        	double discountedPrice = 0.0;
        	if(total < 1000) {
        		//10% discount price
        		System.out.println("10% discount price");
        		double discount = (double)10 / 1000;
        		System.out.println("discount->" + discount);
        		discountedPrice =  ((double)total * discount);
        		System.out.println(discountedPrice);
        	}
        	else if(total >= 1000 && total <= 10000) {
        		System.out.println("20% discount price");
        		double discount = (double)20 / 100;
        		System.out.println("discount->" + discount);
        		discountedPrice =  ((double)total * discount);
        		System.out.println(discountedPrice);
        	}
        	else
        	{
        		System.out.println("30% discount price");
        		double discount = (double)10 / 1000;
        		System.out.println("discount->" + discount);
        		discountedPrice =  ((double)total * discount);
        		System.out.println(discountedPrice);
        	}
        	try {
//        		System.out.println(bulkOrders.get(0) + " " + bulkOrders.get(1));        		
        		request.setAttribute("bulkOrders", bulkOrders);
        		request.setAttribute("NotbulkOrders", NotbulkOrders);
        		request.setAttribute("total", total);
        		request.setAttribute("discountedPrice", discountedPrice);
        		request.getRequestDispatcher("payment.jsp").include(request, response);
        	}
        	catch(Exception e) {
    			e.printStackTrace();
    		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
