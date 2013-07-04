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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

import control.ModuleTable;
import control.UnassignedModulesTable;
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
	
	public Panel tablePanel1;
	public Panel tablePanel11;
	public Panel tablePanel12;
	
	public UnassignedModulesTable umta;
	public Table unassignedModules;
	public Button add1;
	
	public ModuleTable mta;
	public Table modules;
	public Button add2;
	
	
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
		
		
		tablePanel1 = new Panel();
		tablePanel1.setContent(new HorizontalLayout());
		
		tablePanel11 = new Panel();
		tablePanel11.setContent(new VerticalLayout());
		
		umta = new UnassignedModulesTable();
		unassignedModules = umta.generateTable();
		tablePanel11.addComponent(unassignedModules);
		
		add1 = new Button("Modul dem ausgewählten Fach hinzufügen");
		add1.setEnabled(false);
		tablePanel11.addComponent(add1);
		
		tablePanel1.addComponent(tablePanel11);
		

		tablePanel12 = new Panel();
		tablePanel12.setContent(new VerticalLayout());
		
		mta = new ModuleTable(userid);
		System.out.println("User-ID:" + userid);
		modules = mta.generateTable();
		tablePanel12.addComponent(modules);
		
		add2 = new Button("Modul dem ausgewählten Fach hinzufügen");
		add2.setEnabled(false);
		tablePanel12.addComponent(add2);
		
		tablePanel1.addComponent(tablePanel12);
		
		w.addComponent(tablePanel1);
		
				
		Window old = starta.getWindow("M");
		old.open(new ExternalResource(w.getURL()));
		
	}

}
