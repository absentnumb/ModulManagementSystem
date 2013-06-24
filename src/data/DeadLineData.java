package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objects.Deadline;

public class DeadLineData extends KillConnections{
	Connection con;
	private static final String NEWDEADLINE = "Insert into deadlinedata Values(?,?,?)";
	public DeadLineData(){
		
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
     // in Datanbank schreiben
	public void newDeadLine(Deadline deadline){
		PreparedStatement psmt = null;

		try {

			con.setAutoCommit(false);
	
			psmt = con.prepareStatement(NEWDEADLINE);
	
			psmt.setInt(1, deadline.getid());
			psmt.setString(2, deadline.getdatum());
			psmt.setString(3, deadline.getpdf());


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
