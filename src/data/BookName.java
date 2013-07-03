package data;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import objects.Modul;

import com.mysql.jdbc.Connection;

public class BookName extends KillConnections {
	
	private static Connection con;
	
	private static final String GETBOOKNAMES = "SELECT name FROM handbuchname";
	private static final String GETBOOKIDS = "SELECT ID FROM handbuchname";
	private static final String GETBOOKNAMESD = "SELECT name FROM handbuchname WHERE dekan=?";
	private static final String GETBOOKIDSD = "SELECT ID FROM handbuchname WHERE dekan=?";
	private static final String NEWHAND ="INSERT INTO handbookname VALUES(?,?,?)";
	private static final String GETNEWID = "SELECT id FROM handbookname ORDER BY id DESC LIMIT 1";

	
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
	public String[] getBookNames(int user){
		
		LinkedList<String> tmp = new LinkedList<String>();
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			if(user ==0){
			psmt = con.prepareStatement(GETBOOKNAMES);
			}
			else{
			psmt = con.prepareStatement(GETBOOKNAMESD);
			psmt.setInt(1,user);
			}

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
public LinkedList<Integer> getBookID(int user){
	
		LinkedList<Integer> arr = new LinkedList<Integer>();
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);
			if(user ==0){
			psmt = con.prepareStatement(GETBOOKIDS);
			}
			else{
			psmt = con.prepareStatement(GETBOOKIDSD);
			psmt.setInt(1,user);
			}
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

public void newHandbook(String name, int user){
	

		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(NEWHAND);
			
			psmt.setInt(1, getNewId());
			psmt.setString(2, name);
			psmt.setInt(3, user);
	

			psmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		
	
	
	
}

public int getNewId() {
	
	int id = 0;
	
	PreparedStatement psmt = null;
	ResultSet data = null;

	try {

		con.setAutoCommit(false);

		psmt = con.prepareStatement(GETNEWID);

		data = psmt.executeQuery();

		data.next();
		id = data.getInt("id");

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeConnections(data, psmt);
	}
	
	id = id+3;
	System.out.println(id);
	return id;
}
}
