package gui;

import java.util.LinkedList;
import objects.Nachricht;


import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NewMessage extends Startseite implements Button.ClickListener {
	
	private Window newMess, messageW;
	ListSelect nachricht;
	private Button anzeigen = new Button("anzeigen");
	private Button okay;
	private LinkedList<Nachricht>lis;
	Label text;
	
	public NewMessage(LinkedList<Nachricht>lis){
		this.lis = lis;
		
		Window test = starta.getWindow("Aenderungsantraege");
		if(test != null){
			starta.removeWindow(test);	
		}
		newMess = new Window("");
		newMess.setName("Nachrichten");
		starta.addWindow(newMess);
		
		
		anzeigen.addListener(this);
		
		openNewMessages();
		Window old = starta.getWindow("Startseite");
		old.open(new ExternalResource(newMess.getURL()));
		
		
	}
	
	public void openNewMessages() {
		nachricht = new ListSelect();
		
		for(int i=0; i < lis.size(); i++){
			int u = i +1;
			nachricht.addItem(u + ": " + lis.get(i).getBetreff());		
		}
		newMess.addComponent(nachricht);
		newMess.addComponent(anzeigen);
	}
	
	
	public void buttonClick(Button.ClickEvent event){
		if(event.getButton() == anzeigen){
			String tmp = nachricht.getValue().toString();
			String[] splitResult = tmp.split(":", 1);
			int listnr = Integer.parseInt(splitResult[0])-1;
			messageW = new Window(lis.get(listnr).getBetreff());
    		
    		Layout mess = new VerticalLayout();
    		
    		text = new Label(lis.get(listnr).getbeschreibung());
    		messageW.setContent(mess);
    		messageW.addComponent(text);
    		messageW.addComponent(okay);
    		newMess.addWindow(messageW);
    		okay.addListener(this);
			
		}
		if(event.getButton() == okay) {
    		newMess.removeWindow(messageW);
    	}
	}
}
