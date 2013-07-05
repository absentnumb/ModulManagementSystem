package objects;

public class Nachricht {
	
	private int id;
	private String Beschreibung;
	private int benutzer;
	private int module;
	
	//erstellt Nachricht als Objekt
	public Nachricht(int id, String beschreibung, int benutzer, int module){
		this.id = id;
		this.Beschreibung = beschreibung;
		this.benutzer = benutzer;
		this.module = module; 
		}

	public int getid(){
		return id;	
	}
	public String getbeschreibung(){
		return Beschreibung;
	}
	public int getbenutzer(){	
		return benutzer;
	} 
	public int getmodule(){
		
		return module;
	}
}
