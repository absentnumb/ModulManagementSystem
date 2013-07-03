package data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class Archive extends KillConnections {

	private static Connection con;
	private static final String SAVEFILE = "INSERT INTO archiv VALUES(?, ?, ?)";
	
	public Archive(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MMS", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public void saveFile(int local, String path, String time) {
	
		PreparedStatement psmt = null;

		try {

			con.setAutoCommit(false);
			
			psmt = con.prepareStatement(SAVEFILE);
			
			psmt.setInt(1, local);
			psmt.setString(2, path);
			psmt.setString(3,  time);

			psmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(null, psmt);
		}
	}	
}
