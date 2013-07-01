package objects;

public class Deadline {

	private int id;
	private String datum;
	private String pdf;   
	
	
	
	public Deadline(int id, String datum, String pdf){
		this.id = id;
		this.datum = datum;
		this.pdf = pdf;
		}

	public int getid(){
		return id;	
	}
	public String getdatum(){
		return datum;
	}
	public String getpdf(){	
		return pdf;
	} 
	
}
