package testEshopping.functions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import testEshopping.DBconnection.DBconnector;

public class api {
	public static Connection initializeConnection() throws ClassNotFoundException, SQLException {
		return DBconnector.initializeDatabase();
	}
	
	public static Vector<String> allItems() {
		Vector<String> allItemsOrdered = new Vector<String>();
		try {
	        	Connection con = DBconnector.initializeDatabase();
	        	PreparedStatement st = con.prepareStatement("select * from cart");
	        	ResultSet rs = st.executeQuery();
	        	while(rs.next())
	        	{
	        		allItemsOrdered.add(rs.getString(1));
	        	}
	        }
		catch(Exception e) {
				e.printStackTrace();
		}
		return allItemsOrdered;
	}
	
	public static int bulkOrderedItems() {
//		Vector<Vector<String>> bulkOrders = new Vector<Vector<String>>();
		Vector<String> bulkOrders = new Vector<String>();
		try {
	        	Connection con = initializeConnection();
	        	PreparedStatement st = con.prepareStatement("select * from cart where qty >= ?");
	        	st.setInt(1, 5);
	        	ResultSet rs = st.executeQuery();
	        	while(rs.next())
	        	{
	        		bulkOrders.add(rs.getString(1));
	        	}
	        	return bulkOrders.size();
	        }
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int notBulkOrders() {
		Vector<String> notBulkOrders = new Vector<String>();
		try {
	        	Connection con = initializeConnection();
	        	PreparedStatement st = con.prepareStatement("select * from cart where qty < ?");
	        	st.setInt(1, 5);
	        	ResultSet rs = st.executeQuery();
	        	while(rs.next())
	        	{
	        		notBulkOrders.add(rs.getString(1));
	        	}
	        	return notBulkOrders.size();
	        }
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int total() throws ClassNotFoundException, SQLException {
		int total = 0;
		Connection con = initializeConnection();
		PreparedStatement st;
		Vector<String> allItemsOrdered = allItems();
    	for(String itemName: allItemsOrdered) {
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
    	return total;
	}
	
	public static double offerOnTotal() throws ClassNotFoundException, SQLException{
		int total = total();
		double discountedPrice = 0.0;
    	if(total < 1000) {
    		//10% discount price
    		double discount = (double)10 / 1000;
    		discountedPrice =  ((double)total * discount);
    	}
    	else if(total >= 1000 && total <= 10000) {
    		double discount = (double)20 / 100;
    		discountedPrice =  ((double)total * discount);
    	}
    	else
    	{
    		double discount = (double)10 / 1000;
    		discountedPrice =  ((double)total * discount);
    	}
    	return discountedPrice;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println(bulkOrderedItems());
		System.out.println(total());
		System.out.println(offerOnTotal());
	}
}
