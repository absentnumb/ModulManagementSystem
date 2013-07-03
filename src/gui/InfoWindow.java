package gui;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Runo;


public class InfoWindow extends Window implements Button.ClickListener{

	private AbsoluteLayout mainLayout;
	private Label label;
	private Button ok;
	private Window mainWindow;

	public InfoWindow(String betreff,String nachricht,Window _mainWindow) {
		super(betreff);
		mainWindow = _mainWindow;
		buildMainLayout(nachricht);
		setContent(mainLayout);
		setHeight("250px");
		setWidth("350px");
		center();
		mainWindow.addWindow(this);
	}


	private AbsoluteLayout buildMainLayout(String info) {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// ok
		ok = new Button();
		ok.setCaption("OK");
		ok.setImmediate(false);
		ok.setWidth("31.0%");
		ok.setHeight("-1px");
		ok.addListener(this);
		mainLayout.addComponent(ok, "top:80.0%;left:38.0%;");
		
		// label
		label = new Label();
		label.setImmediate(false);
		label.setWidth("89.0%");
		label.setHeight("-1px");
		label.setValue(info);
		label.setStyleName(Runo.LABEL_H2);
		mainLayout.addComponent(label, "top:10.0%;left:10.0%;");
		
		return mainLayout;
	}


	public void buttonClick(ClickEvent event) {
		if(event.getButton()==ok){
			mainWindow.removeWindow(this);
		}
	}

}