package Metier.Gestionnaires;

import java.util.ArrayList;
import Metier.Entreprise;
import Persistance.DAO_Entreprise;

public class Gestionnaire_Entreprise {
	private DAO_Entreprise entreprise;

	public Gestionnaire_Entreprise() {
		super();
		entreprise = new DAO_Entreprise() ;
	}

	public ArrayList<Entreprise> getAllEntreprises() {
		return entreprise.getAll();
	}

	public Entreprise getEntrepriseById(String idEnt) {
		return entreprise.findById(idEnt);
	}

	public boolean ajouterEntreprise(Entreprise E) {
		return entreprise.addEntreprise(E);
	}

	public boolean supprimerEntreprise(String idEnt) {
		return entreprise.deleteEntreprise(idEnt);
	}

	public boolean modifierEntreprise(Entreprise E) {
		return entreprise.updateEntreprise(E);
	}

	public int nombreEntreprise() {
		return entreprise.countEntreprise();
	}

}
