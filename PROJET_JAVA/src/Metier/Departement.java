package Metier;

public class Departement {
	
	private String id_dprt;
	private String nom_dprt;
	private String email_dprt;
	
	public Departement(String id_dprt, String nom_dprt, String email_dprt) {
		super();
		this.id_dprt = id_dprt;
		this.nom_dprt = nom_dprt;
		this.email_dprt = email_dprt;
	}

	public String getID() {
		return id_dprt;
	}

	public void setID(String iD) {
		id_dprt = iD;
	}

	public String getNom() {
		return nom_dprt;
	}

	public void setNom(String nom) {
		this.nom_dprt = nom;
	}

	public String getEmail() {
		return email_dprt;
	}

	public void setEmail(String email) {
		this.email_dprt = email;
	}

	@Override
	public String toString() {
		return "Departement [id_dprt=" + id_dprt + ", nom_dprt=" + nom_dprt + ", email_dprt=" + email_dprt + "]";
	}
	
	
	
	
}
