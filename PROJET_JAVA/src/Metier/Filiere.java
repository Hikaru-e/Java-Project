package Metier;

public class Filiere {
	String nom_filiere;
	String fk_id_dprt;

	public Filiere() {
		super();
	}

	
	public Filiere(String nom_filiere) {
		this.nom_filiere = nom_filiere;
	}


	public Filiere(String nom_filiere, String fk_id_dprt) {
		super();
		this.nom_filiere = nom_filiere;
		this.fk_id_dprt = fk_id_dprt;
	}

	public String getNom_filiere() {
		return this.nom_filiere;
		
	}

	public void setNom_filiere(String nom_filiere) {
		this.nom_filiere = nom_filiere;
	}
	
	public String getFk_id_dprt() {
		return fk_id_dprt;
	}

	public void setFk_id_dprt(String fk_id_dprt) {
		this.fk_id_dprt = fk_id_dprt;
	}

	@Override
	public String toString() {
		return "Filiere [nom_filiere=" + nom_filiere + ", fk_id_dprt=" + fk_id_dprt + "]";
	}
	
	

}
