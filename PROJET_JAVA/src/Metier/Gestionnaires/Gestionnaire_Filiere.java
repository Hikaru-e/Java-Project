package Metier.Gestionnaires;

import java.util.ArrayList;

import Metier.Filiere;
import Persistance.DAO_Filiere;

public class Gestionnaire_Filiere {
	private DAO_Filiere filiere;

	public Gestionnaire_Filiere() {
		super();
		filiere = new DAO_Filiere();
	}

	public ArrayList<Filiere> getAllFilieres() {
		return filiere.getAll();
	}

	public Filiere getFiliereByNom(String nomFil) {
		return filiere.findByNom(nomFil);
	}

	public boolean ajouterFiliere(Filiere F) {
		return filiere.addFiliere(F);
	}

	public boolean supprimerFiliere(String nomFil) {
		return filiere.deleteFiliere(nomFil);
	}

	public boolean modifierFiliere(String nomFil, Filiere F) {
		return filiere.updateFiliere(nomFil, F);
	}

	public int nombreFiliere() {
		return filiere.countFiliere();
	}

}
