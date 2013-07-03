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

public class SetDeputy extends Startseite implements Button.ClickListener {
	
	private Button ok,delete,back;
	private URL oldURL;
	Window setD;
	//private VerticalLayout vertical;
	private AbsoluteLayout mainLayout;
	ListSelect benutzer;
	Label label;
	String aus;
	
	//übergibt Hauptwindow
	public SetDeputy() {
		Window test = starta.getWindow("Stellvertreter");
		if(test != null){
			starta.removeWindow(test);	
		}
		
		setD = new Window("");
	    setD.setName("Stellvertreter");
	    
	    starta.addWindow(setD);		//aktuelles window wird zum Hauptwindow geaddet
	   
		//label = new Label("Modul Managment System");
		//vertical = new VerticalLayout ();
	    buildMainLayout();
		setD.setContent(mainLayout);
		//vertical.addComponent(label);
		//setD.addComponent(label);
		openBenutzerListe(cont.benutzerListeAusgeben());
		Window old = starta.getWindow("Startseite");
		oldURL = old.getURL();
		old.open(new ExternalResource(setD.getURL()));
		//starta.removeWindow(old);	
	
	//Listener für die Buttons
	}
	public void buttonClick (Button.ClickEvent event) {
		
		
		if(event.getButton() == ok){
			try {
				aus = benutzer.getValue().toString();
			}
			catch (NullPointerException e){
				aus = "";
			}
	
			if(aus == ""){
				InfoWindow err = new InfoWindow("Fehler","Wählen Sie bitte einen Nutzer aus",setD);	//"Fehlermeldung" ;)
			}
			else{
				InfoWindow isDep;
				if(cont.getStell(cont.getID(aus))) 
					isDep = new InfoWindow("Fehler","Dieser Nutzer ist bereits ein Stellvertreter",setD);
				else cont.setDep(aus,true);
			}
		}
		
		if(event.getButton()==delete){
			try {
				aus = benutzer.getValue().toString();
			}
			catch (NullPointerException e){
				aus = "";
			}	
			if(aus == ""){
				InfoWindow err = new InfoWindow("Fehler","Wählen Sie bitte einen Nutzer aus",setD);	//"Fehlermeldung" ;)
			}
			else{
				InfoWindow isntDep;
				if(cont.getMyStell(cont.getID(aus)))
					cont.setDep(aus, false);
				else
					isntDep = new InfoWindow("Fehler","Dieser Nutzer ist nicht Ihr Stellvertreter",setD);
			}
		}
		
		if(event.getButton()== logout){
		       starta.getMainWindow().getApplication().close();				
		}
		if(event.getButton()==back){
			setD.open(new ExternalResource(oldURL));
		}
	}
	
	//Benutzerliste wird ausgegeben, aus der ein Benutzer ausgewählt werden kann
	public void openBenutzerListe(String [] liste){
		
		//benutzer = new ListSelect();
		
		for(int i = 0; i < liste.length; i++){
			
			benutzer.addItem(liste[i]);			//geht durch Liste durch und addet Benutzer
		}
		
		benutzer.setNullSelectionAllowed(false);	//leere Auswahl ist nicht erlaubt
		/*ok = new Button("Ok"); 
		ok.addListener(this);
		vertical.addComponent(benutzer);
		vertical.addComponent(ok);*/				
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
		label.setValue("Stellvertreter ernennen");
		label.setStyleName(Runo.LABEL_H1);
		mainLayout.addComponent(label, "top:25.0%;left:35.0%;");
		
		// benutzer
		benutzer = new ListSelect();
		benutzer.setImmediate(false);
		benutzer.setWidth("46.0%");
		benutzer.setHeight("70.0%");
		mainLayout.addComponent(benutzer, "top:35.0%;left:35.0%;");
		
		// ok
		ok = new Button();
		ok.setCaption("Stellvertreter ernennen");
		ok.setImmediate(false);
		ok.setWidth("25%");
		ok.setHeight("-1px");
		ok.addListener(this);
		mainLayout.addComponent(ok, "top:83.0%;left:35.0%;");
		
		// delete
		delete = new Button();
		delete.setCaption("Stellvertreter löschen");
		delete.setImmediate(false);
		delete.setWidth("25%");
		delete.setHeight("-1px");
		delete.addListener(this);
		mainLayout.addComponent(delete, "top:88.0%;left:35.0%;");
		
		// logout
		logout = new Button();
		logout.setCaption("logout");
		logout.setImmediate(false);
		logout.setWidth("-1px");
		logout.setHeight("-1px");
		logout.setStyleName(BaseTheme.BUTTON_LINK);
		logout.addListener(this);
		mainLayout.addComponent(logout, "top:97.0%;left:35.0%;");
		
		// back
		back = new Button();
		back.setCaption("Startseite");
		back.setImmediate(true);
		back.setWidth("-1px");
		back.setHeight("-1px");
		back.setStyleName(BaseTheme.BUTTON_LINK);
		back.addListener(this);
		mainLayout.addComponent(back, "top:94.0%;left:35.0%;");
		
		return mainLayout;
	}
}
