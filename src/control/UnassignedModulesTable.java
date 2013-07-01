package control;

import java.util.LinkedList;

import com.vaadin.ui.Table;

import data.ModulDatabase;

public class UnassignedModulesTable {
	
	ModulDatabase m;
	Table modules;
	
	public Table generateTable() {
		
		m = new ModulDatabase();
		
		modules = new Table("Noch nicht zugeordnete Module");
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
		
		//Trage alle bereits zugeordneten Module in die Tabelle ein
		LinkedList<Integer> idList = m.getNewModules();
		for (int i = 0; i < idList.size(); i++) {
			int x = idList.get(i).intValue();
			modules.addItem(new Object[] {new Integer(x), m.getModulname(x)}, new Integer(i+1));
		}
		
		
		
		
		return modules;
	}
	
	
	
	public static void main(String[] args) {

	}

}
