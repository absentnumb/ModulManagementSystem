package gui;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import objects.ModulKu;

import com.vaadin.data.util.*;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;


public class ModulReview extends Startseite implements Button.ClickListener{
	String savestr;	
	Window modEdCr;
	public ListSelect module; //login
	private Button create, aendern;
	private LinkedList<ModulKu> liste;
	
	public ModulReview(LinkedList<ModulKu> idiot){
		Window test = starta.getWindow("Modulbearbeiten");
		if(test != null){
			starta.removeWindow(test);	
		}
		
		liste = idiot;
		modEdCr = new Window("");
		modEdCr.setName("Modulbearbeiten");
		starta.addWindow(modEdCr);
	
		create = new Button("erstellen");
		aendern = new Button("Ändern");
		create.addListener(this);
		aendern.addListener(this);
		
		module = openModulList(idiot);
		
		
		modEdCr.addComponent(module);
		modEdCr.addComponent(create);
		modEdCr.addComponent(aendern);
		
		Window old = starta.getWindow("Startseite");
		old.open(new ExternalResource(modEdCr.getURL()));
	
		
		
	}
	
	//ButtonListener
	public void buttonClick(Button.ClickEvent event){
	
		if(event.getButton() == create){
			int modul = 0;
			contD.ändernModul(modul);
			}
			
	
		if(event.getButton() == aendern){
			String tmp = module.getValue().toString();
			int modul =0;
			for(int i=0; i < liste.size(); i++){
				
				if(tmp.equals(liste.get(i).gettitle())){
					modul = liste.get(i).getid();
					break;
				}
			}
			System.out.println(modul);
			contD.ändernModul(modul);
			}	
	}
	
	//erstellt Listselect-Element mit Modulliste
	public ListSelect openModulList(LinkedList<ModulKu> idiot) {
	
		ListSelect test = new ListSelect();
	
		for(int i=0; i < idiot.size(); i++){
			test.addItem(idiot.get(i).gettitle());			
		}
		
		
	
		test.setNullSelectionAllowed(false);
		return test;
	}
}

