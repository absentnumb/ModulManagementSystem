package control;

import java.io.File;
import java.util.LinkedList;

import com.vaadin.terminal.FileResource;

import data.Archive;

import gui.LoginApplication;

public class SaveHandbook {
	
	Archive ar = new Archive();
	ModuleHandbook tmp = new ModuleHandbook();
	
	public void archive(LinkedList<Integer> arr, String time){
		LoginApplication test = new LoginApplication();
		
		for(int i=0; i<arr.size(); i++){
			
			int local = arr.get(i);
			FileResource file = tmp.generatePDF(local, test);
			File f = file.getSourceFile();
			String path = f.getAbsolutePath();
			ar.saveFile(local, path, time);
		}
		
	}

}
