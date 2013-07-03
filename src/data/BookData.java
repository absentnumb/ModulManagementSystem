package data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;

public class BookData extends KillConnections {
	private static Connection con;
	private static final String GETFACHLIST = "SELECT Fid FROM handbuchdata where id=?";

	public BookData(){
		
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

	//gibt LinkedList der Fächer zurück
	public LinkedList<Integer> listeFaecher(int fach ){
		
		LinkedList<Integer> arr = new LinkedList<Integer>();
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETFACHLIST);
			psmt.setInt(1, fach );
			data = psmt.executeQuery();
			
			int i=0;
			while(data.next()){
				int id = data.getInt("Fid");
				
					arr.add(new Integer(id));
					i++;
					
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		
		return arr;
	}
}
