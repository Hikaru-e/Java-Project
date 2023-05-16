package Presentation.Menus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Metier.Gestionnaires.Gestionnaire_Entreprise;
import Metier.Gestionnaires.Gestionnaire_Etudiant;
import Metier.Gestionnaires.Gestionnaire_Filiere;
import Metier.Gestionnaires.Gestionnaire_Laboratoire;
import Metier.Gestionnaires.Gestionnaire_Professeur;
import Metier.Gestionnaires.Gestionnaire_Projet;

public class AccueilPanel_1 extends JPanel {
	private Gestionnaire_Etudiant gestionnaire_etd = new Gestionnaire_Etudiant();
	private Gestionnaire_Professeur gestionnaire_prf = new Gestionnaire_Professeur();
	private Gestionnaire_Projet gestionnaire_projet = new Gestionnaire_Projet();
	private Gestionnaire_Filiere gestionnaire_filiere = new Gestionnaire_Filiere();
	private Gestionnaire_Laboratoire gestionnaire_labo = new Gestionnaire_Laboratoire();
	private Gestionnaire_Entreprise gestionnaire_entrp = new Gestionnaire_Entreprise();
	private JPanel profPanel, etudiantPanel, projetPanel, filieresPanel, LaboPanel, EntrpPanel;

	public AccueilPanel_1() {
		super();

		this.inisialise();
		this.dessiner();
	}

	private void inisialise() {

		// Create the data panels

		profPanel = createDataPanel("Professeurs", String.valueOf(gestionnaire_prf.nombreProfesseur()));
		etudiantPanel = createDataPanel("Etudiants", String.valueOf(gestionnaire_etd.countEtudiants()));
		projetPanel = createDataPanel("Projets", String.valueOf(gestionnaire_projet.countProjets()));
		filieresPanel = createDataPanel("Filieres", String.valueOf(gestionnaire_filiere.nombreFiliere()));
		LaboPanel = createDataPanel("Laboratoires", String.valueOf(gestionnaire_labo.nombreLaboratoire()));
		EntrpPanel = createDataPanel("Entreprise", String.valueOf(gestionnaire_entrp.nombreEntreprise()));


	}

	public void dessiner() {
		this.setLayout(new BorderLayout());

		// Add the data panels to the dashboard panel
		JPanel dashboardPanel = new JPanel();
		 dashboardPanel.setLayout(new GridLayout(0,2));
		
		
		dashboardPanel.add(profPanel);
		dashboardPanel.add(etudiantPanel);
		dashboardPanel.add(projetPanel);
		dashboardPanel.add(filieresPanel);
		dashboardPanel.add(LaboPanel);
		dashboardPanel.add(EntrpPanel);

		JPanel countContent = new JPanel();
		countContent.setLayout(new GridBagLayout());
		countContent.add(dashboardPanel);
		

		this.add(countContent, BorderLayout.CENTER);

	}

	private JPanel createDataPanel(String label, String data) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(200, 150));
		panel.setBackground(new Color(44, 62, 80)); // light red background color
		panel.setForeground(new Color(135, 206, 250)); // light blue foreground color
		panel.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 3));

		JLabel labelLabel = new JLabel(label, SwingConstants.CENTER);
		labelLabel.setFont(new Font("Arial", Font.BOLD, 20));
		labelLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		labelLabel.setForeground(Color.WHITE);

		JLabel dataLabel = new JLabel(data, SwingConstants.CENTER);
		dataLabel.setFont(new Font("Arial", Font.PLAIN, 60));
		dataLabel.setForeground(new Color(255, 102, 0));

		panel.add(labelLabel, BorderLayout.NORTH);
		panel.add(dataLabel, BorderLayout.CENTER);

		return panel;
	}


	


}
