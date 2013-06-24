package data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;

public class BookName extends KillConnections {
	
	Connection con;
	
	private static final String GETBOOKNAMES = "SELECT name FROM handbuchname";
	private static final String GETBOOKIDS = "SELECT ID FROM handbuchname";
	
	public BookName(){
		
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
	
	//holt sich Modulhandbuchnamen als Sting-Array aus der Datenbank
	public String[] getBookNames(){
		
		LinkedList<String> tmp = new LinkedList<String>();
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETBOOKNAMES);

			data = psmt.executeQuery();

			while (data.next()) {
				String name = data.getString("name");
				tmp.add(name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		
		String[] list = tmp.toArray(new String[0]);
		return list;
	}
	
//holt sich Modulhanbuchids als LinkedList aus der Datenbank
public LinkedList<Integer> getBookID(){
	
		LinkedList<Integer> arr = new LinkedList<Integer>();
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETBOOKIDS);

			data = psmt.executeQuery();
			
			while(data.next()){
				int id = data.getInt("id");
				if(id%3 == 1){
					arr.add(new Integer(id));
					
				}	
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
