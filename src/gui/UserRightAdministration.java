package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;

import com.vaadin.event.*;
import com.vaadin.Application;

import com.vaadin.Application;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;

import control.Controller;

public class UserRightAdministration extends Startseite implements Button.ClickListener {
	
	private Button speichern,aendern,logout,back;
	private URL oldURL;
	private Label label;
	OptionGroup group;
	Window auswahlW, admin;   //, errW;
	//private VerticalLayout vertical;
	private AbsoluteLayout mainLayout;
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
	   
		/*Label label = new Label("Modul Managment System");
		vertical = new VerticalLayout ();	    
		vertical.addComponent(label);
		admin.addComponent(label);*/
	    
		openBenutzerListe(cont.benutzerListeAusgeben());
		
		buildMainLayout();
		admin.setContent(mainLayout);
		
		Window old = starta.getWindow("Startseite");
		oldURL = old.getURL();
		old.open(new ExternalResource(admin.getURL()));
		//starta.removeWindow(old);	

	}
	


	//öffnet Fenster in dem Ränge verteilt werden
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
		//group.addItem("Dezernat 2");
		if (Rang == "Benutzer" ){
			//kein Rang verteilt
		}
		else{
			if(Rang.contains("Dozent")) group.select("Dozent");
			if(Rang.contains("Dekan")) group.select("Dekan");
			//if(Rang.contains("Dezernat 2")) group.select("Dezernat 2");
		}
			/*if(Rang == "[Dozent, Dekan]"){
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
		}*/
		
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
				selectError(); //"Fehlermeldung" ;)
			}
			else{
				String blub = cont.rangAusgeben(aus);			
				openRangWindow(blub);
			}
		}
		
		/*if(event.getButton() == okay){
			admin.removeWindow(errW);
		}*/
		
		if(event.getButton() == logout){
			starta.getMainWindow().getApplication().close();
		}
		if(event.getButton()==back){
			admin.open(new ExternalResource(oldURL));
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
		/*aendern = new Button("Ändern"); 
		aendern.addListener(this);
		vertical.addComponent(benutzer);
		vertical.addComponent(aendern);*/				
	}
	
	//Fehlerfenster falls keinUser ausgewählt wurde
	public void selectError() {      
		InfoWindow errW = new InfoWindow("Fehler","Wählen Sie bitte einen Nutzer aus",admin);
		/*errW = new Window("Fehler");
	    okay = new Button("Ok");
	    Label wrong = new Label("Wählen Sie bitte einen Nutzer aus.");
	    Layout error = new VerticalLayout();	
	    errW.setContent(error);
	    errW.addComponent(wrong);
	    errW.addComponent(okay);
	    admin.addWindow(errW);
	    errW.setHeight("200px");
	    errW.setWidth("200px");
	    okay.addListener(this);*/
	}
	
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		mainLayout.setWidth("100.0%");
		mainLayout.setHeight("100.0%");
		
		// label
		label = new Label();
		label.setImmediate(false);
		label.setWidth("-1px");
		label.setHeight("-1px");
		label.setValue("Nutzerrechtverwaltung");
		label.setStyleName(Runo.LABEL_H1);
		mainLayout.addComponent(label, "top:25.0%;left:35.0%;");
		
		// benutzer		
		benutzer.setImmediate(false);
		benutzer.setWidth("46.0%");
		benutzer.setHeight("70.0%");
		mainLayout.addComponent(benutzer, "top:35.0%;left:35.0%;");
		
		// aendern
		aendern = new Button();
		aendern.setCaption("Nutzerrechte ändern");
		aendern.setImmediate(true);
		aendern.setWidth("-1px");
		aendern.setHeight("-1px");		
		aendern.addListener(this);
		mainLayout.addComponent(aendern, "top:83.0%;left:35.0%;");
		
		// logout
		logout = new Button();
		logout.setCaption("logout");
		logout.setImmediate(true);
		logout.setWidth("-1px");
		logout.setHeight("-1px");
		logout.setStyleName(BaseTheme.BUTTON_LINK);
		logout.addListener(this);
		mainLayout.addComponent(logout, "top:93.0%;left:35.0%;");
		
		// back
		back = new Button();
		back.setCaption("Startseite");
		back.setImmediate(true);
		back.setWidth("-1px");
		back.setHeight("-1px");
		back.setStyleName(BaseTheme.BUTTON_LINK);
		back.addListener(this);
		mainLayout.addComponent(back, "top:89.0%;left:35.0%;");
		
		return mainLayout;
	}
}
