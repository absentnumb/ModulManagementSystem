package gui;


import java.util.*;

import objects.ModulKu;
import objects.Nachricht;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;


public class ChangeRequest extends Startseite implements Button.ClickListener {

	private Window changeR;
	private AbsoluteLayout mainLayout;
	ListSelect nachricht;
	private Button auswaehlen; 
	private Label label;
	private LinkedList<Nachricht>list; private LinkedList<ModulKu> lis2;
	
	public ChangeRequest(LinkedList<Nachricht>lis, LinkedList<ModulKu> list2) {
		this.list = lis;
		this.lis2 = list2;
		
		Window test = starta.getWindow("Aenderungsantraege");
		if(test != null){
			starta.removeWindow(test);	
		}
		changeR = new Window("");
		changeR.setName("Aenderungsantraege");		
		starta.addWindow(changeR);
		
		openNachrichtList();
		buildMainLayout();
		changeR.setContent(mainLayout);
		auswaehlen.addListener(this);		
		
		
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
		/*changeR.addComponent(nachricht);
		changeR.addComponent(auswaehlen);*/

	}
	
	//ButtonListener
	public void buttonClick(Button.ClickEvent event){
		if(event.getButton() == auswaehlen){
			String tmp = nachricht.getValue().toString();
			int i = 0;
			while( i<list.size()&& !(lis2.get(i).gettitle().equals(tmp))){
				i++;
				}
			contDek.loadRequest(lis2.get(i).getid(), list.get(i));	
		}
		if(event.getButton()== logout){
			starta.getMainWindow().getApplication().close();		
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
		label.setValue("Änderungsanträge");
		label.setStyleName(Runo.LABEL_H1);
		mainLayout.addComponent(label, "top:25.0%;left:35.0%;");
		
		// nachricht		
		nachricht.setImmediate(false);
		nachricht.setWidth("46.0%");
		nachricht.setHeight("70.0%");
		mainLayout.addComponent(nachricht, "top:35.0%;left:35.0%;");
		
		// auswaehlen
		auswaehlen = new Button();
		auswaehlen.setCaption("ansehen");
		auswaehlen.setImmediate(true);
		auswaehlen.setWidth("-1px");
		auswaehlen.setHeight("-1px");
		mainLayout.addComponent(auswaehlen, "top:83.0%;left:35.0%;");
		
		// logout
		logout = new Button();
		logout.setCaption("logout");
		logout.setImmediate(true);
		logout.setWidth("-1px");
		logout.setHeight("-1px");
		logout.addListener(this);
		logout.setStyleName(BaseTheme.BUTTON_LINK);
		mainLayout.addComponent(logout, "top:88.0%;left:35.0%;");
		
		return mainLayout;
	}
}	