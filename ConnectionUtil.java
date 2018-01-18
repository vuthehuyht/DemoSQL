package DemoJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null ) {
			//System.out.println("Creating driver!");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
			//	System.out.println("Creating connection!");
				String url = "jdbc:mysql://127.0.0.1/sinhvien";
				String user = "root";
				String pass = "";
				
				conn = DriverManager.getConnection(url, user, pass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}

}
