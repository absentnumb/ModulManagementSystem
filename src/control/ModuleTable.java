package control;

import java.util.LinkedList;

import objects.ModulKu;

import com.vaadin.ui.Table;

import data.ModulDatabase;

public class ModuleTable {
	
	ModulDatabase m;
	Table modules;
	
	//TODO Konstrukor erstellen, der dekId erwartet
	int dekId;
	
	public ModuleTable(int _dekId) {
		dekId = _dekId;
	}
	
	public Table generateTable() {
		
		m = new ModulDatabase();
		
		modules = new Table("Bereits zugeordnete Module");
		//modules.setSizeFull();
		modules.setSelectable(true);
		modules.setMultiSelect(false);
		modules.setPageLength(10);
		modules.setImmediate(true);
		modules.setNullSelectionAllowed(false);
		modules.addContainerProperty("ID", Integer.class, null);
		modules.addContainerProperty("Titel", String.class, null);
		
		//TODO Noch nicht zugeordnete Module einlesen
		//modules.addItem(new Object[] {new Integer(45), "Test"}, new Integer(1));
		
		//Trage alle nicht zugeordneten Module in die Tabelle ein
		LinkedList<ModulKu> list = m.loadModuleListDek(dekId);
		for (int i = 0; i < list.size(); i++) {
			int x = list.get(i).getid();
			modules.addItem(new Object[] {new Integer(x), m.getModulname(x)}, new Integer(i+1));
		}
		
		
		
		
		return modules;
	}
	
	
	
	public static void main(String[] args) {

	}

}
