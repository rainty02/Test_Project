package Test_627;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Manager {

	private Connection con = null;
	private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr";
	private String pw = "tiger";
	
	Connection getCon() {
		try {
			con = DriverManager.getConnection(jdbcUrl, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return con;	
	}
}