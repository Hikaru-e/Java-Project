package Metier.Gestionnaires;

import java.util.ArrayList;

import Metier.Projet;
import Persistance.DAO_Projet;

public class Gestionnaire_Projet {

	private DAO_Projet pr;

	public Gestionnaire_Projet() {
		super();
		pr = new DAO_Projet();
	}

	public ArrayList<Projet> getAllProjects() {
		return pr.getAllProjects();
	}

	public Projet getProjectById(String idProj) {
		return pr.getProjectById(idProj);
	}

	public boolean ajouterProject(Projet project) {
		return pr.addProject(project);
	}

	public boolean modifierProject(Projet project) {
		return pr.updateProject(project);

	}

	public boolean supprimerProject(String idProj) {
		return pr.deleteProject(idProj);

	}

	public Integer countProjets() {
		return pr.countProjets();

	}

	public ArrayList<Projet> getProjetByProf(String id_prof) {
		return pr.getProjetByProf(id_prof);

	}
	
	public boolean addProjetEtape(String id_proj, String id_etape) {
		return pr.addProjetEtape(id_proj, id_etape);
	}
	
	public boolean deleteProjetEtape(String id_proj, String id_etape) {
		return pr.deleteProjetEtape(id_proj, id_etape);
	}
//	public boolean ifProfHasProjet(String id_proj) {
//		return pr.ifProfHasProjet(id_proj);
//	}

}
