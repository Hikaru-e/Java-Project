package Metier;

import java.sql.Date;

import javax.swing.JOptionPane;

enum type {
	PFA, PFE, Doctorat
}

public class Projet {
	private String id_prj, lieu_prj, titre_prj, fk_id_prf, fk_id_lab, fk_id_ent, fk_cne_etd;
	private Date date_depart;
	private Float duree_prj;
	private type type_prj;

	public Projet() {
		super();
	}

	public Projet(String id_prj, String type_prj, String lieu_prj, String titre_prj, Date date_depart, Float duree_prj) {
		
		this.id_prj = id_prj;
		this.lieu_prj = lieu_prj;
		this.titre_prj = titre_prj;
		this.date_depart = date_depart;
		this.duree_prj = duree_prj;
		setType_prj(type_prj);
	}




	public Projet(String id_prj,String type_prjet, String lieu_prj, String titre_prj,Date date_depart, Float duree_prj,String fk_cne_etd, String fk_id_prf, String fk_id_lab,
			String fk_id_ent ) {
		this.id_prj = id_prj;
		this.lieu_prj = lieu_prj;
		this.titre_prj = titre_prj;
		this.fk_cne_etd = fk_cne_etd;
		this.fk_id_prf = fk_id_prf;
		this.fk_id_lab = fk_id_lab;
		this.fk_id_ent = fk_id_ent;
		this.date_depart = date_depart;
		this.duree_prj = duree_prj;
		setType_prj(type_prjet);
		// see if it's important to add an else clause where
		// the value is set to "null"
	}




	public String getId_prj() {
		return id_prj;
	}

	public void setId_prj(String id_prj) {
		this.id_prj = id_prj;
	}

	public String getLieu_prj() {
		return lieu_prj;
	}

	public void setLieu_prj(String lieu_prj) {
		this.lieu_prj = lieu_prj;
	}

	public String getTitre_prj() {
		return titre_prj;
	}

	public void setTitre_prj(String titre_prj) {
		this.titre_prj = titre_prj;
	}

	public Date getDate_depart() {
		return date_depart;
	}

	public void setDate_depart(Date date_depart) {
		this.date_depart = date_depart;
	}

	public Float getDuree_prj() {
		return duree_prj;
	}

	public void setDuree_prj(Float duree_prj) {
		this.duree_prj = duree_prj;
	}

	public String getType_prj() {
		return type_prj.toString();
	}

	public void setType_prj(String typeProj) {
		if (typeProj.equalsIgnoreCase("PFE")) {
			this.type_prj = type.PFE;
		} else if (typeProj.equalsIgnoreCase("PFA")) {
			this.type_prj = type.PFA;
		} else if (typeProj.equalsIgnoreCase("Doctorat")) {
			this.type_prj = type.Doctorat;
		}
		else {
			this.type_prj = null;
			JOptionPane.showMessageDialog(null, "Type de projet non Permissible", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getFk_id_prf() {
		return fk_id_prf;
	}

	public void setFk_id_prf(String fk_id_prf) {
		this.fk_id_prf = fk_id_prf;
	}

	public String getFk_id_lab() {
		return fk_id_lab;
	}

	public void setFk_id_lab(String fk_id_lab) {
		this.fk_id_lab = fk_id_lab;
	}

	public String getFk_id_ent() {
		return fk_id_ent;
	}

	public void setFk_id_ent(String fk_id_ent) {
		this.fk_id_ent = fk_id_ent;
	}


	public String getFk_cne_etd() {
		return fk_cne_etd;
	}


	public void setFk_cne_etd(String fk_cne_etd) {
		this.fk_cne_etd = fk_cne_etd;
	}


	@Override
	public String toString() {
		return "Projet [id_prj=" + id_prj + ", lieu_prj=" + lieu_prj + ", titre_prj=" + titre_prj + ", fk_id_prf="
				+ fk_id_prf + ", fk_id_lab=" + fk_id_lab + ", fk_id_ent=" + fk_id_ent + ", fk_cne_etd=" + fk_cne_etd
				+ ", date_depart=" + date_depart + ", duree_prj=" + duree_prj + ", type_prj=" + type_prj + "]";
	}



}
