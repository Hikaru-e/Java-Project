package Presentation;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Controlleur {
	
	private Home login;
	private DashboardMenu menuAdmin;
	private DashboardMenuProf menuProf;
	private DashboardMenuChefDepart menuChefDepart;
	private DashboardMenuCoordFiliere CoordFiliere;
	private DashboardEtudiant etudiant;
	private DashboardCreationProjet menuProjet;
	
	
	public Controlleur() {
		super();
	}
	public void demmarrerApplication() {
		this.login = new Home(this);
		this.login.setVisible(true);
		this.login.setTitle("Login");
		this.login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.login.setBounds(100, 100, 729, 476);
		this.login.setLocationRelativeTo(null);
	}
	
	public void afficheAdminPanel() {
		this.login.dispose(); 
		this.menuAdmin = new DashboardMenu(this);
		this.menuAdmin.setVisible(true);
		this.menuAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.menuAdmin.setTitle("Dashboard Admin");
		this.menuAdmin.setSize(1280, 720);
		this.menuAdmin.setLocationRelativeTo(null);

		
	}
	
	public void afficheProfPanel() {
		this.login.dispose(); 
		this.menuProf = new DashboardMenuProf(this);
		this.menuProf.setVisible(true);
		this.menuProf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.menuProf.setTitle("Dashboard Prof");
		this.menuProf.setSize(1280, 720);
		this.menuProf.setLocationRelativeTo(null);
		
	}
	public void afficheChefDepartPanel() {
		this.login.dispose(); 
		this.menuChefDepart = new DashboardMenuChefDepart(this);
		this.menuChefDepart.setVisible(true);
		this.menuChefDepart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.menuChefDepart.setTitle("Dashboard Chef Departement");
		this.menuChefDepart.setSize(1280, 720);
		this.menuChefDepart.setLocationRelativeTo(null);
		
	}
	
	public void afficheCoordFilierPanel() {
		this.login.dispose(); 
		this.CoordFiliere = new DashboardMenuCoordFiliere(this);
		this.CoordFiliere.setVisible(true);
		this.CoordFiliere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.CoordFiliere.setTitle("Dashboard Coordonateur Filiere");
		this.CoordFiliere.setSize(1280, 720);
		this.CoordFiliere.setLocationRelativeTo(null);
		
	}
	
	public void afficheEtudiantPanel() {
		this.login.dispose(); 
		this.etudiant = new DashboardEtudiant(this);
		this.etudiant.setVisible(true);
		this.etudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.etudiant.setTitle("Dashboard Etudiant");
		this.etudiant.setSize(new Dimension(1280,720));
		this.etudiant.setLocationRelativeTo(null);
		
	}
	public void afficheProjetPanel() {
		this.menuProjet = new DashboardCreationProjet(this);
		this.menuProjet.setVisible(true);
		this.menuProjet.setTitle("Creation Du Projet");
		this.menuProjet.setSize(new Dimension(1280,720));
		this.menuProjet.setLocationRelativeTo(null);
	}
	
}
