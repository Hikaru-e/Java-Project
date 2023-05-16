package Metier;

public class Etudiant {

	private String cne_etd;
	private String nom_etd;
	private String prenom_etd;
	private String niveau_etd;
	private String email_etd;
	private String password_etd;
	private String fk_nom_filiere;
	
	
	public Etudiant(String cne_etd, String nom_etd, String prenom_etd, String niveau_etd, String email_etd,
			String password_etd) {
		this.cne_etd = cne_etd;
		this.nom_etd = nom_etd;
		this.prenom_etd = prenom_etd;
		this.niveau_etd = niveau_etd;
		this.email_etd = email_etd;
		this.password_etd = password_etd;
	}
	public Etudiant(String cne_etd, String nom_etd, String prenom_etd, String niveau_etd, String email_etd, String password_etd, String fk_nom_filiere) {
		super();
		this.cne_etd = cne_etd;
		this.nom_etd = nom_etd;
		this.prenom_etd = prenom_etd;
		this.niveau_etd = niveau_etd;
		this.email_etd = email_etd;
		this.password_etd = password_etd;
		this.fk_nom_filiere = fk_nom_filiere;
	}
	public String getCNE_etd() {
		return cne_etd;
	}
	public void setCNE_etd(String CNE) {
		cne_etd = CNE;
	}
	public String getNom() {
		return nom_etd;
	}
	public void setNom(String nom_etd) {
		this.nom_etd = nom_etd;
	}
	public String getPrenom() {
		return prenom_etd;
	}
	public void setPrenom(String prenom) {
		this.prenom_etd = prenom;
	}
	public String getNiveau() {
		return niveau_etd;
	}
	public void setNiveau(String niveau) {
		this.niveau_etd = niveau;
	}
	public String getEmail() {
		return email_etd;
	}
	public void setEmail(String email) {
		this.email_etd = email;
	}
	public String getPassword_etd() {
		return password_etd;
	}
	public void setPassword_etd(String password_etd) {
		this.password_etd = password_etd;
	}

	public String getFk_nom_filiere() {
		return fk_nom_filiere;
	}
	public void setFk_nom_filiere(String fk_nom_filiere) {
		this.fk_nom_filiere = fk_nom_filiere;
	}
	@Override
	public String toString() {
		return "Etudiant [cne_etd=" + cne_etd + ", nom_etd=" + nom_etd + ", prenom_etd=" + prenom_etd + ", niveau_etd="
				+ niveau_etd + ", email_etd=" + email_etd + ", password_etd=" + password_etd + ", fk_nom_filiere="
				+ fk_nom_filiere + "]";
	}
	
	
}
