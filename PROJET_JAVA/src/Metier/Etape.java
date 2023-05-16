package Metier;

import java.sql.Blob;
import java.sql.Date;


public class Etape {
	private String id_etape, duree_etape, documentation;
	private String livraison;
	private Date etape_debut;

	public Etape() {
		super();
	}

	public Etape(String id_etape, String duree_etape, Date etape_debut, String livraison, String documentation) {
		this.id_etape = id_etape;
		this.duree_etape = duree_etape;
		this.livraison = livraison;
		this.documentation = documentation;
		this.etape_debut = etape_debut;
	}

	public String getId_etape() {
		return id_etape;
	}

	public void setId_etape(String id_etape) {
		this.id_etape = id_etape;
	}

	public String getDuree_etape() {
		return duree_etape;
	}

	public void setDuree_etape(String duree_etape) {
		this.duree_etape = duree_etape;
	}

	public String getLivraison() {
		return livraison;
	}

	public void setLivraison(String livraison) {
		this.livraison = livraison;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	public Date getEtape_debut() {
		return etape_debut;
	}

	public void setEtape_debut(Date etape_debut) {
		this.etape_debut = etape_debut;
	}

	@Override
	public String toString() {
		return "Etape [id_etape=" + id_etape + ", duree_etape=" + duree_etape + ", livraison=" + livraison
				+ ", documentation=" + documentation + ", etape_debut=" + etape_debut + "]";
	}



}
