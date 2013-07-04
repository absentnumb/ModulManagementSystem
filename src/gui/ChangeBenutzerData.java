package gui;

import java.net.URL;

import objects.Benutzer;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;

public class ChangeBenutzerData extends Startseite implements Button.ClickListener{
	Window change;  //, errW;
	Label label;
	PasswordField oldPass, newPass, newPass1;
	TextField uName, uMail;
	Button save,back;
	Benutzer tmp1;
	private AbsoluteLayout mainLayout;
	private URL oldURL;
	
public ChangeBenutzerData(Benutzer tmp){
	tmp1 = tmp; 
	Window test = starta.getWindow("AenderungBenutzer");
	if(test != null){
		starta.removeWindow(test);	
	}

	change = new Window("");
	/*change.setName("AenderungBenutzer");
	userName = new Label("Username");

	
	oldPass = new PasswordField("altes Passwort eingeben");
	newPass = new PasswordField("neues Passwort eingeben");
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
	change.addComponent(uMail);
	
	change.addComponent(oldPass);
	change.addComponent(newPass);
	change.addComponent(newPass1);
	change.addComponent(save);
	*/
	buildMainLayout();
	change.setContent(mainLayout);
	
	save.addListener(this);
	logout.addListener(this);
	starta.addWindow(change);
	
	
	Window old = starta.getWindow("Startseite");
	oldURL = old.getURL();
	old.open(new ExternalResource(change.getURL()));
	
}

public void buttonClick(ClickEvent event){
	if(event.getButton() == save){
		if(oldPass.getValue().equals(tmp1.getPw()) ){
			if(newPass1.getValue().equals(newPass.getValue())){
				String name =(String) uName.getValue();		
				String email =(String) uMail.getValue();
				String pw =(String) newPass.getValue();
				
				Benutzer Neu = new Benutzer(tmp1.getId(), name, email, pw, tmp1.isDozent(),
								tmp1.isDekan(), /*tmp1.isDez2(),*/ tmp1.isAdmin(), tmp1.isStell()
									, tmp1.getStellid());	
				cont.changeBenutzer(Neu);
				
				}
				else{
					
				displayError("Passwörter stimmen nicht überein");	
					
				}
						
		}
		else{
			
			displayError("Altes Passwort stimmt nicht");	
		}
	}
	if(event.getButton()== logout){
	       starta.getMainWindow().getApplication().close();			
	}	
	if(event.getButton()==back){
		change.open(new ExternalResource(oldURL));
	}
}
private void displayError(String text) {
		InfoWindow error = new InfoWindow("Fehler",text,change);
		/*errW = new Window("Fehler");

		Label wrong = new Label(text);
		Layout error = new VerticalLayout();
		
		errW.setContent(error);
		errW.addComponent(wrong);
		change.addWindow(errW);
		errW.setHeight("200px");
		errW.setWidth("200px");*/
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
	label.setValue("Benutzerdaten ändern");
	label.setStyleName(Runo.LABEL_H1);
	mainLayout.addComponent(label, "top:25.0%;left:35.0%;");
	
	// save
	save = new Button();
	save.setCaption("speichern");
	save.setImmediate(true);
	save.setWidth("-1px");
	save.setHeight("-1px");
	mainLayout.addComponent(save, "top:75.0%;left:35.0%;");
	
	// back
	back = new Button();
	back.setCaption("Startseite");
	back.setImmediate(true);
	back.setWidth("-1px");
	back.setHeight("-1px");
	back.setStyleName(BaseTheme.BUTTON_LINK);
	back.addListener(this);
	mainLayout.addComponent(back, "top:81.0%;left:35.0%;");
	
	// logout
	logout = new Button();
	logout.setCaption("logout");
	logout.setImmediate(true);
	logout.setWidth("-1px");
	logout.setHeight("-1px");
	logout.setStyleName(BaseTheme.BUTTON_LINK);
	mainLayout.addComponent(logout, "top:85.0%;left:35.0%;");
	
	// uName
	uName = new TextField();
	uName.setCaption("Benutzername");
	uName.setValue(tmp1.getName());
	uName.setImmediate(false);
	uName.setWidth("25.0%");
	uName.setHeight("-1px");
	mainLayout.addComponent(uName, "top:35.0%;left:35.0%;");
	
	// uMail
	uMail = new TextField();
	uMail.setCaption("E-Mail");
	uMail.setValue(tmp1.getEmail());
	uMail.setImmediate(false);
	uMail.setWidth("25.0%");
	uMail.setHeight("-1px");
	mainLayout.addComponent(uMail, "top:43.0%;left:35.0%;");
	
	// oldPass
	oldPass = new PasswordField();
	oldPass.setCaption("altes Passwort");
	oldPass.setImmediate(false);
	oldPass.setWidth("25.0%");
	oldPass.setHeight("-1px");
	mainLayout.addComponent(oldPass, "top:51.0%;left:35.0%;");
	
	// newPass
	newPass = new PasswordField();
	newPass.setCaption("neues Passwort");
	newPass.setImmediate(false);
	newPass.setWidth("25.0%");
	newPass.setHeight("-1px");
	mainLayout.addComponent(newPass, "top:59.0%;left:35.0%;");
	
	// newPass1
	newPass1 = new PasswordField();
	newPass1.setCaption("neues Passwort bestätigen");
	newPass1.setImmediate(false);
	newPass1.setWidth("-1px");
	newPass1.setHeight("-1px");
	mainLayout.addComponent(newPass1, "top:67.0%;left:35.0%;");
	
	return mainLayout;
}

}
