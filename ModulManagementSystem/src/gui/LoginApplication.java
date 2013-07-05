package gui;

import com.vaadin.Application;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;

import control.Controller;

/**
 * Main application class.
 */
public class LoginApplication extends Application implements Button.ClickListener {
	
	private Button login, register, modul, okay, save;
	Window mainWindow, modulW, errW, selErrW ,adminWindow, regW, regErrW;
	Controller control = new Controller();
	private VerticalLayout vertical;
	Label user, wrong, wrong2, userName, userMail, regErr;
	TextField username, uName, uMail;
	PasswordField pass1, pass2, password;
	ListSelect modules;
	String read;
	UserRightAdministration test;
	public static LoginApplication start;
	
    public void init() {
    	
        mainWindow = new Window("");
        mainWindow.setName("main");
        vertical = new VerticalLayout();
        user = new Label("Username");
        login = new Button("login");
        register = new Button("registrieren");
        modul = new Button("Module");
        username = new TextField();
      
        password = new PasswordField("Passwort");
        mainWindow.setContent(vertical);
        vertical.addComponent(user);
        vertical.addComponent(username);
        vertical.addComponent(password);
        vertical.addComponent(login);
        vertical.addComponent(register);
        vertical.addComponent(modul);
        setMainWindow(mainWindow);
        login.addListener(this);
        register.addListener(this);
        modul.addListener(this);
        start = this;
    }
    
    //ButtonListener
    public  void buttonClick(Button.ClickEvent event) {
  
    	if (event.getButton() == login) {
    		String us = username.getValue().toString();
    		
			String pw = password.getValue().toString();
	
			control.login(us, pw, this);			
    	}
    	if (event.getButton() == register) {
    		register();
    	}
    	if (event.getButton() == modul) {
    		control.requestModule(this);
    	}
    	if(event.getButton() == okay) {
    		mainWindow.removeWindow(errW);
    	}
    
    	if(event.getButton() == save) {
    		String us = uName.getValue().toString();
    		String em = uMail.getValue().toString();
    		String p1 = pass1.getValue().toString();
    		String p2 = pass2.getValue().toString();
    		System.out.println(p1);
    		System.out.println(p2);
    		System.out.println(us);
    		System.out.println(em);
    		
    		if (us.equals("")||em.equals("")||p1.equals("")||p2.equals("") ||!(p1.equals(p2))) {
    			registerError();
    		}else {
    		control.register(us, em, p1);
    		mainWindow.removeWindow(regW);
    		}
    	}
    }
    
    //Fehlerfenster für falschen Username/falsches Passwort
    public void displayError() {
    	
    		errW = new Window("Fehler");
    		okay = new Button("Ok");
    		wrong = new Label("username/passwort falsch");
    		Layout error = new VerticalLayout();
    		
    		errW.setContent(error);
    		errW.addComponent(wrong);
    		errW.addComponent(okay);
    		mainWindow.addWindow(errW);
    		errW.setHeight("200px");
    		errW.setWidth("200px");
    		okay.addListener(this);
    }
    
   
    
    //Popup für Registration
    public void register() {
    	
    	regW = new Window("Registrieren");
    	userName = new Label("Username");
    	userMail = new Label("email-addresse");
    	pass1 = new PasswordField("Passwort eingeben");
    	pass2 = new PasswordField("Passwort bestätigen");
    	uName = new TextField();
    	uMail = new TextField();
    	save = new Button("speichern");
    	Layout reg = new VerticalLayout();
    	
    	regW.setContent(reg);
    	regW.addComponent(userName);
    	regW.addComponent(uName);
    	regW.addComponent(userMail);
    	regW.addComponent(uMail);
    	regW.addComponent(pass1);
    	regW.addComponent(pass2);
    	regW.addComponent(save);
    	mainWindow.addWindow(regW);
    	regW.setHeight("300px");
    	regW.setWidth("200px");
    	save.addListener(this);
    }
    
    //Failsafe für die Registration
    public void registerError() {
    	regErrW = new Window("Fehler");
    	regErr = new Label("Daten unvollständig");
    	Layout re = new VerticalLayout();
    	
    	regErrW.setContent(re);
    	regErrW.addComponent(regErr);
    	mainWindow.addWindow(regErrW);
    	regErrW.setHeight("50px");
    	regErrW.setWidth("200px");
    }
}

