package gui;

import objects.Modul;
import objects.Nachricht;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;


public class RequestView extends Startseite implements Button.ClickListener {
	
	Label title,lp,language,turn,responsibleid,doz,filing,requirements,aims,content,lit,events,work,		//Titellabels
	exams,formcond,grades, responsible;
	
	Label titlecon, lpcon, languagecon, turncon, responsibleidcon,dozcon, filingcon, requirementscon, 		//Inhaltslabels
	aimscon, contentcon, litcon, eventscon, workcon, examscon, formcondcon, gradescon, responsiblecon;
	
	private Button confirm, deny;
	Window Requw;
	Modul tmp;
	Nachricht tmp2;
	
	//schreibt alle Werte In Labels
	public RequestView(Modul b, Nachricht d){
		Window test = starta.getWindow("Aenderungsantrag");
		if(test != null){
			starta.removeWindow(test);	
		}
		
		Requw = new Window("");
		Requw.setName("Aenderungsantrag");

		starta.addWindow(Requw);
		
		title = new Label("Titel");
		Requw.addComponent(title);
		titlecon = new Label(b.gettitle());
		Requw.addComponent(titlecon);
		
		lp = new Label("Leistungspunkte");
		Requw.addComponent(lp);
		lpcon = new Label(String.valueOf(b.getlp()));
		Requw.addComponent(lpcon);
		
		language = new Label("Sprache");
		Requw.addComponent(language);
		languagecon = new Label(b.getlanguage());
		Requw.addComponent(languagecon);
		
		turn = new Label("Turnus");
		Requw.addComponent(turn);
		turncon = new Label(b.getturn());
		Requw.addComponent(turncon);
		
		responsibleid = new Label("ID des Verantwortlichen");
		Requw.addComponent(responsibleid);
		responsibleidcon = new Label(String.valueOf(b.getresponsibleid()));
		Requw.addComponent(responsibleidcon);
		
		doz = new Label("Dozent");
		Requw.addComponent(doz);
		dozcon = new Label(b.getdoz());
		Requw.addComponent(dozcon);
		
		filing = new Label("Filing");
		Requw.addComponent(filing);
		filingcon = new Label(b.getfiling());
		Requw.addComponent(filingcon);
		
		requirements = new Label("Voraussetzungen");
		Requw.addComponent(requirements);
		requirementscon = new Label(b.getrequirements());
		Requw.addComponent(requirementscon);
		
		aims = new Label("Lernziele");
		Requw.addComponent(aims);
		aimscon = new Label(b.getaims());
		Requw.addComponent(aimscon);
		
		content = new Label("Inhalt");
		Requw.addComponent(content);
		contentcon = new Label(b.getcontent());
		Requw.addComponent(contentcon);
		
		lit = new Label("Literatur");
		Requw.addComponent(lit);
		litcon = new Label(b.getlit());
		Requw.addComponent(litcon);
		
		events = new Label("Veranstaltungen");
		Requw.addComponent(events);
		eventscon = new Label(b.getevents());
		Requw.addComponent(eventscon);
		
		work = new Label("Arbeitsaufwand");
		Requw.addComponent(work);
		workcon = new Label(b.getwork());
		Requw.addComponent(workcon);
		
		exams = new Label("Klausuren");
		Requw.addComponent(exams);
		examscon = new Label(b.getexams());
		Requw.addComponent(examscon);
		
		formcond = new Label("Formale Anforderungen");
		Requw.addComponent(formcond);
		formcondcon = new Label(b.getformcond());
		Requw.addComponent(formcondcon);
		
		grades = new Label("Benotung");
		Requw.addComponent(grades);
		gradescon = new Label(b.getgrades());
		Requw.addComponent(gradescon);
		
		responsible = new Label("Verantwortliche/r");
		Requw.addComponent(responsible);
		responsiblecon = new Label(b.getresponsible());
		Requw.addComponent(responsiblecon);
		
		confirm = new Button("bestätigen");
		Requw.addComponent(confirm);
		confirm.addListener(this);
		deny = new Button("verwerfen");
		Requw.addComponent(deny);
		deny.addListener(this);
		
		tmp = b;
		tmp2 = d;
		Window old = starta.getWindow("Aenderungsantraege");
		old.open(new ExternalResource(Requw.getURL()));
		//starta.removeWindow(old);	

	}
	
	//ButtonListener
	public void buttonClick(Button.ClickEvent event){
		if (event.getButton() == confirm){
			contDek.okRequest(tmp, tmp2, true);
		}
		if (event.getButton() == deny){
			contDek.okRequest(tmp, tmp2,false);
		}
	}

}
