package objects;

//erstellt Kurzbeschreibungs-Objekt für ein Modul (id und titel)
public class ModulKu {
	protected int id;	
	protected String title;
	
	public ModulKu(int conid,  String contitle){
		id = conid;
		title = contitle;
		
		
	}
	public int getid (){
		return id;
	}
	
	public String gettitle (){
		return title;
	}
}
