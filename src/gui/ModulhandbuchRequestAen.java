package gui;

import java.util.LinkedList;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;

import control.ModuleHandbook;

public class ModulhandbuchRequestAen extends Startseite implements Button.ClickListener {
	
	Window mod; //, selErrW;	
	/*Button recommend = new Button("anfordern");
	Button okay2;
	VerticalLayout mlist = new VerticalLayout();*/
	private Button recommend,back; //, okay2;
	private Label label, wrong2;
	private AbsoluteLayout mainLayout;
	private String [] tmp;
	private LinkedList<Integer> tmp2;
	ListSelect modules;
	public ModulhandbuchRequestAen( LinkedList<Integer> ids, String[] list) {
		Window test = starta.getWindow("Modul auswählen");
		if(test != null){
			start.removeWindow(test);	
		}
		tmp = list;
		tmp2 = ids;
		mod = new Window("Modul auswählen");
		start.addWindow(mod);
		/*mod.setContent(mlist);
		recommend.addListener(this);*/
		openModuleList(list);
		buildMainLayout();
		recommend.addListener(this);		
		mod.setContent(mainLayout);
		//mlist.addComponent(recommend);				
		Window old = starta.getWindow("main");
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
 
				read =(String) modules.getValue();
				int i= 0; 
				int modul = 0; 
				while(!(read.equals(tmp[i]))){
					modul = tmp2.get(i);
					i++;	
				}
					
	    
				
			

    	}
    	if(event.getButton()== back){
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
    	    label.setValue("Modul auswählen");
    	    label.setStyleName(Runo.LABEL_H1);
    	    mainLayout.addComponent(label, "top:25.0%;left:35.0%;");
    	    
    	    // modules    
    	    modules.setImmediate(false);
    	    modules.setWidth("46.0%");
    	    modules.setHeight("70.0%");
    	    mainLayout.addComponent(modules, "top:35.0%;left:35.0%;");
    	    
    	    // recommend
    	    recommend = new Button();
    	    recommend.setCaption("ansehen");
    	    recommend.setImmediate(true);
    	    recommend.setWidth("-1px");
    	    recommend.setHeight("-1px");
    	    mainLayout.addComponent(recommend, "top:83.0%;left:35.0%;");
    	    
    	    // logout
    	    back = new Button();
    	    back.setCaption("Startseite");
    	    back.setImmediate(true);
    	    back.setWidth("-1px");
    	    back.setHeight("-1px");
    	    back.setStyleName(BaseTheme.BUTTON_LINK);
    	    back.addListener(this);
    	    mainLayout.addComponent(back, "top:88.0%;left:35.0%;");
    	    
    	    return mainLayout;
    	}
    
    //Fehlerfenster wenn kein Modul ausgewählt wird
    public void displaySelectionError() {
    	InfoWindow error = new InfoWindow("Fehler","Wählen Sie bitte ein Modul aus",mod);
    	/*selErrW = new Window("Fehler");
    	okay2 = new Button("Ok");
    	wrong2 = new Label("Sie müssen ein Modul auswählen!");
    	Layout selError = new VerticalLayout();
    	
    	selErrW.setContent(selError);
    	selErrW.addComponent(wrong2);
    	selErrW.addComponent(okay2);
    	mod.addWindow(selErrW);
    	selErrW.setHeight("200px");
    	selErrW.setWidth("200px");
    	okay2.addListener(this);*/
    }
}
