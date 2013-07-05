package data;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import objects.Modul;
import objects.ModulKu;

import com.mysql.jdbc.*;

public class ModulDatabase extends KillConnections {
	
	Connection con;
	
	private static final String LOADMODULE = "SELECT * FROM moduldata WHERE id=?";
	private static final String LOADMODULELIST = "SELECT id FROM moduldata WHERE dozid=?";
	private static final String GETNEWID = "SELECT id FROM moduldata ORDER BY id DESC LIMIT 1";
	private static final String SAVEMODULE = "INSERT INTO moduldata " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?," +
			" ?, ?, ?, ?)";
	private static final String GETSPERR = "SELECT sperr FROM moduldata WHERE id=?";
	private static final String SETSPERR = "UPDATE moduldata SET sperr=? WHERE id=?";
	private static final String DELETEMODULE = "DELETE FROM moduldata WHERE id=?";

	public ModulDatabase(){
		
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
	
	//holt sich alle Attribute eines Moduls
	public Modul loadModule (int moduleid){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADMODULE);
			psmt.setInt(1, moduleid);
			
			data = psmt.executeQuery();
			data.next();
			
			int id = data.getInt("id");
			String title = data.getString("title");
			int lp = data.getInt("lp");
			String language = data.getString("language");
			String turn = data.getString("turn");
			int responsibleid = data.getInt("responsibleid");
			String responsible = data.getString("responsible");
			int dozid = data.getInt("dozid");
			String doz = data.getString("doz");
			String filing = data.getString("filing");
			String requirements = data.getString("requirements");
			String aims = data.getString("aims");
			String content = data.getString("content");
			String lit = data.getString("lit");
			String events = data.getString("events");
			String work = data.getString("work");
			String exams = data.getString("exams");
			String formcond = data.getString("formcond");
			String grades = data.getString("grades");
			
			Modul tmp = new Modul(id, title, lp, language, turn, responsibleid,
					  responsible, dozid, doz, filing, requirements, aims,
					  content, lit, events, work, exams, formcond, grades);
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
	
	//schreibt Kurzbeschreibung des Moduls (Titel und ID) in LinkedList
	public LinkedList<ModulKu> loadModuleList (int userid){
		

		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			LinkedList<ModulKu> tmp = new LinkedList<ModulKu>();
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADMODULELIST);
			psmt.setInt(1, userid);
			
			data = psmt.executeQuery();
			

			while(data.next()){
				
				
				int tmp1 = data.getInt("id");
				tmp.add(loadModuleKu(tmp1));
				System.out.println(tmp1);
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
	
	//holt sich Kurzbeschreibung aus der Datenbank
	public ModulKu loadModuleKu(int moduleid){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADMODULE);
			psmt.setInt(1, moduleid);
			
			data = psmt.executeQuery();
			data.next();
			
			int id = data.getInt("id");
			String title = data.getString("title");
			
			ModulKu tmp = new ModulKu(id, title);
			//System.out.println(title);
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
	
	//speichert Änderungen im Modul
	public void saveModule (Modul module){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(SAVEMODULE);
			
			psmt.setInt(1, module.getid());
			psmt.setString(2, module.gettitle());
			psmt.setInt(3, module.getlp());
			psmt.setString(4, module.getlanguage());
			psmt.setString(5, module.getturn());
			psmt.setString(6, module.getresponsible());
			psmt.setInt(7, module.getresponsibleid());
			psmt.setInt(8, module.getdozid());
			psmt.setString(9, module.getdoz());
			psmt.setString(10, module.getfiling());
			psmt.setString(11, module.getrequirements());
			psmt.setString(12, module.getaims());
			psmt.setString(13, module.getcontent());
			psmt.setString(14, module.getlit());
			psmt.setString(15, module.getevents());
			psmt.setString(16, module.getwork());
			psmt.setString(17, module.getexams());
			psmt.setString(18, module.getformcond());
			psmt.setString(19, module.getgrades());

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
	
	//holt sich neue id für neues Modul
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
	
	//löscht Modul aus der Datenbank
	public void deleteModule (int moduleid) {
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(DELETEMODULE);
			psmt.setInt(1, moduleid);

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
	
	//überprüft, ob Moduldatensatz gesperrt ist oder nicht
	public boolean getSperr(int id){
		boolean sperr = false;
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETSPERR);
			psmt.setInt(1, id);

			data = psmt.executeQuery();
			data.next();
			sperr = data.getBoolean("sperr");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		
		return sperr;		
	}
	
	//setzt sperr boolean bei bestimmter id
	public void setSperr(int id, boolean sperr){
		
		PreparedStatement psmt = null;

		try {

			con.setAutoCommit(false);
			
			psmt = con.prepareStatement(SETSPERR);
			
			psmt.setBoolean(1, sperr);
			psmt.setInt(2, id);

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
	
	//Gebe den Fachnamen zu einer ID zurück
		//TODO Test
		public String getFachname(int id) {
			String fachname = null;
			
			PreparedStatement psmt = null;
			ResultSet data = null;
			
			try {
				con.setAutoCommit(false);
				psmt = con.prepareStatement("SELECT name FROM handbuchname WHERE id ="+id);
				data = psmt.executeQuery();
				
				while (data.next()) {
					fachname = data.getString(1);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return fachname;
		}
		
		//Gebe dem Modulnamen zu einer ID zurück
		//TODO Test
		public String getModulname(int id) {
			String modulname = null;
		
			PreparedStatement psmt = null;
			ResultSet data = null;
			
			try {
				con.setAutoCommit(false);
				psmt = con.prepareStatement("SELECT title FROM moduldata WHERE id ="+id);
				data = psmt.executeQuery();
				
				while (data.next()) {
					modulname = data.getString(1);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return modulname;
		}
		//===================================================================================
		
		
		//Gebe alle IDs von Modulhandbüchern aus
		//TODO Test
		public LinkedList<Integer> getModuleHandbookIDs() {
			LinkedList<Integer> list = new LinkedList<Integer>();
			int id;
			
			PreparedStatement psmt = null;
			ResultSet data = null;
			
			try {
				con.setAutoCommit(false);
				psmt = con.prepareStatement("SELECT id FROM handbuchname");
				data = psmt.executeQuery();
				
				while (data.next()) {
					id = data.getInt(1);
					if (id % 3 == 0) {
						list.add(new Integer(id));
					}
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

	
}
