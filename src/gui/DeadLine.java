package gui;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;

public class DeadLine extends Startseite implements Button.ClickListener {

	Window dead;
	String datumstr;
	Label label;
	TextField datum;  
	private Button ok;
	private AbsoluteLayout lay;
	public DeadLine(){
		Window test = starta.getWindow("Stellvertreter");
		if(test != null){
			starta.removeWindow(test);	
	}
		dead = new Window("Stichtag");
		starta.addWindow(dead);
		
		 buildMainLayout();
			dead.setContent(lay);
			
			Window old = starta.getWindow("Startseite");
			old.open(new ExternalResource(dead.getURL()));	
	}
public void buttonClick (Button.ClickEvent event) {
		
		
		if(event.getButton() == ok){
			
			try {
				String datumstr = datum.getValue().toString();
			}
			catch (NullPointerException e){
				datumstr = "";
			}
	
			if(datumstr == ""){
				System.out.println("Datum eingeben");	//"Fehlermeldung" ;)
			}
			else{
				//contDek.saveDatum(datumstr);
				contDek.setDeadline(datumstr);
			}
		}
		if(event.getButton()== logout){
		       starta.getMainWindow().getApplication().close();
				
		}
	}

private AbsoluteLayout buildMainLayout() {
	lay = new AbsoluteLayout();
	lay.setImmediate(false);
	lay.setWidth("100%");
	lay.setHeight("100%");
	lay.setMargin(false);
	
	// top-level component properties
	lay.setWidth("100.0%");
	lay.setHeight("100.0%");
	
	// label
	label = new Label();
	label.setImmediate(false);
	label.setWidth("-1px");
	label.setHeight("-1px");
	label.setValue("Stichtag festlegen");
	label.setStyleName(Runo.LABEL_H1);
	lay.addComponent(label, "top:25.0%;left:35.0%;");
	
	//TextField
	datum = new TextField();
	datum.setCaption("Datum");
	datum.setImmediate(false);
	datum.setWidth("25.0%");
	datum.setHeight("-1px");
	lay.addComponent(datum, "top:40.0%;left:35.0%;");
	
	//Button
	ok = new Button();
	ok.setCaption("OK");
	ok.setImmediate(false);
	ok.setWidth("-1px");
	ok.setHeight("-1px");
	ok.addListener(this);
	lay.addComponent(ok, "top:50.0%;left:35.0%;");
	
	// logout
	logout = new Button();
	logout.setCaption("logout");
	logout.setImmediate(false);
	logout.setWidth("-1px");
	logout.setHeight("-1px");
	logout.setStyleName(BaseTheme.BUTTON_LINK);
	logout.addListener(this);
	lay.addComponent(logout, "top:60.0%;left:35.0%;");
	
	return lay;
}
}