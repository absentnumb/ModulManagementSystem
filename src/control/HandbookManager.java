package control;

import java.sql.SQLException;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import data.ModulDatabase;
import gui.HandbookManager_View;

public class HandbookManager implements ClickListener, ItemClickListener{
	
	/*
	 * Diese Klasse erlaubt das Erzeugen neuer Fächer, Zuordnen von Modulen
	 * und Löschen von Fächern und Modulen
	*/
	
	HandbookManager_View gui;
	ModulDatabase data;
	int id;
	int selectedId;
	String selectedUnassignedModule;
	String selectedModule;
	
	//Der Konstruktor erwartet die ID des Modulhandbuches, dass angezeigt
	//werden soll
	public HandbookManager(int _id) {
		
		id = _id;
		
		gui = new HandbookManager_View(id);
		gui.delete.setEnabled(false);
		
		data = new ModulDatabase();
		gui.addFach.addListener(this);
		gui.delete.addListener(this);
		gui.moduletree.addListener(this);
		gui.unassignedModules.addListener(this);
		gui.modules.addListener(this);
		gui.add1.addListener(this);
		gui.add2.addListener(this);
		
	}
	
	public void buttonClick(ClickEvent e) {
		//Hinzufügen eines neuen Fachs
		if (e.getSource() == gui.addFach) {
			String s = (String) gui.fachname.getValue();
			System.out.println(s);
			String[] splittedString = s.split(" ");
			//Erzeuge neues Fach, lade Tree bzw. Window neu
			//gui.moduletree = gui.mt.generateModuleTree(id);
			data.newFach(selectedId, s);
						
		}
		if (e.getSource() == gui.add1) {
			
			String s = selectedUnassignedModule;			
			String[] splittedString = s.split(" ");
			int fid = Integer.parseInt(splittedString[0]);
			System.out.println("Modul zugeordnet: " + data.moveNewModule(selectedId, fid));
			
			
		}
		if (e.getSource() == gui.add2) {
			String s = selectedModule;
			String[] splittedString = s.split(" ");
			int fid = Integer.parseInt(splittedString[0]);
			System.out.println("Modul (nochmals) zugeordnet: " + data.moveModule(selectedId, fid));
		}
		if (e.getSource() == gui.delete) {
			//Löschen eines Faches
			System.out.println("Löschvorgang durchgeführt: " + data.deleteFachOrModul(selectedId));
		}
	/*	// TODO Refresh noch ausbessern
			try {
				data.con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}*/
			gui.starta.removeWindow(gui.w);
			HandbookManager t = new HandbookManager(id);		
	}
	
	
	public void itemClick(ItemClickEvent e) {
		//Tree
		if (e.getSource() == gui.moduletree &&
				e.getButton() == ItemClickEvent.BUTTON_LEFT) {
			//Wenn das ausgewählte Fach keine Elemente enthält, ist das Löschen
			//möglich
			if (gui.moduletree.hasChildren(e.getItemId())) {
				gui.delete.setEnabled(false);
			} else {
				gui.delete.setEnabled(true);
			}
			String[] splittedString = ((String) e.getItemId()).split(" ");
			if (Integer.parseInt(splittedString[0]) % 3 != 0) {
				System.out.println("Dies ist ein Fach.");
				//Speichere die ID des im Baum ausgewählten Elementes
				selectedId = Integer.parseInt(splittedString[0]);
				gui.addFach.setEnabled(true);
				gui.fachname.setEnabled(true);
				//Prüfe auch ob ein nicht zugeordnetes Modul markiert ist			
				if (gui.unassignedModules.getValue() != null) {
					gui.add1.setEnabled(true);
				}
				if (gui.modules.getValue() != null) {
					gui.add2.setEnabled(true);
				}
			} else {
				System.out.println("Dies ist ein Modul.");
				selectedId = Integer.parseInt(splittedString[0]);
				gui.addFach.setEnabled(false);
				gui.fachname.setEnabled(false);
				
				gui.add1.setEnabled(false);
				gui.add2.setEnabled(false);
				
	
			}
			gui.choice.setValue("Ausgewähltes Element: Fach " + splittedString[2]);
			
			//Table
		} else if (e.getSource() == gui.unassignedModules) {
			selectedUnassignedModule = e.getItem().toString();
			System.out.println(selectedUnassignedModule);
			//if (gui.fachname2.isEnabled() == true && gui.modules.getValue() != null) {}
			if (gui.fachname.isEnabled() == true) {
				gui.add1.setEnabled(true);
			} else {
				gui.add1.setEnabled(false);
			}
		} else if (e.getSource() == gui.modules) {
			selectedModule = e.getItem().toString();
			System.out.println(selectedModule);
			if (gui.fachname.isEnabled() == true) {
				gui.add2.setEnabled(true);
			} else {
				gui.add2.setEnabled(false);
			}
		}
		
	}
	
	



}
