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

public class SetDeputy extends Startseite implements Button.ClickListener {
	
	private Button ok;
	Window setD;
	private VerticalLayout vertical;
	ListSelect benutzer;
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
	   
		Label label = new Label("Modul Managment System");
		vertical = new VerticalLayout ();
		setD.setContent(vertical);
		vertical.addComponent(label);
		setD.addComponent(label);
		openBenutzerListe(cont.benutzerListeAusgeben());
		Window old = starta.getWindow("Startseite");
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
				System.out.println("Du bist zu blöd etwas auszuwählen !!!");	//"Fehlermeldung" ;)
			}
			else{
			cont.setDep(aus);
			}
		}
	}
	
	//Benutzerliste wird ausgegeben, aus der ein Benutzer ausgewählt werden kann
	public void openBenutzerListe(String [] liste){
		
		benutzer = new ListSelect();
		
		for(int i = 0; i < liste.length; i++){
			
			benutzer.addItem(liste[i]);			//geht durch Liste durch und addet Benutzer
		}
		
		benutzer.setNullSelectionAllowed(false);	//leere Auswahl ist nicht erlaubt
		ok = new Button("Ok"); 
		ok.addListener(this);
		vertical.addComponent(benutzer);
		vertical.addComponent(ok);				
	}
}
