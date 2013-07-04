
package control;
import java.util.LinkedList;

import com.vaadin.ui.Window;

import objects.Benutzer;
import objects.Nachricht;

import gui.LoginApplication;
import gui.ModulhandbuchRequest;
import gui.NewMessage;
import gui.Startseite;

import data.BenutzerData;
import data.BookData;
import data.BookName;
import data.ModulDatabase;
import data.ModulPufferData;
import data.NachrichtData;
import data.DeadLineData;



public class Controller {
	
	//test
	protected ModulDatabase modulDatabase = new ModulDatabase();
	protected ModulPufferData modulPufferData = new ModulPufferData();
	protected NachrichtData nachrichtenData = new NachrichtData();
	protected DeadLineData deadlineData = new DeadLineData();
	protected BookName book = new BookName();
	protected BookData bookdata = new BookData();
	protected static int userid ;
	protected static LoginApplication login;
	
	BenutzerData blarghs = new BenutzerData();
	//Konstruktor
	public Controller (){
		
		
	}
	public Controller(LoginApplication tmp){
	login = tmp;
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
		
		//if(blarghs.getRangDozent(Id) && blarghs.getRangDekan(Id) && blarghs.getRangDez2(Id)){rangname="[Dozent, Dekan, Dezernat 2]"; return rangname;}
		if(blarghs.getRangDozent(Id) && blarghs.getRangDekan(Id)){rangname="[Dozent, Dekan]"; return rangname;}
		//if(blarghs.getRangDozent(Id) && blarghs.getRangDez2(Id)){rangname="[Dozent, Dezernat 2]"; return rangname;}
		//if(blarghs.getRangDekan(Id) && blarghs.getRangDez2(Id)){rangname="[Dekan, Dezernat 2]"; return rangname;}
		if(blarghs.getRangDozent(Id)){rangname="Dozent"; return rangname;}
		if(blarghs.getRangDekan(Id)){rangname="Dekan"; return rangname;}
		//if (blarghs.getRangDez2(Id)){rangname="Dezernat 2"; return rangname;}
		return rangname;
		
	}
	
	//liefert aus Benutzer id zu name
	public int getID(String name){
		return blarghs.getID(name);
	}
	
	//gibt an, ob ein User bereits Stellvertreter ist
	public boolean getStell(int id){
		return blarghs.getRangStell(id);
	}
	
	//gibt an, ob der User idStell Stellvertreter von User userid ist
	public  boolean getMyStell(int idStell){
		return (blarghs.getRangStell(idStell)
				&&blarghs.getStellID(idStell)==userid);
	}
	
	//gibt an, ob ein User Rechte hat, auch über Stellvertreter
	public boolean getAdmin(int id){
		if(blarghs.getRangAdmin(id))return true;
		else if(blarghs.getStellAdmin(id))return true;
		else return false;
	}
		
	public boolean getDozent(int id){
		if(blarghs.getRangDozent(id))return true;
		else if(blarghs.getStellDozent(id))return true;
		else return false;
	}
		
	public boolean getDekan(int id){
		if(blarghs.getRangDekan(id))return true;
		else if(blarghs.getStellDekan(id))return true;
		else return false;
	}
		
	/*public boolean getDez2(int id){
		if(blarghs.getRangDez2(id))return true;
		else if(blarghs.getStellDez2(id))return true;
		else return false;
	}*/
	
	//gibt neuen Rang an Datenbankklasse weiter, um diesen zu speichern
	public void aenderungSpeichern(String Name, String Rang){
		
		int Id;
		Id = blarghs.getID(Name);
		blarghs.setRangDozent(Id, Rang.contains("Dozent"));
		blarghs.setRangDekan(Id, Rang.contains("Dekan"));		
		//blarghs.setRangDez2(Id, Rang.contains("Dezernat 2"));
		/*if (Rang.equals("[Dekan, Dozent]")||Rang.equals("[Dozent, Dekan]")){blarghs.setRangDozent(Id, true); blarghs.setRangDekan(Id, true); blarghs.setRangDez2(Id, false);}				//mehrere M�glichkeiten, da Reihenfolge im String nicht festgelegt
		if (Rang.equals("[Dozent, Dezernat 2]")||Rang.equals("[Dezernat 2, Dozent]")){blarghs.setRangDozent(Id, true); blarghs.setRangDez2(Id, true); blarghs.setRangDekan(Id, false);}
		if (Rang.equals("[Dekan, Dezernat 2]")||Rang.equals("[Dezernat 2, Dekan]")){blarghs.setRangDekan(Id, true); blarghs.setRangDez2(Id, true); blarghs.setRangDozent(Id, false);}
		if (Rang.equals("[Dozent]")){blarghs.setRangDozent(Id, true); blarghs.setRangDekan(Id, false); blarghs.setRangDez2(Id, false);}
		if (Rang.equals("[Dekan]")){blarghs.setRangDekan(Id, true); blarghs.setRangDez2(Id, false); blarghs.setRangDozent(Id, false);}
		if (Rang.equals("[Dezernat 2]")){blarghs.setRangDez2(Id, true); blarghs.setRangDozent(Id, false); blarghs.setRangDekan(Id, false);}*/
		Window old = login.getWindow("adminWindow");
		Startseite tmp1 = new Startseite(login, userid,old);
		
	}
	
	//gibt Username und Passwort an Benutzer-Klasse weiter zur Verifizierung, wenn verifiziert werden angegebene Aktionen ausgeführt
	public void login(String us, String pw, LoginApplication b) {
		
	if( blarghs.loginCheck(us,pw) == true){
		
		//UserRightAdministration test = new UserRightAdministration(b);
		userid = blarghs.getID(us);
		Window old = login.getWindow("main");
		Startseite aa = new Startseite(b, userid, old);
		
		

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
		
		Benutzer test = new Benutzer(id, us, em, p1, dozent, dekan, /*dez2,*/ admin, stell, stellid);
		blarghs.newUser(test);
	}

	//ruft Datenbankzugriffe für Modulhandbuchnamen und -ids auf, übergibt Loginapplication
	public void requestModule(LoginApplication start) {
		
		String[] list = book.getBookNames(0);
		LinkedList<Integer>  ids = book.getBookID(0);
		ModulhandbuchRequest req = new ModulhandbuchRequest(start, ids, list);
	}

	public void setDep (String StellName,boolean bool){
		int ID = blarghs.getID(StellName);
		if(bool){			
			blarghs.setRangStell(ID, true);
			blarghs.setStellID(ID, userid);
		}
		else {
			blarghs.setRangStell(ID,false);
			blarghs.setStellID(ID, 0);
		}
		/*if(blarghs.getRangDozent(userid)) { blarghs.setRangDozent(ID, true); }
		if(blarghs.getRangDekan(userid)) { blarghs.setRangDekan(ID, true); }
		if(blarghs.getRangDez2(userid)) { blarghs.setRangDez2(ID, true); }
		if(blarghs.getRangAdmin(userid)) { blarghs.setRangAdmin(ID, true); }*/
	}
	public void changeBenutzer(Benutzer neu){		
		blarghs.deleteUser(neu.getId());
		blarghs.newUser(neu);		
	}
	public Benutzer loadBenutzer(int userid1){
		Benutzer tmp = blarghs.loadBenutzer(userid1);
		return tmp;
	}
	
	public void loadNewMessageList(){

		LinkedList<Nachricht> list = nachrichtenData.loadNewBenachrichtList(userid);
		NewMessage newMessages = new NewMessage(list);
	}
	
	public boolean doesNameExist(String name){
		String[] liste = blarghs.getBenutzerListe();
		for (int i = 0;i<liste.length;i++){
			if(liste[i].equals(name))return true;
		}
		return false;
	}
}
