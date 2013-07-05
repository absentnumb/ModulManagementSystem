package gui;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import control.Controller;
import control.ControllerDekan;
import control.ControllerDozent;

import data.Benutzer;

public class Startseite implements Button.ClickListener{
	Window start;
	Label welcome;
	Button changeModule, declareDeputy, messages, changes, stichtag, viewChangeRequests, changeRights, viewChanges, logout;
	Benutzer user;
	public static LoginApplication starta;
	
	int userid ;
	Controller cont = new Controller();
	ControllerDozent contD = new ControllerDozent();
	ControllerDekan contDek = new ControllerDekan();
	
	//übergibt Hauptwindow
	public Startseite(){
	}
	
	public Startseite (LoginApplication aa, String name){
		starta = aa;
		Window test = starta.getWindow("Startseite");
		if(test != null){
			starta.removeWindow(test);	
		}
		
		start= new Window("Startseite");
		start.setName("Startseite");
		
		starta.addWindow(start);		
		
		user = new Benutzer();
		userid = user.getID(name);
		
		welcome = new Label("Willkommen "+name);
		start.addComponent(welcome);
		
		/*Abfrage, welchen Rang oder welche Ränge der Nutzer
		besitzt und dementsprechende Gestaltung der Oberfläche.*/		
		/*if(user.getRangAdmin(userid)){			
			changeRights = new Button("Nutzerrechte verwalten");
			changeRights.addListener(this);
			start.addComponent(changeRights);
		}*/
		if(user.getRangDozent(userid)){
			changeModule = new Button("Modul ändern/erstellen");
			changeModule.addListener(this);
			declareDeputy = new Button("Stellvertreter ernennen");
			declareDeputy.addListener(this);
			messages = new Button("Benachrichtigungen");
			messages.addListener(this);			
			start.addComponent(changeModule);
			start.addComponent(declareDeputy);
			start.addComponent(messages);
		}
		//doppelte Buttons werden ausgeschlossen
		if(user.getRangDekan(userid)){
			if(!user.getRangDozent(userid)){
				changeModule = new Button("Modul ändern/erstellen");
				changeModule.addListener(this);
				declareDeputy = new Button("Stellvertreter ernennen");
				declareDeputy.addListener(this);
			}
			changes = new Button("aktuelle Änderungen");
			changes.addListener(this);			
			stichtag = new Button("Stichtag festlegen");
			stichtag.addListener(this);			
			if(!user.getRangDozent(userid)) start.addComponent(changeModule);
			start.addComponent(changes);
			if(!user.getRangDozent(userid)) start.addComponent(declareDeputy);
			start.addComponent(stichtag);
		}
		if(user.getRangDez2(userid)){
			viewChangeRequests = new Button("aktuelle Änderungsanträge");
			viewChangeRequests.addListener(this);
			start.addComponent(viewChangeRequests);
		}
		
		
		
		/*if(user.getRangDozent(userid) && user.getRangDekan(userid)){
			welcome = new Label("Willkommen "+name);
			changeModule = new Button("Modul ändern/erstellen");
			changeModule.addListener(this);
			declareDeputy = new Button("Stellvertreter enrennen");
			declareDeputy.addListener(this);
			messages = new Button("Benachrichtigungen");
			messages.addListener(this);
			changes = new Button("aktuelle Änderungen");
			changes.addListener(this);
			stichtag = new Button("Stichtag festlegen");
			stichtag.addListener(this);
			start.addComponent(welcome);
			start.addComponent(changeModule);
			start.addComponent(declareDeputy);
			start.addComponent(messages);
			start.addComponent(changes);
			start.addComponent(stichtag);
		}
		else if(user.getRangDozent(userid) && user.getRangDez2(userid)){
			welcome = new Label("Willkommen "+name);
			changeModule = new Button("Modul ändern/erstellen");
			changeModule.addListener(this);
			declareDeputy = new Button("Stellvertreter ernennen");
			declareDeputy.addListener(this);
			messages = new Button("Benachrichtigungen");
			messages.addListener(this);
			viewChangeRequests = new Button("aktuelle Änderungsanträge einsehen");
			viewChangeRequests.addListener(this);
			start.addComponent(welcome);
			start.addComponent(changeModule);
			start.addComponent(declareDeputy);			
			start.addComponent(messages);
			start.addComponent(viewChangeRequests);
		}
		else if(user.getRangDekan(userid) && user.getRangDez2(userid)){
			welcome = new Label("Willkommen Dekan "+name);
			changeModule = new Button("Modul ändern/erstellen");
			changeModule.addListener(this);
			changes = new Button("aktuelle Änderungen");
			changes.addListener(this);
			declareDeputy = new Button("Stellvertreter enrennen");
			declareDeputy.addListener(this);
			stichtag = new Button("Stichtag festlegen");
			stichtag.addListener(this);
			viewChanges = new Button("aktuelle Änderungsanträge einsehen");
			viewChanges.addListener(this);
			start.addComponent(welcome);
			start.addComponent(changeModule);
			start.addComponent(changes);
			start.addComponent(declareDeputy);
			start.addComponent(stichtag);
			start.addComponent(viewChanges);
		}
		else if(user.getRangDozent(userid)){
			welcome = new Label("Willkommen Dozent "+name);
			changeModule = new Button("Modul ändern/erstellen");
			changeModule.addListener(this);
			declareDeputy = new Button("Stellvertreter ernennen");
			declareDeputy.addListener(this);
			messages = new Button("Benachrichtigungen");
			messages.addListener(this);
			start.addComponent(welcome);
			start.addComponent(changeModule);
			start.addComponent(declareDeputy);
			start.addComponent(messages);			
		}
		else if(user.getRangDekan(userid)){
			welcome = new Label("Willkommen Dekan "+name);
			changeModule = new Button("Modul ändern/erstellen");
			changeModule.addListener(this);
			changes = new Button("aktuelle Änderungen");
			changes.addListener(this);
			declareDeputy = new Button("Stellvertreter ernennen");
			declareDeputy.addListener(this);
			stichtag = new Button("Stichtag festlegen");
			stichtag.addListener(this);
			start.addComponent(welcome);
			start.addComponent(changeModule);
			start.addComponent(changes);
			start.addComponent(declareDeputy);
			start.addComponent(stichtag);
		}
		else if(user.getRangDez2(userid)){
			welcome = new Label("Willkommen Dezernat 2");
			viewChanges = new Button("aktuelle Änderungsanträge einsehen");
			viewChanges.addListener(this);
			start.addComponent(welcome);
			start.addComponent(viewChanges);
		}*/
		logout = new Button("logout");
		logout.addListener(this);
		start.addComponent(logout);
		
		Window old = starta.getWindow("main");
		old.open(new ExternalResource(start.getURL()));
		//starta.removeWindow(old);	

	}

	//ButtonListener
	public void buttonClick(ClickEvent event) {
		if(event.getButton() == changeModule){
			contD.ausgebenModulList(userid);
			//Dozent, Dekan
		}
		
		if(event.getButton() == changes){
			//Dekan
			contDek.loadRequestlist();
		}
		
		if(event.getButton() == declareDeputy){
			//Dozent, Dekan
			SetDeputy a = new SetDeputy();
		}
		
		if(event.getButton() == messages){
			//Dozent
		}
		
		if(event.getButton() == stichtag){
			//Dekan
		}
		
		if(event.getButton() == viewChangeRequests){
			//Dezernat 2
		}
		
		if(event.getButton()== changeRights){
			//Administrator
			UserRightAdministration b = new UserRightAdministration();
		}
		
		if(event.getButton()== viewChanges){
			//Dekan
		}
		if(event.getButton()== logout){
	       starta.getMainWindow().getApplication().close();
			
		}
	}
}
