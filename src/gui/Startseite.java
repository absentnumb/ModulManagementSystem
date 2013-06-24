package gui;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;

import control.Controller;
import control.ControllerDekan;
import control.ControllerDozent;

import data.Benutzer;

public class Startseite implements Button.ClickListener{
	Window start;
	Label welcome;
	Button changeModule, declareDeputy, messages, changes, stichtag, viewChangeRequests, changeRights, viewChanges, logout,nullButton;
	private AbsoluteLayout mainLayout;
	private int count;//Zähler für Buttons
	//Benutzer user;
	public static LoginApplication starta;
	
	int userid, rang;
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
		
		//user = new Benutzer();
		userid = cont.getID(name);
		
		count = 0;
		nullButton = new Button();
		
		welcome = new Label("Willkommen "+name);
		//start.addComponent(welcome);
		
				
		/*Abfrage, welchen Rang oder welche Ränge der Nutzer
		besitzt und dementsprechende Gestaltung der Oberfläche.*/		
		if(cont.getAdmin(userid)){			
			changeRights = new Button("Nutzerrechte verwalten");
			changeRights.addListener(this);
			//start.addComponent(changeRights);
		}
		if(cont.getDozent(userid)){
			changeModule = new Button("Modul ändern/erstellen");
			changeModule.addListener(this);
			declareDeputy = new Button("Stellvertreter ernennen");
			declareDeputy.addListener(this);
			messages = new Button("Benachrichtigungen");
			messages.addListener(this);			
			//start.addComponent(changeModule);
			//start.addComponent(declareDeputy);
			//start.addComponent(messages);
		}
		//doppelte Buttons werden ausgeschlossen
		if(cont.getDekan(userid)){
			if(!cont.getDozent(userid)){
				changeModule = new Button("Modul ändern/erstellen");
				changeModule.addListener(this);
				declareDeputy = new Button("Stellvertreter ernennen");
				declareDeputy.addListener(this);
			}
			stichtag = new Button("neues Semester erstellen");
			stichtag.addListener(this);
			changes = new Button("aktuelle Änderungen");
			changes.addListener(this);			
			//stichtag = new Button("Stichtag festlegen");
			//stichtag.addListener(this);			
			//if(!cont.getDozent(userid)) start.addComponent(changeModule);
			//start.addComponent(changes);
			//if(!cont.getDozent(userid)) start.addComponent(declareDeputy);		
			
		}
		if(cont.getDez2(userid)){
			viewChangeRequests = new Button("aktuelle Änderungsanträge");
			viewChangeRequests.addListener(this);
			//start.addComponent(viewChangeRequests);
		}		
		
		logout = new Button("logout");
		logout.addListener(this);
		
		buildMainLayout();
		start.setContent(mainLayout);
		//start.addComponent(logout);
		
		Window old = starta.getWindow("main");
		old.open(new ExternalResource(start.getURL()));
		//starta.removeWindow(old);	

	}

	private AbsoluteLayout buildMainLayout() {
		Button button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		mainLayout.setWidth("100.0%");
		mainLayout.setHeight("100.0%");
		
		// welcome
		welcome.setImmediate(false);
		welcome.setWidth("-1px");
		welcome.setHeight("-1px");	
		welcome.setStyleName(Runo.LABEL_H1);
		mainLayout.addComponent(welcome, "top:25.0%;left:35.0%;");
		
		// button_1
		button_1 = getButton();
		if(button_1!=nullButton){			
			button_1.setWidth("-1px");
			button_1.setHeight("-1px");
			button_1.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_1, "top:35.0%;left:35.0%;");
		}	
		
		// button_2
		button_2 = getButton();
		if(button_2!=nullButton){
			button_2.setWidth("-1px");
			button_2.setHeight("-1px");
			button_2.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_2, "top:40.0%;left:35.0%;");
		}
		
		// button_3
		button_3 = getButton();
		if(button_3!=nullButton){
			button_3.setWidth("-1px");
			button_3.setHeight("-1px");
			button_3.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_3, "top:45.0%;left:35.0%;");
		}
		
		// button_4
		button_4 = getButton();
		if(button_4!=nullButton){
			button_4.setWidth("-1px");
			button_4.setHeight("-1px");
			button_4.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_4, "top:50.0%;left:35.0%;");
		}
		
		// button_5
		button_5 = getButton();
		if(button_5!=nullButton){					
			button_5.setWidth("-1px");
			button_5.setHeight("-1px");
			button_5.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_5, "top:55.0%;left:35.0%;");
		}
		
		// button_6
		button_6 = getButton();
		if(button_6!=nullButton){
			button_6.setWidth("-1px");
			button_6.setHeight("-1px");
			button_6.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_6, "top:60.0%;left:35.0%;");
		}
		
		// button_7
		button_7 = getButton();
		if(button_7!=nullButton){
			button_7.setWidth("-1px");
			button_7.setHeight("-1px");
			button_7.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_7, "top:65.0%;left:35.0%;");
		}
		
		// button_8
		button_8 = getButton();
		if(button_8!=nullButton){
			button_8.setWidth("-1px");
			button_8.setHeight("-1px");
			button_8.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_8, "top:70.0%;left:35.0%;");
		}
		
		// button_8
		button_9 = getButton();  
		if(button_9!=nullButton){
			button_9.setWidth("-1px");
			button_9.setHeight("-1px");
			button_9.setStyleName(BaseTheme.BUTTON_LINK);
			mainLayout.addComponent(button_9, "top:75.0%;left:35.0%;");
		}
		
		return mainLayout;
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
			SetDeputy dep = new SetDeputy();
		}
		
		if(event.getButton() == messages){
			//Dozent
		}
		
		/*if(event.getButton() == stichtag){
		
			//Dekan
		}*/
		
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
		if(event.getButton()==stichtag){
			DeadLine line = new DeadLine();
			//Dozent
		}
		if(event.getButton()== logout){
	       starta.getMainWindow().getApplication().close();
			
		}
	}
	
	public Button getButton(){//liefert zurück, welcher Button noch fehlt
		
		if (count==0) {//count gibt an, welcher Button geprüft wird			
			count++;
			if(changeModule!=null) return changeModule;				
		}
		if (count==1){
			count++;
			if(declareDeputy!=null) return declareDeputy;			
		}
		if (count==2){
			count++;
			if(messages!=null) return messages;			
		}
		if (count==3){
			count++;
			if(changes!=null) return changes;			
		}
		if (count==4){
			count++;
			if(viewChangeRequests!=null) return viewChangeRequests;			
		}
		if (count==5){
			count++;
			if(changeRights!=null) return changeRights;			
		}
		if (count==6){
			count++;
			if(viewChanges!=null) return viewChanges;			
		}
		if (count==7){
			count++;
			if(stichtag!=null) return stichtag;			
		}
		if(count==8){
			count++;
			if(logout!=null) return logout;
		}
		return nullButton;
	}
}
