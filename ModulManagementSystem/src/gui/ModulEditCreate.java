package gui;

import objects.Modul;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;


public class ModulEditCreate extends Startseite implements Button.ClickListener{
	TextArea title,lp,language,turn,responsibleid, dozid, doz,filing,requirements,aims,content,lit,events,work,
	exams,formcond,grades, responsible;
	Window createW;
	Modul tmp;
	private Button save;
	
	//gibt vorhandene Werte in Textboxen zur Bearbeitung aus
	public ModulEditCreate(Modul b){
		Window test = starta.getWindow("Modulaendern");
		if(test != null){
			starta.removeWindow(test);	
		}
		createW = new Window("");
		createW.setName("Modulaendern");
		
		starta.addWindow(createW);
		Label l1 = new Label("Modul bearbeiten");
		createW.addComponent(l1); 
		title = new TextArea("Name:");
		title.setValue(b.gettitle());
		 createW.addComponent(title);
		 
		 lp = new TextArea("Leistungspunkte:");
		 lp.setValue(String.valueOf(b.getlp()));
		 createW.addComponent(lp);
		 
		 language = new TextArea("Sprache:");
		 language.setValue(b.getlanguage());
		 createW.addComponent(language);
		 
		 turn= new TextArea("Rytmus:");
		 turn.setValue(b.getturn());
		 createW.addComponent(turn);
		 
		 responsibleid = new TextArea("Verantwortlicher:");
		 responsibleid.setValue(String.valueOf(b.getresponsibleid()));
		 createW.addComponent(responsibleid);
		 // direkte umsetzung von name in id wer schön
		 responsible = new TextArea();
		 responsible.setValue(b.getresponsible());
		 createW.addComponent(responsible);
		 
		 dozid = new TextArea("");
		 dozid.setValue(String.valueOf(b.getdozid()));
		 createW.addComponent(dozid);
		 
		 doz = new TextArea("Dozent:");
		 doz.setValue(b.getdoz());
		 createW.addComponent(doz); 
		 
		filing = new TextArea("Einordnung:");
		filing.setValue(b.getfiling());
		 createW.addComponent(filing);
		 
		 requirements = new TextArea("Verausetzungen:");
		 requirements.setValue(b.getrequirements());
		  createW.addComponent(requirements);
		  
		 aims = new TextArea("Lernziele");
		 aims.setValue(b.getaims());
		  createW.addComponent(aims);
		  
		 content = new TextArea("Inhalt:");
		 content.setValue(b.getcontent());
		 createW.addComponent(content);
		 
		 lit = new TextArea("Literatur:");
		 lit.setValue(b.getlit());
		 createW.addComponent(lit);
		 
		 events = new TextArea("Lehreveranstaltungen:");
		 events.setValue(b.getevents());
		 createW.addComponent(events);
		 
		 work = new TextArea("Arbeitsaufwand:");
		 work.setValue(b.getwork());
		 createW.addComponent(work);
		 
		 exams = new TextArea("Leistungsnachweis:");
		 exams.setValue(b.getexams());
		 createW.addComponent(exams);
		 
		 formcond = new TextArea("Voraussetzungen formal:");
		 formcond.setValue(b.getformcond());
		 createW.addComponent(formcond);
		 
		 grades = new TextArea("Notenbildung:");
		 grades.setValue(b.getgrades());
		 createW.addComponent(grades);
		 
		 save = new Button("speichern");
		 createW.addComponent(save);
		 save.addListener(this);
		 tmp = b;
		 Window old = starta.getWindow("Modulbearbeiten");
			
		 old.open(new ExternalResource(createW.getURL()));
			//starta.removeWindow(old);	

		 
	}

	//ButtonListener
	public void buttonClick(Button.ClickEvent event){
		if (event.getButton() == save){
			String title1 = (String)title.getValue();
			String lp1 = (String)lp.getValue();
			String language1 = (String)language.getValue();
			String turn1 = (String)turn.getValue();
			String responsibleid1 = (String)responsibleid.getValue();
			String responsible1 = (String)responsible.getValue();
			String dozid1 = (String)dozid.getValue();
			String doz1 = (String)doz.getValue();
			String filing1 = (String)filing.getValue();
			String requirements1 = (String)requirements.getValue();
			String aims1 = (String)aims.getValue();
			String content1 = (String)content.getValue();
			String lit1 = (String)lit.getValue();
			String events1 = (String)events.getValue();
			String work1 = (String)work.getValue();
			String exams1 = (String)exams.getValue();
			String formcond1 = (String)formcond.getValue();
			String grades1 = (String)grades.getValue();
			
			int dozid2 = Integer.parseInt(dozid1);
			int lp2 = Integer.parseInt(lp1);
			int responsibleid2 = Integer.parseInt(responsibleid1);
			
			
			
			
			Modul tmp1 = new Modul(tmp.getid(), title1, lp2, language1, turn1, responsibleid2, 
							responsible1, dozid2, doz1,filing1, requirements1, aims1, 
							content1, lit1, events1, work1, exams1, formcond1, grades1 );
			System.out.println(tmp1.gettitle());
			contD.speichernModul(tmp1);//Methode in Controller-Klasse
		}
	}	
}
