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
	
	private static Connection con;
	
	private static final String LOADMODULE = "SELECT * FROM moduldata WHERE id=?";
	private static final String LOADMODULELIST = "SELECT id FROM moduldata WHERE dozid=?";
	private static final String GETNEWID = "SELECT id FROM moduldata ORDER BY id DESC LIMIT 1";
	private static final String GETNEWIDP = "SELECT id FROM modulpufferdata ORDER BY id DESC LIMIT 1";

	private static final String SAVEMODULE = "INSERT INTO moduldata " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?," +
			" ?, ?, ?, ?, ?)";
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
			psmt.setBoolean(20,false);

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
		
		int id1 = 0;
		int id2 =0;
		
		PreparedStatement psmt = null;
		PreparedStatement psmt1 = null;
		ResultSet data = null;
		ResultSet data1 = null;
		
		try {

			con.setAutoCommit(false);

			psmt = con.prepareStatement(GETNEWID);

			data = psmt.executeQuery();

			data.next();
			id1 = data.getInt("id");
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);

		}
		try{
			con.setAutoCommit(false);

			psmt1 = con.prepareStatement(GETNEWIDP);

			data1 = psmt1.executeQuery();

			data1.next();
			id2 = data1.getInt("id");
			}
		catch (SQLException e1) {
		e1.printStackTrace();
	} catch (Exception e1) {
		e1.printStackTrace();
	} finally {
		closeConnections(data1, psmt1);
	}
	
		if(id1 >= id2){
		id1 = id1+3;
		}
		else{
		id1 = id2+3;	
			
		}
		System.out.println(id1);
		return id1;
	}
	
	//löscht Modul aus der Datenbank
	public int deleteModule (int moduleid) {
		
		//Die Anzahl der Einträge die gelöscht wurden wird ausgegeben
		int deletings = 0;
		
		PreparedStatement psmt = null;
		ResultSet data = null;

		try {
			con.setAutoCommit(false);

			psmt = con.prepareStatement(DELETEMODULE);
			psmt.setInt(1, moduleid);
			
			deletings = psmt.executeUpdate();
			
			System.out.println("DELETE?:"+deletings);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnections(data, psmt);
		}
		return deletings;
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
			closeConnections(data, psmt);
			return fachname;
		}
		
		//Gebe den Modulnamen zu einer ID zurück
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
			closeConnections(data, psmt);
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
			closeConnections(data, psmt);
			return list;
		}
		
		//Trage ein neues Fach ein (trage Name und neue ID ein)
				//TODO Ändern/Test
				public void newFach(int id, String fachname) {
					
					PreparedStatement psmt = null;
					ResultSet data = null;
					
					try {
						
						//Erzeuge eine neue ID
						
						//con.setAutoCommit(false);
						
						psmt = con.prepareStatement("SELECT id FROM handbuchname ORDER BY id ASC");
						data = psmt.executeQuery();
						
						//x ist die neue ID
						int x = 2;
						int temp = 0;
						
						while (data.next()) {
							temp = data.getInt(1);
							if (x == temp) {
								x = x + 3;
							}
						}
						System.out.println(x);
						
						con.close();
						
						try {
							con =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost/test");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						//Füge den Zeiger auf das Fach hinzu
						String query1 = "INSERT INTO handbuchdata VALUES("+id+","+x+");";
						psmt = con.prepareStatement(query1);
						psmt.executeUpdate();
						
						/*
						psmt = con.prepareStatement("SELECT * FROM handbuchname");
						data = psmt.executeQuery();
						while (data.next()) {
							System.out.println(data.getInt(1));
						}
						System.out.println();
						
						psmt = con.prepareStatement("SELECT * FROM handbuchdata");
						data = psmt.executeQuery();
						while (data.next()) {
							System.out.println(data.getInt(1));
						}		
						System.out.println();
						*/
						
						//Füge den Namen des Faches ein
						String query2 = "INSERT INTO handbuchname VALUES("+x+",'"+fachname+"');";
						psmt = con.prepareStatement(query2);
						psmt.executeUpdate();
						
						System.out.println(query2);
						
						closeConnections(data, psmt);
						
								
					} catch (Exception e) {
						e.printStackTrace();
					}
					closeConnections(data, psmt);
				}
				
				//Lösche das angegebene Fach
				public boolean deleteFachOrModul(int id) {
					System.out.println("ID: " + id);
					
					boolean b = true;
					
					PreparedStatement psmt = null;
					ResultSet data = null;
					
					try {
						//Prüfe, ob das Element ein Fach oder Modul ist
						if (id % 3 == 2) {
						
							//Das zu löschende Fach darf nicht auf andere Fächer zeigen
							psmt = con.prepareStatement("SELECT id FROM handbuchdata");
							data = psmt.executeQuery();
							while (data.next()) {
								if (id == data.getInt(1)) {
									b = false;
								}
							}
							
							if (b == true) {
											
								//Lösche Zeiger, die auf das Fach zeigen
								String s = "DELETE FROM handbuchdata WHERE fid ="+id;
								psmt = con.prepareStatement(s);
								int result = psmt.executeUpdate();
								System.out.println("Zeiger auf Fach gelöscht: " + result);
								
								
								//Lösche den Fachnamen
								s = "DELETE FROM handbuchname WHERE id ="+id;
								psmt = con.prepareStatement(s);
								result = psmt.executeUpdate();
								System.out.println("Fachname gelöscht: " + result);
							
							
						}
						} else if (id % 3 == 0) {
							
							//Lösche Zeiger, die auf das Modul zeigen
							String s = "DELETE FROM handbuchdata WHERE fid ="+id;
							psmt = con.prepareStatement(s);
							int result = psmt.executeUpdate();
							
							System.out.println("Zeiger auf Modul gelöscht: " + result);
							
							//Lösche das Modul
							s = "DELETE FROM moduldata WHERE id ="+id;
							psmt = con.prepareStatement(s);
							result = psmt.executeUpdate();
							System.out.println("Modul gelöscht: " + result);
						}
					
						
					
					} catch (Exception e) {
						e.printStackTrace();
					}
					closeConnections(data, psmt);
					return b;
				}
				
				//Gibt eine Liste mit den IDs von neuen nicht zugeordneten Modulen zurück
				//TODO Test
				public LinkedList<Integer> getNewModules() {
					
					LinkedList<Integer> list = new LinkedList<Integer>();
					
					PreparedStatement psmt = null;
					ResultSet data = null;
					
					try {	
							// Das Fach mit der ID 0 zeigt auf alle nicht zugeordneten Fächer und wird selbst nicht verwendet
							psmt = con.prepareStatement("SELECT fid FROM handbuchdata WHERE id = 0");
							data = psmt.executeQuery();
							while (data.next()) {
								list.add(new Integer(data.getInt(1)));			
							}
							
					} catch (Exception e) {
						e.printStackTrace();
					}
					closeConnections(data, psmt);
					return list;
				}
				
				/*Ordne ein noch nicht zugeordnetes Modul (aus Fach 0) einem anderen Fach zu.
				 * Der Zeiger von Fach 0 au das Modul wird dabei gelöscht.
				 */
				public boolean moveNewModule(int id, int fid) {
					boolean b = false;
					
					PreparedStatement psmt = null;
					ResultSet data = null;
					
					try {	
							// Das Fach mit der ID 0 zeigt auf alle nicht zugeordneten Fächer und wird selbst nicht verwendet
							// TODO Evtl. verbessern
							psmt = con.prepareStatement("INSERT INTO handbuchdata VALUES("+id+", "+fid+")");
							psmt.executeUpdate();
							
							psmt = con.prepareStatement("DELETE FROM handbuchdata WHERE id = 0 AND fid = "+fid);
							psmt.executeUpdate();
							
							b = true;
							
					} catch (Exception e) {
						e.printStackTrace();
					}
					closeConnections(data, psmt);
					return b;
				}
				
				/*Ordne ein Modul einem Fach zu. Diese Methode ist für Module gedacht, die bereits einmal zugeordnet wurden.
				 */
				public boolean moveModule(int id, int fid) {
					boolean b = false;
					
					PreparedStatement psmt = null;
					ResultSet data = null;
					
					try {	
						
							psmt = con.prepareStatement("INSERT INTO handbuchdata VALUES("+id+", "+fid+")");
							psmt.executeUpdate();
							
							b = true;
							
					} catch (Exception e) {
						e.printStackTrace();
					}
					closeConnections(data, psmt);
					return b;
				}
				
				//Erzeugt einen Zeiger von Fach 0 auf ein Modul
				public boolean moveModuleIntoDefaultFach(int fid) {
					boolean b = false;
					
					PreparedStatement psmt = null;
					ResultSet data = null;
					
					try {	
							// Das Fach mit der ID 0 zeigt auf das angegebene Modul
							psmt = con.prepareStatement("INSERT INTO handbuchdata VALUES("+0+", "+fid+")");
							psmt.executeUpdate();
														
							b = true;
							
					} catch (Exception e) {
						e.printStackTrace();
					}
					closeConnections(data, psmt);
					return b;
				}

	
}
