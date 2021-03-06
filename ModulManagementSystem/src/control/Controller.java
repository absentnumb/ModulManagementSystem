package control;

import java.util.LinkedList;

import gui.LoginApplication;
import gui.ModulhandbuchRequest;
import gui.Startseite;

import data.Benutzer;
import data.BookData;
import data.BookName;
import data.ModulDatabase;
import data.ModulPufferData;
import data.NachrichtData;



public class Controller {
	
	
	protected ModulDatabase modulDatabase = new ModulDatabase();
	protected ModulPufferData modulPufferData = new ModulPufferData();
	protected NachrichtData nachrichtenData = new NachrichtData();
	protected BookName book = new BookName();
	protected BookData bookdata = new BookData();
	protected static int userid ;

	
	Benutzer blarghs;
	
	//Konstruktor
	public Controller(){
	
		blarghs = new Benutzer();
	}
	
	//Zugriff auf Datenbank-Klasse, holt sich Benutzerliste
	public String[] benutzerListeAusgeben(){
		
		String[] liste;
		liste = blarghs.getBenutzerListe();
		return liste;
		
		
	}
	
	//liest anhand der Benutzernamen (bzw. deren IDs über getID(Name) in einer Instanz der Benutzer-Klasse die Ränge aus
	public String rangAusgeben(String Name){
		
		String rangname = "";
		int Id;
		Id = blarghs.getID(Name);
		
		if(blarghs.getRangDozent(Id) && blarghs.getRangDekan(Id)){rangname="[Dozent, Dekan]"; return rangname;}
		if(blarghs.getRangDozent(Id) && blarghs.getRangDez2(Id)){rangname="[Dozent, Dezernat 2]"; return rangname;}
		if(blarghs.getRangDekan(Id) && blarghs.getRangDez2(Id)){rangname="[Dekan, Dezernat 2]"; return rangname;}
		if(blarghs.getRangDozent(Id)){rangname="Dozent"; return rangname;}
		if(blarghs.getRangDekan(Id)){rangname="Dekan"; return rangname;}
		if (blarghs.getRangDez2(Id)){rangname="Dezernat 2"; return rangname;}
		return rangname;
		
	}
	
	//gibt neuen Rang an Datenbankklasse weiter, um diesen zu speichern
	public void aenderungSpeichern(String Name, String Rang){
		
		int Id;
		Id = blarghs.getID(Name);
		
		if (Rang.equals("[Dekan, Dozent]")||Rang.equals("[Dozent, Dekan]")){blarghs.setRangDozent(Id, true); blarghs.setRangDekan(Id, true); blarghs.setRangDez2(Id, false);}				//mehrere M�glichkeiten, da Reihenfolge im String nicht festgelegt
		if (Rang.equals("[Dozent, Dezernat 2]")||Rang.equals("[Dezernat 2, Dozent]")){blarghs.setRangDozent(Id, true); blarghs.setRangDez2(Id, true); blarghs.setRangDekan(Id, false);}
		if (Rang.equals("[Dekan, Dezernat 2]")||Rang.equals("[Dezernat 2, Dekan]")){blarghs.setRangDekan(Id, true); blarghs.setRangDez2(Id, true); blarghs.setRangDozent(Id, false);}
		if (Rang.equals("[Dozent]")){blarghs.setRangDozent(Id, true); blarghs.setRangDekan(Id, false); blarghs.setRangDez2(Id, false);}
		if (Rang.equals("[Dekan]")){blarghs.setRangDekan(Id, true); blarghs.setRangDez2(Id, false); blarghs.setRangDozent(Id, false);}
		if (Rang.equals("[Dezernat 2]")){blarghs.setRangDez2(Id, true); blarghs.setRangDozent(Id, false); blarghs.setRangDekan(Id, false);}
		
	}
	
	//gibt Username und Passwort an Benutzer-Klasse weiter zur Verifizierung, wenn verifiziert werden angegebene Aktionen ausgeführt
	public void login(String us, String pw, LoginApplication b) {
		
	if( blarghs.loginCheck(us,pw) == true){
		
		//UserRightAdministration test = new UserRightAdministration(b);
	
		Startseite aa = new Startseite(b, us);
		
		userid = blarghs.getID(us);

	}else {
		b.displayError();
		}
	}
	
	//übernimmt eingegebene Benutzerdaten, erg�nzt Booleans f�r den Rang und ruft Methode zur Generierung einer neuen ID auf
	public void register(String us, String em, String p1) {
		
		boolean dozent = false;
		boolean dekan = false;
		boolean dez2 = false;
		boolean admin = false;
		boolean stell = false;
		int stellid = 0;
		int id = blarghs.getNewId();
		
		blarghs.newUser(id, us, p1, em, dozent, dekan, dez2, admin, stell, stellid);
	}

//ruft Datenbankzugriffe für Modulhandbuchnamen und -ids auf, übergibt Loginapplication
public void requestModule(LoginApplication start) {
		
		String[] list = book.getBookNames();
		LinkedList<Integer>  ids = book.getBookID();
		ModulhandbuchRequest req = new ModulhandbuchRequest(start, ids, list);
	}

public void setDep (String StellName){
	int ID = blarghs.getID(StellName);
	blarghs.setRangStell(ID, true);
	blarghs.setStellID(ID, userid);
	if(blarghs.getRangDozent(userid)) { blarghs.setRangDozent(ID, true); }
	if(blarghs.getRangDekan(userid)) { blarghs.setRangDekan(ID, true); }
	if(blarghs.getRangDez2(userid)) { blarghs.setRangDez2(ID, true); }
	if(blarghs.getRangAdmin(userid)) { blarghs.setRangAdmin(ID, true); }
	}
}
