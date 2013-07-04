package objects;

public class Modul extends ModulKu {
	

	private int lp;
	private String language;
	private String turn;
	private int responsibleid;
	private String responsible;	
	private int dozid;
	private String doz;
	private String filing;
	private String requirements;
	private String aims;
	private String content;
	private String lit;
	private String events;
	private String work;
	private String exams;
	private String formcond;
	private String grades;
	
	//erstellt Modulobjekt mit allen nötigen Attributen
	public Modul(int conid,  String contitle, int conlp, String conlanguage,
				 String conturn, int conresponsibleid, String conresponsible, int condozid,
				 String condoz, String confiling, String conrequirements,
				 String conaims, String concontent, String conlit, String conevents,
				 String conwork, String conexams, String conformcond, String congrades){
		super(conid,contitle);
		lp = conlp;
		language = conlanguage;
		turn = conturn;
		responsibleid = conresponsibleid;
		responsible = conresponsible;	
		dozid = condozid;
		doz = condoz;
		filing = confiling;
		requirements = conrequirements;
		aims = conaims;
		content = concontent;
		lit = conlit;
		events = conevents;
		work = conwork;
		exams = conexams;
		formcond = conformcond;
		grades = congrades;
	}
	

	
	public int getlp(){
		return lp;
	}
	
	public String getlanguage(){
		return language;
	}
	
	public String getturn(){
		return turn;
	}
	
	public int getresponsibleid(){
		return responsibleid;
	}
	
	public String getresponsible(){
		return responsible;
	}
	public int getdozid(){
		
	return dozid;	
		
	}
	public String getdoz(){
		return doz;
	}
	
	public String getfiling(){
		return filing;
	}
	
	public String getrequirements(){
		return requirements;
	}
	
	public String getaims(){
		return aims;
	}
	
	public String getcontent(){
		return content;
	}
	
	public String getlit(){
		return lit;
	}
	
	public String getevents(){
		return events;
	}
	
	public String getwork(){
		return work;
	}
	
	public String getexams(){
		return exams;
	}
	
	public String getformcond(){
		return formcond;
	}
	
	public String getgrades(){
		return grades;
	}
	
}
