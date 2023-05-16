package Metier.Gestionnaires;

import java.util.ArrayList;

import Metier.Etape;
import Persistance.DAO_Etape;

public class Gestionnaire_Etape {

	DAO_Etape pr;

	public Gestionnaire_Etape() {
		super();
		pr = new DAO_Etape();
	}

	public ArrayList<Etape> getEtapesByProjet(String id_proj) {
		return pr.getEtapesByProjet(id_proj);
	}

	public boolean ajouterEtape(Etape etape) {
		return pr.addEtape(etape);
	}

	public boolean modifierEtape(Etape etape) {
		return pr.updateEtape(etape);
	}

	public boolean supprimerEtape(String idEtp) {
		return pr.deleteEtape(idEtp);
	}

	public int countEtapeByProjet(String id_proj) {
		return pr.countEtapeByProjet(id_proj);
	}
	
	public Etape findEtapeById(String idEtape) {
		return pr.getEtapeById(idEtape);
	}
}
