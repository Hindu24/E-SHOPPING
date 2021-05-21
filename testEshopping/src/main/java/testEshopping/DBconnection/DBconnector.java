package testEshopping.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//enter the details of your database heres
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "pass");
		return con;
	}
}
