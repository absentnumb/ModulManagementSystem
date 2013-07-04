package gui;

import java.net.URL;
import java.util.LinkedList;
import objects.Nachricht;


import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;

public class NewMessage extends Startseite implements Button.ClickListener {
	
	private Window newMess;  //, messageW;
	private AbsoluteLayout mainLayout;
	private Label label;
	ListSelect nachricht;
	private Button anzeigen,logout,back;
	private URL oldURL;
	private Button okay;
	private LinkedList<Nachricht>lis;
	Label text;
	
	public NewMessage(LinkedList<Nachricht>lis){
		this.lis = lis;
		
		Window test = starta.getWindow("Nachrichten");
		if(test != null){
			starta.removeWindow(test);	
		}
		newMess = new Window("");
		newMess.setName("Nachrichten");
		starta.addWindow(newMess);
				
		openNewMessages();
		buildMainLayout();
		anzeigen.addListener(this);
		newMess.setContent(mainLayout);
		Window old = starta.getWindow("Startseite");
		oldURL = old.getURL();
		old.open(new ExternalResource(newMess.getURL()));
		
		
	}
	
	public void openNewMessages() {
		nachricht = new ListSelect();
		
		for(int i=0; i < lis.size(); i++){
			int u = i +1;
			nachricht.addItem(u + ": " + lis.get(i).getBetreff());		
		}
		/*newMess.addComponent(nachricht);
		newMess.addComponent(anzeigen);*/
	}
	
	
	public void buttonClick(Button.ClickEvent event){
		if(event.getButton() == anzeigen){
			String tmp = nachricht.getValue().toString();
			String[] splitResult = tmp.split(":");
			int listnr = Integer.parseInt(splitResult[0])-1;
			InfoWindow messageW = new InfoWindow(lis.get(listnr).getBetreff(),lis.get(listnr).getbeschreibung(),newMess);
			/*messageW = new Window(lis.get(listnr).getBetreff());
    		
    		Layout mess = new VerticalLayout();
    		
    		text = new Label(lis.get(listnr).getbeschreibung());
    		messageW.setContent(mess);
    		messageW.addComponent(text);
    		okay = new Button("ok");
    		messageW.addComponent(okay);
    		newMess.addWindow(messageW);
    		okay.addListener(this);*/
			
		}
		/*if(event.getButton() == okay) {
    		newMess.removeWindow(messageW);
    	}*/
		if(event.getButton()== logout){
		       starta.getMainWindow().getApplication().close();				
		}
		if(event.getButton()==back){
			newMess.open(new ExternalResource(oldURL));
		}
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
		label.setValue("Neue Nachrichten");
		label.setStyleName(Runo.LABEL_H1);
		mainLayout.addComponent(label, "top:25.0%;left:35.0%;");
		
		// benutzer		
		nachricht.setImmediate(false);
		nachricht.setWidth("46.0%");
		nachricht.setHeight("70.0%");
		mainLayout.addComponent(nachricht, "top:35.0%;left:35.0%;");
		
		// ok
		anzeigen = new Button();
		anzeigen.setCaption("ansehen");
		anzeigen.setImmediate(false);
		anzeigen.setWidth("-1px");
		anzeigen.setHeight("-1px");
		mainLayout.addComponent(anzeigen, "top:83.0%;left:35.0%;");
		
		// logout
		logout = new Button();
		logout.setCaption("logout");
		logout.setImmediate(false);
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
