package gui;

import objects.Modul;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Runo;


public class ModulEditCreate extends Startseite implements Button.ClickListener{
	TextArea title,lp,language,turn,/*responsibleid, dozid,*/ doz,filing,requirements,aims,content,lit,events,work,
	exams,formcond,grades, responsible;
	Label label;
	Window createW;
	private AbsoluteLayout mainLayout;
	Modul tmp,in;
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
		in=b;
		buildMainLayout();
		
		/*Label l1 = new Label("Modul bearbeiten");
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
		 
		 responsibleid = new TextArea(":");
		 responsibleid.setValue(String.valueOf(b.getresponsibleid()));
		 createW.addComponent(responsibleid);
		 // direkte umsetzung von name in id wär schön
		 responsible = new TextArea("Dekan:");
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
		 createW.addComponent(save);*/
		createW.setContent(mainLayout);
		 save.addListener(this);
		 logout.addListener(this);
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
		//	String responsibleid1 = (String)responsibleid.getValue();
			String responsible1 = (String)responsible.getValue();
		//  String dozid1 = (String)dozid.getValue();
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
			
			int lp2 = Integer.parseInt(lp1);
		
			
			
			
			
			Modul tmp1 = new Modul(tmp.getid(), title1, lp2, language1, turn1, cont.getID(responsible1), 
							responsible1, cont.getID(doz1), doz1,filing1, requirements1, aims1, 
							content1, lit1, events1, work1, exams1, formcond1, grades1 );
			System.out.println(tmp1.gettitle());
			contD.speichernModul(tmp1);//Methode in Controller-Klasse
		}
		if(event.getButton()==logout){
			starta.getMainWindow().getApplication().close();
		}
	}	
	
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		mainLayout.setWidth("100.0%");
		mainLayout.setHeight("400.0%");
		
		// title
		title = new TextArea();
		title.setCaption("Name:");
		if (in.gettitle()!=null)title.setValue(in.gettitle());
		title.setImmediate(false);
		title.setWidth("66.5%");
		title.setHeight("100px");
		mainLayout.addComponent(title, "top:9.5%;left:25.0%;");
		
		// lp
		lp = new TextArea();
		lp.setCaption("Leistungspunkte:");
		if (in.getlp()!=0)lp.setValue(in.getlp());
		lp.setImmediate(false);
		lp.setWidth("66.5%");
		lp.setHeight("100px");
		mainLayout.addComponent(lp, "top:15.0%;left:25.0%;");
		
		// language
		language = new TextArea();
		language.setCaption("Sprache:");
		if (in.getlanguage()!=null)language.setValue(in.getlanguage());
		language.setImmediate(false);
		language.setWidth("66.5%");
		language.setHeight("100px");
		mainLayout.addComponent(language, "top:20.5%;left:25.0%;");
		
		// turn
		turn = new TextArea();
		turn.setCaption("Rythmus:");
		if (in.getturn()!=null)turn.setValue(in.getturn());
		turn.setImmediate(false);
		turn.setWidth("66.5%");
		turn.setHeight("100px");
		mainLayout.addComponent(turn, "top:26.0%;left:25.0%;");
		
		// responsible
		responsible = new TextArea();
		responsible.setCaption("Dekan:");
		if (in.getresponsible()!=null)responsible.setValue(in.getresponsible());
		responsible.setImmediate(false);
		responsible.setWidth("66.5%");
		responsible.setHeight("100px");
		mainLayout.addComponent(responsible, "top:31.5%;left:25.0%;");
		
		// doz
		doz = new TextArea();
		doz.setCaption("Dozent:");
		if (in.getdoz()!=null)doz.setValue(in.getdoz());
		doz.setImmediate(false);
		doz.setWidth("66.5%");
		doz.setHeight("100px");
		mainLayout.addComponent(doz, "top:37.0%;left:25.0%;");
		
		// filing
		filing = new TextArea();
		filing.setCaption("Einordnung:");
		if (in.getfiling()!=null)filing.setValue(in.getfiling());
		filing.setImmediate(false);
		filing.setWidth("66.5%");
		filing.setHeight("100px");
		mainLayout.addComponent(filing, "top:42.5%;left:25.0%;");
		
		// requirements
		requirements = new TextArea();
		requirements.setCaption("Voraussetzungen:");
		if (in.getrequirements()!=null)requirements.setValue(in.getrequirements());
		requirements.setImmediate(false);
		requirements.setWidth("66.5%");
		requirements.setHeight("100px");
		mainLayout.addComponent(requirements, "top:48.0%;left:25.0%;");
		
		// aims
		aims = new TextArea();
		aims.setCaption("Lernziele");
		if (in.getaims()!=null)aims.setValue(in.getaims());
		aims.setImmediate(false);
		aims.setWidth("66.5%");
		aims.setHeight("100px");
		mainLayout.addComponent(aims, "top:53.5%;left:25.0%;");
		
		// content
		content = new TextArea();
		content.setCaption("Inhalt:");
		if (in.getcontent()!=null)content.setValue(in.getcontent());
		content.setImmediate(false);
		content.setWidth("66.5%");
		content.setHeight("100px");
		mainLayout.addComponent(content, "top:59.0%;left:25.0%;");
		
		// lit
		lit = new TextArea();
		lit.setCaption("Literatur:");
		if (in.getlit()!=null)lit.setValue(in.getlit());
		lit.setImmediate(false);
		lit.setWidth("66.5%");
		lit.setHeight("100px");
		mainLayout.addComponent(lit, "top:64.5%;left:25.0%;");
		
		// events
		events = new TextArea();
		events.setCaption("Lehrveranstaltungen:");
		if (in.getevents()!=null)events.setValue(in.getevents());
		events.setImmediate(false);
		events.setWidth("66.5%");
		events.setHeight("100px");
		mainLayout.addComponent(events, "top:70.0%;left:25.0%;");
		
		// work
		work = new TextArea();
		work.setCaption("Arbeitsaufwand:");
		if (in.getwork()!=null)work.setValue(in.getwork());
		work.setImmediate(false);
		work.setWidth("66.5%");
		work.setHeight("100px");
		mainLayout.addComponent(work, "top:75.5%;left:25.0%;");
		
		// exams
		exams = new TextArea();
		exams.setCaption("Leistungsnachweis:");
		if (in.getexams()!=null)exams.setValue(in.getexams());
		exams.setImmediate(false);
		exams.setWidth("66.5%");
		exams.setHeight("100px");
		mainLayout.addComponent(exams, "top:81.0%;left:25.0%;");
		
		// formcond
		formcond = new TextArea();
		formcond.setCaption("Voraussetzungen formal:");
		if (in.getformcond()!=null)formcond.setValue(in.getformcond());
		formcond.setImmediate(false);
		formcond.setWidth("66.5%");
		formcond.setHeight("100px");
		mainLayout.addComponent(formcond, "top:86.5%;left:25.0%;");
		
		// grades
		grades = new TextArea();
		grades.setCaption("Notenbildung:");
		if (in.getgrades()!=null)grades.setValue(in.getgrades());
		grades.setImmediate(false);
		grades.setWidth("66.5%");
		grades.setHeight("100px");
		mainLayout.addComponent(grades, "top:92.0%;left:25.0%;");
		
		// save
				save = new Button();
				save.setCaption("speichern");
				save.setImmediate(true);
				save.setWidth("-1px");
				save.setHeight("-1px");
				mainLayout.addComponent(save, "top:96.8%;left:25.0%;");
				
				// label
				label = new Label();
				label.setImmediate(false);
				label.setWidth("-1px");
				label.setHeight("-1px");
				label.setValue("Modul bearbeiten");
				label.setStyleName(Runo.LABEL_H1);
				mainLayout.addComponent(label, "top:6.2%;left:25.0%;");
				
				// logout
				logout = new Button();
				logout.setCaption("logout");
				logout.setImmediate(false);
				logout.setWidth("-1px");
				logout.setHeight("-1px");
				logout.setStyleName(BaseTheme.BUTTON_LINK);
				mainLayout.addComponent(logout, "top:98.2%;left:25.0%;");
		
		return mainLayout;
	}
}
