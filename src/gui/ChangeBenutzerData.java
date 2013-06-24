package gui;

import objects.Benutzer;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ChangeBenutzerData extends Startseite implements Button.ClickListener{
	Window change ;
	Label userName, userMail;
	PasswordField oldPass, newPass, newPass1;
	TextField uName, uMail;
	Button save;
	
public ChangeBenutzerData(Benutzer tmp){
	
	Window test = starta.getWindow("Aenderungsantraege");
	if(test != null){
		starta.removeWindow(test);	
	}

	change = new Window("");
	change.setName("Aenderung");
	userName = new Label("Username");
	
	
	newPass = new PasswordField("Passwort eingeben");
	newPass1 = new PasswordField("Passwort bestätigen");
	uName = new TextField();
	uName.setValue(tmp.getName());
	uMail = new TextField();
	uMail.setValue(tmp.getEmail());  
	save = new Button("speichern");
	Layout reg = new VerticalLayout();
	
	change.setContent(reg);
	change.addComponent(userName);
	change.addComponent(uName);
	change.addComponent(userMail);
	change.addComponent(uMail);
	change.addComponent(newPass);
	change.addComponent(newPass1);
	change.addComponent(save);

	
	save.addListener(this);	
	starta.addWindow(change);
	
	
	Window old = starta.getWindow("Startseite");
	old.open(new ExternalResource(change.getURL()));
	
}

public void buttonClick(Button.ClickEvent e){
	
	
}
}
