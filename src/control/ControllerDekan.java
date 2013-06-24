package control;

import gui.ChangeRequest;
import gui.RequestView;

import java.util.LinkedList;

import objects.Modul;
import objects.ModulKu;
import objects.Nachricht;

import data.*;

public class ControllerDekan extends Controller{
	
	public ControllerDekan(){	  
	}
	
	//bestätigt oder verwirft Änderung
	public void okRequest(Modul modul, Nachricht tmp, boolean ok){		
		nachrichtenData.delete(tmp.getid());	
		
		if (ok){			
			modulPufferData.deleteBufferModule(modul.getid());	
			modulDatabase.deleteModule(modul.getid());
			modulDatabase.saveModule(modul);		
			modulDatabase.setSperr(modul.getid(), false);
			Nachricht nachricht = new Nachricht(nachrichtenData.getNewId(), "Ihre Anfrage bezüglich des Moduls " +modul.gettitle()+" wurde angenommen.",modul.getdozid(), 0);
			nachrichtenData.newNachricht(nachricht);
			  
		}
		else{	
			modulDatabase.setSperr(modul.getid(), false);
			Nachricht nachricht = new Nachricht(nachrichtenData.getNewId(), "Ihre Anfrage bezüglich des Moduls " +modul.gettitle()+" wurde abgelehnt.",modul.getdozid(), 0);
			nachrichtenData.newNachricht(nachricht);
			modulPufferData.deleteBufferModule(modul.getid());
		}
	}

	//lädt Änderung
	public void loadRequest(int modul, Nachricht nachricht){
		
		Modul tmp = modulPufferData.loadBufferModule(modul);
		RequestView test = new RequestView(tmp, nachricht);
		
	}

	//lädt Änderungsliste
	public void loadRequestlist(){

		LinkedList<Nachricht> list = nachrichtenData.loadBenachrichtList(userid);
		LinkedList<ModulKu> list2 = new LinkedList<ModulKu>();
		
		for (int i =0; i<list.size(); i++){
			
		list2.add(modulPufferData.loadModuleKu(list.get(i).getmodule()));
			System.out.println(list.get(i).getbeschreibung());
			
		}
		ChangeRequest tmp = new ChangeRequest(list, list2);
		
	}
	//speichert Datum und Handbuch-PDF
	public void saveDatum(String datumstr){
		
	}
}
