package Metier.Gestionnaires;

import java.util.ArrayList;

import Metier.Etudiant;
import Metier.Laboratoire;
import Persistance.DAO_Laboratoire;

public class Gestionnaire_Laboratoire {
	private DAO_Laboratoire lab;

	public Gestionnaire_Laboratoire() {
		super();
		lab= new DAO_Laboratoire();
	}
	
	public ArrayList<Laboratoire> getAllLaboratoires()
	{
		return lab.getAll();
	}
	
	public Laboratoire getLaboratoireById(String idLab)
	{
		return lab.findById(idLab);
	}
	
	public boolean ajouterLaboratoire(Laboratoire L)
	{
		return lab.addLaboratoire(L);
	}
	
	
	public boolean supprimerLaboratoire(String idLab) 
	{
		return lab.deleteLaboratoire(idLab);
	}
		
	
	public boolean modifierLaboratoire(Laboratoire L)
	{
		
		return lab.updateLaboratoire(L);
	}
	
	public int nombreLaboratoire()
	{
        return lab.countLaboratoire();
    }
	
	public ArrayList<Etudiant> getEtudiantbyLab(String id_lab)
	{
		return lab.getEtudiantbyLab(id_lab);
	}
}
