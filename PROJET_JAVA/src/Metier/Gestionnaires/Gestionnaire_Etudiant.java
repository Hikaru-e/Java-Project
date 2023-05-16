package Metier.Gestionnaires;


import java.util.ArrayList;

import Metier.Etudiant;
import Persistance.DAO_Etudiant;




public class Gestionnaire_Etudiant {
	
	private DAO_Etudiant etudiant;
	
	public Gestionnaire_Etudiant() {
		super();
		etudiant = new DAO_Etudiant();
	}

	
	public ArrayList<Etudiant> getAllEtudiants(){
		return etudiant.getAll();
	}
	
	public Etudiant getEtudiantCNE(String CNE) {
		return etudiant.findByCNE(CNE);
	}
	
	public boolean ajouterEtudiant(Etudiant e) {
		return etudiant.addEtudiant(e);
	}
	
	
	public boolean supprimerEtudiant(String CNE) {
		return etudiant.DeleteEtudiant(CNE);
	}
	
	public boolean modifierEtudiant(Etudiant e) {
		return etudiant.updateEtudiant(e);
	}
	
	public void modifierPassword(Etudiant e) {
		 etudiant.modifierPassword(e);
	}
	public int countEtudiants() {
		return etudiant.countEtudiant();
	}
}



