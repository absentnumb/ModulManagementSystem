package gui;

import java.util.LinkedList;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import control.ModuleTable;
import control.ModuleTree;

import data.BookData;
import data.ModulDatabase;

public class HandbookManager_View extends Startseite {
	
	public Window w;
	
	public Panel p;
	public TextField fachname;
	public Button addFach;
	
	public Button delete;
	
	
	public Tree moduletree;
	public ModuleTree mt;
	public Label choice;
	
	public ModuleTable mta;
	public Table modules;
	public Button add;
	
	
	public HandbookManager_View (int id) {
		
		mt = new ModuleTree();
		
		w = new Window("");
		w.setName("Fach neu anlegen, Module zuordnen");
		starta.addWindow(w);
		
		moduletree = mt.generateModuleTree(id);
		w.addComponent(moduletree);
		
		w.addComponent(new Label("-"));
		
		choice = new Label("Ausgewähltes Element: -");
		w.addComponent(choice);
		
		p = new Panel();
		p.setContent(new HorizontalLayout());
		fachname = new TextField();
		fachname.setEnabled(false);
		p.addComponent(fachname);
		addFach = new Button("Neues Fach");
		addFach.setEnabled(false);
		p.addComponent(addFach);
		delete = new Button("Löschen");
		p.addComponent(delete);
		w.addComponent(p);
		
		w.addComponent(new Label("-"));
		
		mta = new ModuleTable();
		modules = mta.generateTable();
		w.addComponent(modules);
		
		add = new Button("Modul dem ausgewählten Fach hinzufügen");
		add.setEnabled(false);
		w.addComponent(add);
		
				
		Window old = starta.getWindow("Startseite");
		old.open(new ExternalResource(w.getURL()));
		
	}

}
