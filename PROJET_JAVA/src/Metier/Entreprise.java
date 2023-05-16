package Metier;

public class Entreprise {
	private String id_ent;
	private String raisone_sociale;
	private String adresse_ent;
	private String tele_ent;
	private String email_ent;
	private String responsable_ent;
	
	public Entreprise(String id_ent, String raisone_sociale, String adresse_ent, String tele_ent, String email_ent,
			String responsable_ent) {
		super();
		this.id_ent = id_ent;
		this.raisone_sociale = raisone_sociale;
		this.adresse_ent = adresse_ent;
		this.tele_ent = tele_ent;
		this.email_ent = email_ent;
		this.responsable_ent = responsable_ent;
	}
	
	public Entreprise() {
	}

	public String getId_ent() {
		return id_ent;
	}

	public void setId_ent(String id_ent) {
		this.id_ent = id_ent;
	}

	public String getRaisone_sociale() {
		return raisone_sociale;
	}

	public void setRaisone_sociale(String raisone_sociale) {
		this.raisone_sociale = raisone_sociale;
	}

	public String getAdresse_ent() {
		return adresse_ent;
	}

	public void setAdresse_ent(String adresse_ent) {
		this.adresse_ent = adresse_ent;
	}

	public String getTele_ent() {
		return tele_ent;
	}

	public void setTele_ent(String tele_ent) {
		this.tele_ent = tele_ent;
	}

	public String getEmail_ent() {
		return email_ent;
	}

	public void setEmail_ent(String email_ent) {
		this.email_ent = email_ent;
	}

	public String getResponsable_ent() {
		return responsable_ent;
	}

	public void setResponsable_ent(String responsable_ent) {
		this.responsable_ent = responsable_ent;
	}

	@Override
	public String toString() {
		return "Entreprise [id_ent=" + id_ent + ", raisone_sociale=" + raisone_sociale + ", adresse_ent=" + adresse_ent
				+ ", tele_ent=" + tele_ent + ", email_ent=" + email_ent + ", responsable_ent=" + responsable_ent + "]";
	}
	
}
	
	