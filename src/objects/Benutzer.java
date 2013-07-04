package objects;

public class Benutzer {
	private int id;
	private String name;
	private String email;
	private String pw;
	private boolean dozent; 
	private boolean dekan;
	//private boolean dez2;
	private boolean admin;
	private boolean stell;
	private int stellid;
	
	public Benutzer(int ID, String Name, String Email, String Pw, boolean Dozent,
						boolean Dekan, /*boolean Dez2,*/ boolean Admin, boolean Stell,int Stellid ){
		
		id =ID;
		name= Name; 
		email =Email;
		pw = Pw;
		dozent = Dozent;
		dekan = Dekan;
		//dez2= Dez2;
		admin = Admin;
		stell = Stell;
		stellid = Stellid;
		
		
		
		
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPw() {
		return pw;
	}
	public boolean isDozent() {
		return dozent;
	}
	public boolean isDekan() {
		return dekan;
	}
	/*public boolean isDez2() {
		return dez2;
	}*/
	public boolean isAdmin() {
		return admin;
	}
	public boolean isStell() {
		return stell;
	}
	public int getStellid() {
		return stellid;
	}
	

}
