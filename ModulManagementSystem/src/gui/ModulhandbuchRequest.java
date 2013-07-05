package gui;

import java.util.LinkedList;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import control.ModuleHandbook;

public class ModulhandbuchRequest extends LoginApplication implements Button.ClickListener {
	
	Window mod;
	Button recommend = new Button("anfordern");
	Button okay2;
	VerticalLayout mlist = new VerticalLayout();
	private String [] tmp;
	private LinkedList<Integer> tmp2;
	
	public ModulhandbuchRequest(LoginApplication start, LinkedList<Integer> ids, String[] list) {
		Window test = start.getWindow("Modul auswählen");
		if(test != null){
			start.removeWindow(test);	
		}
		tmp = list;
		tmp2 = ids;
		mod = new Window("Modul auswählen");
		start.addWindow(mod);
		mod.setContent(mlist);
		recommend.addListener(this);
		openModuleList(list);
		mlist.addComponent(recommend);
		
		Window old = start.getWindow("main");
		old.open(new ExternalResource(mod.getURL()));
		//start.removeWindow(old);	

	}
    
    //erstellt ListSelect-Element und fügt es in das Modulfenster (siehe moduleList())
    public void openModuleList(String[] list) {
    	
    	modules = new ListSelect();
		
		for(int i=0; i < list.length; i++){
			modules.addItem(list[i]);			
		}
		
		modules.setNullSelectionAllowed(false);
		mod.addComponent(modules);
    }
    
    //ButtonListener
    public  void buttonClick(Button.ClickEvent event) {
    	String read ;
    	if (event.getButton() == recommend) {
    		try {
				read =(String) modules.getValue();
				int i= 0; 
				int modul = 0; 
				while(!(read.equals(tmp[i]))){
					modul = tmp2.get(i);
					i++;	
				}
				ModuleHandbook handbuch = new ModuleHandbook();
			FileResource test= handbuch.generatePDF(modul, start);
			Link l = new Link("Download the Modulehandbook here", test, "Download", 0, 0, Link.TARGET_BORDER_DEFAULT);
			Window linkPop = new Window("Link");
			linkPop.addComponent(l);
	    
	    	mod.addWindow(linkPop);
	    	linkPop.setHeight("300px");
	    	linkPop.setWidth("200px");
	    
				
			}
			catch (NullPointerException e){
				read = "";
				e.printStackTrace();
			}
    		if(event.getButton() == okay2) {
        		mod.removeWindow(selErrW);
        	}
	
			if(read.equals("")){
				displaySelectionError();
			}
			else{
				System.out.println("recommend");
			}
    	}
    	
    }
    //Fehlerfenster wenn kein Modul ausgewählt wird
    public void displaySelectionError() {
    	
    	selErrW = new Window("Fehler");
    	okay2 = new Button("Ok");
    	wrong2 = new Label("Sie müssen ein Modul auswählen!");
    	Layout selError = new VerticalLayout();
    	
    	selErrW.setContent(selError);
    	selErrW.addComponent(wrong2);
    	selErrW.addComponent(okay2);
    	mod.addWindow(selErrW);
    	selErrW.setHeight("200px");
    	selErrW.setWidth("200px");
    	okay2.addListener(this);
    }
}
