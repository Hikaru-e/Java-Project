package Presentation;

import java.sql.Connection;
import java.sql.Date;

import Metier.Projet;
import Metier.Gestionnaires.Gestionnaire_Departement;
import Metier.Gestionnaires.Gestionnaire_Entreprise;
import Metier.Gestionnaires.Gestionnaire_Etape;
import Metier.Gestionnaires.Gestionnaire_Etudiant;
import Metier.Gestionnaires.Gestionnaire_Filiere;
import Metier.Gestionnaires.Gestionnaire_Laboratoire;
import Metier.Gestionnaires.Gestionnaire_Professeur;
import Metier.Gestionnaires.Gestionnaire_Projet;
import Persistance.Connexion;

public class testingRequetes {
	public static void main(String[] args) {

		Date d = new Date(2002, 1, 1);

		Connection con = new Connexion().ConnectDB();


		
		Gestionnaire_Projet g1 = new Gestionnaire_Projet();
		Gestionnaire_Departement g2 = new Gestionnaire_Departement();
		Gestionnaire_Entreprise g3 = new Gestionnaire_Entreprise();
		Gestionnaire_Etape g4 = new Gestionnaire_Etape();
		Gestionnaire_Etudiant g5 = new Gestionnaire_Etudiant();
		Gestionnaire_Filiere g6 = new Gestionnaire_Filiere();
		Gestionnaire_Laboratoire g7 = new Gestionnaire_Laboratoire();
		Gestionnaire_Professeur g8 = new Gestionnaire_Professeur();

		// ==========> Projet requests

//		List<Projet> listProj = new ArrayList<Projet>(g1.getAllProjects());
//		System.out.println(listProj);
		g1.ajouterProject(new Projet("2", "PFE", "kh", "9bal", d, 20f));
//		System.out.println(g1.countProjets());
//
//		Projet cc = g1.getProjectById("1");
//		System.out.println(cc);
//		
//		g1.modifierProject(new Projet("1", "PFE", "ba3d", "hh", d, 20f));
//		System.out.println(g1.getProjectById("1"));
//		g1.supprimerProject("1");

		// ==========> Departement requests

//		List<Departement> listDep = new ArrayList<Departement>(g2.getAllDepartements());
//		System.out.println(listDep);
//		g2.ajouterDepartement(new Departement("1", "GI", "hh"));
//		System.out.println(g2.countDepartement());
//
//		Departement cc = g2.getDepartementByID("1");
//		System.out.println(cc);
//		
//		g2.modifierDepartement(new Departement("1", "machi GI", "hh"));
//		System.out.println(g2.getDepartementByID("1"));
//		g2.supprimerDepartement("1");

		// ==========> Entreprise requests

//		List<Entreprise> listEntr = new ArrayList<Entreprise>(g3.getAllEntreprises());
//		System.out.println(listEntr);
//		g3.ajouterEntreprise(new Entreprise("1", "2", "2", "2", "2", "2"));
//		System.out.println(g3.nombreEntreprise());
//
//		Entreprise cc = g3.getEntrepriseById("1");
//		System.out.println(cc);
//		
//		g3.modifierEntreprise(new Entreprise("1", "ljadid", "2", "2", "2", "2"));
//		System.out.println(g3.getEntrepriseById("1"));
//		g3.supprimerEntreprise("1");

		// ==========> Etape requests

//		List<Etape> listEtape = new ArrayList<Etape>(g4.getEtapesByProjet("1"));
//		System.out.println(listEtape);
//		g4.ajouterEtape(new Etape("1", "1", d, "1", "1"));
//		System.out.println(g4.getEtapesByProjet("1").size());
//
//		System.out.println(g4.countEtapeByProjet("1"));
//		g4.modifierEtape(new Etape("1", "2", d, "1", "1"));
//		g4.supprimerEtape("1");

		// ==========> Etudiant requests

//		List<Etudiant> listEtd = new ArrayList<Etudiant>(g5.getAllEtudiants());
//		System.out.println(listEtd);
//		g5.ajouterEtudiant(new Etudiant("1", "1", "1", "1", "1", "1"));
//		System.out.println(g5.countEtudiants());
//
//		Etudiant cc = g5.getEtudiantCNE("1");
//		System.out.println(cc);
//		
//		g5.modifierEtudiant(new Etudiant("1", "2", "2", "1", "1", "1"));
//		System.out.println(g5.getEtudiantCNE("1"));
//		g5.supprimerEtudiant("1");

		// ==========> Filiere requests

//		List<Filiere> listFil = new ArrayList<Filiere>(g6.getAllFilieres());
//		System.out.println(listFil);
//		g6.ajouterFiliere(new Filiere("5", null));
//		System.out.println(g6.nombreFiliere());
//
//		Filiere cc = g6.getFiliereByNom("1");
//		System.out.println(cc);
//		
//		g6.modifierFiliere("1",new Filiere("6", null));
//		System.out.println(g6.getFiliereByNom("1"));
//		g6.supprimerFiliere("1");
		
		// ==========> Laboratoire requests
		
//		List<Laboratoire> listLab = new ArrayList<Laboratoire>(g7.getAllLaboratoires());
//		System.out.println(listLab);
//		g7.ajouterLaboratoire(new Laboratoire("1", "1", "1"));
//		System.out.println(g7.getEtudiantbyLab("1"));
//		System.out.println(g7.nombreLaboratoire());
//		
//		Laboratoire cc = g7.getLaboratoireById("1");
//		System.out.println(cc);
//		
//		g7.modifierLaboratoire(new Laboratoire("1", "2", "2"));
//		System.out.println(g7.getLaboratoireById("1"));
//		g7.supprimerLaboratoire("1");
		
		// ==========> Professeur requests
		
//		List<Professeur> listProf = new ArrayList<Professeur>(g8.getAllProfs());
//		System.out.println(listProf);
//		g8.ajouterProfesseur(new Professeur("1", "1", "1", "1", "1", "1", "1"));
//		System.out.println(g8.nombreProfesseur());
//		
//		Professeur cc = g8.getProfesseurById("1");
//		System.out.println(cc);
//		
//		g8.modifierProfesseur(new Professeur("1", "2", "2", "1", "1", "1", "1"));
//		System.out.println(g8.getProfesseurById("1"));
//		g8.supprimerProfesseur("1");
	}
}
