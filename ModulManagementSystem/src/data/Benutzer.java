package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Benutzer extends KillConnections {
	
	Connection con;	
	
	//ein ? gibt eine freie Variable an, in die entsprechend von der Methode eingesetzt wird
	private static final String GETBENUTZERLISTE = "SELECT name FROM benutzer";
	private static final String GETPW = "SELECT pw FROM benutzer WHERE id=?";
	private static final String GETRANG = "SELECT * FROM benutzer WHERE id=?";
	private static final String GETID = "SELECT id FROM benutzer WHERE name=?";
	private static final String SETRANGDEKAN = "UPDATE benutzer SET dekan=? WHERE id=?";
	private static final String SETRANGDOZENT = "UPDATE benutzer SET dozent=? WHERE id=?";
	private static final String SETRANGDEZ2 = "UPDATE benutzer SET dez2=? WHERE id=?";
	private static final String SETRANGADMIN = "UPDATE benutzer SET admin=? WHERE id=?";
	private static final String SETRANGSTELL = "UPDATE benutzer SET stell=? WHERE id=?";
	private static final String SETNEWUSER = "INSERT INTO benutzer VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GETNEWID = "SELECT id FROM benutzer ORDER BY id DESC LIMIT 1";
	private static final String SETSTELLID = "UPDATE benutzer SET stellid=? WHERE id=?";
	
	//Konstruktor, baut Connection zur MySQL-Datenbank auf
	public Benutzer(){
		
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
	
	//holt sich alle in der Tabelle stehenden Benutzer und schreibt sie in eine LinkedList aus Strings
	public String[] getBenutzerListe(){
		
		LinkedList<String> tmp = new LinkedList<String>();
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETBENUTZERLISTE);

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
		
		String[] liste = tmp.toArray(new String[0]);
		return liste;
	}
	
	//holt sich den Boolean in der Dozent-Spalte
	public boolean getRangDozent(int Id){
		
		boolean rang = false;
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETRANG);
			psmt.setInt(1, Id);

			data = psmt.executeQuery();
			data.next();
			rang = data.getBoolean("dozent");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		
		return rang;
	}
	
	//holt sich den boolean aus der Dekan-Spalte
	public boolean getRangDekan(int Id){
		
		boolean rang = false;
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETRANG);
			psmt.setInt(1, Id);

			data = psmt.executeQuery();
			data.next();
			rang = data.getBoolean("dekan");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		
		return rang;		
	}
	
	//holt sich den Boolean aus der dez2-Spalte
	public boolean getRangDez2(int Id){
	
	boolean rang = false;
	
	PreparedStatement psmt = null;
	ResultSet data = null;

	try {

		con.setAutoCommit(false);

		psmt = con.prepareStatement(GETRANG);
		psmt.setInt(1, Id);

		data = psmt.executeQuery();
		data.next();
		rang = data.getBoolean("dez2");

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeConnections(data, psmt);
	}
	
	return rang;		
}

	//holt sich den Boolean aus der Admin-Spalte
	public boolean getRangAdmin(int Id){
	
	boolean rang = false;
	
	PreparedStatement psmt = null;
	ResultSet data = null;

	try {

		con.setAutoCommit(false);

		psmt = con.prepareStatement(GETRANG);
		psmt.setInt(1, Id);

		data = psmt.executeQuery();
		data.next();
		rang = data.getBoolean("admin");

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeConnections(data, psmt);
	}
	
	return rang;		
}

	//holt sich den Rang aus der Stellvertreter-Spalte
	public boolean getRangStell(int Id){
	
	boolean rang = false;
	
	PreparedStatement psmt = null;
	ResultSet data = null;

	try {

		con.setAutoCommit(false);

		psmt = con.prepareStatement(GETRANG);
		psmt.setInt(1, Id);

		data = psmt.executeQuery();
		data.next();
		rang = data.getBoolean("stell");

	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeConnections(data, psmt);
	}
	
	return rang;		
}
	
	//holt sich die zum Username passende ID aus der Datenbank
	public int getID(String Name){
		
		int id = 0;
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETID);
			
			psmt.setString(1, Name);

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
		
		return id;
	}
	
	//schreibt neuen Dekan-Rang zu passender ID
	public void setRangDekan(int Id, boolean Rang){
		
		PreparedStatement psmt = null;

		try {

			con.setAutoCommit(false);
			
			psmt = con.prepareStatement(SETRANGDEKAN);
			
			psmt.setBoolean(1, Rang);
			psmt.setInt(2, Id);

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

	//schreibt neuen Dozenten-Rang zu passender ID
	public void setRangDozent(int Id, boolean Rang){
		
		PreparedStatement psmt = null;
		
		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(SETRANGDOZENT);
			
			psmt.setBoolean(1, Rang);
			psmt.setInt(2, Id);
		
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

	//schreibt neuen Dez2-Rang zu passender ID
	public void setRangDez2(int Id, boolean Rang){
	
	PreparedStatement psmt = null;

	try {

		con.setAutoCommit(false);
		
		psmt = con.prepareStatement(SETRANGDEZ2);
		
		psmt.setBoolean(1, Rang);
		psmt.setInt(2, Id);

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

	//schreibt neuen Stellvertreter-Rang zu pqassender ID
	public void setRangStell(int Id, boolean Rang){
	
	PreparedStatement psmt = null;

	try {

		con.setAutoCommit(false);

		psmt = con.prepareStatement(SETRANGSTELL);
		
		psmt.setBoolean(1, Rang);
		psmt.setInt(2, Id);

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

	//schreibt neuen Admin-Rang zu passender ID
	public void setRangAdmin(int Id, boolean Rang){
	
	PreparedStatement psmt = null;

	try {

		con.setAutoCommit(false);

		psmt = con.prepareStatement(SETRANGADMIN);
		
		psmt.setBoolean(1, Rang);
		psmt.setInt(2, Id);

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

	//überprüft, ob Name und Passwort korrekt sind und gibt einen Boolean zurück
	public boolean loginCheck(String name, String pw){
	
		boolean loginname = false;
		int loginid = 0;
		boolean loginpw = false;
		boolean login = false;
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETBENUTZERLISTE);

			data = psmt.executeQuery();
		
			while (data.next()) {
				String test =  data.getString("name");
				if(name.equals(test)){
				loginname = true;
				loginid = getID(name);
				}
			}
		


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}

		
		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETPW);
			psmt.setInt(1, loginid);

			data = psmt.executeQuery();
			data.next();
			if(pw.equals(data.getString("pw"))){
				loginpw = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		if(loginname && loginpw){
			login = true;
		}
	return login;
	}

	//neuer User wird generiert, verwendet: eingegebene Benutzerdaten, mit 0 initialisierte Rang-Booleans, generierte ID
	public void newUser(int id, String us, String p1, String em, boolean dozent, boolean dekan, boolean dez2, boolean admin, boolean stell, int stellid) {
		
		PreparedStatement psmt = null;

		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(SETNEWUSER);
			
			psmt.setInt(1, id);
			psmt.setString(2, us);
			psmt.setString(3, p1);
			psmt.setString(4, em);
			psmt.setBoolean(5, dozent);
			psmt.setBoolean(6, dekan);
			psmt.setBoolean(7, dez2);
			psmt.setBoolean(8, admin);
			psmt.setBoolean(9, stell);
			psmt.setInt(10, stellid);

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
	
	//generiert ID für neuen Benutzer ausgehend von der ID des letzten Benutzers in der Datenbank
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
	
	public void setStellID(int Id, int StellId){
		
		PreparedStatement psmt = null;

		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(SETSTELLID);
			
			psmt.setInt(1, StellId);
			psmt.setInt(2, Id);

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