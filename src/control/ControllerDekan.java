package control;

import gui.ChangeRequest;
import gui.LoginApplication;
import gui.ModulhandbuchRequest;
import gui.ModulhandbuchRequestAen;
import gui.RequestView;
import gui.Startseite;

import java.util.LinkedList;

import com.vaadin.ui.Window;

import objects.Modul;
import objects.ModulKu;
import objects.Nachricht;

import data.*;

public class ControllerDekan extends Controller{
	
	BookName bookName = new BookName();
	SaveHandbook save = new SaveHandbook();
	
	public ControllerDekan(){	  
	}
	
	//bestätigt oder verwirft Änderung
	public void okRequest(Modul modul, Nachricht tmp, boolean ok){		
		nachrichtenData.delete(tmp.getid());	
		
		if (ok){			
			modulPufferData.deleteBufferModule(modul.getid());	
			//Das Modul ist bereits vorhanden, wenn der Löschvorgang erfolgreich ist
			if (modulDatabase.deleteModule(modul.getid()) == 0) {
				modulDatabase.moveModuleIntoDefaultFach(modul.getid());
			}
			modulDatabase.saveModule(modul);		
			modulDatabase.setSperr(modul.getid(), false);
			Nachricht nachricht = new Nachricht(nachrichtenData.getNewId(), "Ihre Anfrage bezüglich des Moduls " +modul.gettitle()+" wurde angenommen.","Anfrage akzeptiert",modul.getdozid(), 0);
			nachrichtenData.newNachricht(nachricht);
			
		}
		else{	
			modulDatabase.setSperr(modul.getid(), false);
			Nachricht nachricht = new Nachricht(nachrichtenData.getNewId(), "Ihre Anfrage bezüglich des Moduls " +modul.gettitle()+" wurde abgelehnt.","Anfrage abgelehnt",modul.getdozid(), 0);
			nachrichtenData.newNachricht(nachricht);
			modulPufferData.deleteBufferModule(modul.getid());
		}
		Window old = login.getWindow("Aenderungsantrag");
		Startseite tmp1 = new Startseite(login, userid,old);

	}

	//lädt Änderung
	public void loadRequest(int modul, Nachricht nachricht){
		
		Modul tmp = modulPufferData.loadBufferModule(modul);
		RequestView test = new RequestView(tmp, nachricht);
		
	}

	//lädt Änderungsliste
	public void loadRequestlist(){

		LinkedList<Nachricht> list = nachrichtenData.loadBenachrichtList(userid);
		LinkedList<Nachricht> listStell = nachrichtenData.loadBenachrichtList(blarghs.getStellID(userid));
		LinkedList<ModulKu> list2 = new LinkedList<ModulKu>();
		
		for(int i = 0; i<listStell.size();i++){
			list.add(listStell.get(i));
		}
		
		for (int i =0; i<list.size(); i++){
			
		list2.add(modulPufferData.loadModuleKu(list.get(i).getmodule()));
			System.out.println(list.get(i).getbeschreibung());
			
		}
		ChangeRequest tmp = new ChangeRequest(list, list2);
		
	}
	//speichert Datum und Handbuch-PDF
	public void saveDatum(String datumstr){
		
	}
	
	public void requestModule() {
		
		String[] list = book.getBookNames(userid);
		LinkedList<Integer>  ids = book.getBookID(userid);
		ModulhandbuchRequestAen req = new ModulhandbuchRequestAen(ids, list);
	}

	public void scanHandbooks(int userid, String time) {
		LinkedList<Integer> arr = bookName.getBookID(userid);
		save.archive(arr, time);
	}
	
	public void setDeadline(String Deadline) {
		LinkedList<Integer> tmp = new LinkedList<Integer>(); 
		tmp = deadlineData.newDeadlineMessage(userid);
		for(int i = 0; i < tmp.size(); i++) {
			int resid = tmp.get(i).intValue();
			//System.out.println(resid);
			Nachricht deadLine = new Nachricht(nachrichtenData.getNewId(),"Stichtag",Deadline,resid, 0);
			nachrichtenData.newNachricht(deadLine);
		}
	}
	public void saveHandbook(String name){
		
		book.newHandbook(name, userid);
		
	}
}
