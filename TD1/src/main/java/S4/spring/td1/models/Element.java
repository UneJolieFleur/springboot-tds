package S4.spring.td1.models;

public class Element {
	
	public String nom;
	public int evaluation;
	
	public Element(String nom, int evaluation) {
		
		this.nom = nom;
		this.evaluation = evaluation;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
	
}
