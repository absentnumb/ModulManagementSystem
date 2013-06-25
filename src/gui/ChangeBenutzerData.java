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
	Window change, errW ;
	Label userName, userMail;
	PasswordField oldPass, newPass, newPass1;
	TextField uName, uMail;
	Button save;
	Benutzer tmp1;
	
public ChangeBenutzerData(Benutzer tmp){
	tmp1 = tmp; 
	Window test = starta.getWindow("Aenderungsantraege");
	if(test != null){
		starta.removeWindow(test);	
	}

	change = new Window("");
	change.setName("AenderungBenutzer");
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
	if(e.equals("save")){
		if(oldPass.getValue().equals(tmp1.getPw()) ){
			if(newPass1.getValue().equals(newPass.getValue())){
				String name =(String) uName.getValue();		
				String email =(String) uMail.getValue();
				String pw =(String) newPass.getValue();
				
				Benutzer Neu = new Benutzer(tmp1.getId(), name, email, pw, tmp1.isDozent(),
								tmp1.isDekan(), tmp1.isDez2(), tmp1.isAdmin(), tmp1.isStell()
									, tmp1.getStellid());	
				
				
				}
				else{
					
				displayError("Passwörter stimmen nicht überein");	
					
				}
						
		}
		else{
			
			displayError("Altes Passwort stimmt nicht");	
		}
	}

	
}
private void displayError(String text) {
	
		errW = new Window("Fehler");

		Label wrong = new Label(text);
		Layout error = new VerticalLayout();
		
		errW.setContent(error);
		errW.addComponent(wrong);
		change.addWindow(errW);
		errW.setHeight("200px");
		errW.setWidth("200px");
}
}
