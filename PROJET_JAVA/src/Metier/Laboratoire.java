package Metier;

public class Laboratoire {
	
	private String id_lab;
	private String nom_lab;
	private String email_lab;
	
	public Laboratoire(String id_lab, String nom_lab, String email_lab) {
		super();
		this.id_lab = id_lab;
		this.nom_lab = nom_lab;
		this.email_lab = email_lab;
	}
	
	public Laboratoire() {
	}

	public String getId_lab() {
		return id_lab;
	}

	public void setId_lab(String id_lab) {
		this.id_lab = id_lab;
	}

	public String getNom_lab() {
		return nom_lab;
	}

	public void setNom_lab(String nom_lab) {
		this.nom_lab = nom_lab;
	}

	public String getEmail_lab() {
		return email_lab;
	}

	public void setEmail_lab(String email_lab) {
		this.email_lab = email_lab;
	}

	@Override
	public String toString() {
		return "Laboratoire [id_lab=" + id_lab + ", nom_lab=" + nom_lab + ", email_lab=" + email_lab + "]";
	}
	
	
}
