package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import objects.Nachricht;

public class NachrichtData extends KillConnections {
	
	private static Connection con;	
	private static final String LOADBENACHRICHTENLIST = "SELECT id FROM nachrichtendata WHERE modul!=0 && benutzer=?";
	private static final String LOADNACHRICHT = "SELECT * FROM nachrichtendata WHERE id=?";
	private static final String DELETE = "DELETE FROM nachrichtendata where id =?";
	private static final String NEWNACRICHT = "Insert into nachrichtendata Values(?,?,?,?,?)";
	private static final String GETNEWID = "SELECT id FROM nachrichtendata ORDER BY id DESC LIMIT 1";
	private static final String LOADNEWBENACHRICHTENLIST = "SELECT * FROM nachrichtendata WHERE modul=0 && benutzer=?";

	public NachrichtData(){
		
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
	
	//holt sich Nachricht aus der Datenbank
	public Nachricht loadNachricht(int nachrichtid){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADNACHRICHT);
			psmt.setInt(1, nachrichtid);
			
			data = psmt.executeQuery();
			data.next();
			
			int id = data.getInt("id");
			int benutzer = data.getInt("benutzer");
			String beschreibung = data.getString("beschreibung");
			String betreff = data.getString("betreff");
			int modul = data.getInt("modul");
			
			Nachricht tmp = new Nachricht(id, beschreibung, betreff, benutzer, modul);
			return tmp;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnections(data, psmt);
			
		}
	}

	//holt sich Benachrichtigungen als LinkedList aus Nachrichten
	public LinkedList<Nachricht> loadBenachrichtList (int userid){
	
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			LinkedList<Nachricht> tmp = new LinkedList<Nachricht>();
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADBENACHRICHTENLIST);
			psmt.setInt(1, userid);
		
			data = psmt.executeQuery();

			while(data.next()){
			
			

				int tmp1 = data.getInt("id");
			
				tmp.add(loadNachricht(tmp1));
			}
			return tmp;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnections(data, psmt);
		}
	}
	
	public LinkedList<Nachricht> loadNewBenachrichtList (int userid){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			LinkedList<Nachricht> tmp = new LinkedList<Nachricht>();
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADNEWBENACHRICHTENLIST);
			psmt.setInt(1, userid);
		
			data = psmt.executeQuery();

			while(data.next()){
			
			

				int tmp1 = data.getInt("id");
			
				tmp.add(loadNachricht(tmp1));
			}
			return tmp;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeConnections(data, psmt);
		}
	}
	
	//löscht Nachricht
	public void delete(int Id){

		PreparedStatement psmt = null;

		try {

		con.setAutoCommit(false);
	
		psmt = con.prepareStatement(DELETE);
	
		psmt.setInt(1, Id);

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

	//schreibt neue Nachricht in Datenbank
	public void newNachricht(Nachricht nachricht){

		PreparedStatement psmt = null;

		try {

			con.setAutoCommit(false);
	
			psmt = con.prepareStatement(NEWNACRICHT);
	
			psmt.setInt(1, nachricht.getid());
			psmt.setInt(2, nachricht.getbenutzer());
			psmt.setString(3, nachricht.getbeschreibung());
			psmt.setString(4, nachricht.getBetreff());
			psmt.setInt(5, nachricht.getmodule());


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

	//erstellt neue id für Nachricht
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
	
		id = id+1;
		return id;
	}
}
