package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import com.vaadin.event.*;
import com.vaadin.Application;

import com.vaadin.Application;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;

import control.Controller;

public class UserRightAdministration extends Startseite implements Button.ClickListener {
	
	private Button speichern,aendern;
	OptionGroup group;
	Window auswahlW, admin;
	private VerticalLayout vertical;
	ListSelect benutzer;
	String aus;
	
	//übergibt Hauptwindow
	public UserRightAdministration() {
		Window test = starta.getWindow("adminWindow");
		if(test != null){
			starta.removeWindow(test);	
		}
		
		admin = new Window("");
	    admin.setName("adminWindow");
	    
	    starta.addWindow(admin);		//aktuelles window wird zum Hauptwindow geaddet
	   
		Label label = new Label("Modul Managment System");
		vertical = new VerticalLayout ();
		admin.setContent(vertical);
		vertical.addComponent(label);
		admin.addComponent(label);
		openBenutzerListe(cont.benutzerListeAusgeben());
		Window old = starta.getWindow("Startseite");
		old.open(new ExternalResource(admin.getURL()));
		//starta.removeWindow(old);	

	}
	


	//öffnet Fenster in dem R�nge verteilt werden
	public void openRangWindow(String Rang){
		
		auswahlW = new Window("Rang auswählen");
		Layout lay = new VerticalLayout();
		auswahlW.addComponent(lay);
		auswahlW.setHeight("200px");
		auswahlW.setWidth("400px");
		
		group = new OptionGroup("");	//Radio-Buttons
		group.setMultiSelect(true);		//mehrere Optionen können gleichzeitig ausgew�hlt sein
		
		group.addItem("Dozent");
		group.addItem("Dekan");
		group.addItem("Dezernat 2");
		if (Rang == "Benutzer" ){
			//kein Rang verteilt
		}
		else{
			if(Rang == "[Dozent, Dekan]"){
				group.select("Dekan");
				group.select("Dozent");
			}
			else{
				if (Rang.equals("[Dekan, Dezernat 2]" )){
					group.select("Dekan");
					group.select("Dezernat 2");	
				
					}else{if (Rang.equals("[Dozent, Dezernat 2]")){
							group.select("Dozent");
							group.select("Dezernat 2");	
				
							} else{
			
								group.select(Rang);
							}
			}
		}
		}
		lay.addComponent(group);
		speichern = new Button ("speichern");
		speichern.addListener(this);	
		
		lay.addComponent(speichern);
		admin.addWindow(auswahlW);	
	}
	
	//Listener für die Buttons
	public void buttonClick (Button.ClickEvent event) {
		
		if (event.getButton() == speichern){
			String ausw = this.getAuswahl();
			admin.removeWindow (auswahlW);
			cont.aenderungSpeichern(aus, ausw);		//Methode in Controller-Klasse
		}
		
		if(event.getButton() == aendern){
			try {
				aus = benutzer.getValue().toString();
			}
			catch (NullPointerException e){
				aus = "";
			}
	
			if(aus == ""){
				System.out.println("Du bist zu blöd etwas auszuwählen !!!");	//"Fehlermeldung" ;)
			}
			else{
			String blub = cont.rangAusgeben(aus);			
			openRangWindow(blub);
			}
		}
	}
	
	//schreibt getroffene Rangauswahl in einen String um
	public String getAuswahl(){
			
	String ff = group.getValue().toString();	
		return ff;
	}
	
	//Benutzerliste wird ausgegeben, aus der ein Benutzer ausgewählt werden kann
	public void openBenutzerListe(String [] liste){
		
		benutzer = new ListSelect();
		
		for(int i = 0; i < liste.length; i++){
			
			benutzer.addItem(liste[i]);			//geht durch Liste durch und addet Benutzer
		}
		
		benutzer.setNullSelectionAllowed(false);	//leere Auswahl ist nicht erlaubt
		aendern = new Button("Ändern"); 
		aendern.addListener(this);
		vertical.addComponent(benutzer);
		vertical.addComponent(aendern);				
	}
}
