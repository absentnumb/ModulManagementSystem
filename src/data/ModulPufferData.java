package data;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import objects.Modul;
import objects.ModulKu;

import com.mysql.jdbc.*;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Window;

public class ModulPufferData extends KillConnections {
	
	private static Connection con;
	
	private static final String WRITEBUFFERMODULE = "INSERT INTO modulpufferdata " +
								"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
								" ?, ?, ?, ?)";
	private static final String LOADBUFFERMODULE = "SELECT * FROM modulpufferdata " +
								"WHERE id=?";
	private static final String DELETEPUFFERMODULE = "DELETE FROM modulpufferdata WHERE id=?";
	//private static final String LOADBUFFERMODULELIST = "SELECT id FROM moduldata WHERE responsibleid=?";

	public ModulPufferData(){
		
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
	
	//schreibt Moduldaten in den Modulpuffer
	public void writeBufferModule (Modul module){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(WRITEBUFFERMODULE);
			
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
	
	//holt sich Modul aus dem Puffer
	public Modul loadBufferModule (int moduleid){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADBUFFERMODULE);
			psmt.setInt(1, moduleid);
			
			data = psmt.executeQuery();
			data.next();
			
			int id = data.getInt("id");
			String title = data.getString("title");
			int lp = data.getInt("lp");
			String language = data.getString("language");
			String turn = data.getString("turn");
			String responsible = data.getString("responsible");
			int responsibleid = data.getInt("responsibleid");
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
					  responsible,dozid, doz, filing, requirements, aims,
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
	
	//löscht Modul aus dem Puffer
	public void deleteBufferModule (int moduleid) {
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(DELETEPUFFERMODULE);
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
	
	//holt sich Kurzbeschreibung aus dem Puffer
	public ModulKu loadModuleKu(int moduleid){
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(LOADBUFFERMODULE);
			psmt.setInt(1, moduleid);
			
			data = psmt.executeQuery();
			data.next();
			
			int id = data.getInt("id");
			String title = data.getString("title");
			
			ModulKu tmp = new ModulKu(id, title);
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
}