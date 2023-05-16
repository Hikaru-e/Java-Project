package Metier.Gestionnaires;

import java.util.ArrayList;

import Metier.Etudiant;
import Metier.Professeur;
import Persistance.DAO_Professeur;

public class Gestionnaire_Professeur {
	private DAO_Professeur professeur;

	public Gestionnaire_Professeur() {
		super();
		professeur = new DAO_Professeur();
	}
	public ArrayList <Professeur> getAllProfs()
	{
		return professeur.getAll();
	}
	
	public Professeur getProfesseurById(String idProf)
	{
		return professeur.findById(idProf);
	}
	
	public boolean ajouterProfesseur(Professeur P)
	{
		return professeur.addProfesseur(P);
	}
	
	public boolean supprimerProfesseur(String idProf) 
	{
		return professeur.deleteProfesseur(idProf);
	}
	
	public boolean modifierProfesseur(Professeur P)
	{
		return professeur.updateProfesseur(P);
	}
	
	public int nombreProfesseur()
	{
		return professeur.countProfesseur();
	}
	public void modifierPassword(Professeur p) {
		professeur.modifierPassword(p);
	}
	
	public boolean addProfFiliere(String id_prof, String id_filiere) {
		return professeur.addProfFiliere(id_prof, id_filiere);
	}
	
	public boolean deleteProfFiliere(String id_prof, String id_filiere) {
		return professeur.deleteProfFiliere(id_prof, id_filiere);
	}
	
}
