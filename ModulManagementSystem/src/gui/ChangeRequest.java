package gui;


import java.util.*;

import objects.ModulKu;
import objects.Nachricht;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;


public class ChangeRequest extends Startseite implements Button.ClickListener {

	private Window changeR;
	ListSelect nachricht;
	private Button auswaehlen = new Button("auswählen"); 
	private LinkedList<Nachricht>lis; private LinkedList<ModulKu> lis2;
	
	public ChangeRequest(LinkedList<Nachricht>lis, LinkedList<ModulKu> list2) {
		this.lis = lis;
		this.lis2 = list2;
		
		Window test = starta.getWindow("Aenderungsantraege");
		if(test != null){
			starta.removeWindow(test);	
		}
		changeR = new Window("");
		changeR.setName("Aenderungsantraege");
		starta.addWindow(changeR);
		
		
		auswaehlen.addListener(this);
		
		openNachrichtList();
		Window old = starta.getWindow("Startseite");
		old.open(new ExternalResource(changeR.getURL()));
		//starta.removeWindow(old);	

	}
	
	//erstellt ListSelect-Element mit den Benachrichtigungen
	public void openNachrichtList(){
		
		nachricht = new ListSelect();
		
		for(int i=0; i < lis2.size(); i++){
			
			nachricht.addItem(lis2.get(i).gettitle());			
		}
		changeR.addComponent(nachricht);
		changeR.addComponent(auswaehlen);

	}
	
	//ButtonListener
	public void buttonClick(Button.ClickEvent event){
		if(event.getButton() == auswaehlen){
			String tmp = nachricht.getValue().toString();
			int i = 0;
			while( i<lis.size()&& !(lis2.get(i).gettitle().equals(tmp))){
				i++;
				}
			contDek.loadRequest(lis2.get(i).getid(), lis.get(i));	
		}
	}
}	