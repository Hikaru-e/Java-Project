package Metier.Gestionnaires;

import java.util.ArrayList;

import Metier.Departement;
import Persistance.DAO_Departement;

public class Gestionnaire_Departement {

	private DAO_Departement departement;

	public Gestionnaire_Departement() {
		super();
		departement = new DAO_Departement();
	}

	public ArrayList<Departement> getAllDepartements() {
		return departement.getAll();
	}

	public Departement getDepartementByID(String idDeprt) {
		return departement.findByID(idDeprt);
	}

	public boolean ajouterDepartement(Departement D) {
		return departement.addDepartement(D);
	}

	public boolean supprimerDepartement(String idDeprt) {
		return departement.DeleteDepartement(idDeprt);
	}

	public boolean modifierDepartement(Departement D) {
		return departement.UpdateDepartement(D);
	}

	public Integer countDepartement() {
		return departement.countDepartement();
	}

}
