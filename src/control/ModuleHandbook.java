package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.vaadin.terminal.FileResource;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;


import objects.Modul;


import gui.LoginApplication;
import data.BookData;
import data.ModulDatabase;



public class ModuleHandbook{
	
	/*Die Klasse Modulehandbook stellt folgende Methoden zur Verfügung:
	 * 
	 *generatePDF:
	 *Erwartet die ID des gewünschten Modulhandbuches als
	 *Übergabeparameter, erzeugt die PDF und gibt eine FileResource zurück.
	 *
	 *generateAllPDF:
	 *Erzeugt PDF zu allen Modulhandbüchern und gibt eine Liste mit
	 *FileResources zurück.
	 *
	 *deleteTempFiles:
	 *Löscht alle von "generatePDF" erzeugten Dateien.
	 */
	
	private String s = "";
	private String b = "";
	//Zeilenumbruch \r\n
	final private String lineSeparator = System.getProperty("line.separator");
	
	private ModulDatabase modulDatabase = new ModulDatabase();
	
	private String path;
	
	private String operatingSystem = "";
	
	/*Die Methode "generatePDF" erzeugt das Modulhandbuch als PDF. Durch
	 *  rekursivem Aufruf der Methode "createLatexCode" wird die Struktur
	 *  aus Fächern und Modulen durchlaufen (Fächer können wiederum
	 *  Fächer oder Module enthalten). Der Inhalt des Modulhandbuches wird
	 *  zunächst als LaTeX-Code in eine TeX-Datei geschrieben. Aus dieser
	 *  Datei erstellt pdfLaTeX die geforderte PDF. Letztendlich gibt die Methode
	 *  einen Downloadlink zurück.
	 *  
	 *  Es muss pdfLaTeX vorhanden sein, sodass in der command-line pdflatex
	 *  aufgerufen werden kann. MiKTeX oder TeX Live enthalten pdfLaTeX.
	 */
	public FileResource generatePDF(int rootID, LoginApplication la) {
		String modulhandbuchname = modulDatabase.getFachname(rootID);
		
		//Es wird der String semester erzeugt, der das aktuelle Semester
		//(SS oder WS) und Jahr (zweistellig) angibt
		String semester = "";
		DateFormat df = new SimpleDateFormat("MM");
		Calendar x = Calendar.getInstance();
		int month = Integer.parseInt(df.format(x.getTime()));
		if (month > 3 && month < 11) {
			semester = "SS";
		} else {
			semester = "WS";
		}
		df = new SimpleDateFormat("yyyy");
		x = Calendar.getInstance();
		int year = Integer.parseInt(df.format(x.getTime()));
		semester = semester + year;
		
		//s = S;
		s = "\\documentclass{article}" + lineSeparator +
				"\\begin{document} " + lineSeparator +
				"\\title{Modulhandbuch "+modulhandbuchname+" "+semester+"}" + lineSeparator +
				"\\author{Module Management System, University of Ulm}" + lineSeparator +
				"\\maketitle" + lineSeparator +
				"\\begin{abstract}" + lineSeparator +
				"\\end{abstract}" + lineSeparator;
		b = "";
		int actualID = 0;
		actualID = rootID;
		int counter = 0;
		
		createLatexCode(actualID, counter);		
		
		s = s + "\\end{document}";
		//System.out.println(s);
		
		//Schreibe den String s in eine Textdatei
		/*
		//Erzeuge für die Datei eine ID 
		String dataID = "";
		int n = 0;
		for (int i = 0; i < 10; i++) {
			n = (int) (Math.random() * 100) % 10;
			dataID = dataID + n;
		}
		*/
		try {
			//https://vaadin.com/book/-/page/application.resources.html
			path = la.start.getContext().getBaseDirectory().getAbsolutePath();
			System.out.println(path);
			//PrintWriter pw = new PrintWriter(path + "\\Modulhandbuch"+dataID+".tex");
			PrintWriter pw = new PrintWriter(path + "/Modulhandbuch"+modulhandbuchname+semester+".tex");
			//PrintWriter pw = new PrintWriter("Modulhandbuch.tex");
			pw.println(s);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Verwende MiKTeX zu PDF-Generierung
		//run("cmd /c pdflatex.exe  " + path + "\\Modulhandbuch"+dataID+".tex");
	
		run("pdflatex Modulhandbuch"+modulhandbuchname+semester+".tex" +
				" -output-directory=", path);
		//run("cmd /c Modulhandbuch"+dataID+".pdf");
		//FileResource fr = new FileResource(new File( path + "Modulhandbuch"+dataID+".pdf"), null);
		
		File f1 = new File(path + "/Modulhandbuch"+modulhandbuchname+semester+".pdf");
		//Schreibe die Pfade der temporär erzeugten Dateien in eine Textdatei
		//um diese anhand dieser wieder mit "deleteTempFiles" löschen zu können
		File f2 = new File(path + "/tempFiles.txt");
		try {
			FileWriter pw  = new FileWriter(path + "/tempFiles.txt", true);
			pw.write(path + "/Modulhandbuch"+modulhandbuchname+semester+".pdf" + lineSeparator);
			pw.write(path + "/Modulhandbuch"+modulhandbuchname+semester+".tex" + lineSeparator);
			pw.write(path + "/Modulhandbuch"+modulhandbuchname+semester+".aux" + lineSeparator);
			pw.write(path + "/Modulhandbuch"+modulhandbuchname+semester+".log" + lineSeparator);
			pw.close();	
		} catch (IOException e) {
			e.printStackTrace();			
		}
		
		
		FileResource fr = new FileResource(f1, la);
		
		//Link l = new Link("Download the Modulehandbook here", fr);
		//Link l = new Link("Download the Modulehandbook here", fr, "Download", 0, 0, Link.TARGET_BORDER_DEFAULT);
			
		return fr;
	}
		
	//Methode zu Erzeugung des Modulhandbuches in Form von LateX-Code
	//-------------
	//Anmerkungen:
	//Die Methode wird mit der ID der Wurzel initialisiert
	//Die Methode bearbeitet den String s
	private void createLatexCode (int actualID, int counter) {
		BookData bd = new BookData();
		LinkedList<Integer> next = bd.listeFaecher(actualID);
			
	//Sortierung nach Fächern und Modulen
		LinkedList<Integer> nextFach = new LinkedList<Integer>();
		LinkedList<Integer> module = new LinkedList<Integer>();
		for (int n = 0 ; n < next.size(); n++) {
			if (next.get(n).intValue() % 3 != 0) {	
				nextFach.add(next.get(n));
			} else {
				module.add(next.get(n));
			}
		}
		
		//Rekursiver Aufbau des LaTeX-Strings
		String fachname = "fachname";
		String modulname = "modulname";
		String modulbeschreibung = "modulbeschreibung";
		for (int i = 0; i < nextFach.size(); i++) {
			b =  "";
			for (int j = 0; j < counter; j ++) {
				b = b + "sub";
			}
			fachname = modulDatabase.getFachname(nextFach.get(i).intValue());
			s = s +" \\" + b + "section{"+fachname+"}" + lineSeparator;
			createLatexCode(nextFach.get(i).intValue(), counter + 1);
		}
		
		for (int i = 0; i < module.size(); i++) {
			b =  "";
			for (int j = 0; j < counter; j++) {
				b = b + "sub";
			}
			modulname = modulDatabase.getModulname(module.get(i).intValue());
			Modul modul = modulDatabase.loadModule(module.get(i).intValue());
			modulbeschreibung = "Leistungspunkte: "+modul.getlp()+"\\newline"+lineSeparator+
					"Sprache: "+modul.getlanguage()+"\\newline"+lineSeparator+
					"Turnus/Dauer: "+modul.getturn()+"\\newline"+lineSeparator+
					"Modulverantwortlicher: "+modul.getresponsible()+"\\newline"+lineSeparator+
					"Dozenten: "+modul.getdoz()+"\\newline"+lineSeparator+
					"Einordnung des Moduls in Studiengänge: "+modul.getfiling()+"\\newline"+lineSeparator+
					"Voraussetzungen (inhaltlich): "+modul.getrequirements()+"\\newline"+lineSeparator+
					"Lernziele: "+modul.getaims()+"\\newline"+lineSeparator+
					"Inhalt: "+modul.getcontent()+"\\newline"+lineSeparator+
					"Literatur: "+modul.getlit()+"\\newline"+lineSeparator+
					"Lehrveranstaltungen und Lehrformen: "+modul.getevents()+"\\newline"+lineSeparator+
					"Abschätzung des Arbeitsaufwands: "+modul.getwork()+"\\newline"+lineSeparator+
					"Leistungsnachweis und Prüfungen: "+modul.getexams()+"\\newline"+lineSeparator+
					"Voraussetzungen (formal): "+modul.getformcond()+"\\newline"+lineSeparator+
					"Notenbildung: "+modul.getgrades()+"\\newline"+lineSeparator;
			s = s +" \\" + b + "section{"+modulname+"}"+modulbeschreibung+ lineSeparator;
		}
		
	}
	
	//Führt einen Befehl über die command-line aus 
	private void run(String command, String path) {
		try {
			Runtime rt = null;
			Process p = null;
			if (detectOperatingSystem().equals("Windows")) {
				rt = Runtime.getRuntime();
				p = rt.exec(command+"\""+path+"\"",null);
			} else if (detectOperatingSystem().equals("Linux")) {
				rt = Runtime.getRuntime();
				p = rt.exec(command,null, new File(path));
			} else if (detectOperatingSystem().equals("Unknown Operating System")) {
				System.out.println("PDF not created. The Operating System is unknown.");
			}
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = "";
			while ((s = br.readLine()) != null) {
				System.out.println(s);			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Methode zur Löschung aller durch die Methode "generatePDF" erzeugten Dateien.
	//Es wird die von generatePDF bechriebene Datei tempFiles.txt verwendet
	public void deleteTempFiles() {
		try {
			Scanner scanner = new Scanner(new FileReader (path + "\\tempFiles.txt"));
			String string;
			File file;
			boolean deleted = false;
			while (scanner.hasNext()) {
				string = scanner.nextLine();
				file = new File(string);
				deleted = file.delete();
				if (deleted) {
					System.out.println(file.getPath() + " was deleted.");
				} else {
					System.out.println(file.getPath() + " was not deleted.");
				}
			}
			scanner.close();
			file = new File(path + "\\tempFiles.txt");
			deleted = file.delete();
			if (deleted) {
				System.out.println(file.getPath() + " was deleted.");
			} else {
				System.out.println(file.getPath() + " was not deleted.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//Erzeugt eine LinkedList mit allen PDF
	//Kann z.B. für Links verwendet werden
	// TODO Test
	public LinkedList<FileResource> generateAllPDF(LoginApplication la) {
		LinkedList<FileResource> files = new LinkedList<FileResource>();
		FileResource f;			
		
		LinkedList<Integer> idList = modulDatabase.getModuleHandbookIDs();
		int id;
		
		
		for (int i = 0; i < idList.size(); i++) {
			id = idList.get(i).intValue();
			f = generatePDF(id, la);
			files.add(f);
		}
		return files;		
	}
	
	//Ermittle das verwendete Betriebssystem (erkennt Windows oder Linux)
	private String detectOperatingSystem() {
		String os = "";
		
		String name = System.getProperty("os.name").toLowerCase();
		if (name.indexOf("windows") >= 0) {
			os = "Windows";
		} else if (name.indexOf("linux") >=0) {
			os = "Linux";
		} else {
			os = "Unknown Oprating System";
		}
		
		return os;
	}
	
	

	
	public static void main(String[] args) {
		
		
	}

}
