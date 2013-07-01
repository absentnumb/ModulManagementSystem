package objects;

public class Nachricht {
	
	private int id;
	private String Beschreibung;
	private String Betreff;
	private int benutzer;
	private int module;
	
	//erstellt Nachricht als Objekt
	public Nachricht(int id, String beschreibung, String betreff, int benutzer, int module){
		this.id = id;
		this.Beschreibung = beschreibung;
		this.benutzer = benutzer;
		this.module = module; 
		this.Betreff = betreff;
		}

	public int getid(){
		return id;	
	}
	public String getbeschreibung(){
		return Beschreibung;
	}
	public String getBetreff(){
		return Betreff;
	}
	public int getbenutzer(){	
		return benutzer;
	} 
	public int getmodule(){
		
		return module;
	}
}
